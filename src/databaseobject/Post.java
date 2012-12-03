package databaseobject;

import java.io.Serializable;

public class Post implements Serializable{
	private int id;		
	private String title;		
	private String desc;	
	private String link;	

	public Post(){}
	public Post(String title,String desc,String url){
		this.title=title;
		this.desc=desc;
		this.link=url;

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
	public String getLink() {
		return link;
	}
	public void setLink(String url) {
		this.link = url;
	}		

}
