
package databaseobject;

import java.io.Serializable;
public class User implements Serializable{
	private int id;
	private String username;
	private String password;


	public User(){}
	public User(String username,String password){
		this.username=username;
		this.password=password;

	}
	public int getUsrId() {
		return id;
	}
	public void setUsrId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean equals(Object o){
		
		if(!(o instanceof User)) return false;
		User that = (User)o;
		
		if((that.getUsrId() == this.id) && (that.getUsername().equals(username)) && 
				(that.password.equals(password)))
			return true;
		
		return false;
	}
}

