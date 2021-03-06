package domen;

public class Sala {
	private int sala_id;
	private String naziv_sale;
	private String tip_sale;

	public Sala(){};
	
	public Sala(int sala_id, String naziv_sale, String tip_sale) {
		this.sala_id = sala_id;
		this.naziv_sale = naziv_sale;
		this.tip_sale = tip_sale;
	}
	
	public String getTip_sale() {
		return tip_sale;
	}

	public void setTip_sale(String tip_sale) {
		this.tip_sale = tip_sale;
	}
	
	public int getSala_id() {
		return sala_id;
	}
	public void setSala_id(int sala_id) {
		this.sala_id = sala_id;
	}
	public String getNaziv_sale() {
		return naziv_sale;
	}
	public void setNaziv_sale(String naziv_sale) {
		this.naziv_sale = naziv_sale;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj != null){
			if(obj instanceof Sala){
				Sala s = (Sala) obj;
				if(s.getSala_id() == this.getSala_id()){
					return true;
				}else return false;
			}
		}
		return false;
	}
	
}
