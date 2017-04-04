package cal.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

import cal.exception.*;
import cal.service.*;
import cal.view.CalView;
import cal.vo.*;

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
	
	public void doService(Properties prop) {
		emvo = new CalExcMsgVO();
		cals = new CalVO[prop.size()];
		for(int i=0; i<prop.size(); i++) {
			String[] ops = prop.getProperty("e"+(i+1)).split(" ");
			cals[i] = new CalVO(ops);
		}
		
		list = new ArrayList();
		list.add(cals);
		list.add(emvo);

		try {
			cs.doService(list);
			cv.report(list);
		} catch(AddZeroException e) {
			cv.errReport(e.getMessage());
		} catch(SubZeroException e) {
			cv.errReport(e.getMessage());
		} catch(MulOneException e) {
			cv.errReport(e.getMessage());
		} catch(DivOneException e) {
			cv.errReport(e.getMessage());
		} catch(Exception e) {
			cv.errReport(e.getMessage());
			e.printStackTrace();
		}
	}
}
