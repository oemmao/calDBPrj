package calNW.server.entity;

import java.io.*;
import java.util.List;
import java.util.Properties;
import calNW.server.vo.*;

public class CalEntity {
	private ICalEntity ice;
	
	public CalEntity() {
		ice = new CalEntityImpl();
	}
	
	public List<String[]> doService(CalVO[] cals) {
		return ice.doService(cals);
	}
	public String getMsgAddZeroExc() {
		return ice.getMsgAddZeroExc();
	}
	public String getMsgSubZeroExc() {
		return ice.getMsgSubZeroExc();
	}
	public String getMsgMulOneExc() {
		return ice.getMsgMulOneExc();
	}
	public String getMsgDivOneExc() {
		return ice.getMsgDivOneExc();
	}
}
