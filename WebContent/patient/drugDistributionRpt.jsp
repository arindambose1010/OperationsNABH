<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
function fn_getusers(){
	var dispname=document.getElementById('dispname').value;
	if(dispname != null && dispname != ''){
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
	url = "./patientDetailsNew.do?actionFlag=getdrugusers&dispname="+dispname;
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
				
				var totalList = resultArray.split("*");
                    
                    var users = totalList[0];
                    
                    users = users.replace("[","");users = users.replace("@]","");
                	var userFinalList = users.split("@,"); 
                	if(userFinalList.length>0)
                	{  
					  document.forms[0].userName.options.length=1;
            			for(var i = 0; i<userFinalList.length;i++)
                		{	
                     		var arr=userFinalList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].userName.options[i+1] =new Option(val1,val2);
                       	 	    //document.forms[0].userName.value=userName;
                     		}
                		}
            		}
                
	        }
	    }
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
	}
function generate()
{
	if(validate())
	{
		var destDate=document.getElementById('destDate').value;
		var fromDate=document.getElementById('fromDate').value;
		var dispname=document.getElementById('dispname').value;
		var userName=document.getElementById('userName').value;
        fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=drugDistReport&destDate="+destDate+"&fromDate="+fromDate+"&userName="+userName+"&dispname="+dispname;
    	document.forms[0].submit();
	}
}
function generateDetailed(){
	if(validate())
	{
		var destDate=document.getElementById('destDate').value;
		var fromDate=document.getElementById('fromDate').value;
		var dispname=document.getElementById('dispname').value;
		var userName=document.getElementById('userName').value;
        fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=drugDistDetailedReport&destDate="+destDate+"&userName="+userName+"&fromDate="+fromDate+"&dispname="+dispname;
    	document.forms[0].submit();
	}
}
function fn_reset()
{
	 fn_loadImage();
	 document.getElementById("destDate").value="";
	 document.getElementById("fromDate").value=""; 
     document.getElementById('dispname').value="";  
     document.getElementById('userName').value="";  
     document.forms[0].action="patientDetailsNew.do?actionFlag=getDrugDistReport";
 	 document.forms[0].submit();
     
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
	if(document.forms[0].dispname.value=='' || document.forms[0].dispname.value==null){
		alert('Please Select Wellness Center Name ');
		focusBox(document.getElementById("dispname"));
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
function newexportToCsv(arg)
{
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
		
			document.forms[0].action = "patientDetailsNew.do?actionFlag=drugreportCsv&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&reportType="+arg;
		
			document.forms[0].submit();  
	   
}

function fn_getDrugDetailed(drugType,drugId){
	
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	var userName=document.getElementById('userName').value;
	  //var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugType="+drugType+"&drugId="+drugId;
	   var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugTypeNew="+drugType+"&userName="+userName+"&drugId="+drugId;
		var x=window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
}
function fn_getDrugDetailedCase(caseId)
{
	
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	var userName=document.getElementById('userName').value;
    var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&userName="+userName+"&caseId="+caseId;
    var x=window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
	
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
	var detailedRep = '${fromDetailed}';
		var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	
if( detailedRep=="N")
{
	
	var url="./patientDetailsNew.do?actionFlag=drugReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&pageId="+pageId+"&endIndex="+endIndex;
	document.forms[0].action=url;
	 document.forms[0].submit(); 
 }
else if ( detailedRep=="Y")
	{
	
     var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&pageId="+pageId+"&endIndex="+endIndex;
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
		<th colspan="4" class="tbheader">Drug Distribution Report</th>
	</tr>
	<tr>
		<td class="labelheading1 tbcellCss" style="width:20%;"  align="left"><b>From Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" style="width:20%;"  align="left"><html:text property="fromDate" styleId="fromDate" style="width:70%"/></td>
		<td  class="labelheading1 tbcellCss" style="width:20%;"  align="left"><b>To Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" style="width:20%;"  align="left"><html:text property="destDate" styleId="destDate" style="width:70%"/></td>
	
	
	</tr>
		<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	Wellness Center Name</b>&nbsp;<span style="color:red">*</span></td>
	<td align="left"  class="tbcellBorder">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center" onchange="fn_getusers();">
						<option value="">--------Select---------</option>
						 <c:if test="${loginName eq 'TG_D082' or  loginName eq 'TG_D093' or loginName eq 'TG_C246'}">
						<option value="ALL">ALL WCs</option>
						</c:if> 
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" ><b>	User Type</b></td>
	<td align="left"  class="tbcellBorder">

<html:select  property="userName" name="patientForm" styleId="userName"   title="Select User Name">
						<option value="All">All Users</option>
						<c:if test="${fn:length(users) gt '0'}">
						<html:options collection="users"  property="USERNAME" labelProperty="USERID"></html:options>
						 </c:if> 
						</html:select> 

	</td>
	</tr>
	<tr>
	<td colspan="4"  align="center">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/>
		<input class="but" type="button"  value="Generate Detailed Report" onclick="javascript:generateDetailed()"/>
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" />
		</td>

	
		
	</tr>
</table>

<logic:present name="patientForm" property="drugReportList">
<bean:size id="size" name="patientForm" property="drugReportList"/>

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
										<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer; top: 0px;"></span></a></li>
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

<div  style="clear:both;float:right;margin-right:5%; padding-bottom: 1%;">
<span><b>Download as: &nbsp;&nbsp;</b></span>
<c:if test="${drugReportList ne null}">
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv('general')"/>
</c:if>
<c:if test="${drugDetailedReportList ne null}">
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv('detailed')"/>
</c:if>
&nbsp;&nbsp;<a href="javascript:fn_print('miscActTbl','','Drug Distribution Report');" title="Print"><span class="glyphicon glyphicon-print"></span></a> 
</div>
<table class="table" id="miscActTbl"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1" > S NO   </th>
<c:if test="${drugDetailedReportList ne null}">
<th class="tbheader1" > CASE ID </th>
<th class="tbheader1" > PATIENT NAME </th>
<th class="tbheader1" > CARD NUMBER </th>
</c:if>
<c:if test="${userName ne 'All' || drugDetailedReportList ne null}">
<th class="tbheader1" > LOGIN NAME </th>
</c:if>
<th class="tbheader1" > DRUG TYPE </th>
<th class="tbheader1" > DRUG NAME  </th>
<!-- <th class="tbheader1" > MANUFACTURER </th>
<th class="tbheader1" > DISTRIBUTOR </th>
<th class="tbheader1" > BATCH NO </th> -->
<th class="tbheader1" > PRESCRIBED QUANTITY  </th>
<th class="tbheader1" > ISSUED QUANTITY  </th>
<!-- <th class="tbheader1" > EXPIRY DATE </th> -->
<c:if test="${drugDetailedReportList ne null}">
<th class="tbheader1" > ISSUED DATE </th>
</c:if>
<!-- <th class="tbheader1" > WELLNESS CENTER NAME </th> -->
</tr>
<logic:iterate name="patientForm" property="drugReportList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${startIndex+cnt+1}</td>


<c:if test="${drugDetailedReportList ne null}">

 <logic:notEmpty name="labelBean" property="CASEID">
<td align="left" class="tbcellBorder" >
<c:if test="${drugDetailedReportList ne null}">
<a href="javascript:" onclick="fn_getDrugDetailedCase('<bean:write name="labelBean" property="CASEID" />')">
<bean:write name="labelBean" property="CASEID"/></a>
</c:if>
<%-- <c:if test="${drugDetailedReportList ne null}">
<bean:write name="labelBean" property="CASEID"/>
</c:if> --%>

</td>
</logic:notEmpty>


<logic:empty name="labelBean" property="CASEID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
  <logic:notEmpty name="labelBean" property="NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>

  <logic:notEmpty name="labelBean" property="EHFCARDNO">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="EHFCARDNO"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="EHFCARDNO">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
</c:if>
<c:if test="${userName ne 'All' || drugDetailedReportList ne null}">
	 <logic:notEmpty name="labelBean" property="LOGINNAME">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="LOGINNAME"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="LOGINNAME">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
 </c:if>
    <logic:notEmpty name="labelBean" property="DRUGTYPE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGTYPE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGTYPE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>
      
<logic:notEmpty name="labelBean" property="DRUGNAME">
<td align="left" class="tbcellBorder" >
<c:if test="${drugReportList ne null}">
<a href="javascript:" onclick="fn_getDrugDetailed('<bean:write name="labelBean" property="DRUGTYPES" />','<bean:write name="labelBean" property="DRUGIDS" />')">
<bean:write name="labelBean" property="DRUGNAME"/></a>
</c:if>
<c:if test="${drugDetailedReportList ne null}">
<bean:write name="labelBean" property="DRUGNAME"/>
</c:if>

</td>
</logic:notEmpty>


<logic:empty name="labelBean" property="DRUGNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
<%-- 
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
  --%>
     <logic:notEmpty name="labelBean" property="PRESC_QUANTITY">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PRESC_QUANTITY"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="PRESC_QUANTITY">
 <td align="left" class="tbcellBorder" >0</td>
 </logic:empty>  
     <logic:notEmpty name="labelBean" property="QUANTITY">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="QUANTITY"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="QUANTITY">
 <td align="left" class="tbcellBorder" >0</td>
 </logic:empty>  
 <%-- 
    <logic:notEmpty name="labelBean" property="EXPDT">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="EXPDT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="EXPDT">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
  --%>
 <c:if test="${drugDetailedReportList ne null}">
    <logic:notEmpty name="labelBean" property="ISSUEDATE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ISSUEDATE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="ISSUEDATE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
</c:if>
<%-- <logic:notEmpty name="labelBean" property="DISPNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISPNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DISPNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> --%>
</tr>
</logic:iterate>
</table>
</logic:greaterThan>



</logic:present>
<logic:notPresent  name="patientForm" property="drugReportList">
 <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>


</logic:notPresent>

<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />

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
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>DRUG DISTRIBUTION REPORT</td>
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