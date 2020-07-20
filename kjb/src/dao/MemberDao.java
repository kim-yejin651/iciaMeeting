package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dao.JdbcUtil.*;

import bean.Member;

public class MemberDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public MemberDao() {
		con = JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public boolean joinAccess(Member mb) {
		try {
			String sql = "INSERT INTO kjb.ISMEMBER VALUES(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			pstmt.setNString(2, mb.getPw());
			pstmt.setNString(3, mb.getName());
			pstmt.setNString(4, mb.getEmail());
			if(pstmt.executeUpdate()!=0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public int loginAccess(Member mb) {
		try {
			String sql = "SELECT * FROM kjb.ISMEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(mb.getPw().equals(rs.getNString("pw"))) {
					mb.setName(rs.getNString("name"));
					mb.setEmail(rs.getNString("email"));
					return 1;
				}else {
					return -1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}


	public Member searchMyInfo(String id) {
		Member mb = new Member();
		String sql = "SELECT * FROM kjb.ISMEMBER WHERE ID =?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery(); 
			if(rs.next()) {
				mb.setId(rs.getNString("ID"));
				mb.setPw(rs.getNString("PW"));
				mb.setName(rs.getNString("NAME"));
				mb.setEmail(rs.getNString("EMAIL"));
			}
			return mb;
		} catch (SQLException e) {
			System.out.println("내정보 탐색 실패");
			e.printStackTrace();
		}
		return null;
	}

	public boolean changeEmail(Member mb) {
		//con = getConnection();
		String sql = "UPDATE KJB.ISMEMBER SET EMAIL=? WHERE ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getEmail());
			System.out.println(mb.getEmail());
			pstmt.setNString(2, mb.getId());
			System.out.println(mb.getId());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePw(Member mb) {
		con = getConnection();
		String sql = "UPDATE KJB.ISMEMBER SET PW =? WHERE ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getPw());
			pstmt.setNString(2, mb.getId());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return false;
	}

	public int passFind(Member mb) {
		try {
			con = getConnection();
			String sql = "SELECT * FROM KJB.ISMEMBER WHERE ID=? AND EMAIL=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getEmail());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (mb.getId().equals(rs.getNString("ID"))) {
					if (mb.getEmail().equals(rs.getNString("EMAIL"))) {
						mb.setId(rs.getNString("ID"));
						mb.setEmail(rs.getNString("EMAIL"));
						return 1;
					}
				}
			} else {
				return -1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean passUpdate(String pw, String id) {
		con = getConnection();
		String sql = "UPDATE KJB.ISMEMBER SET PW = ? WHERE id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pw);
			pstmt.setString(2, id);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;

			}

		} catch (

		SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
}
