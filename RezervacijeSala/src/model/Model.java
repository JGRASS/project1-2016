package model;

import java.util.LinkedList;

import connection.DBConnector;
import domen.Event;

import java.sql.*;
public class Model {
	DBConnector konektor = new DBConnector();

	public LinkedList<Event> prikupiSale(String tipSale) throws SQLException {
		LinkedList<Event> dogadjaji = new LinkedList<Event>();
		Connection conn = konektor.connect();
		String upit = null;
		if (tipSale == "RacunskiCentar") {
			upit = "select naziv_sale, event_host, vreme, datum from event inner join sala on event.sala_id = sala.sala_id inner join termin on event.termin_id = termin.termin_id where tip_sale = 'rc'";
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String naziv = rs.getString(1);
				String host = rs.getString(2);
				int vreme = rs.getInt(3);
				int datum = rs.getInt(4);
				Event e = new Event(host, naziv, datum, vreme);
				dogadjaji.add(e);
			}
			rs.close();
			ps.close();
			conn.close();
		}
		return dogadjaji;
	}

}
