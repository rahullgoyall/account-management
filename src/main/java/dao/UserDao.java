package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.User;
import db.ConnectionProvider;

public class UserDao {
	
	
	public static boolean validate(User u){  
		boolean status=false;  
		try{  
		Connection con= ConnectionProvider.getDBConnection();
		              
		PreparedStatement ps=con.prepareStatement("select * from users where username=? and password=?");  
		  
		ps.setString(1,u.getUsername());  
		ps.setString(2, u.getPassword());  
		              
		ResultSet rs=ps.executeQuery();  
		status=rs.next();  
		              
		}catch(Exception e){}  
		  
		return status;  
		  
		}  

}
