package domen;

public class Event {
	private String host;
	private String sala;
	private int datum;
	private int termin;
	private int sala_id;
	private int termin_id;

	public Event() {
	};

	public Event(String host, String sala, int datum, int termin, int sala_id, int termin_id) {
		this.host = host;
		this.sala = sala;
		this.datum = datum;
		this.termin = termin;
		this.sala_id = sala_id;
		this.termin_id = termin_id;
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

	public int getSala_id() {
		return sala_id;
	}

	public void setSala_id(int sala_id) {
		this.sala_id = sala_id;
	}

	public int getTermin_id() {
		return termin_id;
	}

	public void setTermin_id(int termin_id) {
		this.termin_id = termin_id;
	}

}
