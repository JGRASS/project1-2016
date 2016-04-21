package kontroler;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import domen.Event;
import domen.Sala;
import gui.RezSalaGUI;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	Model testModel = new Model();

	public void prikupiEventove(String s) {
		try {
			dogadjaji = testModel.prikupiEventoveZaDatiTipSale(s);
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

	public void sviEventoviZaDatoVremeIDatum(int vreme, int datum) {
		try {
			dogadjaji = testModel.prikupiEventoveZaDatiTermin(datum, vreme);
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

	public void sveSale(String s) {
		try {
			LinkedList<Sala> sale = testModel.sveSaleDatogTipa(s);
			System.out.println("ID SALE: \tNAZIV SALE \tTIP SALE");
			for (Sala sala : sale) {
				System.out.println(sala.getSala_id() + "\t\t" + sala.getNaziv_sale() + "\t\t" + sala.getTip_sale());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		k.sviEventoviZaDatoVremeIDatum(10, 3);
	}

}
