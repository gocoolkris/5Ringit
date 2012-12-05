package service;

import java.util.ArrayList;
import java.util.HashSet;

import databaseobject.User;
public class UserService {
	
	//get num of new joined users
	public ArrayList<User> getNewUsers(int num){
		return null;
	}
	
	public boolean saveNewUser(String username, String password) {
		return true;
	}
	
	public User login(String username, String password) {
		
		return null;
	}
	
	public User getUserbyUsername(String username) {
		return null;
	}
	
	public ArrayList<User> getFollowingList(String username){
		return null;
	}
	
	public User getUserbyUsrid(int usrid) {
		
		return null;
	}

	public HashSet<Integer> getFolloweeList(User user) {

		return null;
	}

}
