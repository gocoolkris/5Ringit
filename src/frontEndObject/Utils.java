package frontEndObject;

public class Utils {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(convertDate(100));
		System.out.println(convertDate(100000));
		System.out.println(convertDate(100000000));
		System.out.println(convertDate(999999999));
		System.out.println(convertDate(9999));
	}
	
	public static String convertDate(long relativeTime) {
		long years = relativeTime/1000/3600/24/365;
		if(years >= 1) {
			return years + " year ago";
		}
		long months = relativeTime/1000/3600/24/30;
		if(months >= 1) {
			return months + " month ago";
		}
		long weeks = relativeTime/1000/3600/24/7;
		if(weeks >= 1) {
			return weeks + " weeks ago";
		}
		long days = relativeTime/1000/3600/24;
		if(days >= 1) {
			return days + " days ago";
		}
		long hours = relativeTime/1000/3600;
		if(hours >= 1) {
			return hours + " hours ago";
		}
		long minutes = relativeTime/1000/60;
		if(minutes >=1 ) {
			return minutes+ " minutes ago";
		}
		long seconds = relativeTime/1000;
		if(seconds >= 1) {
			return seconds + " seconds ago";
		}
		long miliseconds = relativeTime;
		return miliseconds + " miliseconds ago";
	}

}
