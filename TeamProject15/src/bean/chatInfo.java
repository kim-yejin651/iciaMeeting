package bean;

import java.sql.Date;

public class chatInfo {
 private String Ninkname;
 private String chatContent;
 private Date date;
public String getNinkname() {
	return Ninkname;
}
public void setNinkname(String ninkname) {
	Ninkname = ninkname;
}
public String getChatContent() {
	return chatContent;
}
public void setChatContent(String chatContent) {
	this.chatContent = chatContent;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
}
