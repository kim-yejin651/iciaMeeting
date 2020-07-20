package service;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.WordDao;

public class WordsMM {
	
	HttpServletRequest request;
	HttpServletResponse response;
	
	public WordsMM(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
	}

	public String allWords() {
		String json = null;
		WordDao wd = new WordDao(); 
		ArrayList<String> sList = new ArrayList<String>();
		if(wd.allWord(sList)) {
			Collections.shuffle(sList);
			json = new Gson().toJson(sList);
		}
		wd.closeDao();
		return json;
	}

}
