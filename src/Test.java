import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import db.DBUtil;

import databaseobject.Post;
import service.PostService;


public class Test {
	public static void main(String [] args)
	{
		Date date = new Date();
		long now = date.getTime();
		DBUtil.getConnection();
		try {
		PostService ps=new PostService();
		Post p=new Post(1, "2008 summer olympic"," ","http://en.wikipedia.org/wiki/2008_Summer_Olympics", new Timestamp(now));
		Post p2=new Post(2, "2012 summer olympic"," ","http://en.wikipedia.org/wiki/2012_Summer_Olympics", new Timestamp(now));
		Post p3=new Post(3, "2016 summer olympic"," ","http://en.wikipedia.org/wiki/2016_Summer_Olympics", new Timestamp(now));
		ps.save(p);
		ps.save(p2);
		ps.save(p3);
		ArrayList<Post> posts=ps.getAllPost();
		for(Post post:posts)
		{
			System.out.println(post.getUsrid());
			System.out.println(post.getTitle());
			System.out.println(post.getLink());
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
