package cal.server.service;

import java.util.*;

import cal.server.entity.*;
import cal.server.exception.*;
import cal.server.vo.*;

import java.io.*;

public class CalServiceImpl implements ICalService {
	private CalEntity ce;
	private CalExcMsgVO emvo;
	private CalVO[] cals;
	private int result;
	
	public CalServiceImpl() {
		emvo = new CalExcMsgVO();
		ce = new CalEntity();
	}

	public List<String[]> doService(List list) throws AddZeroException, SubZeroException, MulOneException, DivOneException {

		cals = (CalVO[]) list.get(0);
		emvo = (CalExcMsgVO) list.get(1);

		for (int i = 0; i < cals.length; i++) {
			String op1 = cals[i].getOp1();
			String op = cals[i].getOp();
			String op2 = cals[i].getOp2();
			
			if (op.equals(CalVO.ADD)) {
				if (Integer.parseInt(op1) == 0 || Integer.parseInt(op2) == 0) {
					emvo.setMsgAddZeroExc(getMsgAddZeroExc());
					throw new AddZeroException(emvo.getMsgAddZeroExc());
				} else {
					result = Integer.parseInt(op1) + Integer.parseInt(op2);
				}
			}

			else if (op.equals(CalVO.SUB)) {
				if (Integer.parseInt(op2) == 0) {
					emvo.setMsgSubZeroExc(getMsgSubZeroExc());
					throw new SubZeroException(emvo.getMsgSubZeroExc());
				} else {
					result = Integer.parseInt(op1) - Integer.parseInt(op2);
				}
			}

			else if (op.equals(CalVO.MUL)) {
				if (Integer.parseInt(op1) == 1 || Integer.parseInt(op2) == 1) {
					emvo.setMsgMulOneExc(getMsgMulOneExc());
					throw new MulOneException(emvo.getMsgMulOneExc());
				} else {
					result = Integer.parseInt(op1) * Integer.parseInt(op2);
				}
			}

			else if (op.equals(CalVO.DIV)) {
				if (Integer.parseInt(op2) == 1) {
					emvo.setMsgDivOneExc(getMsgDivOneExc());
					throw new DivOneException(emvo.getMsgDivOneExc());
				} else {
					result = Integer.parseInt(op1) / Integer.parseInt(op2);
				}
			}
			cals[i].setResult(result);	
		}
		return ce.doService(cals);
	}

	public String getMsgAddZeroExc() {
		return ce.getMsgAddZeroExc();
	}

	public String getMsgSubZeroExc() {
		return ce.getMsgSubZeroExc();
	}

	public String getMsgMulOneExc() {
		return ce.getMsgMulOneExc();
	}

	public String getMsgDivOneExc() {
		return ce.getMsgDivOneExc();
	}

}
