package kontroler;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import domen.Event;
import domen.Sala;
import domen.Termin;
import gui.RezSalaGUI;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	Model testModel = new Model();

	public void prikupiEventoveZaDatiTerminITipSale(int datum, int vreme, String tipSale) {
		try {
			dogadjaji = testModel.prikupiEventoveZaDatiTerminITipSale(datum, vreme, tipSale);
			for (Event event : dogadjaji) {
				System.out.println("Sala: " + event.getSala().getNaziv_sale() + "\tTip sale: "
						+ event.getSala().getTip_sale() + "\tDatum: " + event.getTermin().getDatum() + "\tTermin: "
						+ event.getTermin().getVreme() + "\tPredmet: " + event.getHost() + "\tid sale:"
						+ event.getSala().getSala_id() + "\tid termina: " + event.getTermin().getTermin_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void unesiEvent(Event e) {
		try {
			testModel.unesiEvent(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

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
	/*
	 * TEST
	 */
	public static void main(String[] args) {
		Kontroler k = new Kontroler();
		/*
		 * RezSalaGUI gui = new RezSalaGUI(); gui.setVisible(true);
		 * k.prikupiSale(gui.vratiOdabranTipSale()); k.prikupiEventove("RC");
		 */
		// k.sveSale("rc");

		/*
		 * k.prikupiEventoveZaDatiTerminITipSale(3, 10, "ucionica");
		 */

		/*
		 * TEST ZA UNOS NOVOG EVENTA
		 */
		/*
		 * Event e = new Event("Engleski Jezik", new Sala(1, "B009",
		 * "amfiteatar"), new Termin(6, 18, 1)); k.unesiEvent(e);
		 */

		try {
			LinkedList<Event> eventoviZaTerminITipSale = k.testModel.prikupiEventoveZaDatiTerminITipSale(3, 10,
					"ucionica");
			
			for (Event event : eventoviZaTerminITipSale) {
				System.out.println("Sala: " + event.getSala().getNaziv_sale() + "\tTip sale: "
						+ event.getSala().getTip_sale() + "\tDatum: " + event.getTermin().getDatum() + "\tTermin: "
						+ event.getTermin().getVreme() + "\tPredmet: " + event.getHost() + "\tid sale:"
						+ event.getSala().getSala_id() + "\tid termina: " + event.getTermin().getTermin_id());
			}
			
			LinkedList<Sala> sveSale = k.testModel.sveSaleDatogTipa("ucionica");
			for (Sala sala : sveSale) {
				System.out.println(sala.getSala_id() + "\t\t" + sala.getNaziv_sale() + "\t\t" + sala.getTip_sale());
			}
			LinkedList<Sala> slobodneSale = k.testModel.vratiSlobodneSaleUTerminu(sveSale, eventoviZaTerminITipSale);
			System.out.println(slobodneSale.size());
			for (Sala sala : slobodneSale) {
				System.out.println(sala.getSala_id() + "\t\t" + sala.getNaziv_sale() + "\t\t" + sala.getTip_sale());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
