package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import models.Workout;
import models.WorkoutEntry;
import play.db.DB;

public class WorkoutDao {

	private static final WorkoutDao INSTANCE = new WorkoutDao();

	public static WorkoutDao get() {
		return INSTANCE;
	}

	private WorkoutDao() {}

	public List<Workout> getUserWorkouts(int userId) {
		List<Workout> workouts = new ArrayList<Workout>();
		Connection connection = null;
		try {
			connection = DB.getConnection();
			PreparedStatement p = connection.prepareStatement("SELECT workouts.*, gyms.gym_name, workout_entries.*, exercises.exercise_name "
					+ "FROM workouts "
					+ "LEFT JOIN gyms USING (gym_id) "
					+ "LEFT JOIN workout_entries USING (workout_id) "
					+ "LEFT JOIN exercises USING (exercise_id) "
					+ "WHERE user_id = ? "
					+ "ORDER BY workouts.finished_at DESC");
			p.setInt(1, userId);
			ResultSet resultSet = p.executeQuery();
			TreeMap<Integer, Workout> workoutsTree = new TreeMap<Integer, Workout>();

			while (resultSet.next()) {
				int id = resultSet.getInt("workout_id");
				Workout w;
				if (!workoutsTree.containsKey(id)) {
					int gymId = resultSet.getInt("gym_id");
					Timestamp startedAt = resultSet.getTimestamp("started_at");
					Timestamp finishedAt = resultSet.getTimestamp("finished_at");
					String note = resultSet.getString("note");
					w = new Workout(userId, gymId, startedAt, finishedAt);
					w.setId(id);
					w.setGymName(resultSet.getString("gym_name"));
					w.setNote(note);
					workoutsTree.put(id, w);

					workouts.add(w);
				} else {
					w = workoutsTree.get(id);
				}

				resultSet.getInt("exercise_id");
				if (!resultSet.wasNull()) {
					WorkoutEntry we = new WorkoutEntry();
					we.setExerciseId(resultSet.getInt("exercise_id"));
					we.setRepsPerSet(resultSet.getInt("reps_per_set"));
					we.setSetsCount(resultSet.getInt("set_count"));
					we.setWeight(resultSet.getDouble("weight"));
					we.setExerciseName(resultSet.getString("exercise_name"));

					w.addWorkoutEntry(we);
				}
			}
			resultSet.close();
			p.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return workouts;
	}

	public boolean create(Workout workout) {
		Connection connection = null;
		try {
			connection = DB.getConnection();
			connection.setAutoCommit(false);
			if (workout.getStartedAt() == null || workout.getFinishedAt() == null)
				return false;

			PreparedStatement p = connection.prepareStatement("INSERT INTO "
					+ "workouts(user_id, gym_id, started_at, finished_at, note) "
					+ "VALUES (?, ?, ?, ?, ?) "
					+ "RETURNING workout_id");

			p.setInt(1, workout.getUserId());
			if (workout.getGymId() == null)
				p.setNull(2, Types.INTEGER);
			else
				p.setInt(2, workout.getGymId());
			p.setTimestamp(3, workout.getStartedAt());
			p.setTimestamp(4, workout.getFinishedAt());
			if (workout.getGymId() == null)
				p.setNull(5, Types.VARCHAR);
			else
				p.setString(5, workout.getNote());

			p.execute();
			ResultSet resultSet = p.getResultSet();
			resultSet.next();
			int workoutId = resultSet.getInt("workout_id");
			workout.setId(workoutId);

			resultSet.close();
			p.close();

			for (WorkoutEntry we : workout.getWorkoutEntries()) {
				we.setWorkoutId(workoutId);
				p = connection.prepareStatement("INSERT INTO "
						+ "workout_entries(workout_id, exercise_id, set_count, reps_per_set, weight) "
						+ "VALUES (?, ?, ?, ?, ?)");
				p.setInt(1, we.getWorkoutId());
				p.setInt(2, we.getExerciseId());
				p.setInt(3, we.getSetsCount());
				if (we.getRepsPerSet() == null)
					p.setNull(4, Types.INTEGER);
				else
					p.setInt(4, we.getRepsPerSet());
				if (we.getWeight() == null)
					p.setNull(5, Types.NUMERIC);
				else
					p.setDouble(5, we.getWeight());
				p.execute();
				p.close();
			}
			connection.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			play.Logger.info("ROLLBACK");
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int like(int workoutId, int userId) {

		Connection connection = null;
		try {
			connection = DB.getConnection();
			PreparedStatement p;
			try {
				p = connection.prepareStatement("INSERT INTO "
						+ "likes(user_id, workout_id) "
						+ "VALUES (?, ?)");

				p.setInt(1, userId);
				p.setInt(2, workoutId);
				p.execute();
				p.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			p = connection.prepareStatement("SELECT count(*) "
					+ "FROM likes "
					+ "WHERE workout_id = ?");
			p.setInt(1, workoutId);
			p.execute();
			ResultSet resultSet = p.getResultSet();
			resultSet.next();
			int likesCount = resultSet.getInt(1);
			p.close();

			return likesCount;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
