package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import bean.Basket;
import bean.Order;
import bean.Product;

public class ProductDao {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	public ProductDao() {
		con = JdbcUtil.getConnection();
	}

	public void close() {
		JdbcUtil.close(rs, pstmt, con);
	}

	public boolean proUp(Product p) {
		String sql = "INSERT INTO KJB.ISPRODUCT VALUES (?||LPAD(kjb.P_SEQ.NEXTVAL,4,0),?,?,?,?,?,default,?,?)";
		int result;
		try {
			System.out.println(p.getP_id());
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, p.getP_kind());
			pstmt.setNString(2, p.getP_id());
			pstmt.setNString(3, p.getP_name());
			pstmt.setInt(4, p.getP_price());
			pstmt.setInt(5, p.getP_qty());
			pstmt.setNString(6, p.getP_contents());
			pstmt.setNString(7, p.getP_oriFileName());
			pstmt.setNString(8, p.getP_sysFileName());
			result = pstmt.executeUpdate();
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

	public List<Product> getItemList(String kind) {
		String sql = "SELECT * FROM KJB.ISPRODUCT WHERE P_CODE LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, "%" + kind + "%");
			System.out.println(kind);
			rs = pstmt.executeQuery();
			List<Product> pList = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setP_code(rs.getNString("P_CODE"));
				p.setP_name(rs.getNString("P_NAME"));
				p.setP_price(rs.getInt("P_PRICE"));
				p.setP_oriFileName(rs.getNString("P_ORIFILENAME"));
				pList.add(p);
			}
			return pList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product getDetail(String code) {
		String sql = "SELECT * FROM KJB.ISPRODUCT WHERE P_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Product p = new Product();
				p.setP_code(rs.getNString("p_code"));
				p.setP_name(rs.getNString("P_NAME"));
				p.setP_price(rs.getInt("P_PRICE"));
				p.setP_contents(rs.getNString("P_CONTENTS"));
				p.setP_oriFileName(rs.getNString("p_orifilename"));
				System.out.println(p.getP_oriFileName());
				p.setP_sysFileName(rs.getNString("p_sysfilename"));
				return p;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean basket(Basket bk) {
		String sql = "INSERT INTO KJB.ISBASKET VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bk.getB_m_id());
			pstmt.setNString(2, bk.getB_p_code());
			pstmt.setInt(3, bk.getB_qty());
			pstmt.setInt(4, bk.getB_price());
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("4458844");
		}
		return false;
	}

	public boolean updateBasketQty(Basket bk) {
		String sql = "UPDATE KJB.ISBASKET SET B_QTY=?, B_PRICE=? WHERE B_M_ID = ? AND B_P_CODE = ?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bk.getB_qty());
			pstmt.setInt(2, bk.getB_price());
			pstmt.setNString(3, bk.getB_m_id());
			pstmt.setNString(4, bk.getB_p_code());
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Basket> selectBasket(String id) {
		try {
			ArrayList<Basket> bList = new ArrayList<Basket>();
			String sql = "SELECT * FROM kjb.isproduct ip INNER JOIN KJB.isbasket ib "
					+ "ON ip.p_code = ib.b_p_code WHERE ib.b_m_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			System.out.println(id);
			while (rs.next()) {
				Basket bk = new Basket();
				bk.setB_m_id(id);
				bk.setB_p_code(rs.getNString("b_p_code"));
				bk.setB_qty(rs.getInt("b_qty"));
				bk.setB_price(rs.getInt("b_price"));
				bk.setP_name(rs.getNString("p_name"));
				bk.setP_oriFileName(rs.getNString("P_ORIFILENAME"));
				bList.add(bk);
			}
			return bList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateBasket(Basket bk) {
		String sql = "UPDATE KJB.ISBASKET SET B_QTY=?, B_PRICE=? WHERE B_M_ID = ? AND B_P_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bk.getB_qty());
			pstmt.setInt(2, bk.getB_price());
			pstmt.setNString(3, bk.getB_m_id());
			pstmt.setNString(4, bk.getB_p_code());
			if (pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public int setPrice(Basket bk) {
		String sql = "SELECT * FROM kjb.isproduct ip INNER JOIN KJB.isbasket ib ON ip.p_code = ib.b_p_code WHERE P_CODE=?";
		try {
			con = JdbcUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bk.getB_p_code());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bk.setP_name(rs.getNString("p_name"));
				bk.setP_oriFileName(rs.getNString("p_oriFileName"));
				return rs.getInt("p_price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
//
//	public boolean orderlist(Basket bk) {
//		String sql = "INSERT INTO KJB.ISORDERLIST VALUES (LPAD(kjb.O_SEQ.NEXTVAL,6,0), ?, ?, ?)";
//		try {
//			pstmt = con.prepareStatement(sql);
//			pstmt.setNString(1, bk.getB_p_code());
//			pstmt.setInt(2, bk.getB_qty());
//			pstmt.setInt(3, bk.getB_price());
//			int result = pstmt.executeUpdate();
//			if (result != 0) {
//				return true;
//			}
//		} catch (SQLException e) {
//			System.out.println("오더리스트 추가실패");
//			e.printStackTrace();
//		}
//
//		return false;
//	}

	public boolean deleteCol(String id, String b_p_code) {
		String sql = "DELETE FROM KJB.ISBASKET WHERE B_M_ID=? AND B_P_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, b_p_code);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int setQty(Basket bk) {
		String sql = "SELECT * FROM KJB.ISBASKET WHERE B_M_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, bk.getB_m_id());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				bk.setB_p_code(rs.getNString("b_p_code"));
				bk.setB_price(rs.getInt("b_price"));
				bk.setB_qty(rs.getInt("b_qty"));
				return rs.getInt("b_qty");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public List<Product> searchData(String search) {
		String sql = "SELECT * FROM KJB.ISPRODUCT WHERE P_NAME LIKE ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, "%" + search + "%");
			rs = pstmt.executeQuery();
			List<Product> pList = new ArrayList<>();
			while (rs.next()) {
				Product p = new Product();
				p.setP_code(rs.getNString("P_CODE"));
				p.setP_name(rs.getNString("P_NAME"));
				p.setP_price(rs.getInt("P_PRICE"));
				p.setP_oriFileName(rs.getNString("P_ORIFILENAME"));
				pList.add(p);
			}
			return pList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Basket selectBasketIdPCode(String id, String pCode) {
		String sql = "SELECT * FROM KJB.ISBASKET WHERE B_M_ID = ? AND B_P_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setNString(2, pCode);
			rs = pstmt.executeQuery();
			Basket bk = new Basket();
			if (rs.next()) {
				bk.setB_m_id(id);
				bk.setB_p_code(rs.getNString(pCode));
				bk.setB_qty(rs.getInt("b_qty"));
				bk.setB_price(rs.getInt("b_price"));
				bk.setP_name(rs.getNString("p_name"));

			}
			return bk;
		} catch (SQLException e) {
			System.out.println("장바구니정보검색실패");
			e.printStackTrace();
		}
		return null;
	}

	public int inputOrder(String id, int price) {
		String sql = "INSERT INTO KJB.ISORDER VALUES (LPAD(KJB.O_SEQ.NEXTVAL, 6, 0), ?, DEFAULT, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			pstmt.setInt(2, price);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return 0;
			}
		} catch (SQLException e) {
			System.out.println("인풋오더");
			e.printStackTrace();
		}
		return 1;

	}

	public int inputOrderlist(String pCode, int qty, int price) {
		String sql = "INSERT INTO KJB.ISORDERLIST VALUES ((LPAD(KJB.O_SEQ.CURRVAL, 6, 0)), ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, pCode);
			pstmt.setInt(2, qty);
			pstmt.setInt(3, price);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				return 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public boolean clean(String id, String[] test_p_code) {
		String sql = "DELETE FROM KJB.ISBASKET WHERE B_M_ID = ? AND B_P_CODE = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			for (int i = 0; i < test_p_code.length; i++) {
				pstmt.setNString(2, test_p_code[i]);
				int result = pstmt.executeUpdate();
				if (result == 0) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public Order selectOrder(String id) {
		String sql = "SELECT * FROM KJB.ISORDER WHERE O_M_ID = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setNString(1, id);
			rs = pstmt.executeQuery();
			List<Order> oList = new ArrayList<>();
			while(rs.next()) {
				Order o = new Order();
				o.setO_code(rs.getNString("O_CODE"));
				o.setO_date(rs.getDate("O_DATE"));
				o.setO_m_id(id);
				o.setO_total(rs.getInt("O_TOTAL"));
				oList.add(o);
				System.out.print(Integer.parseInt(o.getO_code())+"//");
			}
			int[] big = new int[oList.size()];
			System.out.println("size = "+oList.size());
			for (int i = 0; i < oList.size(); i++) {
				big[i] = Integer.parseInt(oList.get(i).getO_code());
				System.out.print(big[i]+"//");
			}
			Arrays.sort(big);
			int num = big[big.length - 1];
			for (int j = 0; j < oList.size(); j++) {
				if (Integer.parseInt(oList.get(j).getO_code()) == num) {
					return oList.get(j);
				}
			}

		} catch (SQLException e) {
			System.out.println("오더리스트실패");
			e.printStackTrace();
		}
		return null;
	}

		public int selectProductQty(String pCode) {
	         String sql = "SELECT * FROM KJB.ISPRODUCT WHERE P_CODE = ?";
	         try {
	            pstmt = con.prepareStatement(sql);
	            pstmt.setNString(1, pCode);
	            rs=pstmt.executeQuery();
	            if(rs.next()) {
	               return rs.getInt("p_qty");
	            }
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	         return 0;
	      }

		public boolean deleteProduct(String pCode, int qty, int oriqty) {
		      String sql = "UPDATE KJB.ISPRODUCT SET P_QTY = ? WHERE P_CODE = ?";
		      try {
		         pstmt = con.prepareStatement(sql);
		         pstmt.setInt(1, oriqty-qty);
		         pstmt.setNString(2, pCode);
		         if(pstmt.executeUpdate()!=0) {
		            return true;
		         }
		      } catch (SQLException e) {
		         // TODO Auto-generated catch block
		         e.printStackTrace();
		      }
		      return false;
		   }

}
