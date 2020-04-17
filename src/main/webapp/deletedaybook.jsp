<%@page import="dao.DayBookDao"%>  
<jsp:useBean id="c" class="bean.DayBook"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
DayBookDao.delete(c);  
response.sendRedirect("viewdaybook.jsp");  
%> 