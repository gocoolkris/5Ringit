package servlets;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;

import databaseobject.Post;
import databaseobject.User;
import frontEndObject.Entry;

import service.PublishService;

public class PublishPost extends VelocityServlet{
	
	public Template handleRequest( HttpServletRequest request,
	           HttpServletResponse response,
	            	Context context ) {
		
		Template template = null;
		
		try {
			
				String newEntryTitle = request.getParameter("new-entry-title");
				String newEntryUrl =  request.getParameter("new-entry-url");
				String newPostDescription = request.getParameter("new-post-description");
				
				Post newPost = new Post(newEntryTitle, newPostDescription, newEntryUrl);
							
				User tmp= new User("david", "david");
				
				Date now = new Date();
				long currentTime = now.getTime();
				Entry newEntry = new Entry(newEntryTitle, newPostDescription, newEntryUrl, 
						tmp.getUsername(), currentTime, 0, 0);
				
				PublishService publishService = new PublishService();
				
				/**
				publishService.save(newPost, tmp);
				*/
				System.out.println(newEntryTitle + " saved");
				
				context.put("entry", newEntry);
				
				template = Velocity.getTemplate("entry.html");
				
				
			} catch( Exception e ) {
				 System.err.println("Exception caught: " + e.getMessage());
			}
				
			return template;
	    }
		
		
	}
