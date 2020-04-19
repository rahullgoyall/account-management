<!DOCTYPE html>  
<%@page import="dao.DayBookDao"%>
<jsp:include page="header.jsp"></jsp:include>  
<%@page import="dao.CommodityDao,dao.PartyInfoDao,bean.*,java.util.*"%>  

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
} %> 

<%  
String id=request.getParameter("id");  
DayBook d = DayBookDao.getRecordById(Integer.parseInt(id));
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
  <h2>Edit Daybook Info</h2>
  <div class="panel panel-default">
   
    <div class="panel-body">
    
    <form class="form-horizontal" action="editdaybook.jsp" method="post">
    <input type="hidden" name="id" value="<%=d.getId() %>"/>  
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Date:<br>(MM/DD/YYYY)</label>
      <div class="col-sm-2">
        <input class="form-control" id="date1" name="date" placeholder="MM/DD/YYYY" type="date" value="<%=d.getDate() %>" />
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Sales Accounts:</label>
      <div class="col-sm-4">     
      <select class="form-control" id="chosen" name="salesAccount" onChange = "setSale(value);">
      <%for(PartyInfo p : saleAcc){ %>
          <option <%if(d.getSalesAccount().equals(p.getPartyName())){ out.print("selected=true");} %>  value='<%= p.getPartyName()+"@@"+p.getBrokrage()%>'><%= p.getPartyName()%></option> 
         <%}%>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Commodity:</label>
      <div class="col-sm-4">     
      <select class="form-control" id="commodity" name="commodity">
      <%for(String c : commodityNames){ %>
          <option <%if(d.getCommodity().equals(c)){ out.print("selected=true");} %>  value='<%= c%>'><%= c%></option> 
         <%}%>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Brokrage:</label>
      <div class="col-sm-4">
        <br><input type="text" id="salebro" class="form-control input-sm" name="brokrageSale" value="<%= d.getBrokrageSale()%>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Weight:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="weight" value="<%= d.getWeight()%>">
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="email">Rate:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="rate" value="<%= d.getRate()%>">
      </div>
    </div>
    
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Purchase Accounts:</label>
      <div class="col-sm-4">     
      <select class="form-control" id="chosen2" name="purchaseAccount" onChange = "setPurchase(value);">
      <%for(PartyInfo p : saleAcc){ %>
          <option <%if(d.getPurchaseAccount().equals(p.getPartyName())){ out.print("selected=true");} %> value='<%= p.getPartyName()+"@@"+p.getBrokrage()%>'><%= p.getPartyName()%></option> 
         <%}%>
       </select>   
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><br>Brokrage:</label>
      <div class="col-sm-4">
        <br><input type="text" id="purchasebro" class="form-control input-sm" name="brokragePurchase" value="<%= d.getBrokragePurchase()%>"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Delivery / Expiry Date:</label>
      <div class="col-sm-2">
        <input class="form-control" id="date2" name="deliverDate" placeholder="MM/DD/YYYY" type="date" value="<%= d.getDeliverDate()%>"/>
      </div>
      <div class="col-sm-2">
        <input class="form-control" id="date3" name="expiryDate" placeholder="MM/DD/YYYY" type="date" value="<%= d.getExpiryDate()%>"/>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Trucks:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="truck" value="<%= d.getTruck()%>">
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">Remarks:</label>
      <div class="col-sm-4">
        <input type="text" class="form-control input-sm" name="remarks" value="<%= d.getRemarks()%>">
      </div>
    </div>
    
    <div> 
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
	$("#chosen2").chosen();
	$("#commodity").chosen();
</script>