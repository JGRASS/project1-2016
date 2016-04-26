package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindowGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnRezervisiSalu;
	private JButton btnProveriSalu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowGUI frame = new MainWindowGUI();
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
	public MainWindowGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnRezervisiSalu());
		contentPane.add(getBtnProveriSalu());
	}
	private JButton getBtnRezervisiSalu() {
		if (btnRezervisiSalu == null) {
			btnRezervisiSalu = new JButton("Rezervisi salu");
			btnRezervisiSalu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					RezSalaGUI rez = new RezSalaGUI();
					rez.setVisible(true);
				}
			});
			btnRezervisiSalu.setBounds(64, 168, 123, 23);
		}
		return btnRezervisiSalu;
	}
	
	private JButton getBtnProveriSalu() {
		if (btnProveriSalu == null) {
			btnProveriSalu = new JButton("Proveri salu");
			btnProveriSalu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PrikazSaleGUI prikaz = new PrikazSaleGUI();
					prikaz.setVisible(true);
				}
			});
			btnProveriSalu.setBounds(197, 168, 123, 23);
		}
		return btnProveriSalu;
	}
}
