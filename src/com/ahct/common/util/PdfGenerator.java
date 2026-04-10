package com.ahct.common.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.ahct.common.vo.LabelBean;
import com.ahct.patient.form.PatientForm;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.Font;


public class PdfGenerator {
	static Logger goslogger = Logger.getLogger(PdfGenerator.class);
	
	
	public static byte[] getBytesFromFile(File file)
	        throws IOException
	{
	        FileInputStream fileinputstream = new FileInputStream(file);
	        long l = file.length();
	        if(l <= 0x50fffffffL);
	        byte abyte0[] = new byte[(int)l];
	        int i = 0;
	        for(int j = 0; i < abyte0.length && (j = fileinputstream.read(abyte0, i, abyte0.length - i)) >= 0; i += j);
	        if(i < abyte0.length)
	        {
	            throw new IOException("Could not completely read file " + file.getName());
	        } else
	        {
	            fileinputstream.close();
	            return abyte0;
	        }
	 }
	//Tejasri: cr-8927- generating pdf
	public static void prepareReport(File lPdffile,
			List<LabelBean> ReportList, PatientForm patientForm, String headingVal)
			throws FileNotFoundException, DocumentException {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(lPdffile));
			document.open();

			// ===== TITLE =====
			Paragraph heading = new Paragraph(headingVal,
			        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, 0, Color.BLUE));
			heading.setAlignment(Element.ALIGN_CENTER);
			heading.setSpacingBefore(18F);
			heading.setSpacingAfter(5F);
			document.add(heading);

			// ===== SYSTEM DATE WITH TIMESTAMP (LEFT SIDE) =====
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currentDateTime = sdf.format(new Date());

			Paragraph timeStamp = new Paragraph(
			        "Report Generated On : " + currentDateTime,
			        FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, Color.BLACK));

			timeStamp.setAlignment(Element.ALIGN_RIGHT);
			timeStamp.setSpacingAfter(10F);

			document.add(timeStamp);

			int cols = 11;
			PdfPTable table = new PdfPTable(cols);
			table.setHeaderRows(1);
			table.setWidthPercentage(110);

			String[] headers = { "S.NO", "PO NO", "PO DATE", "DISTRIBUTOR NAME",  "DRUG NAME",
					"DRUG TYPE", "AVAILABLE STOCK", "PO QTY",
					"RECEIVED STOCK", "PENDING STOCK", "PO STATUS"};
			Color headersColor = new Color(70, 130, 180);
			for (int i = 0; i < headers.length; i++) {
				PdfPCell cell = new PdfPCell(new Phrase(headers[i],
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, 0, Color.WHITE)));
				cell.setBackgroundColor(headersColor);
				cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				cell.setVerticalAlignment(Paragraph.ALIGN_CENTER);
				table.addCell(cell);
			}

			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);
			for (int i = 0; i < ReportList.size(); i++) {
				table.addCell(createCell(new PdfPCell(new Phrase((i + 1) + "",
						font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(ReportList.get(i).getPOID()),
						font)), i));
				Date poDate = ReportList.get(i).getPODATE();
				String formattedDate = "";

				if (poDate != null) {
				    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				    formattedDate = sdf1.format(poDate);
				}

				table.addCell(createCell(new PdfPCell(
				        new Phrase(formattedDate, font)), i));
				table.addCell(createCell(new PdfPCell(
						new Phrase(removeNullString(ReportList.get(i)
								.getDSTRBTRNAME()), font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(ReportList.get(i).getDRUGNAME()),
						font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(ReportList.get(i).getDRUGTYPE()),
						font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(ReportList.get(i).getAVL()),
						font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(ReportList.get(i).getLVL()),
						font)), i));
				String recQty = ReportList.get(i).getRECQTY();

				if (recQty == null || recQty.trim().isEmpty()) {
				    recQty = "NA";
				}
				table.addCell(createCell(new PdfPCell(
				        new Phrase(recQty, font)), i));
				table.addCell(createCell(new PdfPCell(
						new Phrase(removeNullString(ReportList.get(i)
								.getPOQTY()), font)), i));
				table.addCell(createCell(new PdfPCell(
						new Phrase(removeNullString(ReportList.get(i)
								.getPARTFULL()), font)), i));
				
			}

