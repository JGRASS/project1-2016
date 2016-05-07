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
import sistemskeoperacije.SODaLiPostojiSala;
import sistemskeoperacije.SODodajEvent;
import sistemskeoperacije.SOVratiHosta;
import sistemskeoperacije.SOVratiSlobodneSale;
import sistemskeoperacije.SOVratiTermineZaSalu;
import sistemskeoperacije.SOVratiTipSale;

public class Kontroler {
	private static Model testModel = new Model();


	/**
	 * Metoda na osnovu parametara vraca slobodne sale odredjenog tipa u datom
	 * terminu
	 * 
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 * @return lista objekta slobodnih sala
	 * @throws SQLException 
	 */
	public static LinkedList<Sala> vratiSlobodneSale(int datum, int vreme, String tipSale) throws SQLException {
		return SOVratiSlobodneSale.izvrsi(testModel, datum, vreme, tipSale);
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
	 * @throws SQLException 
	 */
	public static void dodajEvent(String host, String sala, int datum, int vreme, String tipSale) throws SQLException {
		SODodajEvent.izvrsi(testModel, host, sala, datum, vreme, tipSale);
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

	public static LinkedList<Termin> vratiSveTermineZaDatuSalu(String naziv_sale) throws SQLException {
		return SOVratiTermineZaSalu.izvrsi(testModel, naziv_sale);
	}

	public static String vratiNazivHostaNaOsnovuTerminaISale(int row, int column, String sala) throws SQLException {
		return SOVratiHosta.izvrsi(testModel, row, column, sala);
	}
	
	/**
	 * Metoda na osnovu parametra proverava da li sala postoji
	 * @param sala - naziv sale
	 * @return 
	 * 			<ul>
	 *         <li>true - Postoji sala</li>
	 *         <li>false - Nepostoji sala</li>
	 *         </ul>
	 * @throws SQLException 
	 */
	public static boolean daLiPostojiSala(String sala) throws SQLException{
		return SODaLiPostojiSala.izvrsi(testModel, sala);
	}
	
	public static String vratiTipSale(String sala) throws SQLException{
		return SOVratiTipSale.izvrsi(testModel, sala);
	}
	


}
