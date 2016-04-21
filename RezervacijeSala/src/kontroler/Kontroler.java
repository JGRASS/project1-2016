package kontroler;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Event;
import gui.RezSalaGUI;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	Model testModel = new Model();
	public void prikupiSale(String s) {
		try {
			dogadjaji = testModel.prikupiEventove(s);
			for (Event event : dogadjaji) {
				System.out.println("Sala: "+event.getSala().getNaziv_sale() + "\tDatum: " + event.getTermin().getDatum() + "\tTermin: " + event.getTermin().getVreme() + "\tPredmet: " + event.getHost()+"\tid sale:" + event.getSala().getSala_id() + "\tid termina: "+ event.getTermin().getTermin_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Kontroler k = new Kontroler();
		RezSalaGUI gui = new RezSalaGUI();
		gui.setVisible(true);
			k.prikupiSale(gui.vratiOdabranTipSale());
	}

}
