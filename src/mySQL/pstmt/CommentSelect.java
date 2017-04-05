package mySQL.pstmt;

import java.sql.*;

public class CommentSelect {
	public CommentSelect() {}
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //µå¶óÀÌ¹ö·Îµù
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "1234";
		
		try {
			Connection con = DriverManager.getConnection(url, user, pass); //Ä¿³Ø¼Ç È¹µæ
			PreparedStatement pstmt = null;
			try {
				String query = "select * from tb_comment";
				pstmt = con.prepareStatement(query); //Statement °´Ã¼ »ý¼º //Äõ¸® Àü¼Û
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					String id = rs.getString("id").trim();
					String name = rs.getString("name").trim();
					String contect = rs.getString("contect").trim();
					String str = id +" "+ name +" "+ contect;
					System.out.println(str);
				}	
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
