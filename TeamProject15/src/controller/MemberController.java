package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;

@WebServlet({"/joinFrm","/joinAccess","/loginFrm","/loginAccess","/main","/logout","/imgDetail","/match","/chat","/cart","/delete","/MyInfo","/likeChoice","/matchDetail","/xxxy","/eight","/mydelete"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cmd = request.getServletPath();
		ForwardInfo fi = new ForwardInfo();
		MovingPage mp = new MovingPage(request,response);
		fi = mp.execute(cmd);
		System.out.println(fi.getPath());
		if(fi!=null) {
			if(fi.isRedirect()) {
				response.sendRedirect(fi.getPath());
			}else {
				request.getRequestDispatcher(fi.getPath()).forward(request, response);
			}
		}else {
			System.out.println("fjmslfjojfsedofijoe");
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
