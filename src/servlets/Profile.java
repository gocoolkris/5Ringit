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
		 
		//create fake entries object
		 //ArrayList<Post> posts = new ArrayList<Post>();
		 ArrayList<Entry> entries = new ArrayList<Entry>();
		 Date tmp = new Date();
		 for (int i=0; i<totalPosts; i++) {
			   Entry e = new Entry("2008 summer olympic"," description1_" + i,"http://en.wikipedia.org/wiki/2008_Summer_Olympics", userName, tmp.getTime(), 1, 1);
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
		
		ArrayList<User> followingList = new ArrayList<User>();
		for(int i=0; i<followingListLimit; i++) {
			User followed = new User("Estrella", "Estrella");
			followingList.add(followed);
		}
		 
		 
		 
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
					template = Velocity.getTemplate("user.html");
					
					
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
					
				return template;
		 
		 
	 }
}
