<%@page import="dao.UserDao"%>  
<jsp:useBean id="u" class="bean.User"/>  
  
<jsp:setProperty property="*" name="u"/>  
  
<%  
boolean status=UserDao.validate(u);  
if(status){  
session.setAttribute("login_session","true");  
request.setAttribute("login_msg", "You are successfully logged in");
RequestDispatcher rd = request.getRequestDispatcher("/viewpartyinfo.jsp");
rd.forward(request, response);

}  
else  
{  
out.print("Sorry, email or password error");  
request.setAttribute("login_msg", "Invalid login and password");
RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
rd.forward(request, response);
}  
%>  