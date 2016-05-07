package sistemskeoperacije;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Sala;
import model.Model;

public class SOVratiTipSale {

	public static String izvrsi(Model model, String sala) throws SQLException {
		String tipSale = null;
		String naziv = sala.toUpperCase().trim();
		LinkedList<Sala> sale;
		sale = model.vratiSveSale();
		for (Sala s : sale) {
			if (s.getNaziv_sale().equals(naziv)) {
				tipSale = s.getTip_sale();
			}
		}
		return tipSale;
	}
}
