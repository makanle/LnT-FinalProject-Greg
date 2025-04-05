package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private static final String URL = "jdbc:mysql://localhost:3306/puddingdb";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
//	private Connection con;
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public Database() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection(URL, USER, PASSWORD);
//		} catch(ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public PreparedStatement ps(String query) throws SQLException{
//		return con.prepareStatement(query);
//	}
}
