package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.User;
import play.Logger;
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

	public User login(String login, String password) {
		Connection connection = null;
		try {
			connection = DB.getConnection();
			Statement getuserStatement = connection.createStatement();
			ResultSet resultUser = getuserStatement.executeQuery("SELECT * FROM users "
					+ "WHERE (login = '" + login + "' OR email = '" + login + "') AND password_digest = '" + password + "'");
			
			User user = null;
			if (resultUser.next()) {
				Logger.info("User " + resultUser.getString("login") + " logged in!"); //TODO
				user = new User();
				user.setLogin(resultUser.getString("login"));
				user.setEmail(resultUser.getString("email"));
			}
			
			resultUser.close();
			getuserStatement.close();
			
			return user;
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
	}
	
}
