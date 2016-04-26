package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JTable;
import java.awt.Dimension;

public class SalaGUI extends JFrame {
	
	private String tipSale;
	private String sala;

	private JPanel contentPane;
	private JTable jtblSala;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaGUI frame = new SalaGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SalaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getJtblSala(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
	}
	
	public SalaGUI(String sala, String tipSale) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getJtblSala(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.EAST);
		this.sala = sala;
		this.tipSale = tipSale;
	}

	private JTable getJtblSala() {
		if (jtblSala == null) {
			jtblSala = new JTable();
			jtblSala.setRowHeight(50);
			TableModelSala model = new TableModelSala(sala);
			jtblSala.setModel(model);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(JLabel.CENTER);
			jtblSala.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		}
		return jtblSala;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(170, 10));
		}
		return panel;
	}
}
