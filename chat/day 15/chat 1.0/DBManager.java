package roberto.day15.database.chat;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBManager {

	private static DBManager _instance;
	private static LoadConfigDB loadConfigDB;
	
	static {
		try {
			loadConfigDB = new LoadConfigDB();
			loadConfigDB.loadProps();
			String driverMysql = (String) loadConfigDB.getProperties().get("MySqlDriver");
			Class.forName(driverMysql);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DBManager() {
		_instance = this;
	}
	
	public static synchronized DBManager getInstance() {
		if(_instance == null)
			_instance = new DBManager();
		return _instance;
	}
		
	public Connection getMySqlConnection() throws SQLException{
		String url = (String) loadConfigDB.getProperties().get("MySqlUrl");
		String username = (String) loadConfigDB.getProperties().get("MySqlUsername");
		String passwrod = (String) loadConfigDB.getProperties().get("MySqlPassword");
		return DriverManager.getConnection(url, username, passwrod);
	}
	
//	public Connection getPostgreSqlConnection() throws SQLException {
//		return DriverManager.getConnection("URL", "username", "password");
//	}
}
