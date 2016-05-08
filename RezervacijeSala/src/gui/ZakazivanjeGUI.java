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
import gui.kontroler.GUIKontroler;
import kontroler.Kontroler;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JScrollPane scrollPane;
	private JLabel lblTipSale;
	private JLabel lblIzabranTipSale;
	//parametri
	private String tipSale;
	private int datum;
	private int vreme;
	

	
	
	
	
	/**
	 * Create the dialog.
	 */
	public ZakazivanjeGUI() {
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
	 * @throws SQLException 
	 */
	public ZakazivanjeGUI(int datum, int vreme, String tipSale) throws SQLException {
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
		napuniListu();
		lblIzabranTermin.setText(GUIKontroler.pretvoriTerminUString(vreme));
		lblIzabranDan.setText(GUIKontroler.pretvoriDanUString(datum));
		lblIzabranTipSale.setText(GUIKontroler.pretvoriTipSaleUString(tipSale));
		setResizable(false);
		
	}
	
	/**
	 * Metoda koja puni JList sa odgovarajucim
	 * slobodnim salama koje mogu da se rezervisu.
	 * @throws SQLException 
	 */
	private void napuniListu() throws SQLException{
		DefaultListModel<String> dlm = GUIKontroler.vratiListModel(datum, vreme, tipSale);//vraca listu slobodnih sala
		jlstSale.setModel(dlm);
		if (dlm.getSize() != 0) {
			jlstSale.setSelectedIndex(0);
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
			txtHost.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						rezervisi();
						dispose();
					}					
				}
			});
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
					rezervisi();
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

	private void rezervisi(){
		if (jlstSale.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "Morate izabrati salu", "Greska",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		String sala = jlstSale.getSelectedValue().toString().trim();

		if (txtHost.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Morate uneti hosta", "Greska",
					JOptionPane.WARNING_MESSAGE);
		}
		String host = txtHost.getText();

		try {
			String poruka = "Host: " + host + "\nSala: " + sala + "\nTip sale: " + tipSale + "\nDan: "
					+ GUIKontroler.pretvoriDanUString(datum) + "\nVreme: " + GUIKontroler.pretvoriTerminUString(vreme);
			int opcija = JOptionPane.showConfirmDialog(contentPanel, poruka, "Potvrdite rezervaciju", JOptionPane.YES_NO_OPTION);
			if (opcija == JOptionPane.YES_OPTION) {
				GUIKontroler.rezervisi(host, sala, datum, vreme, tipSale);
				JOptionPane.showMessageDialog(contentPanel, poruka, "Uspesna rezervacija", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
			
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null,
					"Doslo je do greske pri upisivanju u bazu.\nSala nije rezervisana", "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
