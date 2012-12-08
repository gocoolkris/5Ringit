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
	
	public ArrayList<Integer>  getFollowers(User followee){
	
		String query = "SELECT FOLLOWEEID FROM FOLLOWER WHERE FOLLOWERID=%d";
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
		String query = "SELECT FOLLOWERID FROM FOLLOWER WHERE FOLLOWEEID=%d";
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
}
