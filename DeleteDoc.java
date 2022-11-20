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
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DeleteDoc extends JFrame {

	private JPanel contentPane;
	private JTextField txtDelete;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDoc frame = new DeleteDoc();
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
	public DeleteDoc() {
		setTitle("Delete Doctor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteMsg = new JLabel("Enter the ID of the doctor you want to delete:");
		lblDeleteMsg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDeleteMsg.setBounds(36, 32, 281, 43);
		contentPane.add(lblDeleteMsg);
		
		txtDelete = new JTextField();
		txtDelete.setBounds(167, 69, 115, 21);
		contentPane.add(txtDelete);
		txtDelete.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBConnection db = new DBConnection();
				try {
					ResultSet rs = db.getResultSetDoc();
					int deleteID = Integer.parseInt(txtDelete.getText());
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
		btnNewButton.setBounds(258, 108, 85, 21);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(36, 189, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(36, 220, 85, 21);
		contentPane.add(btnExit);
	}
}
