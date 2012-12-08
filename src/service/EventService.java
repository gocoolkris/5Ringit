package service;

import java.sql.ResultSet;

import databaseobject.*;
import db.DBUtil;

public class EventService {

	
	public Event getEventInfo(int eventid){
		
		Event evnt = new Event();
		try{
			String query = "SELECT * FROM EVENT WHERE EID=%d";
			
			ResultSet rs = DBUtil.executeQuery(String.format(query,eventid));
			while(rs.next()){
				evnt.setEid(rs.getInt("EID"));
				evnt.setYear(rs.getInt("YEAR"));
				evnt.setSeason(rs.getString("SEASON"));
				evnt.setCity(rs.getString("CITY"));
				evnt.setEventname(rs.getString("EVENTNAME"));
			}
			return evnt;
		}
		catch(Exception e){}
		return null;
	}
	
	
	
}
