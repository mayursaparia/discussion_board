package neu.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import neu.edu.data.UserRegistration;
import neu.edu.data.UserSession;
import neu.edu.database.DatabaseConnector;

public class RegisterDAO {

	private Connection connection;
	boolean registerStatus = false;

	public RegisterDAO() {

	}

	public boolean RegisterUser(String username, String firstname, String lastname, String password, String email) {

		PreparedStatement pst = null;
		UserSession userSession = null;
		String role = "user";

		try {
			connection = DatabaseConnector.getInstance().getConnection();
			pst = connection.prepareStatement("INSERT INTO USER VALUES (?,?,?,md5(?),?,?);");
			pst.setString(1, username);
			pst.setString(2, firstname);
			pst.setString(3, lastname);
			pst.setString(4, password);
			pst.setString(5, email);
			pst.setString(6, role);

			pst.execute();
			
			registerStatus = true;


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
		return registerStatus;

	}

}