package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import gui.kontroler.GUIKontroler;
import gui.model.TableModelRezSalaGUI;
import kontroler.Kontroler;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class RezSalaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblTipSale;
	private JComboBox comboBox;
	private JPanel panel_1;
	private JLabel lblYesU;
	private JLabel lblNoU;

	/**
	 * Create the frame.
	 */
	public RezSalaGUI() {
		setTitle("Rezervacija sala");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 869, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.EAST);
		contentPane.add(getPanel_1(), BorderLayout.SOUTH);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(100, 10));
			panel.add(getLblTipSale());
			panel.add(getComboBox());
		}
		return panel;
	}

	private JScrollPane getScrollPane(String tipSale) {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable(tipSale));
		}
		return scrollPane;
	}

	private JTable getTable(String tipSale) {
		if (table == null) {
			table = new JTable();
			table.setRowHeight(50);
			TableModelRezSalaGUI model = new TableModelRezSalaGUI(tipSale);
			table.setModel(model);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		}
		return table;
	}

	
	private JLabel getLblTipSale() {
		if (lblTipSale == null) {
			lblTipSale = new JLabel("Tip sale:");
		}
		return lblTipSale;
	}

	/**
	 * Metoda vraca comboBox koji kreira tabelu na odabir odgovarajuceg tipa sale
	 * i na nju postavlja listener na klik tabele koji ukoliko u polju postoji slobodna sala
	 * otvara novi JDialog sa ponudjenim salama i mogucnoscu da se odabere sala i rezervise.
	 * @return combo box
	 */
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] { "Amfiteatar", "RC", "Ucionica" }));
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String tipSale = comboBox.getSelectedItem().toString().toLowerCase().trim();
					if (table != null) {
						table.setModel(new TableModelRezSalaGUI(tipSale));
						table.repaint();
						 //return;
					}else{
						table = new JTable();

						table.setRowHeight(50);
						TableModelRezSalaGUI model = new TableModelRezSalaGUI(tipSale);
						table.setModel(model);
						DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
						centerRenderer.setHorizontalAlignment(JLabel.CENTER);
						table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
						// table.setPreferredScrollableViewportSize(new
						// Dimension(200, 250));
						scrollPane = new JScrollPane();
						scrollPane.setViewportView(table);
						contentPane.add(scrollPane, BorderLayout.CENTER);
						contentPane.revalidate();
						contentPane.repaint();
					}

					table.addMouseListener(new MouseListener() {

						@Override
						public void mouseReleased(MouseEvent e) {
							// TODO Auto-generated method stub

						}

						@Override
						public void mousePressed(MouseEvent e) {

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
							if (table.getModel().getValueAt(red, kolona) == "NO") {
								return;
							}

							if (kolona != 0) {
								try {
									GUIKontroler.prikaziZakazivanjeGUI(kolona, red, tipSale);
								} catch (SQLException e1) {
									JOptionPane.showMessageDialog(null,
											"Doslo je do greske pri citanju iz baze.\nProbajte malo kasnije", "Greska",
											JOptionPane.ERROR_MESSAGE);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(null, "Doslo je do greske:\n." + e1.getMessage(),
											"Greska", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					});
					
				}
			});
		}
		return comboBox;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setPreferredSize(new Dimension(10, 50));
			panel_1.setLayout(null);
			panel_1.add(getLblYesU());
			panel_1.add(getLblNoU());
		}
		return panel_1;
	}
	private JLabel getLblYesU() {
		if (lblYesU == null) {
			lblYesU = new JLabel("YES - u datom terminu postoji makar jedna slobodna sala");
			lblYesU.setBounds(10, 11, 326, 14);
		}
		return lblYesU;
	}
	private JLabel getLblNoU() {
		if (lblNoU == null) {
			lblNoU = new JLabel("NO - u datom terminu nema slobodnih sala");
			lblNoU.setBounds(10, 25, 326, 14);
		}
		return lblNoU;
	}
}