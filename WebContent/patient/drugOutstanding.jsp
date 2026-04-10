<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
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
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<style type="text/css">
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
<script>
var date = new Date();
$(document).ready(function() {
	 $('#fromDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:'01/01/2012',
			endDate:new Date(),
	 }).on('changeDate', function(selected){
			if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#destDate').datepicker('setStartDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#destDate').datepicker('setStartDate','01/01/1900');		
			
		});	
    
		$('#destDate').datepicker({
			startDate: '01/01/2012',
			todayHighlight:true,
			format : 'dd-mm-yyyy',
			autoclose:true,
			clearBtn:true,
			endDate:new Date(),
		}).on('changeDate',function(selected){
	 		if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#fromDate').datepicker('setEndDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#fromDate').datepicker('setEndDate',new Date());
		});	
				
});
	
	</script>


<script type="text/javascript">
function generate()
{
	fn_loadImage();
	var drugType=document.getElementById('dType').value;
	var drugName=document.getElementById('dName').value;
	var wc=document.getElementById('wellnesscenter').value;
	document.forms[0].action="patientDetailsNew.do?actionFlag=wcDrugOutstandingBalance&drugType1="+drugType+"&drugName1="+drugName+"&wc1="+wc;
    document.forms[0].submit();  
}

function newexportToCsv()
{
	var drugType=document.getElementById('dType').value;
	var drugName=document.getElementById('dName').value;
	var wc=document.getElementById('wellnesscenter').value;

	document.forms[0].action="patientDetailsNew.do?actionFlag=wcDrugOsReportCsv&drugType1="+drugType+"&drugName1="+drugName+"&wc1="+wc;
	document.forms[0].submit();	   
}

function fn_reset()
{
	fn_loadImage();
	document.getElementById("dType").value="";
	document.getElementById("dName").value=""; 
    document.getElementById('wellnesscenter').value=""; 
    document.getElementById('searchType').value=""; 
     
    document.forms[0].action="patientDetailsNew.do?actionFlag=wcDrugOutstandingBalance";
 	document.forms[0].submit();
      
}




