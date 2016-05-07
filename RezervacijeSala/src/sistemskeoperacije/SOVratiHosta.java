package sistemskeoperacije;

import java.sql.SQLException;

import model.Model;

public class SOVratiHosta {
	
	public static String izvrsi(Model model, int row, int column, String sala)
			throws SQLException {
		return model.vratiNazivHosta(row, column, sala);
	}
}
