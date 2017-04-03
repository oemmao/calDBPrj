package cal.service;

import java.util.List;

import cal.exception.*;

public class CalService {
	private ICalService ics;
	
	public CalService() {
		ics = new CalServiceImpl();
	}
	
	public List<String[]> doService(List list) throws Exception {
		ics.doService(list);
		return list;
	}
}