function getDrugName()
{
	var drugType=document.getElementById("dType").value;
	if (window.XMLHttpRequest)
	{
	   	xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject)
	{		
	   	xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
	else
	{
		jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	}   
	url = "./patientDetailsNew.do?actionFlag=getDrugByType&callType=Ajax&drugType1="+drugType;
	
	xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {
	    	 var resultArray=xmlhttp.responseText;
	    	if(resultArray.trim()=="SessionExpired*"){
	    		jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	        }
	    	 else
	        {
				resultArray = resultArray.replace("[","");
	           	resultArray = resultArray.replace("@]*","");
	          	var drugList = resultArray.split("@,"); 
				if(drugList.length>0)
                	{  
					document.forms[0].dName.options.length=1;
            			for(var i = 0; i<drugList.length;i++)
                		{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].dName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
				
	        } 
	    }
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
}

function fn_onload(){
	document.getElementById("dName").value='${drugId}';
}
function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 	 
}

function fn_print(id,type,heading){	
	
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
function fn_getDrugDetails(drugId,type){
	//fn_loadImage();
	//var drugType=document.getElementById('dType').value;
	//var drugName=document.getElementById('dName').value;
	var wc=document.getElementById('wellnesscenter').value;
	var searchType = document.getElementById('searchType').value;
	var url="./patientDetailsNew.do?actionFlag=wcDrugOutstandingBalance&drugType1="+type+"&drugName1="+drugId+"&wc1="+wc+"&drugDrill=Y&searchType="+searchType;
	var x=window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
}
function newexportToCsvDeatiled()
{
	//var drugType=document.getElementById('dType').value;
	//var drugName=document.getElementById('dName').value;
	//var wc=document.getElementById('wellnesscenter').value;

	document.forms[0].action="patientDetailsNew.do?actionFlag=wcDrugOsReportCsv&drugType1=${type}&drugName1=${drugId}&wc1=${wc}&drugDrill=${drugDrill}&searchType=${selectType}";
	document.forms[0].submit();	   
}
</script>
</head>
<body onload="getDrugName();fn_onload();">
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
<c:if test="${drugDrill ne 'Y'}">
<table id="searchDiv" style="width:90%;margin:1% auto">
	<tr>
		<th colspan="6" class="tbheader">Total Available Drugs</th>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" style="width:15%;"  align="left"><b>Drug Type</b></td>
		<td align="left"  class="tbcellBorder">
			<html:select  property="dType" name="patientForm" styleId="dType"   title="Select Drug Type"  onchange="getDrugName()">
		    <option value="">--------ALL---------</option>
		   
		 <html:options collection="drugType"  property="ID" labelProperty="VALUE"></html:options>
			</html:select> 
		</td>
		<td  class="labelheading1 tbcellCss" style="width:15%;"  align="left"><b>Drug Name</b></td>
		<td align="left"  class="tbcellBorder">
			<html:select  property="dName" name="patientForm" styleId="dName"   title="Select Drug Name">
		    <option value="">--------ALL---------</option>
			</html:select> 
		</td>
	<%-- 
	<td colspan="1" class="labelheading1 tbcellCss" style="width:15%;" align="left" ><b>Wellness Center</b></td>
	<td align="left"  class="tbcellBorder">
			<html:select  property="wellnesscenter" name="patientForm" styleId="wellnesscenter"   title="Select Wellness Center">
		    <option value="">--------ALL WCs---------</option>
			
			 <html:options collection="wellnesscenters"  property="ID" labelProperty="VALUE"></html:options>
			</html:select> 
		</td>
 --%>
	</tr>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>Selection Type</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder">
	<html:select  property="searchType"  styleId="searchType" name="patientForm"  title="Selection Type">
						<html:option value="-1">Available Drugs without Batch No</html:option>
						<html:option value="AllBatch">All Drugs with Batch no</html:option>
						<html:option value="AvblBatch">Available Drugs with Batch No</html:option>
						<html:option value="AllNoBatch">All Drugs without Batch No</html:option>
						
	</html:select> 

	</td>
	</tr> 
		<tr>
	</tr>
	<tr>
	<td colspan="6"  align="center">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()" title="Click on Generate"/>
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" title="Click on Reset"/>
		</td>
	
	
		
	</tr>
</table>
</c:if>
<logic:present name="patientForm" property="drugReportList">
<bean:size id="size" name="patientForm" property="drugReportList"/>

<logic:greaterThan value="0" name="size"> 
<c:if test="${drugDrill eq 'Y'}">
<table id="searchDiv" style="width:90%;margin:1% auto">
	<tr>
		<th colspan="6" class="tbheader">Total Available Drugs Detailed</th>
	</tr>
</table>
</c:if>
<div  style="clear:both;float:right;margin-right:5%; padding-bottom: 1%;">
<span><b>Download as: &nbsp;&nbsp;</b></span>
<c:choose>
<c:when test="${drugDrill ne 'Y'}">
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/>
</c:when>
<c:otherwise>
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsvDeatiled()"/>
</c:otherwise>
</c:choose>
&nbsp;&nbsp;<a href="javascript:fn_print('miscActTbl','','Total Available Drugs');" title="Print"><span class="glyphicon glyphicon-print"></span></a> 
</div>

<table class="table" id="miscActTbl"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">

<tr>
<th style="padding: 5px;" class="tbheader1" >S NO</th>
<th style="padding: 5px;" class="tbheader1" >TYPE</th>
<th style="padding: 5px;" class="tbheader1" >DRUG NAME</th>
<c:if test="${selectType == 'AvblBatch' or selectType == 'AllBatch'}">	
<th style="padding: 5px;" class="tbheader1" >DRUG CODE</th>
<th style="padding: 5px;" class="tbheader1" >MANUFACTURER</th>
<th style="padding: 5px;" class="tbheader1" >DISTRIBUTOR</th>

<th style="padding: 5px;" class="tbheader1" >BATCH NO</th>

<th style="padding: 5px;" class="tbheader1" >EXPIRY DATE</th>
<th style="padding: 5px;" class="tbheader1" >UNIT PRICE(in Rs.)</th>
<th style="padding: 5px;" class="tbheader1" >INVOICE NUM</th>
<th style="padding: 5px;" class="tbheader1" >TOTAL ADDED QUANTITY</th>
</c:if>
<th style="padding: 5px;" class="tbheader1" >CURRENT BALANCE</th>
<c:if test="${(selectType == 'AvblBatch' || selectType == 'AllBatch' || ((selectType =='-1' || selectType =='AllNoBatch') && wc ne '' && wc ne null)) || drugDrill eq 'Y'}">
<th style="padding: 5px;" class="tbheader1" >WELLNESS CENTER</th>
</c:if>
</tr>

<logic:iterate name="patientForm" property="drugReportList" id="labelBean" indexId="cnt">
<tr>
<td align="center" class="tbcellBorder" >${cnt+1}</td>

<logic:notEmpty name="labelBean" property="DRUGTYPE">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGTYPE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGTYPE">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
<logic:notEmpty name="labelBean" property="DRUGNAME">
<c:choose>
<c:when test="${(selectType =='-1' || selectType =='AllNoBatch' ) && drugDrill ne 'Y'}"><td align="center" class="tbcellBorder" ><a href="javascript:" onclick="fn_getDrugDetails('<bean:write name="labelBean" property="DRUGIDS" />','<bean:write name="labelBean" property="DRUGTYPE" />')"><bean:write name="labelBean" property="DRUGNAME"/></td></c:when>
<c:otherwise><td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGNAME"/></td></c:otherwise>
</c:choose>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGNAME">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
  <c:if test="${selectType == 'AvblBatch' or selectType == 'AllBatch'}">
 <logic:notEmpty name="labelBean" property="DRUGCODE">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGCODE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGCODE">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
  
 <logic:notEmpty name="labelBean" property="MNFCTRNAME">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="MNFCTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="MNFCTRNAME">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 <logic:notEmpty name="labelBean" property="DSTRBTRNAME">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="DSTRBTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DSTRBTRNAME">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>   

 <logic:notEmpty name="labelBean" property="BATCHNO">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="BATCHNO"/></td>
</logic:notEmpty>

<logic:empty name="labelBean" property="BATCHNO">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
 <logic:notEmpty name="labelBean" property="EXPDT">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="EXPDT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="EXPDT">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
  <logic:notEmpty name="labelBean" property="UNIT_PRICE">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="UNIT_PRICE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="UNIT_PRICE">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
  <logic:notEmpty name="labelBean" property="INVOICE_NUM">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="INVOICE_NUM"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="INVOICE_NUM">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
  <logic:notEmpty name="labelBean" property="TOTALQUANTITY">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="TOTALQUANTITY"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="TOTALQUANTITY">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>   
 </c:if>
     <logic:notEmpty name="labelBean" property="QUANTITY">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="QUANTITY"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="QUANTITY">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
<c:if test="${(selectType == 'AvblBatch' || selectType == 'AllBatch' || ((selectType =='-1' || selectType =='AllNoBatch') && wc ne '' && wc ne null)) || drugDrill eq 'Y'}">

  <logic:notEmpty name="labelBean" property="WCNAME">
<td align="center" class="tbcellBorder" ><bean:write name="labelBean" property="WCNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="WCNAME">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 </c:if>
      <%-- <logic:notEmpty name="labelBean" property="WCNAME">
      <logic:equal name="labelBean" property="WCNAME" value="DISP1">
      <td align="center" class="tbcellBorder" >KHAIRATHABAD</td>
      </logic:equal>
      <logic:equal name="labelBean" property="WCNAME" value="DISP2">
      <td align="center" class="tbcellBorder" >VANASTHALIPURAM</td>
      </logic:equal>
      <logic:equal name="labelBean" property="WCNAME" value="DISP3">
      <td align="center" class="tbcellBorder" >WARANGAL</td>
      </logic:equal>
      <logic:equal name="labelBean" property="WCNAME" value="DISP4">
      <td align="center" class="tbcellBorder" >SANGAREDDY</td>
      </logic:equal>
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="WCNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="WCNAME">
 <td align="center" class="tbcellBorder" >-NA-</td>
 </logic:empty>  --%> 

</tr>
</logic:iterate>
</table>
</logic:greaterThan>
<logic:empty name="patientForm" property="drugReportList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
</logic:present>

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
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>TOTAL AVAILABLE DRUGS <c:if test="${drugDrill eq 'Y'}"> DETAILED</c:if></td>
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

<input type="hidden" id="wellnesscenter" value="${dispId}" >
</html:form>


</body>
</html>