package cal.test;
import cal.view.CalView;

public class TestCal {
	public static void main(String[] args) {	
			CalView cv = new CalView();
			cv.doService();
	}
}

/*
String[] msg = { "0�� ���ϱ�",
			     "0�� ����", 
			     "1�� ���ϱ�",
			     "1�� ������",
			   };

		if(args.length !=3) {
			System.out.println("Ex) java TestCal 5 add 3");
		} else {
			CalView cv = new CalView();
			cv.doService(args);
		}	
*/