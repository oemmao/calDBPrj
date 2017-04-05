package cal.view;

import java.util.*;

import cal.controller.*;
import cal.vo.*;

import java.io.*;

public class CalViewImpl implements ICalView {
	private CalController cc;
	private Properties prop;
	
	public CalViewImpl(CalView cv) {
		cc = new CalController(cv);
	}
	
	public void doService() {	
		prop = new Properties();
			try {
				prop.load(new FileInputStream("src\\cal\\file\\input.txt"));
			} catch (IOException e) {
				System.out.println("지정된 파일을 찾을 수 없습니다.");
			}
			cc.doService(prop);
	}
	
	public void report(List list) {	
		 List<String[]> result = (List<String[]>)list.get(2); // list.get(2)에 resultList가 담겨있음. //형변환 
		for(String[] str : result) {
			System.out.println(Arrays.toString(str));
		}
	}
	
	public void errReport(String msg) {
		System.out.println(msg);
	}

}

/*
for(int i=0; i<cals.length; i++) {
System.out.println("e"+(i+1)+"=" + cals[i].getResult());
}
*/