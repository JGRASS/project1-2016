package gui;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import domen.Termin;
import kontroler.Kontroler;

public class TableModelSala extends AbstractTableModel{

	private String sala;
	private final String[] kolone = new String[] { "Termin", "Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak", "Subota", "Nedelja" };
	
	public TableModelSala() {
		this.sala = null;
	}
	public TableModelSala(String sala){
		this.sala = sala;
	}
	@Override
	public int getRowCount() {
		return 6;
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			switch (rowIndex) {
			case 0:
				return "8:15-10:00";
			case 1:
				return "10:15 - 12:00";
			case 2:
				return "12:15 - 14:00";
			case 3:
				return "14:15 - 16:00";
			case 4:
				return "16:15 - 18:00";
			case 5:
				return "18:15 - 20:00";
			default:
				return null;
			}
		} else {
			if (isCellEditable(rowIndex, columnIndex)) {
				return "YES";
			} else {
				return "NO";
			}
		}
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		LinkedList<Termin> zauzetiTermini = Kontroler.vratiZauzeteTermineZaSalu(sala);
		for(Termin t : zauzetiTermini){
			int vreme = t.getVreme();
			int datum = t.getDatum();
			if (vreme == rowIndex && datum == columnIndex) {
				return false;
			}
		}
		return true;
	}
	
	
	

}
