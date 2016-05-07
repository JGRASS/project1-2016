package gui.model;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import domen.Termin;
import kontroler.Kontroler;

public class TableModelPrikazSaleGUI extends AbstractTableModel {

	private String sala;
	private LinkedList<Termin> termini = new LinkedList<Termin>();

	public TableModelPrikazSaleGUI(String sala) {
		if (sala != null) {
			this.sala = sala;
			termini = Kontroler.vratiSveTermineZaDatuSalu(sala);
		}else{
			System.out.println("greska");
		}
		
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public LinkedList<Termin> getTermini() {
		return termini;
	}

	public void setTermini(LinkedList<Termin> termini) {
		this.termini = termini;
	}

	public String[] getKolone() {
		return kolone;
	}

	private static final long serialVersionUID = 1L;
	private final String[] kolone = new String[] { "Termin", "Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak",
			"Subota", "Nedelja" };

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		return 6;
	}

	@Override
	public Object getValueAt(int row, int column) {
		if (column == 0) {
			switch (row) {
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
			if (isCellEditable(row, column)) {
				return "YES";
			} else {
				return Kontroler.vratiNazivHostaNaOsnovuTerminaISale(row, column, sala);
			}
		}
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		super.setValueAt(aValue, rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		for (Termin t : termini) {
			if (t.getVreme() == rowIndex && t.getDatum() == columnIndex) {
				return false;
			}
		}
		return true;
	}

	public void osveziTabelu() {
		fireTableDataChanged();
	}

}
