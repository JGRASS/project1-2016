package domen;

import java.util.List;

public class Sala {

	private String imeSale;
	private EnumTipSale tip;
	private List<Dan> listaDana;
	
	public String getImeSale() {
		return imeSale;
	}
	public void setImeSale(String imeSale) {
		this.imeSale = imeSale;
	}
	public EnumTipSale getTip() {
		return tip;
	}
	public void setTip(EnumTipSale tip) {
		this.tip = tip;
	}
	public List<Dan> getListaDana() {
		return listaDana;
	}
	public void setListaDana(List<Dan> listaDana) {
		this.listaDana = listaDana;
	}
	
	
}
