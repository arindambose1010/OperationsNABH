<%@ page contentType="text/html;charset=ISO-8859-1" language="java"
    import="java.util.ResourceBundle, java.util.Locale, java.util.ArrayList, java.util.Hashtable,
                  java.util.StringTokenizer, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar,
                  java.text.DateFormat, java.io.File, java.io.FileOutputStream, java.util.List,com.ahct.common.vo.LabelBean,
                  java.util.StringTokenizer"
    isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">



<!-- Bootstrap -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/Newstyle.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">

<!-- Select2 -->
<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>

<!-- DataTables CSS -->
<link href="css/static.jquery.dataTables.css" rel="stylesheet">

<!-- DataTables JS (core) -->
<script src="js/jquery-dataTables.min.js"></script>

<!-- Buttons extension (must load AFTER DataTables) -->
<link rel="stylesheet" href="css/buttons.dataTables.min.css">
<script src="js/dataTables-buttons.min.js"></script>

<!-- JSZip (MANDATORY for Excel) -->
<script src="js/jszip.min.js"></script>

<!-- pdfmake (MANDATORY for PDF) -->
<script src="js/pdfmake.min.js"></script>
<script src="js/vfs_fonts.js"></script>

<!-- HTML5 export buttons -->
<script src="js/buttons-html5.min.js"></script>
<script src="js/buttons.print.min.js"></script>

<!-- Other scripts -->
<script src="js/jquery.msgBox.js"></script>

<style type="text/css">
.small-excel-button {
    padding: 5px 13px !important;
    font-weight: bold !important;
    margin-top: -27px !important;
    margin-left: 87.5%;
}
.small-pdf-button{
    padding: 5px 13px !important;
    font-weight: bold !important;
    margin-top: -27px !important;
}
table.dataTable tbody th, table.dataTable tbody td {
    padding: 5px 8px;
}
.table>thead>tr>th, 
.table>tbody>tr>th,
.table>tfoot>tr>th, 
.table>thead>tr>td, 
.table>tbody>tr>td, 
.table>tfoot>tr>td {
    padding: 8px;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
}
.border{
border : 1 px solid white;
}
#printable { display: none; }
	@page 
    {
        size:  auto;   /* auto is the initial value */
        margin: 5mm;  /* this affects the margin in the printer settings */
    }
    @media print
    {
        #non-printable { display: none; }
        #printable { display: flex;
 
       justify-content:center;
         }
        #logo img {
		display:none;
		
	}
	#logo:after {
		content:url(images/TG_logo2.png);
		
	}
      
    }
</style>



<script type="text/javascript">
//Tejasri -9073- Added this to download excel and pdf
var jq = jQuery.noConflict();
jq(document).ready(function () {
    jq('#miscActTbl').DataTable({ 
        // Only buttons, no search, no length menu, no info
        dom: 'B',  
        buttons: [
            {
                extend: 'excelHtml5',
                footer: true,
                text: 'Excel',
                title: 'Stock Indent Approval Report',
                className: 'small-excel-button'
            },
            {
                extend: 'pdfHtml5',
                footer: true,
                text: 'PDF',
                title: 'Stock Indent Approval Report',
                orientation: 'landscape',
                className: 'small-pdf-button'
            }
        ],

        paging: false,
        searching: false,
        info: false,
        ordering: false
    });
});
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";
}
//Chandana - 9008 - Added flag in the existing function to know the differentiation for critical or non critical
function generate(val,flg)
{
	var id="dispId"+val;
		fn_loadImage();
		var yearFlag='${yearFlag}';
	    
	    var dispId = document.getElementById(id).value;
		document.forms[0].action="patientDetailsNew.do?actionFlag=getstkIndentedPo&yearFlag="+yearFlag+"&indentedStock=Y&lowStock=Y&dispId="+dispId+"&flag="+flg;
    	document.forms[0].submit();
	
}
//Tejasri -9073- Added generateList function to get Entire list
function generateList(val, flg) {

    fn_loadImage();

    var yearFlag = '${yearFlag}';
    var total = "Y"; 

    document.forms[0].action =
        "patientDetailsNew.do?actionFlag=getstkIndentedPo"
        + "&yearFlag=" + yearFlag
        + "&indentedStock=Y"
        + "&lowStock=Y"
        + "&flag=" + flg
        + "&total=" + total;
    document.forms[0].submit();
}

 
</script>
</head>
<body style="font-size: 12px;">
<html:form action="/patientDetailsNew.do">
<div id="processImagetable" style="top:50%;left:45%;display:none;position:absolute;z-index:60;height:100%">
<table border="0"  width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" >
          <img src="images/Progress.gif" width="100" height="100" border="0" ></img>
         </div>
       </td>
     </tr>
  </table>
