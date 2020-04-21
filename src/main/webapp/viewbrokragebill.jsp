
<%@page import="utility.PdfGenerator"%>
<%@page import="dao.DayBookDao"%>
<%@page import="dao.PartyInfoDao,bean.*,java.util.*"%>  
<jsp:include page="header.jsp"></jsp:include>  
 <!DOCTYPE html>   
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


<body class="container-fluid">  
<%if(request.getAttribute("login_msg")!=null){%>

<div class="alert alert-success text-center">
  <strong ><% out.print(request.getAttribute("login_msg"));%></strong>
</div>
<%} %>


<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
}
 %>   

 
 <%  
double totalAmt = 0.0;
List<PartyInfo> saleAcc=PartyInfoDao.getAllPartyName(); 
List<BrokrageBill> list = new ArrayList<BrokrageBill>();
 
String lName=request.getParameter("lName");
String dateFrom=request.getParameter("dateFrom");
String dateTo=request.getParameter("dateTo");
int gst;

if(request.getParameter("gst") ==  null){
	gst = 0;
}else{ gst=Integer.parseInt(request.getParameter("gst")); }
if(lName!=null && dateFrom!=null && dateTo!=null){
	list=DayBookDao.getBrokrageBillByFilter(lName,dateFrom,dateTo);
}

if(lName==null){
	lName= "";
}
PdfGenerator.BrokrageBillPdf(list,lName,gst,dateFrom,dateTo);

for(BrokrageBill bb : list){
	totalAmt = totalAmt + Double.parseDouble(bb.getBrokrage());
}
%>    

<div class="row">
  <div class="col-sm-4" style="background-color:lavender;"><b>Brokrage Bill Information List</b></div>
  <div class="col-sm-5" ><a class="btn btn-info " role="button" href="brokragepdfopen.jsp">Pdf</a></div>
  <div class="col-sm-3" ><h4 style="margin-left: 50px"><b>Total Brokrage: </b> <b><%=totalAmt %></b></h4> </div>
</div> 

<div class="row">
<div class="col-sm-12" style="background-color:lavender;">
<form class="form-inline" method="post" action="viewbrokragebill.jsp">
        <span><b>IGST:</b></span>
        <input style="width: 30px" id="gst" name="gst"  type="text" value="18"/>
        <span><b>Date From:</b></span>
        <input id="date1" name="dateFrom"  type="date" />
        <span><b>To:</b></span>
        <input id="date2" name="dateTo"  type="date" />
           
      <select id="chosen" name="lName" onChange = "setSale(value);">
      <%for(PartyInfo p : saleAcc){ %>
          <option  <%if(lName.equals(p.getPartyName())){ out.print("selected=true");} %> value='<%= p.getPartyName()%>'><%= p.getPartyName()%></option> 
         <%}%>
       </select>   
      
<button class="btn btn-primary btn-sm" type="submit" name="save" >Search</button>
</form>
</div>

</div>

<table class="table">
    <thead>
      <tr>
        <th style="width: 10%">Bill Date</th>
        <th style="width: 20%">Particulars</th>
        <th style="width: 10%">Sales_Purchase</th>
        <th style="width: 10%">Commodity</th>
        <th style="width: 5%">Weight</th>
        <th style="width: 5%">Rate</th>
        <th style="width: 5%">Brokrage</th>
        <th style="width: 10%">Delivery Date</th>
        <th style="width: 10%">Expiry Date</th>
        <th style="width: 5%">Trucks</th>
      </tr>
    </thead>
    <tbody>
<% for(BrokrageBill t : list){ %>    
      <tr>
        <td style ="word-break:break-all;"><%= t.getBillDate() %></td>
        <td style ="word-break:break-all;"><%= t.getParticulars() %></td>
        <td style ="word-break:break-all;"><%= t.getSalesPurchase() %></td>
        <td style ="word-break:break-all;"><%= t.getCommodity() %></td>
        <td style ="word-break:break-all;"><%= t.getWeight() %></td>
        <td style ="word-break:break-all;"><%= t.getRate() %></td>
        <td style ="word-break:break-all;"><%= t.getBrokrage() %></td>
        <td style ="word-break:break-all;"><%= t.getDeliverDate() %></td>
        <td style ="word-break:break-all;"><%= t.getExpiryDate() %></td>
        <td style ="word-break:break-all;"><%= t.getTruck() %></td>
      </tr>
<%}%>      
    </tbody>
  </table>
</body>  
</html>  

<script type="text/javascript">
$("#chosen").chosen();

</script>