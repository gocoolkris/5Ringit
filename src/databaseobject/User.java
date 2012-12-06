
package databaseobject;

import java.io.Serializable;
public class User implements Serializable{
	private int usrid;
	private String username;
	private String password;


	public User(){}
	public User(String username,String password){
		this.username=username;
		this.password=password;

	}
	public int getUsrid() {
		return usrid;
	}
	public void setUsrid(int usrid) {
		this.usrid = usrid;
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

}

