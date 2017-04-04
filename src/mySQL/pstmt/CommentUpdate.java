package mySQL.pstmt;

import java.sql.*;

public class CommentUpdate {
	public CommentUpdate() {}
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //드라이버 로딩
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "1234";
		
		try {
			Connection con = DriverManager.getConnection(url,user,pass);
			String query = "Update tb_comment set name='강감찬' ";
			PreparedStatement psmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
