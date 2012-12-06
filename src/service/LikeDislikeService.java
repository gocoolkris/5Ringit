package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import databaseobject.Post;
import databaseobject.PostDisLike;
import databaseobject.PostLike;
import databaseobject.User;
import db.DBUtil;

public class LikeDislikeService {
	public boolean Like(Post post,User user)
	{
		StringBuffer sql=new StringBuffer();
		sql.append("insert into postlike(usrid,pid,time)");
		sql.append("values("+user.getUsrid());
		sql.append(",'"+post.getPid()+"',");
		sql.append("CURRENT_TIMESTAMP)");
		return DBUtil.executeUpdateInsertDelete(sql.toString())==1;//TODO check if key already exist
	}
	
	public boolean DisLike(Post post,User user)
	{
		StringBuffer sql=new StringBuffer();
		sql.append("insert into postdislike(usrid,pid,time)");
		sql.append("values("+user.getUsrid());
		sql.append(",'"+post.getPid()+"',");
		sql.append("CURRENT_TIMESTAMP)");
		return DBUtil.executeUpdateInsertDelete(sql.toString())==1;
	}
	
	public ArrayList<PostLike> getPostLikesforPost(Post post)
	{

		ArrayList<PostLike> list=new ArrayList<PostLike>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from postlike where pid="+post.getPid());
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				PostLike postlike=new PostLike();
//				postlike.setTimestamp(set.getTimestamp("time"));
//				postlike.setUserid(set.getInt("usrid"));
//				postlike.setpid(set.getInt("pid"));
				list.add(postlike);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	public ArrayList<PostDisLike> getPostDisLikesforPost(Post post)
	{
		ArrayList<PostDisLike> list=new ArrayList<PostDisLike>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from postdislike where pid="+post.getPid());
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				PostDisLike postdislike=new PostDisLike();
//				postdislike.setTimestamp(set.getTimestamp("time"));
//				postdislike.setUserid(set.getInt("usrid"));
//				postdislike.setpid(set.getInt("pid"));
				list.add(postdislike);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
}