</div>
<table style="width:80%;margin:1% auto">
	<tr>
	<c:choose>
	    <c:when test="${yearFlag eq 'Y'}">
		<th colspan="8" class="tbheader">Indent Report (From Jan 2021)</th>
		</c:when>
		<c:when test="${indentedStock eq 'Y' && lowStock eq 'Y'}">
			<th colspan="8" class="tbheader">Drug Indent Approvals(Wellness Center)</th>
		</c:when>
		<c:when test="${indentedStock eq 'Y' && lowStock ne 'Y'}">
			<th colspan="8" class="tbheader">Indent Report (2019-2020) Based on Indented Stock </th>
		</c:when>
		 <c:otherwise>
		 <th colspan="4" class="tbheader" style="font-size:15px">Stock Indent Approvals</th>
		 
		</c:otherwise>
	</c:choose>	
	</tr>

	
</table>


<logic:present name="patientForm" property="indentList">
<bean:size id="size" name="patientForm" property="indentList"/>

<logic:greaterThan value="0" name="size"> 

<%-- <div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<p><b>Generated On Date:</b>${updDate} </p>
<br>
<span><b>Download as: &nbsp;&nbsp;</b></span>
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/>
</div> 
<div style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<a href="javascript:fn_print('miscActTbl','','Indent Report');" title="Print"><span style="padding:2%" class="glyphicon glyphicon-print"></span></a>  		    
</div> --%>

<table class="table" id="miscActTbl" cellspacing="1" cellpadding="1" style="text-align:center;margin:1 auto;width: 94%;width: 94%;font-size: 107%;margin-top: 1%;font-family: Tahoma, Geneva, sans-serif;">
<thead>
<tr>
<th class="tbheader1 border" id = "sno">S.NO<!-- <input type="checkbox" id="select_all"/><input class="checkAll" type="checkbox" onclick="checkAll(this)"></th> --></th>
 <th class="tbheader1 border" >WELLNESS CENTER NAME</th> 
<!-- <th class="tbheader1 border" >WELLNESS CENTER</th> -->
 <th class="tbheader1 border" >TOTAL INDENT VALUE</th> 
<th class="tbheader1 border" >NUMBER OF INDENT ITEMS</th>
<!-- Chandana - 9008 - Added below for critical and non critical indent values -->
<th class="tbheader1 border">CRITICAL INDENT VALUE</th>
<th class="tbheader1 border">NUMBER OF CRITICAL INDENT ITEMS</th>
<th class="tbheader1 border">NON CRITICAL INDENT VALUE</th>
<th class="tbheader1 border">NUMBER OF NON CRITICAL INDENT ITEMS</th>

<%-- <c:choose>
<c:when test="${indentedStock eq 'Y'}">
	<th class="tbheader1 border" >IDEAL STOCK TO BE KEPT</th>
	<th class="tbheader1 border" >STOCK AVAILABLE</th>
	<th class="tbheader1 border" >INDENTED BUT NOT RECEIVED</th>
