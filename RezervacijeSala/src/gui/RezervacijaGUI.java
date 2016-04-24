package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
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
import javax.swing.JLabel;
import javax.swing.JTextField;

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

	private JLabel lblDan;
	private JLabel lblTermin;
	private JLabel lblIzabranDan;
	private JLabel lblIzabranTermin;
	private JLabel lblHost;
	private JTextField txtHost;

	/**
	 * Create the frame.
	 */
	public RezervacijaGUI(int datum, int vreme, String tipSale) {
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
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
		setTitle("Rezervacija");
		lblIzabranTermin.setText(pretvoriTerminUString(vreme));
		lblIzabranDan.setText(pretvoriDanUString(datum));
	}
	public RezervacijaGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 384, 301);
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
			panel.setPreferredSize(new Dimension(160, 50));
			panel.setLayout(null);
			panel.add(getBtnRezervacija());
			panel.add(getBtnOtkazi());
			panel.add(getLblDan());
			panel.add(getLblTermin());
			panel.add(getLblIzabranDan());
			panel.add(getLblIzabranTermin());
			panel.add(getLblHost());
			panel.add(getTxtHost());
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
			btnRezervacija.setBounds(10, 103, 100, 23);
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
			btnOtkazi.setBounds(10, 137, 100, 23);
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
	private JLabel getLblDan() {
		if (lblDan == null) {
			lblDan = new JLabel("Dan: ");
			lblDan.setBounds(10, 11, 46, 14);
		}
		return lblDan;
	}
	private JLabel getLblTermin() {
		if (lblTermin == null) {
			lblTermin = new JLabel("Termin: ");
			lblTermin.setBounds(10, 36, 46, 14);
		}
		return lblTermin;
	}
	private String pretvoriTerminUString(int termin){
		switch(termin){
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
			return "NN";
		}
		}
	private String pretvoriDanUString(int dan){
		switch(dan){
		case 1:
			return "Ponedeljak";
		case 2:
			return "Utorak";
		case 3:
			return "Sreda";
		case 4:
			return "Cetvrtak";
		case 5:
			return "Petak";
		case 6: 
			return "Subota";
		case 7:
			return "Nedelja";
		default:
			return "NN";
		}
		}
	private JLabel getLblIzabranDan() {
		if (lblIzabranDan == null) {
			lblIzabranDan = new JLabel("");
			lblIzabranDan.setBounds(43, 11, 110, 14);
		}
		return lblIzabranDan;
	}
	private JLabel getLblIzabranTermin() {
		if (lblIzabranTermin == null) {
			lblIzabranTermin = new JLabel("");
			lblIzabranTermin.setBounds(53, 36, 100, 14);
		}
		return lblIzabranTermin;
	}
	private JLabel getLblHost() {
		if (lblHost == null) {
			lblHost = new JLabel("Host:");
			lblHost.setBounds(10, 61, 46, 14);
		}
		return lblHost;
	}
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(48, 58, 86, 20);
			txtHost.setColumns(10);
		}
		return txtHost;
	}
	}
