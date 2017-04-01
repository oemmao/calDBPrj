package cal.server.test;

import cal.server.controller.*;

public class CalServer {
	public static void main(String[] args) {
		CalController cc = new CalController(null);
		cc.doService();
	}
}