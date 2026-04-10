<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="/common/include.jsp"%>


   
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<%@ include file="/common/includeCalendar.jsp"%> 
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootbox.min.js"></script>

<style>

 .active {
  position: relative;
  z-index: -1;
} 
.fontadjust
{
 font-size:13px;
 font-weight:500;
 
}
</style>



<script type="text/javascript">
var msg ='${msg}';
if(msg!="")
	{
	alert(msg);
	}
/* function generate()
{
	if(validate())
	{	
		var dispname=document.getElementById('dispname').value;
		fn_loadImage();
		document.forms[0].action="patientDetails.do?actionFlag=viewAppointentsReport&dispname="+dispname;
    	document.forms[0].submit();
	}
}
function fn_reset()
{
	document.getElementById('dispname').value="";
	document.forms[0].action="patientDetails.do?actionFlag=viewAppointents";
	document.forms[0].submit();
} */
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0); 
}
function fnCompareDates(FromDate,ToDate)
{
    var FromDateVal;
    var ToDateVal;
    var k = FromDate.indexOf("-");
    var t = FromDate.indexOf("-",3);  
    FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
  	k = ToDate.indexOf("-");
    t = ToDate.indexOf("-",3);
    ToDateVal = ToDate.substr(k+1,t-k-1)+"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
	 if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
         {
		 jqueryAlertMsg('Search Criteria Validation',"From Date should be less than To Date");
     		return false; 
          } 
    else
      return true;       
}

function validateDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Date Validation',"Please Select "+arg1);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoB>DoC)
		{
		input.value="";
		jqueryErrorMsg('Date Validation',arg1+" should not be greater than Today's Date");
		}
	}
}
function validateHealthCard(arg1,input)
{
	var a=input.value;
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Health Card Validation',"Please Enter "+arg1);
	//focusBox(input);
		return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Health Card Validation',arg1+ " should not start with space");
		//focusBox(input);
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z\/\ ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Health Card Validation',"Only alphanumeric are allowed for "+arg1);
		input.value="";
		//focusBox(input);
		return false;
		}
	else
		input.value=a.trim();
}



function resetSearch()
{
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
	
}

function fnSearch()
{
	if(document.forms[0].fromDate.value=="" && document.forms[0].toDate.value=="" )
		    
            {  
				jqueryAlertMsg('Search Criteria Validation','Please Enter All Search Fields! ');
                return false;
            }
	
	var regFrom=document.forms[0].fromDate.value;
	var regTo=document.forms[0].toDate.value;
	if((regFrom.length > 0 && regTo.length == 0 && regTo.length == "") || (regTo.length > 0 && regFrom.length == 0 && regFrom.length=="") )
		{
		jqueryAlertMsg('Search Criteria Validation','Please Select none or both Registration From and Registration To dates');
		return false;
		}
	else if(regFrom.length > 0 && regTo.length > 0)
		{     
			if(fnCompareDates(regFrom, regTo)) 
				{
					search="true"; 
				}
			else
				{
					return false;      
				}
		} 
	
	
	 document.getElementById("currFromDate").value=document.forms[0].fromDate.value;
	document.getElementById("currToDate").value=document.forms[0].toDate.value;
	 
	
	
	var endIndex=${endIndex};
	fn_pagination(0,endIndex);
	
}

/* function validate()
{
	
	 if(document.forms[0].dispname.value==''||document.forms[0].dispname.value==null)
			{
			alert('Please Select Wellness Center Name ');
			focusBox(document.getElementById("dispname"));
			return false;
			}
		else
		{
			return true;
		}
} */

