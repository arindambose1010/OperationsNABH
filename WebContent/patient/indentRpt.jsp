<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/select2.min.js"></script>
<style type="text/css">
.border{
border : 1 px solid white;
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
		});	
				
	
	</script>


<script type="text/javascript">
function generate()
{
		var dispname=document.getElementById('dispname').value;
		var drugName=document.getElementById('dispDrugID').value;
		fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=getIndentReports&dispname="+dispname+"&drugName="+drugName;
    	document.forms[0].submit();
	
}
function fn_reset()
{
	document.getElementById('dispname').value="";
	document.getElementById("dispDrugID").value=""; 
	document.getElementById("drugType").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getIndentReport";
	document.forms[0].submit();
	
}
 function fn_resetNew()
{
	 document.getElementById('dispname').value="";
	 document.getElementById("dispDrugID").value=""; 
	 document.getElementById("drugType").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getIndentReport";
	document.forms[0].submit();
	
} 
/* function validate()
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
} */

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
function newexportToCsv()
{
	var dispname=document.getElementById('dispname').value;
	var drugName=document.getElementById('dispDrugID').value;
	document.forms[0].action = "patientDetailsNew.do?actionFlag=getIndentReportsCsv&dispname="+dispname+"&drugName="+drugName;
	document.forms[0].submit();  
	   
}


function showinSetsOf(num)
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
  var dispname=document.getElementById('dispname').value;
  var drugName=document.getElementById('dispDrugID').value;
  fn_loadImage();
  var url="patientDetailsNew.do?actionFlag=getIndentReports&dispname="+dispname+"&pageId="+pageId+"&endIndex="+endIndex+"&drugName="+drugName;
 document.forms[0].action=url;
 document.forms[0].submit(); 
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
}


