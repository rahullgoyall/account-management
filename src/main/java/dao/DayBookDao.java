package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import bean.BrokrageBill;
import bean.DayBook;
import bean.PartyInfo;
import db.ConnectionProvider;

public class DayBookDao {
	
	public static int save(DayBook p){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"insert into daybook(date,sales_ac,comodity,brokrage_sale,weight,rate,purchase_ac,brokrage_purchase,delivery_date,expiry_date,truck,remarks) values(?,?,?,?,?,?,?,?,?,?,?,?)");  
	        String date = p.getDate();
	        String deliveryDate = p.getDeliverDate();
	        String expiryDate = p.getExpiryDate();
	        ps.setDate(1,getDate(date));  
	        ps.setString(2,p.getSalesAccount().split(",")[0]);
	        ps.setString(3, p.getCommodity());
	        ps.setString(4, p.getBrokrageSale());
	        ps.setString(5, p.getWeight());
	        ps.setString(6, p.getRate());
	        ps.setString(7, p.getPurchaseAccount().split(",")[0]);
	        ps.setString(8, p.getBrokragePurchase());
	        ps.setDate(9, getDate(deliveryDate));
	        ps.setDate(10, getDate(expiryDate));
	        ps.setString(11, p.getTruck());
	        ps.setString(12, p.getRemarks());
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int update(DayBook p){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update daybook set date=?,sales_ac=?,comodity=?,brokrage_sale=?,weight=?,rate=?,purchase_ac=?,brokrage_purchase=?,delivery_date=?,expiry_date=?,truck=?,remarks=? where id=?");  
	        String date = p.getDate();
	        String deliveryDate = p.getDeliverDate();
	        String expiryDate = p.getExpiryDate();
	  
	        ps.setDate(1,getDate(date));  
	        ps.setString(2,p.getSalesAccount().split(",")[0]);
	        ps.setString(3, p.getCommodity());
	        ps.setString(4, p.getBrokrageSale());
	        ps.setString(5, p.getWeight());
	        ps.setString(6, p.getRate());
	        ps.setString(7, p.getPurchaseAccount().split(",")[0]);
	        ps.setString(8, p.getBrokragePurchase());
	        ps.setDate(9, getDate(deliveryDate));
	        ps.setDate(10, getDate(expiryDate));
	        ps.setString(11, p.getTruck());
	        ps.setString(12, p.getRemarks());
	        ps.setInt(13,p.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	} 
	
	public static int updatePartyName(String oldParty, String newParty){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update daybook set sales_ac=? where sales_ac=?");  
	        PreparedStatement ps2=con.prepareStatement(  
	        		"update daybook set purchase_ac=? where purchase_ac=?");  
	        ps.setString(1,newParty);  
	        ps.setString(2,oldParty);
	        ps2.setString(1,newParty);  
	        ps2.setString(2,oldParty);
	       
	        status=ps.executeUpdate();  
	        status=ps2.executeUpdate();
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	} 
	
	public static int delete(DayBook s){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("delete from daybook where id=?");  
	        ps.setInt(1,s.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	  
	    return status;  
	}  
	
	
	public static DayBook getRecordById(int id){  
		DayBook d=null;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from daybook where id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            d=new DayBook();  
	            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
	        	String date = formatter.format(rs.getDate("date"));
	        	String deliveryDate = formatter.format(rs.getDate("delivery_date"));
	        	String expiryDate = formatter.format(rs.getDate("expiry_date"));
	        	
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setSalesAccount(rs.getString("sales_ac"));
	        	d.setCommodity(rs.getString("comodity"));
	        	d.setBrokrageSale(rs.getString("brokrage_sale"));
	        	d.setWeight(rs.getString("weight"));
	        	d.setRate(rs.getString("rate"));
	        	d.setPurchaseAccount(rs.getString("purchase_ac"));
	        	d.setBrokragePurchase(rs.getString("brokrage_purchase"));
	        	d.setDeliverDate(deliveryDate);
	        	d.setExpiryDate(expiryDate);
	        	d.setTruck(rs.getString("truck"));
	        	d.setRemarks(rs.getString("remarks"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return d;  
	}  
	
	
	public static List<DayBook> getAllRecords(){  
		List<DayBook> list=new ArrayList<DayBook>();  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from daybook"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));
	        	String deliveryDate = formatter.format(rs.getDate("delivery_date"));
	        	String expiryDate = formatter.format(rs.getDate("expiry_date"));
	        	
	        	DayBook d=new DayBook(); 
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setSalesAccount(rs.getString("sales_ac"));
	        	d.setCommodity(rs.getString("comodity"));
	        	d.setBrokrageSale(rs.getString("brokrage_sale"));
	        	d.setWeight(rs.getString("weight"));
	        	d.setRate(rs.getString("rate"));
	        	d.setPurchaseAccount(rs.getString("purchase_ac"));
	        	d.setBrokragePurchase(rs.getString("brokrage_purchase"));
	        	d.setDeliverDate(deliveryDate);
	        	d.setExpiryDate(expiryDate);
	        	d.setTruck(rs.getString("truck"));
	        	d.setRemarks(rs.getString("remarks"));
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static List<DayBook> getDayBookByDates(String dateFrom,String dateTo){  
		List<DayBook> list=new ArrayList<DayBook>();  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from daybook where date >= '"+dateFrom+"' and date <= '"+dateTo+"' ORDER BY date ASC"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));
	        	String deliveryDate = formatter.format(rs.getDate("delivery_date"));
	        	String expiryDate = formatter.format(rs.getDate("expiry_date"));
	        	
	        	DayBook d=new DayBook(); 
	        	d.setId(rs.getInt("id"));
	        	d.setDate(date);  
	        	d.setSalesAccount(rs.getString("sales_ac"));
	        	d.setCommodity(rs.getString("comodity"));
	        	d.setBrokrageSale(rs.getString("brokrage_sale"));
	        	d.setWeight(rs.getString("weight"));
	        	d.setRate(rs.getString("rate"));
	        	d.setPurchaseAccount(rs.getString("purchase_ac"));
	        	d.setBrokragePurchase(rs.getString("brokrage_purchase"));
	        	d.setDeliverDate(deliveryDate);
	        	d.setExpiryDate(expiryDate);
	        	d.setTruck(rs.getString("truck"));
	        	d.setRemarks(rs.getString("remarks"));
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	
	public static List<DayBook> getDaybookByFilter(String lName, String dateFrom, String dateTo){  
		List<DayBook> list=new ArrayList<DayBook>();  
	    try{  
	    	PreparedStatement ps;
	        Connection con=ConnectionProvider.getDBConnection();
	        if(!dateFrom.isEmpty() && !dateTo.isEmpty()){
	        	ps=con.prepareStatement("select * from daybook where date >= '"+dateFrom+"' and date <= '"+dateTo+"' and (sales_ac = '"+lName+"' or purchase_ac = '"+lName+"') ORDER BY date ASC"); 
	        }else{
	        	ps=con.prepareStatement("select * from daybook where sales_ac = '"+lName+"' or purchase_ac = '"+lName+"'"); 
	        }
	        
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	Format formatter = new SimpleDateFormat("dd/MM/yyyy");
	        	String date = formatter.format(rs.getDate("date"));
	        	String deliveryDate = formatter.format(rs.getDate("delivery_date"));
	        	String expiryDate = formatter.format(rs.getDate("expiry_date"));
	        	
	        	DayBook d=new DayBook(); 
	        	d.setDate(date);  
	        	d.setSalesAccount(rs.getString("sales_ac"));
	        	d.setCommodity(rs.getString("comodity"));
	        	d.setBrokrageSale(rs.getString("brokrage_sale"));
	        	d.setWeight(rs.getString("weight"));
	        	d.setRate(rs.getString("rate"));
	        	d.setPurchaseAccount(rs.getString("purchase_ac"));
	        	d.setBrokragePurchase(rs.getString("brokrage_purchase"));
	        	d.setDeliverDate(deliveryDate);
	        	d.setExpiryDate(expiryDate);
	        	d.setTruck(rs.getString("truck"));
	        	d.setRemarks(rs.getString("remarks"));
	            list.add(d);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static List<BrokrageBill> getBrokrageBillByFilter(String lName, String dateFrom, String dateTo){ 
		List<DayBook> filterDaybook = getDaybookByFilter(lName,dateFrom,dateTo);
		List<BrokrageBill> brokrageBills=new ArrayList<BrokrageBill>();
		
		for(DayBook fd: filterDaybook){
			BrokrageBill bb = new BrokrageBill();
			bb.setBillDate(fd.getDate());
			if(fd.getSalesAccount().equals(lName)){
				bb.setParticulars(fd.getPurchaseAccount());
			}else{bb.setParticulars(fd.getSalesAccount());}
			
			if(fd.getSalesAccount().equals(lName)){
				bb.setSalesPurchase("Sales");
			}else{bb.setSalesPurchase("Purchase");}
			
			bb.setCommodity(fd.getCommodity());
			bb.setWeight(fd.getWeight());
			bb.setRate(fd.getRate());
			
			if(fd.getSalesAccount().equals(lName)){
				bb.setBrokrage(fd.getBrokrageSale());
			}else{bb.setBrokrage(fd.getBrokragePurchase());}
			
			bb.setDeliverDate(fd.getDeliverDate());
			bb.setExpiryDate(fd.getExpiryDate());
			bb.setTruck(fd.getTruck());
			brokrageBills.add(bb);
		}
		
	    return brokrageBills;  
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
