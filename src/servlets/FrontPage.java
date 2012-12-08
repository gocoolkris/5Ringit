package servlets;

import java.util.ArrayList;
import java.util.Date;

import databaseobject.Post;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import service.PostService;
import service.UserService;

import javax.servlet.http.*;
import frontEndObject.Entry;
import databaseobject.User;

public class FrontPage extends VelocityServlet {
	
	 private final int numPostsPerPage = 10;
	 private final int totalPosts = 100;
	 private final int userListNum = 10;
	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		 
		 String order = request.getParameter("order");
		 String loginSuccess = request.getParameter("login");		 
		 String msg = request.getParameter("msg");
		 String signup = request.getParameter("signup");
		 
		 String alertMessage = null;
		 if(msg!=null&&msg.equals("unloggedin")) {
			 alertMessage = "Please login first.Thanks";
		 }
		 if(msg!=null&&msg.equals("voted")) {
			 alertMessage = "You already voted for this post. Thanks.";
		 }
		 
		 if(loginSuccess!=null && loginSuccess.equals("fail")) {
			 alertMessage = "Not logged in. Please try again";
		 }
		 if(signup!=null) {
			 if(signup.equals("success")) {
				 alertMessage = "Thank you for sign up!";
			 }else if(signup.equals("fail")) {
				 alertMessage = "Please try again";
			 }
		 }
		 HttpSession session = request.getSession();
		 String currentUser = null;
		 
		 if(!session.isNew() && session.getAttribute("username")!=null) {
			 currentUser = (String) session.getAttribute(("username"));
			 alertMessage = "Welcome back " + currentUser;
		 }
		 
		 int pageNum = 1;
		 if(request.getParameter("page")!=null) {
			 String page = (String)request.getParameter("page");
			 System.out.println("getting page :" + page);
			  pageNum = Integer.parseInt(page);
		 }
		 
		 System.out.println(pageNum);
		 
		 //real data
		   /**
		   ArrayList<Post> posts;
		   PostService postService = null;
		   try {
			   postService = new PostService();
		   }catch(Exception e) {
			   e.printStackTrace();
		   }
		   if(order!=null && order.equals("popular")) {
			   	if(currentUser!=null){
			   		UserService userService = new UserService();
			   		User user = userService.getUserbyUsername(currentUser);
			   		posts = postService.getPostsRankbyScoreforUser(totalPosts, user);
			   		
			   	}else {
			   		posts = postService.getPostsRankbyScore(totalPosts);
			   	}
		   }else{
			   posts = postService.getPostsRankbyTime(totalPosts);
		   }
		   
		  ArrayList<Entry> entriesTmp = Entry.getEntries(posts);
	   	  ArrayList<Entry> entries = new ArrayList<Entry>();
	   	  
	   	  for(int i = 0; i< entriesTmp.size(); i++) {
	   		  Entry e = entriesTmp.get(i);
	   		  String postIDVote = (String)session.getAttribute(Integer.toString(e.getId()));
			   //System.out.println("postIDVote:"+postIDVote);
			   e.setIsLiked(false);
			   e.setIsDisliked(false);
			   if(postIDVote!=null) {
				   if(postIDVote.equals("like")) {
					   e.setIsLiked(true);
				   }
				   if(postIDVote.equals("dislike")) {
					   e.setIsDisliked(true);
				   }
			   }
			   entries.add(e);
	   	  }
	   	  */
		 //end
		

		 
		 //create fake entries object
		 //ArrayList<Post> posts = new ArrayList<Post>();
		 
		 ArrayList<Entry> entries = new ArrayList<Entry>();
		 for (int i=0; i<totalPosts; i++) {
			   Entry e = Entry.getFakeEntry(i);
			   String postIDVote = (String)session.getAttribute(Integer.toString(e.getId()));
			   //System.out.println("postIDVote:"+postIDVote);
			   e.setIsLiked(false);
			   e.setIsDisliked(false);
			   if(postIDVote!=null) {
				   if(postIDVote.equals("like")) {
					   e.setIsLiked(true);
				   }
				   if(postIDVote.equals("dislike")) {
					   e.setIsDisliked(true);
				   }
			   }
			   entries.add(e);
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
		
		
		UserService userService = new UserService();
		
		/**
		ArrayList<User> userList;
		if(currentUser == null) {
			userList = userService.getNewUsers(userListNum);
		}else {
			User user = userService.getUserbyUsername(currentUser);
			//userList = userService.getRecommendedFriends(user);
		}
		*/
		
		
		//creating fake userList;
		
		ArrayList<User> userList = new ArrayList <User> ();
		for(int i=0; i<userListNum; i++) {
			userList.add(new User("david", "david"));
		}
		
		//end
		
		Template template = null;
				
		try {
				
				context.put("entries", entriesPage);
				//context.put("alertMessage", "psw invalid");
				context.put("order", "latest");
				context.put("currentPage", pageNum);
				context.put("totalPages", totalNumOfPages);
				context.put("pages", pages);
				context.put("currentUser", currentUser);
				context.put("userList", userList);
				context.put("alertMessage", alertMessage);
				context.put("order", order);
				template = Velocity.getTemplate("index.html");
				
			} catch( Exception e ) {
				 System.err.println("Exception caught: " + e.getMessage());
			}
				
			return template;
	    }
	   
}