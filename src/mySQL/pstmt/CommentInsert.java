package mySQL.pstmt;

import java.sql.*;

public class CommentInsert {
	public CommentInsert() {}
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //µå¶óÀÌ¹ö ·Îµù
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "1234";
		
		try {
			Connection con = DriverManager.getConnection(url,user,pass); //Ä¿³Ø¼Ç È¹µæ
			PreparedStatement pstmt = null;
			try {
			String query = "insert into (name, contect)" + "values(?,?)";
			pstmt = con.prepareStatement(query); // statement °´Ã¼ »ý¼º //Äõ¸®Àü¼Û
				pstmt.setString(1, "È«±æµ¿");
				pstmt.setString(2, "¾È³çÇÏ¼¼¿ä");
				pstmt.executeQuery();
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
