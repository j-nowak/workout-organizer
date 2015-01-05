package database;

import java.sql.Connection;
import java.sql.SQLException;

import models.User;
import play.db.DB;

public class UsersDao {

	private static final UsersDao INSTANCE = new UsersDao();

	private UsersDao() {
	}
	
	public static UsersDao get() {
		return INSTANCE;
	}
	
	public boolean insert(User user) {
		Connection connection = null;
		try {
			connection = DB.getConnection();
			String sql = connection.nativeSQL("INSERT INTO users(login, email, password_digest, first_name, last_name) VALUES" + 
					"  ('" + user.getLogin() + "','" + user.getEmail() + "','" + user.getPassword() + "','" + user.getFirstName() + "','" + user.getLastName() + "')");
			play.Logger.info("Insert user: " + sql);
			connection.createStatement().execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
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
	
}
