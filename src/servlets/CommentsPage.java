package servlets;

import java.util.ArrayList;
import java.util.Date;

import org.apache.velocity.Template;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;

import service.CommentService;
import service.PostService;

import javax.servlet.http.*;

import frontEndObject.CommentEntry;
import frontEndObject.Entry;
import databaseobject.Comment;
import databaseobject.Post;
import databaseobject.User;

public class CommentsPage extends VelocityServlet {
	
	private final int commentNumLimit = 20;
	

	 public Template handleRequest( HttpServletRequest request,
           HttpServletResponse response,
            	Context context ) {
		 
		 String alertMessage = null;
		 String currentUser = null;
		 String newComment = request.getParameter("newcomment");
		 if(newComment != null && newComment.equals("fail")) {
			 alertMessage = "Illegal comment. Please try again.";
		 }
		 HttpSession session = request.getSession();
		 if(session.getAttribute("username")!=null) {
			 currentUser = (String)session.getAttribute("username");
			 System.out.println("session for " + currentUser);
		 }
		 
		 Template template = null;
		 
		 String postID = request.getParameter("postID");
		 System.out.println("commentPage postID:" + postID);
		 
		 PostService postService = null;
		 try {
			 postService = new PostService();
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		  CommentService commentService = new CommentService();
		  Post post = postService.getPostById(Integer.parseInt(postID));
		  System.out.println("get comments for:" + postID + " title:" + post.getTitle());
		  
		  Entry entry = new Entry(post);
		  ArrayList<Comment> comments = commentService.getCommentsForPost(post, commentNumLimit);
		  ArrayList<CommentEntry> commentEntries = CommentEntry.getCommentEntries(comments);
		  
		 
		 //faking entry object
		 /**
		 Date now = new Date();
		 long nowTime = now.getTime();
		 Entry entry = new Entry("title", "Description", "URL://url.com", "Wei Dai", nowTime, 100, 100);
		 
		 ArrayList<CommentEntry> comments = new ArrayList<CommentEntry>();
		 for(int i=0; i<commentNumLimit; i++) {
			 comments.add(new CommentEntry("JK rowling", "IJIJINNIHIJIJI"));
		 }
		 */
		 //end
			
			try {
							
					context.put("entry", entry);
					context.put("currentUser", currentUser);
					context.put("comments", commentEntries);
					context.put("alertMessage", alertMessage);
					template = Velocity.getTemplate("entry.html");
					
					
				} catch( Exception e ) {
					 System.err.println("Exception caught: " + e.getMessage());
				}
					
				return template;
	 }
		 
		 
}
