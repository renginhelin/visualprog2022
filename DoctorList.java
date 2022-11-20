package mysqlödev;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

public class DoctorList extends JFrame {

	private JPanel contentPane;
	DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorList frame = new DoctorList();
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
	public DoctorList() {
		setTitle("Doctor List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 52, 416, 170);
		contentPane.add(scrollPane);
		
		model = new DefaultTableModel();
		
		table = new JTable();
		table.setModel(model);
		scrollPane.setViewportView(table);
		
		DBConnection db = new DBConnection();
		try {
			ResultSet rs = db.getResultSetDoc();
			String[] columns = new String[rs.getMetaData().getColumnCount()];
			for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				columns[i-1] = rs.getMetaData().getColumnName(i);
			}
			model.setColumnIdentifiers(columns);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnBack.setBounds(10, 232, 85, 21);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(341, 232, 85, 21);
		contentPane.add(btnExit);
		
		JButton btnList = new JButton("Get List");
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				DBConnection db = new DBConnection();
				ResultSet rs;
				try {
					rs = db.getResultSetDoc();
					int col;
					col = rs.getMetaData().getColumnCount();
					while (rs.next()){
						Object[] row = new Object[col];
						for (int i = 1; i <= col; i++) {
							row[i-1] = rs.getObject(i);
						}
						model.addRow(row);
				    }
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnList.setBounds(30, 21, 85, 21);
		contentPane.add(btnList);
	}
}
