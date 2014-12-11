CREATE SCHEMA workout_organizer;

CREATE TABLE exercises (
	exercise_id          serial  NOT NULL,
	exercise_name        varchar(100)  NOT NULL,
	description          text  ,
	movie_uri            varchar(200)  ,
	CONSTRAINT pk_exercises PRIMARY KEY ( exercise_id )
 );

CREATE TABLE gyms (
	gym_id               serial  NOT NULL,
	gym_name             varchar(100)  NOT NULL,
	address              varchar(100)  NOT NULL,
	latitude             real  ,
	longitude            real  ,
	url                  varchar(300)  ,
	CONSTRAINT pk_gyms PRIMARY KEY ( gym_id )
 );

CREATE TABLE muscle_groups (
	muscle_name          varchar(100)  NOT NULL,
	image                bytea  ,
	CONSTRAINT pk_muscle_groups PRIMARY KEY ( muscle_name )
 );

CREATE TABLE target_muscles (
	exercise_id          integer  NOT NULL,
	muscle_name          varchar(100)  NOT NULL
 );

CREATE INDEX idx_target_muscles ON target_muscles ( muscle_name );

CREATE INDEX idx_target_muscles_0 ON target_muscles ( exercise_id );

CREATE TABLE users (
	user_id              serial  NOT NULL,
	login                varchar(20)  NOT NULL,
	email                varchar(50)  NOT NULL,
	password_digest      char(40)  NOT NULL,
	first_name           varchar(100)  NOT NULL,
	last_name            varchar(100)  NOT NULL,
	height               integer  ,
	weight               numeric(5,2)  ,
	date_of_birth        date  ,
	CONSTRAINT pk_users PRIMARY KEY ( user_id ),
	CONSTRAINT unique_login UNIQUE ( login ) ,
	CONSTRAINT unique_mail UNIQUE ( email )
 );

ALTER TABLE users ADD CONSTRAINT height_chk CHECK ( height > 0 AND height < 300 );

ALTER TABLE users ADD CONSTRAINT weight_ckh CHECK ( weight > 0 );

CREATE TABLE workouts (
	workout_id           serial  NOT NULL,
	user_id              integer  NOT NULL,
	gym_id               integer  ,
	started_at           timestamp DEFAULT current_timestamp NOT NULL,
	finished_at          timestamp  NOT NULL,
	weight               numeric(5,2)  ,
	note                 varchar(300)  ,
	CONSTRAINT pk_workouts PRIMARY KEY ( workout_id )
 );

ALTER TABLE workouts ADD CONSTRAINT time_check CHECK ( started_at < finished_at );

ALTER TABLE workouts ADD CONSTRAINT weight_chk CHECK ( weight > 0 );

CREATE INDEX idx_workouts ON workouts ( user_id );

CREATE INDEX idx_workouts_0 ON workouts ( gym_id );

CREATE TABLE exercise_ratings (
	user_id              integer  NOT NULL,
	exercise_id          integer  NOT NULL,
	rating               integer  NOT NULL,
	CONSTRAINT pk_exercises_ratings PRIMARY KEY ( user_id, exercise_id )
 );

ALTER TABLE exercise_ratings ADD CONSTRAINT rating_chk CHECK ( rating BETWEEN 1 AND 10 );

CREATE INDEX idx_exercises_ratings ON exercise_ratings ( user_id );

CREATE INDEX idx_exercises_ratings_0 ON exercise_ratings ( exercise_id );

CREATE TABLE friendships (
	first_user_id        integer  NOT NULL,
	second_user_id       integer  NOT NULL,
	CONSTRAINT unique_friendships_rel UNIQUE ( first_user_id, second_user_id )
 );

ALTER TABLE friendships ADD CONSTRAINT proper_rel_check CHECK ( first_user_id < second_user_id );

CREATE TABLE gym_ratings (
	gym_id               integer  NOT NULL,
	user_id              integer  NOT NULL,
	rating               integer  NOT NULL,
	CONSTRAINT idx_gym_ratings PRIMARY KEY ( gym_id, user_id )
 );

ALTER TABLE gym_ratings ADD CONSTRAINT rating_chk CHECK ( rating BETWEEN 1 AND 10 );

CREATE TABLE likes (
	user_id              integer  NOT NULL,
	workout_id           integer  NOT NULL,
	CONSTRAINT idx_likes UNIQUE ( user_id, workout_id )
 );

CREATE TABLE workout_entries (
	workout_id           integer  NOT NULL,
	exercise_id          integer  NOT NULL,
	set_count            integer  NOT NULL,
	reps_per_set         integer  ,
	weight               numeric(5,2)  NOT NULL,
	CONSTRAINT idx_workout_entry PRIMARY KEY ( workout_id, exercise_id )
 );

ALTER TABLE workout_entries ADD CONSTRAINT non_negative_chk CHECK ( set_count > 0 AND reps_per_set > 0 AND weight >= 0 );

CREATE INDEX idx_workout_entry_0 ON workout_entries ( workout_id );

CREATE INDEX idx_workout_entry_1 ON workout_entries ( exercise_id );

