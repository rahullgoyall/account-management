package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Commodity;
import bean.PartyInfo;
import db.ConnectionProvider;

public class CommodityDao {
	
	public static int save(Commodity c){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("insert into commodity(name) values(?)");  
	        ps.setString(1,c.getName());   
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int update(Commodity c){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update commodity set name=? where id=?");  
	        ps.setString(1,c.getName()); 
	        ps.setInt(2,c.getId());
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int delete(Commodity c){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("delete from commodity where id=?");  
	        ps.setInt(1,c.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	  
	    return status;  
	}  
	
	
	public static Commodity getRecordById(int id){  
		Commodity s=null;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from commodity where id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            s=new Commodity();  
	            s.setId(rs.getInt("id"));  
	            s.setName(rs.getString("name"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return s;  
	}  
	
	
	public static List<Commodity> getAllRecords(){  
		List<Commodity> list=new ArrayList<Commodity>();  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from commodity"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Commodity s=new Commodity(); 
	        	s.setId(rs.getInt("id"));  
	            s.setName(rs.getString("name"));
	            list.add(s);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static List<Commodity> getAllRecordsByKey(String key){  
	    List<Commodity> list=new ArrayList<Commodity>();  
	      
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from commodity where name like '%"+key+"%'");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Commodity s=new Commodity();  
	        	s.setId(rs.getInt("id"));  
	            s.setName(rs.getString("name"));  
	            list.add(s);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	} 
	
	public static List<String> getAllCommodityName(){  
		List<String> list=new ArrayList<String>();  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from commodity"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){     	
	            list.add(rs.getString("name"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	

}
