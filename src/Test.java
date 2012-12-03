import java.util.ArrayList;

import db.DBUtil;

import databaseobject.Post;
import service.PostService;


public class Test {
	public static void main(String [] args)
	{
		DBUtil.getConnection();
		PostService ps=new PostService();
		Post p=new Post("2008 summer olympic"," ","http://en.wikipedia.org/wiki/2008_Summer_Olympics", "david");
		Post p2=new Post("2012 summer olympic"," ","http://en.wikipedia.org/wiki/2012_Summer_Olympics", "gokul");
		Post p3=new Post("2016 summer olympic"," ","http://en.wikipedia.org/wiki/2016_Summer_Olympics", "yang");
		ps.save(p);
		ps.save(p2);
		ps.save(p3);
		ArrayList<Post> posts=ps.getAllPost();
		for(Post post:posts)
		{
			System.out.println(post.getId());
			System.out.println(post.getTitle());
			System.out.println(post.getLink());
		}
	}
}
