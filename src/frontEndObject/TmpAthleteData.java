package frontEndObject;

import java.util.ArrayList;

public class TmpAthleteData {
	private String key;
	private String value;
	
	public TmpAthleteData(String key, String value) {
		this.key = key;
		this.value = value;
	}
	public String getKey() {
		return this.key;
	}
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public static ArrayList<TmpAthleteData> getFakeAthleteData(int num) {
		 ArrayList<TmpAthleteData> list = new ArrayList<TmpAthleteData>();
		 for(int i=0; i<num; i++) {
			 list.add(new TmpAthleteData("name","peter"));
		 }
		 return list;
	}
	
	
	
	
}
