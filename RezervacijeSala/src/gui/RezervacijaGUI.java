package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domen.Sala;

import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class RezervacijaGUI extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList jlstSale;
	private JPanel panel;
	private JButton btnRezervacija;
	private JButton btnOtkazi;

	LinkedList<Sala> sale;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RezervacijaGUI frame = new RezervacijaGUI();
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
	public RezervacijaGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 304, 301);
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
			panel.setPreferredSize(new Dimension(120, 50));
			panel.setLayout(null);
			panel.add(getBtnRezervacija());
			panel.add(getBtnOtkazi());
		}
		return panel;
	}
	private JButton getBtnRezervacija() {
		if (btnRezervacija == null) {
			btnRezervacija = new JButton("Rezervisi");
			btnRezervacija.setBounds(10, 11, 100, 23);
		}
		return btnRezervacija;
	}
	private JButton getBtnOtkazi() {
		if (btnOtkazi == null) {
			btnOtkazi = new JButton("Otkazi");
			btnOtkazi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RezervacijaGUI rg = new RezervacijaGUI();
					rg.dispose();
				}
			});
			btnOtkazi.setBounds(10, 45, 100, 23);
		}
		return btnOtkazi;
	}
	
	public void napuniListu(){
		DefaultListModel<Sala> dlm = new DefaultListModel<>();
		
	}
}
