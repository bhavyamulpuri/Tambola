import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.PreparedStatement;

public class Databasequeries {
	public ResultSet getData(String table_name) {
		System.out.println("called");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
			PreparedStatement ps = con.prepareStatement("select * from " + table_name);
			return ps.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public ResultSet putData(String table_name,String[] data) {
		//System.out.println("put"+data[0]+" "+data[1]);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "123456");
			PreparedStatement ps = con.prepareStatement("insert into " + table_name+" values('"+data[0]+"' , '"+data[1]+"');");
			ps.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
}
