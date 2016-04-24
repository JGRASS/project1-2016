package gui.osluskivac;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class Osluskivac implements MouseListener{
	
	private JTable table;
	private int red;
	private int kolona;
	public Osluskivac(JTable table) {
		this.table = table;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table != null) {
			red = table.getSelectedRow();
			kolona = table.getSelectedColumn();
		}
		System.out.println("Red ="+ red);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public int getRed() {
		return red;
	}

	public int getKolona() {
		return kolona;
	}

	
	
}
