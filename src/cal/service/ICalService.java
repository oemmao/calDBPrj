package cal.service;

import java.util.List;

import cal.exception.*;

public interface ICalService {
	public List<String[]> doService(List list) throws Exception;
	public String getMsgAddZeroExc();
	public String getMsgSubZeroExc();
	public String getMsgMulOneExc();
	public String getMsgDivOneExc();
	
}
