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
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/Newstyle.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<link href="css/static.jquery.dataTables.css" rel="stylesheet">
<script src="js/jquery-dataTables.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/select2.min.js"></script>

<style type="text/css">
.blueFont {
    color: blue !important;
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
    .tbheader1  {
background-color: #20996c;
color: black;
text-align: center;
height: 6%;
}
.tbheader  {
background:#20996c;
repeat-x; 
color: #fff;
text-align: center;
}
.but  {
background-color: #20AFA7 !important;
color: #fff !important;

}
#teamDtlsTbl {
    width: 100%;
    table-layout: fixed; /* fixed widths for all columns */
    font-size: 7px;
}

#teamDtlsTbl th, 
#teamDtlsTbl td {
    text-align: center;
    padding: 4px 6px;
    border: 1px solid #000;
    white-space: normal;          /* Allow wrapping */
    word-wrap: break-word;        /* Break long words */
    overflow-wrap: break-word;    /* Modern version */
    font-size: 12px;
}

/* Adjust column widths */
#teamDtlsTbl th:nth-child(1), #teamDtlsTbl td:nth-child(1) { width: 4%; }   /* S.No */
#teamDtlsTbl th:nth-child(2), #teamDtlsTbl td:nth-child(2) { width: 10%; }   /* PO No */
#teamDtlsTbl th:nth-child(3), #teamDtlsTbl td:nth-child(3) { width: 8%; }   /* PO Date */
#teamDtlsTbl th:nth-child(4), #teamDtlsTbl td:nth-child(4) { width: 16%; }  /* Distributor Name */
#teamDtlsTbl th:nth-child(5), #teamDtlsTbl td:nth-child(5) { width: 15%; }  /* Drug Name */
#teamDtlsTbl th:nth-child(6), #teamDtlsTbl td:nth-child(6) { width: 7%; }   /* Drug Type */
#teamDtlsTbl th:nth-child(7), #teamDtlsTbl td:nth-child(7) { width: 7%; }   /* Available Stock */
#teamDtlsTbl th:nth-child(8), #teamDtlsTbl td:nth-child(8) { width: 7%; }   /* PO Qty */
#teamDtlsTbl th:nth-child(9), #teamDtlsTbl td:nth-child(9) { width: 7%; }   /* Received Stock */
#teamDtlsTbl th:nth-child(10), #teamDtlsTbl td:nth-child(10) { width: 7%; } /* Pending Stock */
#teamDtlsTbl th:nth-child(11), #teamDtlsTbl td:nth-child(11) { width: 7%; } /* PO Status */
/* Table cells */
#miscActTbl td.tbcellBorder {
    contrast: Aa solid #000000; /* thick border for cells */
}
</style>
<script>
var date = new Date();
$(document).ready(function() {
	$('#fromDate').datepicker({
		autoclose:true,
		format : 'dd-mm-yyyy',
		todayHighlight:true,
		clearBtn:true,
		startDate:'01/11/2020',
		endDate:new Date(),
 });	

	$('#destDate').datepicker({
		startDate:'01/11/2020',
		endDate:new Date(),
		todayHighlight:true,
		format : 'dd-mm-yyyy',
		autoclose:true,
		clearBtn:true,
	});	
	
	//fn_getDrugList($('#drugTypeID').val());
		});	
				
	
	</script>


