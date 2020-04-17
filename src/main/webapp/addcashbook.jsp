<%@page import="dao.CashBookDao"%>  
<jsp:useBean id="t" class="bean.CashBook"></jsp:useBean>  
<jsp:setProperty property="*" name="t"/>  
  
<%  
int i=CashBookDao.save(t);  
if(i>0){  
session.setAttribute("message", "Record successfully saved!");	
}else{  
session.setAttribute("message", "Sorry, an error occurred!");
} 

response.sendRedirect("addcashbookform.jsp"); 
%>  