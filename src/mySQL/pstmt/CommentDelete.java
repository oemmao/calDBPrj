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
			PreparedStatement pstmt = null;
			try {
			String query = "delete from tb_comment";
			pstmt = con.prepareStatement(query);
			pstmt.executeUpdate();
			} finally {
				if (pstmt != null) {
					pstmt.close();
					if (con != null) {
						con.close();
					}
				}
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
