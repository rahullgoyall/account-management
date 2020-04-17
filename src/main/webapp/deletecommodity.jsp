<%@page import="dao.CommodityDao"%>  
<jsp:useBean id="c" class="bean.Commodity"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
<%  
CommodityDao.delete(c);  
response.sendRedirect("viewcommodityinfo.jsp");  
%> 