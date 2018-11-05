package ignite.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.test.message.model.LoginModel;

public class Ignite {

	public Connection conn;
	final private String insertsql = "INSERT INTO MB_Login_test (reg_id, udid, dtstamp , login_status , cif , access_token , refresh_token ) "
			+ "VALUES (?,?,?,?,? , ? , ?)";

	public Ignite() {
		try {

			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
			conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Open JDBC connection.

	}

	public String igniteSelectExample() {
		String query  = ""; 
		try (Statement stmt = conn.createStatement()){
			try (ResultSet rs = stmt.executeQuery("select * from MB_Login_test")){
				while (rs.next()) {
					 query += "**********************************\n"+ "id:"+rs.getString(1) 
					+ "\nudid:"+ rs.getString(2) 
					+ "\ntimeStamp:"+  rs.getString(3) 
					+ "\ncif: "+  rs.getString(4)
					+ "\nlogin_status: " +rs.getString(5) 
					+ "\naccess_token: " +rs.getString(6)
					+ "\nrefrest_token: " + rs.getString(7) + "\n";
					
				}
				
			} catch (SQLException e) {
				return e.getMessage();
			}
		} catch (SQLException e) {
			return e.getMessage();
		}
		return query;		
	}
	
	public void igniteInsertoMbLogins (LoginModel login ) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()).toString();
		try(PreparedStatement stmt = conn.prepareStatement(insertsql)){			
			stmt.setLong(1, login.getReg_id());
			stmt.setString(2, login.getUdid());
			stmt.setString(3, timeStamp);
			stmt.setString(4, login.getLogin_status());
			stmt.setString(5, login.getCif());
			stmt.setString(6, login.getAccess_token());
			stmt.setString(7, login.getRefresh_token());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void igniteCreateMbLogins() throws ClassNotFoundException, SQLException {
		// register JDBC Driver
		try (Statement stmt = conn.createStatement()) {
			// Create table based on REPLICATED template.
			stmt.executeUpdate("CREATE TABLE MB_Login_test (" + " reg_id LONG PRIMARY KEY, udid VARCHAR"
					+ ",dtstamp VARCHAR " + ", login_status VARCHAR" + ", cif VARCHAR" + ", access_token VARCHAR"
					+ ",refresh_token VARCHAR) " + " WITH \"template=replicated\" ");
			// Create an index on the City table.
			stmt.executeUpdate("CREATE INDEX indx_id ON MB_login_test (reg_id)");
		}

	}

	public void igniteCreateSQLExample() throws ClassNotFoundException, SQLException {

		// Create database tables.
		try (Statement stmt = conn.createStatement()) {

			// Create table based on REPLICATED template.
			stmt.executeUpdate(
					"CREATE TABLE City (" + " id LONG PRIMARY KEY, name VARCHAR) " + " WITH \"template=replicated\"");

			// Create table based on PARTITIONED template with one backup.
			stmt.executeUpdate("CREATE TABLE Person (" + " id LONG, name VARCHAR, city_id LONG, "
					+ " PRIMARY KEY (id, city_id)) " + " WITH \"backups=1, affinityKey=city_id\"");
			// Create an index on the City table.
			stmt.executeUpdate("CREATE INDEX idx_city_name ON City (name)");

			// Create an index on the Person table.
			stmt.executeUpdate("CREATE INDEX idx_person_name ON Person (name)");
		}
	}
}
