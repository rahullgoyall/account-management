<% 
if(session.getAttribute("login_session")==null){
	response.sendRedirect("index.jsp");
} %> 

<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Obo</a>
        
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="viewpartyinfo.jsp">Party Info</a></li>
      <li class="active"><a href="viewcommodityinfo.jsp">Commodity Info</a></li>
      <li class="active"><a href="viewcashbook.jsp">Cash Book</a></li>
       <li class="active"><a href="viewdaybook.jsp">Day Book</a></li>
       <li class="active"><a href="viewbrokragebill.jsp">Brokrage Bill</a></li>
       <li class="active"><a href="ledgerreportpdfopen.jsp">Ledger Report</a></li>
       <li class="active"><a href="backup.jsp">Backup</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <li><a href="logout.jsp"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>

</body>
</html>