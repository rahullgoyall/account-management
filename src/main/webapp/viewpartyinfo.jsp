
<%@page import="dao.PartyInfoDao,bean.*,dao.FinancialYearDao,java.util.*"%>  
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

<%if(request.getAttribute("delete_success")!=null){%>

<div class="alert alert-success text-center">
  <strong ><% out.print(request.getAttribute("delete_success"));%></strong>
</div>
<%} %>

<%if(request.getAttribute("delete_failed")!=null){%>

<div class="alert alert-danger text-center">
  <strong ><% out.print(request.getAttribute("delete_failed"));%></strong>
</div>
<%} %>


<%  
List<FinancialYear> years=FinancialYearDao.getAllRecords();
String yearId =request.getParameter("year");
if(yearId!=null){
	FinancialYearDao.update(Integer.parseInt(yearId));
}

%> 

<div class="row">
  <div class="col-sm-3" style="background-color:lavender;"><b>Party Information List</b></div>
  <div class="col-sm-2" ><a href="addpartyinfoform.jsp" class="btn btn-info" role="button">Add New Party</a></div>
  <div class="col-sm-4" ">
   <form class="form-inline" method="post" action="viewpartyinfo.jsp">
	<label class="control-label col-sm-3" for="pwd">Financial Year:</label>
      <div class="col-sm-4">     
      <select class="form-control"  name="year">
      <%for(FinancialYear f : years){ %>
       <option  <%if(f.getStatus().equals("yes")){ out.print("selected=true");} %> value='<%= f.getId()%>'><%= f.getYear()%></option>  
         <%}%>
       </select>   
      </div>
	<button type="submit" name="apply" class="btn btn-primary">Apply</button>
	</form>
  </div>
</div> 

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
}
 %>   

 
 <%  
List<PartyInfo> list=PartyInfoDao.getAllRecords();  
request.setAttribute("list",list); 
String key=request.getParameter("key");
if(key!=null){
	list = PartyInfoDao.getAllRecordsByKey(key);
}
%>    

<form class="form-inline" method="post" action="viewpartyinfo.jsp">
<input type="text" name="key" class="form-control" placeholder="Search Party Name.." value="<%if(key!=null){out.println(key);}%>">
<button type="submit" name="save" class="btn btn-primary">Search</button>
</form>
<table class="table">
    <thead>
      <tr>
        <th style="width: 5%">L/P</th>
        <th style="width: 20%">Party Name</th>
        <th style="width: 10%">Type</th>
        <th style="width: 10%">Gst No</th>
        <th style="width: 10%">Pan No</th>
        <th style="width: 15%">Address</th>
        <th style="width: 7%">Phone</th>
        <th style="width: 10%">Opening Bal</th>
        <th style="width: 7%">Op Type</th>
        <th style="width: 10%">Brokrage</th>
        <th style="width: 5%">Edit</th>
        <th style="width: 5%">Delete</th>
      </tr>
    </thead>
    <tbody>
<% for(PartyInfo t : list){ %>    
      <tr>
        <td style ="word-break:break-all;"><%= t.getLedger() %></td>
        <td style ="word-break:break-all;"><%= t.getPartyName() %></td>
        <td style ="word-break:break-all;"><%= t.getType() %></td>
        <td style ="word-break:break-all;"><%= t.getGstNo() %></td>
        <td style ="word-break:break-all;"><%= t.getPan() %></td>
        <td style ="word-break:break-all;"><%= t.getAddress() %></td>
        <td style ="word-break:break-all;"><%= t.getPhone() %></td>
        <td style ="word-break:break-all;"><%= t.getOpeningBal() %></td>
        <td style ="word-break:break-all;"><%= t.getOpType() %></td>
        <td style ="word-break:break-all;"><%= t.getBrokrage() %></td>
        <td style ="word-break:break-all;"><a class="btn btn-warning btn-xs" role="button" href="editpartyform.jsp?id=<%=t.getId()%>">Edit</a></td>
        <td style ="word-break:break-all;"><a class="btn btn-danger btn-xs" role="button" onclick="return confirm('Are you sure you want to delete?')" href="deletepartyinfo.jsp?id=<%=t.getId()%>&partyName=<%=t.getPartyName()%>">Delete</a></td>
      </tr>
<%}%>      
    </tbody>
  </table>

  
</body>  
</html>  