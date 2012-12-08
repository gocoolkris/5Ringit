package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.Comment;
import databaseobject.User;

import service.CommentService;
import service.UserService;

public class NewComment extends HttpServlet{
		
		public void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException {
			
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			int postID = Integer.parseInt(request.getParameter("post-id"));
			String content = request.getParameter("comment-content");
			
			
			
			if(username == null) {
				System.out.println("no username specified, redirect to entry.html");
				response.sendRedirect("/index?msg=unloggedin");
				
			}else {
				UserService userService = new UserService();
				User currentUser = userService.getUserbyUsername(username);
				
				System.out.println(username + " is trying to comment");
				Comment newComment = new Comment(currentUser.getUsrid(), postID, content);
				CommentService commentService = new CommentService();
				boolean commentSuccess = commentService.saveComment(newComment);
				if(commentSuccess) {
					System.out.println("comment saved for postID:" + postID);
					response.sendRedirect("/entry?postID="+postID);
				}else {
					System.out.println("Error when saving the comment for postID:" + postID);
					response.sendRedirect("/entry?newcomment=fail");
				}
			}
			
			
		}

}
