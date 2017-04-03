package cal.view;

import java.util.List;

import cal.vo.CalVO;

public interface ICalView {
	public void doService();
	public void report(List<String[]> resultList);
	public void errReport(String msg);
}
