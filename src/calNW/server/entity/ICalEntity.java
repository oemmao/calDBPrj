package calNW.server.entity;

import java.util.List;
import calNW.server.vo.*;

public interface ICalEntity {
	public List<String[]> doService(CalVO[] cals);
	public String getMsgAddZeroExc();
	public String getMsgSubZeroExc();
	public String getMsgMulOneExc();
	public String getMsgDivOneExc();
}