<script type="text/javascript">
function generate(val)
{
	fn_loadImage();
	document.forms[0].action="patientDetailsNew.do?actionFlag=getIndentedPoRpt";
    document.forms[0].submit();
	
}
function fn_GeneratePdf(val)
{   
    var select = document.getElementById("dispname");
    var dispName = select.options[select.selectedIndex].text;

    document.forms[0].action =
        "patientDetailsNew.do?actionFlag=getIntendPdf&dispName=" + encodeURIComponent(dispName);

    document.forms[0].submit();
}
function closeModal(arg,frameId)
{
	document.getElementById(frameId).src="";
	$("#"+arg).modal('hide');
}
function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 	 
}
var selectedPoNo = "";
var selectedStatus = "";
function poRpt(arg, poNo, status) {
    selectedPoNo = poNo;
    selectedStatus = status;
    fn_loadImage();

    var url = 'patientDetailsNew.do';
    var params = {
        'actionFlag': 'poNoOnclick',
        'poNo': poNo,
        'status': status
    };

    $.ajax({
        type: 'POST',
        url: url,
        data: params,
        success: function(data) {
            data = data.replace("*", "");
            var result = JSON.parse(data);
            var content = "";

            document.getElementById("tbodyC1").innerHTML = "";
            document.getElementById("header").innerHTML = arg.innerHTML;

            for (var j = 0; j < result.length; j++) {
                content += "<tr>";
                content += "<td style='text-align:center' data-title='S No'>" + (j + 1) + "</td>";
                content += "<td style='cursor:pointer;text-align:center' data-title='POID'>" + result[j].POID + "</td>";
                content += "<td style='text-align:center' data-title='PO DATE'>" + result[j].PODATE + "</td>";
                content += "<td style='text-align:center' data-title='DISTRIBUTOR NAME'>" + result[j].DSTRBTRNAME + "</td>";
                content += "<td style='text-align:center' data-title='DRUG NAME'>" + result[j].DRUGNAME + "</td>";
                content += "<td style='text-align:center' data-title='DRUG TYPE'>" + result[j].DRUGTYPE + "</td>";
                content += "<td style='text-align:center' data-title='AVAILABLE STOCK'>" + result[j].AVL + "</td>";
                content += "<td style='text-align:center' data-title='PO QTY'>" + result[j].LVL + "</td>";
                content += "<td style='text-align:center' data-title='RECEIVED STOCK'>" + (result[j].RECQTY || 'NA') + "</td>";
                content += "<td style='text-align:center' data-title='PENDING STOCK'>" + result[j].POQTY + "</td>";
                content += "<td style='text-align:center' data-title='STATUS'>" + result[j].PARTFULL + "</td>";
                content += "</tr>";
            }

            document.getElementById("tbodyC1").innerHTML = content;

            $("#phcDataDiv").hide();
            $("#phcBackBtn").hide();
            $("#phcHeading").hide();
            $("#phcCountDiv").show();
            $('#poNoDtls').modal('show');
            fn_removeLoadingImage();
        }
        
    }); 
}
function fn_generateCSV()
{  
    var url = "./patientDetailsNew.do?actionFlag=getIndentedPoRpt&csvFlag=Y";
    document.forms[0].action = url;
    document.forms[0].submit();
}

function fn_generateReport()
{

    var url = "patientDetailsNew.do?actionFlag=poNoOnclick&poNo="+ selectedPoNo + "&status="+ selectedStatus+ "&csvFlag=Y";
    document.forms[0].action = url;
    document.forms[0].submit();
}
function fn_print()
{

    var url = "patientDetailsNew.do?actionFlag=generatePdf&poNo="+ selectedPoNo + "&status="+ selectedStatus+ "&csvFlag=Y";
    document.forms[0].action = url;
    document.forms[0].submit();
}

function fn_print11(id,type,heading){	
	
	var currentDate=new Date();
	var dd = currentDate.getDate(); 
	var mm = currentDate.getMonth()+1;
	var yyyy = currentDate.getFullYear(); 
	var hr = currentDate.getHours();
	var min =currentDate.getMinutes();
	var sec = currentDate.getSeconds();
	
	if(dd<10){dd='0'+dd} 
	if(mm<10){mm='0'+mm} 
	if(hr<10){hr ='0'+hr}
	if(min<10){min ='0'+min}
	if(sec<10){sec ='0'+sec}
	
	document.getElementById("dateToday").innerHTML =dd+'-'+mm+'-'+yyyy+' '+hr+':'+min+':'+sec; 
	
			 var original1 = document.getElementById("headerPrint");
			var clone1 = original1.cloneNode(true);
			//var original2 = document.getElementById("footer");
			//var clone2 = original2.cloneNode(true);
			//clone2.style.display="";
			var div = document.createElement("div");	
			var head=document.createElement("span");
			var original = document.getElementById(id);
			var clone = original.cloneNode(true);
			
			clone.style.border="1px solid black";
			clone.style.borderCollapse="collapse";
			clone.style.font="14px";
			clone1.style.display="";
			div.style.padding = "0px 10px 10px 10px";

			 for(var k=0;k<clone.children[0].children.length;k++)
			{
				var childNodes1=clone.children[0].children[k].children;	
				for(var i=0;i<childNodes1.length;i++)
				{
					childNodes1[i].style.border="1px solid black";
				}
			}
			/* for(var k=0;k<clone.children[1].children.length;k++)
			{
				var childNodes2=clone.children[1].children[k].children;	
				for(var i=0;i<childNodes2.length;i++)
				{
					childNodes2[i].style.border="1px solid black";
				}
			} */ 
			div.appendChild(clone1);
			div.appendChild(head);
			//div.appendChild(clone2);
		 div.appendChild(clone);
		// var divToPrint=div;
		 var newWin= window.open("");
		 newWin.document.write(div.outerHTML);
		    newWin.document.close(); 
		    newWin.focus();
		    newWin.print();
		    newWin.close();	
		/* $("#printable").html(divToPrint);
			window.document.close(); // necessary for IE >= 10
		    window.focus(); // necessary for IE >= 10*/
			/*window.print();
			window.close(); */
   	
}
 
