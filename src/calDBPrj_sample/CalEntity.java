package calDBPrj_sample;
import java.sql.*;

public class CalEntity {
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //2. 컨넥션 획득
	private String user = "scott"; //2. 컨넥션 획득
	private String pass = "tiger"; //2. 컨넥션 획득
	private Connection con;
	private PreparedStatement stmt;
	
	public CalEntity() { //1. 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //드라이버 로딩
		}catch(ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	public void insert(int op1, String op, int op2, int result) {
		try {		
			con = DriverManager.getConnection(url,user,pass);		
			try {	
				String query = "insert into tb_cal(id, op1, op, op2, result) " +
		                        "values(seq_log.nextval,?,?,?,?)";
				stmt = con.prepareStatement(query);	//3.Statement 객체 생성	 //4.쿼리전송	
				stmt.setInt(1, op1);
				stmt.setString(2, op);
				stmt.setInt(3, op2);
				stmt.setInt(4, result);
				
				stmt.executeUpdate(); 
			} finally {
				if (stmt != null) {
					stmt.close(); //6.자원반납
					if (con != null) {
						con.close(); //6.자원반납
					}
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} 
	}
}


//try {		
//con = DriverManager.getConnection(url,user,pass);		
//stmt = con.createStatement();	//3.Statement 객체 생성		
//String query = "insert into tb_cal(id, op1, op, op2, result) " +
//                "values(seq_log.nextval, "+ op1 +", '"+ op +"', "+ op2 +", "+ result +")";
//int i= stmt.executeUpdate(query); //응답을 필요로 하지 않음. /////4.쿼리전송			
//System.out.println(i+"행이 추가되었습니다.");
//stmt.close(); //6.자원반납
//con.close(); //6.자원반납
//}catch(SQLException e) {
//e.printStackTrace();
//} 