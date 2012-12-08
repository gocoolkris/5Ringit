package databaseobject;

import java.io.Serializable;

public class PersonalInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	int aid, usrid;
	String name, Gender, dob, bcity, bstate, country, sport;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getUsrid() {
		return usrid;
	}
	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBcity() {
		return bcity;
	}
	public void setBcity(String bcity) {
		this.bcity = bcity;
	}
	public String getBstate() {
		return bstate;
	}
	public void setBstate(String bstate) {
		this.bstate = bstate;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getSport() {
		return sport;
	}
	public void setSport(String sport) {
		this.sport = sport;
	}
	
	
	@Override
	public boolean equals(Object o){
	
		if(!(o instanceof PersonalInfo)) return false;
		PersonalInfo that = (PersonalInfo) o;
		if(that.getAid() == this.aid && that.getUsrid() == this.usrid) return true;
		return false;
	}
	
	
}
