
<%@page import="utility.PdfGenerator"%>
<%@page import="dao.DayBookDao"%>
<%@page import="dao.PartyInfoDao,bean.*,java.util.*"%>  
<jsp:include page="header.jsp"></jsp:include>  
 <!DOCTYPE html>   
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>View Users</title>  
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
List<DayBook> list=DayBookDao.getAllRecords();  
double totalBro = 0.0;

String dateFrom=request.getParameter("dateFrom");
String dateTo=request.getParameter("dateTo");
if(dateFrom!=null && dateTo!=null){
	list=DayBookDao.getDayBookByDates(dateFrom,dateTo);
}

for(DayBook db: list){
	totalBro = totalBro + Double.parseDouble(db.getBrokrageSale()) + Double.parseDouble(db.getBrokragePurchase());
}

PdfGenerator.DayBookPdf(list,dateFrom,dateTo);
%> 

<div class="row">
  <div class="col-sm-4" style="background-color:lavender;"><b>DayBook Information List</b></div>
  <div class="col-sm-5" ><a class="btn btn-info" role="button" href="adddaybookform.jsp">Add DayBook</a><a style="margin-left: 10px" class="btn btn-primary" role="button" href="daybookopenpdf.jsp">Pdf</a></div>
  <div class="col-sm-3" ><h4 style="margin-left: 50px"><b>Total Brokrage: </b> <b><%=totalBro %></b></h4> </div>
</div> 

   

<div class="row">
<div class="col-sm-12" style="background-color:lavender;">
<form class="form-inline" method="post" action="viewdaybook.jsp">        
        <span>Date From:</span>
        <input id="date1" name="dateFrom"  type="date" />
        <span>To:</span>
        <input id="date2" name="dateTo"  type="date" />
      
<button class="btn btn-primary btn-md" type="submit" name="save" >Search</button>
</form>
</div>

</div>

<table class="table">
    <thead>
      <tr>
        <th style="width: 10%">Date</th>
        <th style="width: 20%">Sales Account</th>
        <th style="width: 10%">Commodity</th>
        <th style="width: 5%">Sales Brokrage</th>
        <th style="width: 5%">Weight</th>
        <th style="width: 5%">Rate</th>
        <th style="width: 20%">Purchase Account</th>
        <th style="width: 5%">Purchase Brokrage</th>
        <th style="width: 10%">Delivery Date</th>
        <th style="width: 10%">Expiry Date</th>
        <th style="width: 5%">Trucks</th>
        <th style="width: 10%">Remarks</th>
        <th style="width: 5%">Edit</th>
        <th style="width: 5%">Delete</th>
      </tr>
    </thead>
    <tbody>
<% for(DayBook t : list){ %>    
      <tr>
        <td style ="word-break:break-all;"><%= t.getDate() %></td>
        <td style ="word-break:break-all;"><%= t.getSalesAccount() %></td>
        <td style ="word-break:break-all;"><%= t.getCommodity() %></td>
        <td style ="word-break:break-all;"><%= t.getBrokrageSale() %></td>
        <td style ="word-break:break-all;"><%= t.getWeight() %></td>
        <td style ="word-break:break-all;"><%= t.getRate() %></td>
        <td style ="word-break:break-all;"><%= t.getPurchaseAccount() %></td>
        <td style ="word-break:break-all;"><%= t.getBrokragePurchase() %></td>
        <td style ="word-break:break-all;"><%= t.getDeliverDate() %></td>
        <td style ="word-break:break-all;"><%= t.getExpiryDate() %></td>
        <td style ="word-break:break-all;"><%= t.getTruck() %></td>
        <td style ="word-break:break-all;"><%= t.getRemarks() %></td>
        <td style ="word-break:break-all;"><a class="btn btn-warning btn-xs" role="button" href="editdaybookform.jsp?id=<%=t.getId()%>">Edit</a></td>
        <td style ="word-break:break-all;"><a class="btn btn-danger btn-xs" role="button" onclick="return confirm('Are you sure you want to delete?')" href="deletedaybook.jsp?id=<%=t.getId()%>">Delete</a></td>
      </tr>
<%}%>      
    </tbody>
  </table>
  
</body>  
</html>  

<script>
	
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
</script>