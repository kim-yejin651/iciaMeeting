package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import service.MovingPage;

@WebServlet({"/joinFrm", "/joinAccess", "/loginFrm", "/loginAccess", 
	"/main", "/myPage", "/logout", "/home","/emailChange",
	"/emailInfoChange","/pwChange","/pwInfoChange","/passwordUpdate",
	"/passUpdate","/passFind"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getServletPath();
		ForwardInfo fi = new ForwardInfo();
		MovingPage mp = new MovingPage(request, response);
		fi = mp.execute(cmd);
		
		if(fi.isRedirect()) {
			response.sendRedirect(fi.getPath());
		}else {
			RequestDispatcher dis = request.getRequestDispatcher(fi.getPath());
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
