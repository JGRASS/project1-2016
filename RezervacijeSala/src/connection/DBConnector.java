package connection;

import java.sql.*;

public class DBConnector {
	Connection conn = null;
	String url = "jdbc:mysql://localhost:3306/sale_v2?autoReconnect=true&useSSL=false";
	String user = "root";
	String password = "Sennheiser95";

	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}
