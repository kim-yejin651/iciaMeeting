package bean;

import java.util.Date;

public class Order {
	String o_code;
	String o_m_id;
	Date o_date;
	int o_total;
	public String getO_code() {
		return o_code;
	}
	public void setO_code(String o_code) {
		this.o_code = o_code;
	}
	public String getO_m_id() {
		return o_m_id;
	}
	public void setO_m_id(String o_m_id) {
		this.o_m_id = o_m_id;
	}
	public Date getO_date() {
		return o_date;
	}
	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}
	public int getO_total() {
		return o_total;
	}
	public void setO_total(int o_total) {
		this.o_total = o_total;
	}
	
}
