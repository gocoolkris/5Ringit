package servlets;

import java.util.ArrayList;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import javax.servlet.http.*;
import frontEndObject.Entry;
import databaseobject.User;

public class Profile extends VelocityServlet {
	
	private final int totalPosts = 30;
	private final int numPostsPerPage = 6;
	private final int followingListLimit = 20;
	

	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		 
		 
		 String userName = request.getParameter("username");
		 HttpSession session = request.getSession();
		 
		 int pageNum = 1;
		 String pageString = request.getParameter("page");
		 if(pageString!=null) {
			 pageNum = Integer.parseInt(pageString);
		 }
		 
		 String currentUser = null;
		 if(!session.isNew() && session.getAttribute("username")!=null) {
			 currentUser = (String)session.getAttribute("username"); 
		 }
		 
		 /**
		  * PostService postService = new PostService();
		  * ArrayList<Post> entries = postService.getPostsByUser(String username);
		  * ArrayList<entry> entries = ...
		  */
		 
		//create fake entries object
		 //ArrayList<Post> posts = new ArrayList<Post>();
		 ArrayList<Entry> entries = new ArrayList<Entry>();
		 Date tmp = new Date();
		 for (int i=0; i<totalPosts; i++) {
			 entries.add(Entry.getFakeEntry(i));			
		 }
		 //end
		 
		 ArrayList<Entry> entriesPage = new ArrayList<Entry>();
		 for(int i=numPostsPerPage*(pageNum-1); i<numPostsPerPage*pageNum; i++) {
			 entriesPage.add(entries.get(i));
		 }

		int totalNumOfPages = entries.size()/numPostsPerPage;
		
		int[] pages = new int[totalNumOfPages];
		for(int i=1; i<=totalNumOfPages; i++) {
			pages[i-1] = i;
		}
		
		/**
		 * UserService userService = new UserService();
		 * ArrayList<User> followingList = userService.getFollowingList(String username);
		 * ArrayList<User> followerList = userService.getFollowerList(String username);
		 * 
		 */
		
		//faking followingList
		ArrayList<User> followingList = new ArrayList<User>();
		ArrayList<User> followerList = new ArrayList<User>();
		for(int i=0; i<followingListLimit; i++) {
			User followed = new User("Estrella", "Estrella");
			User follower = new User("Obama", "Obama");
			followingList.add(followed);
			followerList.add(follower);
		}
		//end

		
		 	Template template = null;
			
			try {
							
					context.put("userName", userName);
					context.put("currentUser", currentUser);
					context.put("followed", "true");
					context.put("currentPage", pageNum);
					context.put("pages", pages);
					context.put("totalPages", totalNumOfPages);
					context.put("followingList", followingList);
					context.put("entries", entriesPage);
					context.put("followerList", followerList);
					template = Velocity.getTemplate("user.html");
					
					
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
					
				return template;
		 
		 
	 }
}
