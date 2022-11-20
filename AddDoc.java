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
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddDoc extends JFrame {

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
					AddDoc frame = new AddDoc();
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
	public AddDoc() {
		setTitle("Add New Doctor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("Doctor ID");
		lblID.setBounds(20, 23, 58, 13);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBounds(99, 20, 96, 19);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(20, 63, 68, 13);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(99, 60, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(20, 109, 58, 13);
		contentPane.add(lblSurname);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(99, 106, 96, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblDept = new JLabel("Department");
		lblDept.setBounds(21, 161, 67, 13);
		contentPane.add(lblDept);
		
		JComboBox cbDept = new JComboBox();
		cbDept.setBounds(99, 158, 96, 19);
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
		
		JLabel lblDay = new JLabel("Birth Day");
		lblDay.setBounds(232, 23, 59, 13);
		contentPane.add(lblDay);
		
		JComboBox cbDay = new JComboBox();
		cbDay.setBounds(318, 20, 68, 19);
		contentPane.add(cbDay);
		
        for(int i=1; i<=31; i++) {
			cbDay.addItem(i);
		}
		
		JLabel lblMonth = new JLabel("Birth Month");
		lblMonth.setBounds(232, 63, 76, 13);
		contentPane.add(lblMonth);
		
		JComboBox cbMonth = new JComboBox();
		cbMonth.setBounds(318, 60, 68, 19);
		contentPane.add(cbMonth);
		
		for(int i=1; i<=12; i++) {
			cbMonth.addItem(i);
		}
		
		JLabel lblYear = new JLabel("Birth Year");
		lblYear.setBounds(232, 109, 76, 13);
		contentPane.add(lblYear);
		
		JComboBox cbYear = new JComboBox();
		cbYear.setBounds(318, 105, 68, 19);
		contentPane.add(cbYear);
		
		for(int i=1940; i<=2021; i++) {
			cbYear.addItem(i);
		}
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Doctor doc = new Doctor();
				doc.id = Integer.parseInt(txtID.getText());
				doc.name = txtName.getText();
				doc.surname = txtSurname.getText();
				doc.department = cbDept.getSelectedItem().toString();
				doc.day = Integer.parseInt(cbDay.getSelectedItem().toString());
				doc.month = Integer.parseInt(cbMonth.getSelectedItem().toString());
				doc.year = Integer.parseInt(cbYear.getSelectedItem().toString());
				
				DBConnection db = new DBConnection();
				try {
					
					db.newDoctor(doc);
					JOptionPane.showMessageDialog(contentPane, "Saved");
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(261, 157, 85, 21);
		contentPane.add(btnSave);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(328, 204, 76, 19);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(328, 233, 76, 19);
		contentPane.add(btnExit);
	}
}
