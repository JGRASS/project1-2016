package sistemskeoperacije;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Termin;
import model.Model;

public class SOVratiTermineZaSalu {

	public static LinkedList<Termin> izvrsi(Model model, String naziv_sale) throws SQLException {
		return model.pronadjiSveTermineZaDatuSalu(naziv_sale);
	}
}
