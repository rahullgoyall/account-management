package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.BrokrageBill;
import bean.CashBook;
import bean.DayBook;
import bean.FinancialYear;
import db.ConnectionProvider;

public class CashBookDao {
	
	public static int save(CashBook p){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"insert into cashbook(date,party_name,amount,status,remarks,p_mode) values(?,?,?,?,?,?)");  
	        String date = p.getDate();
	        ps.setDate(1,getDate(date));  
	        ps.setString(2,p.getPartyName());
	        ps.setDouble(3, Double.parseDouble(p.getAmount()));
	        ps.setString(4, p.getStatus());
	        ps.setString(5, p.getRemarks());
	        ps.setString(6, p.getPaymentMode());
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int update(CashBook p){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update cashbook set date=?,party_name=?,amount=?,status=?,remarks=?,p_mode=? where id=?");  
	        String date = p.getDate();
	        ps.setDate(1,getDate(date));  
	        ps.setString(2,p.getPartyName());
	        ps.setString(3, p.getAmount());
	        ps.setString(4, p.getStatus());
	        ps.setString(5, p.getRemarks());
	        ps.setString(6, p.getPaymentMode());
	        ps.setInt(7,p.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	} 
	
	public static int updatePartyName(String oldParty, String newParty){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update cashbook set party_name=? where party_name=?");  
	        
	        ps.setString(1,newParty);
	        ps.setString(2, oldParty);
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}    
	
	public static int delete(CashBook s){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("delete from cashbook where id=?");  
	        ps.setInt(1,s.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	  
	    return status;  
	}  
	
	
	public static CashBook getRecordById(int id){  
		CashBook d=null;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from cashbook where id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            d=new CashBook();  
	            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
	        	String date = formatter.format(rs.getDate("date"));
	        	
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setPartyName(rs.getString("party_name"));
	        	d.setAmount(rs.getString("amount"));
	        	d.setStatus(rs.getString("status"));
	        	d.setRemarks(rs.getString("remarks"));
	        	d.setPaymentMode(rs.getString("p_mode"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return d;  
	}  
	
	
	public static List<CashBook> getAllRecords(){  
		List<CashBook> list=new ArrayList<CashBook>();  
		FinancialYear f = FinancialYearDao.getRecordByStatus();
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from cashbook where date >= '"+f.getFrom()+"' and date <= '"+f.getTo()+"'"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));
	        	
	        	CashBook d=new CashBook(); 
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setPartyName(rs.getString("party_name"));
	        	d.setAmount(rs.getString("amount"));
	        	d.setStatus(rs.getString("status"));
	        	d.setRemarks(rs.getString("remarks"));
	        	d.setPaymentMode(rs.getString("p_mode"));
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	
	public static List<CashBook> getCashBookByFilter(String dateFrom, String dateTo){  
		List<CashBook> list=new ArrayList<CashBook>();  
	    try{  
	    	PreparedStatement ps;
	        Connection con=ConnectionProvider.getDBConnection();
	       
	        	ps=con.prepareStatement("select * from cashbook where date >= '"+dateFrom+"' and date <= '"+dateTo+"' ORDER BY date ASC"); 
	        
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));    	
	        	CashBook d=new CashBook(); 
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setPartyName(rs.getString("party_name"));
	        	d.setAmount(rs.getString("amount"));
	        	d.setStatus(rs.getString("status"));
	        	d.setRemarks(rs.getString("remarks"));
	        	d.setPaymentMode(rs.getString("p_mode"));
	     
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	
	public static List<CashBook> getCashBookByPartyNames(String partyName){  
		List<CashBook> list=new ArrayList<CashBook>();  
		FinancialYear f = FinancialYearDao.getRecordByStatus();
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from cashbook where party_name='"+partyName+"' and date >= '"+f.getFrom()+"' and date <= '"+f.getTo()+"'"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));
	        	
	        	CashBook d=new CashBook(); 
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setPartyName(rs.getString("party_name"));
	        	d.setAmount(rs.getString("amount"));
	        	d.setStatus(rs.getString("status"));
	        	d.setRemarks(rs.getString("remarks"));
	        	d.setPaymentMode(rs.getString("p_mode"));
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	
	public static Date getDate(String d){
		Date sqlDate = null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt;
		try {
			dt = simpleDateFormat.parse(d);
			sqlDate = new java.sql.Date(dt.getTime()); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlDate;
		
	}

}
