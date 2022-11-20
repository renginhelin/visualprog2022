package mysqlödev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public Connection getConnected() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","1234");
		return con;
	}
	
	public ResultSet getResultSetDoc() throws SQLException {
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from doctor");
		return rs;
	}
	
	public ResultSet getDept() throws SQLException {
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from department");
		return rs;
	}
	
	public ResultSet getResultSetPt() throws SQLException {
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from patient");
		return rs;
	}
	
	public ResultSet getResultSetCmp(Patient pat) throws SQLException{
		Statement st = getConnected().createStatement();
		ResultSet rs = st.executeQuery("select * from patient where Department = '"+pat.department+"' and Doctor = '"+pat.doctor+
				"' and apphour = "+pat.apphour+" and appmin = "+pat.appmin+" and dateday = "+pat.dateday+" and datemonth = "+pat.datemonth);
		return rs;
	}
	
	public void newPatient(Patient pat) throws SQLException {
		String query = "insert into patient values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, pat.id);
		ps.setString(2, pat.name);
		ps.setString(3, pat.surname);
		ps.setString(4, pat.department);
		ps.setString(5, pat.doctor);
		ps.setInt(6, pat.apphour);
		ps.setInt(7, pat.appmin);
		ps.setInt(8, pat.dateday);
		ps.setInt(9, pat.datemonth);
		ps.executeUpdate();
	}
	
	public void newDoctor(Doctor doc) throws SQLException {
		String query = "insert into doctor values (?,?,?,?,?,?,?)";
		PreparedStatement ps = getConnected().prepareStatement(query);
		ps.setInt(1, doc.id);
		ps.setString(2, doc.name);
		ps.setString(3, doc.surname);
		ps.setString(4, doc.department);
		ps.setInt(5, doc.day);
		ps.setInt(6, doc.month);
		ps.setInt(7, doc.year);
		ps.executeUpdate();
	}
	
	public void deleteDoctor(int docid) throws SQLException {
		Statement st = getConnected().createStatement();
		st.executeUpdate("delete from doctor where id = "+docid);
	}
	
	public void deletePatient(int patid) throws SQLException {
		Statement st = getConnected().createStatement();
		st.executeUpdate("delete from patient where idpatient = "+patid);
	}
	
	public boolean checkLogin(String username, String password) throws SQLException{
		Statement st = getConnected().createStatement();
		ResultSet res = st.executeQuery("select * from adminlogin where username = '"+
		username+"' and password = '"+password+"'");
		if (res.next()){
			return true;
		}
		else{
			return false;
		}
		
	}

}
