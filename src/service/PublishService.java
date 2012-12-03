//<<<<<<< HEAD
//package service;

//import java.sql.Timestamp;
//
//import object.Post;
//import object.User;
//
//public class PublishService {
//	public void publish(Post post,User user,Timestamp time)
//	{
//		
//	}
//	
//	
//	
//}
//=======
package service;

import java.sql.ResultSet;
import java.sql.Timestamp;

import databaseobject.Post;
import databaseobject.User;
import db.DBUtil;

public class PublishService {
	public int save(Post post,User user)
	{
		StringBuffer sql=new StringBuffer();
		sql.append("insert into publish(usrid,pid,posttime)");
		sql.append("values("+user.getId());
		sql.append(",'"+post.getId()+"',");
		sql.append("CURRENT_TIMESTAMP)");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
		
	}
	
	public void delete(Post post,User user)
	{
		
	}
	
	public long getPublishTime(Post post)
	{
		long time=0;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from publish where pid="+post.getId()+";");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Timestamp ts =set.getTimestamp("posttime");
				time=ts.getTime();
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return time;
	}
}
//>>>>>>> Post.save() and getAllPost() is working
