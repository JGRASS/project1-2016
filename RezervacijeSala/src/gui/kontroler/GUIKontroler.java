package gui.kontroler;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.LinkedList;

import domen.Sala;
import domen.Termin;
import gui.MainWindowGUI;
import gui.PrikazSaleGUI;
import gui.RezSalaGUI;
import gui.ZakazivanjeGUI;
import kontroler.Kontroler;

public class GUIKontroler {

	private static MainWindowGUI mainFrame;
	private static PrikazSaleGUI prikazSale;
	private static RezSalaGUI rezSala;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame = new MainWindowGUI();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * Metode za prikaz formi
	 */
	public static void prikaziZakazivanjeGUI(int datum, int vreme, String tipSale) throws SQLException{
		LinkedList<Sala> sale = Kontroler.vratiSlobodneSale(datum, vreme, tipSale);
		ZakazivanjeGUI z = ZakazivanjeGUI.vratiObjekat(datum, vreme, tipSale, sale);
		//z.setModal(true);
		z.setVisible(true);
	}
	
	public static void prikaziPrikazSaleGUI() {
		if (prikazSale == null) {
			prikazSale = new PrikazSaleGUI();
		}		
		prikazSale.setVisible(true);
	}
	
	public static void prikaziRezSalaGUI(){
		if (rezSala == null) {
			rezSala = new RezSalaGUI();
		}		
		rezSala.setVisible(true);
	}
	
	/*
	 * ######################
	 * Metode za pristup bazi
	 * ######################
	 */
	
	/**
	 * Metoda za dodavanje eventa
	 * @param host
	 * @param sala
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 * @throws java.sql.SQLException
	 */
	public static void rezervisi(String host, String sala, int datum, int vreme, String tipSale) throws SQLException{
		Kontroler.dodajEvent(host, sala, datum, vreme, tipSale);
	}
	
	public static void rezervisi(String host, String sala, int datum, int vreme) throws SQLException{
		String tipSale = Kontroler.vratiTipSale(sala);
		Kontroler.dodajEvent(host, sala, datum, vreme, tipSale);
	}
	
	/*
	 * Metode za upravljanje salama
	 */
	public static boolean daLiPostojiSala(String sala) throws SQLException{
		return Kontroler.daLiPostojiSala(sala);
	}
	
	public static String vratiTipSale(String sala) throws SQLException{
		return Kontroler.vratiTipSale(sala);
	}
	
	public static boolean daLiImaSlobodnaSalaZaTermin(int datum, int vreme, String tipSale) throws SQLException{
		if (Kontroler.daLiPostojiNekaSlobodnaSalaUTerminu(
				Kontroler.vratiSlobodneSale(datum, vreme, tipSale))) {
			return true;
		}
		return false;
	}
	
	/*
	 * Metoda za upravljanje terminima
	 */
	public static LinkedList<Termin> vratiTermineZaSalu(String sala) throws SQLException{
		return Kontroler.vratiSveTermineZaDatuSalu(sala);
	}
	

	/*
	 * metoda vraca hosta na osnovu termina i sale
	 */
	public static String vratiHosta(int row, int column, String sala) throws SQLException{
		return Kontroler.vratiNazivHostaNaOsnovuTerminaISale(row, column, sala);
	}
	
	/*
	 * Pomocne metode
	 */
	/**
	 * Sredjuje string za tip sale
	 * @param s
	 * @return
	 */
	public static String pretvoriTipSaleUString(String s) {
		switch (s) {
		case "rc":
			return "RC";
		case "amfiteatar":
			return "Amfiteatar";
		case "ucionica":
			return "Ucionica";
		default:
			return s;
		}
	}
	
}
