
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
      <%@ include file="/common/include.jsp"%>

<html>
<head>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/navyblue/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<script  src="bootstrap/js/modernizr.min.js"></script>
<script  src="bootstrap/js/css3-mediaqueries.js"></script> 
<script  src="bootstrap/js/html5.js"></script>
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<title>Search Employee Results</title>
<style>
 body{font-size:1.2em !important;} ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
</style>
<script>
function fn_pagination(pageId,end_index)
{
	/* var deptName= document.getElementById('deptName').value;
	var desgName=document.getElementById('desgName').value;
	var loginName=document.getElementById('loginName').value;
	var empName=document.getElementById('empName').value;
	var status=null;
	if(document.getElementById('status').checked==true)
	 status=document.getElementById('status').value;
	var groupName=document.getElementById('groupName').value; */
	
	//document.forms[0].Search.disabled = true;
	document.forms[0].action='patientDetailsNew.do?actionFlag=leaveReport&fromDispnsry=Y&pageId='+pageId+'&end_index='+end_index;
	//document.forms[0].action='createEmployee.do?actionFlag=leaveReport&pageId='+pageId+'&end_index='+end_index+'&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName;
    
	document.forms[0].submit();
}
function showinSetsOf(num)
{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	fn_pagination(1,num);	
}
function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	pageDisplays=pageDisplays+'<ul class="pagination">';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var i=0;
	if(minPageNo>1)
	{
		//pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
		pageDisplays=pageDisplays+'<li> <span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
	}
	/* else
	{
		minPageNo=minPageNo+1;
	} */
	if(minPageNo==0)
		{
			minPageNo=1;
		}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			pageDisplays=pageDisplays+' <li class="active"><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			pageDisplays=pageDisplays+' <li><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var i=0;
	pageDisplays=pageDisplays+'<ul class="pagination">';
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	//pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')">Previous</a>&nbsp;&nbsp;';
	pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
			pageDisplays=pageDisplays+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		else
		{
			//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
			pageDisplays=pageDisplays+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
		}
		
	}
	if(i<noOfPages)
	{
		//pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')">Next</a>';
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function showPagination(num)
{	
	document.forms[0].showPage.value=num; 
	fn_pagination(num,document.forms[0].rowsPerPage.value);	
}
/* function fn_pagination(pageNo,rowsPerPage) 
{
	
	var deptName= document.getElementById('deptName').value;
	var desgName=document.getElementById('desgName').value;
	var loginName=document.getElementById('loginName').value;
	var empName=document.getElementById('empName').value;
	var status=document.getElementById('status').value;
	var groupName=document.getElementById('groupName').value;
	parent.document.getElementById("empSearchFrame").src='createEmployee.do?actionFlag=searchResultList&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName+'&pageNo='+pageNo+'&rowsPerPage'+rowsPerPage;
	
	document.forms[0].method=post";
	document.forms[0].submit(); 
	
	document.forms[0].Search.disabled = true;
} */

function exportToExcel()
{
/* 	var deptName= document.getElementById('deptName').value;
	var desgName=document.getElementById('desgName').value;
	var loginName=document.getElementById('loginName').value;
	var empName=document.getElementById('empName').value;
	var status=document.getElementById('status').value;
	var groupName=document.getElementById('groupName').value; */
	disablingButtonsAndImgs('excel');
	document.forms[0].action='patientDetailsNew.do?actionFlag=leaveReport&fromDispnsry=Y';
<%-- 	document.forms[0].action='/<%=context%>/createEmployee.do?actionFlag=searchResultList&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName+'&excelFlag=yes';
 --%>   	document.forms[0].submit();
}
function exportToCsv()
{
 
	/* var deptName= document.getElementById('deptName').value;
	var desgName=document.getElementById('desgName').value;
	var loginName=document.getElementById('loginName').value;
	var empName=document.getElementById('empName').value;
	var status=null;
	if(document.getElementById('status').checked==true)
		status=document.getElementById('status').value;
	var groupName=document.getElementById('groupName').value; */

	
	disablingButtonsAndImgs('csv');
	document.forms[0].action='patientDetails.do?actionFlag=leaveReportNew&fromDispnsry=Y';
/* 	document.forms[0].action='createEmployee.do?actionFlag=searchResultList&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName+'&excelFlag=yes';
 */   	document.forms[0].submit();
	 		
}
function disablingButtonsAndImgs(type)
{
	document.getElementById('csvImg').disabled=true;
//	document.getElementById('exlImg').disabled=true;
	
}
function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}

