<%@page import="dao.CommodityDao"%>  
<jsp:useBean id="c" class="bean.Commodity"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
int i=CommodityDao.update(c);  
response.sendRedirect("viewcommodityinfo.jsp");  
%>  