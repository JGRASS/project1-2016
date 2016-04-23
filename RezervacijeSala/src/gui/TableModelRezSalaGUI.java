package gui;

import javax.swing.table.AbstractTableModel;

import kontroler.Kontroler;

public class TableModelRezSalaGUI extends AbstractTableModel {
	private String tipSale;

	public TableModelRezSalaGUI() {
		this.tipSale = null;
	}

	public TableModelRezSalaGUI(String tipSale) {
		this.tipSale = tipSale;
	}

	public String getTipSale() {
		return tipSale;
	}

	public void setTipSale(String tipSale) {
		this.tipSale = tipSale;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String[] kolone = new String[] { "Termin", "Ponedeljak", "Utorak", "Sreda", "Cetvrtak", "Petak" };

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
				System.out.println(Kontroler.daLiPostojiNekaSlobodnaSalaUTerminu(
					Kontroler.vratiSlobodneSaleZaDatiTerminITipSale(column, row, tipSale)));
				return "YES";
			} else {
				return "NO";
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
			if (Kontroler.daLiPostojiNekaSlobodnaSalaUTerminu(
					Kontroler.vratiSlobodneSaleZaDatiTerminITipSale(columnIndex, rowIndex, tipSale))) {
				return true;
			} else
				return false;
	}

}
