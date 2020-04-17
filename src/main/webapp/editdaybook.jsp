<%@page import="dao.DayBookDao"%>  
<jsp:useBean id="c" class="bean.DayBook"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
int i=DayBookDao.update(c);
out.print(i);
response.sendRedirect("viewdaybook.jsp");  
%>  