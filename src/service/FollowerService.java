package service;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import databaseobject.User;
import db.DBUtil;

public class FollowerService {

	
	public boolean follow(User follower, User followee){
	
		String query = "INSERT INTO FOLLOWERLIST VALUES(%d,%d)";
		try{
			DBUtil.executeUpdateInsertDelete(String.format(query,follower.getUsrId(), followee.getUsrId()));
			return true;
		}
		catch(Exception e){}
		return false;
	}
	
	public boolean unfollow(User follower, User followee){
		String query = "DELETE FROM FOLLOWERLIST WHERE FOLLOWERID=%d AND FOLLOWEEID = %d";
		try{
			DBUtil.executeUpdateInsertDelete(String.format(query,follower.getUsrId(), followee.getUsrId()));
			return true;
		}
		catch(Exception e){}
		return false;
	}
	
	public ArrayList<Integer>  getFollowers(User followee){
	
		String query = "SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d";
		try{
			
			ResultSet rs = DBUtil.executeQuery(String.format(query, followee.getUsrId()));
			ArrayList<Integer> followers = new ArrayList<Integer>();
			while(rs.next()){
				int fid = rs.getInt("FOLLOWEEID");
				followers.add(fid);
			}
			return followers;
		}
		catch(Exception e){}
		return null;
	}
	
	public ArrayList<Integer> getFollowees(User follower){
		String query = "SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d";
		try{
			
			ResultSet rs = DBUtil.executeQuery(String.format(query, follower.getUsrId()));
			ArrayList<Integer> followees = new ArrayList<Integer>();
			while(rs.next()){
				int fid = rs.getInt("FOLLOWEEID");
				followees.add(fid);
			}
			return followees;
		}
		catch(Exception e){}
		return null;
	}
public ArrayList<User> getFollowerList(User user){
		
		ArrayList<User> followingList = new ArrayList<User>();

		int usrid = us.getUserbyUsername(user.getUsername()).getUsrId();

		String query = "SELECT  * FROM USERLIST WHERE USRID IN(SELECT FOLLOWERID FROM FOLLOWERLIST WHERE FOLLOWEEID=%d)";

		ResultSet rs = DBUtil.executeQuery(String.format(query, usrid));
		try{
			while(rs.next()){
				User usr=new User();
				usr.setUsrId(rs.getInt("usrid"));
				usr.setUsername(rs.getString("uname"));
				usr.setPassword(rs.getString("passwrd"));
				followingList.add(usr);
			}
			return followingList;
		}
		catch(Exception e){}
		return null;
	}

	
	public ArrayList<User> getFolloweeList(User user){
		ArrayList<User> followeesList = new ArrayList<User>();

		int usrid = us.getUserbyUsername(user.getUsername()).getUsrId();

		String query = "SELECT  * FROM USERLIST WHERE USRID IN(SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d)";

		ResultSet rs = DBUtil.executeQuery(String.format(query, usrid));

		try{
			
			while(rs.next()){
				User usr=new User();
				usr.setUsrId(rs.getInt("usrid"));
				usr.setUsername(rs.getString("uname"));
				usr.setPassword(rs.getString("passwrd"));
				followeesList.add(usr);
			}
			return followeesList;
		}
		catch(Exception e){}
		return null;
		
		
		
	}
	
	
	
	
	public ArrayList<Integer> getFollowees(int followerid){
		
		String query = "SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d";
		try{
			
			ResultSet rs = DBUtil.executeQuery(String.format(query, followerid));
			ArrayList<Integer> followees = new ArrayList<Integer>();
			while(rs.next()){
				int fid = rs.getInt("FOLLOWEEID");
				followees.add(fid);
			}
			return followees;
		}
		catch(Exception e){}
		return null;
		
	}
	
	
	public boolean isfollowing(User follower, User followee){
		
		String query = "SELECT COUNT(*) AS CNT FROM FOLLOWERLIST WHERE FOLLOWERID=%d AND FOLLOWEEID=%d";
		try{
			ResultSet rs = DBUtil.executeQuery(String.format(query,follower.getUsrId(),followee.getUsrId()));
			while(rs.next()){
				int count = rs.getInt("CNT");
				if(count == 1) return true;
				return false;
			}
		}
		catch(Exception e){}
		return false;
	}
	UserService us=new UserService();
}
