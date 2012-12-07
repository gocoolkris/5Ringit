
package databaseobject;

import java.sql.Timestamp;

public class PostLike {
	int usrid;
	int pid;
	Timestamp time;
	
	public int getUsrid()
	{
		return usrid;
	}
	
	public void setUsrid(int id)
	{
		this.usrid=id;
	}
	
	public int getPid()
	{
		return pid;
	}
	
	public void setPid(int id)
	{
		this.pid=id;
	}
	
	public Timestamp getTime()
	{
		return time;
	}
	public void setTime(Timestamp time)
	{
		this.time=time;
	}
}
