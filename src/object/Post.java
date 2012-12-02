package object;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable{
	private int id;		
	private String title;		
	private String desc;	
	private String link;
	private String author;
	private long postedTime;
	private int commentCount;
	private int likeCount;

	public Post(){}
	public Post(String title,String desc,String url,String author){
		this.title=title;
		this.desc=desc;
		this.link=url;
		this.author = author;

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
	public String getAuthor() {
		return author;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String url) {
		this.link = url;
	}		
	

}
