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
		try{
		StringBuffer sql=new StringBuffer();
		sql.append("insert into postlike(usrid,pid,liketime)");
		sql.append("values("+user.getUsrId());
		sql.append(",'"+post.getPid()+"',");
		sql.append("CURRENT_TIMESTAMP)");
		return DBUtil.executeUpdateInsertDelete(sql.toString())==1;//TODO check if key already exist
		}
		
		catch(Exception e){return false;}
	}
	
	public boolean DisLike(Post post,User user)
	{
		try{
		StringBuffer sql=new StringBuffer();
		sql.append("insert into postdislike(usrid,pid,disliketime)");
		sql.append("values("+user.getUsrId());
		sql.append(",'"+post.getPid()+"',");
		sql.append("CURRENT_TIMESTAMP)");
		return DBUtil.executeUpdateInsertDelete(sql.toString())==1;
		}
		catch(Exception e){
			return false;
		}
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
				postlike.setTime(set.getTimestamp("liketime"));
				postlike.setUsrid(set.getInt("usrid"));
				postlike.setPid(set.getInt("pid"));
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
				postdislike.setTime(set.getTimestamp("disliketime"));
				postdislike.setUsrid(set.getInt("usrid"));
				postdislike.setPid(set.getInt("pid"));
				list.add(postdislike);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	public int getLikeCount(Post post)
	{
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) as likecount from postlike where pid=");
		sql.append(post.getPid());
		sql.append("group by pid");
		int likecount=0;
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				likecount=set.getInt("likecount");
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return likecount;
	}
	
	public int getDisLikeCount(Post post)
	{
		StringBuffer sql=new StringBuffer();
		sql.append("select count(*) as dislikecount from postdislike where pid=");
		sql.append(post.getPid());
		sql.append("group by pid");
		int dislikecount=0;
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				dislikecount=set.getInt("dislikecount");
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return dislikecount;
	}
}

