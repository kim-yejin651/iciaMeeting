package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;

public class MovingPage {

	HttpServletRequest request;
	HttpServletResponse response;
	
	public MovingPage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo execute(String cmd) {
		System.out.println(cmd);
		ForwardInfo fi = new ForwardInfo();
		MemberMM mm = new MemberMM(request,response);
		if(cmd.equals("/joinFrm")) {
			fi.setPath("join.jsp");
			fi.setRedirect(true);
		}else if(cmd.equals("/joinAccess")) {
			fi = mm.joinAccess();
		}else if(cmd.equals("/loginFrm")) {
			fi.setPath("login.jsp");
			fi.setRedirect(true);
		}else if(cmd.equals("/loginAccess")) {
			fi = mm.loginAccess();
		}else if(cmd.equals("/main")) {
			fi = mm.main();
		}else if(cmd.equals("/logout")) {
			fi = mm.logout();
		}else if(cmd.equals("/imgDetail")) {
			fi = mm.imgDetail();
		}else if(cmd.equals("/cart")) {
			fi = mm.cart();
		}else if(cmd.equals("/delete")) {
			fi = mm.delete();
		}else if(cmd.equals("/match")) {
			fi = mm.match();
		}else if(cmd.equals("/MyInfo")) {
			fi =mm.MyInfo();
		}else if(cmd.equals("/likeChoice")) {
			fi = mm.LikeChoice();
		}else if(cmd.equals("/matchDetail")) {
			fi=mm.matchDetail();
		}else if(cmd.equals("/chat")) {
			fi=mm.chat();
		}else if(cmd.equals("/xxxy")) {
			fi=mm.xxxy();
		}else if(cmd.equals("/eight")) {
			fi=mm.eight();
		}else if(cmd.equals("/mydelete")) {
		    fi=mm.mydelete();
	}
		return fi;
	}

}
