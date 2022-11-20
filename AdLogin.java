package mysqlödev;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdLogin frame = new AdLogin();
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
	public AdLogin() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setBounds(10, 36, 66, 13);
		contentPane.add(lblUser);
		
		JLabel lblPass = new JLabel("Password");
		lblPass.setBounds(10, 59, 66, 13);
		contentPane.add(lblPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(86, 33, 96, 19);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(86, 56, 96, 19);
		contentPane.add(passwordField);
		
		JPanel panel = new JPanel();
		panel.setBounds(192, 21, 234, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.setVisible(false);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUser.getText();
				String password = new String(passwordField.getPassword());
				DBConnection db = new DBConnection();
				try {
					if(db.checkLogin(username, password)) {
						panel.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Login Failed.");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setBounds(116, 86, 66, 19);
		contentPane.add(btnLogin);
		
		JButton btnAdd = new JButton("Add New Doctor");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDoc add = new AddDoc();
				add.setVisible(true);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnAdd.setBounds(59, 26, 114, 37);
		panel.add(btnAdd);
		
		JButton btnDelete = new JButton("Delete Doctor");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteDoc delete = new DeleteDoc();
				delete.setVisible(true);
			}
		});
		btnDelete.setBounds(59, 76, 114, 37);
		panel.add(btnDelete);
		
		JButton btnList = new JButton("Doctor List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoctorList list = new DoctorList();
				list.setVisible(true);
			}
		});
		btnList.setBounds(59, 124, 114, 37);
		panel.add(btnList);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(20, 232, 73, 19);
		contentPane.add(btnExit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(20, 203, 73, 19);
		contentPane.add(btnBack);
	}
}
