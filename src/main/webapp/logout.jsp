<%
session.removeAttribute("login_session");
session.invalidate();
response.sendRedirect("index.jsp");
 %>