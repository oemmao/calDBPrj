package calDBPrj_sample;

public class CalController {
	private CalService cs;
	private CalView cv;
	private int result;
	
	public CalController() {
		cs = new CalService();
		cv = new CalView();
	}
	void doService(String[] ops) {
		int op1 = Integer.parseInt(ops[0]);
		String op = ops[1];
		int op2 = Integer.parseInt(ops[2]);
		if(op.equals("add")) {
			result = cs.add(op1, op, op2);
		}
		cv.report(op1, op, op2, result);
	}
}
