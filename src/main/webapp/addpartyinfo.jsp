<%@page import="dao.PartyInfoDao,bean.*,java.util.*"%>
<jsp:useBean id="t" class="bean.PartyInfo"></jsp:useBean>
<jsp:setProperty property="*" name="t" />

<%  
int i=PartyInfoDao.save(t);  
if(i>0){  
	session.setAttribute("message", "Record successfully saved!");
}else{ 
	List<PartyInfo> party = PartyInfoDao.getAllRecordsByKey(t.getPartyName());
	if(party.size()==0){
		session.setAttribute("message", "Sorry, an error occurred!");
	}else{
		session.setAttribute("message", "Duplicate Name found");
	}
} 

response.sendRedirect("addpartyinfoform.jsp"); 
%>