function fn_removeLoadingImage()
 {   
   document.getElementById('processImagetable').style.display="none";
 }
function searchEmployee()
{

	desgName=document.getElementById('desgName').value;
	loginName=document.getElementById('loginName').value;
	empName=document.getElementById('empName').value;
	status="";
			
			
			 for (var i=0; i<document.forms[0].elements.length; i++)
				{	
					var type = document.forms[0].elements[i].type;
					if (type=="radio")
					{	
						if(document.forms[0].elements[i].checked)
						{	
							status=document.forms[0].elements[i].value;
						}
					}
				}
			
			

	 if((desgName=="")&&(status=="")&&(loginName==null ||loginName=="")&&(empName==""||empName==null)){
		 jqueryAlertMsg("Alert","Please select any search criteria");
			  return;
		}
	 else{	
		   document.forms[0].action='patientDetailsNew.do?actionFlag=leaveReport&fromDispnsry=Y';
/* 		    document.forms[0].action='createEmployee.do?actionFlag=searchResultList&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status;
 */		   	document.forms[0].submit();
	 }
	 
			}
	
//To close the iframe
  
function resetPage()
{
	 document.getElementById('deptName').value="";$('#deptName-input').val('');
	 document.getElementById('desgName').value="";$('#desgName-input').val('');
	 document.getElementById('loginName').value="";
	 document.getElementById('empName').value="";
	 //document.getElementById('status').value="";$('#status-input').val(''); 
	document.forms[0].status[0].checked=false;
	      document.forms[0].status[1].checked=false;
	 document.getElementById('groupName').value="";$('#groupName-input').val('');
	 document.getElementById('hospName').value="";$('#hospName-input').val('');
	 document.getElementById("empSearchFrame").src="";
     document.getElementById("iFrameIdEmpSearch").style.display="none";
     document.getElementById("empSearchFrame").style.display="none"; 
     document.getElementById('schemeType').value=""; $("#schemeType-input").val('');
    // $("#schemeType-input").val($("#schemeType option:first").text());
}
function fn_setOption()
{
	$("#schemeType-input").val($("#schemeType option:first").text());
	 parent.fn_removeLoadingImage();
}
/* function fn_updateEmp(id)
{ 	
		var 	url = 'createEmployee.do?actionFlag=loadDispEmployee&fromDispnsry=Y&empId='+id;    
	     document.getElementById("empSearchFrame").src="";
		 document.getElementById("empSearchFrame").src=url;
} */
function fn_disbaled(){
	document.forms[0].status[0].checked=false;
	document.forms[0].status[1].checked=false;
	
}
</script>

</head>
<body onload="fn_disbaled();">
<form action="/patientDetailsNew.do" method="post">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss">
                                   <div class="tbheader1"><b>Leave Report </b></div>
                                   
