package calNW.server.service;

import java.util.List;
import calNW.server.exception.*;

public class CalService {
	private ICalService ics;
	
	public CalService() {
		ics = new CalServiceImpl();
	}
	
	public List<String[]> doService(List list) throws Exception {
		return ics.doService(list);
	}
}
