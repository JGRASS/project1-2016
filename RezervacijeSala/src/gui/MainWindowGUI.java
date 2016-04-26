package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class MainWindowGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnRezervisiSalu;
	private JButton btnProveriSalu;
	private JLabel lblLogo;
	ImageIcon logo;
	/**
	 * Launch the application.
	 */
	/*
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
	*/
	/**
	 * Create the frame.
	 */
	public MainWindowGUI() {
		setTitle("Sistem za rezervisanje sala");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 425, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnRezervisiSalu());
		contentPane.add(getBtnProveriSalu());
		/*
		try {
			URL url = new URL("http://www.fon.bg.ac.rs/wp-content/themes/fon/img/fon-logo=cir=.png");
			logo = new ImageIcon(url);
			lblLogo = new JLabel(logo);
			lblLogo.setBounds(0, 0, 409, 261);
			contentPane.add(lblLogo);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		logo = new ImageIcon("resource/img/foncic.png");
		lblLogo = new JLabel(logo);
		lblLogo.setBounds(0, 0, 409, 261);
		contentPane.add(lblLogo);
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
			btnRezervisiSalu.setBounds(63, 218, 123, 32);
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
			btnProveriSalu.setBounds(212, 218, 123, 32);
		}
		return btnProveriSalu;
	}
	private JLabel getLblLogo() {
		if (lblLogo == null) {
			logo = new ImageIcon(getClass().getResource("/resource/img/foncic.png"));
			lblLogo = new JLabel(logo);
			lblLogo.setBounds(0, 0, 409, 261);
		}
		return lblLogo;
	}
}
