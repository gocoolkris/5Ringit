package databaseobject;

public class ParticipationInfo {

	int aid, eventid, age;
	String team, rnk, medalswon;
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public int getEventid() {
		return eventid;
	}
	public void setEventid(int eventid) {
		this.eventid = eventid;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getRnk() {
		return rnk;
	}
	public void setRnk(String rnk) {
		this.rnk = rnk;
	}
	public String getMedalswon() {
		return medalswon;
	}
	public void setMedalswon(String medalswon) {
		this.medalswon = medalswon;
	}
}
