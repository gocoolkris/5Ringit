package servlets;

import java.util.ArrayList;

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
		   
		    Post p=new Post("2008 summer olympic"," ","http://en.wikipedia.org/wiki/2008_Summer_Olympics");
			Post p2=new Post("2012 summer olympic"," ","http://en.wikipedia.org/wiki/2012_Summer_Olympics");
			Post p3=new Post("2016 summer olympic"," ","http://en.wikipedia.org/wiki/2016_Summer_Olympics");
			
			ArrayList <Post> posts = new ArrayList<Post>();
			posts.add(p);
			posts.add(p2);
			posts.add(p3);

				Template template = null;
				
				try {
						
					
					context.put("posts", posts);
					//context.put("des", p.getDesc());
					//p.getDesc();
					template = Velocity.getTemplate("index_latest.html");
				
				
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
				
				return template;
	    }
	   
}