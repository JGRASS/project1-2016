package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;

import connection.DBConnector;
import domen.Event;
import domen.Sala;
import domen.Termin;

public class Model {
	DBConnector konektor = new DBConnector();
/*
	public LinkedList<Event> prikupiEventoveZaDatiTipSale(String tipSale) throws SQLException {
		LinkedList<Event> dogadjaji = new LinkedList<Event>();
		Connection conn = konektor.connect();
		String upit = null;
		upit = "select naziv_sale, event_host, vreme, datum, event.sala_id, event.termin_id, tip_sale from event inner join sala on event.sala_id = sala.sala_id inner join termin on event.termin_id = termin.termin_id where tip_sale = "
				+ "'" + tipSale + "'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String naziv = rs.getString(1);
			String host = rs.getString(2);
			int vreme = rs.getInt(3);
			int datum = rs.getInt(4);
			int sala_id = rs.getInt(5);
			int termin_id = rs.getInt(6);
			String tip_sale = rs.getString(7);
			Event e = new Event(host, new Sala(sala_id, naziv, tip_sale), new Termin(termin_id, vreme, datum));
			dogadjaji.add(e);
		}
		rs.close();
		ps.close();
		conn.close();
		return dogadjaji;
	}
*/
	public LinkedList<Event> prikupiEventoveZaDatiTerminITipSale(int datum, int vreme, String tipSale) throws SQLException {
		LinkedList<Event> dogadjaji = new LinkedList<Event>();
		Connection conn = konektor.connect();
		String upit = null;
		upit = "select naziv_sale, event_host, vreme, datum, event.sala_id, event.termin_id, tip_sale from event inner join sala on event.sala_id = sala.sala_id inner join termin on event.termin_id = termin.termin_id where termin.vreme="
				+ "'" + vreme + "'" + " and termin.datum =" + "'" + datum + "'" + " and sala.tip_sale ="+ "'" + tipSale +"'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String naziv = rs.getString(1);
			String host = rs.getString(2);
			int vremeReal = rs.getInt(3);
			int datumReal = rs.getInt(4);
			int sala_id = rs.getInt(5);
			int termin_id = rs.getInt(6);
			String tip_sale = rs.getString(7);
			Event e = new Event(host, new Sala(sala_id, naziv, tip_sale), new Termin(termin_id, vremeReal, datumReal));
			dogadjaji.add(e);
		}
		rs.close();
		ps.close();
		conn.close();
		return dogadjaji;
	}

	private boolean daLiJeSalaSlobodna(LinkedList<Event> eventoviUTerminu, Sala s) {
		boolean slobodna = true;
		for (Event event : eventoviUTerminu) {
			if (s.equals(event.getSala())) {
				slobodna = false;
				break;
			}
		}
		return slobodna;
	}

	public LinkedList<Sala> vratiSlobodneSaleUTerminu(LinkedList<Sala> sale, LinkedList<Event> eventoviUTerminu) {
		LinkedList<Sala> slobodneSale = new LinkedList<Sala>();

		for (Sala sala : slobodneSale) {
			if (daLiJeSalaSlobodna(eventoviUTerminu, sala)) {
				slobodneSale.add(sala);
			}
		}
		return slobodneSale;
	}

	public LinkedList<Sala> sveSaleDatogTipa(String tipSale) throws SQLException {
		LinkedList<Sala> sale = new LinkedList<Sala>();
		Connection conn = konektor.connect();
		String upit = null;
		upit = "select sala_id, naziv_sale, tip_sale from sala where tip_sale =" + "'" + tipSale + "'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int sala_id = rs.getInt(1);
			String naziv_sale = rs.getString(2);
			String tip_sale = rs.getString(3);
			sale.add(new Sala(sala_id, naziv_sale, tip_sale));
		}
		rs.close();
		ps.close();
		conn.close();
		return sale;
	}

	public void unesiEvent(Event e) throws SQLException {
		Connection conn = konektor.connect();
		String upit = "INSERT INTO event (event_host, event.termin_id, event.sala_id) VALUES (?,?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ps.setString(1, e.getHost().trim());
		ps.setInt(2, e.getTermin().getTermin_id());
		ps.setInt(3, e.getSala().getSala_id());
		ps.execute();
	}

}
