package mysqlödev;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Appointment extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtSurname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Appointment frame = new Appointment();
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
	public Appointment() {
		setTitle("Create Appointment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Enter your ID");
		lblID.setBounds(23, 21, 86, 13);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(108, 18, 96, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(23, 53, 45, 13);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(108, 50, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(23, 90, 62, 13);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(108, 87, 96, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblDept = new JLabel("Department");
		lblDept.setBounds(23, 128, 75, 13);
		contentPane.add(lblDept);
		
		JComboBox cbDept = new JComboBox();
		cbDept.setBounds(108, 126, 96, 19);
		contentPane.add(cbDept);
		
		DBConnection db = new DBConnection();
		
		try {
			ResultSet rs = db.getDept();
			while(rs.next()) {
				cbDept.addItem(rs.getString(2));
			}
			
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		JLabel lblDoc = new JLabel("Doctor");
		lblDoc.setBounds(23, 166, 45, 13);
		contentPane.add(lblDoc);
		
		JComboBox cbDoc = new JComboBox();
		cbDoc.setBounds(108, 162, 96, 19);
		contentPane.add(cbDoc);
		
		DefaultComboBoxModel<String> doc_model = new DefaultComboBoxModel();
		
		cbDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = db.getResultSetDoc();
					doc_model.removeAllElements();
					while(rs.next()) {
						if(cbDept.getSelectedItem().toString().equals(rs.getString(4))) {
							doc_model.addElement(rs.getString(2)+" "+rs.getString(3));
							cbDoc.setModel(doc_model);
						}
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(23, 234, 75, 19);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(23, 210, 75, 19);
		contentPane.add(btnExit);
		
		JLabel lblDate = new JLabel("Appt. Date");
		lblDate.setBounds(235, 21, 62, 13);
		contentPane.add(lblDate);
		
		JComboBox cbDateDay = new JComboBox();
		cbDateDay.setBounds(245, 50, 52, 19);
		contentPane.add(cbDateDay);
		
		for(int i=1; i<=31; i++) {
			cbDateDay.addItem(i);
		}
		
		JLabel lblTime = new JLabel("Appt. Time");
		lblTime.setBounds(235, 90, 62, 13);
		contentPane.add(lblTime);
		
		JLabel lblNewLabel = new JLabel("/");
		lblNewLabel.setBounds(307, 53, 13, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("/");
		lblNewLabel_1.setBounds(382, 53, 13, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblYear = new JLabel("2022");
		lblYear.setBounds(395, 53, 45, 13);
		contentPane.add(lblYear);
		
		JComboBox cbDateMonth = new JComboBox();
		cbDateMonth.setBounds(320, 49, 52, 19);
		contentPane.add(cbDateMonth);
		
		for(int i=1; i<=12; i++) {
			cbDateMonth.addItem(i);
		}
		
		JComboBox cbHour = new JComboBox();
		cbHour.setBounds(245, 125, 52, 19);
		contentPane.add(cbHour);
		
		for(int i=8; i<=17; i++) {
			cbHour.addItem(i);
		}
		
		JLabel lblNewLabel_2 = new JLabel(":");
		lblNewLabel_2.setBounds(307, 128, 13, 13);
		contentPane.add(lblNewLabel_2);
		
		JComboBox cbMin = new JComboBox();
		cbMin.setBounds(320, 125, 52, 19);
		contentPane.add(cbMin);
		
		for(int i=0; i<=59; i=i+5) {
			cbMin.addItem(i);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Patient pat = new Patient();
				pat.id = Integer.parseInt(txtID.getText());
				pat.name = txtName.getText();
				pat.surname = txtSurname.getText();
				pat.department = cbDept.getSelectedItem().toString();
				pat.doctor = cbDoc.getSelectedItem().toString();
				pat.dateday = Integer.parseInt(cbDateDay.getSelectedItem().toString());
				pat.datemonth = Integer.parseInt(cbDateMonth.getSelectedItem().toString());
				pat.apphour = Integer.parseInt(cbHour.getSelectedItem().toString());
				pat.appmin = Integer.parseInt(cbMin.getSelectedItem().toString());
				
				
				DBConnection db = new DBConnection();
				try {
					ResultSet rs = db.getResultSetCmp(pat);
					if(rs.next()) {
						JOptionPane.showMessageDialog(contentPane, "Time period is taken");
					}
					else {
						db.newPatient(pat);
						JOptionPane.showMessageDialog(contentPane, "Saved");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(320, 184, 85, 21);
		contentPane.add(btnSave);
	}
}
