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
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<title>Detailed Drug Distribution Report</title>

<script type="text/javascript">
function newexportToCsv(arg)
{
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	//var dispname=document.getElementById('dispname').value;
		
			document.forms[0].action = "patientDetailsNew.do?actionFlag=drugreportCsv&destDate="+destDate+"&fromDate="+fromDate+"&reportType="+arg;
		
			document.forms[0].submit();  
	   
}

function fn_Print() {
	window.print();
}

function closeNrefresh()
{
	window.close();
}

</script>
<script>



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
	var fromDate='${fromdate}';
	var destDate='${todate}';
	var drugType='${drugType}';
	var drugId='${drugId}';
	  //var url="./reportsAction.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugType="+drugType+"&drugId="+drugId;
	   var url="./patientDetailsNew.do?actionFlag=drugDetailedReport&destDate="+destDate+"&fromDate="+fromDate+"&drugTypeNew="+drugType+"&drugId="+drugId+"&pageId="+pageId+"&endIndex="+endIndex;
/* 		var x=window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
 */
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
</script>
</head>
<body>
<html:form action="/patientDetailsNew.do">
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

<%-- <div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<span><b>Download as: &nbsp;&nbsp;</b></span>
<c:if test="${drugReportList ne null}">
<img id="csvImg" src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:newexportToCsv('general')"/>
</c:if>
<c:if test="${drugDetailedReportList ne null}">
<img id="csvImg" src="images/csv1.png" onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:newexportToCsv('detailed')"/>
</c:if>
</div>
 --%>


<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1" > S.NO   </th>
<th class="tbheader1" > PATIENT ID </th>
<th class="tbheader1" > PATIENT NAME </th>
<th class="tbheader1" > DRUG TYPE </th> 
 <th class="tbheader1" > DRUG NAME  </th> 
<th class="tbheader1" > MANUFACTURER </th> 
 <th class="tbheader1" > DISTRIBUTOR </th> 
 <th class="tbheader1" > BATCH NO </th> 
<th class="tbheader1" > DRUG ISSUED DATE</th>
<th class="tbheader1" > ISSUED QUANTITY  </th>
 <th class="tbheader1" > EXPIRY DATE </th> 

</tr>
<logic:iterate name="patientForm" property="drugReportList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${cnt+1}</td>



 <logic:notEmpty name="labelBean" property="CASEID">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="CASEID"/></td>
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


    <logic:notEmpty name="labelBean" property="DRUGTYPE"> 
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGTYPE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGTYPE">
 <td align="left" class="tbcellBorder" >-NA-</td> 
 </logic:empty>
      
  <logic:notEmpty name="labelBean" property="DRUGNAME">
<td align="left" class="tbcellBorder" > 
<bean:write name="labelBean" property="DRUGNAME"/>
</td> 
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGNAME">
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

    <logic:notEmpty name="labelBean" property="EXPDT"> 
 <td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="EXPDT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="EXPDT">
 <td align="left" class="tbcellBorder" >-NA-</td> 
 </logic:empty>   
</tr>
</logic:iterate>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="drugReportList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
	<table style="width:100%;align:center">
	<tr class="print_buttons">
			<td align="center">
				<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
				<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
			</td>
		</tr>	
		</table>	
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />

</html:form>


</body>
</html>