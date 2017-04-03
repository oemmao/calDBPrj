package calNW.server.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.net.*;
import java.io.*;
import calNW.server.exception.*;
import calNW.server.service.*;
import calNW.client.view.CalView;
import calNW.server.vo.*;

public class CalController {
	private CalView cv;
	private CalService cs;
	private CalVO[] cals;
	private CalExcMsgVO emvo;
	private List list;
	
	
	public CalController(CalView cv) {
		this.cv = cv;
		cs = new CalService();
	}
	
	public void doService() {
		ServerSocket ss = null;
		try {
		ss = new ServerSocket(5419);
		}catch (IOException e) {
			
			e.printStackTrace();
		} 
		
			while(true) {
				try {
					Socket s1 = ss.accept(); //요청대기
					ObjectInputStream in;
					ObjectOutputStream out;
				
					in = new ObjectInputStream(s1.getInputStream());
					Properties prop = (Properties)in.readObject(); //소켓으로 받은 데이터를 형변환해서 받아옴.
					
					emvo = new CalExcMsgVO();
					cals = new CalVO[prop.size()];
					for(int i=0; i<prop.size(); i++) {
						String[] ops = prop.getProperty("e"+(i+1)).split(" ");
						cals[i] = new CalVO(ops);
					}
					
					list = new ArrayList();
					list.add(cals);
					list.add(emvo);
				
					out = new ObjectOutputStream(s1.getOutputStream());

					try {
						
						List<String[]> resultList = cs.doService(list);
						list.add(resultList);						
//						out.writeObject(resultList);
//						cv.report(resultList);
						
					} catch(AddZeroException e) {
						list.add(e.getMessage());
					} catch(SubZeroException e) {
						list.add(e.getMessage());
					} catch(MulOneException e) {
						list.add(e.getMessage());
					} catch(DivOneException e) {
						list.add(e.getMessage());
					} catch(Exception e) {
						list.add(e.getMessage());
					} finally {
						out.writeObject(list);
						if(in != null && s1 != null) {
							in.close();
							s1.close();
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		
	}
}