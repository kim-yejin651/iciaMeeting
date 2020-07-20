package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MovingPage;


@WebServlet("/shoppingMall")
public class RestController extends HttpServlet {
   private static final long serialVersionUID = 1L;
  
   

   protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8");
      String cmd = request.getServletPath();
      System.out.println(cmd);
      MovingPage mp = new MovingPage(request, response);
      String json = mp.makeJson(cmd);
      if(json!=null) {
         PrintWriter out = response.getWriter();
         out.write(json);
      }
   }
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doProcess(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}