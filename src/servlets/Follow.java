import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.FollowerService;
import service.UserService;
import databaseobject.User;

public class Follow extends HttpServlet  {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String followee = request.getParameter("object");
		HttpSession session = request.getSession();
		String follower = (String)session.getAttribute("username");
		String follow = request.getParameter("follow");
		
		UserService userService = new UserService();
		FollowerService followerService = new FollowerService();
		
		User followerUser = userService.getUserbyUsername(follower);
		User followeeUser = userService.getUserbyUsername(followee);
		
		if(follow.equals("true")) {
			followerService.follow(followerUser, followeeUser);
			System.out.println(follower + "is now following "+ followee);
		}else {
			followerService.unfollow(followerUser, followeeUser);
			System.out.println(follower + "unfollowed " + followee);
		}
		
		response.sendRedirect("/user?username=" + followee);
		
		
		
		
	}
}