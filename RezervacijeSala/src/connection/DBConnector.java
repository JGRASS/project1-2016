package connection;

import java.sql.*;

public class DBConnector {
	Connection conn = null;

	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sale_v2", "root", "kosarkas95");
		return conn;
	}
}