function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 	 
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
 
  var dispname=document.forms[0].dispname.value;
  //var healthCardNo=document.forms[0].cardNo.value;
  var fromDate =document.forms[0].fromDate.value;
  var toDate =document.forms[0].toDate.value;
   fn_loadImage();
  var url="patientDetails.do?actionFlag=viewAppointentsEmp&fromDate="+fromDate+"&toDate="+toDate+"&dispname="+dispname+"&pageId="+pageId+"&endIndex="+endIndex+"&from=Y";
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
function cancelPatient()
{
	
	var aisRemarks=document.getElementById("aisRemarks").value;
	var PAT_CARD_NUMBER=document.getElementById("buttonVal").value;
	 if(aisRemarks!=null && aisRemarks!="")
	{
		 $('#myModal').modal('hide');	
	 bootbox.confirm("Are you sure you want to cancel registration ? ", function(result) {
		 	if(result)
			{
		 			 
		 		
					 document.forms[0].action="./patientDetails.do?actionFlag=cancelPatientReg&cardNo="+PAT_CARD_NUMBER+"&aisRemarks="+aisRemarks;
				   	 document.forms[0].method="post";
				   	 document.forms[0].submit();
			}
		});
	}
	else
	{
		alert("Please enter remarks");
		$('#myModal').modal('show');	
	}
	 

}
function cancelPatient1(PAT_CARD_NUMBER)
{
	document.getElementById("aisRemarks").value="";
	document.getElementById("buttonVal").value=PAT_CARD_NUMBER;
}
function retrieveDetails1(cardNo,serv,appDate,scheme,unitId,relation)
{

	/*Disabling Communication hno,street,pin as these fields are enabled in resetPatientData();*/
	var wapFamilyNo=cardNo;
	if(wapFamilyNo!=null && wapFamilyNo.startsWith("IAS"))
	{
		
		scheme = "CD504";
	}


	var fromDisp = '${fromDisp}';
 	if(wapFamilyNo!=false)
	 {
 		document.forms[0].action="./patientDetails.do?actionFlag=openPatRegForm&cardNo="+wapFamilyNo+"&appntDate="+appDate+"&patDispScheme="+scheme+"&unitId="+unitId+"&serv="+serv+"&relation="+relation+"&fromDispnsry=Y&mobileAppAis=Y&aisFlag=Y&aisDG=N";
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 }
 	else
	 return false;
}
</script>
</head>
<body>
<html:form action="/patientDetails.do">
<c:if test="${saveMsg ne null && saveMsg ne '' }">
<script>
alert('${saveMsg}');
</script>
</c:if>
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

<table style="width:90%;margin:1% auto">
	<tr>
		<th colspan="8" class="tbheader fontadjust">Appointments Through Mobile App</th>
	</tr>
		
	
	<%-- <tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	Wellness Center Name</b>&nbsp;<span style="color:red">*</span></td>
	<td colspan="2" align="left"  class="tbcellBorder">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						<option value="">--------Select---------</option>
					 <html:options collection="wellnessapplist"  property="DISPID" labelProperty="CENTERID"></html:options>
						
						</html:select> 

	</td></tr>
	
	<tr>
	
	<td colspan="2"  align="right">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/></td>
		<td colspan="2"  align="left">
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" /></td>
	
	
		
	</tr> --%>

</table>
<table style="width:90%;margin:1% auto">
<tr><td>
<!-- <table style="width:100%" class="tb"> -->
<%-- <td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/>Health Card No</b></td>
<td  class="tbcellBorder"><html:text name="patientForm"  property="cardNo" styleId="cardNo" maxlength="21" title="Enter Health Card No" onchange="validateHealthCard('Health Card No',this)" style="WIDTH: 15em;"/></td>
 --%>
 <tr>
<td class="labelheading1 tbcellCss fontadjust">Appointments : From Date</td>
<td class="tbcellBorder"><html:text name="patientForm" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
<td class="labelheading1 tbcellCss fontadjust">To Date</td>
<td class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
</tr>
<tr align="center" >
<td  colspan="4" align="center" >  <button class="but fontadjust"  type="button" style="margin-top:12px ! important;" onclick="javascript:fnSearch()">Search</button>
 <button class="but fontadjust"  type="button" onclick="resetSearch()">Reset</button></td>
</tr>
</table>

<br>
<logic:present name="patientForm" property="appointmentsList">
<bean:size id="size" name="patientForm" property="appointmentsList"/>

<logic:greaterThan value="0" name="size"> 


<div class="row">
<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left: 7%;">
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
												<li  class="active"><span><%=i%></span></li> 
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

<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left:4%;">		
								<ul class="pagination">
									<li class="lispacing">Show results in sets of </li><c:if test="${endIndex ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
										<c:if test="${endIndex eq 10}"><li  class="active"><span>10</span></li></c:if>	
										<c:if test="${endIndex ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
										<c:if test="${endIndex eq 20}"><li  class="active"><span>20</span></li></c:if>
										<c:if test="${endIndex ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
										<c:if test="${endIndex eq 50}"><li  class="active"><span>50</span></li></c:if>
										<c:if test="${endIndex ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
										<c:if test="${endIndex eq 100}"><li  class="active"><span>100</span></li></c:if>
										<c:if test="${endIndex ne 1000}"><li><a href="javascript:fn_pagination(1,1000)">1000</a></li></c:if>
										<c:if test="${endIndex eq 1000}"><li  class="active"><span>1000</span></li></c:if>
								</ul> 
</div>     
       
</div>


<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1 fontadjust" > S.NO   </th>
<th class="tbheader1 fontadjust" >EMPLOYEE/CARD NUMBER</th>
<th class="tbheader1 fontadjust" >NAME</th>
<th class="tbheader1 fontadjust" >SERVICE TYPE</th>
<th class="tbheader1 fontadjust" >MOBILE NUMBER</th>
<th class="tbheader1 fontadjust" >APPOINTMNET DATE</th>
<th class="tbheader1 fontadjust" >TIME SLOT</th>
<th class="tbheader1 fontadjust" >TYPE</th>
<th class="tbheader1 fontadjust" >&nbsp;</th>


