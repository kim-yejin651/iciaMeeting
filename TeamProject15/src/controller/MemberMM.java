package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import bean.ForwardInfo;
import bean.Member;
import bean.Profile;
import dao.MemberDao;

public class MemberMM {

	HttpServletResponse response;
	HttpServletRequest request;

	public MemberMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public ForwardInfo joinAccess() {
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload");
		ForwardInfo fi = new ForwardInfo();
		System.out.println(uploadPath);
		File dir = new File(uploadPath);
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, 10 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());
			mb.setId(multi.getParameter("id"));
			mb.setPw(multi.getParameter("pw"));
			mb.setNickname(multi.getParameter("nickname"));
			System.out.println(multi.getParameter("nickname"));
			mb.setGender(multi.getParameter("gender"));
			mb.setAge(Integer.parseInt(multi.getParameter("age")));
			mb.setOrifilename(multi.getOriginalFileName("orifilename"));
			if (mDao.joinAccess(mb)) {
				fi.setPath("index.jsp");
				fi.setRedirect(true);
			} else {
				request.setAttribute("wrongJoin", "회원 가입에 실패했습니다. <br> 다시 시도 해 주세요!");
				fi.setPath("join.jsp");
				fi.setRedirect(false);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mDao.daoClose();
		return fi;
	}

	public ForwardInfo loginAccess() {
		ForwardInfo fi = new ForwardInfo();
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		mb.setId(request.getParameter("id"));
		mb.setPw(request.getParameter("pw"));
		if (mDao.loginAccess(mb)) {
			fi.setPath("index.jsp");
			fi.setRedirect(false);
			request.getSession().setAttribute("id", mb.getId());
		} else {
			request.setAttribute("wrongLogin", "로그인에 실패했습니다. <br> 다시 시도 해 주세요!");
			fi.setPath("login.jsp");
			fi.setRedirect(false);
		}
		mDao.daoClose();
		return fi;
	}

	public ForwardInfo main() {
		ForwardInfo fi = new ForwardInfo();
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		if (request.getSession().getAttribute("id") == null) {
			return null;
		}
		mb = mDao.main(request.getSession().getAttribute("id").toString());
		if (mb.getGender().equals("남")) {
			ArrayList<Profile> wList = mDao.selectAllWomen();
			request.setAttribute("selectAllProFile", makeShowAllProFile(wList));
		} else if (mb.getGender().equals("여")) {
			ArrayList<Profile> mList = mDao.selectAllMan();
			request.setAttribute("selectAllProFile", makeShowAllProFile(mList));
		}
		fi.setPath("main.jsp");
		fi.setRedirect(false);
		mDao.daoClose();
		return fi;
	}

	private String makeShowAllProFile(ArrayList<Profile> wList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		for (int i = 0; i < wList.size(); i++) {
			sb.append("<tr><td><div class='profile'>");
			sb.append("<img style='width:200px; height:170px;' src='upload/" + wList.get(i).getOrifilename() + "'><br>");
			sb.append(wList.get(i).getNickname() + "님<br>");
			sb.append("<input type='hidden' name='yourId' value='" + wList.get(i).getId() + "'>");
			sb.append("</div></td></tr>");
		}
		sb.append("</table>");
		return sb.toString();
	}

	public ForwardInfo logout() {
		request.getSession().invalidate();
		ForwardInfo fi = new ForwardInfo();
		fi.setPath("index.jsp");
		fi.setRedirect(true);
		return fi;
	}

	public ForwardInfo imgDetail() {
		ForwardInfo fi = new ForwardInfo();
		MemberDao mDao = new MemberDao();
		Profile pro = new Profile();
		pro.setId(request.getParameter("yourId"));
		if (mDao.proFile(pro)) {
			request.setAttribute("ProFile", pro);
			fi.setPath("imgDetail.jsp");
			fi.setRedirect(false);
		} else {
			request.setAttribute("wrongProFile", "탈퇴한 회원입니다.");
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		}
		mDao.daoClose();
		return fi;
	}

