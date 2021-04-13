package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;


public class DbUtils {

	static Logger logger = Logger.getLogger(DbUtils.class);


	public static String getTestData(String key){
		String value = null;
		try {
			PreparedStatement stmt = ConnectionProvider.getDBConnection().prepareStatement("select * from test_data where d_key=?");
			stmt.setString(1,key);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			value = rs.getString(3);
		}catch (Exception e){
			logger.error("Exception while getting Test Data "+ key, e);
		}
		return value;

	}

	public static String getConfigData(String name){
		String value = null;
		try {
			PreparedStatement stmt = ConnectionProvider.getDBConnection().prepareStatement("select * from configuration where name=? and environment_id=0");
			stmt.setString(1,name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			value = rs.getString(3);
		}catch (Exception e){
			logger.error("Exception while getting Config Data", e);
		}
		return value;

	}
	
	public static String getConfigDataByEnvironmnet(String key,String env_name){
		int env_id = getEnvironmentId(env_name);
		String value = null;
		try {
			PreparedStatement stmt = ConnectionProvider.getDBConnection().prepareStatement("select * from configuration where name=? and environment_id="+env_id);
			stmt.setString(1,key);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			value = rs.getString(3);
		}catch (Exception e){
			logger.error("Exception while getting Config Data", e);
		}
		return value;

	}

	public static HashMap<String,String> getLocatorData(String pageName, String locatorName){
		HashMap<String,String> hashMap = new HashMap<String,String>();
		try {
			PreparedStatement stmt = ConnectionProvider.getDBConnection().prepareStatement("select * from locators where page_name=? and locator_name=?");
			stmt.setString(1,pageName);
			stmt.setString(2,locatorName);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			hashMap.put(rs.getString(4),rs.getString(5));
		}catch (Exception e){
			logger.error("Exception while getting Locator Data", e);
		}
		return hashMap;

	}
	
	public static int getEnvironmentId(String name){
		int id= 0;
		try {
			PreparedStatement stmt = ConnectionProvider.getDBConnection().prepareStatement("select * from environment where name=?");
			stmt.setString(1,name);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			id = rs.getInt(1);
		}catch (Exception e){
			logger.error("Exception while getting Test Data "+ name, e);
		}
		return id;

	}
	
	public static List<String> getActiveSuiteData(String env_name){
		int env_id = getEnvironmentId(env_name.toUpperCase());
		List<String> list=new ArrayList<String>();  
	    try{  
	        PreparedStatement ps=ConnectionProvider.getDBConnection().prepareStatement("select * from suiteinfo where active_status='true' and environment_id="+env_id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){
	            list.add(rs.getString(2));  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;

	}

}
