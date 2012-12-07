package db;
import java.sql.*;

public class DBUtil {
	private static Connection conn=null;
	private static Statement st=null;

	public static Connection getConnection(){
		if(conn==null){
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				String username="gokul";//TODO
				String password="gocool12";
				String url="jdbc:oracle:thin:@gocool:1521:xe";
				conn=DriverManager.getConnection(url,username,password);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return conn;
	}

	public static  Statement getStatement(){
		if(st==null){
			try{
				Connection c=getConnection();
				st=c.createStatement();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return st;
	}
	
	public static ResultSet executeQuery(String sql){
		ResultSet set=null;
		try{
			Statement state=getStatement();
			set=state.executeQuery(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return set;
	}
	public static int executeUpdateInsertDelete(String sql){
		int i=0;
		try{
			Statement state=getStatement();
			i=state.executeUpdate(sql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return i;
	}
	
	public static int getMaxId(String tableName){//TODO
		StringBuffer sql=new StringBuffer();
		sql.append("select id from "+tableName+" order by id desc;");
		int i=0;
		ResultSet set=executeQuery(sql.toString());
		try{
			while(set.next()){
				i=set.getInt("id");
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return i;
	} 
	public static int getPostMaxId(){//TODO
		StringBuffer sql=new StringBuffer();
		int i=0;
		ResultSet set=executeQuery("select pid from post t1 where not exists (select pid from post where pid > t1.pid) ");
		try{
			while(set.next()){
				i=set.getInt("pid");
				break;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return i;
	} 
}
