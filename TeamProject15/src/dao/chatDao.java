package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.chatInfo;

public class chatDao {
Connection con=JdbcUtil.getConnection();
PreparedStatement pstmt;
ResultSet rs;

	public void chatInfo(chatInfo ci) {
		String sql = "INSERT INTO CHAT VALUES(?,?,DEFAULT)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, ci.getNinkname());
			pstmt.setNString(2, ci.getChatContent());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("채팅정보 예외처리");
			e.printStackTrace();
		}
		
		
	}

}
