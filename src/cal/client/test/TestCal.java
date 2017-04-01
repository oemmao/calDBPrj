package cal.client.test;
import cal.client.view.CalView;

public class TestCal {
	public static void main(String[] args) {	
			CalView cv = new CalView();
			cv.doService();
	}
}
//TestCal - test하는거임A
/*
String[] msg = { "0을 더하기",
			     "0을 빼기", 
			     "1을 곱하기",
			     "1로 나누기",
			   };

		if(args.length !=3) {
			System.out.println("Ex) java TestCal 5 add 3");
		} else {
			CalView cv = new CalView();
			cv.doService(args);
		}	
*/