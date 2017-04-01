package cal.server.service;

import java.util.List;

import cal.server.exception.*;

public interface ICalService {
	public List<String[]> doService(List list) throws AddZeroException, SubZeroException, MulOneException, DivOneException;
	public String getMsgAddZeroExc();
	public String getMsgSubZeroExc();
	public String getMsgMulOneExc();
	public String getMsgDivOneExc();
	
}
