<!DOCTYPE html>  
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
List<String> commodityNames =CommodityDao.getAllCommodityName();
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
  <h2>Add CashBook Info</h2>
  <div class="panel panel-default">
    <div class="panel-heading"><a href="viewcashbook.jsp">View All Records</a>
    <% String message = (String)session.getAttribute("message");
    out.print(message);
    session.setAttribute("message", "");
    %>
     </div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="addcashbook.jsp" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Date:</label>
      <div class="col-sm-2">
        <input class="form-control" id="date1" name="date" placeholder="MM/DD/YYYY" type="date" autofocus/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Party Name:</label>
      <div class="col-sm-5">     
      <select class="form-control" id="chosen" name="partyName" >
      <%for(PartyInfo p : saleAcc){ %>
          <option  value='<%= p.getPartyName()%>'><%= p.getPartyName()%></option> 
         <%}%>
         <option  value='Opening Balance'>Opening Balance</option> 
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Payment Mode:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="paymentMode">
      <option value='Cash'>Cash</option> 
      <option value='Check'>Check</option> 
      <option value='NEFT'>NEFT</option> 
      <option value='RTGS'>RTGS</option> 
      <option value='UPI'>UPI</option> 
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Amount:</label>
      <div class="col-sm-5">
        <br><input type="text"  class="form-control" name="amount">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Status:</label>
      <div class="col-sm-5">     
      <select class="form-control"  name="status">
      <option value='Received'>Received</option> 
      <option value='Payment'>Payment</option> 
      
       </select>   
      </div>
    </div>
    
     <div class="form-group">
      <label class="control-label col-sm-2" for="email">Remarks:</label>
      <div class="col-sm-5">
        <input type="text" class="form-control" name="remarks">
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

<script>
	$("#chosen").chosen();
	
	var today = new Date();
	var dd = today.getDate();

	var mm = today.getMonth()+1; 
	var yyyy = today.getFullYear();
	if(dd<10) 
	{
	    dd='0'+dd;
	} 

	if(mm<10) 
	{
	    mm='0'+mm;
	} 

	today = yyyy+'-'+mm+'-'+dd;
	document.getElementById("date1").defaultValue = today;
</script>