function fn_getDrugList()
{
 document.forms[0].dispDrugID.value="-1";
  $("#dispDrugID").select2().val("");
	var xmlhttp;
   var url;
   var drugType = document.getElementById("drugType").value;
   if(drugType=='-1')
	   {  	   
   	   document.forms[0].dispDrugID.value="-1";
   	   $("#dispDrugID").select2().val("");
      
	   return false;
	   }
  else
	   {

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
 		 url = "patientDetailsNew.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugType;    
 		 xmlhttp.onreadystatechange=function()
 		 {
     		if(xmlhttp.readyState==4)
     		{
  	   		var resultArray=xmlhttp.responseText;
  	 	    resultArray = resultArray.replace("[","");
   	    resultArray = resultArray.replace("@]",""); 
   	    resultArray = resultArray.replace("]",""); 
   	    resultArray = resultArray.replace("*",""); 
			resultArray = resultArray.trim();
   	    
   	   
         		if(resultArray.trim()=="SessionExpired*")
         		{
         			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
         		}
         		else
         		{         	                                           
                      		var drugsList = resultArray.split(","); 
                  			if(drugsList.length>0)
                  			{
                      			document.forms[0].dispDrugID.options.length=0;
                     			document.forms[0].dispDrugID.options[0]=new Option("--select--","-1");
                      			for(var i = 0; i<drugsList.length;i++)
                      			{	
                           		var arr=drugsList[i].split("~");                     
                          			if(arr[1]!=null && arr[0]!=null)
                           		{
                               		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                               		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                                                                              
                               		document.forms[0].dispDrugID.options[i+1] =new Option(val1,val2);
                           		}
                          
                       		}
                   		}
       		}
     		}
 		 }
 		
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	   
	}
	 
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
		<th colspan="8" class="tbheader">Indent Report</th>
	</tr>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						<!-- <option value="">ALL</option> -->
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td>
		<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Type</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type"  onchange="fn_getDrugList()"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>

<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"    >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
</html:select>
</td>
</tr>


	<tr align="center">
	<td colspan="3"  align="right">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/></td>
		<td colspan="3"  align="left">
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" /></td>
	
	
		
	</tr>
</table>

<logic:present name="patientForm" property="indentList">
<bean:size id="size" name="patientForm" property="indentList"/>

<logic:greaterThan value="0" name="size"> 

<div class="row">
<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left: 3%;">
<ul class="pagination">
						Showing Results :${startIndex+1} - ${endResults} of ${totalRecords}
</ul>
</div>


<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" id="pageNoDisplay" >
						<ul class="pagination">
							<li>
								Pages :&nbsp;&nbsp;
							</li>	
						<% int selectedPage=Integer.parseInt(request.getAttribute("pageId").toString());  
								  int totalPages=Integer.parseInt(request.getAttribute("totalPages").toString());
								  int totalRecords=Integer.parseInt(request.getAttribute("totalRecords").toString());
								  int endIndex=Integer.parseInt(request.getAttribute("endIndex").toString());
								  
								  int a=0,minVal=0,maxVal=0;
								  a=selectedPage/10;
								  minVal=a*10;
								  if(selectedPage%10==0)
									  minVal=minVal-10;
								  maxVal=minVal+10;
								  if(maxVal>=totalPages)
									  maxVal=totalPages; 
								  minVal=minVal+1;
								  if(minVal > 10)
								  	{
									  %>
										<li><a href="#"><span class="glyphicon glyphicon-backward" 
											onclick="javascript:viewPreviousPages(<%=minVal%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer; top: 0px;"></span></a></li>
									  <%  
								  	}
								  for(int i=minVal;i<=maxVal ;i++)
								  	{
									  	if(i==selectedPage)
										  	{
												%>
												<li class="active"><span><%=i%></span></li> 
												<%
											}
									  	else
											{
												%>
												<li><a href="javascript:fn_pagination(<%=i%>,<%=endIndex%>)"><%=i%></a></li>
												<%
											}
									  		
								  	}
								  if(maxVal<totalPages)
								  	{
									  	%>
										<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=maxVal+1%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer; top: 0px;"></span></li>
										<%
								  	}
							   %>
						</ul>
</div>

<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left: 8%;">		
								<ul class="pagination">
									<li class="lispacing">Show results in sets of </li><c:if test="${endIndex ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
										<c:if test="${endIndex eq 10}"><li class="active"><span>10</span></li></c:if>	
										<c:if test="${endIndex ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
										<c:if test="${endIndex eq 20}"><li class="active"><span>20</span></li></c:if>
										<c:if test="${endIndex ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
										<c:if test="${endIndex eq 50}"><li class="active"><span>50</span></li></c:if>
										<c:if test="${endIndex ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
										<c:if test="${endIndex eq 100}"><li class="active"><span>100</span></li></c:if>
										<c:if test="${endIndex ne 1000}"><li><a href="javascript:fn_pagination(1,1000)">1000</a></li></c:if>
										<c:if test="${endIndex eq 1000}"><li class="active"><span>1000</span></li></c:if>
								</ul> 
</div>     
       
</div>

<div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<p><b>Generated On Date:</b>${updDate} </p>
<br>
<span><b>Download as: &nbsp;&nbsp;</b></span>
<img id="csvImg" src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:newexportToCsv()"/>
</div>
<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1 border" >S NO</th>
<th class="tbheader1 border" >DRUG TYPE</th>
<th class="tbheader1 border" >DRUG NAME</th>
<th class="tbheader1 border" >AVERAGE MONTHLY CONSUMPTION(LAST 3 MONTHS)</th>
<th class="tbheader1 border" >REQUIRED STOCK FOR NEXT 3 MONTHS</th>
<th class="tbheader1 border" >AVAILABLE STOCK</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED</th>
</tr>

<logic:iterate name="patientForm" property="indentList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${startIndex+cnt+1}</td>

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
 
 <logic:notEmpty name="labelBean" property="AVG_STK">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="AVG_STK"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="AVG_STK">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 

    <logic:notEmpty name="labelBean" property="REQ">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="REQ"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="REQ">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="AVL">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="AVL"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="AVL">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="STOCK">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="STOCK"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="STOCK">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
</tr>
</logic:iterate>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="indentList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />

</html:form>


</body>
</html>