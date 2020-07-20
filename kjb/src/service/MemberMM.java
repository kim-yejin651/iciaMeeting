package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;
import bean.Member;
import dao.MemberDao;
import dao.ProductDao;

public class MemberMM {

	HttpServletRequest request;
	HttpServletResponse response;

	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public MemberMM() {
	}

	public ForwardInfo joinAccess() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		MemberDao mDao = new MemberDao();
		System.out.println(request.getParameter("id"));
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		mb.setName(request.getParameter("name"));
		mb.setEmail(request.getParameter("email"));
		if (mDao.joinAccess(mb)) {
			fi.setPath("main");
			fi.setRedirect(true);
		} else {
			fi.setPath("joinFrm");
			fi.setRedirect(false);
			request.setAttribute("wrongJoin", "회원가입 실패");
		}
		return fi;
	}

	public ForwardInfo loginAccess() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		MemberDao mDao = new MemberDao();
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		int result = mDao.loginAccess(mb);
		if (result == 0) {
			fi.setPath("login.jsp");
			fi.setRedirect(false);
			request.setAttribute("wrongLogin", "아이디가 존재하지 않습니다.");
		} else if (result == -1) {
			fi.setPath("login.jsp");
			fi.setRedirect(false);
			request.setAttribute("wrongLogin", "비밀번호가 틀렸습니다.");
		} else if (result == 1) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			request.getSession().setAttribute("id", request.getParameter("id"));
		}
		return fi;
	}

	public ForwardInfo emailChange() {
		ForwardInfo fi=new ForwardInfo();
		HttpSession session = request.getSession();
		String id=(String) session.getAttribute("id");
		System.out.println("id="+id);
		String email=request.getParameter("email");
		Member mb=new Member();
		mb.setId(id);
		mb.setEmail(email);
		MemberDao mDao=new MemberDao();
		boolean result=mDao.changeEmail(mb);
		System.out.println(result);
		mDao.close();
		if(result){
			fi.setPath("myPage");
			fi.setRedirect(false);
		}else {
			fi.setPath("myPage");
			fi.setRedirect(false);
		}
		
		return fi;
	}
	
	public ForwardInfo searchMyInfo() {
		ForwardInfo fi = new ForwardInfo();
		HttpSession session = request.getSession();
		Member mb;
		String id = (String) session.getAttribute("id");
		System.out.println(id);
		MemberDao mDao = new MemberDao(); // mDao
		// 인스턴스선언
		mb = mDao.searchMyInfo(id);
		String myInfo = makeMyInfoHtml(mb);
		if (mb != null) {
			request.setAttribute("myInfo", myInfo);
			fi.setPath("myPage.jsp");
			fi.setRedirect(false);
		}
		return fi;
	}

	public String makeMyInfoHtml(Member mb) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table border = '1'>");
		sb.append("<tr><td>내 ID</td><td>" + mb.getId() + "</td></tr>");
		sb.append("<tr><td>내 비밀번호</td><td>" + mb.getPw() + "</td></tr>");
		sb.append("<tr><td>내 이름</td><td>" + mb.getName() + "</td></tr>");
		sb.append("<tr><td>내 이메일</td><td>" + mb.getEmail() + "</td></tr>"); // get이메일 바꿔야함
		sb.append("</table>");

		return sb.toString();
	}

	public ForwardInfo logout() {
		HttpSession session = request.getSession();
		session.invalidate();
		ForwardInfo fi = new ForwardInfo();
		fi.setPath("index.jsp");
		fi.setRedirect(true);
		return fi;
	}

	public ForwardInfo passFind() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		MemberDao mDao = new MemberDao();
		mb.setId(request.getParameter("id"));
		mb.setEmail(request.getParameter("email"));
		int result = mDao.passFind(mb);

		if (result == 1) {
			request.setAttribute("wrongpass", "zzz");
			fi.setPath("passUpdate.jsp");
			fi.setRedirect(false);
			request.setAttribute("id", request.getParameter("id"));
			return fi;
		} else {
			request.setAttribute("wrongpass", "정보일치하지않음");
		}

		return fi;
	}
	
	public ForwardInfo passUpdate() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		MemberDao mDao = new MemberDao();
		String pw = request.getParameter("pw1");
		String pw2 = request.getParameter("pw2");
		if (pw.equals(pw2)) {
			if (mDao.passUpdate(pw, request.getParameter("id"))) {
				fi.setPath("index.jsp");
				fi.setRedirect(false);
			} else {
				fi.setPath("index.jsp");
				fi.setRedirect(false);
			}
		} else {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
		}
		return fi;

	}
	
	public ForwardInfo pwChange() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String pw = request.getParameter("pw");
		MemberDao mDao = new MemberDao();
		mb.setId(id);
		mb.setPw(pw);
		boolean result = mDao.changePw(mb);
		if (result) {
			fi.setPath("myPage");
			fi.setRedirect(false);
		} else {
			fi.setPath("myPage");
			fi.setRedirect(false);
		}
		return fi;
	}

}
