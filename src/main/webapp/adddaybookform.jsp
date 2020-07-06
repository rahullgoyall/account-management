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
String sname=request.getParameter("sname"); 
String pname=request.getParameter("pname"); 
String commodity=request.getParameter("commodity"); 
String sbro=request.getParameter("sbro");
String pbro=request.getParameter("pbro");
String truck=request.getParameter("truck");
if(sname == null && pname==null && commodity==null&&truck==null){
	sname = "";
	pname = "";
	commodity = "";
	sbro="";
	pbro="";
	truck="";
			
}
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

<script>
        function setSale(str) {
        	  var bro = str.split("@@");
              document.getElementById('salebro').value = bro[1];
        }; 
        
        function setPurchase(str) {
      	  var bro = str.split("@@");
            document.getElementById('purchasebro').value = bro[1];
      }; 
</script>
</head>  
<body>  
  


<div class="container">
  <h2>Add DayBook Info</h2>
  <div class="panel panel-default">
    <div class="panel-heading"><a class="btn btn-info " role="button" href="viewdaybook.jsp">View All Records</a>
     <% String message = (String)session.getAttribute("message");
    out.print(message);
    session.setAttribute("message", "");
    %>
    </div>
    <div class="panel-body">
    
    <form class="form-horizontal" action="adddaybook.jsp" method="post">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Date:</label>
      <div class="col-sm-2">
        <input class="form-control" id="date1" name="date" placeholder="MM/DD/YYYY" type="date" autofocus/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Sales Accounts:</label>
      <div class="col-sm-4">     
      <select class="form-control" id="chosen" name="salesAccount" onChange = "setSale(value);">
      <option disabled selected>Select sales account</option>
      <%for(PartyInfo p : saleAcc){ %>
          <option  <%if(sname.split("@@")[0].equals(p.getPartyName())){ out.print("selected=true");} %>  value='<%= p.getPartyName()+"@@"+p.getBrokrage()%>'><%= p.getPartyName()%></option> 
         <%}%>
       </select>   
      </div>
    </div>
    <div class="form-group">
     <label class="control-label col-sm-2" for="pwd">Commodity:</label>
      <div class="col-sm-4">     
      <select class="form-control" id="commodity" name="commodity">
      <%for(String c : commodityNames){ %>
          <option <%if(commodity.equals(c)){ out.print("selected=true");} %> value='<%= c%>'><%= c%></option> 
         <%}%>
       </select>   
      </div>
      </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Brokrage:</label>
      <div class="col-sm-4">
        <br><input type="text" id="salebro" class="form-control input-sm" name="brokrageSale" value="<% if(!sname.isEmpty()&&sbro.isEmpty()){out.print(sname.split("@@")[1]);}else{out.print(sbro);} %>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Weight:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="weight">
      </div>
    </div>
    <div class="form-group">
    	<label class="control-label col-sm-2" for="email">Rate:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="rate">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Purchase Accounts:</label>
      <div class="col-sm-4">     
      <select class="form-control input-sm" id="chosen2" name="purchaseAccount" onChange = "setPurchase(value);">
      <option disabled selected>Select purchase account</option>
      <%for(PartyInfo p : saleAcc){ %>
          <option <%if(pname.split("@@")[0].equals(p.getPartyName())){ out.print("selected=true");} %> value='<%= p.getPartyName()+"@@"+p.getBrokrage()%>'><%= p.getPartyName()%></option> 
         <%}%>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Brokrage:</label>
      <div class="col-sm-4">
        <br><input type="text" id="purchasebro" class="form-control input-sm" name="brokragePurchase" value="<% if(!sname.isEmpty()&&pbro.isEmpty()){out.print(pname.split("@@")[1]);}else{out.print(pbro);} %>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Delivery / Expiry Date:</label>
      <div class="col-sm-2">
        <input class="form-control" id="date2" name="deliverDate" placeholder="MM/DD/YYYY" type="date" />
      </div>
      
      <div class="col-sm-2">
        <input class="form-control" id="date3" name="expiryDate" placeholder="MM/DD/YYYY" type="date" />
      </div>
    </div>
 
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Trucks:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="truck" value="<%=truck %>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Remarks:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="remarks">
      </div>
    </div>
    
    <div> 
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
	$("#chosen2").chosen();
	$("#commodity").chosen();
	
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
	document.getElementById("date2").defaultValue = today;
	document.getElementById("date3").defaultValue = today;
</script>