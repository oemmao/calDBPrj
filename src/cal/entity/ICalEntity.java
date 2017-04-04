package cal.entity;

import java.util.List;
import java.util.Properties;

import cal.vo.*;

public interface ICalEntity {
	public void doService(List list);
	public String getMsgAddZeroExc();
	public String getMsgSubZeroExc();
	public String getMsgMulOneExc();
	public String getMsgDivOneExc();
}
