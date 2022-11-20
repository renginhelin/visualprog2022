package mysqlödev;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CancelApp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelApp frame = new CancelApp();
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
	public CancelApp() {
		setTitle("Cancel Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter your ID to cancel your appointment:");
		lblNewLabel.setBounds(56, 52, 235, 23);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(171, 85, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection db = new DBConnection();
				try {
					ResultSet rs = db.getResultSetPt();
					int deleteID = Integer.parseInt(textField.getText());
					boolean status = rs.next();
					if(status) {
						while(rs.next()) {
							if(rs.getInt(1) == deleteID) {
								db.deletePatient(deleteID);
								JOptionPane.showMessageDialog(contentPane, "Your Appointment is deleted: "+rs.getString(2)+" "+rs.getString(3));
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Patient doesn't exist.");
								break;
							}
						}
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Patient doesn't exist.");
					}
				} catch (SQLException e1) {
						e1.printStackTrace();
					}
				
			}
		});
		btnCancel.setBounds(243, 125, 85, 21);
		contentPane.add(btnCancel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(29, 194, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(29, 225, 85, 21);
		contentPane.add(btnExit);
	}

}
