package kontroler;

import java.sql.SQLException;
import java.util.LinkedList;

import domen.Event;
import model.Model;

public class Kontroler {
	private LinkedList<Event> dogadjaji = null;
	
	public void prikupiSale(){
	Model testModel = new Model();
	try {
		dogadjaji = testModel.prikupiSale("RacunskiCentar");
		for (Event event : dogadjaji) {
			System.out.println(event.getSala()+"\t"+event.getDatum()+"\t"+event.getTermin()+"\t"+event.getHost());
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
