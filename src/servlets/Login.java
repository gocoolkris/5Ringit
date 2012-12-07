package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.User;

import service.UserService;

public class Login extends HttpServlet  {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String psw = request.getParameter("login-password");
		String username = request.getParameter("login-username");
		
		UserService userService= new UserService();
		
		
		User passedUser = userService.login(username, psw);
		
		
		//fake object
		//User passedUser = new User(psw, username);
		//end
		
		HttpSession session = request.getSession();

		if(passedUser!=null) {
			System.out.println("logged in:"+passedUser.getUsername());
			session.setAttribute("username", passedUser.getUsername());
			response.sendRedirect("/index");
				
		}else {
			System.out.println("login failed");
			//session.setAttribute("login", "fail");
			response.sendRedirect("/index?login=fail");
		}
		
	}

}
