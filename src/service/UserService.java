package service;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import databaseobject.User;
import databaseobject.ParticipationInfo;
import db.DBUtil;
import databaseobject.PersonalInfo;
import databaseobject.Post;
import databaseobject.Event;
import frontEndObject.AtheleteObject;


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
			int usrid = getNewUsrId();
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

	private int getNewUsrId() throws Exception{

		int newUsrid = -1;
		String getCount = "SELECT UID FROM USERLIST ORDER BY UID DESC;";
		ResultSet rs = DBUtil.executeQuery(getCount);
		try{
			while(rs.next()){
				newUsrid = rs.getInt("UID") + 1;
				break;
			}
			return newUsrid;
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

	
	/**
	 * recommend followees based on two things
	 * 1 - recommend people from the same country
	 * 2 - recommend people who participated in an event and are from the same team.
	 * @param user
	 * @return
	 */
	public ArrayList<User> recommendFollowees(User user){
		
		return recommendCountryAtheletes(user);
	}
	
	
	
	
	
	private ArrayList<User> recommendTeamAtheletes(User user){
		
		

		return null;
	}
	
	
	private ArrayList<User> recommendCountryAtheletes(User user){
		
		PersonalInfoService pis = new PersonalInfoService();
		PersonalInfo pi = pis.getPersonalInfobyUsrid(user.getUsrId());
		ArrayList<Integer> UsrIdsOfCountryAtheletes = pis.getPeopleInfoFromSameCountry(pi);
		ArrayList<User> followees = new ArrayList<User>();
		int count = 0;
		
		for(int i = 0; i < UsrIdsOfCountryAtheletes.size();++i){
			int fid = UsrIdsOfCountryAtheletes.get(i);
			User followee = this.getUserbyUsrid(fid);
			if(count == 10) break;
			if(!follow(user, followee)){
				followees.add(followee);
				count++;
			}
		}
		return followees;
	}
	
	public boolean follow(User follower, User  followee){
		
		FollowerService fs = new FollowerService();
		return fs.follow(follower, followee);
	}
	
	
	public boolean isfollowing(User follower, User followee){
		
		FollowerService fs = new FollowerService();
		return fs.isfollowing(follower, followee);
	}
	
	
	public boolean unfollow(User follower, User followee){
		FollowerService fs = new FollowerService();
		return fs.unfollow(follower, followee);
	}
	
	public ArrayList<Post> getLikedPostsByUser(User usr){
		try{
			ArrayList<Post> posts = new ArrayList<Post>();
			LikeDislikeService lds = new LikeDislikeService();
			PostService ps = new PostService();
			ArrayList<Integer> pids = lds.getLikedPostIdsByUser(usr);
			for(int pid: pids){
				Post pst = ps.getPostById(pid);
				posts.add(pst);
			}
			return posts;
		}
		catch(Exception e){}
		return null;
	}	
	
	
	public ArrayList<Post> getDisLikedPostsByUser(User usr){
		
		try{
			ArrayList<Post> posts = new ArrayList<Post>();
			LikeDislikeService lds = new LikeDislikeService();
			PostService ps = new PostService();
			ArrayList<Integer> pids = lds.getDisLikedPostIdsByUser(usr);
			for(int pid: pids){
				Post pst = ps.getPostById(pid);
				posts.add(pst);
			}
			return posts;
		}
		catch(Exception e){}
		return null;
		
	}
	
	
	public boolean isAthelete(User usr){
		
		PersonalInfoService pis = new PersonalInfoService();
		return pis.isAthelete(usr);
	}
	
	
	
	public ArrayList<Post> getPostsCommented(User usr){
		
		try{
			CommentService cs = new CommentService();
			PostService ps = new PostService();
			ArrayList<Integer> pids = cs.getPostIdsByUser(usr);
			ArrayList<Post> posts = new ArrayList<Post>();
			for(int p : pids){
				
				Post pst = ps.getPostById(p);
				posts.add(pst);
			}
			return posts;
		}
		catch(Exception e){}
		return null;
	}
	
	public ArrayList<AtheleteObject> getProfileInformation(User usr){
		
		PersonalInfoService pis = new PersonalInfoService();
		
		PersonalInfo pi = pis.getPersonalInfobyUsrid(usr.getUsrId());
		
		ArrayList<AtheleteObject> profileInfo = addPersonalData(pi);
		if(profileInfo != null){
			profileInfo.addAll(participationInfo(usr));
		}
		return profileInfo;
	}
	
	private ArrayList<AtheleteObject> participationInfo(User usr){
		
		ArrayList<AtheleteObject> participations = new ArrayList<AtheleteObject>();
		
		ParticipationInfoService pis = new ParticipationInfoService();
		ArrayList<ParticipationInfo> pt_info = pis.getParticipationInformation(usr);
		EventService es = new EventService();
		AtheleteObject ao = null;
		for(ParticipationInfo pi : pt_info){
			Event ev = es.getEventInfo(pi.getEventid());
			ao  = new AtheleteObject("EventName",ev.getEventname());
			participations.add(ao);
			ao = new AtheleteObject("EventCity", ev.getCity());
			participations.add(ao);
			ao = new AtheleteObject("EventDate", ev.getSeason() + ev.getYear());
			participations.add(ao);
			ao = new AtheleteObject("TeamParticipated", pi.getTeam());
			participations.add(ao);
			ao = new AtheleteObject("Rank", pi.getRnk());
			participations.add(ao);	
			ao = new AtheleteObject("MedalsWon",pi.getMedalswon());
			participations.add(ao);
		}
		return participations;
	}
	
	
	
	private ArrayList<AtheleteObject> addPersonalData(PersonalInfo pi){
		
		ArrayList<AtheleteObject> personalDetails = new ArrayList<AtheleteObject>();
		
		AtheleteObject ao = new AtheleteObject("Name",pi.getName());
		personalDetails.add(ao);
		ao = new AtheleteObject("DateOfBirth",pi.getDob());
		personalDetails.add(ao);
		ao = new AtheleteObject("Gender",pi.getGender());
		personalDetails.add(ao);
		ao = new AtheleteObject("Place",pi.getBcity() + "," + pi.getBstate());
		personalDetails.add(ao);
		ao = new AtheleteObject("Country",pi.getCountry());
		personalDetails.add(ao);
		
		return personalDetails;
	}
	
}
