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
				//TODO - muscle groups
				//TODO - rating
				
				Logger.debug(e.getName(), e.getDescription(), e.getMovieUri());
				exercises.add(e);
			}
			
			exerciseSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
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
	
	private static final ExerciseDao INSTANCE = new ExerciseDao();
	
	private ExerciseDao() {}
	
}
