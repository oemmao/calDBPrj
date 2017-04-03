package calNW.client.view;

import java.util.List;
import calNW.server.vo.CalVO;

public interface ICalView {
	public void doService();
	public void report(List<String[]> resultList);
	public void errReport(String msg);
}
