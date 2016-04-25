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
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class ZakazivanjeGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
	private JScrollPane scrollPane;
	private JLabel lblTipSale;
	private JLabel lblIzabranTipSale;
	
	public static ZakazivanjeGUI vratiObjekat(){
		if (objekat == null) {
			objekat = new ZakazivanjeGUI();
		}
		return objekat;
	}
	
	/**
	 * Singleton metoda koja vraca objekat klase u kojoj 
	 * se nalazi i obezbedjuje da postoji maksimalno jedan objekat te klase.
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 * @return
	 */
	/*public static ZakazivanjeGUI vratiObjekat(int datum, int vreme, String tipSale){
		if (objekat == null) {
			objekat = new ZakazivanjeGUI(datum, vreme, tipSale);
		} else if (!poklapaSe(datum, vreme, tipSale)) {
			//Unisti prozor kako znas i umes :D
			objekat.dispose();
			objekat = null;
			objekat = new ZakazivanjeGUI(datum, vreme, tipSale);
		} else if (poklapaSe(datum, vreme, tipSale)) {
			objekat.setVisible(false);
			objekat.revalidate();
			objekat.repaint();
			objekat.setVisible(true);
		}
		return objekat;
	}*/
	
	public static ZakazivanjeGUI vratiObjekat(int datum, int vreme, String tipSale){
		if (objekat == null) {
			objekat = new ZakazivanjeGUI(datum, vreme, tipSale);
		} else {
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
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Rezervacija");
		setBounds(100, 100, 233, 254);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.EAST);
		getContentPane().add(getScrollPane_1(), BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
	}
	
	/**
	 * Napravi dijalog sa odgovarajucim parametrima
	 * @param datum
	 * @param vreme
	 * @param tipSale
	 */
	private ZakazivanjeGUI(int datum, int vreme, String tipSale) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Rezervacija");
		setBounds(100, 100, 292, 269);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.EAST);
		getContentPane().add(getScrollPane_1(), BorderLayout.CENTER);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.datum = datum;
		this.vreme = vreme;
		this.tipSale = tipSale;
		//JEBENI TERMINI KRECU OD 1 ZA 8 UJUTRU A OVDE VREME KRECE OD NULE JEBACU VAS U USTA
		// ne psuj olosu
		sale = Kontroler.vratiSlobodneSale(datum, vreme, tipSale);//vreme+1
		napuniListu();
		setTitle("Rezervacija");
		lblIzabranTermin.setText(pretvoriTerminUString(vreme));
		lblIzabranDan.setText(pretvoriDanUString(datum));
		lblIzabranTipSale.setText(pretvoriTipSaleUString(tipSale));
		setResizable(false);
		
	}
	
	/**
	 * Metoda koja puni JList sa odgovarajucim
	 * slobodnim salama koje mogu da se rezervisu.
	 */
	private void napuniListu(){
		DefaultListModel<String> dlm = new DefaultListModel<>();
		for(Sala sala : sale){
			dlm.addElement(sala.getNaziv_sale());
		}
		jlstSale.setModel(dlm);
		if (!sale.isEmpty()) {
			jlstSale.setSelectedIndex(0);
		} 
		
		
	}
	
	/**
	 * Metoda koja termine pretvara u odgovarajuce stringove
	 * da bi prikaz bio razumljiviji.
	 * @param termin
	 * @return
	 */
	private String pretvoriTerminUString(int termin) {
		switch (termin) {
		case 1:
			return "8:15-10:00";
		case 2:
			return "10:15 - 12:00";
		case 3:
			return "12:15 - 14:00";
		case 4:
			return "14:15 - 16:00";
		case 5:
			return "16:15 - 18:00";
		case 6:
			return "18:15 - 20:00";
		default:
			return "" + termin;
		}
	}

	/**
	 * Metoda koja pretvara dan u odgovarajuce stringove
	 * da bi prikaz bio razumljiviji.
	 * @param dan
	 * @return
	 */
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
	private JList getJlstSale() {
		if (jlstSale == null) {
			jlstSale = new JList();
			
		}
		return jlstSale;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(140, 10));
			panel.setLayout(null);
			panel.add(getLblDan());
			panel.add(getLblTermin());
			panel.add(getLblHost());
			panel.add(getTxtHost());
			panel.add(getBtnRezervisi());
			panel.add(getBtnOtkazi());
			panel.add(getLblIzabranDan());
			panel.add(getLblIzabranTermin());
			panel.add(getLblTipSale());
			panel.add(getLblIzabranTipSale());
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
			lblHost.setBounds(10, 85, 38, 14);
		}
		return lblHost;
	}
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(57, 82, 73, 20);
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
					
					if (jlstSale.getSelectedValue()==null) {
						JOptionPane.showMessageDialog(null, "Morate izabrati salu","Greska",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String sala = jlstSale.getSelectedValue().toString().trim();

					if (txtHost.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Morate uneti hosta","Greska",JOptionPane.WARNING_MESSAGE);
					}
					System.out.println(sala);
					String host = txtHost.getText();
					System.out.println(host);
					int termin = vreme + 1;
					Kontroler.dodajEvent(host, sala, datum, termin, tipSale);
					dispose();
				}
			});
			btnRezervisi.setBounds(10, 125, 89, 23);
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
			btnOtkazi.setBounds(10, 159, 89, 23);
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
			lblIzabranTermin.setBounds(60, 36, 73, 14);
		}
		return lblIzabranTermin;
	}
	
	private static boolean poklapaSe(int datum, int vreme, String tipSale){
		if (objekat.datum == datum && objekat.vreme == vreme && objekat.tipSale.equals(tipSale)) {
			return true;
		}
		return false;
	}
	private JScrollPane getScrollPane_1() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getJlstSale());
		}
		return scrollPane;
	}
	private JLabel getLblTipSale() {
		if (lblTipSale == null) {
			lblTipSale = new JLabel("Tip sale:");
			lblTipSale.setBounds(10, 60, 60, 14);
		}
		return lblTipSale;
	}
	private JLabel getLblIzabranTipSale() {
		if (lblIzabranTipSale == null) {
			lblIzabranTipSale = new JLabel("");
			lblIzabranTipSale.setBounds(70, 60, 60, 14);
		}
		return lblIzabranTipSale;
	}
	/**
	 * Sredjuje string za tip sale
	 * @param s
	 * @return
	 */
	private String pretvoriTipSaleUString(String s){
		switch(s){
		case "rc": return "RC";
		case "amfiteatar": return "Amfiteatar";
		case "ucionica": return "Ucionica";
		default: return s;
		}
	}
}
