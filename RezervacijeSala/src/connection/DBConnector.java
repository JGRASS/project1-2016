package connection;

import java.sql.*;

public class DBConnector {
	Connection conn = null;
	public Connection connect() {
		try {
			conn = DriverManager.getConnection
					("jdbc:mysql://localhost:3308/saledb","root","root");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}
}
