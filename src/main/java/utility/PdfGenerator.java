package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import bean.BrokrageBill;
import bean.CashBook;
import bean.DayBook;
import bean.PartyInfo;
import dao.CashBookDao;
import dao.DayBookDao;
import dao.PartyInfoDao;

public class PdfGenerator {

	public static void DayBookPdf(List<DayBook> dayBooks, String dateFrom,String dateTo){
		
		double totalSale = 0.0;
		double totalPurchase = 0.0;
		int sNo = 0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		String fileName = "D:\\bills\\daybook.pdf";
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.addTitle("DayBook Report");

			Font head = new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLUE);
			Font fontText = new Font(FontFamily.TIMES_ROMAN,11,Font.NORMAL);
			Font fonttable = new Font(FontFamily.UNDEFINED,9,Font.NORMAL);

			Font fontBrokrageBill = new Font(FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);

			Paragraph brokragBillDetail = new Paragraph("DayBook Report",fontBrokrageBill);
			brokragBillDetail.setAlignment(Element.ALIGN_CENTER);
			document.add(brokragBillDetail);

			Paragraph datepdf = new Paragraph("Date: "+formatter.format(date),fontText);
			datepdf.setAlignment(Element.ALIGN_CENTER);
			document.add(datepdf);


			document.add(new Paragraph("    "));

			PdfPTable table = new PdfPTable(11);
			table.setTotalWidth(new float[]{ 20,50, 100, 40, 50, 30, 40, 100, 40, 50, 50 });
			table.setLockedWidth(true);

