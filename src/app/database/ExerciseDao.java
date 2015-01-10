package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Exercise;
import play.Logger;
import play.db.DB;

public class ExerciseDao {
	
	public static ExerciseDao get() {
		return INSTANCE;
	}
	
	public List<Exercise> getAll() {
		List<Exercise> exercises = new ArrayList<Exercise>();
		
		Connection connection = null;
		try {
			connection = DB.getConnection();
			Statement statement = connection.createStatement();
			ResultSet exerciseSet = statement.executeQuery("SELECT * FROM exercises");
			
			while (exerciseSet.next()) {
				Exercise e = new Exercise();
				e.setName(exerciseSet.getString("exercise_name"));
				e.setDescription(exerciseSet.getString("description"));
				e.setMovieUri(exerciseSet.getString("movie_uri"));
				int exerciseId = exerciseSet.getInt("exercise_id");
				e.setRating(getExerciseRating(exerciseId));
				//TODO - muscle groups
				
				exercises.add(e);
			}
			
			exerciseSet.close();
			statement.close();
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

		return exercises;
	}
	
	private double getExerciseRating(int exerciseId) {
		double rating = 0;
		Connection connection = null;
		try {
			connection = DB.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT avg(rating) FROM exercise_ratings WHERE exercise_id = " + exerciseId);
			
			if (resultSet.next()) {
				rating = resultSet.getDouble(1);
			}
			
			resultSet.close();
			statement.close();
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
		
		return rating;
	}

	private static final ExerciseDao INSTANCE = new ExerciseDao();
	
	private ExerciseDao() {}
	
}
