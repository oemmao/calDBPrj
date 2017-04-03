package cal.server.entity;

import java.io.*;
import java.util.*;
import cal.server.vo.*;
import java.sql.*;

public class CalEntityImpl implements ICalEntity {
	private Properties exceptionMsg;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //약속된 규약
	private String user = "scott"; //약속된 규약
	private String pass = "tiger"; //약속된 규약
	private Connection con;
	private PreparedStatement stmt;
	private Statement stmt1;
	private ResultSet rs;
	private List<String[]> resultList;
	
	public CalEntityImpl() {
		exceptionMsg = new Properties(); //예외메시지 파일 불러오기~
		try {
			exceptionMsg.load(new FileInputStream("src\\cal\\server\\file\\exceptionmsg.txt"));	
			Class.forName("oracle.jdbc.driver.OracleDriver"); //JDBC 1. 드라이버 로딩
		} catch (IOException e) {
			System.out.println("예외메시지 파일을 찾을 수 없습니다.");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}
	}
	
	public List<String[]> doService(CalVO[] cals) {	
		resultList = new ArrayList<String[]>();		
		try {
			con = DriverManager.getConnection(url,user,pass); //JDBC 2.커넥션 획득 //공유가능
			String query = "insert into tb_cal(id, op1, op, op2, result)" +
					"values(seq_log.nextval, ?, ?, ?, ?)";
			stmt = con.prepareStatement(query);	//JDBC 3.Statement 객체 생성 //JDBC 4.쿼리전송
			for (int i=0; i < cals.length; i++) {							
				stmt.setInt(1, Integer.parseInt(cals[i].getOp1()));
				stmt.setString(2, cals[i].getOp());
				stmt.setInt(3, Integer.parseInt(cals[i].getOp2()));
				stmt.setInt(4, cals[i].getResult());
				
				int j = stmt.executeUpdate(); //응답을 필요로 하지 않음.		
				System.out.println(j+"행이 추가되었습니다.");
				
				if (j > 0) {
					tableData();
				}
			}	  
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt1 != null) {
				try {
					stmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	return resultList;
	}

	public void tableData() {
		try {
			stmt1 = con.createStatement();
			String query1 = "select * from (select * from tb_cal order by id desc) where rownum <2"; //result값을 가져오기
			
			rs = stmt1.executeQuery(query1);
			rs.next();
			String id = rs.getString("id").trim();
			String op1 = rs.getString("op1").trim();
			String op = rs.getString("op").trim();
			String op2 = rs.getString("op2").trim();
			String result = rs.getString("result").trim();	
								
			String[] ops = new String[5];
			ops[0] = id;
			ops[1] = op1;
			ops[2] = op;
			ops[3] = op2;
			ops[4] = result;
			
			resultList.add(ops);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getMsgAddZeroExc() {	
		return exceptionMsg.getProperty("e1");
	}
	
	public String getMsgSubZeroExc() {
		return exceptionMsg.getProperty("e2");
	}
	
	public String getMsgMulOneExc() {
		return exceptionMsg.getProperty("e3");
	}
	
	public String getMsgDivOneExc() {
		return exceptionMsg.getProperty("e4");
	}
}