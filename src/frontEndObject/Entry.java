package frontEndObject;

import java.util.Date;

public class Entry {
	
	private int id;		
	private String title;		
	private String desc;	
	private String link;
	private String author;
	private long postedTime;
	private int commentCount;
	private int likeCount;

	public Entry(int id, String title,String desc,String url,String author,long postedTime, 
			int commentCount, int likeCount){
		this.id = id;
		this.title=title;
		this.desc=desc;
		this.link=url;
		this.author = author;
		this.postedTime = postedTime;
		this.commentCount = commentCount;
		this.likeCount = likeCount;

	}
	
	/**
	 * returns the number of days from now to postedTime
	 * @return
	 */
	public long getRalativePostedTime() {
		
		
		Date date = new Date();
		long now = date.getTime();
		long relativeTime = now - postedTime;		
		return relativeTime/1000/3600;
		
	}
	
	/**
	 * set the postedTime by now
	 */
	public void setPostedTime() {
		Date date = new Date();
		long now = date.getTime();
		this.postedTime = now;
		
	}
	
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String url) {
		this.link = url;
	}	

}
