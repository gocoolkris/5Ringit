package service;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import databaseobject.Post;
import databaseobject.User;

import db.DBUtil;

public class PostService {
	public int save(Post post){
		
		StringBuffer sql=new StringBuffer();
		sql.append("insert into post (pid,usrid,title,description,lnk,posttime,score)");
		int newpid=DBUtil.getPostMaxId()+1;
		sql.append("values("+newpid);
		sql.append(","+post.getUsrid());
		sql.append(",'"+post.getTitle()+"',");
		sql.append("'"+post.getDesc()+"',");
		sql.append("'"+post.getLink()+"',");
		sql.append("current_timestamp,0)");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}
	
	public int saveandReturnPid(Post post){
		save(post);
		return DBUtil.getPostMaxId();
	}
	
	
	public int delete(Post post){
		StringBuffer sql=new StringBuffer();
		sql.append("delete from post where pid="+post.getPid()+";");
		return DBUtil.executeUpdateInsertDelete(sql.toString());
	}

	
	
	public Post getPostById(int pid){
		Post post=new Post();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where pid="+pid);
		try{
			ResultSet set=DBUtil.executeQuery(sql.toString());
			while(set.next()){
				post.setPid(set.getInt("pid"));
				post.setUsrid(set.getInt("usrid"));
				post.setTitle(set.getString("title"));
				post.setDesc(set.getString("description"));
				post.setLink(set.getString("lnk"));
				post.setTime(set.getTimestamp("posttime"));
				post.setScore(set.getFloat("score"));				
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return post;
	}
	public ArrayList<Post> getAllPosts(){
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
	
	
	
	public ArrayList<Post> getPostsRankbyScore(int limit){//TODO
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from postpopular where rownum<");
		sql.append(limit);
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
	
	
	public ArrayList<Post> getPostsRankbyScoreforUser(int num,User user){//TODO
		ArrayList<Post> list=getPostsRankbyScore(num);
		HashSet<Integer> followeelist=us.getFolloweeListAsUserIdList(user);
		
		for(Post p:list)
		{
			if(followeelist.contains(p.getUsrid()))
				p.setScore(p.getScore()*followee_ratio);
		}
		Collections.sort(list);
		return list;
	}
	
	public ArrayList<Post> getPostsRankbyTime(int limit){//TODO
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from postlatest where rownum<");
		sql.append(limit);
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
	
	
	public ArrayList<Post> getAllPostsforUser(User user)
	{
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where usrid=");
		sql.append(user.getUsrId());
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
	
	public ArrayList<Post> getAllPostsforUser(User user, int limit)
	{
		ArrayList<Post> list=new ArrayList<Post>();
		StringBuffer sql=new StringBuffer();
		sql.append("select * from post where usrid=");
		sql.append(user.getUsrId());
		sql.append(" AND ROWNUM <= ");
		sql.append(limit);
		sql.append(" ORDER BY SCORE DESC");
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
	
	
	
	
	public User getUserByPost(Post post){
		return us.getUserbyUsrid(post.getUsrid());
	}
	
	public void Like(Post post)
	{
//		post.setLikecount(ls.getLikeCount(post));
		updateScore(post);
	}
	
	public void DisLike(Post post)
	{
//		post.setDisLikecount(ls.getLikeCount(post));
		updateScore(post);
	}
	
	
	public int  updateScore(Post post)
	{
		double newscore=calScore(ls.getLikeCount(post),ls.getDisLikeCount(post),post.getTime());
		StringBuffer sql=new StringBuffer();
		sql.append("update  post set score=");
		sql.append(newscore);
		sql.append(" where pid=");
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
	static float followee_ratio=10f;
	UserService us;
	LikeDislikeService ls;
	
	public PostService() throws ParseException
	{
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/01/2012");
		long start_time = date.getTime();
		START_TIME=new Timestamp(start_time);
		us=new UserService();
		ls=new LikeDislikeService();
	}
}
