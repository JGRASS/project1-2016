package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import connection.DBConnector;
import domen.Event;
import domen.Sala;
import domen.Termin;

public class Model {
	private static DBConnector konektor = new DBConnector();

	/*
	 * public LinkedList<Event> prikupiEventoveZaDatiTipSale(String tipSale)
	 * throws SQLException { LinkedList<Event> dogadjaji = new
	 * LinkedList<Event>(); Connection conn = konektor.connect(); String upit =
	 * null; upit =
	 * "select naziv_sale, event_host, vreme, datum, event.sala_id, event.termin_id, tip_sale from event inner join sala on event.sala_id = sala.sala_id inner join termin on event.termin_id = termin.termin_id where tip_sale = "
	 * + "'" + tipSale + "'"; PreparedStatement ps = (PreparedStatement)
	 * conn.prepareStatement(upit); ResultSet rs = ps.executeQuery(); while
	 * (rs.next()) { String naziv = rs.getString(1); String host =
	 * rs.getString(2); int vreme = rs.getInt(3); int datum = rs.getInt(4); int
	 * sala_id = rs.getInt(5); int termin_id = rs.getInt(6); String tip_sale =
	 * rs.getString(7); Event e = new Event(host, new Sala(sala_id, naziv,
	 * tip_sale), new Termin(termin_id, vreme, datum)); dogadjaji.add(e); }
	 * rs.close(); ps.close(); conn.close(); return dogadjaji; }
	 */
	/**
	 * Metoda vraca evente (sale sa svojim zakazanim terminima) iz baze podataka
	 * koji odgovaraju datom terminu i tipu sale.
	 * 
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 * @return lista objekata Event
	 * @throws java.sql.SQLException
	 */
	public LinkedList<Event> prikupiEventoveZaDatiTerminITipSale(int datum, int vreme, String tipSale)
			throws SQLException {
		LinkedList<Event> dogadjaji = new LinkedList<Event>();
		Connection conn = konektor.connect();
		String upit = null;
		upit = "select naziv_sale, event_host, vreme, datum, event.sala_id, event.termin_id, tip_sale from event inner join sala on event.sala_id = sala.sala_id inner join termin on event.termin_id = termin.termin_id where termin.vreme="
				+ "'" + vreme + "'" + " and termin.datum =" + "'" + datum + "'" + " and sala.tip_sale =" + "'" + tipSale
				+ "'";
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

	public LinkedList<Termin> sviTermini() throws SQLException {
		Connection conn;
		LinkedList<Termin> termini;
		String upit = null;
		conn = konektor.connect();
		upit = "select termin_id, vreme, datum from termin";
		termini = new LinkedList<Termin>();
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int termin_id = rs.getInt(1);
			int vreme = rs.getInt(2);
			int datum = rs.getInt(3);
			termini.add(new Termin(termin_id, vreme, datum));
		}
		rs.close();
		ps.close();
		conn.close();
		return termini;
	}

	/**
	 * Metoda proverava da li je data sala slobodna odnosno da li se ne poklapa
	 * sa nekom salom iz liste eventa.
	 * 
	 * @param eventoviUTerminu
	 * @param s
	 * @return true ako je sala slobodna u
	 */
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

	/**
	 * Metoda vraca listu slobodnih sala u datom terminu na osnovu liste sala
	 * odredjenog tipa i liste eventa.
	 * 
	 * @param sale
	 * @param eventoviUTerminu
	 * @return lista slobodnih sala
	 */
	public LinkedList<Sala> vratiSlobodneSaleUTerminu(LinkedList<Sala> sale, LinkedList<Event> eventoviUTerminu) {
		LinkedList<Sala> slobodneSale = new LinkedList<Sala>();

		for (Sala sala : sale) {
			if (daLiJeSalaSlobodna(eventoviUTerminu, sala)) {
				slobodneSale.add(sala);
			}
		}
		return slobodneSale;
	}

	/**
	 * Metoda vraca sale istog tipa
	 * 
	 * @param tipSale
	 * @return lista sala istog tipa
	 * @throws java.sql.SQLException
	 */
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

	public void unesiEvent(String host, int termin_id, int sala_id) throws SQLException {
		Connection conn = konektor.connect();
		String upit = "INSERT INTO event (event_host, event.termin_id, event.sala_id) VALUES (?,?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ps.setString(1, host);
		ps.setInt(2, termin_id);
		ps.setInt(3, sala_id);
		ps.execute();
		ps.close();
		conn.close();
	}

	/*
	 * public int vratiMaxIdSale() throws SQLException{ String upit =
	 * "SELECT sale_id from table ORDER BY id DESC LIMIT 1"; Connection conn =
	 * konektor.connect(); Statement statement = (Statement)
	 * conn.createStatement(); ResultSet rs = statement.executeQuery(upit); int
	 * max = rs.getInt(1); return max; }
	 */
}
