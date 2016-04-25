package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import kontroler.Kontroler;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

	/**
	 * Create the frame.
	 */
	public RezSalaGUI() {
		setTitle("Rezervacija sala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.EAST);

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
							int red = table.getSelectedRow();
							int kolona = table.getSelectedColumn();
							if (table.getModel().getValueAt(red, kolona)== "NO") {
								return;
							}

							if (kolona != 0) {
								/*RezervacijaGUI rg = new RezervacijaGUI(kolona, red, tipSale);
								rg.setVisible(true);*/
								
								ZakazivanjeGUI zk = ZakazivanjeGUI.vratiObjekat(kolona, red, tipSale);
								zk.setVisible(true);
							}

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
							// TODO Auto-generated method stub

						}
					});
					
				}
			});
		}
		return comboBox;
	}
}