			if(dateFrom != null &&  dateTo != null){
				PdfPCell dateRange = new PdfPCell(new Paragraph("From: "+formatter.format(getDate(dateFrom))+" To: "+formatter.format(getDate(dateTo)),fontText));
				dateRange.setBorder(Rectangle.NO_BORDER);
				dateRange.setColspan(11);
				table.addCell(dateRange);
				}
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("No",fontText));
			cell11.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell11.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell11);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Date",fontText));
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Sales Party",fontText));
			cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Paragraph("S. Bro.",fontText));
			cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);

			PdfPCell cell10 = new PdfPCell(new Paragraph("Commodity",fontText));
			cell10.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell10.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell10);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Weight",fontText));
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell4.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Rate",fontText));
			cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell5.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Paragraph("Purchase Party",fontText));
			cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell6.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Paragraph("P. Bro.",fontText));
			cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell7.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Paragraph("Del. Date",fontText));
			cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell8.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell8);

			PdfPCell cell9 = new PdfPCell(new Paragraph("Expiry Date",fontText));
			cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell9.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell9);



			for(DayBook db: dayBooks){

				sNo++;
				PdfPCell cell = new PdfPCell(new Paragraph(""+sNo,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(db.getDate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getSalesAccount(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getBrokrageSale(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getCommodity(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getWeight(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getRate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getPurchaseAccount(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getBrokragePurchase(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getDeliverDate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(db.getExpiryDate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				totalSale = totalSale + Double.parseDouble(db.getBrokrageSale());
				totalPurchase = totalPurchase + Double.parseDouble(db.getBrokragePurchase());
				

			}
			
			PdfPCell total = new PdfPCell(new Paragraph("Total:",fontText));
			total.setBackgroundColor(BaseColor.LIGHT_GRAY);
			total.setBorder(Rectangle.NO_BORDER);
			total.setColspan(3);
			table.addCell(total);

			PdfPCell weightCell = new PdfPCell(new Paragraph(""+totalSale,fontText));
			weightCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			weightCell.setBorder(Rectangle.NO_BORDER);
			weightCell.setColspan(5);
			table.addCell(weightCell);

			PdfPCell brokrageCell = new PdfPCell(new Paragraph(""+totalPurchase,fontText));
			brokrageCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			brokrageCell.setBorder(Rectangle.NO_BORDER);
			brokrageCell.setColspan(3);
			table.addCell(brokrageCell);



			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void BrokrageBillPdf(List<BrokrageBill> brokrageBills, String billPartyName,int igst,String dateFrom,String dateTo){

		File directory = new File("D:\\bills");
		if (! directory.exists()){
			directory.mkdir();
		}

		PartyInfo partyInfo = PartyInfoDao.getRecordByName(billPartyName);

		double totalBrokrage = 0.0;
		double totalWeight = 0.0;
		int sNo =0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		//List<BrokrageBill> brokrageBills = DayBookDao.getBrokrageBillByFilter("SHUBHI", "", "");

		String fileName = "D:\\bills\\"+billPartyName+".pdf";
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.addTitle("Brokrage Bill");

			Font head = new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLUE);
			Font fontText = new Font(FontFamily.TIMES_ROMAN,11,Font.NORMAL);
			Font partyfont = new Font(FontFamily.TIMES_ROMAN,11,Font.BOLD);
			Font fonttable = new Font(FontFamily.UNDEFINED,9,Font.NORMAL);

			Font fontBrokrageBill = new Font(FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);

			Paragraph companyName = new Paragraph("Ashok Kumar Goyal, Agra",head);
			companyName.setAlignment(Element.ALIGN_CENTER);
			document.add(companyName);

			Paragraph companyAddress = new Paragraph("3/42 Shanker Ganj, Chatta Bazar,Agra, Mob. No-9412254037",fontText);
			companyAddress.setAlignment(Element.ALIGN_CENTER);
			document.add(companyAddress);

			Paragraph brokragBillDetail = new Paragraph("Brokrage Bill Details",fontBrokrageBill);
			brokragBillDetail.setAlignment(Element.ALIGN_CENTER);
			document.add(brokragBillDetail);

			Paragraph datepdf = new Paragraph("Date: "+formatter.format(date),fontText);
			datepdf.setAlignment(Element.ALIGN_CENTER);
			document.add(datepdf);

			Paragraph partyName = new Paragraph("PARTY NAME: "+billPartyName,partyfont);
			document.add(partyName);
			if(partyInfo!=null){
				if(partyInfo.getGstNo()!=null){
				if(!partyInfo.getGstNo().equals("null")){
					Paragraph gstNo = new Paragraph("GST NO: "+partyInfo.getGstNo()+"             Pan No: "+partyInfo.getPan(),fontText);
					document.add(gstNo);

				}}
			}
			if(partyInfo!=null){
				if(partyInfo.getAddress()!=null){
				if(!partyInfo.getAddress().equals("null")){
					Paragraph partyAddress = new Paragraph("Address: "+partyInfo.getAddress(),fontText);
					document.add(partyAddress);

				}}
			}

			if(partyInfo!=null){
				if(partyInfo.getPhone()!=null){
				if(!partyInfo.getPhone().equals("null")){
					Paragraph partyPhone = new Paragraph("Phone No: "+partyInfo.getPhone(),fontText);
					document.add(partyPhone);

				}}
			}




			document.add(new Paragraph("    "));

			PdfPTable table = new PdfPTable(9);
			table.setTotalWidth(new float[]{ 20,50, 210, 80, 40, 40, 60, 35, 60 });
			table.setLockedWidth(true);
			
			if(dateFrom != null &&  dateTo != null){
				if(!dateFrom.isEmpty() && !dateTo.isEmpty()){
				PdfPCell dateRange = new PdfPCell(new Paragraph("From: "+formatter.format(getDate(dateFrom))+" To: "+formatter.format(getDate(dateTo)),fontText));
				dateRange.setBorder(Rectangle.NO_BORDER);
				dateRange.setColspan(9);
				table.addCell(dateRange);
				}
				}
			
			PdfPCell cell9 = new PdfPCell(new Paragraph("No",fontText));
			cell9.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell9.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell9);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Date",fontText));
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Party Name",fontText));
			cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Commodity",fontText));
			cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Weight",fontText));
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell4.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Rate",fontText));
			cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell5.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell5);

			PdfPCell cell6 = new PdfPCell(new Paragraph("Delivery Date",fontText));
			cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell6.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell6);

			PdfPCell cell7 = new PdfPCell(new Paragraph("Trucks",fontText));
			cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell7.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Paragraph("Brokrage",fontText));
			cell8.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell8.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell8);

			for(BrokrageBill bb : brokrageBills){
				
				sNo++;
				
				PdfPCell cell = new PdfPCell(new Paragraph(""+sNo,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(bb.getBillDate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getParticulars(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getCommodity(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getWeight(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getRate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				String dDate[] = bb.getDeliverDate().split("/");
				String eDate[] = bb.getExpiryDate().split("/");

				cell = new PdfPCell(new Paragraph(dDate[0]+"/"+dDate[1]+"-"+eDate[0]+"/"+eDate[1],fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getTruck(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(bb.getBrokrage(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				totalBrokrage = totalBrokrage + Double.parseDouble(bb.getBrokrage());
				totalWeight = totalWeight + Double.parseDouble(bb.getWeight());
			}

			double gstCal = (totalBrokrage*igst)/100;
			double netCal = (totalBrokrage+gstCal);

			PdfPCell total = new PdfPCell(new Paragraph("Total:",fontText));
			total.setBackgroundColor(BaseColor.LIGHT_GRAY);
			total.setBorder(Rectangle.NO_BORDER);
			total.setColspan(4);
			table.addCell(total);

			PdfPCell weightCell = new PdfPCell(new Paragraph(""+totalWeight,fontText));
			weightCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			weightCell.setBorder(Rectangle.NO_BORDER);
			weightCell.setColspan(4);
			table.addCell(weightCell);

			PdfPCell brokrageCell = new PdfPCell(new Paragraph(""+totalBrokrage,fontText));
			brokrageCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			brokrageCell.setBorder(Rectangle.NO_BORDER);
			brokrageCell.setColspan(4);
			table.addCell(brokrageCell);

			PdfPCell gst = new PdfPCell(new Paragraph("IGST "+igst+"%:",fontText));
			gst.setBackgroundColor(BaseColor.LIGHT_GRAY);
			gst.setBorder(Rectangle.NO_BORDER);
			gst.setColspan(8);
			table.addCell(gst);

			PdfPCell gstAmount = new PdfPCell(new Paragraph("+"+gstCal,fontText));
			gstAmount.setBackgroundColor(BaseColor.LIGHT_GRAY);
			gstAmount.setBorder(Rectangle.NO_BORDER);
			gstAmount.setColspan(1);
			table.addCell(gstAmount);

			PdfPCell net = new PdfPCell(new Paragraph("Net Amount:",fontText));
			net.setBackgroundColor(BaseColor.LIGHT_GRAY);
			net.setBorder(Rectangle.NO_BORDER);
			net.setColspan(8);
			table.addCell(net);
			
			
			PdfPCell netTotal = new PdfPCell(new Paragraph(""+netCal,fontText));
			netTotal.setBackgroundColor(BaseColor.LIGHT_GRAY);
			netTotal.setBorder(Rectangle.NO_BORDER);
			netTotal.setColspan(1);
			table.addCell(netTotal);




			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	



	public static void cashBookPdf(List<CashBook> cashBooks, String dateFrom,String dateTo){

		double totalCr = 0.0;
		double totalDr = 0.0;
		int sNo = 0;
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();

		String fileName = "D:\\bills\\cashbook.pdf";
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.addTitle("Cash Book");

			Font head = new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLUE);
			Font fontText = new Font(FontFamily.TIMES_ROMAN,11,Font.NORMAL);
			Font fonttable = new Font(FontFamily.UNDEFINED,9,Font.NORMAL);

			Font fontBrokrageBill = new Font(FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);

			Paragraph companyName = new Paragraph("Ashok Kumar Goyal, Agra",head);
			companyName.setAlignment(Element.ALIGN_CENTER);
			document.add(companyName);

			Paragraph companyAddress = new Paragraph("3/42 Shanker Ganj, Chatta Bazar,Agra, Mob. No-9412254037",fontText);
			companyAddress.setAlignment(Element.ALIGN_CENTER);
			document.add(companyAddress);

			Paragraph brokragBillDetail = new Paragraph("Cash Book Report",fontBrokrageBill);
			brokragBillDetail.setAlignment(Element.ALIGN_CENTER);
			document.add(brokragBillDetail);

			Paragraph datepdf = new Paragraph("Date: "+formatter.format(date),fontText);
			datepdf.setAlignment(Element.ALIGN_CENTER);
			document.add(datepdf);


			document.add(new Paragraph("    "));

			PdfPTable table = new PdfPTable(7);
			table.setTotalWidth(new float[]{ 30,50, 50, 230, 70,70,70});
			table.setLockedWidth(true);
			
			if(dateFrom != null &&  dateTo != null){
			PdfPCell dateRange = new PdfPCell(new Paragraph("From: "+formatter.format(getDate(dateFrom))+" To: "+formatter.format(getDate(dateTo)),fontText));
			dateRange.setBorder(Rectangle.NO_BORDER);
			dateRange.setColspan(7);
			table.addCell(dateRange);
			}
			
			PdfPCell cell7 = new PdfPCell(new Paragraph("S.No.",fontText));
			cell7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell7.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell7);

			PdfPCell cell1 = new PdfPCell(new Paragraph("Date",fontText));
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell1);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Status",fontText));
			cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Party Info Remarks",fontText));
			cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);

			PdfPCell cell6 = new PdfPCell(new Paragraph("P. Mode",fontText));
			cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell6.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell6);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Dr Amt",fontText));
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell4.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell4);

			PdfPCell cell5 = new PdfPCell(new Paragraph("Cr Amt",fontText));
			cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell5.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell5);

			for(CashBook cb : cashBooks){
				double crAmount = 0.0;
				double drAmount = 0.0;
				sNo++;

				if(cb.getStatus().equals("Payment")){
					drAmount = Double.parseDouble(cb.getAmount());
				}
				if(cb.getStatus().equals("Received")){
					crAmount = Double.parseDouble(cb.getAmount());
				}
				
				PdfPCell cell = new PdfPCell(new Paragraph(""+sNo,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(cb.getDate(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(cb.getStatus(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				if(cb.getRemarks()!=null){
					cell = new PdfPCell(new Paragraph(cb.getPartyName()+" "+cb.getRemarks(),fonttable));
					cell.setBorder(Rectangle.NO_BORDER);
					table.addCell(cell);
					}else{
						cell = new PdfPCell(new Paragraph(cb.getPartyName(),fonttable));
						cell.setBorder(Rectangle.NO_BORDER);
						table.addCell(cell);
					}
				

				cell = new PdfPCell(new Paragraph(cb.getPaymentMode(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(""+drAmount,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(""+crAmount,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				if(cb.getStatus().equals("Payment")){
					totalDr = totalDr+ Double.parseDouble(cb.getAmount());
				}
				if(cb.getStatus().equals("Received")){
					totalCr = totalCr+ Double.parseDouble(cb.getAmount());
				}

			}
            
			PdfPCell total = new PdfPCell(new Paragraph("Total:",fontText));
			total.setBackgroundColor(BaseColor.LIGHT_GRAY);
			total.setBorder(Rectangle.NO_BORDER);
			total.setColspan(5);
			table.addCell(total);

			PdfPCell weightCell = new PdfPCell(new Paragraph(""+totalDr,fontText));
			weightCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			weightCell.setBorder(Rectangle.NO_BORDER);
			weightCell.setColspan(1);
			table.addCell(weightCell);

			PdfPCell brokrageCell = new PdfPCell(new Paragraph(""+totalCr,fontText));
			brokrageCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
			brokrageCell.setBorder(Rectangle.NO_BORDER);
			brokrageCell.setColspan(1);
			table.addCell(brokrageCell);
			
			PdfPCell net = new PdfPCell(new Paragraph("Net Amount:",fontText));
			net.setBackgroundColor(BaseColor.LIGHT_GRAY);
			net.setBorder(Rectangle.NO_BORDER);
			net.setColspan(6);
			table.addCell(net);

			PdfPCell netAmount = new PdfPCell(new Paragraph(""+(totalDr-totalCr),fontText));
			netAmount.setBackgroundColor(BaseColor.LIGHT_GRAY);
			netAmount.setBorder(Rectangle.NO_BORDER);
			netAmount.setColspan(1);
			table.addCell(netAmount);

			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ledgerReportPdf(){


		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		int sNo=0;
		double totalNetAmount =0.0;

		List<String> partyNames = PartyInfoDao.getAllPartyOnly();	

		String fileName = "D:\\bills\\debitreport.pdf";
		Document document = new Document();

		try {
			PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.addTitle("Debit Report");

			Font head = new Font(FontFamily.TIMES_ROMAN,18,Font.BOLD,BaseColor.BLUE);
			Font fontText = new Font(FontFamily.TIMES_ROMAN,11,Font.NORMAL);
			Font fonttable = new Font(FontFamily.UNDEFINED,9,Font.NORMAL);

			Font fontBrokrageBill = new Font(FontFamily.TIMES_ROMAN,14,Font.UNDERLINE);

			Paragraph brokragBillDetail = new Paragraph("Debit Report",fontBrokrageBill);
			brokragBillDetail.setAlignment(Element.ALIGN_CENTER);
			document.add(brokragBillDetail);

			Paragraph datepdf = new Paragraph("Date: "+formatter.format(date),fontText);
			datepdf.setAlignment(Element.ALIGN_CENTER);
			document.add(datepdf);


			document.add(new Paragraph("    "));

			PdfPTable table = new PdfPTable(6);
			table.setTotalWidth(new float[]{ 30,200,110, 70, 70,100});
			table.setLockedWidth(true);
			
			PdfPCell cell5 = new PdfPCell(new Paragraph("S.No.",fontText));
			cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell5.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell5);
			
			PdfPCell cell1 = new PdfPCell(new Paragraph("Ledger Name",fontText));
			cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell1.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell1);
			
			PdfPCell cell6 = new PdfPCell(new Paragraph("Opening Balance",fontText));
			cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell6.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell6);

			PdfPCell cell2 = new PdfPCell(new Paragraph("Dr Amt",fontText));
			cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell2.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell2);

			PdfPCell cell3 = new PdfPCell(new Paragraph("Cr Amt",fontText));
			cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell3.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell3);

			PdfPCell cell4 = new PdfPCell(new Paragraph("Net Amt",fontText));
			cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cell4.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell4);


			for(String partyName: partyNames){
				double totalBrokrage =0.0;
				double crAmount = 0.0;
				double drAmount = 0.0;
				double netAmount;

				PartyInfo partyInfo = PartyInfoDao.getRecordByName(partyName);
				List<BrokrageBill>  brokrageBills=DayBookDao.getBrokrageBillByFilter(partyName, "", "");
				for(BrokrageBill bb: brokrageBills){
					totalBrokrage = totalBrokrage + Double.parseDouble(bb.getBrokrage());
				}

				drAmount = drAmount + totalBrokrage;

				List<CashBook> cashBooks = CashBookDao.getCashBookByPartyNames(partyName);

				for(CashBook cb : cashBooks){
					if(cb.getStatus().equals("Payment")){
						drAmount = drAmount+ Double.parseDouble(cb.getAmount());
					}
					if(cb.getStatus().equals("Received")){
						crAmount = crAmount+ Double.parseDouble(cb.getAmount());
					}				

				}

				netAmount = Double.parseDouble(partyInfo.getOpeningBal())+drAmount-crAmount;
				sNo++;
				
				PdfPCell cell = new PdfPCell(new Paragraph(""+sNo,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(partyName,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(partyInfo.getOpeningBal(),fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(""+drAmount,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(""+crAmount,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(""+netAmount,fonttable));
				cell.setBorder(Rectangle.NO_BORDER);
				table.addCell(cell);
				
				totalNetAmount = totalNetAmount+netAmount;

			}
			
			PdfPCell total = new PdfPCell(new Paragraph("Total:",fontText));
			total.setBackgroundColor(BaseColor.LIGHT_GRAY);
			total.setBorder(Rectangle.NO_BORDER);
			total.setColspan(5);
			table.addCell(total);

			PdfPCell totalamt = new PdfPCell(new Paragraph(""+totalNetAmount,fontText));
			totalamt.setBackgroundColor(BaseColor.LIGHT_GRAY);
			totalamt.setBorder(Rectangle.NO_BORDER);
			totalamt.setColspan(1);
			table.addCell(totalamt);



			document.add(table);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Date getDate(String d){
		Date sqlDate = null;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dt;
		try {
			dt = simpleDateFormat.parse(d);
			sqlDate = new java.sql.Date(dt.getTime()); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sqlDate;
		
	}

}
