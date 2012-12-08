package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import db.DBUtil;
import databaseobject.Comment;
import databaseobject.Post;
import databaseobject.User;

public class CommentService {

	public boolean saveComment(Comment comment){
		
		String query = "INSERT INTO COMMENTS VALUES(%d,%d,current_timestamp,'%s');\n";
		try{
			DBUtil.executeQuery(String.format(query,comment.getUsrid(),comment.getPid(),comment.getContent()));
			return true;
		}
		catch(Exception e){
			return false;
		}
		
	}
	/*
	public boolean deleteComment(Comment comment){
		
		
		String query = "DELETE FROM COMMENTS WHERE "
		
		
		
		return false;
		
	}
	*/
	
	public ArrayList<Comment> getCommentsForPost(Post post, int limit){
		
		String query = "SELECT * FROM COMMENTS WHERE PID=%d AND ROWNUM <=%d";
		ResultSet rs = DBUtil.executeQuery(String.format(query,post.getPid(),limit));
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try{
			while(rs.next()){
				
				Comment c = new Comment();
				c.setUsrid(rs.getInt("USRID"));
				c.setPid(rs.getInt("PID"));
				c.setPosttime(rs.getTimestamp("POSTTIME"));
				c.setContent("CONTENT");
				comments.add(c);
			}
			
			return comments;
		}
		catch(Exception e){
			
		}
	
		return null;
	}
	
	
	
	public ArrayList<Comment> getAllCommentsForPost(Post post){
		
		String query = "SELECT * FROM COMMENTS WHERE PID=%d;";
		ResultSet rs = DBUtil.executeQuery(String.format(query,post.getPid()));
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try{
			while(rs.next()){
				
				Comment c = new Comment();
				c.setUsrid(rs.getInt("USRID"));
				c.setPid(rs.getInt("PID"));
				c.setPosttime(rs.getTimestamp("POSTTIME"));
				c.setContent("CONTENT");
				comments.add(c);
			}
			
			return comments;
		}
		catch(Exception e){
			
		}
	
		return null;
		
	}
	
	
	public ArrayList<Comment> getCommentsForUser(User user){
		
		String query = "SELECT * FROM COMMENTS WHERE USRID=%d;";
		ResultSet rs = DBUtil.executeQuery(String.format(query,user.getUsrId()));
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try{
			while(rs.next()){
				
				Comment c = new Comment();
				c.setUsrid(rs.getInt("USRID"));
				c.setPid(rs.getInt("PID"));
				c.setPosttime(rs.getTimestamp("POSTTIME"));
				c.setContent(rs.getString("CONTENT"));
				comments.add(c);
			}
			
			return comments;
		}
		catch(Exception e){
			
		}
	
		return null;
		
	}
	
	public int getCommentsCountForPost(Post post){
	
		String query = "SELECT COUNT(*) AS CMTCOUNT FROM COMMENTS WHERE PID=%d";
		int count = Integer.MIN_VALUE;
		try{
			ResultSet rs = DBUtil.executeQuery(String.format(query,post.getPid()));
			while(rs.next()){
				
			count = rs.getInt("CMTCOUNT");	
			break;
			}
		}
		catch(Exception e){
			
		}
		return count;
	}
	
	
	public ArrayList<Integer> getPostIdsByUser(User usr){
		
		ArrayList<Integer> postids = new ArrayList<Integer>();
		String query = "SELECT PID FROM COMMENTS WHERE USRID=%d";
		try{
			ResultSet rs = DBUtil.executeQuery(String.format(query,usr.getUsrId()));
			while(rs.next()){
				
				int pid = rs.getInt("PID");
				postids.add(pid);
			}
			
			return postids;
		}
		catch(Exception e){}
		return null;
	}
	
	public ArrayList<Post> getAllCommentedPostforUser(User user)
	{
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where pid in(");
		sql.append("select pid from comments where usrid=");
		sql.append(user.getUsrId());
		sql.append(")");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("lnk"));
				post.setTime(set.getTimestamp("posttime"));
				post.setScore(set.getFloat("score"));		
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	
}
