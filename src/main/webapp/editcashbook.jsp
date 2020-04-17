<%@page import="dao.CashBookDao"%>  
<jsp:useBean id="c" class="bean.CashBook"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
int i=CashBookDao.update(c);  
response.sendRedirect("viewcashbook.jsp");  
%>  