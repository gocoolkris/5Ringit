package databaseobject;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Comment implements Serializable{

	private int usrid, pid;
	private Timestamp posttime;
	private String content;
	
	
	public Comment(){}
	public Comment(int usrid, int pid, String contnt){
		this.usrid = usrid;
		this.pid = pid;
		this.posttime = new Timestamp(new java.util.Date().getTime());
		this.content = contnt;
	}
	
	public Comment(int usrid, int pid, Timestamp psttime, String contnt){
		this.usrid = usrid;
		this.pid = pid;
		this.posttime = psttime;
		this.content = contnt;
	}
	
	public int getUsrid() {
		return usrid;
	}
	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Timestamp getPosttime() {
		return posttime;
	}
	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	
	
	
}
