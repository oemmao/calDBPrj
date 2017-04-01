package calDBPrj_sample;

public class CalService {
	private CalEntity ce;
	private int result;
	
	public CalService() {
		ce = new CalEntity();
	}
	
	public int add(int op1, String op, int op2) {
		if(op.equals("add")) {
		result = op1 + op2;
		}
		ce.insert(op1, op, op2, result);
		
		return result;
	}
}