</script>
</head>
<body>
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
		 <th colspan="11" class="tbheader">STOCK INDENTED WITH PO NO</th>
		</c:otherwise>
	</c:choose>	
	</tr>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
    <c:if test="${dsgnId eq 'DG7911'}">
						 <option value="-1">ALL WCs</option>
</c:if>
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td>
<%--  <td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Status</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="status" styleId="status" style="width:12em;" title="Select Status"    >
		<html:option value="-1">Select</html:option>
		<html:option value="Partial Pending">Partial Pending</html:option>
		<html:option value="Pending">Pending</html:option>
		<html:option value="Completed">Completed</html:option>
		<html:option value="Cancelled">Cancelled</html:option>
    </html:select> 
</td> --%>


<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%;">
    <b>PO Date</b>&nbsp;
</td>
<td align="left" class="tbcellBorder" style="width:16%">
    <!-- Struts tag to bind fromDate property to the form -->
    <html:text property="fromDate" name="patientForm" styleId="fromDate"/>
</td>




<%-- 		<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="reportsForm" property="dispDrugID" styleId="dispDrugID" style="width:12em;" title="Select Drug Name"    >
		<html:option value="-1">Select</html:option>
		<logic:notEmpty name="dispDrugsList">
					<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
					</logic:notEmpty>
</html:select>
 
</td> --%>
</tr> 

	
</table>
<tr>
<div class='row' align="center">
		<input class="tbheader1" type="button"  value="Generate" onclick="javascript:generate()"/>
</div>
</tr>
<logic:present name="patientForm" property="indentedPOList">
<bean:size id="size" name="patientForm" property="indentedPOList"/>

<logic:greaterThan value="0" name="size"> 

<tr>
<div style="clear:both; float:right; margin-right:1%; padding-bottom:1%;">
    <span><b>Download as: &nbsp;&nbsp;</b></span>
    <img id="csvImg" src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" style="cursor:pointer;" onclick="javascript:fn_generateCSV()"/>
    <img id="pdf1Img" src="images/pdf1.png" title="Download PDF" width="46px" height="20" style="cursor:pointer;"
	        onclick="javascript:fn_GeneratePdf()" />
</div>

<script>
$(document).ready(function() {
    $('#miscActTbl').DataTable({
        pageLength: 10,
        lengthMenu: [
            [10, 25, 50, 100, -1],  // -1 represents "All" rows
            [10, 25, 50, 100, "All"] // text shown in dropdown
        ],
        ordering: true,
        searching: true,
        paging: true,
        info: true,
        autoWidth: false,
        pagingType: "full_numbers" // full pagination controls
    });
});
document.getElementById('fromDate').setAttribute('autocomplete', 'off');
</script>

