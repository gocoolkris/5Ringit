package frontEndObject;

import java.util.Date;


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
	public CommentEntry()
	
	

}
