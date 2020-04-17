<!DOCTYPE html>  
<%@page import="dao.PartyInfoDao,bean.PartyInfo"%>  
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
PartyInfo t=PartyInfoDao.getRecordById(Integer.parseInt(id));  
%>  
 

<div class="container">
  <h2>Edit Party Info</h2>
  <div class="panel panel-default">
    <div class="panel-heading"></div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="editpartyinfo.jsp?pname=<%=t.getPartyName()%>" method="post">
  	<input type="hidden" name="id" value="<%=t.getId() %>"/>  
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Party Name:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%=t.getPartyName() %>" name="partyName">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">GST No:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" value="<%=t.getGstNo() %>" name="gstNo" autofocus>
      </div>
      <label class="control-label col-sm-2" for="email">PAN No:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control" value="<%=t.getPan() %>" name="pan" autofocus>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Party/Ledger:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="ledger">
      <option <%if(t.getLedger().equals("Ledger")){ out.print("selected=true");} %> value='Ledger'>Ledger</option>
      <option <%if(t.getLedger().equals("Party")){ out.print("selected=true");} %> value='Party'>Party</option>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Type</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="type">
      <option <%if(t.getType().equals("Sale")){ out.print("selected=true");} %> value='Sale'>Sale</option>
      <option <%if(t.getType().equals("Purchase")){ out.print("selected=true");} %> value='Purchase'>Purchase</option>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Address:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%=t.getAddress() %>" name="address">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Phone:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%=t.getPhone()%>" name="phone">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Opening Bal:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%=t.getOpeningBal()%>" name="openingBal">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Brokrage:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" value="<%=t.getBrokrage()%>" name="brokrage">
      </div>
    </div>
   <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Cr/Dr:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="opType">
      <option <%if(t.getOpType().equals("Dr")){ out.print("selected=true");} %> value='Dr'>Dr</option>
      <option <%if(t.getOpType().equals("Cr")){ out.print("selected=true");} %> value='Cr'>Cr</option>
       </select>   
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