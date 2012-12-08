package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.User;

import service.UserService;

public class Signup extends HttpServlet  {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("signup-username");
		String password = request.getParameter("signup-password");
		String repeat = request.getParameter("signup-repeat-password");
		
		
		if(repeat == null||password==null||username==null||!repeat.equals(password)) {
			response.sendRedirect("/index?signup=fail");
			return;
		}
		
		UserService userService= new UserService();
		boolean signonSuccess= userService.saveNewUser(username, password);
		
		
		//fake object
		//User passedUser = new User(psw, username);
		//end
		
		HttpSession session = request.getSession();

		if(signonSuccess) {
			System.out.println("signon successful:" + username + ":" + password);
			session.setAttribute("username", username);
			response.sendRedirect("/index?signup=success");
				
		}else {
			System.out.println("signup failed");
			//session.setAttribute("login", "fail");
			response.sendRedirect("/index?signup=fail");
		}
		
	}

}
