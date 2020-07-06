<%@page import="dao.DayBookDao"%>  
<jsp:useBean id="t" class="bean.DayBook"></jsp:useBean>  
<jsp:setProperty property="*" name="t"/>  
  
<%  
int i=DayBookDao.save(t);  
if(i>0){  
session.setAttribute("message", "Record successfully saved!");	
}else{  
session.setAttribute("message", "Sorry, an error occurred!");
} 

response.sendRedirect("adddaybookform.jsp?sname="+t.getSalesAccount().replace("&", "%26")+"&pname="+t.getPurchaseAccount().replace("&", "%26")+"&commodity="+t.getCommodity()+"&sbro="+t.getBrokrageSale()+"&pbro="+t.getBrokragePurchase()+"&truck="+t.getTruck()); 
%>  