package sistemskeoperacije;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Event;
import domen.Sala;
import model.Model;

public class SOVratiSlobodneSale {

	public static LinkedList<Sala> izvrsi(Model model, int datum, int vreme, String tipSale) throws SQLException {
		LinkedList<Sala> sale;
		LinkedList<Event> eventi;
		LinkedList<Sala> slobodne = new LinkedList<>();
		
		sale = model.sveSaleDatogTipa(tipSale);
		eventi = model.prikupiEventoveZaDatiTerminITipSale(datum, vreme, tipSale);
		slobodne = model.vratiSlobodneSaleUTerminu(sale, eventi);

		return slobodne;
	}
}
