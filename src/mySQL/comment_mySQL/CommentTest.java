package mySQL.comment_mySQL;

import java.sql.*;

public class CommentTest {
    public CommentTest() {}
    public static void main(String args[]) {	
		try {
			Class.forName("com.mysql.jdbc.Driver");	
		}catch(ClassNotFoundException e ) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/test";
		String user = "root";
		String pass = "1234";
	try {		
		Connection con = DriverManager.getConnection(url,user,pass);		
		Statement stmt = con.createStatement();		
		String query = "SELECT * FROM tb_comment";		
		ResultSet rs = stmt.executeQuery(query);		
		while(rs.next()){
                    String id=rs.getString("id").trim();
                    String content=rs.getString("content").trim();
                    String name=rs.getString("name").trim();
                    System.out.println("id= " + id);
                    System.out.println("content= " + content);
                    System.out.println("name= " + name);
		}
        rs.close();		stmt.close();		con.close();
	}catch(SQLException e) {
		e.printStackTrace();
	}    
   }   
}