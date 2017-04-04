package mySQL.pstmt;

import java.sql.*;

public class CommentDelete {
	public CommentDelete() {}
	public static void main(String[] rags) {
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
			String query = "delete from tb_comment";
			PreparedStatement psmt = con.prepareStatement(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
