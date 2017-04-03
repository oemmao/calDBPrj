package calNW.server.vo;

import java.io.Serializable;
import java.util.*;

public class CalExcMsgVO implements Serializable {
	private String msgAddZeroExc;
	private String msgSubZeroExc;
	private String msgMulOneExc;
	private String msgDivOneExc;
	
	public CalExcMsgVO() {		
	}
	
	public void setMsgAddZeroExc(String msg) {
		this.msgAddZeroExc = msg;
	}
	public String getMsgAddZeroExc() {
		return msgAddZeroExc;
	}
	
	public void setMsgSubZeroExc(String msg) {
		this.msgSubZeroExc = msg;
	}
	public String getMsgSubZeroExc() {
		return msgSubZeroExc;
	}
	
	public void setMsgMulOneExc(String msg) {
		this.msgMulOneExc = msg;
	}
	public String getMsgMulOneExc() {
		return msgMulOneExc;
	}
	
	public void setMsgDivOneExc(String msg) {
		this.msgDivOneExc = msg;
	}
	public String getMsgDivOneExc() {
		return msgDivOneExc;
	}
}
