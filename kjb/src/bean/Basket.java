package bean;

public class Basket {
	private String b_m_id;
	private String b_p_code;
	private int b_qty;
	private int b_price;
	private String p_name;
	private String p_oriFileName;
	private int p_b_oriqty;
	
	public int getP_b_oriqty() {
		return p_b_oriqty;
	}
	public void setP_b_oriqty(int p_b_oriqty) {
		this.p_b_oriqty = p_b_oriqty;
	}
	public String getP_oriFileName() {
		return p_oriFileName;
	}
	public void setP_oriFileName(String p_oriFileName) {
		this.p_oriFileName = p_oriFileName;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getB_m_id() {
		return b_m_id;
	}
	public void setB_m_id(String b_m_id) {
		this.b_m_id = b_m_id;
	}
	public String getB_p_code() {
		return b_p_code;
	}
	public void setB_p_code(String b_p_code) {
		this.b_p_code = b_p_code;
	}
	public int getB_qty() {
		return b_qty;
	}
	public void setB_qty(int b_qty) {
		this.b_qty = b_qty;
	}
	public int getB_price() {
		return b_price;
	}
	
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	
}
