<!DOCTYPE html>  
<%@page import="dao.CommodityDao,bean.Commodity"%>  
<jsp:include page="header.jsp"></jsp:include>  

<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Edit Form</title>  
</head>  
<body class="container-fluid">  

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
} %> 

<%  
String id=request.getParameter("id");  
Commodity c= CommodityDao.getRecordById(Integer.parseInt(id));  
%>  
 

<div class="container">
  <h2>Edit Commodity Form</h2>
  <div class="panel panel-default">
    <div class="panel-heading"></div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="editcommodity.jsp" method="post">
  	<input type="hidden" name="id" value="<%=c.getId() %>"/>  
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Name:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%= c.getName()%>" name="name">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Update</button>
      </div>
    </div>
  </form>
    </div>
    
    
    <div class="panel-footer"></div>
  </div>
</div>

  
</body>  
</html>  