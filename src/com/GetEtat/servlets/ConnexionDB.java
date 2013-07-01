package com.GetEtat.servlets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnexionDB{
	
	public Connection connect;
	Statement st;

	void loadDriver() throws ClassNotFoundException{
		Class.forName("com.mysql.jdbc.Driver");
	}
	
	Connection newConnection() throws SQLException{
		final String url = "jdbc:mysql://localhost:3306/BDDFinal";
		Connection conn = DriverManager.getConnection(url, "root", "yodalapute123");
		return conn;
	}
	
	public ConnexionDB() throws ClassNotFoundException, SQLException {
		loadDriver();
		connect = newConnection();
	}
	
	public void listMaison() throws SQLException{
		connect = newConnection();
		st = connect.createStatement();
		/*String query = "Select * from maison";
		ResultSet rs = st.executeQuery(query);		
		 while (rs.next()) {
			 System.out.println(rs.getString(1));
		 }*/
	}
	
	
}
