import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBUtil;

import databaseobject.Post;
import databaseobject.User;
import service.FollowerService;
import service.PostService;


public class Test {
	public static void main(String [] args)
	{
		DBUtil.getConnection();
		FollowerService followerService=new FollowerService();
		User A=new User("A","A");
		User B=new User("B","B");
		User C=new User("C","C");
		A.setUsrId(500000);
		B.setUsrId(600000);
		C.setUsrId(700000);
//		followerService.follow(A, B);
//		followerService.follow(A, C);
//		followerService.follow(C, B);
//		ArrayList<User> list=followerService.getFolloweeList(A);
//		for(User u:list)
//		{
//			System.out.println(u.getUsername());
//			System.out.println(u.getUsrId());
//		}
		ArrayList<User> list=followerService.getFollowerList(B);
		for(User u:list)
		{
			System.out.println(u.getUsername());
			System.out.println(u.getUsrId());
		}
		
		
//		PostService ps=new PostService();
//		Post p=new Post("2008 summer olympic"," ","http://en.wikipedia.org/wiki/2008_Summer_Olympics");
//		Post p2=new Post("2012 summer olympic"," ","http://en.wikipedia.org/wiki/2012_Summer_Olympics");
//		Post p3=new Post("2016 summer olympic"," ","http://en.wikipedia.org/wiki/2016_Summer_Olympics");
//		ps.save(p);
//		ps.save(p2);
//		ps.save(p3);
//		ArrayList<Post> posts=ps.getAllPost();
//		for(Post post:posts)
//		{
//			System.out.println(post.getUsrId());
//			System.out.println(post.getTitle());
//			System.out.println(post.getLink());
//		}
	}
	
	
//	public static void main(String[] args){
//		try{
//		Connection c = DBUtil.getConnection();
//		String sql = "select * from winterolympics";
//		DBUtil.executeQuery(sql);
//		
//		}
//		catch(Exception e){
//			System.out.println("could not connect");
//			e.printStackTrace();
//		}
//		
//	}
	
}
