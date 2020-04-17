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
  <h2>Add Party Info</h2>
  <div class="panel panel-info">
    <div class="panel-heading"><a href="viewpartyinfo.jsp">View All Records</a>
    <% String message = (String)session.getAttribute("message");
    out.print(message);
    session.setAttribute("message", "");
    %>
    </div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="addpartyinfo.jsp" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Party Name:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="partyName" autofocus>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">GST No:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" name="gstNo" autofocus>
      </div>
      <label class="control-label col-sm-2" for="email">PAN No:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" name="pan" autofocus>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Party/Ledger:</label>
      <div class="col-sm-4">     
      <select class="form-control"  name="ledger">
      <option value='Ledger'>Ledger</option> 
      <option value='Party'>Party</option> 
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Type</label>
      <div class="col-sm-4">     
      <select class="form-control"  name="type">
      <option value='Sale'>Sale</option> 
      <option value=Purchase>Purchase</option> 
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Address:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="address" >
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Phone:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="phone" >
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Opening Bal:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="openingBal" value="0.0">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Brokrage:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="brokrage" value="0.0">
      </div>
    </div>
   <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Cr/Dr:</label>
      <div class="col-sm-4">     
      <select class="form-control"  name="opType">
      <option value='Dr'>Dr</option> 
      <option value='Cr'>Cr</option> 
       </select>   
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
    </div>
  </div>
</div>
  
</body>  
</html>  