package domen;

public class Termin {

	private int termin_id;
	private int vreme;
	private int datum;

	public Termin() {
	};

	public Termin(int termin_id, int vreme, int datum) {
		super();
		this.termin_id = termin_id;
		this.vreme = vreme;
		this.datum = datum;
	}

	public int getTermin_id() {
		return termin_id;
	}

	public void setTermin_id(int termin_id) {
		this.termin_id = termin_id;
	}

	public int getVreme() {
		return vreme;
	}

	public void setVreme(int vreme) {
		this.vreme = vreme;
	}

	public int getDatum() {
		return datum;
	}

	public void setDatum(int datum) {
		this.datum = datum;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			if(obj instanceof Termin){
				Termin t = (Termin) obj;
				if(t.getTermin_id() == this.getTermin_id()){
					return true;
				}else return false;
			}
		}
		return false;
	}
	
}
