<%@page import="utility.PdfGenerator"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.io.File"%>

<% 

String dbName = "account";
String dbUser = "root";
String dbPass = "admin";

/***********************************************************/
// Execute Shell Command
/***********************************************************/
String executeCmd = "";
executeCmd = "mysqldump -u "+dbUser+" -p"+dbPass+" "+dbName+" -r D:\\backup\\backup_"+java.time.LocalDate.now()+".sql";

File directory = new File("D:\\backup");
if (! directory.exists()){
	directory.mkdir();
}

Process runtimeProcess;
try {
	runtimeProcess = Runtime.getRuntime().exec(executeCmd);
	int processComplete = runtimeProcess.waitFor();
	if(processComplete == 0){

    	out.print("Back successfully created on "+java.time.LocalDate.now());
    	

    	} else {

    	out.print("backup failed");

    	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
 

%>