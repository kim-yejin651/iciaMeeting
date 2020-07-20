package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.chatMM;
import bean.ForwardInfo;
import bean.chatInfo;

@WebServlet("/chatInfo")
public class charController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public charController() {
        super();
       
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String cmd = request.getServletPath();
    	chatMM cm = new chatMM(request, response);
    	String chatName= request.getParameter("chatName");
		String chatContent= request.getParameter("chatContent");
    	switch(cmd) {
    	case "/chatInfo":
    		cm.chatInfo(chatName,chatContent);
    		break;
    	}
    	
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doProcess(request, response);
    	
    }
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
