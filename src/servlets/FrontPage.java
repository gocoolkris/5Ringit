package servlets;

import java.util.ArrayList;
import java.util.Date;

import object.Post;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import javax.servlet.http.*;

public class FrontPage extends VelocityServlet {

	   public Template handleRequest( HttpServletRequest request,
               HttpServletResponse response,
               Context context ) {
	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		   
		   int numPostsPerPage = 5;
		    Post p=new Post("2008 summer olympic"," description1","http://en.wikipedia.org/wiki/2008_Summer_Olympics","david");
			Post p2=new Post("2012 summer olympic"," hello world","http://en.wikipedia.org/wiki/2012_Summer_Olympics", "gokul");
			Post p3=new Post("2016 summer olympic","foo bar","http://en.wikipedia.org/wiki/2016_Summer_Olympics", "yang");
			
			ArrayList <Post> posts = new ArrayList<Post>();
			posts.add(p);
			posts.add(p2);
			posts.add(p3);
			
			p.setId(1);
			p2.setId(2);
			p3.setId(3);
			
			p.setPostedTime();
			p2.setPostedTime();
			p3.setPostedTime();
			
			int totalPages = posts.size()/numPostsPerPage;
		   
		   Post[] posts = new Post[11];
		   
		   for (int i=0; i<posts.length; i++) {
			   Post p = new Post("2008 summer olympic"," description1","http://en.wikipedia.org/wiki/2008_Summer_Olympics","david");
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
					
					//context.put("des", p.getDesc());
					//p.getDesc();
					template = Velocity.getTemplate("index.html");
				
				
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
				
				return template;
	    }
	   
}