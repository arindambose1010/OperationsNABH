<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="/common/include.jsp"%>
<html>
<fmt:setLocale value='${langID}'/>  
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<style type="text/css">.centerone{padding-left:6%;}</style>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%@ include file="/common/editableComboBox.jsp"%>  
<%@ include file="/common/includePatientDetails.jsp"%> 
<style type="text/css">
body{font-size:1.3em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}

.centerone {
padding-left:5%;
}
*{
font-size: 13px;
}
</style>
<script type="text/javascript">
var myApp;
myApp = myApp || (function () {
    var pleaseWaitDiv = $('<div class="modal fade" id="pleaseWaitDialog" data-backdrop="static" data-keyboard="false" role="dialog" aria-labelledby="basicModal" aria-hidden="true" tabindex="-1"><div class="modal-dialog" style="width:275px;"><div class="modal-content" style="height:16%;"><div class="modal-header" style="height:40px;background:white !important;"><h5>Processing...</h5></div><div class="modal-body" style="height:25px;"><div class="progress progress-striped active"><div class="progress-bar" style="width: 100%;height:15px;border-radius:15px;"><span class="sr-only">60% Complete</span></div></div></div></div></div></div></div></div>');
    return {
        showPleaseWait: function () {
            pleaseWaitDiv.modal();
        },
        hidePleaseWait: function () {
            pleaseWaitDiv.modal('hide');
        },

    };
})();

$(document).ready(function() {
	 $('#fromDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:'01/01/2012',
			endDate:new Date(),
		})
		$('#toDate').datepicker({
			startDate: '01/01/2012',
			todayHighlight:true,
			format : 'dd-mm-yyyy',
			autoclose:true,
			clearBtn:true,
			endDate:new Date(),
		})
		});
function fn_generatereport(){
	
	 var date1 = document.getElementById('fromDate').value;
   	 var date2 = document.getElementById('toDate').value;
   	if(document.forms[0].fromDate.value==='' || document.forms[0].fromDate.value===null){
		alert('Please Select  From Date');
		document.forms[0].fromDate.focus();
		return;
	}
	 if(document.forms[0].toDate.value==='' || document.forms[0].toDate.value===null){
		alert('Please Select To Date');
		document.forms[0].toDate.focus();
		return;
		}

  /*   var fromStartDtSplit=date1.split('-');																
    inputfrmdt=new Date(fromStartDtSplit[2],fromStartDtSplit[1]-1,fromStartDtSplit[0]);

    var toStartDtSplit=date2.split('-');																
    inputtoDt=new Date(toStartDtSplit[2],toStartDtSplit[1]-1,toStartDtSplit[0]);
 
    var diff = inputtoDt.getTime() - inputfrmdt.getTime();
    diff = diff / (1000 * 60 * 60 * 24 );
     if(diff > 90)
      	{
  			alert('Please select Less than 3 months');
			return ;
       	} */
    else if(document.forms[0].schemeType.value == '-1' )
  	    {    
  	     alert("Please select scheme");
  	     document.forms[0].schemeType.focus();
  	     return;    
  	    }
    else{
    	myApp.showPleaseWait();
    	 document.forms[0].action="casesSearchAction.do?actionFlag=nabhReport";
    	 caseSearchURl="casesSearchAction.do?actionFlag=nabhReport";
    	 parent.setGlobalUrl(caseSearchURl);
	     document.forms[0].submit();
		}
}

function enableGenerate(){
	document.getElementById("generate").disabled=false;

}
function disableGenerate(){
	document.getElementById("generate").disabled=true;
	
}
function exportToCSV()
{
	  //myApp.showPleaseWait();
	  document.forms[0].action="casesSearchAction.do?actionFlag=nabhReport&csvFlag=Y";
	  caseSearchURl="casesSearchAction.do?actionFlag=nabhReport&csvFlag=Y";
	  parent.setGlobalUrl(caseSearchURl);
      document.forms[0].submit();
	
}
function fn_blockScreen()
{
	$(function () { 
		 var $modal = $('#progressBar'),
	    $bar = $modal.find('.progress-bar progress-bar-striped active');
	
	$modal.modal('show');
	$bar.addClass('animate');
	
	setTimeout(function() {
	  $bar.removeClass('animate');
	  $modal.modal('hide');
	}, 60000);
	});	
}


function showinSetsOf(num)
{   

		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPageNew.value='1'; 
		fn_pagination(1,num);
}

function showPagination(num)
{
		document.forms[0].showPageNew.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);		
}
	
