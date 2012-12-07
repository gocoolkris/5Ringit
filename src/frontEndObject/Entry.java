package frontEndObject;

import java.util.ArrayList;
import java.util.Date;

import service.CommentService;
import service.LikeDislikeService;
import service.UserService;

import databaseobject.Post;

public class Entry {
	
	private int id;		
	private String title;		
	private String desc;	
	private String link;
	private String author;
	private long postedTime;
	private int commentCount;
	private int likeCount;
	private int dislikeCount;
	private boolean isLiked;
	private boolean isDisliked;
	
	public Entry(String title,String desc,String url,String author,long postedTime, 
			int commentCount, int likeCount){
		//this.id = id;
		this.title=title;
		this.desc=desc;
		this.link=url;
		this.author = author;
		this.postedTime = postedTime;
		this.commentCount = commentCount;
		this.likeCount = likeCount;

	}
	
	public Entry(Post post) {
		
		
		this.title=post.getTitle();
		this.desc= post.getDesc();
		this.link= post.getLink();
		this.id = post.getPid();
		this.postedTime = post.getTime().getTime();
		
	}
	
    public static ArrayList<Entry> getEntries(ArrayList<Post> posts) {
    	
		UserService userService = new UserService();
		LikeDislikeService ldService = new LikeDislikeService();
		CommentService commentService = new CommentService();
		
    	ArrayList<Entry> entries = new ArrayList<Entry>();
		for(int i=0; i<posts.size(); i++) {
			Entry entry = new Entry(posts.get(i));
			entry.setAuthor(userService.getUserbyUsrid((posts.get(i).getUsrid())).getUsername());
			//entry.setDislikeCount(ldService.getDislikeCount(posts.get(i)));
			//entry.setLikeCount(ldService.getLikeCount(posts.get(i)));
			//entry.setCommentCount(commentService.getCommentCount(posts.get(i)));
			entries.add(entry);
		}
		
		return entries;
		
		
	}	
	
	/**
	 * returns the number of days from now to postedTime
	 * @return
	 */
	public String getRalativePostedTime() {
		
		Date date = new Date();
		long now = date.getTime();
		long relativeTime = now - this.postedTime;	
		String relativePostedTime = Utils.convertDate(relativeTime);
		return relativePostedTime;		
		
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
	
	public int getDislikeCount() {
		return dislikeCount;
	}
	public void setDislikeCount(int dislikeCount) {
		this.dislikeCount = dislikeCount;
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
	
	public boolean isLiked() {
		return isLiked;
	}
	public boolean isDisliked() {
		return isDisliked;
	}
	
	public void setIsLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}
	public void setIsDisliked(boolean isDisliked) {
		this.isDisliked = isDisliked;
	}
	
	public static Entry getFakeEntry(int entryId) {
		Date date = new Date();
		long now = date.getTime();
		Entry e = new Entry("2008 summer olympic"," description1_" + entryId,"http://en.wikipedia.org/wiki/2008_Summer_Olympics", "david", now, 1, 1);
		e.setLikeCount(0);
	    e.setDislikeCount(0);
	    e.setIsDisliked(true);
		e.setIsLiked(true);
		e.setId(entryId);
		return e;
	}

}
