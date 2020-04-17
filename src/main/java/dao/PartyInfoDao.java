package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.CashBook;
import bean.DayBook;
import bean.PartyInfo;
import db.ConnectionProvider;

public class PartyInfoDao {
	
	public static int save(PartyInfo p){  
	    int status=0;  
	    try{  
	    	List<PartyInfo> party = getAllRecordsByPartyName(p.getPartyName());
	    	if(party.size() == 0){
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"insert into party_info(partyname,type,address,phone,opening_bal,op_type,brokrage,ledger,gstno,pan) values(?,?,?,?,?,?,?,?,?,?)");  
	        ps.setString(1,p.getPartyName());  
	        ps.setString(2,p.getType());
	        ps.setString(3, p.getAddress());
	        ps.setString(4, p.getPhone());
	        ps.setDouble(5, Double.parseDouble(p.getOpeningBal()));
	        ps.setString(6, p.getOpType());
	        ps.setDouble(7, Double.parseDouble(p.getBrokrage()));
	        ps.setString(8, p.getLedger());
	        ps.setString(9, p.getGstNo());
	        ps.setString(10, p.getPan());
	        status=ps.executeUpdate();  
	    	}
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int update(PartyInfo p){  
	    int status=0;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement(  
	"update party_info set partyname=?,type=?,address=?,phone=?,opening_bal=?,op_type=?,brokrage=?,ledger=?,gstno=?,pan=? where id=?");  
	        ps.setString(1,p.getPartyName());  
	        ps.setString(2,p.getType());
	        ps.setString(3, p.getAddress());
	        ps.setString(4, p.getPhone());
	        ps.setString(5, p.getOpeningBal());
	        ps.setString(6, p.getOpType());
	        ps.setString(7, p.getBrokrage());
	        ps.setString(8, p.getLedger());
	        ps.setString(9, p.getGstNo());
	        ps.setString(10, p.getPan());
	        ps.setInt(11,p.getId());  
	        status=ps.executeUpdate();  
	    }catch(Exception e){System.out.println(e);}  
	    return status;  
	}  
	
	public static int delete(PartyInfo s){  
	    int status=0; 
	    try{  
	    	System.out.println(s.getPartyName());
	    	List<CashBook> cash = CashBookDao.getCashBookByPartyNames(s.getPartyName());
			List<DayBook> day = DayBookDao.getDaybookByFilter(s.getPartyName(), "", "");
			System.out.println(day.size());
			if(cash.size()==0 && day.size() == 0){
				System.out.println("Enter");
		        Connection con=ConnectionProvider.getDBConnection();
		        PreparedStatement ps=con.prepareStatement("delete from party_info where id=?");  
		        ps.setInt(1,s.getId());  
		        status=ps.executeUpdate();
	        }
	    }catch(Exception e){System.out.println(e);}  
	  
	    return status;  
	}  
	
	
	public static PartyInfo getRecordById(int id){  
		PartyInfo s=null;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info where id=?");  
	        ps.setInt(1,id);  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            s=new PartyInfo();  
	            s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));  
	            s.setType(rs.getString("type"));  
	            s.setAddress(rs.getString("address")); 
	            s.setPhone(rs.getString("phone")); 
	            s.setOpeningBal(rs.getString("opening_bal")); 
	            s.setOpType(rs.getString("op_type"));
	            s.setBrokrage(rs.getString("brokrage"));
	            s.setLedger(rs.getString("ledger"));
	            s.setGstNo(rs.getString("gstno"));
	            s.setPan(rs.getString("pan"));
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return s;  
	}  
	
	public static PartyInfo getRecordByName(String name){  
		PartyInfo s=null;  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info where partyname='"+name+"' order by partyname");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            s=new PartyInfo();  
	            s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));  
	            s.setType(rs.getString("type"));  
	            s.setAddress(rs.getString("address")); 
	            s.setPhone(rs.getString("phone")); 
	            s.setOpeningBal(rs.getString("opening_bal")); 
	            s.setOpType(rs.getString("op_type"));
	            s.setBrokrage(rs.getString("brokrage"));
	            s.setLedger(rs.getString("ledger"));
	            s.setGstNo(rs.getString("gstno"));
	            s.setPan(rs.getString("pan"));
	            
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return s;  
	}  
	
	
	public static List<PartyInfo> getAllRecords(){  
		List<PartyInfo> list=new ArrayList<PartyInfo>();  
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info order by partyname"); 
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	PartyInfo s=new PartyInfo(); 
	        	s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));  
	            s.setType(rs.getString("type"));  
	            s.setAddress(rs.getString("address")); 
	            s.setPhone(rs.getString("phone"));  
	            s.setOpeningBal(rs.getString("opening_bal")); 
	            s.setOpType(rs.getString("op_type"));
	            s.setBrokrage(rs.getString("brokrage"));
	            s.setLedger(rs.getString("ledger"));
	            s.setGstNo(rs.getString("gstno"));
	            s.setPan(rs.getString("pan"));
	            list.add(s);
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	}
	
	public static List<PartyInfo> getAllRecordsByKey(String key){  
	    List<PartyInfo> list=new ArrayList<PartyInfo>();  
	      
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info where partyname like '%"+key+"%'");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	PartyInfo s=new PartyInfo();  
	        	s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));  
	            s.setType(rs.getString("type"));  
	            s.setAddress(rs.getString("address")); 
	            s.setPhone(rs.getString("phone")); 
	            s.setOpeningBal(rs.getString("opening_bal")); 
	            s.setOpType(rs.getString("op_type"));
	            s.setBrokrage(rs.getString("brokrage"));
	            s.setLedger(rs.getString("ledger"));
	            s.setGstNo(rs.getString("gstno"));
	            s.setPan(rs.getString("pan"));
	            list.add(s);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	} 
	
	public static List<PartyInfo> getAllRecordsByPartyName(String name){  
	    List<PartyInfo> list=new ArrayList<PartyInfo>();  
	      
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info where partyname='"+name+"'");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	PartyInfo s=new PartyInfo();  
	        	s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));  
	            s.setType(rs.getString("type"));  
	            s.setAddress(rs.getString("address")); 
	            s.setPhone(rs.getString("phone")); 
	            s.setOpeningBal(rs.getString("opening_bal")); 
	            s.setOpType(rs.getString("op_type"));
	            s.setBrokrage(rs.getString("brokrage"));
	            s.setLedger(rs.getString("ledger"));
	            s.setGstNo(rs.getString("gstno"));
	            s.setPan(rs.getString("pan"));
	            list.add(s);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	} 
	
	public static List<PartyInfo> getAllPartyName(){  
	    List<PartyInfo> list=new ArrayList<PartyInfo>();  
	      
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info order by partyname");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	        	PartyInfo s=new PartyInfo();  
	        	s.setId(rs.getInt("id"));  
	            s.setPartyName(rs.getString("partyname"));
	            s.setBrokrage(rs.getString("brokrage"));
	            list.add(s);  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	} 
	
	public static List<String> getAllPartyOnly(){  
	    List<String> list=new ArrayList<String>();  
	      
	    try{  
	        Connection con=ConnectionProvider.getDBConnection();
	        PreparedStatement ps=con.prepareStatement("select * from party_info order by partyname");  
	        ResultSet rs=ps.executeQuery();  
	        while(rs.next()){  
	            list.add(rs.getString("partyname"));  
	        }  
	    }catch(Exception e){System.out.println(e);}  
	    return list;  
	} 
	
	

}
