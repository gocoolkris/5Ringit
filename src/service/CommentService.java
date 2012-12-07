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
	
	
}
