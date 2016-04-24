package kontroler;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Event;
import domen.Sala;
import domen.Termin;
import gui.RezSalaGUI;
import gui.TableModelRezSalaGUI;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	private static Model testModel = new Model();
	private static RezSalaGUI gui;
	//Model testModel = new Model();
	
	
	

	/*
	 * public void prikupiEventoveZaDatiTerminITipSale(int datum, int vreme,
	 * String tipSale) { try { dogadjaji =
	 * testModel.prikupiEventoveZaDatiTerminITipSale(datum, vreme, tipSale); for
	 * (Event event : dogadjaji) { System.out.println("Sala: " +
	 * event.getSala().getNaziv_sale() + "\tTip sale: " +
	 * event.getSala().getTip_sale() + "\tDatum: " +
	 * event.getTermin().getDatum() + "\tTermin: " +
	 * event.getTermin().getVreme() + "\tPredmet: " + event.getHost() +
	 * "\tid sale:" + event.getSala().getSala_id() + "\tid termina: " +
	 * event.getTermin().getTermin_id()); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	/*
	 * public void unesiEvent(Event e) { try { testModel.unesiEvent(e); } catch
	 * (SQLException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); } }
	 */
	/*
	 * public void sviEventoviZaDatoVremeIDatum(int vreme, int datum) { try {
	 * dogadjaji = testModel.prikupiEventoveZaDatiTermin(datum, vreme); for
	 * (Event event : dogadjaji) { System.out.println("Sala: " +
	 * event.getSala().getNaziv_sale() + "\tTip sale: " +
	 * event.getSala().getTip_sale() + "\tDatum: " +
	 * event.getTermin().getDatum() + "\tTermin: " +
	 * event.getTermin().getVreme() + "\tPredmet: " + event.getHost() +
	 * "\tid sale:" + event.getSala().getSala_id() + "\tid termina: " +
	 * event.getTermin().getTermin_id()); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
	/*
	 * public void sveSale(String s) { try { LinkedList<Sala> sale =
	 * testModel.sveSaleDatogTipa(s); System.out.println(
	 * "ID SALE: \tNAZIV SALE \tTIP SALE"); for (Sala sala : sale) {
	 * System.out.println(sala.getSala_id() + "\t\t" + sala.getNaziv_sale() +
	 * "\t\t" + sala.getTip_sale()); } } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */

	/**
	 * Metoda na osnovu parametara vraca slobodne sale odredjenog tipa u datom terminu
	 * @param datum
	 * @param vreme
	 * @param tipSale 
	 * @return lista objekta slobodnih sala
	 */
	public static LinkedList<Sala> vratiSlobodneSale(int datum, int vreme, String tipSale){
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
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Obrati paznju na indexe
	public static void dodajEvent(String host, String sala, int datum, int vreme, String tipSale){
		Termin termin = new Termin(90, vreme, datum);
		Sala salla = new Sala(90, sala, tipSale);
		Event event = new Event(host, salla, termin);
		try {
			testModel.unesiEvent(event);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean daLiPostojiNekaSlobodnaSalaUTerminu(LinkedList<Sala> slobodneSale){
		return !slobodneSale.isEmpty();
	}
	/*
	 * TEST
	 */
	public static void main(String[] args) {
		gui = new RezSalaGUI();
		gui.setVisible(true);

		
		/*
		LinkedList<Sala> slobodne = vratiSlobodneSaleZaDatiTerminITipSale(4, 10, "amfiteatar");
		for (Sala sala : slobodne) {
			System.out.println(sala.getNaziv_sale() + sala.getSala_id() + sala.getTip_sale());
		}
		*/
		
		
		/*try {
			System.out.println(testModel.vratiMaxIdSale());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
		
		
		
		
		
		
		
		// TEST UNOS NOVOG EVENTA
		/*
		 * Event e = new Event("Modelovanje", new Sala(4, "104", "ucionica"),
		 * new Termin(3,3,10)); k.unesiEvent(e); /*
		 */
		/*
		 * try { LinkedList<Event> eventoviZaTerminITipSale =
		 * k.testModel.prikupiEventoveZaDatiTerminITipSale(1, 8, "ucionica");
		 * 
		 * for (Event event : eventoviZaTerminITipSale) { System.out.println(
		 * "Sala: " + event.getSala().getNaziv_sale() + "\tTip sale: " +
		 * event.getSala().getTip_sale() + "\tDatum: " +
		 * event.getTermin().getDatum() + "\tTermin: " +
		 * event.getTermin().getVreme() + "\tPredmet: " + event.getHost() +
		 * "\tid sale:" + event.getSala().getSala_id() + "\tid termina: " +
		 * event.getTermin().getTermin_id()); }
		 * 
		 * LinkedList<Sala> sveSale = k.testModel.sveSaleDatogTipa("ucionica");
		 * System.out.println("\n-----------------SVE SALE-----------------\n");
		 * for (Sala sala : sveSale) { System.out.println(sala.getSala_id() +
		 * "\t\t" + sala.getNaziv_sale() + "\t\t" + sala.getTip_sale()); }
		 * LinkedList<Sala> slobodneSale =
		 * k.testModel.vratiSlobodneSaleUTerminu(sveSale,
		 * eventoviZaTerminITipSale); System.out.println(
		 * "\nBroj slobodnih sala:\t" + slobodneSale.size());
		 * System.out.println(
		 * "\n\n-------------------SLOBODNE SALE-----------------\n\n"); for
		 * (Sala sala : slobodneSale) { System.out.println(sala.getSala_id() +
		 * "\t\t" + sala.getNaziv_sale() + "\t\t" + sala.getTip_sale()); } }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 */
	}
}
