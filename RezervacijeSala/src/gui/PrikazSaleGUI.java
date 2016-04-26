package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PrikazSaleGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblSala;
	private JTextField txtNazivSale;
	private JButton btnPrikazi;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { PrikazSaleGUI frame = new
	 * PrikazSaleGUI(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the frame.
	 */
	public PrikazSaleGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 904, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.NORTH);
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
		}
		return panel;
	}

	private JLabel getLblSala() {
		if (lblSala == null) {
			lblSala = new JLabel("Sala:");
			lblSala.setBounds(219, 11, 32, 14);
		}
		return lblSala;
	}

	private JTextField getTxtNazivSale() {
		if (txtNazivSale == null) {
			txtNazivSale = new JTextField();
			txtNazivSale.setBounds(261, 8, 86, 20);
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
					}}
			});
			btnPrikazi.setBounds(399, 7, 89, 23);
		}
		return btnPrikazi;
	}
}