<!-- 	<th class="tbheader1 border" >NOTIONAL STOCK AVAILABLE</th>
	<th class="tbheader1 border" >NOTIONAL DEFICIT VIS A VIS IDEAL STOCK</th> -->
	<th class="tbheader1 border" >FURTHER INDENT TO BE PLACED</th>
	<th class="tbheader1 border" >RATE CONTRACT PRICE</th>
	<th class="tbheader1 border" >INDENT VALUE</th>
</c:when>
<c:otherwise>
<th class="tbheader1 border" >AVERAGE MONTHLY CONSUMPTION</th>
<th class="tbheader1 border" >REQUIRED STOCK FOR NEXT 3 MONTHS</th>
<th class="tbheader1 border" >AVAILABLE STOCK</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED</th>
<th class="tbheader1 border" >RATE CONTRACT PRICE</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED VALUE</th>
</c:otherwise>
</c:choose> --%>
<!-- <th class="tbheader1 border" >RATE CONTRACT YEAR</th> -->
</tr>
</thead>
<tbody>
<logic:iterate name="patientForm" property="indentList" id="labelBean" indexId="cnt">
        

<tr>
<td align="left" class="tbcellBorder" > ${startIndex+cnt+1} </td>
            <input type="hidden" id="status${cnt+1}" value="<bean:write name="labelBean" property="INTERNALSTATUS"/>">
           <input type="hidden" id="dispId${cnt+1}" value="<bean:write name="labelBean" property="DISP_ID"/>">
<logic:notEmpty name="labelBean" property="DISP_NAME">
<td align="left" class="tbcellBorder" > <bean:write name="labelBean" property="DISP_NAME"/></td><!-- Chandana - 9008 - Removed onclick anchor tag for disp id, as we are giving separate clicks for each -->
</logic:notEmpty>
<logic:empty name="labelBean" property="DISP_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 

 
<logic:notEmpty name="labelBean" property="TOTAL_INDENT_VALUE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="TOTAL_INDENT_VALUE"/></td>
<input type="hidden" id="indentVal${cnt+1}" value="<bean:write name="labelBean" property="TOTAL_INDENT_VALUE"/>" >

</logic:notEmpty>
<logic:empty name="labelBean" property="TOTAL_INDENT_VALUE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="NO_INDENT_ITEMS">
<td align="left" class="tbcellBorder" onkeyup="checkAlphaNumeric(this);" ><a href="javascript:generate(${cnt+1},'T');"><bean:write name="labelBean" property="NO_INDENT_ITEMS"/></a></td>
<input type="hidden" id="drugname${cnt+1}" value="<bean:write name="labelBean" property="NO_INDENT_ITEMS"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="NO_INDENT_ITEMS">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 <!-- Chandana - 9008  - Added below for Critical drugs indent -->
<logic:notEmpty name="labelBean" property="TOT_CRTICALINDNT_VALUE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="TOT_CRTICALINDNT_VALUE"/></td>
	<input type="hidden" id="indentVal${cnt+1}" value="<bean:write name="labelBean" property="TOT_CRTICALINDNT_VALUE"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="TOT_CRTICALINDNT_VALUE">
	<td align="left" class="tbcellBorder" >-NA-</td>
</logic:empty>
 
<logic:notEmpty name="labelBean" property="NO_CRTICALINDNT_ITEMS">
	<td align="left" class="tbcellBorder" ><a href="javascript:generate(${cnt+1},'Y');"><bean:write name="labelBean" property="NO_CRTICALINDNT_ITEMS"/></a></td>
	<input type="hidden" id="indentVal${cnt+1}" value="<bean:write name="labelBean" property="NO_CRTICALINDNT_ITEMS"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="NO_CRTICALINDNT_ITEMS">
	<td align="left" class="tbcellBorder" >-NA-</td>
</logic:empty>

 <!-- Chandana - 9008  - Added below for Non Critical drugs indent --> 