	public ForwardInfo cart() {
		ForwardInfo fi = new ForwardInfo();
		Member mb = new Member();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		mb.setId(request.getSession().getAttribute("id").toString());
		if (request.getParameter("yourId") != null) {
			pro.setId(request.getParameter("yourId"));
			if (mDao.cart(mb, pro)) {

			} else {
				mDao.cartUpdate(mb, pro);
			}
		}
		ArrayList<Profile> pList = mDao.CartProList(mb);
		request.setAttribute("cartProFile", makeCart(pList));
		fi.setPath("cart.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private String makeCart(ArrayList<Profile> pList) {
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='profile'><table>");
		for (int i = 0; i < pList.size(); i++) {
			sb.append("<tr><td><img src='upload/" + pList.get(i).getOrifilename() + "'width='200' height='200'></td>");
			sb.append("<td>" + pList.get(i).getNickname() + "님<br>");
			sb.append("<td><input type='submit' value='삭제' onclick='javascript:form.action=\"delete?deleteID="
					+ pList.get(i).getId() + "\"'>");
		}
		sb.append("</table></div>");
		return sb.toString();
	}

	public ForwardInfo delete() {
		ForwardInfo fi = new ForwardInfo();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		MemberDao mDao = new MemberDao();
		if (mDao.delete(request.getSession().getAttribute("id").toString(), request.getParameter("deleteID"))) {
			fi.setPath("cart");
			fi.setRedirect(false);
		} else {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
		}
		return fi;
	}

	public ForwardInfo match() {
		ArrayList<Profile> pList = new ArrayList<Profile>();
		ArrayList<Profile> matchList = new ArrayList<Profile>();
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		pList = mDao.matchFirst(request.getSession().getAttribute("id").toString());
		for (int i = 0; i < pList.size(); i++) {
			pro = mDao.matchSecond(pList.get(i).getId(), request.getSession().getAttribute("id").toString());
			if (pro != null) {
				matchList.add(pro);
			}
		}
		request.setAttribute("mList", makeMatch(matchList));

		for (int i = 0; i < matchList.size(); i++) {
			matchList.get(i).getNickname();
		}

		fi.setPath("match.jsp");
		fi.setRedirect(false);

		return fi;
	}

	private String makeMatch(ArrayList<Profile> matchList) {
		HttpSession session = request.getSession();
		StringBuilder sb = new StringBuilder();
		sb.append("<div class='profile'><table>");
		for (int i = 0; i < matchList.size(); i++) {
			sb.append("<tr><td><img src='upload/" + matchList.get(i).getOrifilename() + "'width='200' height='200'></td>");
			sb.append("<td>" + matchList.get(i).getNickname() + "님<br>");
			sb.append("<td><a href='matchDetail?yourId="+matchList.get(i).getId() + "'>상대방 정보 확인</a></td></tr>");
		}
		sb.append("</table></div>");
		return sb.toString();
	}

	public ForwardInfo MyInfo() {
		String id = request.getSession().getAttribute("id").toString();
		ForwardInfo fi = new ForwardInfo();
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		mb = mDao.MyInfo(id);

		if (mb != null) {
			request.setAttribute("MyInfo", makeMyInfo(mb));
			fi.setPath("MyInfo.jsp");
			fi.setRedirect(false);
		} else {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
		}

		return fi;
	}

	private String makeMyInfo(Member mb) {
		StringBuilder sb = new StringBuilder();
		sb.append("<table><tr><th>프로필 사진</th>");
		sb.append("<th>닉네임</th>");
		sb.append("<th>나이</th>");
		sb.append("<th>아이디</th></tr>");
		sb.append("<tr><td><img src='upload/" + mb.getOrifilename() + "'width='200' height='200'></td>");
		sb.append("<td>" + mb.getNickname() + "</td>");
		sb.append("<td>" + mb.getAge() + "</td>");
		sb.append("<td>" + mb.getId() + "</td></tr></table>");
		return sb.toString();
	}

	public ForwardInfo LikeChoice() {
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		ArrayList<Profile> pList = mDao.LikeChoice(request.getSession().getAttribute("id").toString());
		for(int i = 0;i<pList.size();i++) {
			mDao.proFile(pList.get(i));
		}
		fi.setPath("likeChoice.jsp");
		fi.setRedirect(false);
		request.setAttribute("likechoice", makeLikeChoice(pList));
		return fi;
	}

	private String makeLikeChoice(ArrayList<Profile> pList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pList.size(); i++) {
					sb.append("<div class='profile'><form ><img src='upload/" 
		                       + pList.get(i).getOrifilename() 
		                       + "'width='200' height='200'><br>"
					           + pList.get(i).getNickname() + "<br>" + pList.get(i).getAge() + "<br><input type='hidden' name='yourId' value='"+pList.get(i).getId()+"'><input id='button1' onclick='javascript:form.action=\"imgDetail\"' type='submit' value='상대방 정보 보기'></form></div>");
		}
		return sb.toString();
	}

