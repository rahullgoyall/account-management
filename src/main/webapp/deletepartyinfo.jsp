<%@page import="dao.PartyInfoDao,dao.CashBookDao,dao.DayBookDao,bean.*,java.util.*"%>  
<jsp:useBean id="t" class="bean.PartyInfo"></jsp:useBean>  
<jsp:setProperty property="*" name="t"/>  
<%  
int status = PartyInfoDao.delete(t);  
out.print(status);
if(status == 0){ 
List<CashBook> cash = CashBookDao.getCashBookByPartyNames(t.getPartyName());
List<DayBook> day = DayBookDao.getDaybookByFilter(t.getPartyName(), "", "");		
request.setAttribute("delete_failed", "Party Name not deleted, "+cash.size()+" CashBook and "+day.size()+" DayBook entries are present");
RequestDispatcher rd = request.getRequestDispatcher("/viewpartyinfo.jsp");
rd.forward(request, response);
}else{
	request.setAttribute("delete_success", "Party Name deleted Successfully");
	RequestDispatcher rd = request.getRequestDispatcher("/viewpartyinfo.jsp");
	rd.forward(request, response);
}

%> 