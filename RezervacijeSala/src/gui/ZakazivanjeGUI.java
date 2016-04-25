package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Sala;
import kontroler.Kontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ZakazivanjeGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane;
	private JList jlstSale;
	private JPanel panel;
	private JLabel lblDan;
	private JLabel lblTermin;
	private JLabel lblHost;
	private JTextField txtHost;
	private JButton btnRezervisi;
	private JButton btnOtkazi;
	private JLabel lblIzabranDan;
	private JLabel lblIzabranTermin;

	private int datum;
	private int vreme;
	private String tipSale;
	private LinkedList<Sala> sale;
	
	//Singleton
	private static ZakazivanjeGUI objekat;
	
	public static ZakazivanjeGUI vratiObjekat(){
		if (objekat == null) {
			objekat = new ZakazivanjeGUI();
		}
		return objekat;
	}
	
	public static ZakazivanjeGUI vratiObjekat(int datum, int vreme, String tipSale){
		if (objekat == null) {
			objekat = new ZakazivanjeGUI(datum, vreme, tipSale);
		} else if (!poklapaSe(datum, vreme, tipSale)) {
			//Unisti prozor kako znas i umes :D
			objekat.dispose();
			objekat = null;
			objekat = new ZakazivanjeGUI(datum, vreme, tipSale);
		}
		return objekat;
	}
	
	
	/**
	 * Create the dialog.
	 */
	private ZakazivanjeGUI() {
		setTitle("Rezervacija");
		setBounds(100, 100, 431, 300);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(getScrollPane(), "cell 0 0,grow");
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	}
	
	private ZakazivanjeGUI(int datum, int vreme, String tipSale) {
		setTitle("Rezervacija");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow]", "[grow]"));
		getContentPane().add(getScrollPane(), "cell 0 0,grow");
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.datum = datum;
		this.vreme = vreme;
		this.tipSale = tipSale;
		//JEBENI TERMINI KRECU OD 1 ZA 8 UJUTRU A OVDE VREME KRECE OD NULE JEBACU VAS U USTA
		// ne psuj olosu
		sale = Kontroler.vratiSlobodneSale(datum, vreme+1, tipSale);
		napuniListu();
		setTitle("Rezervacija");
		lblIzabranTermin.setText(pretvoriTerminUString(vreme));
		lblIzabranDan.setText(pretvoriDanUString(datum));
		setResizable(false);
		
	}
	
	private void napuniListu(){
		DefaultListModel<String> dlm = new DefaultListModel<>();
		for(Sala sala : sale){
			dlm.addElement(sala.getNaziv_sale());
		}
		jlstSale.setModel(dlm);
		
	}
	
	private String pretvoriTerminUString(int termin) {
		switch (termin) {
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
			return "" + termin;
		}
	}

	private String pretvoriDanUString(int dan) {
		switch (dan) {
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
			return "" + dan;
		}
	}
	
	

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setRowHeaderView(getJlstSale());
			scrollPane.setViewportView(getPanel());
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
			panel.setLayout(null);
			panel.add(getLblDan());
			panel.add(getLblTermin());
			panel.add(getLblHost());
			panel.add(getTxtHost());
			panel.add(getBtnRezervisi());
			panel.add(getBtnOtkazi());
			panel.add(getLblIzabranDan());
			panel.add(getLblIzabranTermin());
		}
		return panel;
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
	private JLabel getLblHost() {
		if (lblHost == null) {
			lblHost = new JLabel("Host: ");
			lblHost.setBounds(10, 61, 46, 14);
		}
		return lblHost;
	}
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(10, 78, 86, 20);
			txtHost.setColumns(10);
		}
		return txtHost;
	}
	private JButton getBtnRezervisi() {
		if (btnRezervisi == null) {
			btnRezervisi = new JButton("Rezervisi");
			btnRezervisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//```````````````````OVI PRINTOVI SU SAMO TESTIRANJA RADI``````````````
					String sala = jlstSale.getSelectedValue().toString().trim();
					System.out.println(sala);
					String host = txtHost.getText();
					System.out.println(host);
					int termin = vreme + 1;
					Kontroler.dodajEvent(host, sala, datum, termin, tipSale);;
				}
			});
			btnRezervisi.setBounds(7, 129, 89, 23);
		}
		return btnRezervisi;
	}
	private JButton getBtnOtkazi() {
		if (btnOtkazi == null) {
			btnOtkazi = new JButton("Otkazi");
			btnOtkazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					
				}
			});
			btnOtkazi.setBounds(7, 163, 89, 23);
		}
		return btnOtkazi;
	}
	private JLabel getLblIzabranDan() {
		if (lblIzabranDan == null) {
			lblIzabranDan = new JLabel("");
			lblIzabranDan.setBounds(50, 11, 83, 14);
		}
		return lblIzabranDan;
	}
	private JLabel getLblIzabranTermin() {
		if (lblIzabranTermin == null) {
			lblIzabranTermin = new JLabel("");
			lblIzabranTermin.setBounds(50, 36, 46, 14);
		}
		return lblIzabranTermin;
	}
	
	private static boolean poklapaSe(int datum, int vreme, String tipSale){
		if (objekat.datum == datum && objekat.vreme == vreme && objekat.tipSale.equals(tipSale)) {
			return true;
		}
		return false;
	}
}