	public ForwardInfo matchDetail() {
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		pro.setId(request.getParameter("yourId"));
		mDao.proFile(pro);
		fi.setPath("matchDetail.jsp");
		fi.setRedirect(false);
		request.setAttribute("yourNickName", pro);
		return fi;
	}

	public ForwardInfo chat() {
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		Member mb = new Member();
		System.out.println(request.getParameter("yourfile"));
		mb = mDao.MyInfo(request.getSession().getAttribute("id").toString());
		request.setAttribute("yourfile", request.getParameter("yourfile"));
		request.setAttribute("myNickName", mb.getNickname());
		request.setAttribute("file", mb.getOrifilename());
		request.setAttribute("yourNickName", request.getParameter("nickname"));
		fi.setPath("chatting.jsp");
		fi.setRedirect(false);
		return fi;
	}

	public ForwardInfo xxxy() {
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		ArrayList<Profile> pList = new ArrayList<Profile>();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		mb = mDao.main(request.getSession().getAttribute("id").toString());
		if(mb.getGender().equals("남")) {
			pro.setGender("여");
			pList = mDao.selectAllWomen();
		}else {
			pro.setGender("남");
			pList = mDao.selectAllMan();
		}
		request.setAttribute("xxxy", xxxyCheck(pList));
		fi.setPath("xxxy.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private String xxxyCheck(ArrayList<Profile> pList) {
		StringBuilder sb = new StringBuilder();
		Collections.shuffle(pList);
		sb.append("<p id='pT'>"+pList.size()+"</p>");
		for(int i = 0; i < pList.size();i++) {
			sb.append("<div id='xxxy"+i+"' style='float:left; display:none;' class='xxxy'><img style='width:300px;height:300px;' src='upload/"+pList.get(i).getOrifilename()+"'><br>");
			sb.append("<p>"+pList.get(i).getNickname()+"</p>");
			sb.append("<input type='hidden' name='orifilename' value='"+pList.get(i).getOrifilename()+"'></div>");
		}
		return sb.toString();
	}

	public ForwardInfo eight() {
		ForwardInfo fi = new ForwardInfo();
		Profile pro = new Profile();
		MemberDao mDao = new MemberDao();
		Member mb = new Member();
		ArrayList<Profile> pList = new ArrayList<Profile>();
		if (request.getSession().getAttribute("id") == null) {
			fi.setPath("index.jsp");
			fi.setRedirect(true);
			return fi;
		}
		String[] nickname = request.getParameterValues("nickname");
		String[] orifilename = request.getParameterValues("orifilename");
		request.setAttribute("xxxy", makeXxxy(nickname,orifilename));
		fi.setPath("xxxy.jsp");
		fi.setRedirect(false);
		return fi;
	}

	private String makeXxxy(String[] nickname, String[] orifilename) {
		StringBuilder sb = new StringBuilder();
		sb.append("<p id='pT'>"+(orifilename.length)+"</p>");
		for(int i = 0; i < nickname.length;i++) {
			sb.append("<div id='xxxy"+i+"' style='float:left; display:none;' class='xxxy'><img style='width:300px;height:300px;' src='upload/"+orifilename[i]+"'><br>");
			sb.append("<p>"+nickname[i]+"</p>");
			sb.append("<input type='hidden' name='orifilename' value='"+orifilename[i]+"'></div>");
		}
		return sb.toString();
	}

	public ForwardInfo mydelete() {
		String id = request.getSession().getAttribute("id").toString();
		ForwardInfo fi = new ForwardInfo();
		MemberDao mDao = new MemberDao();
		
		boolean result1 = mDao.myyouriddelete(id);
		boolean result = mDao.mydelete(id);
		if(result) {
			request.setAttribute("mydelete", "탈퇴가 완료되었습니다");
			request.getSession().invalidate();
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		}else if(result&&result1) {
			request.setAttribute("mydelete", "탈퇴가 완료되었습니다");
			request.getSession().invalidate();
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		}else {
			request.setAttribute("mydelete", "회원정보가 없습니다");
			fi.setPath("index.jsp");
			fi.setRedirect(false);
		}
		
		return fi;
	}

}
