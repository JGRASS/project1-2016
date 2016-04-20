package domen;

public class Termin {

	private int brojTermina;
	private boolean slobodan;
	
	public Termin(){};
	
	public Termin(int brojTermina, boolean slobodan) {
		this.brojTermina = brojTermina;
		this.slobodan = slobodan;
	}
	
	public int getBrojTermina() {
		return brojTermina;
	}
	public void setBrojTermina(int brojTermina) {
		this.brojTermina = brojTermina;
	}
	public boolean isSlobodan() {
		return slobodan;
	}
	public void setSlobodan(boolean slobodan) {
		this.slobodan = slobodan;
	}
	
	
	
}
