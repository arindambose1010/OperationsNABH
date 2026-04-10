package com.ahct.patient.util;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfPatient;
import com.ahct.patient.vo.LabelVO;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;



public class PatientInvestReportPdfGenerator {

	 @SuppressWarnings({ "deprecation", "unused" })
		public static void genPdfInvestReport(File lPdffile,List<LabelVO> reportData, List<LabelBean> investList, EhfPatient ehfPatient, HashMap<String, Object> lParameterMap) throws DocumentException {
		 try{
			 String gender=null;			
				SimpleDateFormat sdfDtTm=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
				SimpleDateFormat sdfDt = new SimpleDateFormat("dd-MM-yyyy");
				
			 if(ehfPatient.getGender()!=null){
				 if(ehfPatient.getGender().equalsIgnoreCase("M")){
					 gender="Male";
				 }
				 else
					 gender="Female";
			 }
			 else
				 gender=ehfPatient.getGender();
			 
			String signedName[] = lParameterMap.get("signedName").toString().split("~");
//			System.out.println(signedName[0]+"~"+signedName[1]);
          Document document = new Document(PageSize.A4, 10, 10, 10, 10);
          PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(lPdffile));
          
  	    	document.open();
  	    	Image signImage = null;
  	    	Image ltImage = Image.getInstance((String)lParameterMap.get("ltImagePath")); 
  	    	ltImage.scaleAbsolute(40, 40); 
  	    	Image rtImage = Image.getInstance((String)lParameterMap.get("rtImagePath")); 
  	    	rtImage.scaleAbsolute(40, 40);
  	    	if((String)lParameterMap.get("signImagePath")!=null){
  	    	signImage= Image.getInstance((String)lParameterMap.get("signImagePath"));
  	    	signImage.scaleAbsolute(100, 50); 
  	    	}
  	    	PdfPCell cell;
    	    PdfPTable table1 = new PdfPTable(3);
    	    table1.setWidthPercentage(90);
    	    table1.setWidths(new int[]{20,60,20});
    	    table1.setHorizontalAlignment(1);
//    	    String monYr=reportData.get(0).getINVESTIGATIONNAME().toString();
    	    Paragraph heading = new Paragraph("TELANGANA GOVERNMENT \n EMPLOYEE AND JOURNALIST HEALTH SCHEME" , FontFactory.getFont("TIMES_ROMAN", 12.0F, Font.BOLD));
    	    cell=new PdfPCell(ltImage);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	    table1.addCell(cell);
    	    cell=new PdfPCell(heading);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//    	    cell.setPaddingTop(40);
    	    table1.addCell(cell);
    	    cell=new PdfPCell(rtImage);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    table1.addCell(cell);
    	    document.add(table1);
    	    
    	    Paragraph ph1 = new Paragraph();
    	    ph1.add(new Chunk("\n"));
//            document.add(ph1);
            
            PdfPTable table12 = new PdfPTable(1);
            table12.setWidthPercentage(90);
            table12.setWidths(new int[]{100});
            table12.setHorizontalAlignment(1);
            cell=new PdfPCell(new Paragraph("INVESTIGATION REPORT", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
            cell.setBorder(PdfPCell.BOTTOM);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table12.addCell(cell);
            document.add(table12);
            
//    	    ph1.add(new Chunk("\n"));
            document.add(ph1);
            
//            PdfPTable table10 = new PdfPTable(1);
//            table10.setWidthPercentage(90);
//            table10.setWidths(new int[]{100});
//            table10.setHorizontalAlignment(1);
//            cell=new PdfPCell(new Paragraph("Patient Details", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
//            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            cell.setBorder(PdfPCell.NO_BORDER);
//            table10.addCell(cell);
//            document.add(table10);
            
            PdfPTable table2 = new PdfPTable(6);
            table2.setWidthPercentage(90);
    	    table2.setWidths(new int[]{22,6,22,22,6,22});
    	    table2.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Patient Name", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table2.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table2.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getName(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
//    	    cell.setPaddingTop(40);
    	    table2.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("Patient No", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table2.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table2.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getPatientId(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table2.addCell(cell);
    	    document.add(table2);
    	    
    	    PdfPTable table3 = new PdfPTable(6);
            table3.setWidthPercentage(90);
    	    table3.setWidths(new int[]{22,6,22,22,6,22});
    	    table3.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Employee/Pensioner Card No", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getEmployeeNo(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("Health Card No", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getCardNo(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table3.addCell(cell);
    	    document.add(table3);
    	    
    	    PdfPTable table4 = new PdfPTable(6);
            table4.setWidthPercentage(90);
    	    table4.setWidths(new int[]{22,6,22,22,6,22});
    	    table4.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Gender", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(gender, FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("District", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    cell=new PdfPCell(new Paragraph((String)lParameterMap.get("patientDistrict"), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table4.addCell(cell);
    	    document.add(table4);
    	    
    	    PdfPTable table7 = new PdfPTable(6);
            table7.setWidthPercentage(90);
    	    table7.setWidths(new int[]{22,6,22,22,6,22});
    	    table7.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Date Of Birth", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(sdfDt.format(ehfPatient.getEnrollDob()), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("Age", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getAge().toString()+"Y "+ehfPatient.getAgeMonths().toString()+"M "+ehfPatient.getAgeDays().toString()+"D", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table7.addCell(cell);
    	    document.add(table7);
    	    
    	    PdfPTable table8 = new PdfPTable(6);
            table8.setWidthPercentage(90);
    	    table8.setWidths(new int[]{22,6,22,22,6,22});
    	    table8.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Relationship", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    cell=new PdfPCell(new Paragraph((String)lParameterMap.get("relationName"), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("Contact No", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(ehfPatient.getContactNo().toString(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table8.addCell(cell);
    	    document.add(table8);
    	    
    	    PdfPTable table9 = new PdfPTable(6);
            table9.setWidthPercentage(90);
    	    table9.setWidths(new int[]{22,6,22,22,6,22});
    	    table9.setHorizontalAlignment(1);
    	    cell=new PdfPCell(new Paragraph("Registration date and time", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(sdfDtTm.format(ehfPatient.getRegHospDate()), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    cell=new PdfPCell(new Paragraph("Wellness Center Name", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    cell=new PdfPCell(new Paragraph(":", FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    cell=new PdfPCell(new Paragraph((String)lParameterMap.get("dispName"), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
    	    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	    cell.setBorder(PdfPCell.NO_BORDER);
    	    table9.addCell(cell);
    	    document.add(table9);
    	    document.add(ph1);
            for(int i=0;i<investList.size();i++){
            	PdfPTable table5 = new PdfPTable(1);
            	table5.setWidthPercentage(90);
        	    table5.setWidths(new int[]{100});
        	    table5.setHorizontalAlignment(1);
        	    cell=new PdfPCell(new Paragraph(investList.get(i).getVALUE(), FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
        	    cell.setBorder(PdfPCell.NO_BORDER);
        	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        	    cell.setPaddingTop(40);
        	    table5.addCell(cell);
        	    document.add(table5);
        	    
        	    PdfPTable table11 = new PdfPTable(3);
        	    table11.setWidthPercentage(90);
        	    table11.setWidths(new int[]{33,33,33});
        	    table11.setHorizontalAlignment(1);
        	    cell=new PdfPCell(new Paragraph("Description of the field", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
//        	    cell.setBorder(PdfPCell.NO_BORDER);
        	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        	    cell.setPaddingTop(40);
        	    table11.addCell(cell);
        	    cell=new PdfPCell(new Paragraph("Result", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
//        	    cell.setBorder(PdfPCell.NO_BORDER);
        	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        	    cell.setPaddingTop(40);
        	    table11.addCell(cell);
        	    cell=new PdfPCell(new Paragraph("Reference Interval", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
//        	    cell.setBorder(PdfPCell.NO_BORDER);
        	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//        	    cell.setPaddingTop(40);
        	    table11.addCell(cell);        	    
        	    document.add(table11);
        	    
        	    for(int j=0;j<reportData.size();j++){
        	    	if(investList.get(i).getID().equalsIgnoreCase(reportData.get(j).getINVESTID())){
        	    	PdfPTable table6 = new PdfPTable(3);
                	table6.setWidthPercentage(90);
            	    table6.setWidths(new int[]{33,33,33});
            	    table6.setHorizontalAlignment(1);
            	    cell=new PdfPCell(new Paragraph(reportData.get(j).getINVESTIGATIONNAME(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
            	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	    table6.addCell(cell);
            	    cell=new PdfPCell(new Paragraph(reportData.get(j).getVALUEOFFIELD(), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
            	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	    table6.addCell(cell);
            	    cell=new PdfPCell(new Paragraph(reportData.get(j).getREFERENCEINTERVAL().replace(", ", "\r"), FontFactory.getFont("TIMES_ROMAN", 11.0F)));
            	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            	    table6.addCell(cell);
            	    document.add(table6);
        	    	}
        	    }
        	    document.add(ph1);
            }
            PdfPTable table14 = new PdfPTable(3);
            table14.setWidthPercentage(90);
            table14.setWidths(new int[]{33,33,33});
            table14.setHorizontalAlignment(1);
            cell=new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table14.addCell(cell);
            document.add(table14);
            cell=new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table14.addCell(cell);
            document.add(table14);
            if(signImage!=null)
            cell=new PdfPCell(signImage);
            else
            cell=new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table14.addCell(cell);
            document.add(table14);
            PdfPTable table13 = new PdfPTable(3);
            table13.setWidthPercentage(90);
            table13.setWidths(new int[]{33,33,33});
            table13.setHorizontalAlignment(1);
            cell=new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table13.addCell(cell);
            document.add(table13);
            cell=new PdfPCell();
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table13.addCell(cell);
            document.add(table13);
            if(signedName!=null)
            cell=new PdfPCell(new Paragraph(signedName[0]+" ("+signedName[1]+", "+signedName[2]+")\nMicrobiologist", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
            else
            cell=new PdfPCell(new Paragraph("Microbiologist", FontFactory.getFont("TIMES_ROMAN", 11.0F, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(PdfPCell.NO_BORDER);
            table13.addCell(cell);
            document.add(table13);
            document.close(); 
//			 System.out.println("In PDF generator code");
		 }
		 catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	public static File createFile(String lStrDirPath,String lStrFileName) throws IOException 
  	{
  		//Making Directory		  
  		File file = new File(lStrDirPath);
  		if (!file.exists()) 
  		{
  			file.mkdirs();
  		}
  		File lfile = new File(lStrFileName);
  		if(!lfile.exists())
  		{
  			lfile.createNewFile();
  		}
  		else
  		{
  			lfile.delete();
  			lfile.createNewFile();
  		}
  		return lfile;
  		
  	}
	
	public static  PdfPCell setBackGroundColor(String str)
    {
    	Font bf12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10,0,new Color(255,255,255));
    	PdfPCell cell = new PdfPCell(new Paragraph(str,bf12));
    	cell.setBackgroundColor(new Color(0, 123, 202));
    	return cell;
    }
    
 public static  PdfPCell rightAlignPdfCell(String str)
    {
    	 Font bf12 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 8); 
    	PdfPCell cell = new PdfPCell(new Paragraph(str,bf12));
    	cell.setHorizontalAlignment(Element.ALIGN_RIGHT);	
    	cell.setFixedHeight(15);
    	cell.setBorderWidth(1);
    	cell.setBackgroundColor(new Color(248, 248, 248));
    	return cell;
    	
    }
    
    public static  PdfPCell leftAlignPdfCell(String str)
    {
    	Font bfBold12 = FontFactory.getFont(FontFactory.HELVETICA, 8); 
    	PdfPCell cell = new PdfPCell(new Paragraph(str,bfBold12));
    	cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    	cell.setFixedHeight(15);
    	cell.setBorderWidth(1);
    	cell.setBackgroundColor(new Color(248, 248, 248));
    	return cell;
    	
    }
 
    
    public static PdfPCell leftAlignPdfCellDet (String str)
    {
    	Font bfBold12 = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 8); 
    	PdfPCell cell = new PdfPCell(new Paragraph(str,bfBold12));
    	cell.setBorder(PdfPCell.NO_BORDER);
    	cell.setHorizontalAlignment(Element.ALIGN_LEFT);	
    	return cell;
    }
    
    public static PdfPCell leftAlignPdfCellNoDet (String str)
    {
    	Font bfBold12 = FontFactory.getFont(FontFactory.HELVETICA, 8); 
    	PdfPCell cell = new PdfPCell(new Paragraph(str,bfBold12));
    	cell.setBorder(PdfPCell.NO_BORDER);
    	cell.setHorizontalAlignment(Element.ALIGN_LEFT);	
    	return cell;
    }
}
