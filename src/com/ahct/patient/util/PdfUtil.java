package com.ahct.patient.util;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;

public class PdfUtil {
    public PdfUtil() {
    }
    
    public static final Color TABLE_HEADER_COLOR = new Color(153,204,255);
    public static final Color TABLE_DATA_COLOR = new Color(255,255,255);
    
    /** 
     * Name: doAlignment()
     * Functionality:   This method takes a phrase and returns a PdfPCell with the
     *                  content as aligned as per the 2nd argument. Phrase and int
     *                  iAlignment, where iAlignment should be derived from the Element
     *                  object like Element.ALIGN_CENTER
     * @param Phrase
     * @param iAlignment
     * Author: Uttam Kumar Biswas
     */
    public PdfPCell doAlignment(Phrase p1, int iAlignment){
        PdfPCell cell= new PdfPCell(p1);
        cell.setHorizontalAlignment(iAlignment);
        return cell;
    }
    
    /**
     * Name: removeBorder()
     * Author: Uttam Kumar Biswas
     * Functionality:   it takes a PdfPTable as argument and removes the border 
     *                  and returns a cell of type PdfPCell.
     * @param PdfPTable
     * @return PdfPCell
     */
    public PdfPCell removeBorder(PdfPTable subTable){
        PdfPCell cellToContainSubTable= new PdfPCell(subTable);
        cellToContainSubTable.setBorder(0);//this removes the border
        return cellToContainSubTable;
    }
    
    /**
     * Name: removeBorder()
     * Author: Uttam Kumar Biswas
     * @param Phrase
     * @return PdfPCell with no border.
     */
    public PdfPCell removeBorder(Phrase p1){
        PdfPCell cell= new PdfPCell(p1);
        cell.setBorder(0);//this removes the border
        return cell;
    }
    public PdfPCell removeBorder(Phrase p1,int iAlignment){
        PdfPCell cell= new PdfPCell(p1);
        cell.setHorizontalAlignment(iAlignment);
        cell.setBorder(0);//this removes the border
        return cell;
    }
    
    /**
     * Name: removeBorder()
     * Author: Uttam Kumar Biswas
     * Functionality:   It takes PdfPCell as an argument and remove it's border.
     * @param cell
     * @return PdfPCell
     */
    public PdfPCell removeBorder(PdfPCell cell){
        cell.setBorder(0);//this removes the border
        return cell;
    }
    
    /**
     * Name: removeBorderColSpan();
     * Author: Uttam Kumar Biswas
     * Functionality:   It takes Phrase and int as arguments and creates a cell with
     *                  no border and with colspan as per the 2nd argument.
     * @param p1
     * @param colSpan
     * @return
     */
    public PdfPCell removeBorderColSpan(Phrase p1,int colSpan){
        PdfPCell cell= new PdfPCell(p1);
        cell.setBorder(0);//this removes the border
        cell.setColspan(colSpan);
        return cell;
    }
    /**
     * Name: removeBorderColSpan()
     * Author: Uttam Kumar Biswas
     * Functionality:   It takes String and int as arguments and returns a cell 
     *                  of PdfPCell type with border removed and it will be of 
     *                  colspan of 2nd argument.
     * @param strData
     * @param colSpan
     * @return PdfPCell
     */
    public PdfPCell removeBorderColSpan(String strData,int colSpan){
        PdfPCell cell= new PdfPCell(new Phrase(strData));
        cell.setBorder(0);//this removes the border
        cell.setColspan(colSpan);
        return cell;
    }
    
    /**
     * Name: addCellColSpan();
     * Author: Uttam Kumar Biswas
     * @param strData
     * @param colSpan
     * @return PdfPCell
     */
   public PdfPCell addCellColSpan(String strData,int colSpan){
        PdfPCell cell= new PdfPCell(new Phrase(strData));
        cell.setBorder(0);//this removes the border
        cell.setColspan(colSpan);
        return cell;
    }
    
    /**
     * Name: getCellColSpanWithBorder()
     * Author: Uttam Kumar Biswas
     * Functionality:   returns a PdfPCell with no border with the content passed.
     *                  cell get a colspan of the value of 2nd argument.
     * @param strData
     * @param colSpan
     * @return PdfPCell
     */    
    public PdfPCell getCellColSpanWithBorder(String strData,int colSpan){
        PdfPCell cell= new PdfPCell(new Phrase(strData));
        cell.setColspan(colSpan);
        return cell;
    }
    
