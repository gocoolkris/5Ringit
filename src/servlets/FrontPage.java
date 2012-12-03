package servlets;

import java.util.ArrayList;
import java.util.Date;

import databaseobject.Post;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import service.PostService;

import javax.servlet.http.*;
import frontEndObject.Entry;

public class FrontPage extends VelocityServlet {

	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		   
		   int numPostsPerPage = 5;
		   
		   PostService postService = new PostService();
		   
		   Post[] posts = new Post[11];
		   
		   
		   Entry[] entries = new Entry[10];
		   
		   for (int i=0; i<posts.length; i++) {
			   Post p = new Post(i, "2008 summer olympic"," description1","http://en.wikipedia.org/wiki/2008_Summer_Olympics");
			   p.setId(1);
			   p.setLikeCount(1);
			   p.setCommentCount(1);
			   p.setPostedTime();
			   posts[i] = p;
		   }

			int totalPages = posts.length/numPostsPerPage;


				Template template = null;
				
				try {
						
					
					context.put("entries", posts);
					//context.put("alertMessage", "psw invalid");
					context.put("order", "latest");
					context.put("currentPage", 1);
					context.put("totalPages", totalPages);
					
					template = Velocity.getTemplate("index.html");
				
				
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
				
				return template;
	    }
	   
}