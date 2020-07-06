<%@page import="java.awt.Desktop"%>
<%@page import="java.io.File"%>
<% 

try {
	String party = (String)session.getAttribute("partyName");
	File pdfFile = new File("C:\\New folder\\"+party+".pdf");
	if (pdfFile.exists()) {

		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().open(pdfFile);
		} else {
			System.out.println("Awt Desktop is not supported!");
		}

	} else {
		System.out.println("File is not exists!");
	}

	System.out.println("Done");

  } catch (Exception ex) {
	ex.printStackTrace();
  }

%>