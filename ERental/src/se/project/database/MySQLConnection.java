package se.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Majey
 */
public class MySQLConnection implements DatabaseType {
	// Kết nối vào MySQL.
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String hostName = "localhost";
		String dbName = "ebike";
		String userName = "root";
		String password = "123456789";

		return getConnection(hostName, dbName, userName, password);
	}
    
	public  Connection getConnection(String hostName, String dbName, String userName, String password)
			throws SQLException, ClassNotFoundException {

		String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

		Connection conn = DriverManager.getConnection(connectionURL, userName, password);
		return conn;
	}
}