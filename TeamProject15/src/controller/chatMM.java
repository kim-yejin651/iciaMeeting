package controller;

import dao.chatDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ForwardInfo;
import bean.chatInfo;

public class chatMM {
	HttpServletRequest request; 
	HttpServletResponse response;

	public chatMM(HttpServletRequest request, HttpServletResponse response) {
		this.request=request;
		this.response=response;
	}

	public void chatInfo(String chatName, String chatContent) {
		System.out.println(chatName);
		System.out.println(chatContent);
		chatInfo ci= new chatInfo();
		ci.setNinkname(chatName);
		ci.setChatContent(chatContent);
		
		chatDao cDao = new chatDao();
		cDao.chatInfo(ci);
		
	}

}
