<!DOCTYPE html>  
<%@page import="dao.CashBookDao"%>
<jsp:include page="header.jsp"></jsp:include>  
<%@page import="dao.CommodityDao,dao.PartyInfoDao,bean.*,java.util.*"%>  

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
} %> 

<%  
String id=request.getParameter("id");  
List<PartyInfo> saleAcc=PartyInfoDao.getAllPartyName();
CashBook t=CashBookDao.getRecordById(Integer.parseInt(id)); 
%>  

<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title>Add User Form</title>  
<!-- Special version of Bootstrap that only affects content wrapped in .bootstrap-iso -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.jquery.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.8.7/chosen.min.css">

<!-- Include Date Range Picker -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>

</head>  
<body>  
  


<div class="container">
  <h2>Edit CashBook Info</h2>
  <div class="panel panel-default">
    <div class="panel-heading"> </div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="editcashbook.jsp" method="post">
    <input type="hidden" name="id" value="<%=t.getId() %>"/>  
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Date:<br>(MM/DD/YYYY)</label>
      <div class="col-sm-2">
        <input class="form-control" id="date1" name="date" placeholder="MM/DD/YYYY" type="date" value="<%=t.getDate() %>"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Party Name:</label>
      <div class="col-sm-5">     
      <select class="form-control" id="chosen" name="partyName" >
      <%for(PartyInfo p : saleAcc){ %>
          <option  <%if(t.getPartyName().equals(p.getPartyName())){ out.print("selected=true");} %> value='<%= p.getPartyName()%>'><%= p.getPartyName()%></option> 
         <%}%>
         <option  <%if(t.getPartyName().equals("Opening Balance")){ out.print("selected=true");} %> value='Opening Balance'>Opening Balance</option> 
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Payment Mode:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="paymentMode">
      <option <%if(t.getPaymentMode().equals("Cash")){ out.print("selected=true");} %> value='Cash'>Cash</option> 
       <option <%if(t.getPaymentMode().equals("Check")){ out.print("selected=true");} %> value='Check'>Check</option>
        <option <%if(t.getPaymentMode().equals("NEFT")){ out.print("selected=true");} %> value='NEFT'>NEFT</option>
         <option <%if(t.getPaymentMode().equals("RTGS")){ out.print("selected=true");} %> value='RTGS'>RTGS</option>
          <option <%if(t.getPaymentMode().equals("UPI")){ out.print("selected=true");} %> value='UPI'>UPI</option>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Amount:</label>
      <div class="col-sm-5">
        <br><input type="text"  class="form-control" name="amount" value="<%=t.getAmount() %>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Status:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="status">
      <option <%if(t.getStatus().equals("Payment")){ out.print("selected=true");} %> value='Payment'>Payment</option> 
      <option  <%if(t.getStatus().equals("Received")){ out.print("selected=true");} %> value='Received'>Received</option> 
       </select>   
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="email">Remarks:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="remarks" value="<%=t.getRemarks() %>">
      </div>
    </div>    
   
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-success">Update</button>
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

<script>
	$("#chosen").chosen();
	
</script>