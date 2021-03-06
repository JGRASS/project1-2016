package gui.model;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import domen.Termin;
import gui.kontroler.GUIKontroler;
import kontroler.Kontroler;

public class TableModelPrikazSaleGUI extends AbstractTableModel {

	private String sala;
	private LinkedList<Termin> termini = new LinkedList<Termin>();

	public TableModelPrikazSaleGUI(String sala) {
		if (sala != null) {
			this.sala = sala;
				try {
					termini = GUIKontroler.vratiTermineZaSalu(sala);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Greska pri citanju iz baze.", "Greska", JOptionPane.ERROR_MESSAGE);
				}
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
				try {
					return GUIKontroler.vratiHosta(row, column, sala);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Greska pri citanju iz baze.", "Greska", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		return "Greska";//Nepotrebno ako se ukloni try catch
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
