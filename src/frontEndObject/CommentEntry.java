package frontEndObject;

import java.util.ArrayList;
import java.util.Date;

import databaseobject.Comment;

import service.UserService;


public class CommentEntry {
	
	String author;
	String content;
	long postedTime;
	
	public CommentEntry(String author, String content) {
		this.author=author;
		this.content=content;
	}
	
	public String getAuthor() {
		return this.author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content= content;
	}
	public long getPostedTime() {
		return postedTime;
	}
	public void setPostedTime(long postedTime) {
		this.postedTime = postedTime;
	}
	
	public String getRalativePostedTime() {
		
		Date date = new Date();
		long relativeTime = date.getTime() - postedTime;
		return Utils.convertDate(relativeTime);
		
	}
	
	public CommentEntry(Comment comment) {
		UserService userService = new UserService();

		 this.author = userService.getUserbyUsrid(comment.getUsrid()).getUsername();	  	
		 this.content = comment.getContent();
		this.postedTime = comment.getPosttime().getTime();
  	
	}
	
	  public static ArrayList<CommentEntry>getCommentEntries(ArrayList<Comment> comments) {
  	
		  ArrayList<CommentEntry> commentEntries = new ArrayList<CommentEntry>();
		  for(int i=0;i<comments.size(); i++) {
		      commentEntries.add(new CommentEntry(comments.get(i)));
		  }
		   return commentEntries;
	}
	

}
