package kontroler;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import domen.Event;
import domen.Sala;
import domen.Termin;
import gui.MainWindowGUI;
import gui.RezSalaGUI;
import gui.model.TableModelRezSalaGUI;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	private static Model testModel = new Model();
	private static MainWindowGUI mw;


	/**
	 * Metoda na osnovu parametara vraca slobodne sale odredjenog tipa u datom
	 * terminu
	 * 
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 * @return lista objekta slobodnih sala
	 */
	public static LinkedList<Sala> vratiSlobodneSale(int datum, int vreme, String tipSale) {
		LinkedList<Sala> sale;
		LinkedList<Event> eventi;
		LinkedList<Sala> slobodne = new LinkedList<>();
		try {
			sale = testModel.sveSaleDatogTipa(tipSale);
			eventi = testModel.prikupiEventoveZaDatiTerminITipSale(datum, vreme, tipSale);
			slobodne = testModel.vratiSlobodneSaleUTerminu(sale, eventi);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return slobodne;
	}

	/**
	 * Metoda na osnovu parametara dodaje event u bazu.
	 * 
	 * @param host
	 *            - Razlog zakazivanja
	 * @param sala
	 *            - Sala koja se zakazuje
	 * @param datum
	 *            - Dan u nedelji zakazivanja
	 * @param vreme
	 *            - Vreme u toku dana za zakazivanja
	 * @param tipSale
	 *            - Tip sale koji se zakazuje
	 */
	public static void dodajEvent(String host, String sala, int datum, int vreme, String tipSale) {
		try {
			int sala_id = 6;
			int termin_id = 5;
			LinkedList<Sala> sveSale = testModel.sveSaleDatogTipa(tipSale);
			for (Sala salica : sveSale) {
				if (salica.getNaziv_sale().equals(sala)) {
					sala_id = salica.getSala_id();
					break;
				}
			}
			LinkedList<Termin> sviTermini = testModel.sviTermini();
			for (Termin t : sviTermini) {
				if (t.getDatum() == datum && t.getVreme() == vreme) {
					termin_id = t.getTermin_id();
					break;
				}
			}
			testModel.unesiEvent(host, termin_id, sala_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Metoda vraca true ako postoji makar jedna slobodna sala.
	 * 
	 * @param slobodneSale
	 *            - lista slobodnih sala
	 * @return
	 * 		<ul>
	 *         <li>true - makar jedna slobodna</li>
	 *         <li>false - nijedna slobodna sala</li>
	 *         </ul>
	 */
	public static boolean daLiPostojiNekaSlobodnaSalaUTerminu(LinkedList<Sala> slobodneSale) {
		return !slobodneSale.isEmpty();
	}

	public static LinkedList<Termin> vratiSveTermineZaDatuSalu(String naziv_sale) {
		try {
			return testModel.pronadjiSveTermineZaDatuSalu(naziv_sale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String vratiNazivHostaNaOsnovuTerminaISale(int row, int column, String sala) {
		try {
			return testModel.vratiNazivHosta(row, column, sala);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Metoda na osnovu parametra proverava da li sala postoji
	 * @param sala - naziv sale
	 * @return 
	 * 			<ul>
	 *         <li>true - Postoji sala</li>
	 *         <li>false - Nepostoji sala</li>
	 *         </ul>
	 */
	public static boolean daLiPostojiSala(String sala){
		LinkedList<Sala> sale;
		try {
			sale = testModel.vratiSveSale();
			for(Sala s : sale){
				if (s.getNaziv_sale().equals(sala.toUpperCase())) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static String vratiTipSale(String sala){
		String tipSale = null;
		String naziv = sala.toUpperCase().trim();
		LinkedList<Sala> sale;
		try {
			sale = testModel.vratiSveSale();
			for(Sala s : sale){
				if (s.getNaziv_sale().equals(naziv)) {
					tipSale = s.getTip_sale();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tipSale;
	}
	


}
