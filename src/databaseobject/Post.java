
package databaseobject;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post implements Serializable, Comparable{
	private int pid;
	private int usrid;
	private String title;		
	private String desc;	
	private String link;
	private int likecount;
	private int dislikecount;
	private double score;
	private Timestamp time;

	public Post(){}
	public Post(int usrid,String title,String desc,String url,Timestamp time){
		this.title=title;
		this.desc=desc;
		this.link=url;
		this.time=time;
		this.usrid=usrid;

	}
	public int getPid() {
		return pid;
	}
	public void setPid(int id) {
		this.pid = id;
	}

	public int getUsrid() {
		return usrid;
	}
	public void setUsrid(int usrid) {
		this.usrid=usrid;
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
	
	public int getLikecount()
	{
		return likecount;
	}
	
	public void setLikecount(int likecount)
	{
		this.likecount=likecount;
	}
	
	public int getDisLikecount() {
		return dislikecount;
	}

	public void setDisLikecount(int dislikecount)
	{
		this.dislikecount=dislikecount;
	}
	
	public double getScore()
	{
		return score;
	}
	
	public void setScore(double score)
	{
		this.score=score;
	}
	
	public  Timestamp getTime()
	{
		return time;
	}
	
	
	public void setTime(Timestamp time)
	{
		this.time=time;
	}
	
	public int compareTo(Object p)
	{
		return (int) (score-((Post)p).getScore());
	}

	
}
