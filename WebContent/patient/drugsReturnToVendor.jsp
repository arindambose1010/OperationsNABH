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
<script>
var date = new Date();
$(document).ready(function() {
	var msg = '${status}';
	if(msg == 'Yes'){
		alert("Drugs Return to Vendor request raised successfully");
		parent.fn_drugsReturnToVendor();
	}
	else if(msg == 'No'){
		alert("Something went wrong .Please try again..");
		parent.fn_drugsReturnToVendor();
	}
	 $('#fromDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:'01/01/2016',
			endDate:new Date(),
	 }).on('changeDate', function(selected){
			if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#destDate').datepicker('setStartDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#destDate').datepicker('setStartDate','01/01/2016');		
			
		});	
 
		$('#destDate').datepicker({
			startDate: '01/01/2016',
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
	if(validate())
	{
	
		
		fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=getDrugsExpiredForRTV&search=Y";
    	document.forms[0].submit();
	}
}

 function fn_reset()
{
	document.getElementById("dstrName").value="";
	document.getElementById("searchType").value=""; 
	
	document.forms[0].action="patientDetailsNew.do?actionFlag=getDrugsExpiredForRTV&search=N";
	document.forms[0].submit();
	
} 
function validate()
{
	if(document.forms[0].dstrName.value==='' || document.forms[0].dstrName.value===null){
		alert('Please Select Distributor Name');
		focusBox(document.getElementById("dstrName"));
		return false;
	}
	else if(document.forms[0].searchType.value==='' || document.forms[0].searchType.value===null){
			alert('Please Select Search Type');
			focusBox(document.getElementById("searchType"));
			return false;
	}
	return true;
}

function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 	 
}
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0); 
}
function fn_submitRTV(){
	 var drugList="";
	   var remarks =document.getElementById("remarks").value; 
		 if(remarks == null || remarks == ""){
			 alert("Please enter remarks");
			 return false;
		 }
		 var table = document.getElementById("drugInfo"); 
		// var totalRows = document.getElementById("someTableID").rows.length;
		 
			 var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
			 var flag=0;
			 for(var i=0;i<rows-1;i++)
				 {
				 var j=i+1;
				 var id="chb"+j;
				 if(document.getElementById(id).checked===true)
				 {  var rowId = i+1;
				 var quantId = "quant"+j;
					 var manfctr = table.rows[rowId].cells[5].innerHTML;
					 var dstrbtr = table.rows[rowId].cells[6].innerHTML;
					 var batchNo = table.rows[rowId].cells[7].innerHTML;
					 var expiryDt = table.rows[rowId].cells[8].innerHTML;
					 var avblQuant   = table.rows[rowId].cells[11].innerHTML;
					 var drugCode=document.getElementById(id).value;
					 var quantity =document.getElementById(quantId).value;
					 if(avblQuant == "NA"){
						 avblQuant ="0";
					 }
					 if(parseInt(quantity)>parseInt(avblQuant)){
						 alert("Quantity cannot be greater than available Quantity ");
						 focusBox(document.getElementById(quantId));
						 return false;
					 }
					
					 if((drugCode!=null && ""!=drugCode )&& (quantity != null && quantity != ""))
						 {
						 drugList=drugList+drugCode+"~"+avblQuant+"~"+manfctr+"~"+dstrbtr+"~"+batchNo+"~"+quantity+"@";
						 flag=1;
						 }
					 
				 }
				 
			
			 }
			 if(flag===0)
			 {
			 alert("Please select altleast one Check box to Submit");
			 return false;
			 }
		 
		 
		 if(confirm("Do you want to Submit?"))
		 {
		     $(':input[type="button"]').prop('disabled', true);
		     fn_loadImage();
		  var url="./patientDetailsNew.do?actionFlag=submitRequestForRTV&drugList="+drugList;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
		 }
		 //jq(':input[type="button"]').prop('disabled', false);
	
}

function fn_enable(){
	if($('#chb').is(':checked')){
		$('.checkCode').prop("checked",true);
	}
	else{
		$('.checkCode').prop("checked",false);
	}
}
function checkInput(ele){
	var val = ele.value;
	 if(val.search(/[^0-9]/) > -1){
		ele.value = val.replace(/[^0-9]+/, '');
	}
	if(ele.value.charAt(0) == 0 && ele.value.charAt(1)){
	ele.value = ele.value.replace(/^00+/, '0');
	}

}
function checkAlphaNumeric(ele){
	var val = ele.value;
	
	if(val.search(/[^A-Za-z0-9.\s ]/) > -1){
		ele.value = val.replace(/[^A-Za-z0-9.\s ]+/, '');
	}
	

}

</script>
</head>
<body>
<html:form action="/patientDetailsNew.do">
<div id="processImagetable" style="top:45%;left:45%;display:none;position:absolute;z-index:60;height:100%">
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
		<th colspan="4" class="tbheader">Return to Vendor</th>
	</tr>
	
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	Distributor Name</b>&nbsp;<span style="color:red">*</span></td>
	<td align="left"  class="tbcellBorder">

<html:select  property="dstrName" name="patientForm" styleId="dstrName"   title="Select Distributor Name">
					 <html:option value="">Select</html:option>
					 <html:options collection="dstrList"  property="ID" labelProperty="VALUE"></html:options>
						
						</html:select> 

	</td>
	
