package database;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Map.Entry;

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
			String passwordDigest = byteArrayToHexString(MessageDigest
					.getInstance("SHA-1").digest(user.getPassword().getBytes()));
			String sql = connection.nativeSQL("INSERT INTO users(login, email, password_digest, first_name, last_name) VALUES" + 
					"  ('" + user.getLogin() + "','" + user.getEmail() + "','" + passwordDigest + "','" + user.getFirstName() + "','" + user.getLastName() + "')");
			play.Logger.info("Insert user: " + sql);
			connection.createStatement().execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchAlgorithmException e) {
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
			String passwordDigest = byteArrayToHexString(MessageDigest
					.getInstance("SHA-1").digest(password.getBytes()));
			ResultSet resultUser = getuserStatement.executeQuery("SELECT * FROM users "
					+ "WHERE (login = '" + login + "' OR email = '" + login + "') AND password_digest = '" + passwordDigest + "'");
			
			User user = null;
			if (resultUser.next()) {
				Logger.info("User " + resultUser.getString("login") + " logged in!");
				user = new User();
				user.setId(resultUser.getInt("user_id"));
				user.setLogin(resultUser.getString("login"));
				user.setEmail(resultUser.getString("email"));
			}
			
			resultUser.close();
			getuserStatement.close();
			
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
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
	
	private static String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	public User getById(String userId) { //TODO change to userId
		Connection connection = null;
		try {
			connection = DB.getConnection();
			Statement getuserStatement = connection.createStatement();
			ResultSet resultUser = getuserStatement.executeQuery("SELECT * FROM users "
					+ "WHERE email = '" + userId + "'");
			
			User user = null;
			if (resultUser.next()) {
				user = new User();
				user.setId(resultUser.getInt("user_id"));
				user.setLogin(resultUser.getString("login"));
				user.setEmail(resultUser.getString("email"));
				user.setFirstName(resultUser.getString("first_name"));
				user.setLastName(resultUser.getString("last_name"));
				user.setDateOfBirth(resultUser.getDate("date_of_birth"));
				user.setHeight(resultUser.getInt("height"));
				user.setWeight(resultUser.getDouble("weight"));
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

	public boolean checkPasswordForUser(String userId, String password) {
		Connection connection = null;
		try {
			String passwordDigest = byteArrayToHexString(MessageDigest
					.getInstance("SHA-1").digest(password.getBytes()));
			
			connection = DB.getConnection();
			Statement getuserStatement = connection.createStatement();
			ResultSet resultUser = getuserStatement.executeQuery("SELECT * FROM users "
					+ "WHERE email = '" + userId + "' AND password_digest = '" + passwordDigest + "'");
			
			boolean result;
			if (resultUser.next()) {
				result = true;
			}
			else {
				result = false;
			}
			
			resultUser.close();
			getuserStatement.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NoSuchAlgorithmException e) {
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

	public void changePassword(String userId, String password) {
		Connection connection = null;
		try {
			String passwordDigest = byteArrayToHexString(MessageDigest
					.getInstance("SHA-1").digest(password.getBytes()));
			
			connection = DB.getConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE users SET password_digest = '"+ passwordDigest + "' where email = '" + userId + "';";
			statement.executeUpdate(sql);
			play.Logger.info("Password changed!");
			
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
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
	}

	public boolean update(String userId, Map<String, String> toUpdate) {
		Connection connection = null;
		try {	
			connection = DB.getConnection();
			Statement statement = connection.createStatement();
			String sql = "UPDATE users SET ";
			for (Entry<String, String> entry : toUpdate.entrySet()) {
				sql += entry.getKey() + " = '" + entry.getValue() + "', ";
			}
			sql = sql.substring(0, sql.length() - 2);
			sql += " where email = '" + userId + "';";
			
			play.Logger.info(sql);
			statement.executeUpdate(sql);
			
			
			statement.close();
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
