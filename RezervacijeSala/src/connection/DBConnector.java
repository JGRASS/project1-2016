package connection;

import java.sql.*;

public class DBConnector {
	/**
	 * Glavni atribut klase - predstavlja samu konekciju
	 */
	Connection conn = null;
	
	String url = "jdbc:mysql://localhost:3306/sale_v2?autoReconnect=true&useSSL=false";
	String user = "root";
	String password = "Sennheiser95";

	/**
	 * Metoda vraca novu konekciju
	 * @return konekcija
	 * @throws java.sql.SQLException
	 */
	public Connection connect() throws SQLException {
		conn = DriverManager.getConnection(url, user, password);
		return conn;
	}

}
