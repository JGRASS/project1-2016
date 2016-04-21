package domen;

public class Event {
	private String host;
	private Sala sala;
	private Termin termin;

	public Event(String host, Sala sala, Termin termin) {
		this.host = host;
		this.sala = sala;
		this.termin = termin;
	}

	public Event() {
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	};

}
