package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//jdbc common 
public class JdbcUtil {

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle Driver Loading Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} // catch end
	}// static end

	public static Connection getConnection() { // static�� ����
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.105:1521:xe", "kyj", "0000");
//			con.setAutoCommit(false);// ���� Ŀ��.
			System.out.println("Connect Success");
			// select, insert
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connect Failed");
		} // catch end
		return con;
	}// connect end

	public static void close(ResultSet rs, PreparedStatement pstmt, Connection con) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println("Close Failed");
			e.printStackTrace();
		} // catch end
	}

	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("Commit Success");
		} catch (SQLException e) {
			System.out.println("Commit Failed");
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("Rollback Success");
		} catch (SQLException e) {
			System.out.println("Rollback Failed");
			e.printStackTrace();
		}
	}
}

