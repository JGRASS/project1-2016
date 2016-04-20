package domen;

public class Event {
	private String host;
	private String sala;
	private int datum;
	private int termin;

	public Event() {
	};

	public Event(String host, String sala, int datum, int termin) {
		this.host = host;
		this.sala = sala;
		this.datum = datum;
		this.termin = termin;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public int getDatum() {
		return datum;
	}

	public void setDatum(int datum) {
		this.datum = datum;
	}

	public int getTermin() {
		return termin;
	}

	public void setTermin(int termin) {
		this.termin = termin;
	}

}
