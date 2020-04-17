<%@page import="dao.CashBookDao"%>  
<jsp:useBean id="t" class="bean.CashBook"></jsp:useBean>  
<jsp:setProperty property="*" name="t"/>  
<%  
CashBookDao.delete(t);  
response.sendRedirect("viewcashbook.jsp");  
%> 