<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>Selection Type</b>&nbsp;<span style="color:red">*</span></td>
	<td align="left"  class="tbcellBorder">

<html:select  property="searchType" name="patientForm" styleId="searchType"   title="Select Selection Type">
						<html:option value="">Select</html:option>
					    <html:option value="Exp">Expired Drugs</html:option> 
					    <html:option value="Avbl">Available Drugs</html:option>
						<html:option value="All">All</html:option>
						</html:select> 
	</td>
	
	</tr>
	 <%-- <tr>
		<td class="labelheading1 tbcellCss" align="left" style="width:20%;" ><b>From Date</b>&nbsp;</td>
		<td  class="tbcellBorder" align="left" style="width:20%;" ><html:text name="patientForm" property="fromDate" styleId="fromDate" style="width:70%" /></td>
		<td  class="labelheading1 tbcellCss" align="left" style="width:20%;" ><b>To Date</b>&nbsp;</td>
		<td  class="tbcellBorder" align="left" style="width:20%;" ><html:text name="patientForm" property="destDate" styleId="destDate" style="width:70%" /></td>
	
	
	</tr> --%>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						<!-- <option value="">ALL</option> -->
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 
	</td>
	
	</tr>
	
	<tr>
	<td colspan="4"  align="center">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/>&nbsp;&nbsp;
		
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" />
	
	</td>
		
	</tr>
</table>
<c:if test="${search eq 'Y' }">
<logic:present name="patientForm" property="drugExpList">
<bean:size id="size" name="patientForm" property="drugExpList"/>

<logic:greaterThan value="0" name="size"> 




<table class="table" id="drugInfo"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1" >S NO</th>
<th class="tbheader1"><input type="checkbox" id="chb"  onclick="fn_enable('0');" placeholder="check All"></input></th>
<th class="tbheader1" >WC NAME</th>
<th class="tbheader1" >DRUG TYPE</th>
<th class="tbheader1" >DRUG NAME</th>
<th class="tbheader1" >MANUFACTURER NAME</th>
<th class="tbheader1" >DISTRIBUTER NAME</th>
<th class="tbheader1" >BATCH NO</th>
<th class="tbheader1" >EXPIRY DATE</th>
<th class="tbheader1" >PURCHASE INVOICE NUMBER</th>
<th class="tbheader1" >PURCHASE INVOICE DATE</th>
<th class="tbheader1" >AVAILABLE QUANTITY</th>
<th class="tbheader1" >QUANTITY</th>

</tr>
<logic:iterate name="patientForm" property="drugExpList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${cnt+1}</td>
<td align="left" class="tbcellBorder" ><input type="checkbox" class="checkCode" id="chb${cnt+1}" value="<bean:write name="labelBean" property="DRUGCODE"/>" ></input></td>
<logic:notEmpty name="labelBean" property="DISP_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISP_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DISP_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 

    <logic:notEmpty name="labelBean" property="DRUG_TYPE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUG_TYPE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUG_TYPE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="DRUG_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUG_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUG_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="MNFCTRNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="MNFCTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="MNFCTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
     <logic:notEmpty name="labelBean" property="DSTRBTRNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DSTRBTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DSTRBTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
     <logic:notEmpty name="labelBean" property="BATCHNO">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="BATCHNO"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="BATCHNO">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
     <logic:notEmpty name="labelBean" property="EXPDT">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="EXPDT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="EXPDT">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
   <logic:notEmpty name="labelBean" property="INVOICENUMBER">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INVOICENUMBER"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="INVOICENUMBER">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
  <logic:notEmpty name="labelBean" property="ISSUEDATE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ISSUEDATE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="ISSUEDATE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  

 
 <logic:notEmpty name="labelBean" property="QUANTITY">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="QUANTITY"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="QUANTITY">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 <logic:notEmpty name="labelBean" property="QUANTITY">
<td align="left" class="tbcellBorder" ><input type="text" id="quant${cnt+1}" name="quant${cnt+1}" onkeyup="checkInput(this);" value="<bean:write name="labelBean" property="QUANTITY" />"></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="QUANTITY">
 <td align="left" class="tbcellBorder" ><input type="text" id="quant${cnt+1}" name="quant${cnt+1}" value="0" readonly></td>
 </logic:empty>  
</tr>
</logic:iterate>
</table>
<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr><td class="labelheading1 tbcellCss" align="left" style="width:20%;" ><b>Remarks</b>&nbsp;<span style="color:red">*</span></td>

</tr>
<tr><td  class="tbcellBorder" align="left" style="width:40%;" >
<textarea type="textarea" rows="2" cols="40"  id="remarks" name="remarks" onkeyup="checkAlphaNumeric(this);"></textarea>

</tr>
<tr>
	<td colspan="2"  align="center">
		<input class="but" type="button"  value="Submit" onclick="javascript:fn_submitRTV();"/></td>
		
	
	
		
	</tr>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="drugExpList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
</logic:empty>
</c:if>
<c:if test="${search ne 'Y' }">
 <div class="error-desk" align="center">
         
            <br> <h4>Please use search criteria to get the result</h4> <br>
            </div>
</c:if>
</html:form>


</body>
