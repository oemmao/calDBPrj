package calNW.server.vo;

import java.io.Serializable;
import java.util.*;

public class CalVO implements Serializable {
	private String op1;
	private String op;
	private String op2;
	private int result;
	
	public static final String ADD = "add";
	public static final String SUB = "sub";
	public static final String MUL = "mul";
	public static final String DIV = "div";
	
	public CalVO(String[] ops) {
		this.op1 = ops[0];
		this.op = ops[1];
		this.op2 = ops[2];
	}	

	public String getOp1() {
		return op1;
	}
	public void setOp1(String op1) {
		this.op1 = op1;
	}
	public String getOp() {
		return op;
	}
	public void setOp(String op) {
		this.op = op;
	}
	public String getOp2() {
		return op2;
	}
	public void setOp2(String op2) {
		this.op2 = op2;
	}	
	public void setResult(int result) {
		this.result = result;
	}	
	public int getResult() {
		return result;
	}
}
