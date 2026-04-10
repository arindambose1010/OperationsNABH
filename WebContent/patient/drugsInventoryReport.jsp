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
var drugFlag = "${drugFlag}";
function generate()
{
	if(validate())
	{
		var destDate=document.getElementById('destDate').value;
		var fromDate=document.getElementById('fromDate').value;
		var drugName=document.getElementById('drugName').value;
		
		//var dispname=document.getElementById('dispname').value;
		fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=viewDrugInvRep&destDate="+destDate+"&fromDate="+fromDate+"&drugName="+drugName;
    	document.forms[0].submit();
	}
}

function fn_reset()
{
	 
	 document.getElementById("destDate").value="";
	 document.getElementById("fromDate").value=""; 
	 document.getElementById("drugName").value=""; 
   
    
}
function validate()
{

	if(document.forms[0].fromDate.value==='' || document.forms[0].fromDate.value===null){
		alert('Please Select From Date');
		focusBox(document.getElementById("fromDate"));
		return false;
	}else if(document.forms[0].destDate.value==='' || document.forms[0].destDate.value===null){
		alert('Please Select To Date');
		focusBox(document.getElementById("destDate"));
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
function fn_generateCSV()
{
	 
	var url="./patientDetailsNew.do?actionFlag=viewDrugInvRep&csvFlag=Y";
	document.forms[0].action=url;
	document.forms[0].submit();

}
 



/*function showinSetsOf(num)
{   

		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPage.value='1'; 
		fn_pagination(1,num);
}

function showPagination(num)
{
		document.forms[0].showPage.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);		
}
	
function fn_pagination(pageId,endIndex)
{
	var detailedRep = '${fromDetailed}'
		var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	//var dispname=document.getElementById('dispname').value;
	
if( detailedRep=="N")
{
	
	var url="./patientDetailsNew.do?actionFlag=drugReport&destDate="+destDate+"&fromDate="+fromDate+"&pageId="+pageId+"&endIndex="+endIndex;
	document.forms[0].action=url;
	 document.forms[0].submit(); 
 }
else if ( detailedRep=="Y")
	{
	
     var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&pageId="+pageId+"&endIndex="+endIndex;
	 document.forms[0].action=url;
	 document.forms[0].submit(); }
}


function viewPreviousPages(minVal,totalPages,selectedPage,endIndex)
{
	var newMinVal=minVal-10;
	var pageNoDisplay='';
	pageNoDisplay=pageNoDisplay+'<ul class="pagination">';
	if(newMinVal >1)
		{
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+newMinVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer; top: 0px;"></span></li>';
		}
	for(var i=newMinVal;i<minVal;i++)
		{
			if(selectedPage==i)
			{
				pageNoDisplay=pageNoDisplay+' <li class="active"><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
			}
		else
			{
				pageNoDisplay=pageNoDisplay+' <li><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
			}
		}
	
	pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+minVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer; top: 0px;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;

}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageNoDisplay='';
	pageNoDisplay=pageNoDisplay+'<ul class="pagination">';
	var minVal=pageNo;
	var maxVal=minVal+9;
	if(maxVal>noOfPages)
		maxVal=noOfPages;

	pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer; top: 0px;"></span></li>';
	for( var i=minVal ; i<=maxVal ; i++)
		{
			if(selectedPage==i)
				{
					pageNoDisplay=pageNoDisplay+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
				}
			else
				{
					pageNoDisplay=pageNoDisplay+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
				}
		}
	if(maxVal<noOfPages)
		{
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer; top: 0px;"></span></li></ul>';
		}
	
	document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;
} */
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
<table style="width:80%;margin:1% auto;margin-left: 18%;" align="center">
	<tr>
		<th colspan="4" class="tbheader">Drug Inventory Report</th>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" style="width:20%;"  align="left"><b>From Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" style="width:20%;"  align="left"><html:text property="fromDate" styleId="fromDate" style="width:70%"/></td>
		<td  class="labelheading1 tbcellCss" style="width:20%;"  align="left"><b>To Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" style="width:20%;"  align="left"><html:text property="destDate" styleId="destDate" style="width:70%"/></td>
	
	
	</tr>
		<tr>
		<td class="labelheading1 tbcellCss" style="width:20%;"  align="left"><b>Drug Name</b>&nbsp;</td>
		<td  class="tbcellBorder" style="width:20%;"  align="left"><html:text property="drugName" styleId="drugName" style="width:70%"/></td>
		
	</tr>
	<%-- 	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	Wellness Center Name</b>&nbsp;<span style="color:red">*</span></td>
	<td align="left"  class="tbcellBorder">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						<option value="">--------Select---------</option>
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td></tr> --%>
	<tr>
	<td colspan="4"  align="center">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/>
		
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" />
		</td>
   <td>
<button id="Excel" class="btn btn-warning"  onclick="javascript:fn_generateCSV();">Excel</button>
</td>
	
		
	</tr>
</table>


<table  width="100%" style="margin:0 auto" cellspacing="4" cellpadding="4">


<tr><td colspan="4" > 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  
     
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
        <table  width="100%"  id="drugsTable" border="1">
         <tr>  
       	<th class="tbheader1"><b>S NO</b></th>         
        <th class="tbheader1"><b>Drug Type</b></th>
        <th class="tbheader1"><b>Drug Code</b></th>
        <th class="tbheader1"><b>Drug Name</b></th>
         <th class="tbheader1"><b>Manufacturer Name</b></th>
        <th class="tbheader1"><b>Distributor Name</b></th>   
         <th class="tbheader1"><b>Batch No</b></th>
        <th class="tbheader1"><b>Expiry Date</b></th> 
		 <th class="tbheader1"><b>Invoice No</b></th> 
         <th class="tbheader1"><b>Quantity Added Date</b></th>    
       	<th class="tbheader1"><b>Added Balance</b></th>
       	
        </tr>
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td style="text-align:center;" width="5%"><%=k++ %></td>         
		<td width="10%"><bean:write name="drugLst" property="drugTypeName" /></td>
		<td width="10%"><bean:write name="drugLst" property="drugCode" /></td>
        <td width="10%"><bean:write name="drugLst" property="drugName" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugMfg" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugDsbtr" /></td>
        <td width="10%"><bean:write name="drugLst" property="batchNo" /></td>
        <td width="10%"><bean:write name="drugLst" property="expiryDt" /></td> 
  <td width="10%"><bean:write name="drugLst" property="invoiceNo" /></td>  		
        <td width="10%"><bean:write name="drugLst" property="crtDt" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGID" /></td> 
       
        </tr>
        </logic:iterate>
        </table>
        </logic:greaterThan>
        <logic:equal value="0" name="drugSize">
        <p style="margin: 10px auto; text-align: center;"><b>No Records Found</b></p>
        </logic:equal>
        </logic:present>    
        </div>
</td></tr>

</table>
</html:form>


</body>
</html>