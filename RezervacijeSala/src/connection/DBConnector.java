package connection;

import java.sql.*;

public class DBConnector {
	Connection conn = null;

	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3308/sale_v2?autoReconnect=true&useSSL=false", "root", "root");
		return conn;
	}
}
