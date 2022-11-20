package mysqlödev;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setTitle("Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnApp = new JButton("Patient Appointment ");
		btnApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PatientWindow app = new PatientWindow();
				app.setVisible(true);
			}
		});
		btnApp.setBounds(127, 39, 157, 58);
		contentPane.add(btnApp);
		
		JButton btnAdministratorLogin = new JButton("Administrator Login");
		btnAdministratorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdLogin log =  new AdLogin();
				log.setVisible(true);
			}
		});
		btnAdministratorLogin.setBounds(127, 132, 157, 58);
		contentPane.add(btnAdministratorLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(319, 232, 85, 21);
		contentPane.add(btnExit);
	}
}
