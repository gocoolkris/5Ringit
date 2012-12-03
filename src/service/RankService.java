//<<<<<<< HEAD
//package service;
//
//public class RankService {
//
//}
//=======
package service;

import java.util.*;
import databaseobject.Post;
import databaseobject.PostDisLike;
import databaseobject.PostLike;
import databaseobject.User;

public class RankService {

	public ArrayList<Post> rankbyScore(ArrayList<Post> posts)
	{
		Map<Post,Integer> unsortMap =new HashMap<Post,Integer>();
		for(Post p:posts)
		{
			ArrayList<PostLike> pls=ls.getPostLikesforPost(p);
			ArrayList<PostDisLike> pdls=ls.getPostDisLikesforPost(p);
			int score=getScore(pls,pdls);
			unsortMap.put(p,score);
			
		}
		Map<Post,Integer> sortedMap = sortByComparator(unsortMap);
		ArrayList<Post> al=new ArrayList<Post>();
		for(Post p:sortedMap.keySet())
		{
			al.add(p);
		}
		return al;
	}
	
	public ArrayList<Post> rankbyScoreforUser(ArrayList<Post> posts,User user)
	{
		Map<Post,Integer> unsortMap =new HashMap<Post,Integer>();
		for(Post p:posts)
		{
			ArrayList<PostLike> pls=ls.getPostLikesforPost(p);
			ArrayList<PostDisLike> pdls=ls.getPostDisLikesforPost(p);
			int score=getScoreByUser(pls,pdls,user);
			unsortMap.put(p,score);
			
		}
		Map<Post,Integer> sortedMap = sortByComparator(unsortMap);
		ArrayList<Post> al=new ArrayList<Post>();
		for(Post p:sortedMap.keySet())
		{
			al.add(p);
		}
		return al;
	}
	
	public ArrayList<Post> rankbyTime(ArrayList<Post> posts)
	{
		Map<Post,Long> unsortMap =new HashMap<Post,Long>();
		for(Post p:posts)
		{
			ArrayList<PostLike> pls=ls.getPostLikesforPost(p);
			ArrayList<PostDisLike> pdls=ls.getPostDisLikesforPost(p);
			long score=ps.getPublishTime(p);
			unsortMap.put(p,score);
		}
		Map<Post,Integer> sortedMap = sortByComparator(unsortMap);
		ArrayList<Post> al=new ArrayList<Post>();
		for(Post p:sortedMap.keySet())
		{
			al.add(p);
		}
		return al;
	}
	
	public int getScore(ArrayList<PostLike> pls,ArrayList<PostDisLike> pdls)
	{
		return pls.size()-pdls.size();
	}
	
	public int getScoreByUser(ArrayList<PostLike> pls,ArrayList<PostDisLike> pdls,User user)
	{
		return pls.size()-pdls.size();//TODO
	}
	

	
	LikeDislikeService ls;
	PublishService ps;
	public RankService()
	{
		ls=new LikeDislikeService();
		ps=new PublishService();
	}
	private static Map sortByComparator(Map unsortMap) {
		 
		List list = new LinkedList(unsortMap.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return -((Comparable) ((Map.Entry) (o1)).getValue())
                                       .compareTo(((Map.Entry) (o2)).getValue());//TODO DESC
			}
		});
 
		// put sorted list into map again
                //LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
//>>>>>>> Post.save() and getAllPost() is working
