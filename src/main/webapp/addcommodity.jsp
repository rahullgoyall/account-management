<%@page import="dao.CommodityDao"%>  
<jsp:useBean id="c" class="bean.Commodity"></jsp:useBean>  
<jsp:setProperty property="*" name="c"/>  
  
<%  
int i=CommodityDao.save(c);  
if(i>0){  
session.setAttribute("message", "Record successfully saved!");	
}else{  
session.setAttribute("message", "Sorry, an error occurred!");
} 

response.sendRedirect("addcommodityform.jsp"); 
%>  