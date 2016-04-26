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

	/**
	 * Metoda vraca listu svih mogucih termina
	 * @return lista termina
	 * @throws java.sql.SQLException
	 */
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
	 * @param eventoviUTerminu - Svi eventi kojima je zajednicki jedan termin
	 * @param s - sala odredjenog tipa
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
	 * @param sale - sale odrdjenog tipa
	 * @param eventoviUTerminu - eventi u odredjenom terminu
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

	/**
	 * Metoda bacuje novi event u tabelu
	 * @param host - Razlog zakazivanja sale
	 * @param termin_id - ID termina za koji je event zakazan
	 * @param sala_id - ID sale za koju je event zakazan
	 * @throws java.sql.SQLException
	 */
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

	/**
	 * Metoda vraca ID sale iz baze na osnovu njenog naziva
	 * @param sala - naziv sale
	 * @return - id sale
	 * @throws java.sql.SQLException
	 */
	public int vratiIdSale(String sala) throws SQLException{
		Connection conn = konektor.connect();
		String upit = "select sala_id from sala where naziv_sale =" + "'" + sala + "'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery(upit);
		rs.next();
		int id = rs.getInt("sala_id");
		rs.close();
		ps.close();
		conn.close();
		return id;
	}
	
	/**
	 * Metoda vraca sve zauzete termine iz eventova na osnovu ID sale.
	 * Ukoliko se ID sale poklapa sa ID sale iz eventa, odgovarajuci termin
	 * se ubacuje u listu.
	 * @param idSale
	 * @return lista zauzetih termina
	 * @throws SQLException
	 */
	public LinkedList<Termin> vratiZauzeteTermine(int idSale) throws SQLException{
		Connection conn = konektor.connect();
		LinkedList<Termin> termini = new LinkedList<>();
		String upit = "SELECT termin.termin_id, vreme, datum FROM termin inner join event on event.termin_id = termin.termin_id WHERE event.sala_id =" + "'" + idSale + "'";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(upit);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt(1);
			int vreme = rs.getInt(2);
			int datum = rs.getInt(3);
			termini.add(new Termin(id, vreme, datum));
		}
		return termini;
	}
	
}
