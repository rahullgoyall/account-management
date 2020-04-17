
<%@page import="utility.PdfGenerator"%>
<%@page import="dao.CashBookDao"%>
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

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
}
 %>   

 
 <%  
 double totalCr=0.0;
 double totalDr=0.0;
 List<CashBook> list = CashBookDao.getAllRecords();
 
 for(CashBook cb:list){
 if(cb.getStatus().equals("Payment")){
		totalDr = totalDr+ Double.parseDouble(cb.getAmount());
	}
	if(cb.getStatus().equals("Received")){
		totalCr = totalCr+ Double.parseDouble(cb.getAmount());
	}
 }		
	
 
String dateFrom=request.getParameter("dateFrom");
String dateTo=request.getParameter("dateTo");

if(dateFrom!=null && dateTo!=null){
	list=CashBookDao.getCashBookByFilter(dateFrom,dateTo);
}
PdfGenerator.cashBookPdf(list,dateFrom,dateTo);
%>  

<body class="container-fluid">  
<%if(request.getAttribute("login_msg")!=null){%>

<div class="alert alert-success text-center">
  <strong ><% out.print(request.getAttribute("login_msg"));%></strong>
</div>
<%} %>



<div class="row">
  <div class="col-sm-4" style="background-color:lavender;"><b>CashBook Information List</b></div>
  <div class="col-sm-5" ><a class="btn btn-info" role="button" href="addcashbookform.jsp">Add CashBook</a><a style="margin-left: 10px" class="btn btn-primary" role="button" href="cashbookopenpdf.jsp">Pdf</a></div>
  <div class="col-sm-3" ><h4 style="margin-left: 50px"><b>Net Amount: </b> <b><%= totalDr-totalCr %></b></h4> </div>
</div> 

  

<div class="row">
<div class="col-sm-12" style="background-color:lavender;">
<form class="form-inline" method="post" action="viewcashbook.jsp">        
        <span>Date From:</span>
        <input id="date1" name="dateFrom"  type="date" />
        <span>To:</span>
        <input id="date2" name="dateTo"  type="date" />
      
<button class="btn btn-primary btn-sm" type="submit" name="save" >Search</button>
</form>
</div>

</div>

<table class="table">
    <thead>
      <tr>
        <th style="width: 10%">Date</th>
        <th style="width: 20%">Party Name</th>
        <th style="width: 10%">Status</th>
        <th style="width: 10%">Payment Mode</th>
        <th style="width: 10%">Remarks</th>
        <th style="width: 5%">Dr Amount</th>
        <th style="width: 5%">Cr Amount</th>
         <th style="width: 5%">Edit</th>
        <th style="width: 10%">Delete</th>
       
      </tr>
    </thead>
    <tbody>
<% for(CashBook t : list){ %>    
      <tr>
        <td style ="word-break:break-all;"><%= t.getDate() %></td>
        <td style ="word-break:break-all;"><%= t.getPartyName()%></td>
        <td style ="word-break:break-all;"><%= t.getStatus() %></td>
         <td style ="word-break:break-all;"><%= t.getPaymentMode() %></td>
        <td style ="word-break:break-all;"><%= t.getRemarks() %></td>
        <td style ="word-break:break-all;"><%if(t.getStatus().equals("Payment")){out.print(t.getAmount());}else{out.print("0");} %></td>
        <td style ="word-break:break-all;"><%if(t.getStatus().equals("Received")){out.print(t.getAmount());}else{out.print("0");} %></td>
        <td style ="word-break:break-all;"><a class="btn btn-warning btn-xs" role="button" href="editcashbookform.jsp?id=<%=t.getId()%>">Edit</a></td>
        <td style ="word-break:break-all;"><a class="btn btn-danger btn-xs" role="button" onclick="return confirm('Are you sure you want to delete?')" href="deletecashbook.jsp?id=<%=t.getId()%>">Delete</a></td>
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
