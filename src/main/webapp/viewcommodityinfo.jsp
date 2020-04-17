
<%@page import="dao.CommodityDao,bean.*,java.util.*"%>  
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

<div class="row">
  <div class="col-sm-6" style="background-color:lavender;"><b>Commodity Information List</b></div>
  <div class="col-sm-6" ><a href="addcommodityform.jsp" class="btn btn-info" role="button">Add New Commodity</a></div>
</div> 

<% 
if(session.getAttribute("login_session")==null){
	out.print(session.getAttribute("login_session"));
	response.sendRedirect("index.jsp");
}
 %>   

 
 <%  
List<Commodity> list=CommodityDao.getAllRecords();  
request.setAttribute("list",list); 
String key=request.getParameter("key");
if(key!=null){
	list = CommodityDao.getAllRecordsByKey(key);
}
%>    

<form class="form-inline" method="post" action="viewcommodityinfo.jsp">
<input type="text" name="key" class="form-control" placeholder="Search Commodity Name.." value="<%if(key!=null){out.println(key);}%>">
<button type="submit" name="save" class="btn btn-primary">Search</button>
</form>
<table class="table">
    <thead>
      <tr>
        <th style="width: 80%">Commodity Name</th>
        <th style="width: 10%">Edit</th>
        <th style="width: 10%">Delete</th>
      </tr>
    </thead>
    <tbody>
<% for(Commodity t : list){ %>    
      <tr>
        <td style ="word-break:break-all;"><%= t.getName() %></td>
        <td style ="word-break:break-all;"><a href="editcommodityform.jsp?id=<%=t.getId()%>" class="btn btn-warning btn-xs" role="button">Edit</a></td>
        <td style ="word-break:break-all;"><a class="btn btn-danger btn-xs" role="button" onclick="return confirm('Are you sure you want to delete?')" href="deletecommodity.jsp?id=<%=t.getId()%>">Delete</a></td>
      </tr>
<%}%>      
    </tbody>
  </table>

  
</body>  
</html>  