<!DOCTYPE html>  
<jsp:include page="header.jsp"></jsp:include>  
<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
} %> 
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Add User Form</title>  
</head>  
<body>  
  


<div class="container">
  <h2>Add Commodity</h2>
  <div class="panel panel-default">
    <div class="panel-heading"><a href="viewcommodityinfo.jsp">View All Records</a><br/>  </div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="addcommodity.jsp" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Commodity Name:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="name" autofocus>
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Save</button>
      </div>
    </div>
  </form>
    </div>
    
    
    <div class="panel-footer">
    <% String message = (String)session.getAttribute("message");
    out.print(message);
    session.setAttribute("message", "");
    %>
    </div>
  </div>
</div>
  
</body>  
</html>  