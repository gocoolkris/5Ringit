package service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import databaseobject.User;
import db.DBUtil;
public class UserService {

	//get num of new joined users
	public ArrayList<User> getNewUsers(int num){
		
		ArrayList<User> newUsers = new ArrayList<User>();
		String query = "SELECT * FROM USERLIST WHERE ROWNUM <= %d ORDERY BY USRID DESC";
		ResultSet rs = DBUtil.executeQuery(String.format(query,num));
		try{
			User usr;
			while(rs.next()){
			
				usr = new User();
				usr.setUsrId(rs.getInt("USRID"));
				usr.setUsername(rs.getString("UNAME"));
				usr.setPassword(rs.getString("PASSWRD"));
				newUsers.add(usr);
			}
			return newUsers;
		}
		catch(Exception e){}
		return null;
	}

	public boolean saveNewUser(String username, String password) {

		try{
			int usrid = getUserTableCount();
			if(usrid == -1) return false;  //sanity check;
			
			String queryTemplate = "INSERT INTO USERLIST VALUES(%d,'%s','%s');";
			String query = String.format(queryTemplate, usrid, username,password);
			DBUtil.executeQuery(query);

		}
		catch(Exception e){
			return false;
		}
		return true;
	}

	private int getUserTableCount() throws Exception{

		int count = -1;
		String getCount = "SELECT COUNT(*) as USERCOUNT FROM POST;";
		ResultSet rs = DBUtil.executeQuery(getCount);
		try{
			while(rs.next()){
				count = rs.getInt("USERCOUNT");
				break;
			}
			return count;
		}
		catch(Exception e){throw new Exception();}

	}

	public User login(String username, String password) {
		
		User user = getUserbyUsername(username);
		if(user == null) return null;
		if(user.getPassword().equals(password)) return user;
		return null;
	}

	public User getUserbyUsername(String username) {
		
		User usrobj = new User();
		String query = "SELECT * FROM USERLIST WHERE UNAME = '%s'";
		try{
			ResultSet rs = DBUtil.executeQuery(String.format(query, username));
			while(rs.next()){
				usrobj.setUsrId(rs.getInt("USRID"));
				usrobj.setUsername(rs.getString("UNAME"));
				usrobj.setPassword(rs.getString("PASSWRD"));
				return usrobj;
			}
		}
		catch(Exception e){}
		return null;
	}

	public ArrayList<User> getFollowingList(String username){
		
		ArrayList<User> followingList = new ArrayList<User>();

		int usrid = getUserbyUsername(username).getUsrId();

		String query = "SELECT FOLLOWERID FROM FOLLOWERLIST WHERE FOLLOWEEID=%d";

		ResultSet rs = DBUtil.executeQuery(String.format(query, usrid));
		try{
			User usr;
			while(rs.next()){
				int fid = rs.getInt("FOLLOWERID");
				usr = getUserbyUsrid(fid);
				followingList.add(usr);
			}
			return followingList;
		}
		catch(Exception e){}
		return null;
	}


	public HashSet<Integer> getFolloweeListAsUserIdList(User user){

		HashSet<Integer> followees = new HashSet<Integer>();
		String query = "SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d";
		ResultSet rs = DBUtil.executeQuery(String.format(query,user.getUsrId()));
		try{
			while(rs.next()){
				followees.add(rs.getInt("FOLLOWEEID"));
			}
			return followees;
		}
		catch(Exception e){}
		return null;
	}

	public ArrayList<User> getFolloweeList(User user){
		ArrayList<User> followeesList = new ArrayList<User>();

		int usrid = getUserbyUsername(user.getUsername()).getUsrId();

		String query = "SELECT FOLLOWEEID FROM FOLLOWERLIST WHERE FOLLOWERID=%d";

		ResultSet rs = DBUtil.executeQuery(String.format(query, usrid));
		try{
			User usr;
			while(rs.next()){
				int fid = rs.getInt("FOLLOWEEID");
				usr = getUserbyUsrid(fid);
				followeesList.add(usr);
			}
			return followeesList;
		}
		catch(Exception e){}
		return null;
	}
	

	public User getUserbyUsrid(int usrid) {

		try{
			User usr = new User();
			String query = "SELECT * FROM USERLIST WHERE USRID=%d";
			ResultSet rs = DBUtil.executeQuery(String.format(query, usrid));
			while(rs.next()){
				usr.setUsrId(rs.getInt("usrid"));
				usr.setUsername(rs.getString("uname"));
				usr.setPassword("passwd");
				break;
			}
			return usr;
		}
		catch(Exception e){}
		return null;
	}

	
		
}
