package sistemskeoperacije;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Sala;
import model.Model;

public class SODaLiPostojiSala {

	public static boolean izvrsi(Model model, String sala) throws SQLException {
		LinkedList<Sala> sale;
		sale = model.vratiSveSale();
		for (Sala s : sale) {
			if (s.getNaziv_sale().equals(sala.toUpperCase())) {
				return true;
			}
		}
		return false;
	}
}