</div>


	<%-- 				<div class="container-fluid center-block">
						<div class="row">
                        	<div class="col-lg-6 col-md-46col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								 Employee Id:
		                         <html:text property="loginName" name="patientForm" styleId="loginName" styleClass="form-control" title="Login Name of Employee">
								</html:text>
							</div>
								 <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						 First Name:
                         <html:text property="empName" name="patientForm" styleId="empName" styleClass="form-control" title="Name of the Employee">
						</html:text>
						</div>
				      	
					  </div>
				
						<div class="row">
				       		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								 Designation:
		                         <html:select property="desgName" name="patientForm" styleId="desgName" styleClass="form-control" title="Select Designation">
								<option value="">--------Select---------</option>
								<logic:notEmpty name="desgNamesList"></logic:notEmpty>
								 <html:options collection="desgNamesList" labelProperty="VALUE" property="ID"/>
								</html:select>
							</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						
						Status:
						<br>
						 <html:radio title="Active" name="patientForm" property = "status"  styleId="status"  value = "Y" onclick=""/>Active &nbsp;&nbsp;
						<html:radio title="Inactive" name="patientForm" property = "status"  styleId="status"  value = "N" onclick=""/>Inactive
					  </div>
		
			
					
						
						</div>
		
						</div>
						
                              
						</div> 
						<div></div>
                       <div align="center">
                                 <button class="btn but"   id="search" name="search" tabindex="0" type="button"  title="Search"   onClick="searchEmployee();">Search</button>
                                  <button class="btn but"   id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetPage();">Reset</button>   
                     </div> --%>
            



<div  class="leftonePag " style="float:left;width:40%;">
		<ul class="pagination" style="min-width:100%;">
		<%-- <td width="25%">Total No of records found:${noOfRecords}</td>	  
		<td width="25%">Displaying records from ${start_index+1} to ${start_index+end_index}</td>  --%>
		<li class="lispacing" style="padding-right:10%">Total No of records found:${noOfRecords}</li>
		<li class="lispacing" >Displaying records from ${start_index+1} to ${endresults}</li>
	</ul>
	</div>	
	<!-- <td align="center" colspan="1" id="pageNoDisplay"  style="width:25%">col-xs-12 col-sm-12 col-md-9 col-lg-3 -->
	<div id="pageNoDisplay" class="centeronePag " style="float:left;">
	<ul class="pagination">
<%
int noOfPages = ((Integer) request.getAttribute("lastPage")).intValue();
int selectedPage = ((Integer) request.getAttribute("pageNo")).intValue();
int end_index=((Integer) request.getAttribute("end_index")).intValue();
int pageNo=0;
int a=selectedPage/10;
int minVal=(a*10);
if(selectedPage%10==0)
	{
		minVal=minVal-10;
	}
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages;
}
if(minVal>=10&&minVal!=noOfPages)
	{
		minVal=minVal+1;
		%>
		<%-- <a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Prev</a>&nbsp; --%>
			<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></a></li>
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<=maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
			<%-- <b><%=pageNo%></b> --%>
			<li class="active"><span><%=pageNo%></span></li> 
			<%
		}
		else
		{
			%>
			<%-- <a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp; --%>
			<li><a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><%=pageNo%></a></li>
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<%-- <a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a> --%>
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></li>
		<%
	}
		%>
		<!-- </td> col-xs-12 col-sm-12 col-md-9 col-lg-4-->
		</ul>
		</div>
		<div class="rightonePag " style="float:right;padding-right:19px" style="margin:0px 0px 0px 0px">
		<ul class="pagination">
		<!-- <td width="25%"> --><li class="lispacing">Show results in sets of </li><c:if test="${end_index ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
												<c:if test="${end_index eq 10}"><li class="active"><span>10</span></li></c:if>	
												<c:if test="${end_index ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
												<c:if test="${end_index eq 20}"><li class="active"><span>20</span></li></c:if>
												<c:if test="${end_index ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
												<c:if test="${end_index eq 50}"><li class="active"><span>50</span></li></c:if>
												<c:if test="${end_index ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
												<c:if test="${end_index eq 100}"><li class="active"><span>100</span></li></c:if><!-- </td> -->
												
	<!-- </table> -->
	</ul>
	</div>
                                  
 
  <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 pull-right" >
                                    
                                    <table  width="100%">