CREATE VIEW detailed_news AS SELECT u.user_id as friend_id, u.first_name, u.last_name,
  f.first_user_id, f.second_user_id,
  g.gym_id, g.gym_name,
  w.started_at, w.finished_at, w.note,
  array_agg(DISTINCT tm.muscle_name ORDER BY tm.muscle_name) as muscles_names,
  count(l) as likes_count
FROM users u
JOIN friendships f ON u.user_id IN (f.first_user_id, f.second_user_id)
JOIN workouts w USING (user_id)
LEFT JOIN gyms g USING (gym_id)
LEFT JOIN workout_entries we USING (workout_id)
LEFT JOIN exercises e USING (exercise_id)
LEFT JOIN target_muscles tm USING (exercise_id)
LEFT JOIN likes l USING (workout_id)
GROUP BY u.user_id, f.first_user_id, f.second_user_id, w.workout_id, g.gym_id
ORDER BY w.finished_at DESC;

CREATE VIEW simple_news AS SELECT u.user_id AS friend_id, u.first_name, u.last_name,
    f.first_user_id, f.second_user_id,
    g.gym_id, g.gym_name,
    w.started_at, w.finished_at, w.note,
    count(l) as likes_count
  FROM users u
  JOIN friendships f ON u.user_id IN (f.first_user_id, f.second_user_id)
  JOIN workouts w ON u.user_id = w.user_id
  LEFT JOIN gyms g ON g.gym_id = w.gym_id
  LEFT JOIN likes l ON l.workout_id = w.workout_id
  GROUP BY u.user_id, f.first_user_id, f.second_user_id, g.gym_id, w.workout_id
  ORDER BY w.finished_at DESC;

CREATE TRIGGER update_weight_trigger
    AFTER INSERT OR UPDATE ON workouts
    FOR EACH ROW
    WHEN (NEW.weight IS NOT NULL)
    EXECUTE PROCEDURE update_weight();;

CREATE OR REPLACE FUNCTION update_weight() RETURNS TRIGGER AS $$
    DECLARE
        newer_workouts_count int;
    BEGIN
        IF TG_OP = 'INSERT' THEN
            UPDATE users SET weight = NEW.weight WHERE user_id = NEW.user_id;
        ELSIF TG_OP = 'UPDATE'  THEN
            newer_workouts_count = (SELECT COUNT(*) FROM workouts WHERE user_id = NEW.user_id AND started_at > NEW.started_at);
            IF newer_workouts_count = 0 THEN
                UPDATE users SET weight = NEW.weight WHERE user_id = NEW.user_id;
            END IF;
        END IF;

        RETURN NEW;
    END
$$ LANGUAGE plpgsql;

ALTER TABLE exercise_ratings ADD CONSTRAINT fk_exercises_ratings_users FOREIGN KEY ( user_id ) REFERENCES users( user_id ) ON DELETE CASCADE;

ALTER TABLE exercise_ratings ADD CONSTRAINT fk_exercises_ratings_exercises FOREIGN KEY ( exercise_id ) REFERENCES exercises( exercise_id ) ON DELETE CASCADE;

ALTER TABLE friendships ADD CONSTRAINT fk_friendships_users FOREIGN KEY ( first_user_id ) REFERENCES users( user_id );

ALTER TABLE friendships ADD CONSTRAINT fk_friendships_users2 FOREIGN KEY ( second_user_id ) REFERENCES users( user_id );

ALTER TABLE gym_ratings ADD CONSTRAINT fk_gym_ratings FOREIGN KEY ( gym_id ) REFERENCES gyms( gym_id ) ON DELETE CASCADE;

ALTER TABLE gym_ratings ADD CONSTRAINT fk_gym_ratings_0 FOREIGN KEY ( user_id ) REFERENCES users( user_id ) ON DELETE CASCADE;

ALTER TABLE likes ADD CONSTRAINT fk_likes_users FOREIGN KEY ( user_id ) REFERENCES users( user_id ) ON DELETE CASCADE;

ALTER TABLE likes ADD CONSTRAINT fk_likes_workouts FOREIGN KEY ( workout_id ) REFERENCES workouts( workout_id ) ON DELETE CASCADE;

ALTER TABLE target_muscles ADD CONSTRAINT fk_target_muscles FOREIGN KEY ( muscle_name ) REFERENCES muscle_groups( muscle_name ) ON DELETE CASCADE;

ALTER TABLE target_muscles ADD CONSTRAINT fk_target_muscles_exercises FOREIGN KEY ( exercise_id ) REFERENCES exercises( exercise_id );

ALTER TABLE workout_entries ADD CONSTRAINT fk_workout_entry_workouts FOREIGN KEY ( workout_id ) REFERENCES workouts( workout_id );

ALTER TABLE workout_entries ADD CONSTRAINT fk_workout_entry_exercises FOREIGN KEY ( exercise_id ) REFERENCES exercises( exercise_id );

ALTER TABLE workouts ADD CONSTRAINT fk_workouts_users FOREIGN KEY ( user_id ) REFERENCES users( user_id );

ALTER TABLE workouts ADD CONSTRAINT fk_workouts_gyms FOREIGN KEY ( gym_id ) REFERENCES gyms( gym_id );

