package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;
import neu.edu.database.DatabaseConnector;

public class ProfileDAO {

	private Connection connection;
	boolean updatePasswordStatus = false;

	public ProfileDAO() {

	}

	public UserSession UpdateUser(String username, String firstname, String lastname, String email, String olduser) {

		PreparedStatement pst = null;
		UserSession userSession = null;
		String role = "user";

		try {
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("UPDATE USER SET username = ?, first_name = ?, last_name = ?, email = ? WHERE username = ?;");
			pst.setString(1, username);
			pst.setString(2, firstname);
			pst.setString(3, lastname);
			pst.setString(4, email);
			pst.setString(5, olduser);

			pst.execute();
			
			
			userSession = new UserSession(username, firstname, lastname, email, role);


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userSession;

	}
	
	public boolean UpdateUserPassword(String password, String username) {

		PreparedStatement pst = null;

		try {
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("UPDATE USER SET password = md5(?) WHERE username = ?;");
			pst.setString(1, password);
			pst.setString(2, username);

			pst.execute();
			
			updatePasswordStatus = true;


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return updatePasswordStatus;

	}

}