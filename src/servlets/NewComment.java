package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.Comment;

import service.CommentService;

public class NewComment extends HttpServlet{
	
	public class Login extends HttpServlet  {
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			if(username == null) {
				System.out.println("no username specified, redirect to entry.html");
				response.sendRedirect("/index?msg=unloggedin");
				
			}else {
				
				System.out.println(username + " is trying to comment");
				Comment newComment = new Comment(userID, postID, content);
				CommentService commentService = new CommentService();
				boolean commentSuccess = commentService.saveComment(newComment);
				if(commentSuccess) {
					response.sendRedirect("/entry?postID="+postID);
				}else {
					response.sendRedirect("/index");
				}
			}
			
			
		}
	}

}
