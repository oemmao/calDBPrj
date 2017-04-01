package cal.server.entity;

import java.io.*;
import java.util.*;
import java.util.Date;
import cal.server.vo.*;
import java.text.*;
import java.sql.*;

public class CalEntityImpl implements ICalEntity {
	private Properties resultProp;
	private Properties exceptionMsg;
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //JDBC 2. 컨넥션 획득
	private String user = "scott"; //JDBC 2. 컨넥션 획득
	private String pass = "tiger"; //JDBC 2. 컨넥션 획득
	private Connection con;
	private Statement stmt;
	private Statement stmt1;
	private ResultSet rs;
	
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
		resultProp = new Properties(); //Cal result를 File로 출력하기 위해 생성~
		List<String[]> resultList = new ArrayList<String[]>();
		String query;
		
		try {
			String comment = "해답(e1~e4)"; //Cal result를 File로 출력할때 한글이 깨지는 것을 방지하기 위해 사용
//			PrintWriter bufOutput = new PrintWriter(new FileWriter("src\\file\\log.dat"));
			BufferedWriter bufOutput = new BufferedWriter(new FileWriter("src\\cal\\server\\file\\log.dat")); //log File 출력을 위해 사용
			StringBuffer sb = new StringBuffer();
			sb.append("년   월 일 시 분 초   트랜잭션번호    유형");
			sb.append("\n"+"-------------------"+"  "+"---------------"+"  "+"---");		
			try {
//				bufOutput.write("년   월 일 시 분 초   트랜잭션번호    유형");
//				bufOutput.newLine();
//				bufOutput.write("-------------------"+"  "+"---------------"+"  "+"---");
				bufOutput.write(sb.toString());
				bufOutput.newLine();
				con = DriverManager.getConnection(url,user,pass); //JDBC 2.커넥션 획득 //공유가능		
				stmt = con.createStatement();	//JDBC 3.Statement 객체 생성
				for (int i=0; i < cals.length; i++) {
					resultProp.setProperty("e"+(i+1), Integer.toString(cals[i].getResult())); //Cal result 출력용
					String type	= cals[i].getOp();
					Date today = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
					SimpleDateFormat trNo = new SimpleDateFormat("yyyyMMddhhmmss");
									
					//String trNo = 	sdf.format(today);
					bufOutput.write(sdf.format(today) +"  "+"T"+trNo.format(today)+"  " + type);
					bufOutput.newLine();
					
					query = "insert into tb_cal(id, op1, op, op2, result) " +
	                        "values(seq_log.nextval, "+ cals[i].getOp1() +", '"+ cals[i].getOp() +"', "+ cals[i].getOp2() +", "+ cals[i].getResult() +")";
					//JDBC 4.쿼리전송
					int j = stmt.executeUpdate(query); //응답을 필요로 하지 않음.		
					System.out.println(j+"행이 추가되었습니다.");
					
					if (j > 0) {
						stmt1 = con.createStatement();
						String query1 = "SELECT * FROM (SELECT * FROM tb_cal ORDER BY id desc) WHERE rownum <2"; //result값을 가져오기
						rs = stmt.executeQuery(query1);
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
					}
//					query = "insert into tb_msg(id, code, message)" +
//							"values(msg_log.nextval,'100','AddZeroException')" ;
//					int k = stmt.executeUpdate(query);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				bufOutput.close();
				if(rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (stmt1 != null) {
					try {
						stmt1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			comment = new String(comment.getBytes("EUC-KR"), "8859_1"); //Cal result를 File로 출력할때 한글이 깨지는 것을 방지하기 위해 사용
			resultProp.store(new FileOutputStream("src\\cal\\server\\file\\output.txt"), comment); //Cal result를 File로 출력
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
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

//		for(int i=0; i<cals.length; i++) {
//			resultProp.setProperty("e"+(i+1), Integer.toString(cals[i].getResult()));
//		}
//		String comment = "해답(e1~e4)";
//		try {
//			comment = new String(comment.getBytes("EUC-KR"), "8859_1");
//			resultProp.store(new FileOutputStream("src\\file\\output.txt"), comment);
//		//src\\file\\output.txt
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//return "CalServiceImpl :: add() : AddZeroException : 0을 더하기";
//return "CalServiceImpl :: sub() : SubZeroException : 0을 빼기";
//return "CalServiceImpl :: mul() : MulOneException : 1을 곱하기";
//return "CalServiceImpl :: div() : DivOneException : 1로 나누기";