<logic:notEmpty name="labelBean" property="TOT_NONCINDENT_VALUE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="TOT_NONCINDENT_VALUE"/></td>
	<input type="hidden" id="indentVal${cnt+1}" value="<bean:write name="labelBean" property="TOT_NONCINDENT_VALUE"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="TOT_NONCINDENT_VALUE">
	<td align="left" class="tbcellBorder" >-NA-</td>
</logic:empty>
 
<logic:notEmpty name="labelBean" property="NO_NONCINDENT_ITEMS">
	<td align="left" class="tbcellBorder" ><a href="javascript:generate(${cnt+1},'N');"><bean:write name="labelBean" property="NO_NONCINDENT_ITEMS"/></a></td>
	<input type="hidden" id="indentVal${cnt+1}" value="<bean:write name="labelBean" property="NO_NONCINDENT_ITEMS"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="NO_NONCINDENT_ITEMS">
	<td align="left" class="tbcellBorder" >-NA-</td>
</logic:empty>

</tr>
</logic:iterate> 
    </tbody>
    <!-- Total row -->
    <tfoot>
    <tr> 
        <td></td>
        <td class="totheader"><b>Total</b></td>
         <td style="text-align:left"><b>${noIndentItems}</b></td>
 
	    <td style="text-align:left" onkeyup="checkAlphaNumeric(this);">
	        <a href="javascript:generateList(${cnt+2},'T');">
	            <b>${totalIndentValue}</b>
	        </a>
	    </td>
    
        <td style="text-align:left"><b>${noCIndentItems}</b></td>
        
        <td style="text-align:left" onkeyup="checkAlphaNumeric(this);">
        <a href="javascript:generateList(${cnt+2},'Y');">
            <b>${totalCIndentValue}</b>
        </a>
        </td>
        
      
        
        <td style="text-align:left"><b>${noNcIndentItems}</b></td>
         <td style="text-align:left" onkeyup="checkAlphaNumeric(this);">
        <a href="javascript:generateList(${cnt+2},'N');">
            <b>${totalNcIndentValue}</b>
        </a>
        </td>
       
    </tr>
   </tfoot>
</table>


</logic:greaterThan>
<logic:lessThan value="0" name="size">
<div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
            </logic:lessThan>
</logic:present>


<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />
<input type="hidden" id="noIndentItems" value="${noIndentItems}" >
<input type="hidden" id="totalIndentValue" value="${totalIndentValue}" >


<div id="footer" style="display:none; position: fixed; bottom:0;">

		 <table style="    width: 100%;">
		 <tr>
		 <td style="font: normal; font-size: 10px;"></td>
		 </tr>
		 </table>
	 </div>			
	<div id="printable">
	<div id="headerPrint">
<table style=" font-family:Trebuchet MS;    width: 100%;border-bottom: 3px solid rgba(6, 170, 26, 1);">
<tr>
<td style="width:100%;text-align:center;padding-left: 13%;"><font style="font-size:20px"><b>Reports</b></font>
<br>
<br>
<span style="font-size: 18px;"><b>GOVERNMENT OF TELANGANA</b></span>

</td>
<td id="logo">
<img src="images/TG_logo2.png" alt="..." height="80px" width="120px" style="float:right" >
</td>
</tr>
<tr>
<br>
<c:choose>
 <c:when test="${yearFlag eq 'Y'}">
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (FROM JAN 2021)
</b></span></td></c:when>
<c:when test="${indentedStock eq 'Y'}">
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (2019-2020) BASED ON INDENTED STOCK
</b></span></td>
</c:when>
 <c:otherwise>
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (2019-2020)
</b></span></td></c:otherwise>
</c:choose>
</tr>
<tr >
<td colspan="2" style="width:100%;text-align:right;">
<span >Report Generated On :<span id="dateToday" ></span></span>
</td>
</tr>

</table>
<br>
</div> 
       
    </div>
</html:form>


</body>
</html>