package roberto.day15.database.chat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJDBC {

	public static void main(String[] args) throws SQLException {
		DBManager dbManager = DBManager.getInstance();
		Connection connection = dbManager.getMySqlConnection();
		
		String SQL = "USE PROCEACMI_DEV;";
		Statement stmt = connection.createStatement();
		stmt.execute(SQL);
		SQL = "SELECT *  FROM USERS LIMIT 4;";
		ResultSet resultSet = stmt.executeQuery(SQL);

		while (resultSet.next()) {
			int id = Integer.parseInt(resultSet.getString("id"));
			String usern = resultSet.getString("email");
			String pass = resultSet.getString("password");
			System.out.println(id + "--> username:" + usern + ", password: " + pass);
		}

		resultSet.close();
		stmt.close();
		connection.close();
	}

}
