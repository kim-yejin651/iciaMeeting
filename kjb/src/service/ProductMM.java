package service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.Basket;
import bean.ForwardInfo;
import bean.Order;
import bean.Product;
import dao.ProductDao;

public class ProductMM {
	HttpServletRequest request;
	HttpServletResponse response;
	ForwardInfo fi;
	ProductDao pDao;

	public ProductMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo proUpload() {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		int size = 10 * 1024 * 1024;
		File dir = new File(uploadPath);
		System.out.println(uploadPath);
		if (!dir.exists()) {
			dir.mkdir();
		}
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
			String kind = multi.getParameter("p_kind");
			String name = multi.getParameter("p_name");
			int price = Integer.parseInt(multi.getParameter("p_price"));
			int qty = Integer.parseInt(multi.getParameter("p_qty"));
			String contents = multi.getParameter("p_contents");
			String oriFileName = multi.getOriginalFileName("p_file");
			String sysFileName = multi.getFilesystemName("p_file");
			System.out.println(oriFileName);
			System.out.println(sysFileName);
			Product p = new Product();
			p.setP_kind(kind);
			p.setP_id("kjb95");
			p.setP_name(name);
			p.setP_price(price);
			p.setP_qty(qty);
			p.setP_contents(contents);
			p.setP_oriFileName(oriFileName);
			p.setP_sysFileName(sysFileName);
			pDao = new ProductDao();
			boolean result = pDao.proUp(p);
			pDao.close();
			fi = new ForwardInfo();
			if (result) {
				fi.setPath("/product/proUpForm.jsp");
				fi.setRedirect(false);
				System.out.println("�젙蹂댁엯�젰 �꽦怨�");
			} else {
				fi.setPath("/product/proUpForm.jsp");
				fi.setRedirect(false);
				System.out.println("�젙蹂댁엯�젰 �떎�뙣");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fi;
	}

	public ForwardInfo getItemList(String kind) {
		fi = new ForwardInfo();
		pDao = new ProductDao();
		List<Product> pList = null;
		pList = pDao.getItemList(kind);
		pDao.close();
		if (pList != null) {
			request.setAttribute("pList", makeHtml_pList(pList));
		}
		fi.setPath("main.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private String makeHtml_pList(List<Product> pList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pList.size(); i++) {
			Product p = new Product();
			p = pList.get(i);
			sb.append("<div style='width:350px;height:400px; float:left;' class='prductImg'><a href='imgDetail?p_code="
					+ p.getP_code() + "'>");
			sb.append("<img src='upload/" + p.getP_oriFileName() + "' alt=" + p.getP_oriFileName()
					+ "' width='300' height='300'></a>");
			sb.append("<div class='desc'>" + p.getP_name() + "<br>" + p.getP_price() + "</div></div>");

		}
		return sb.toString();
	}

	public ForwardInfo getDetail() {
		fi = new ForwardInfo();
		String p_code = request.getParameter("p_code");

		System.out.println(p_code);
		pDao = new ProductDao();
		Product p = new Product();
		p = pDao.getDetail(p_code);
		if (request.getSession().getAttribute("id") != null) {

			p.setP_id(request.getSession().getAttribute("id").toString());
		}
		pDao.close();
		if (p != null) {
			fi.setPath("product/detail.jsp");
			fi.setRedirect(false);
			request.setAttribute("p", p);
		}
		return fi;
	}

	public ForwardInfo basket() {
		fi = new ForwardInfo();
		Basket bk = new Basket();
		ProductDao pDao = new ProductDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("login.jsp");
			fi.setRedirect(true);
			return fi;
		}
		bk.setB_m_id(request.getSession().getAttribute("id").toString());
		if (request.getParameter("p_qty") != null && request.getParameter("p_name") != null
				&& request.getParameter("p_code") != null) {
			bk.setB_p_code(request.getParameter("p_code"));
			bk.setB_qty(Integer.parseInt(request.getParameter("p_qty")));
			bk.setB_price(bk.getB_qty() * pDao.setPrice(bk));
		}
		if (pDao.basket(bk)) {
			ArrayList<Basket> bList = pDao.selectBasket(request.getSession().getAttribute("id").toString());
			request.setAttribute("bList", makeBListHtml(bList));

		} else {
			if (pDao.updateBasketQty(bk)) {
				ArrayList<Basket> bList = pDao.selectBasket(request.getSession().getAttribute("id").toString());
				request.setAttribute("bList", makeBListHtml(bList));

			} else {
				System.out.println("fjoifijojifoejojoie");
				ArrayList<Basket> bList = pDao.selectBasket(request.getSession().getAttribute("id").toString());
				request.setAttribute("bList", makeBListHtml(bList));

			}
		}

		fi.setPath("product/basket.jsp");
		fi.setRedirect(false);
		return fi;

	}

