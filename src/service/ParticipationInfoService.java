package service;

import java.sql.ResultSet;
import java.util.ArrayList;

import databaseobject.ParticipationInfo;
import databaseobject.User;
import databaseobject.PersonalInfo;
import db.DBUtil;

public class ParticipationInfoService {

	
	public ArrayList<ParticipationInfo> getParticipationInformation(User user){
		
		ArrayList<ParticipationInfo> ptcp_info = new ArrayList<ParticipationInfo>();
		PersonalInfoService perinfoService = new PersonalInfoService();
		PersonalInfo pi = perinfoService.getPersonalInfobyUsrid(user.getUsrId());
		String query = "SELECT * FROM ATHELETEPARTICIPATION WHERE AID=%d";
		try{
			ResultSet rs = DBUtil.executeQuery(String.format(query, pi.getAid()));
			ParticipationInfo partInfo = null;
			while(rs.next()){
				partInfo = new ParticipationInfo();
				partInfo.setAid(rs.getInt("AID"));
				partInfo.setEventid(rs.getInt("EVENTID"));
				partInfo.setAge(rs.getInt("AGE"));
				partInfo.setTeam(rs.getString("TEAM"));
				partInfo.setRnk(rs.getString("RNK"));
				partInfo.setMedalswon(rs.getString("MEDALSWON"));
				ptcp_info.add(partInfo);
			}
			return ptcp_info;
		}
		catch(Exception e){}
		
		return null;
	}
	
	
	
	
	
}