</tr>


<logic:iterate name="patientForm" property="appointmentsList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${startIndex+cnt+1}</td>

    <logic:notEmpty name="labelBean" property="PAT_CARD_NUMBER">
    
 <td align="center" class="tbcellBorder" > <a href="javascript:" onclick="javascript:retrieveDetails1('<bean:write name="labelBean" property="PAT_CARD_NUMBER" />','<bean:write name="labelBean" property="SERVICE_TYPE" />','<bean:write name="labelBean" property="APP_DATE"/>' ,'<bean:write name="labelBean" property="PATIENTSCHEME" />','<bean:write name="labelBean" property="UNITID" />','<bean:write name="labelBean" property="RELATION" />')"><bean:write name="labelBean" property="PAT_CARD_NUMBER"/></a></td>
<%-- <td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PAT_CARD_NUMBER"/></td> --%>
</logic:notEmpty>
<logic:empty name="labelBean" property="PAT_CARD_NUMBER">
 <td align="left" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty>  
 
 
  <logic:notEmpty name="labelBean" property="PAT_NAME">
<td align="left" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="PAT_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="PAT_NAME">
 <td align="left" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty>  
   <logic:notEmpty name="labelBean" property="SERVICE_TYPE">
<td align="left" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="SERVICE_TYPE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="SERVICE_TYPE">
 <td align="left" class="tbcellBorder fontadjust" >Employee</td>
 </logic:empty>  
  
     <logic:notEmpty name="labelBean" property="MOBILE_NO">
<td align="center" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="MOBILE_NO"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="MOBILE_NO">
 <td align="center" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty>  
 
 
      <logic:notEmpty name="labelBean" property="APP_DATE">
<td align="center" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="APP_DATE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="APP_DATE">
 <td align="center" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="TIME_SLOT">
<td align="left" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="TIME_SLOT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="TIME_SLOT">
 <td align="left" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty>  
 
<logic:notEmpty name="labelBean" property="GRP_NAME">
<td align="left" class="tbcellBorder fontadjust" ><bean:write name="labelBean" property="GRP_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="GRP_NAME">
 <td align="left" class="tbcellBorder fontadjust" >-NA-</td>
 </logic:empty> 
  <logic:notEmpty name="labelBean" property="PAT_CARD_NUMBER">
 <td align="center" class="tbcellBorder fontadjust"><button class="btn btn-danger"  type="button" title="Click to Cancel Registration"  data-toggle="modal" data-target="#myModal" data-backdrop="static"  onclick="javascript:cancelPatient1('<bean:write name="labelBean" property="PAT_CARD_NUMBER" />')">Cancel</button></td>
</logic:notEmpty>
 
 
   <%--   <logic:notEmpty name="labelBean" property="CONSULTANT_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="CONSULTANT_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="CONSULTANT_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  --%> 
</tr>
</logic:iterate>
</table>
</logic:greaterThan>
</logic:present>
<c:if test="${appointmentsList eq 'N' }">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</c:if>
		
<tr>
<td>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal">&times;</button> 
          <h4 class="modal-title" align="center">Remarks<font color="red"></font></h4>
        </div>
           <div class="modal-body" style="overflow:scroll !important; height:150px">
	
           
          <table id="hospDtlsTbl" class="table table-bordered table-hover" style="font-size: 12px;border: 1px solid #ddd;" >
					 <thead id="thead_dashboard">
				
						<tr>
						<td  class="labelheading1 tbcellCss"><span  style="padding: 24px 10px; margin: 0 auto;display: table;" ><b>Remarks <font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></span></td>
						<td colspan="6"><html:textarea   name="patientForm" property="aisRemarks" styleClass="form-control" styleId="aisRemarks" style="float:left;width:100%;overflow-y:auto;resize:none;height:6em" cols="30" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" onkeydown="return maxLengthPress(this,4000,event)"  title="Please Enter Remarks"/></td>
						</tr>
		
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
	      	
					</table>
					<div id="buttonVal">
					</div>
				
        </div>
  		
            
        <div class="modal-footer">
          <button type="button" class="btn btn-primary"  onclick="javascript:cancelPatient()">Submit</button>
        </div>
    </div>
  </div>
</div>
</td>
</tr>


<html:hidden property="showPage" name="patientForm" />
<html:hidden name="patientForm" property="currFromDate" styleId="currFromDate"/> 
 <html:hidden name="patientForm" property="currToDate"  styleId="currToDate"/>
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />
<html:hidden property="dispname" name="patientForm" />
</html:form>


</body>
</html>