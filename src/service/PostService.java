package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import object.Post;
import object.User;

import db.DBUtil;

public class PostService {
	public int save(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("insert into post(pid,title,description,link)");
		int newpid=DBUtil.getPostMaxId()+1;
		sql.append("values("+newpid);
		sql.append(",'"+post.getTitle()+"',");
		sql.append("'"+post.getDesc()+"',");
		sql.append("'"+post.getLink()+"')");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	
	
	public int delete(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from article where id="+post.getId()+";");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}

	
	
	public Post getPostById(int id){
		Post post=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where id="+id+";");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				post=new Post();
				post.setId(set.getInt("id"));
				post.setTitle(set.getString("title"));

				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return post;
	}
	public ArrayList<Post> getAllPost(){
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
				post.setId(set.getInt("pid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("link"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	
	
	public ArrayList<Post> getPostByRank(int limit){
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
//		sql.append("select * from article order by pid");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
//				post.setId(set.getInt("id"));
//				post.setTitle(set.getString("title"));
//				post.setDesc(set.getString("description"));
//				post.setLink(set.getString("link"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<Post> getPostforUser(User user){
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
//		sql.append("select * from article order by pid");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
//				post.setId(set.getInt("id"));
//				post.setTitle(set.getString("title"));
//				post.setDesc(set.getString("description"));
//				post.setLink(set.getString("link"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
//	public int update(Post article){
//	StringBuffer sql=new StringBuffer();
//	int i=0;
//	try{
//		sql.append("update article set title='"+article.getTitle()+"',");
//		sql.append("content='"+article.getContent()+"',");
//		sql.append("where id="+article.getId()+";");
//		i=DBUtil.executeUpdateInsertDelete(sql.toString());
//	}catch(Exception ex){
//		ex.printStackTrace();
//	}
//	return i;
//}
}