	public ForwardInfo deleteCol() {
		HttpSession session = request.getSession();
		ForwardInfo fi = new ForwardInfo();
		ProductDao pDao = new ProductDao();
		String id = (String) session.getAttribute("id");
		String b_p_code = request.getParameter("b_p_code");
		System.out.println("id" + id);
		System.out.println("b_p_code=" + b_p_code);
		boolean result = pDao.deleteCol(id, b_p_code);
		ArrayList<Basket> bList = new ArrayList<Basket>();
		bList = pDao.selectBasket(request.getSession().getAttribute("id").toString());
		pDao.close();
		if (result) {
			fi.setPath("product/basket.jsp");
			fi.setRedirect(false);
			request.setAttribute("bList", makeBListHtml(bList));
		} else {
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}

	public ForwardInfo getSearchList() {
		String search = request.getParameter("search");
		System.out.println(search);
		ForwardInfo fi = new ForwardInfo();
		ProductDao pDao = new ProductDao();
		List<Product> pList = null;
		pList = pDao.searchData(search);
		pDao.close();
		if (pList != null) {
			request.setAttribute("pList", makeHtml_pList(pList));
		}
		fi.setPath("search.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private Object makeBListHtml(ArrayList<Basket> bList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr><td>踰덊샇</td><td>援щℓ</td><td>�젣�뭹紐�</td>");
		sb.append("<td>媛�寃�</td><td>�닔�웾</td><td>二쇰Ц愿�由�</td></tr>");
		for (int i = 0; i < bList.size(); i++) {
			sb.append("<tr><td>" + (i + 1) + "</td>");
			sb.append("<td><input type='checkbox' class='checkbox'></td>");
			sb.append("<td><img width='100' highth='100' src ='upload/" + bList.get(i).getP_oriFileName() + "'>"
					+ "<a href='imgDetail?p_code="+bList.get(i).getB_p_code()+"'><p class='p_name'>" + bList.get(i).getP_name() + "</p></a>"
					+ "<input hidden='hidden' type='text' value='" + bList.get(i).getP_name()
					+ "' name='p_name'></td>");
			sb.append("<td><p class='b_p_price'>" + bList.get(i).getB_price() + "</p></td>");
			sb.append("<td><p class='b_qty'>" + bList.get(i).getB_qty() + "</p>"
					+ "<div class='up'>�뼯</div><div class='down'>�뼹</div></td>");
			sb.append("<td><a href='deleteCol?b_p_code=" + bList.get(i).getB_p_code() + "'>�궘�젣</a></td>");
			sb.append("<td hidden='hidden'><p class='p_code' hidden = 'hidden'" + "value='" + bList.get(i).getB_p_code()
					+ "'>" + bList.get(i).getB_p_code() + "</p></td>"
					+ "<td hidden='hidden'><input type='hidden' value='" + bList.get(i).getP_oriFileName()
					+ "'></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	public String updateBasketQty() {
		ProductDao pDao = new ProductDao();
		Basket bk = new Basket();
		bk.setB_m_id(request.getSession().getAttribute("id").toString());
		bk.setB_p_code(request.getParameter("p_code"));
		bk.setB_qty(Integer.parseInt(request.getParameter("b_qty")));
		bk.setP_b_oriqty(pDao.selectProductQty(bk.getB_p_code()));
		if (bk.getP_b_oriqty() < bk.getB_qty()) {
			bk.setB_qty(bk.getP_b_oriqty());
			bk.setB_price(bk.getP_b_oriqty() * pDao.setPrice(bk));
		} else {
			bk.setB_price(bk.getB_qty() * pDao.setPrice(bk));
		}
		if (pDao.updateBasketQty(bk)) {
			String json = new Gson().toJson(bk);
			System.out.println(json);
			pDao.close();
			return json;
		}
		pDao.close();
		return null;
	}

	public ForwardInfo nowBuy() {
		ForwardInfo fi = new ForwardInfo();
		String id = (String) request.getSession().getAttribute("id");
		ProductDao pDao = new ProductDao();
		if (request.getParameterValues("test_p_code") == null) {
			fi.setPath("main");
			fi.setRedirect(true);
			return fi;
		}

		String[] test_p_code = request.getParameterValues("test_p_code");
		String[] test_p_price = request.getParameterValues("test_p_price");
		String[] test_p_qty = request.getParameterValues("test_p_qty");
		String[] test_p_name = request.getParameterValues("test_p_name");
		String[] test_p_orifilename = request.getParameterValues("test_p_oriFileName");

		fi.setRedirect(false);
		fi.setPath("/product/buyFrm.jsp");
		request.setAttribute("Basket",
				makeBasketEndHtml(test_p_code, test_p_orifilename, test_p_price, test_p_qty, test_p_name));
		return fi;
	}

	private Object makeBasketEndHtml(String[] test_p_code, String[] test_p_orifilename, String[] test_p_price,
			String[] test_p_qty, String[] test_p_name) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1'>");
		sb.append("<tr text-align='center'><td>踰덊샇</td><td>�젣�뭹紐�</td><td>�닔�웾</td><td>媛�寃�</td></tr>");
		for (int i = 0; i < test_p_code.length; i++) {
			sb.append("<tr text-align='center'><td>" + (i + 1) + "</td>");
			sb.append("<td>" + test_p_name[i]);
			sb.append("<br><img width='100' height='100' src ='upload/" + test_p_orifilename[i] + "'></td>");
			sb.append("<td>" + test_p_qty[i] + "</td>");
			sb.append("<td>" + test_p_price[i] + "</td>");
		}
		sb.append("</table>");
		for (int i = 0; i < test_p_code.length; i++) {
			sb.append("<input type ='hidden' name ='test_p_code' value='" + test_p_code[i] + "'>");
			sb.append("<input type ='hidden' name ='test_p_name' value='" + test_p_name[i] + "'>");
			sb.append("<input type ='hidden' name ='test_p_oriFileName' value='" + test_p_orifilename[i] + "'>");
			sb.append("<input type ='hidden' id='test' name ='test_p_price' value='" + test_p_price[i] + "'>");
			sb.append("<input type ='hidden' name ='test_p_qty' value='" + test_p_qty[i] + "'>");
		}
		return sb.toString();
	}

	private String makeBasketEndHtml(ArrayList<Basket> bList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1'>");
		sb.append("<tr text-align='center'><td>踰덊샇</td><td>�젣�뭹紐�</td><td>�닔�웾</td><td>媛�寃�</td></tr>");
		for (int i = 0; i < bList.size(); i++) {
			sb.append("<tr text-align='center'><td>" + (i + 1) + "</td>");
			sb.append("<td>" + bList.get(i).getP_name());
			sb.append("<br><img src ='upload/" + bList.get(i).getP_oriFileName() + "'></td>");
			sb.append("<td>" + bList.get(i).getB_qty() + "</td>");
			sb.append("<td>" + bList.get(i).getB_price() + "</td>");
		}
		sb.append("</table>");
		for (int i = 0; i < bList.size(); i++) {
			sb.append("<input type='hidden' name='test_p_name' value='" + bList.get(i).getP_name() + "'>");
			sb.append("<input type='hidden' name='test_p_code' value='" + bList.get(i).getB_p_code() + "'>");
			sb.append("<input type='hidden' name='test_p_price' value='" + bList.get(i).getB_price() + "'>");
			sb.append("<input type='hidden' name='test_p_qty' value='" + bList.get(i).getB_qty() + "'>");
			sb.append(
					"<input type='hidden' name='test_p_oriFileName' value='" + bList.get(i).getP_oriFileName() + "'>");
		}
		return sb.toString();
	}

	public ForwardInfo buy() {
		ForwardInfo fi = new ForwardInfo();
		String id = (String) request.getSession().getAttribute("id");
		ProductDao pDao = new ProductDao();
		Basket bk = new Basket();

		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("login.jsp");
			fi.setRedirect(true);
			return fi;
		}

		bk.setB_m_id(request.getSession().getAttribute("id").toString());
		bk.setB_p_code(request.getParameter("p_code"));
		bk.setB_qty(Integer.parseInt(request.getParameter("p_qty")));
		bk.setB_price(Integer.parseInt(request.getParameter("p_price")) * bk.getB_qty());
		bk.setP_name(request.getParameter("p_name"));
		bk.setP_oriFileName(request.getParameter("p_oriFileName"));

		ArrayList<Basket> bList = new ArrayList<Basket>();
		bList.add(bk);
		int price = bk.getB_price();
		String pCode = bk.getB_p_code();
		int qty = bk.getB_qty();

		if (pDao.inputOrder(id, price) + pDao.inputOrderlist(pCode, qty, price) != 0) {
			fi.setPath("main");
			fi.setRedirect(true);
		}

		fi.setRedirect(false);
		fi.setPath("/product/buyFrm.jsp");
		request.setAttribute("Basket", makeBasketEndHtml(bList));
		pDao.close();
		return fi;
	}

	public ForwardInfo success() {
		ForwardInfo fi = new ForwardInfo();
		String id = (String) request.getSession().getAttribute("id");
		ProductDao pDao = new ProductDao();
		ArrayList<Basket> bList = pDao.selectBasket(id);
		String[] test_p_code = request.getParameterValues("test_p_code");
		String[] test_p_price = request.getParameterValues("test_p_price");
		String[] test_p_qty = request.getParameterValues("test_p_qty");
		String[] test_p_name = request.getParameterValues("test_p_name");
		String[] test_p_orifilename = request.getParameterValues("test_p_oriFileName");
		int total = 0;
		for (int i = 0; i < test_p_price.length; i++) {
			total += Integer.parseInt(test_p_price[i]);
		}

		int result1 = pDao.inputOrder(id, total);
		int result = 0;
		for (int i = 0; i < test_p_code.length; i++) {
			String pCode = test_p_code[i];
			int price = Integer.parseInt(test_p_price[i]);
			int qty = Integer.parseInt(test_p_qty[i]);
			result += pDao.inputOrderlist(pCode, qty, price);
		}

		Order o = pDao.selectOrder(id);
		if ((result + result1) != 0) {
			fi.setRedirect(true);
			fi.setPath("main");
		}
		fi.setRedirect(false);
		fi.setPath("/product/success.jsp");

		request.setAttribute("Basket",
				makeBasketEndHtml(test_p_code, test_p_orifilename, test_p_price, test_p_qty, test_p_name));

		request.setAttribute("Success", makeSuccessHtml(o));
		pDao.clean(id, test_p_code);
		pDao.close();

		return fi;
	}

	private String makeSuccessHtml(Order o) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border='1'><tr>");
		sb.append("<td>二쇰Ц踰덊샇</td><td>二쇰Ц�씪�떆</td><td>珥� 湲덉븸</td></tr>");
		sb.append("<tr><td>" + o.getO_code() + "</td>");
		sb.append("<td>" + o.getO_date() + "</td>");
		sb.append("<td>" + o.getO_total() + "</td>");
		sb.append("</tr></table>");
		return sb.toString();
	}
}
