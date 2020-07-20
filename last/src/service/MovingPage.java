package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MovingPage {

	HttpServletRequest request;
	HttpServletResponse response;
	public MovingPage(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}
	public String execute(String cmd) {
		String json = null;
		WordsMM wm = new WordsMM(request,response);
		if(cmd.equals("/allWords")) {
			json = wm.allWords();
		}
		
		return json;
	}

}
