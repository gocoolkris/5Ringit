package frontEndObject;

public class AtheleteObject {

	private String attName, attValue;
	
	public AtheleteObject(String aName, String value){
		attName = aName;
		attValue = value;
	}
	
	
	public String getKey(){
		return attName;
	}
	
	public String getValue(){
		
		return attValue;
	}
	
	
	
	
}
