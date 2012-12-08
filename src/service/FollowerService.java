package service;
import java.sql.ResultSet;
import java.util.ArrayList;

import databaseobject.User;
import db.DBUtil;

public class FollowerService {

	
	public boolean follow(User follower, User followee){
	
		String query = "INSERT INTO FOLLOWERLIST VALUES(%d,%d);";
		try{
			DBUtil.executeQuery(String.format(query,follower.getUsrId(), followee.getUsrId()));
			return true;
		}
		catch(Exception e){}
		return false;
	}
	
	public boolean unfollow(User follower, User followee){
		String query = "DELETE FROM FOLLOWERLIST WHERE FOLLOWERID=%d AND FOLLOWEEID = %d;";
		try{
			DBUtil.executeQuery(String.format(query,follower.getUsrId(), followee.getUsrId()));
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
		String query = "SELECT FOLLOWERID FROM FOLLOWERLIST WHERE FOLLOWEEID=%d";
		try{
			
			ResultSet rs = DBUtil.executeQuery(String.format(query, follower.getUsrId()));
			ArrayList<Integer> followees = new ArrayList<Integer>();
			while(rs.next()){
				int fid = rs.getInt("FOLLOWERID");
				followees.add(fid);
			}
			return followees;
		}
		catch(Exception e){}
		return null;
	}
	
	
	public boolean isfollowing(User follower, User followee){
		
		String query = "SELECT COUNT(*) AS CNT FROM FOLLOWERLIST WHERE FOLLOWER=%d AND FOLLOWEE=%d";
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
}
