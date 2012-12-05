package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.User;

import service.UserService;

public class Logout extends HttpServlet  {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
			HttpSession session = request.getSession();
			if(session.getAttribute("username")!=null) {
				System.out.println("logged out:"+ session.getAttribute("username"));
				session.invalidate();
				response.sendRedirect("/index");
			}
	}

}
