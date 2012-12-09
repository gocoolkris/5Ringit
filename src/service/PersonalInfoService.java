package service;
import java.sql.ResultSet;
import java.util.ArrayList;

import databaseobject.PersonalInfo;
import databaseobject.User;
import db.DBUtil;

public class PersonalInfoService {

	
	public PersonalInfo getPersonalInfobyUsrid(int usrid){
		
		String query = "SELECT * FROM ATHELETEPERSONALINFO WHERE USRID=%d";
		ResultSet rs = DBUtil.executeQuery(String.format(query,usrid));
		PersonalInfo pi = new PersonalInfo();
		try{
			while(rs.next()){
			pi.setAid(rs.getInt("AID"));
			pi.setUsrid(rs.getInt("USRID"));
			pi.setName(rs.getString("NAME"));
			pi.setGender(rs.getString("GENDER"));
			pi.setDob(rs.getString("DOB"));
			pi.setBcity(rs.getString("BIRTHCITY"));
			pi.setBstate(rs.getString("BIRTHSTATE"));
			pi.setCountry(rs.getString("COUNTRY"));
			pi.setSport(rs.getString("SPORT"));
			}
			return pi;
		}
		catch(Exception e){}
		return null;
	}
	
	public PersonalInfo getPersonalInfobyAid(int aid){
		String query = "SELECT * FROM ATHELETEPERSONALINFO WHERE AID=%d";
		ResultSet rs = DBUtil.executeQuery(String.format(query,aid));
		PersonalInfo pi = new PersonalInfo();
		try{
			while(rs.next()){
			pi.setAid(rs.getInt("AID"));
			pi.setUsrid(rs.getInt("USRID"));
			pi.setName(rs.getString("NAME"));
			pi.setGender(rs.getString("GENDER"));
			pi.setDob(rs.getString("DOB"));
			pi.setBcity(rs.getString("BIRTHCITY"));
			pi.setBstate(rs.getString("BIRTHSTATE"));
			pi.setCountry(rs.getString("COUNTRY"));
			pi.setSport(rs.getString("SPORT"));
			break;
			}
			return pi;
		}
		catch(Exception e){}
		return null;
	}
	
	
	public ArrayList<Integer> getPeopleInfoFromSameCountry(PersonalInfo pi){
		
		ArrayList<Integer> alreadyFollowing = new FollowerService().getFollowees(pi.getUsrid());
		
		StringBuffer followees = new StringBuffer();
		followees.append(pi.getUsrid());
		for(int fid : alreadyFollowing){
			followees.append(",");
			followees.append(fid);
		}
		String followingUsrIds = followees.toString();
		
		String query = "SELECT USRID FROM ATHELETEPERSONALINFO WHERE COUNTRY = '" + pi.getCountry() + "'" +
				"AND USRID NOT IN (" + followingUsrIds + ") AND ROWNUM <= 10";
		PersonalInfo ps = null;
		ArrayList<Integer> countryAtheletes = new ArrayList<Integer>();

		try{
			ResultSet rs = DBUtil.executeQuery(query);
			while(rs.next()){
				int usrid = rs.getInt("USRID");
				countryAtheletes.add(usrid);
				}
			return countryAtheletes;
		}
		catch(Exception e){}
		return null;
	}
	
	
	public boolean isAthelete(User usr){

		try{
			String query = "SELECT COUNT(*) AS CNT FROM ATHELETEPERSONALINFO WHERE USRID=%d";
			ResultSet rs = DBUtil.executeQuery(String.format(query,usr.getUsrId()));

			while(rs.next()){
				if(rs.getInt("CNT") == 1) return true;
				else return false;
			}
		}
		catch(Exception e){}
		return false;
	}
	
	
	
}