//			document.setPageCount(drugExpiryList.size() > 50 ? (drugExpiryList
//					.size() / 50) + 1 : 1);
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	public static void intendReport(File lPdffile,
			List<LabelBean> indentedPOList, PatientForm patientForm, String headingVal)
			throws FileNotFoundException, DocumentException {
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(lPdffile));
			document.open();

			// ===== TITLE =====
			Paragraph heading = new Paragraph(headingVal,
			        FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, 0, Color.BLUE));
			heading.setAlignment(Element.ALIGN_CENTER);
			heading.setSpacingBefore(18F);
			heading.setSpacingAfter(5F);
			document.add(heading);

			// ===== SYSTEM DATE WITH TIMESTAMP (LEFT SIDE) =====
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String currentDateTime = sdf.format(new Date());

			Paragraph timeStamp = new Paragraph(
			        "Report Generated On : " + currentDateTime,
			        FontFactory.getFont(FontFactory.HELVETICA, 9, Font.NORMAL, Color.BLACK));

			timeStamp.setAlignment(Element.ALIGN_RIGHT);
			timeStamp.setSpacingAfter(10F);

			document.add(timeStamp);

			int cols = 5;
			PdfPTable table = new PdfPTable(cols);
			table.setHeaderRows(1);
			table.setWidthPercentage(110);

			String[] headers = { "S.NO", "PO DATE", "PO NO", "STATUS",  "WELLNESS CENTER"};
			Color headersColor = new Color(70, 130, 180);
			for (int i = 0; i < headers.length; i++) {
				PdfPCell cell = new PdfPCell(new Phrase(headers[i],
						FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9, 0, Color.WHITE)));
				cell.setBackgroundColor(headersColor);
				cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
				cell.setVerticalAlignment(Paragraph.ALIGN_CENTER);
				table.addCell(cell);
			}

			Font font = FontFactory.getFont(FontFactory.HELVETICA, 7);
			for (int i = 0; i < indentedPOList.size(); i++) {
				table.addCell(createCell(new PdfPCell(new Phrase((i + 1) + "",
						font)), i));
				Date poDate = indentedPOList.get(i).getPODATE();
				String formattedDate = "";

				if (poDate != null) {
				    SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
				    formattedDate = sdf1.format(poDate);
				}

				table.addCell(createCell(new PdfPCell(
				        new Phrase(formattedDate, font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(indentedPOList.get(i).getPOID()),
						font)), i));
				table.addCell(createCell(new PdfPCell(
						new Phrase(removeNullString(indentedPOList.get(i)
								.getPARTFULL()), font)), i));
				table.addCell(createCell(new PdfPCell(new Phrase(
						removeNullString(indentedPOList.get(i).getDISPNAME()),
						font)), i));
				
			}

//			document.setPageCount(drugExpiryList.size() > 50 ? (drugExpiryList
//					.size() / 50) + 1 : 1);
			document.add(table);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static <T> void preparePdf(File lPdffile, List<T> list, String head) throws Exception {
		 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
		 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
		     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
		     document.open();
		   
		          
		     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
	         heading.setAlignment(Paragraph.ALIGN_CENTER);
	         heading.setSpacingBefore(15F);
	         heading.setSpacingAfter(10F);
	         document.add(heading);
	         PdfPTable table=new PdfPTable(fields.length);
	         
	        
	         
	         for(int i=0;i<list.size();i++)
	 		 {
	        	 for(int j=0;j<fields.length;j++)
		 		 {
	        		 
	        		 fields[j].setAccessible(true);
	        		 if(fields[j].get(list.get(i)) != null)
	        		 {
	        		 table.addCell(fields[j].get(list.get(i)).toString());
	        		 }
		 		 }
	 		 }
	         document.add(table);		
	 		 document.close(); 
	}
	
	
	
	public static <T> void preparePdfSpecificLength(File lPdffile, List<T> list, String head , int length) throws Exception {
	 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
	 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
	     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
	     document.open();
	    
	     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
        heading.setAlignment(Paragraph.ALIGN_CENTER);
        heading.setSpacingBefore(15F);
        heading.setSpacingAfter(10F);
        document.add(heading);
        PdfPTable table=new PdfPTable(length);
       
       
        for(int i=0;i<list.size();i++)
		 {
       	 for(int j=0;j<fields.length;j++)
	 		 { 
       		 
       		 fields[j].setAccessible(true);
       		 if(fields[j].get(list.get(i)) != null)
       		 {
       		 table.addCell(fields[j].get(list.get(i)).toString());
       		 }
       		
		 }
		 }
        document.add(table);		
		 document.close(); 
}
	
	
	
	
	public static <T> void preparePdfRTIreport(File lPdffile, List<T> list, String head , int length) throws Exception {
	 	 Document document = new Document(PageSize.LEGAL, 10, 10, 10, 10);
	 	 Field[] fields = list.get(0).getClass().getDeclaredFields();
	     PdfWriter.getInstance(document,new FileOutputStream(lPdffile));
	     document.open();
	    
	     Paragraph heading = new Paragraph(head , FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,0,Color.BLUE));
       heading.setAlignment(Paragraph.ALIGN_CENTER);
       heading.setSpacingBefore(15F);
       heading.setSpacingAfter(10F);
       document.add(heading);
       PdfPTable table=new PdfPTable(length);
       String col="RequestId~name~emailId~mobileno~Reply";
       String headValues[] = col.split("~");
       
      
       
       if(headValues!=null && headValues.length>0)
       {
    	 for(int headSize=0;headSize<headValues.length;headSize++)
    		 table.addCell(headValues[headSize]);
	 
       }
       for(int i=0;i<list.size();i++)
		 {
    	   
      	 for(int j=0;j<fields.length;j++)
	 		 { 
      		 
      		 fields[j].setAccessible(true);
      		 if(fields[j].get(list.get(i)) != null)
      		 {
      		 table.addCell(fields[j].get(list.get(i)).toString());
      		 }
      		
		 }
		 }
       document.add(table);		
		 document.close(); 
}
	private static PdfPCell createCell(PdfPCell pdfPCell, int i) {
		if (i % 2 == 0) {
			pdfPCell.setBackgroundColor(Color.WHITE);
		} else {
			pdfPCell.setBackgroundColor(new Color(229, 228, 226));
		}
		pdfPCell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
		pdfPCell.setVerticalAlignment(Paragraph.ALIGN_CENTER);
		return pdfPCell;
	}
	
	private static Cell createCell(Cell cell, int i) {
		if (i % 2 == 0) {
			cell.setBackgroundColor(Color.WHITE);
		} else {
			cell.setBackgroundColor(new Color(229, 228, 226));
		}
		cell.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
		cell.setVerticalAlignment(Paragraph.ALIGN_CENTER);
		return cell;
	}
	
	private static String removeNullString(String data) {
		if (data == null)
			return "";
		else
			return data;
	}
	
	}

