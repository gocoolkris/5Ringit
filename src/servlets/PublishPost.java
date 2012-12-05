package servlets;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;

import service.PostService;

import databaseobject.Post;
import databaseobject.User;
import frontEndObject.Entry;

public class PublishPost extends VelocityServlet{
	
	public Template handleRequest( HttpServletRequest request,
	           HttpServletResponse response,
	            	Context context ) {
		
		Template template = null;
		
		try {
			
				String newEntryTitle = request.getParameter("new-entry-title");
				String newEntryUrl =  request.getParameter("new-entry-url");
				String newPostDescription = request.getParameter("new-post-description");
				int userID = 1;
				
				Date now = new Date();
				Timestamp timestamp = new Timestamp(now.getTime());
				
				Post newPost = new Post(userID, newEntryTitle, newPostDescription, newEntryUrl, timestamp);
							
				User tmp= new User("david", "david");
				
				long currentTime = now.getTime();
				Entry newEntry = new Entry(newEntryTitle, newPostDescription, newEntryUrl, 
						tmp.getUsername(), currentTime, 0, 0);
				
				PostService postService = new PostService();
				
				/**
				postService.save(newPost, tmp);
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
