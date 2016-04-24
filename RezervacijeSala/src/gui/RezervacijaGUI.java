package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Sala;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.nio.file.ClosedFileSystemException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class RezervacijaGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList jlstSale;
	private JPanel panel;
	private JButton btnRezervacija;
	private JButton btnOtkazi;

	private LinkedList<Sala> sale;
	private int datum;
	private int vreme;
	private String tipSale;
	/**
	 * Create the frame.
	 */
	public RezervacijaGUI(int datum, int vreme, String tipSale) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
		this.datum = datum;
		this.vreme = vreme;
		this.tipSale = tipSale;
		sale = Kontroler.vratiSlobodneSale(datum, vreme, tipSale);
		napuniListu();
	}
	public RezervacijaGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 304, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getJlstSale());
		}
		return scrollPane;
	}
	private JList getJlstSale() {
		if (jlstSale == null) {
			jlstSale = new JList();
		}
		return jlstSale;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(120, 50));
			panel.setLayout(null);
			panel.add(getBtnRezervacija());
			panel.add(getBtnOtkazi());
		}
		return panel;
	}
	private JButton getBtnRezervacija() {
		if (btnRezervacija == null) {
			btnRezervacija = new JButton("Rezervisi");
			btnRezervacija.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sala =(String) jlstSale.getSelectedValue();
					//Kontroler.
				}
			});
			btnRezervacija.setBounds(10, 11, 100, 23);
		}
		return btnRezervacija;
	}
	
	
	private JButton getBtnOtkazi() {
		if (btnOtkazi == null) {
			btnOtkazi = new JButton("Otkazi");			
			btnOtkazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnOtkazi.setBounds(10, 45, 100, 23);
		}
		return btnOtkazi;
	}
	

	/**
	 * Metoda popunjava listu u formi rezervacije.
	 */
	private void napuniListu(){
		DefaultListModel<String> dlm = new DefaultListModel<>();
		for(Sala sala : sale){
			dlm.addElement(sala.getNaziv_sale());
		}
		jlstSale.setModel(dlm);
		/*ArrayList<String> i = new ArrayList<>();
		String[] str = new String[i.size()];
		//Assuming there is data in your list
		JList<String> list = new JList<>(i.toArray(str));*/
		
	}
}
