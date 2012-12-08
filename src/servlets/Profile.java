package servlets;

import java.util.ArrayList;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import service.CommentService;
import service.FollowerService;
import service.LikeDislikeService;
import service.PostService;
import service.UserService;

import javax.servlet.http.*;
import frontEndObject.Entry;
import frontEndObject.TmpAthleteData;
import databaseobject.Post;
import databaseobject.User;

public class Profile extends VelocityServlet {
	
	private final int totalPosts = 30;
	private final int numPostsPerPage = 6;
	private final int followingListLimit = 20;
	

	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		 
		 
		 String userName = request.getParameter("username");
		 String mode = request.getParameter("mode");
		 HttpSession session = request.getSession();
		 boolean hasAttribute = false;
		 
		 int pageNum = 1;
		 String pageString = request.getParameter("page");
		 if(pageString!=null) {
			 pageNum = Integer.parseInt(pageString);
		 }
		 
		 String currentUser = null;
		 if(!session.isNew() && session.getAttribute("username")!=null) {
			 currentUser = (String)session.getAttribute("username"); 
			 hasAttribute = true;
			 
		 }else {
			 try {
				 response.sendRedirect("/index?msg=unloggedin");
				 return null;
			 }catch(Exception e) {
				 e.printStackTrace();
			 }
		 }
		 
		  PostService postService = null;
		  try {
			  postService = new PostService();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  UserService userService = new UserService();
		  FollowerService fservice = new FollowerService();
		  LikeDislikeService ldService = new LikeDislikeService();
		  CommentService commentService = new CommentService();
		  
		  boolean followed = fservice.isfollowing(userService.getUserbyUsername(currentUser), 
				  userService.getUserbyUsername(userName));

		  
		  /**
		  ArrayList<TmpAthleteData> athleteInfo = null;
		  ArrayList<Post> posts;
		  if(mode.equals("posts")) {
			  posts = postService.getAllPostsforUser(userService.getUserbyUsername(userName));
		  }else if(mode.equals("likes")) {
			  posts = ldService.getAllLikedPostforUser(userService.getUserbyUsername(userName));
		  }else if(mode.equals("dislikes")) {
			  posts = ldService.getAllDisLikedPostforUser(userService.getUserbyUsername(userName));
		  }else if(mode.equals("comments")) {
			  posts = commentService.getAllCommentedPostforUser(userService.getUserbyUsername(userName));
		  }else if(mode.equals("attributes")) {
			  athleteInfo = userService.getAthleteInformation(userService.getUserbyUsername(userName));
		  }
		  else {
			  System.out.println("mode = "+mode);
			  posts = postService.getAllPostsforUser(userService.getUserbyUsername(userName));
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
		  
		//create fake entries object
		 //ArrayList<Post> posts = new ArrayList<Post>();
		 ArrayList<Entry> entries = new ArrayList<Entry>();
		 Date tmp = new Date();
		 for (int i=0; i<totalPosts; i++) {
			 entries.add(Entry.getFakeEntry(i));			
		 }
		 
		 ArrayList<TmpAthleteData> athleteInfo = TmpAthleteData.getFakeAthleteData(10);
		 //end
		 
		 ArrayList<Entry> entriesPage = new ArrayList<Entry>();
		 
		 for(int i=numPostsPerPage*(pageNum-1); i < numPostsPerPage*pageNum && i < entries.size(); i++) {
			 entriesPage.add(entries.get(i));
		 }

		int totalNumOfPages = entries.size()/numPostsPerPage;
		
		int[] pages = new int[totalNumOfPages];
		for(int i=1; i<=totalNumOfPages; i++) {
			pages[i-1] = i;
		}
		/**
		 ArrayList<User> followingList = userService.getFollowingList(userName);
		 ArrayList<User> followerList = userService.getFolloweeList(userService.getUserbyUsername(userName));
		 */
		 
		
		//faking followingList
		ArrayList<User> followingList = new ArrayList<User>();
		ArrayList<User> followerList = new ArrayList<User>();
		for(int i=0; i<followingListLimit; i++) {
			User followee = new User("Estrella", "Estrella");
			User follower = new User("Obama", "Obama");
			followingList.add(followee);
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
					context.put("attributes", athleteInfo);
					context.put("hasAttribute", hasAttribute);
					context.put("followed", followed);
					context.put("mode", mode);
					template = Velocity.getTemplate("user.html");
					
					
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
					
				return template;
		 
		 
	 }
}