    /**
     * Name: getCellColSpanWithBorderFont()
     * Author: Uttam Kumar Biswas
     * Functionality:   returns a PdfPCell with border with the content passed,
     *                  which will have the font of 3rd argument. The cell get a
     *                  colspan of the value of 2nd argument.
     * @param strData
     * @param colSpan
     * @param Font
     * @return PdfPCell
     */    
    public PdfPCell getCellColSpanWithBorderFont(String strData,int colSpan,Font HeaderFont){
        PdfPCell cell= new PdfPCell(new Phrase(strData,HeaderFont));
        cell.setColspan(colSpan);
        return cell;
    }
    
    /**
     * Name: getCellColSpanWithFont()
     * Author: Uttam Kumar Biswas
     * Functionality:   returns a PdfPCell with no border with the content passed 
     *                  as String which is of passed font. The cell is is 
     *                  colspanned with the 2nd argument.
     * @param strData
     * @param colSpan
     * @param HeaderFont
     * @return PdfPCell
     */
    public PdfPCell getCellColSpanWithFont(String strData,int colSpan,Font HeaderFont){
        PdfPCell cell= new PdfPCell(new Phrase(strData,HeaderFont));
        cell.setBorder(0);//this removes the border
        cell.setColspan(colSpan);
        return cell;
    }
    public PdfPCell getCellColSpanWithFont2(String strData,int colSpan,Font HeaderFont){
        PdfPCell cell= new PdfPCell(new Phrase(strData,HeaderFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colSpan);
        return cell;
    }
    
    /**
     * Name: getCellColSpanWithFont() overloaded method
     * Author: Uttam Kumar Biswas
     * Functionality:   returns a PdfPCell with border with the content passed 
     *                  as String which is of passed font. The cell is 
     *                  colspanned with the 2nd argument.The cell content is 
     *                  alligned with the 4th argument.
     * @param strData
     * @param colSpan
     * @param HeaderFont
     * @return PdfPCell
     */    
    public PdfPCell getCellColSpanWithFont(String strData,int colSpan,Font HeaderFont,int iAlignment){
        PdfPCell cell= new PdfPCell(new Phrase(strData,HeaderFont));
        cell.setHorizontalAlignment(iAlignment);
        cell.setBorder(0);//this removes the border
        cell.setColspan(colSpan);
        return cell;
    }

    /**
     * Name: addBgColor()
     * Author: Uttam Kumar Biswas
     * Function:    This function returns a cell with the phrase passed and makes
     *              the color of the cell as of the passed color
     * @param ps
     * @param bgColor
     * @return PdfPCell
     */
    public PdfPCell addBgColor(Phrase ps,Color bgColor) 
    {
        //Color bgColor= new Color(153,204,255);
        PdfPCell cellTemp= new PdfPCell(ps);
        cellTemp.setBackgroundColor(bgColor);
        return cellTemp;
    }
    
    /**
     * Name: addBgColor()
     * Author: Uttam Kumar Biswas
     * @param ps
     * @param iAlignment
     * @param bgColor
     * @return PdfPCell with background color as the passed color. text alligned
     *          as per the 2nd argument.
     */
    public PdfPCell addBgColor(Phrase ps,int iAlignment,Color bgColor) 
    {
        //Color bgColor= new Color(153,204,255);
        PdfPCell cell= new PdfPCell(ps);
        cell.setHorizontalAlignment(iAlignment);
        cell.setBackgroundColor(bgColor);
        return cell;
    }
    
    /**
     * Name: addBgColor()
     * Author: Uttam Kumar Biswas
     * @param cell
     * @param bgColor
     * @return PdfPCell with background color as the passed color
     */
    public PdfPCell addBgColor(PdfPCell cell,Color bgColor) 
    {
        //Color bgColor= new Color(153,204,255);
        cell.setBackgroundColor(bgColor);
        return cell;
    }
    
    /**
     * Name: getCenterAlignedTableHeader()
     * Author: Uttam Kumar Biswas
     * @param strData
     * @param colSpan
     * @param HeaderFont
     * @param bgColor
     * @return PdfPCell
     */
    
    public PdfPCell getCenterAlignedTableHeader(String strData, int colSpan, Font HeaderFont, Color bgColor){
        PdfPCell cell= new PdfPCell(new Phrase(strData,HeaderFont));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(colSpan);
        cell.setBackgroundColor(bgColor);
        return cell;
    }
    
    /**
     * 
     * @param newLn
     * @param colSpan
     * @return
     */    
    public PdfPCell AddParagraphToCell(String newLn,int colSpan)
    {
        Paragraph p1=new Paragraph(newLn);
        PdfPCell cell1= new PdfPCell();
        cell1.addElement(p1);
        cell1.setColspan(colSpan);
        cell1.setBorder(0);
        return cell1;
    }
}
