package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.JdbcUtil.*;

import bean.Member;
import bean.Profile;

public class MemberDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public MemberDao() {
		con = getConnection();
	}

	public void daoClose() {
		close(rs, pstmt, con);
	}

	public boolean joinAccess(Member mb) {
		try {
			String sql = "INSERT INTO MEMBER VALUES(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			System.out.println(mb.getId());
			pstmt.setNString(2, mb.getPw());
			pstmt.setNString(3, mb.getNickname());
			pstmt.setInt(4, mb.getAge());
			pstmt.setNString(5, mb.getGender());
			pstmt.setNString(6, mb.getOrifilename());
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean loginAccess(Member mb) {
		try {
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (mb.getPw().equals(rs.getNString("pw"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Member main(String id) {
		try {
			Member mb = new Member();
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mb.setGender(rs.getNString("gender"));
				return mb;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Profile> selectAllWomen() {
		try {
			ArrayList<Profile> wList = new ArrayList<Profile>();
			String sql = "SELECT * FROM MEMBER WHERE GENDER = '여'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Profile women = new Profile();
				women.setId(rs.getNString("id"));
				women.setNickname(rs.getNString("nickname"));
				women.setAge(rs.getInt("age"));
				women.setOrifilename(rs.getNString("orifile"));
				wList.add(women);
			}
			return wList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Profile> selectAllMan() {
		try {
			ArrayList<Profile> mList = new ArrayList<Profile>();
			String sql = "SELECT * FROM MEMBER WHERE GENDER = '남'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Profile man = new Profile();
				man.setId(rs.getNString("id"));
				man.setNickname(rs.getNString("nickname"));
				man.setAge(rs.getInt("age"));
				man.setOrifilename(rs.getNString("orifile"));
				mList.add(man);
			}
			return mList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean proFile(Profile pro) {
		try {
			String sql = "SELECT * FROM MEMBER WHERE ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, pro.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pro.setNickname(rs.getNString("nickname"));
				pro.setAge(rs.getInt("age"));
				pro.setOrifilename(rs.getNString("orifile"));
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean cart(Member mb, Profile pro) {
		try {
			String sql = "INSERT INTO CART VALUES(?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			pstmt.setNString(2, pro.getId());
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Profile> CartProList(Member mb) {
		try {
			ArrayList<Profile> pList = new ArrayList<Profile>();
			String sql = "SELECT * FROM CART C JOIN MEMBER MB ON C.YOURID = MB.ID WHERE C.MYID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Profile pro = new Profile();
				pro.setId(rs.getNString("YOURID"));
				pro.setAge(rs.getInt("age"));
				pro.setNickname(rs.getNString("nickname"));
				pro.setOrifilename(rs.getNString("orifile"));
				pList.add(pro);
			}
			return pList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void cartUpdate(Member mb, Profile pro) {
		try {
			String sql = "UPDATE CART SET MYID=?,YOURID=? WHERE MYID=? AND YOURID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, mb.getId());
			pstmt.setNString(2, pro.getId());
			pstmt.setNString(3, mb.getId());
			pstmt.setNString(4, pro.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean delete(String myId, String yourId) {
		System.out.println("dao delete call");
		System.out.println(yourId+ yourId.length());
		
		try {
			String sql = "DELETE FROM CART WHERE MYID=? AND YOURID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, myId);
			pstmt.setNString(2, yourId);
			System.out.println(yourId);
			if (pstmt.executeUpdate() != 0) {
				System.out.println("11111111111111111111111111111");
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Profile> matchFirst(String myId) {
		try {
			ArrayList<Profile> pList = new ArrayList<Profile>();
			String sql = "SELECT * FROM CART C JOIN MEMBER MB ON C.YOURID = MB.ID WHERE C.MYID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, myId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Profile pro = new Profile();
				pro.setId(rs.getNString("yourID"));
				pro.setNickname(rs.getNString("nickname"));
				pList.add(pro);
			}
			return pList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Profile matchSecond(String yourId, String myId) {
		try {
			Profile pro = new Profile();
			String sql = "SELECT * FROM CART WHERE MYID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, yourId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getNString("yourid").equals(myId))
					;
				pro.setId(yourId);
				if (proFile(pro)) {
					System.out.println("efijrfljfdpaqwjdoqa");
					return pro;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Member MyInfo(String id) {
		String sql = "SELECT * FROM MEMBER WHERE ID=?";
		Member mb = new Member();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mb.setId(rs.getNString("ID"));
				mb.setNickname(rs.getNString("NICKNAME"));
				mb.setAge(rs.getInt("AGE"));
				mb.setGender(rs.getNString("GENDER"));
				mb.setOrifilename(rs.getNString("ORIFILE"));
				System.out.println("정보 확인");
				return mb;
			}
			
		} catch (SQLException e) {
			System.out.println("Info 예외");
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Profile> LikeChoice(String id) {
		String sql = "SELECT * FROM CART WHERE YOURID=?";
		ArrayList<Profile> pList = new ArrayList<Profile>();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Profile pro =new Profile();
				pro.setId(rs.getNString("myId"));
				pList.add(pro);
			}return pList;
			
		} catch (SQLException e) {
			System.out.println("Info 예외");
			e.printStackTrace();
		}
		return null;
	}

	public boolean mydelete(String id) {
		String sql="DELETE FROM MEMBER WHERE ID=? ";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("회원탈퇴 예외처리");
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean myyouriddelete(String id) {
		String sql="DELETE FROM CART WHERE YOURID=? OR MYID=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, id);
			int result=pstmt.executeUpdate();
			if(result!=0) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println("회원탈퇴 예외처리");
			e.printStackTrace();
		}
		return false;
	}
}
