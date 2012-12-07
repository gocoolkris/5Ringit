package service;

import java.util.ArrayList;

import databaseobject.Comment;
import databaseobject.Post;
import databaseobject.User;
import db.DBUtil;

public class CommentService {
	public int save(Comment comment){
		StringBuffer sql=new StringBuffer();
//		sql.append("insert into post(pid,usrid,title,description,link,likecount,dislikecount,score,time)");
//		int newpid=DBUtil.getPostMaxId()+1;
//		sql.append("values("+newpid);
//		sql.append(",'"+post.getTitle()+"',");
//		sql.append(",'"+post.getUsrid()+"',");
//		sql.append("'"+post.getDesc()+"',");
//		sql.append("'"+post.getLink()+"',");
//		sql.append("'"+post.getLikecount()+"',");
//		sql.append("'"+post.getDisLikecount()+"',");
//		sql.append("'"+post.getTime()+"')");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	public int delete(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from post where pid="+post.getPid()+";");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	
	public ArrayList<Comment> getCommentsforPost(Post post,int limit)
	{
		return null;
	}
	
	public ArrayList<Comment> getCommentsforPostRankbyTime(Post post,int limit)
	{
		return null;
	}
	
	public ArrayList<Comment> getCommentsforUser(User user)
	{
		return null;
	}
}
