package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static dao.JdbcUtil.*;

public class WordDao {

	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public WordDao() {
		con = getConnection();
	}
	
	public void closeDao() {
		close(rs, pstmt, con);
	}
	
	public boolean allWord(ArrayList<String> sList) {
		try {
			String sql = "SELECT * FROM WORDS";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sList.add(rs.getNString("words"));
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
}
