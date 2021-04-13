<%@page import="utility.PdfGenerator"%>
<%@page import="java.awt.Desktop"%>
<%@page import="java.io.File"%>
<% 

PdfGenerator.ledgerReportPdf();

try {

	File pdfFile = new File("D:\\bills\\debitreport.pdf");
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