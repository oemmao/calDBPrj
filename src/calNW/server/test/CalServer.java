package calNW.server.test;

import calNW.server.controller.*;

public class CalServer {
	public static void main(String[] args) {
		CalController cc = new CalController(null);
		cc.doService();
	}
}