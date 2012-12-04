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
		 
		 int pageNum = 1;
		 if(request.getParameter("page")!=null) {
			 String page = (String)request.getParameter("page");
			 System.out.println(page);
			  pageNum = Integer.parseInt(page);
		 }
		 
		 System.out.println(pageNum);
		 
		 		   		   
		   /**
		   PostService postService = new PostService();
		   ArrayList<Post> posts = postService.getPostRankbyTime(totalPosts);
		   */
		   
		 ArrayList<Post> posts = new ArrayList<Post>();
		
		 
		 //create fake entries object
		 ArrayList<Entry> entries = new ArrayList<Entry>();
		 Date tmp = new Date();
		 for (int i=0; i<totalPosts; i++) {
			   Entry e = new Entry("2008 summer olympic"," description1_" + i,"http://en.wikipedia.org/wiki/2008_Summer_Olympics", "david", tmp.getTime(), 1, 1);
			   e.setId(i);
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
		
		/**
		UserService userService = new UserService();
		ArrayList<User> userList = userService.getNewUsers(userListNum);
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
				context.put("currentUser", "david");
				context.put("userList", userList);
				template = Velocity.getTemplate("index.html");
				
				
			} catch( Exception e ) {
				 System.err.println("Exception caught: " + e.getMessage());
			}
				
			return template;
	    }
	   
}