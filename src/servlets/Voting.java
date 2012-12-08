package servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import databaseobject.User;

import service.LikeDislikeService;
import service.PostService;
import service.UserService;

public class Voting extends HttpServlet  {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String vote = request.getParameter("vote");
		String postID = request.getParameter("postID");
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		
		System.out.println("voting:" + username);
		
		boolean success =false;;
		
		if(username!=null) {
			LikeDislikeService ldService = new LikeDislikeService();
			UserService userService = new UserService();
			PostService ps = null;
			try {
				ps = new PostService();
			}catch(Exception e) {
				e.printStackTrace();
			}

			if(vote.equals("like")) {
				success = ldService.Like(ps.getPostById(Integer.parseInt(postID)), userService.getUserbyUsername(username));
				System.out.println(username + " like post: " + postID);
				session.setAttribute(postID, "like");

			}else if(vote.equals("dislike")) {
				success = ldService.DisLike(ps.getPostById(Integer.parseInt(postID)), userService.getUserbyUsername(username));
				System.out.println(username + " dislike post: " + postID);
				session.setAttribute(postID, "dislike");
			}
			if(!success) {
				response.sendRedirect("/index?msg=voted");
			}else {
				response.sendRedirect("/index");
			}
	
		}else {
			System.out.println("voting: not logged in");
			response.sendRedirect("/index?login=fail");
		}
		
	}

}
