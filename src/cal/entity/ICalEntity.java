package cal.entity;

import java.util.List;
import java.util.Properties;

import cal.vo.*;

public interface ICalEntity {
	public List<String[]> doService(CalVO[] cals);
	public String getMsgAddZeroExc();
	public String getMsgSubZeroExc();
	public String getMsgMulOneExc();
	public String getMsgDivOneExc();
}
