package kontroler;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Event;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;

	public void prikupiSale() {
		Model testModel = new Model();
		try {
			dogadjaji = testModel.prikupiSale("RC");
			for (Event event : dogadjaji) {
				System.out.println("Sala: "+event.getSala() + "\tDatum: " + event.getDatum() + "\tTermin: " + event.getTermin() + "\tPredmet: " + event.getHost()+"\tid sale:" + event.getSala_id() + "\tid termina: "+ event.getTermin_id());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Kontroler k = new Kontroler();
		k.prikupiSale();
	}

}
