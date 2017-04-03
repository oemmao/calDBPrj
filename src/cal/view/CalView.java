package cal.view;

import java.util.List;

import cal.vo.*;

public class CalView {
	private ICalView icv;
	
	public CalView() {
		icv = new CalViewImpl(this);
	}
	
	public void doService() {
		icv.doService();
	}
	
	public void report(List<String[]> resultList) {
		icv.report(resultList);
	}
	
	public void errReport(String msg) {
		icv.errReport(msg);
	}
	
}
