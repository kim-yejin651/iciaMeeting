package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ForwardInfo;

public class MovingPage {

	HttpServletRequest request;
	HttpServletResponse response;

	public MovingPage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo execute(String cmd) {

		ForwardInfo fi = new ForwardInfo();
		MemberMM mm = new MemberMM(request, response);
		ProductMM pm = new ProductMM(request, response);
		HttpSession session = request.getSession();

		System.out.println(cmd);

		if (cmd.equals("/joinFrm")) {
			if (session.getAttribute("id") == null) {
				fi.setPath("join.jsp");
				fi.setRedirect(true);
			}
		} else if (cmd.equals("/joinAccess")) {
			if (session.getAttribute("id") == null) {
				fi = mm.joinAccess();
			}
		} else if (cmd.equals("/loginFrm")) {
			if (session.getAttribute("id") == null) {
				fi.setPath("login.jsp");
				fi.setRedirect(true);
			}
		} else if (cmd.equals("/loginAccess")) {
			if (session.getAttribute("id") == null) {
				fi = mm.loginAccess();
			}
		} else if (cmd.equals("/myPage")) {
			if (session.getAttribute("id") != null) {
				fi = mm.searchMyInfo();
			}
		} else if (cmd.equals("/logout")) {
			fi = mm.logout();
		} else if (cmd.equals("/main")) {
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		} else if (cmd.equals("/imgDetail")) {
			fi = pm.getDetail();
		} else if (cmd.equals("/emailInfoChange")) {
			fi = mm.emailChange();
		} else if (cmd.equals("/deleteCol")) {
			fi = pm.deleteCol();
		} else if (cmd.equals("/pwChange")) {
			fi.setPath("changePw.jsp");
			fi.setRedirect(false);
		} else if (cmd.equals("/pwInfoChange")) {
			fi = mm.pwChange();
		} else if (cmd.equals("/basketFrm")) {
			fi = pm.basket();
		} else if (cmd.equals("/emailChange")) {
			fi.setPath("changeEmail.jsp");
			fi.setRedirect(false);
		}else if (cmd.equals("/pwInfoChange")) {
			fi = mm.pwChange();
		}else if (cmd.equals("/passFind")) {
	         fi.setPath("passFind.jsp");
	         fi.setRedirect(false);
	      } else if (cmd.equals("/passUpdate")) {         
	         fi = mm.passFind();
	      }else if(cmd.equals("/passwordUpdate")) {
	         fi = mm.passUpdate();
	      }

		if (cmd.equals("/black")) {
			fi = pm.getItemList("b");
		} else if (cmd.equals("/white")) {
			fi = pm.getItemList("w");
		} else if (cmd.equals("/proUpload")) {
			fi = pm.proUpload();
		} else if (cmd.equals("/proUpFrm")) {
			fi.setRedirect(false);
			fi.setPath("/product/proUpForm.jsp");
		} else if (cmd.equals("/basketFrm")) {
			fi = pm.basket();
		} else if (cmd.equals("/nowBuy")) {
			fi = pm.nowBuy();
		} else if (cmd.equals("/buyFrm")) {
			fi.setRedirect(false);
			fi.setPath("/product/buyFrm.jsp");
		} else if (cmd.equals("/search")) {
			fi = pm.getSearchList();
		} else if (cmd.equals("/buyFrm")) {
			fi.setRedirect(false);
			fi.setPath("/product/buyFrm.jsp");
		} else if (cmd.equals("/buy")) {
			fi = pm.buy();
		} else if (cmd.equals("/success")) {
			fi = pm.success();
		}
		
		return fi;
	}

	public String makeJson(String cmd) {
		ProductMM pm = new ProductMM(request, response);
		String json = null;
		if (cmd.equals("/shoppingMall")) {

			json = pm.updateBasketQty();

		}
		return json;
	}
}