<!-- Table -->
<table id="miscActTbl" class="table" style="width:100%; text-align:center;">
    <thead>
      <tr>
	<th class="tbheader1 border" id = "sno">S.NO<!-- <input type="checkbox" id="select_all"/><input class="checkAll" type="checkbox" onclick="checkAll(this)"></th> --></th>
 	<th class="tbheader1 border" >PO DATE</th> 
	<!-- <th class="tbheader1 border" >WELLNESS CENTER</th> -->
 	<th class="tbheader1 border" >PO NO</th> 
	<th class="tbheader1 border" >STATUS</th>
	<th class="tbheader1 border" >WELLNESS CENTER</th>
   </tr>   
    </thead>
    <tbody>
        <logic:iterate name="patientForm" property="indentedPOList" id="labelBean" indexId="cnt">
            <%
                List<LabelBean> indentedPOList = (List<LabelBean>) request.getAttribute("indentedPOList");
                Date poDate = indentedPOList.get(cnt).getPODATE();
                Calendar cal = Calendar.getInstance();
                cal.setTime(poDate);
                cal.add(Calendar.DATE, 60);
                Date poDatePlus60 = cal.getTime();

                Date today = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                String strPoPlus60 = sdf.format(poDatePlus60);
                String strToday = sdf.format(today);
            %>
            <tr style="<%= strToday.compareTo(strPoPlus60) > 0 ? "background-color:#FFEBCC;" : "" %>">

                <!-- S.NO -->
                <td align="left" class="tbcellBorder">${startIndex + cnt + 1}</td>

                <!-- PO DATE -->
                <td align="left" class="tbcellBorder">
                    <logic:notEmpty name="labelBean" property="PODATE">
                        <bean:write name="labelBean" property="PODATE"/>
                    </logic:notEmpty>
                    <logic:empty name="labelBean" property="PODATE">-NA-</logic:empty>
                </td>

                <!-- PO NO -->
                <td align="left" class="bothSideBorder2Px blueFont tbcellBorder"  style="cursor: pointer;"
                    onclick="poRpt(this,'<bean:write name='labelBean' property='POID'/>' , '<bean:write name='labelBean' property='PARTFULL'/>' )">
                    <logic:notEmpty name="labelBean" property="POID">
                        <bean:write name="labelBean" property="POID"/>
                    </logic:notEmpty>
                    <logic:empty name="labelBean" property="POID">-NA-</logic:empty>

                    <!-- Hidden PO ID -->
                    <input type="hidden" id="poNo${cnt+1}" value="<bean:write name='labelBean' property='POID'/>"/>
                </td>

                <!-- STATUS / PARTFULL -->
                <td align="left" class="tbcellBorder">
                    <logic:notEmpty name="labelBean" property="PARTFULL">
                        <bean:write name="labelBean" property="PARTFULL"/>
                    </logic:notEmpty>
                    <logic:empty name="labelBean" property="PARTFULL">-NA-</logic:empty>

                    <!-- Hidden status -->
                    <input type="hidden" id="status${cnt+1}" value="<bean:write name='labelBean' property='PARTFULL'/>"/>
                </td>

                <!-- WELLNESS CENTER -->
                <td align="left" class="tbcellBorder">
                    <logic:notEmpty name="labelBean" property="DISPNAME">
                        <bean:write name="labelBean" property="DISPNAME"/>
                    </logic:notEmpty>
                    <logic:empty name="labelBean" property="DISPNAME">-NA-</logic:empty>

                    <!-- Hidden DISP ID -->
                    <input type="hidden" id="dispId${cnt+1}" value="<bean:write name='labelBean' property='DISPID'/>"/>
                </td>

            </tr>
        </logic:iterate>
    </tbody>
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
<input type="hidden" id="dsgnId" name="dsgnId" value="${dsgnId}">
<div class="modal fade" id="poNoDtls" role="dialog" >
    <div class="modal-dialog" style="width:100%">
    
      <!-- Modal content-->
      <div class="modal-content" >
        <div style="text-align:center; font-size:14px;">
        <button type="button" class="close" style="opacity:1;color:#01964d;padding-right: 2%;margin-top: 1%;"
        onclick="closeModal('poNoDtls','phcDataFrameDiv')">×</button> 
       <h3 id="header" style="display: contents; color: white;"></h3>
         <div style="margin-top: 2%;">
	       <span style="color:black; font-weight:bold;margin-left: 79%;">Download as: &nbsp;&nbsp;</span>
	       <img id="csvImg" src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'"
	         onclick="fn_generateReport()" style="cursor:pointer;" />
	       <img id="pdf1Img" src="images/pdf1.png" title="Download PDF" width="4.5%" height="20" style="cursor:pointer;"
	        onclick="javascript:fn_print()" />
         </div>
       </div>

        <div class="modal-body">
        <div>
		<div id="phcCountDiv">

		<section id="no-more-tables">
          <table id="teamDtlsTbl" class="table fontclass table-bordered" >
					 <thead id="thead_dashboard">
				<tr>
							<th class="tbheader1 border"  style="text-align:center"><b>S.No</b></th>
							<th class="tbheader1 border " style="text-align:center"><b>PO NO</b></th>
							<th class="tbheader1 border " style="text-align:center"><b>PO DATE</b></th>
							<th class="tbheader1 border "  style="text-align:center"><b>DISTRIBUTOR NAME</b></th>
							<th class="tbheader1 border "  style="text-align:center"><b>DRUG NAME</b></th>
							<th class="tbheader1 border " style="text-align:center"><b>DRUG TYPE</b></th> 
							<th class="tbheader1 border " style="text-align:center"><b>AVALABLE STOCK</b></th>
							<th class="tbheader1 border "  style="text-align:center"><b>PO QTY</b></th> 
							<th class="tbheader1 border "  style="text-align:center"><b>RECEIVED STOCK</b></th> 
							<th class="tbheader1 border "  style="text-align:center"><b>PENDING STOCK</b></th> 
							<th class="tbheader1 border "  style="text-align:center"><b>PO STATUS</b></th> 
						</tr>
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
					
					</table>
					
					</section>
					</div>
         <div id="phcDataDiv" style="display:none;">
			<iframe id="phcDataFrameDiv" name="phcDataFrameDiv" frameborder="0" width="100%" height="450px"></iframe>
  		 </div> 
					</div>
        </div>
        
      </div>
      
    </div>
  </div>
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