<!-- <tr style="height:2em">
<td colspan="5" style="width:10em;">
<font size="2px"><b>Download Search Results as:</b></font>
<img id='exlImg' src="images/excel1.png"  onmouseover="this.src='images/excel2.png'" onmouseout="this.src='images/excel1.png'" onclick="javascript:exportToExcel()"/>  	
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'"  onclick="javascript:exportToCsv()"/>
<img id="csvImg" src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript:exportToCsv()"/>
</td>
</tr> -->
</table>
</div>
</div>
                                    <br>


<html:hidden name="patientForm" property="startIndex" styleId="startIndex"/>

<%
int i = 1;
%>
<c:set var="index1" value="${start_index+1}" />
<table  width="100%" class="table table-bordered ">

<tr>
<th class="tbheader1" style="width: 40px; height: 20px;">S.No</th>
<th class="tbheader1" style="width:15%">Name</th>
<th class=" visible-md visible-lg tbheader1" style="width:10%">Speciality</th>
<th class=" visible-md visible-lg tbheader1" style="width:15%">Mobile Number</th>
<th class=" visible-md visible-lg tbheader1" style="width:15%">Leave From Date</th>
<th class=" visible-md visible-lg tbheader1" style="width:10%">Leave To Date</th>

</tr>


<logic:iterate name="patientForm" property="levRepList" id="empList" indexId="index">
<tr>
<td class="tbcellBorder" style="width: 5%; height: 20px;">${index1}</td>
<td class="tbcellBorder" align="center" title="Employee Name"><bean:write name="empList" property="fName"/></td>
<td class="tbcellBorder visible-md visible-lg" align="center" title="Employee Designation"><bean:write name="empList" property="desgName"/></td>
<td class="tbcellBorder visible-md visible-lg" align="center" title="Employee Designation"><bean:write name="empList" property="mobileNo"/></td>
<td class="tbcellBorder visible-md visible-lg" align="center" title="Employee Designation"><bean:write name="empList" property="fromDate"/></td>
<td class="tbcellBorder visible-md visible-lg" align="center" title="Employee Designation"><bean:write name="empList" property="toDate"/></td>

</tr>
<c:set var="index1" value="${index1+1}" />
</logic:iterate>
</table>


<logic:empty name="empList">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;">
<tr>
<td align="center" height="50">
<b>No Results Found</b>
</td>
</tr>
</table>
</logic:empty>
<br>
 
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content" style="width:800px;">
                    
      
        <div class="tbheader1">
                             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
         <h4 class="modal-title" align="center">Update Emoloyee Details </h4>

     </div>
 
        <div class="modal-body"  style="height:600px;">

						 <div id="iFrameIdEmpSearch">
         <iframe width="100%" height="100%" style="width: 790px;height: 555px;" id="empSearchFrame"  name="empSearchFrame" frameborder="0" onload="fn_removeLoadingImage();">
        </iframe>
          </div>
		 </div>
      
                 
                 <!-- 		 
              <div id="processImagetable" style="top:40%;z-index:50;position:absolute;left:45%;display:none;" align="center">
                <img src="images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>  -->
            
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
        </div>
     
    </div>
  </div>
</div>
<%-- <html:hidden  name="patientForm"  property="deptName" styleId="deptName" />
 <html:hidden  name="patientForm"  property="desgName" styleId="desgName" />
  <html:hidden  name="patientForm"  property="loginName" styleId="loginName" />
   <html:hidden  name="patientForm"  property="empName" styleId="empName" />
      <html:hidden  name="patientForm"  property="status" styleId="status" />
      <html:hidden  name="patientForm"  property="groupName" styleId="groupName" />
      
  <html:hidden property="showPage" name="patientForm"/> --%>
		<html:hidden property="startPage" name="patientForm" value="${startPage}"/>
		<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
		<html:hidden property="rowsPerPage" name="patientForm"/>


 </form>
</body>

</html>