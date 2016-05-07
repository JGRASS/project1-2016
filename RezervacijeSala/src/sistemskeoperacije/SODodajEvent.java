package sistemskeoperacije;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Sala;
import domen.Termin;
import model.Model;

public class SODodajEvent {

	public static void izvrsi(Model model, String host, String sala, int datum, int vreme, String tipSale)
			throws SQLException {
		int sala_id = 6;
		int termin_id = 5;
		LinkedList<Sala> sveSale = model.sveSaleDatogTipa(tipSale);
		for (Sala salica : sveSale) {
			if (salica.getNaziv_sale().equals(sala)) {
				sala_id = salica.getSala_id();
				break;
			}
		}
		LinkedList<Termin> sviTermini = model.sviTermini();
		for (Termin t : sviTermini) {
			if (t.getDatum() == datum && t.getVreme() == vreme) {
				termin_id = t.getTermin_id();
				break;
			}
		}
		model.unesiEvent(host, termin_id, sala_id);

	}
}
