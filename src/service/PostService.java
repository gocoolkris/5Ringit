package service;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import databaseobject.Post;
import databaseobject.User;

import db.DBUtil;

public class PostService {
	public int save(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("insert into post(pid,usrid,title,description,link,likecount,dislikecount,score,time)");
		int newpid=DBUtil.getPostMaxId()+1;
		sql.append("values("+newpid);
		sql.append(",'"+post.getTitle()+"',");
		sql.append(",'"+post.getUsrid()+"',");
		sql.append("'"+post.getDesc()+"',");
		sql.append("'"+post.getLink()+"',");
		sql.append("'"+post.getLikecount()+"',");
		sql.append("'"+post.getDisLikecount()+"',");
		sql.append("'"+post.getTime()+"')");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	
	
	public int delete(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from post where pid="+post.getPid()+";");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}

	
	
	public Post getPostById(int pid){
		Post post=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where pid="+pid+";");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				post=new Post();
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("link"));
				post.setLikecount(set.getInt("likecount"));
				post.setDisLikecount(set.getInt("dislikecount"));
				post.setScore(set.getDouble("score"));
				post.setTime(set.getTimestamp("time"));
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
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("link"));
				post.setLikecount(set.getInt("likecount"));
				post.setDisLikecount(set.getInt("dislikecount"));
				post.setScore(set.getDouble("score"));
				post.setTime(set.getTimestamp("time"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	
	
	public ArrayList<Post> getPostByRank(int limit){//TODO
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
//		sql.append("select * from article order by score");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("link"));
				post.setLikecount(set.getInt("likecount"));
				post.setDisLikecount(set.getInt("dislikecount"));
				post.setScore(set.getDouble("score"));
				post.setTime(set.getTimestamp("time"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<Post> getPostforUser(User user){//TODO
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
//		sql.append("select * from article order by pid");
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				Post post=new Post();
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("link"));
				post.setLikecount(set.getInt("likecount"));
				post.setDisLikecount(set.getInt("dislikecount"));
				post.setScore(set.getDouble("score"));
				post.setTime(set.getTimestamp("time"));
				list.add(post);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	
	public User getUserByPost(Post post){
		return us.getUserbyusrid(post.getUsrid());
	}
	
	public int  updateScore(Post post)
	{
		double newscore=calScore(post.getLikecount(),post.getDisLikecount(),post.getTime());
		StringBuffer sql=new StringBuffer();
		sql.append("update  post set score=");
		sql.append(newscore);
		sql.append("where pid=");
		sql.append(post.getPid());
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	public double calScore(int likecount,int dislikecount,Timestamp time)
	{
		long t=time.getTime()-START_TIME.getTime();
		int x=likecount-dislikecount;
		int y=0;
		int z=1;
		if(x>0)
			y=1;
		else if(x<0)
			y=-1;
		if(Math.abs(x)>=1)
			z=Math.abs(x);
		return Math.log10(z)+y*t/time_ratio;
	}
	static Timestamp START_TIME=null;
	static int time_ratio=45000;
	UserService us;
	
	public PostService() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/01/2012");
		long start_time = date.getTime();
		START_TIME=new Timestamp(start_time);
		us=new UserService();
	}
}
