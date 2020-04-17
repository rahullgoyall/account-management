<%@page import="dao.CashBookDao"%>
<%@page import="dao.DayBookDao"%>
<%@page import="dao.PartyInfoDao"%>  
<jsp:useBean id="c" class="bean.PartyInfo"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
String old=request.getParameter("pname");
DayBookDao.updatePartyName(old, c.getPartyName());
CashBookDao.updatePartyName(old, c.getPartyName());
int i=PartyInfoDao.update(c);  
response.sendRedirect("viewpartyinfo.jsp");  
%>  