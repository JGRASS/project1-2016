package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import gui.model.TableModelPrikazSaleGUI;
import kontroler.Kontroler;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class PrikazSaleGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblSala;
	private JTextField txtNazivSale;
	private JButton btnPrikazi;
	private JButton btnRezervisi;
	private JLabel lblHost;
	private JTextField txtHost;
	private JLabel lblDan;
	private JLabel lblIzabranDan;
	private JLabel lblVreme;
	private JLabel lblIzabranoVreme;

	/**
	 * Create the frame.
	 */
	public PrikazSaleGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 904, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.NORTH);
		btnRezervisi.setVisible(false);
		lblHost.setVisible(false);
		txtHost.setVisible(false);
		lblDan.setVisible(false);
		lblVreme.setVisible(false);
		setTitle("Pregled sale");
	}

	private JScrollPane getScrollPane(String sala) {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable(sala));
		}
		return scrollPane;
	}

	private JTable getTable(String sala) {
		if (table == null) {
			table = new JTable();
			table.setRowHeight(50);
			TableModelPrikazSaleGUI model = new TableModelPrikazSaleGUI(sala);
			table.setModel(model);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		}
		return table;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(10, 30));
			panel.setLayout(null);
			panel.add(getLblSala());
			panel.add(getTxtNazivSale());
			panel.add(getBtnPrikazi());
			panel.add(getBtnRezervisi());
			panel.add(getLblHost());
			panel.add(getTxtHost());
			panel.add(getLblDan());
			panel.add(getLblIzabranDan());
			panel.add(getLblVreme());
			panel.add(getLblIzabranoVreme());
		}
		return panel;
	}

	private JLabel getLblSala() {
		if (lblSala == null) {
			lblSala = new JLabel("Sala:");
			lblSala.setBounds(10, 11, 32, 14);
		}
		return lblSala;
	}

	private JTextField getTxtNazivSale() {
		if (txtNazivSale == null) {
			txtNazivSale = new JTextField();
			txtNazivSale.setBounds(52, 8, 86, 20);
			txtNazivSale.setColumns(10);
		}
		return txtNazivSale;
	}

	private JButton getBtnPrikazi() {
		if (btnPrikazi == null) {
			btnPrikazi = new JButton("Prikazi");
			btnPrikazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String sala = txtNazivSale.getText().toUpperCase().trim();
					if (sala == null || sala.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Morate uneti salu", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					} else if (!Kontroler.daLiPostojiSala(sala)) {
						JOptionPane.showMessageDialog(null, "Sala koju ste uneli ne postoji", "Greska", JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (table != null) {
						table.setModel(new TableModelPrikazSaleGUI(sala));
						table.repaint();
						 //return;
					}else{
						table = new JTable();
						table.setRowHeight(50);
						TableModelPrikazSaleGUI model = new TableModelPrikazSaleGUI(sala);
						table.setModel(model);
						DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						centerRenderer.setHorizontalAlignment(JLabel.CENTER);
						table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
						scrollPane = new JScrollPane();
						scrollPane.setViewportView(table);
						contentPane.add(scrollPane, BorderLayout.CENTER);
						contentPane.revalidate();
						contentPane.repaint();
						table.addMouseListener(new MouseListener() {
							
							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public void mouseClicked(MouseEvent e) {
								int red = table.getSelectedRow();
								int kolona = table.getSelectedColumn();
								if (table.getModel().getValueAt(red, kolona)!= "YES") {
									btnRezervisi.setVisible(false);
									txtHost.setVisible(false);
									lblDan.setVisible(false);
									lblVreme.setVisible(false);
									lblIzabranDan.setVisible(false);
									lblIzabranoVreme.setVisible(false);
									return;
								}
								btnRezervisi.setVisible(true);
								lblHost.setVisible(true);
								txtHost.setVisible(true);
								lblDan.setVisible(true);
								lblVreme.setVisible(true);
								lblIzabranDan.setText(pretvoriDanUString(kolona));
								lblIzabranoVreme.setText(pretvoriTerminUString(red));
								lblIzabranDan.setVisible(true);
								lblIzabranoVreme.setVisible(true);
							}
						});
					}}
			});
			btnPrikazi.setBounds(148, 7, 89, 23);
		}
		return btnPrikazi;
	}
	private JButton getBtnRezervisi() {
		if (btnRezervisi == null) {
			btnRezervisi = new JButton("Rezervisi");
			btnRezervisi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int vreme = table.getSelectedRow();
					int datum = table.getSelectedColumn();
					String sala = txtNazivSale.getText().toUpperCase().trim();
					String host = txtHost.getText().trim();
					if (host == null || host.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Morate uneti hosta.", "Greska", JOptionPane.WARNING_MESSAGE);
					}
					String tipSale = Kontroler.vratiTipSale(sala);
					Kontroler.dodajEvent(host, sala, datum, vreme, tipSale);
					table.setModel(new TableModelPrikazSaleGUI(sala));
					table.repaint();
				}
			});
			btnRezervisi.setBounds(683, 7, 89, 23);
		}
		return btnRezervisi;
	}
	private JLabel getLblHost() {
		if (lblHost == null) {
			lblHost = new JLabel("Host: ");
			lblHost.setBounds(247, 11, 32, 14);
		}
		return lblHost;
	}
	private JTextField getTxtHost() {
		if (txtHost == null) {
			txtHost = new JTextField();
			txtHost.setBounds(289, 8, 130, 20);
			txtHost.setColumns(10);
		}
		return txtHost;
	}
	private JLabel getLblDan() {
		if (lblDan == null) {
			lblDan = new JLabel("Dan: ");
			lblDan.setBounds(429, 11, 32, 14);
		}
		return lblDan;
	}
	private JLabel getLblIzabranDan() {
		if (lblIzabranDan == null) {
			lblIzabranDan = new JLabel("");
			lblIzabranDan.setBounds(456, 11, 53, 14);
		}
		return lblIzabranDan;
	}
	private JLabel getLblVreme() {
		if (lblVreme == null) {
			lblVreme = new JLabel("Vreme: ");
			lblVreme.setBounds(528, 11, 46, 14);
		}
		return lblVreme;
	}
	private JLabel getLblIzabranoVreme() {
		if (lblIzabranoVreme == null) {
			lblIzabranoVreme = new JLabel("");
			lblIzabranoVreme.setBounds(570, 11, 103, 14);
		}
		return lblIzabranoVreme;
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
	
	/**
	 * Metoda koja termine pretvara u odgovarajuce stringove
	 * da bi prikaz bio razumljiviji.
	 * @param termin
	 * @return
	 */
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
}
