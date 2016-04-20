package optional;

import java.util.List;

public class Dan {

	private int dan;
	private List<Termin> listaTermina;
	
	public Dan(){};
	
	public Dan(int dan, List<Termin> listaTermina) {
		this.dan = dan;
		this.listaTermina = listaTermina;
	}
	public int getDan() {
		return dan;
	}
	public void setDan(int dan) {
		this.dan = dan;
	}
	public List<Termin> getListaTermina() {
		return listaTermina;
	}
	public void setListaTermina(List<Termin> listaTermina) {
		this.listaTermina = listaTermina;
	}
	
	
	
}
