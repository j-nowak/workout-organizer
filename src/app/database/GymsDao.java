package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Address;
import models.Coordinates;
import models.Gym;
import play.db.DB;

public class GymsDao {
	
	public static GymsDao get() {
		return INSTANCE;
	}
	
	public List<Gym> getAll() {
		List<Gym> gyms = new ArrayList<Gym>();
		
		Connection connection = null;
		try {
			connection = DB.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM gyms");
			
			while (resultSet.next()) {
				String name = resultSet.getString("gym_name");
				String city = resultSet.getString("city");
				String street = resultSet.getString("street");
				double longitude = resultSet.getDouble("longitude");
				double latitude = resultSet.getDouble("latitude");
				String url = resultSet.getString("url");
				Gym g = new Gym(name, new Address(city, street, new Coordinates(longitude, latitude))); //TODO if not defined
				g.setUrl(url);
				//TODO rating
				gyms.add(g);
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

		return gyms;
	}
	
	private static final GymsDao INSTANCE = new GymsDao();
	
	private GymsDao() {}

}