function fn_pagination(pageId,endIndex)
{
	 myApp.showPleaseWait();
	  document.forms[0].action="casesSearchAction.do?actionFlag=nabhReport&endIndex="+endIndex+"&pageId="+pageId;
	  caseSearchURl="casesSearchAction.do?actionFlag=nabhReport&endIndex="+endIndex+"&pageId="+pageId;
	  parent.setGlobalUrl(caseSearchURl);
     document.forms[0].submit();
}
function fn_reset()
{
	document.getElementById('fromDate').value= '';
	document.getElementById('toDate').value= '';
	document.getElementById('schemeType').value= '-1';
	
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
<html:form method="post"  action="/casesSearchAction.do">
<table style="width:80%;margin:1% auto">
	<tr>
		<th colspan="4" class="tbheader">NABH Hospitals Report</th>
	</tr>
		<tr>
		<td class="labelheading1 tbcellCss" align="left" style="width: 25%;"><b>From Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" align="left" ><html:text property="fromDate" styleId="fromDate" style="width:70%"/></td>
		<td  class="labelheading1 tbcellCss" align="left" style="width: 25%;"><b>To Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" align="left"><html:text property="toDate" styleId="toDate" style="width:70%"/></td>
	</tr>
	 
     <tr>    
      <td width="15%" align=right class="labelheading1 tbcellCss" style="text-align: left"><b>Select Scheme</b>&nbsp;<span style="color:red">*</span></td>
   <td  width="25%" class="tbcellBorder" style="text-align: left">
             <html:select name="casesSearchFormClaims" property="schemeType" styleId="schemeType">
               <html:option value="-1" key="select" >----Select----</html:option>
               <html:option value="CD501" key ="select">EHS</html:option>
               <html:option value="CD502" key ="select">JHS</html:option>
              </html:select></td></tr>
	<tr>
		<td colspan="2"  align="right">
		<input class="but" type="button"  value="Generate" onclick="javascript:fn_generatereport()"/></td>
		<td colspan="2"  align="left">
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" /></td>
	</tr>
	</table>


<logic:present name="casesSearchFormClaims" property="nabhreport">
 <bean:size id="size" name="casesSearchFormClaims" property="nabhreport"/>   
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
			<span><b>Download as: &nbsp;&nbsp;</b></span><img  src="images/csv1.png"   onclick="javascript:exportToCSV()"/>
	</div>
 <table style=" width: 95%; margin: 0 auto;">
    <thead>
   			<tr>
    			<th class="tbheader1">S.No</th>
    			<th class="tbheader1" >Case Id</th>
    			<th class="tbheader1" >Patient Name</th>
    			<th class="tbheader1" >Card No</th>
    			<th class="tbheader1" >Age</th>
    			<th class="tbheader1">Hospital Name</th>
    			<th class="tbheader1">Status </th>
    			<th class="tbheader1">IP Registered Date</th>
    		</tr>
      </thead>  
      <logic:iterate name="casesSearchFormClaims" property="nabhreport" id="rowId" indexId="cnt">
       <tr>
				<td class="tbcellCss">${cnt+1}</td>
    			 <td class="tbcellCss"><bean:write name="rowId" property="CASEID" /></td>
                 <td class="tbcellCss"><bean:write name="rowId" property="PATIENTNAME" /></td>
                 <td class="tbcellCss"><bean:write name="rowId" property="CARDNO" /></td>
                 <td class="tbcellCss"><bean:write name="rowId" property="AGE" /></td>   
                 <td class="tbcellCss"><bean:write name="rowId" property="HOSPNAME"/></td>
                 <td class="tbcellCss"><bean:write name="rowId" property="CLIAMSTATUS" /></td>                
                 <td class="tbcellCss"><bean:write name="rowId" property="IPCASEREGDATE" /></td>
                
                 </tr>
        </logic:iterate>
   </table>
</logic:greaterThan>   
</logic:present>
<c:if test="${totalrecords eq 'N' }">
						 <div>
   </br>
        <img src="images/norec.jpg" align="middle" style="display: block; margin-left: auto; margin-right: auto; width: 14%;"> 
        </div> 
</c:if>
	
				
			<div class="modal fade"   id="progressBar" >
<div class="modal-dialog modal-lg">
 
      <div class="modal-body">
 
 <div class="centerProgress">
  <div class="progress">
    <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width:100%">
      <span>Your file is being downloaded.Please Wait for few Minutes</span>
    </div>
  </div>
</div>
</div></div></div>
<html:hidden property="showPageNew" name="casesSearchFormClaims" />
<html:hidden property="startPage" name="casesSearchFormClaims" value="${startPage}" />
<html:hidden property="endPage" name="casesSearchFormClaims" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="casesSearchFormClaims" />
</html:form>
</body>

</html>