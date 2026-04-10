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
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

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

<title>Allocate Employee</title>
<style>
 body{font-size:1.2em !important;} 
.but{
border-radius:16px;
}
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
.ui-autocomplete-input {
    width: 11em;
}
.no-margin{
margin:0px !important;
}
</style>
<script>
$(document).ready(function () {
	$('#createEmployeeForm').formValidation(
			{
				framework: 'bootstrap',
				icon: 
				{
		            valid: 'glyphicon glyphicon-ok',
		            invalid: 'glyphicon glyphicon-remove',
		            validating: 'glyphicon glyphicon-refresh'
				},
				 fields:{
							
					 empNumber:
					 {
				          validators: {
				                notEmpty: {
				                    message: 'The  employee number is required'
				                },
				                regexp:	{
		            				regexp: /^[a-zA-Z0-9]+$/,
		            				message: 'Employee Number can only consists of alphabets and numbers'
		            				}
		            		}
		            },
		            loginName: {
		                validators: {
		                    notEmpty: {
		                        message: 'The login name name is required'
		                    },
		                    regexp: {
		                        regexp: /^[a-zA-Z0-9]+$/,
		                        message: 'The login name can only consist of alphabets'
		                    }
		                }
		            }
		      
				 }

			});
		 

	});
</script>
<script>
		
function validateUserNo(arg){
	
	var userNo = document.getElementById(arg).value;
	var dot=/^[.!@#$%^&*()~|+,]+$/;
      if(userNo!="")	{	
   if(userNo.match(dot))
	 {
         var fr=partial(focusBox, document.getElementById(arg));
         jqueryAlertMsg("Alert","Plaese enter valid Employee Number",fr);
         document.getElementById(arg).value="";	
         return ; 
     }
      }
}
function addTooltip(id)
{
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;		

}
function validateLoginName(arg){
	
	var loginName = document.getElementById(arg).value;
	var dot=/^[.!@#$%^&*()~|+,]+$/;
      if(loginName!="")	{	
   if(loginName.match(dot))
	 {
         var fr=partial(focusBox, document.getElementById(arg));
         jqueryAlertMsg("Alert","Plaese enter valid Login Name",fr);
         document.getElementById(arg).value="";	
         return ; 
     }
      }
}

function loadEmployee()
{

 	/* $('#createEmployeeForm').formValidation().formValidation('validate');
	var x=$('#createEmployeeForm').data('formValidation').isValid(); 
	alert(x); */
	var empNumber=document.getElementById('empNumber').value;
	var loginName=document.getElementById('loginName').value;
	 var radioValue1="";	
	 
	 
	 
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="allocationFlag")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					 radioValue1=document.forms[0].elements[i].value;							
				}
			
			}
		
		}

	if(empNumber=="" && loginName=="")
	{
	var fr=partial(focusBox,document.getElementById('empNumber'));
	jqueryAlertMsg("Alert","Please enter Employee Number or Login name",fr);
	return false;
	}
	if(document.forms[0].allocationFlag[0].checked==false && document.forms[0].allocationFlag[1].checked==false  )
	{
	 var fr=partial(focusBox,document.getElementById('allocationFlag'));
	jqueryAlertMsg("Alert","Please Select the action whether to Allocate/Unallocate employee",fr);
	return false;
	}	
  else
	{
	  
	 fn_pagination('1','10');
	 
	}

}
function partial(func /*, 0..n args */) {
	var args = new Array();
	for ( var i = 1; i < arguments.length; i++) {
		args.push(arguments[i]);
	}
	return function() {
		var allArguments = args.concat(Array.prototype.slice
				.call(arguments));
		return func.apply(this, allArguments);
	};
}
function focusBox(arg) {

	aField = arg;
	setTimeout("aField.focus()", 0);

}
function resetPage()
{
	document.getElementById('empNumber').value="";
	document.getElementById('loginName').value="";
	document.forms[0].allocationFlag[0].checked=false;
	document.forms[0].allocationFlag[1].checked=false;
	$('#empployeesTable').empty();
	document.getElementById("vacPosTable").style.display="none";
	document.getElementById("vacPosTable1").style.display="none";
	document.getElementById('allocateTR').style.display="none";
	document.getElementById('noRecords').style.display="none";
	
	
}

function fn_getVacantPosition()
{
	document.getElementById('vacPos1').style.visibility="visible";
	document.getElementById('vacPos2').style.visibility="visible";	
	document.getElementById('level1').style.visibility="hidden";
	document.getElementById('level2').style.visibility="hidden";
	document.getElementById('vacPosTR').style.display="none";
	
	document.getElementById('allocateTR').style.display="none";

	var xmlhttp;
	var url;
	var deptId=document.getElementById('deptName').value;
	url= 'createEmployee.do?actionFlag=getVacantPosition&deptId='+deptId;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.forms[0].vacPosName.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){   
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].vacPosName.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
	        if(resultArray=="[]")
			{
			
			document.forms[0].vacPosName.options[0]=new Option('No Vacant Position',"NA");
			 var fr=partial(focusBox,document.getElementById('vacPosName'));
				jqueryAlertMsg("Alert","Please create a new vacant position for creating new employee ",fr);
				return false;
			}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	document.getElementById('vacPosName').options.length=0;
	$('#vacPosName-input').val('');
	
	
	
	
}

function fn_getVacantPositionDetails()
{
	var xmlhttp;
	var url;
	var deptId=document.getElementById('deptName').value;
	var vacPosName=document.getElementById('vacPosName').value;
	 document.forms[0].groupName.value="";
	 if(vacPosName!="NA"){
		 document.getElementById('level1').style.visibility="visible";
			document.getElementById('level2').style.visibility="visible";
			document.getElementById('vacPosTR').style.display="";
			document.getElementById('allocateTR').style.display="";
	url= 'createEmployee.do?actionFlag=getVacantPosDetails&dept='+deptId+'&vacPosName='+vacPosName;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){
		    
	    	var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");
		         resultArray2=resultArray2.split("^");
	        	var vacPosDtls = resultArray2[0].split("~");
	        	document.getElementById('level').value=vacPosDtls[0];
	        	document.getElementById('desgName').value=vacPosDtls[1];
	        	document.getElementById('repPerson').value=vacPosDtls[2];
	        	
	        	
	        	var groupNames=resultArray2[1].split("~");
	        	
	        	if(groupNames.length>0){   
	                for(var i = 0; i<groupNames.length;i++){
	                	
	                     if(groupNames[i]!="null"){
	                         var val1 = groupNames[i].replace(/^\s+|\s+$/g,"");
	                         if(document.forms[0].groupName.value!=null)
	                     	 document.forms[0].groupName.value +=val1+"\n";  
	                         else
	    	                   document.forms[0].groupName.value=val1;
					   } 
	                     else
	                    	 document.forms[0].groupName.value="No Groups Found";
	                }
	            }
	        	
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	
	 }

	
	
}
function fn_getVacantPositions()
{
	 var radioValue="";	
	
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="allocationFlag")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					 radioValue=document.forms[0].elements[i].value;	
				}
			
			}
		
		}
		if(radioValue=="A")
			{ 
			document.getElementById('deptName').value="";$('#deptName-input').val('');
			  document.getElementById("vacPosTable").style.display="";
			  document.getElementById('vacPosTR').style.display="none";
			  document.getElementById('allocateTR').style.display="none";
			  document.getElementById('vacPos1').style.visibility="hidden";
				document.getElementById('vacPos2').style.visibility="hidden";
				document.getElementById('level1').style.visibility="hidden";
				document.getElementById('level2').style.visibility="hidden";
				}
		else
			{
			document.getElementById('allocatedDeptName').value="";$('#allocatedDeptName-input').val('');
			 document.getElementById("vacPosTable1").style.display="";
			 document.getElementById("empPos1").style.visibility="hidden";
				document.getElementById("empPos2").style.visibility="hidden";
				document.getElementById("allocRepPerson1").style.visibility="hidden";
				document.getElementById("allocRepPerson2").style.visibility="hidden";
				document.getElementById("unAllocateTR").style.display="none";
			 
			 var radioValue1="";
			
			 for (var i=0; i<document.forms[0].elements.length; i++)
				{	
					var type = document.forms[0].elements[i].type;
				    var id=document.forms[0].elements[i].id;
					if (type=="radio" && id=="employeeRow")
					{
						
						if(document.forms[0].elements[i].checked)
						{
							 radioValue1=document.forms[0].elements[i].value;							
						}
					
					}
				
				}


			 var xmlhttp;
				var url;
				url= 'createEmployee.do?actionFlag=getAllocDetails&selectedRadio='+radioValue1;
				if (window.XMLHttpRequest){
				 xmlhttp=new XMLHttpRequest();
				}
				else if (window.ActiveXObject){		
				 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				else{
				 alert("Your Browser Does Not Support XMLHTTP!");
				}
				xmlhttp.onreadystatechange=function(){
				    if(xmlhttp.readyState==4){	
				    	document.forms[0].allocatedDeptName.options[0]=new Option('-------- Select --------',"");
				        var resultArray=xmlhttp.responseText;
				        if(resultArray!= null){
				        	 resultArray2 = resultArray.replace("[","");
					         resultArray2 = resultArray2.replace("]","");            
					         var addList = resultArray2.split(", @");
					         if(addList.length>0){   
					                for(var i = 0; i<addList.length;i++){	
					                    var arr=addList[i].split("~");
					                     if(arr[1]!=null && arr[0]!=null){
					                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
					                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					                     	 document.forms[0].allocatedDeptName.options[i+1] =new Option(val1,val2);       
									   } 
					                }
					            }
				        	}
				        if(resultArray=="[]")
						{
				        	jqueryAlertMsg("Alert","Selected employee is not allocated to any department.");
						document.forms[0].allocatedDeptName.options[0]=new Option('No Department',"NA");
						$("#allocatedDeptName-input").val($("#allocatedDeptName option:first").text());
							return false;
						}
				    }			
				}
				xmlhttp.open("Post",url,true);
				xmlhttp.send(null);

			}
 
}

function fn_getEmployeePositions()
{
	 var radioValue1="";	
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="employeeRow")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					 radioValue1=document.forms[0].elements[i].value;							
				}
			
			}
		
		}
	 var xmlhttp;
		var url;
		var allocDeptId=document.getElementById('allocatedDeptName').value;
		 if(allocDeptId!="NA"){
			    document.getElementById("empPos1").style.visibility="visible";
				document.getElementById("empPos2").style.visibility="visible";
				document.getElementById("allocRepPerson1").style.visibility="hidden";
				document.getElementById("allocRepPerson2").style.visibility="hidden";
				document.getElementById("unAllocateTR").style.display="none";
		url= 'createEmployee.do?actionFlag=getAllocPosDetails&selectedRadio='+radioValue1+'&allocDeptId='+allocDeptId;
		if (window.XMLHttpRequest){
		 xmlhttp=new XMLHttpRequest();
		}
		else if (window.ActiveXObject){		
		 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else{
		 alert("Your Browser Does Not Support XMLHTTP!");
		}
		xmlhttp.onreadystatechange=function(){
		    if(xmlhttp.readyState==4){	
		    	document.forms[0].empPosName.options[0]=new Option('-------- Select --------',"");
		        var resultArray=xmlhttp.responseText;
		        if(resultArray!= null){
		        	 resultArray2 = resultArray.replace("[","");
			         resultArray2 = resultArray2.replace("]","");            
			         var addList = resultArray2.split(", @");
			         if(addList.length>0){   
			                for(var i = 0; i<addList.length;i++){	
			                    var arr=addList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null){
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                     	 document.forms[0].empPosName.options[i+1] =new Option(val1,val2);       
							   } 
			                }
			            }
		        	}
		        if(resultArray=="[]")
				{
				
				document.forms[0].empPosName.options[0]=new Option('No Employee Position',"NA");
				}
		    }			
		}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);

	 }

}
function fn_getAllocRepPersons()
{
	var xmlhttp;
	var url;
	var deptId=document.getElementById('allocatedDeptName').value;
	var empPosName=document.getElementById('empPosName').value;
	document.getElementById("allocRepPerson1").style.visibility="visible";
	document.getElementById("allocRepPerson2").style.visibility="visible";
	document.getElementById("unAllocateTR").style.display="";
	url= 'createEmployee.do?actionFlag=getAllocRepPersons&dept='+deptId+'&empPosName='+empPosName;
	if (window.XMLHttpRequest){
		 xmlhttp=new XMLHttpRequest();
		}
		else if (window.ActiveXObject){		
		 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		else{
		 alert("Your Browser Does Not Support XMLHTTP!");
		}

	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){
		    
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");  
	        	document.getElementById('allocRepPerson').value=resultArray2;
	        	
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

}
function cancelEmployee()
{
	document.getElementById("vacPosTable").style.display="none";
	document.getElementById("vacPosTable1").style.display="none";
	 var radioValue="";
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="employeeRow")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					document.forms[0].elements[i].checked=false;
					
				}
			
			}
		
		}
	
}
function allocateEmployee()
{
	
	var unAllocateFlag="NO";
	 var fr=partial(allocateFinal,unAllocateFlag);
     jqueryConfirmMsg("Alert","Are you sure you want to allocate selected employee?" ,fr);
}

function unAllocateEmployee()
{
	var unAllocateFlag="YES";
	 var fr=partial(allocateFinal,unAllocateFlag);
     jqueryConfirmMsg("Alert","Are you sure you want to unallocate selected employee?" ,fr);
}
function allocateFinal(unAllocateFlag)
{

	var cntTemp=0;
	 var radioValue="";
	
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="employeeRow")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					cntTemp=cntTemp+1;
					 radioValue=document.forms[0].elements[i].value;
				}
			
			}
		
		}

	 if(cntTemp!=0){
		 document.forms[0].action = 'createEmployee.do?actionFlag=employeeAllocation&details='+radioValue+'&unAllocateFlag='+unAllocateFlag;
		document.forms[0].submit();
		 document.forms[0].allocate.disabled = true;
		
	 }
}
function fn_showAlert()
{
	 var successflag="${success}";
	 if(successflag=="allocate")
		{
	    var fr=partial(resetPage);
		jqueryAlertMsg("Alert","Employee Allocated Successfully ",fr);
		}
	 if(successflag=="unAllocate")
		{
	    var fr=partial(resetPage);
		jqueryAlertMsg("Alert","Employee unallocated Successfully ",fr);
		}

}

function delSearch()
{
	$('#empployeesTable').empty();
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
function fn_pagination(pageNo,rowsPerPage) 
{

	var empNumber=document.getElementById('empNumber').value;
	var loginName=document.getElementById('loginName').value;
	 var radioValue1="";	
	 for (var i=0; i<document.forms[0].elements.length; i++)
		{	
			var type = document.forms[0].elements[i].type;
		    var id=document.forms[0].elements[i].id;
			if (type=="radio" && id=="allocationFlag")
			{
				
				if(document.forms[0].elements[i].checked)
				{
					 radioValue1=document.forms[0].elements[i].value;							
				}
			
			}
		
		}

	if(empNumber=="" && loginName=="")
	{
	var fr=partial(focusBox,document.getElementById('empNumber'));
	jqueryAlertMsg("Alert","Please enter Employee Number or Login name",fr);
	return false;
	}
	if(document.forms[0].allocationFlag[0].checked==false && document.forms[0].allocationFlag[1].checked==false  )
	{
	 var fr=partial(focusBox,document.getElementById('allocationFlag'));
	jqueryAlertMsg("Alert","Please Select the action whether to Allocate/Unallocate employee",fr);
	return false;
	}	
  else{
	  
	document.forms[0].action = 'createEmployee.do?actionFlag=loadEmpForAllocation&empNumber='+empNumber+'&loginName='+loginName+'&allocationFlag='+radioValue1+'&pageNo='+pageNo+'&rowsPerPage'+rowsPerPage;
	document.forms[0].submit();
	document.forms[0].Search.disabled = true;}
}
</script>
</head>

<body onload="fn_showAlert()">
<form action="/createEmployee.do" method="POST" enctype="multipart/form-data"  id="createEmployeeForm">
<br>

                                   <div class="row tbheader"><b>Allocate Employee</b></div>
	<div class="container-fluid center-block">
		
                      <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
					       Employee Number:
					       <html:text property="empNumber" name="createEmployeeForm" styleId="empNumber" styleClass="form-control" title="Enter Employee Number" onchange="validateUserNo('empNumber')">
						</html:text>
						</div>
						 <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
					     Login Name:
					       <html:text property="loginName" name="createEmployeeForm" styleId="loginName" styleClass="form-control" title="Enter Employee Number" onchange="validateLoginName('loginName')">
						</html:text>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Action:
						<br>
						 <html:radio title="Select action" name="createEmployeeForm" property = "allocationFlag"  styleId="allocationFlag"  value = "A" onclick="cancelEmployee();delSearch()"/>Allocate &nbsp;&nbsp;
						<html:radio title="Select action" name="createEmployeeForm" property = "allocationFlag"  styleId="allocationFlag"  value ="U" onclick="cancelEmployee();delSearch()"/>Unallocate
					  </div>
					  </div>
						  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center">
                                 <button class="btn but"   id="search" name="search" tabindex="0" type="button"  title="Search"   onClick="loadEmployee();">Search</button>
                                  <button class="btn but"   id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetPage();">Reset</button>   
                     </div>
                      <br>
                      
<logic:present name="createEmployeeForm"  property="empList">
<bean:size id="size" name="createEmployeeForm" property="empList"/>
<logic:greaterThan name="size" value="0">
<div class=" col-sm-12 col-xs-12 col-lg-12 col-md-12">
							<div class="leftone col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<ul class="pagination">
								<li class="lispacing"><b>Showing Results</b>&nbsp;</li>
								<li class="lispacing"><b><c:out value="${startIndex}" /></b>  - <b><c:out value="${endIndex}" /></b> 
								of  <b><c:out value="${totalRecords}" /></b></li>
							</ul>
						</div>
						<div class="centerone col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<ul class="pagination"> 
							<li><b>Show Page</b></li>
 							<logic:notEmpty name="createEmployeeForm" property="prev"> 
							<li><span class="glyphicon glyphicon-backward" onclick="javascript:showPagination('prev')"></span></li>
							</logic:notEmpty>
							<c:set var="startPage1" scope="session" value="${startPage}"/>
							<c:set var="endPage1" scope="session" value="${endPage}"/>
							<c:set var="pages1" scope="session" value="${pages}"/>
							<c:if test="${pages>=startPage1}">

							<c:forEach var="page" begin="${startPage1}" end="${endPage1}">
   							<c:if test="${liPageNo eq page}" >
								<li class="active"><span><c:out value="${page}" /></span></li> 
							</c:if>
							<c:if test="${liPageNo ne page}">
							<c:if test="${page le pages1}" >
							<li><a href="javascript:showPagination('<c:out value="${page}" />')"> <c:out value="${page}" /></a></li>
							</c:if>
							</c:if> 
 
							</c:forEach>

							<logic:notEmpty name="createEmployeeForm" property="next">
							<li><span class="glyphicon glyphicon-forward" onclick="javascript:showPagination('next') "></span></li>
							</logic:notEmpty> 
							</c:if>
							</ul>
						</div>
						<div class="rightone visible-md visibel-lg col-lg-4 col-md-4 col-sm-12 col-xs-12">
							<ul class="pagination">
								<li>&nbsp;<b>Show in sets of</b>&nbsp;</li>
								<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
								<c:forEach var="set" items="${ListNoSet}"  >
								<c:if test="${rowsPerPage eq set }" >
									<li class="active"><span><c:out value="${set}" /></span></li> 
								</c:if>
								<c:if test="${rowsPerPage ne set }" >
									<li ><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
								</c:if>
								</c:forEach>			
								</ul>
						</div>
</div>
</logic:greaterThan>
</logic:present>
<br><br><br><br><br>

<logic:present name="createEmployeeForm"  property="empList">
<bean:size id="size" name="createEmployeeForm" property="empList"/>
<logic:equal name="size" value="0">
							<table align="center" width="100%">
								<tr align="center">
									<td align="center" class="tbcellBorder" width="100%">
										<center><b>No Records found</b></center>
									</td>
								</tr>
							</table>
						</logic:equal>		
					</logic:present>
              <logic:present name="createEmployeeForm" property="empList">
				<bean:size id="size" name="createEmployeeForm" property="empList" />
					<logic:greaterThan name="size" value="0">
						<table class="table-bordered col-lg-12 col-md-12 col-sm-12 col-xs-12 table b-t text-small"  style="padding-left:1em; font-size:13px;" border="0" >

								<tr >
									<th class="tbheader1" style="width:40px; height: 30px; ">S.No</th>
									<th class="tbheader1" style="width:280px; height: 30px;">Employee Name</th>
									<th class="tbheader1" style="width:250px; height: 30px;">Login Name</th>
									<th class="tbheader1" style="width:200px; height: 30px;">In Service</th>								
									<th class="tbheader1" style="width:50px; height: 30px;">Select</th>
									</tr>
									
         
         <logic:iterate id="list" name="createEmployeeForm"  property="empList" indexId="index">
         <tr>
         <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="list"  property="index"/></td>
         <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="list" property="fName" /></td>
         <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="list" property="loginName" /></td>
         <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><bean:write name="list" property="serviceFlg" /></td>
         <td  class="tbcellBorder" style="word-wrap:break-word;padding:3px;"><input type="radio"  title="Select Employee for allocation" onclick="fn_getVacantPositions()" name="employeeRow" id="employeeRow" value='${list.fName}~${list.loginName}~${list.serviceFlg}~${list.userNo}' /></td> 
          
             </tr>   
             </logic:iterate>
</table>
</logic:greaterThan>
</logic:present>
	
<br/>
	<br/>
	 

		<table id="vacPosTable" width="90%" cellpadding="0" cellspacing="0" style="padding-top:0px;margin:0px auto;display:none" class="tb" >
                  <tr class="HeaderRow">
                                  <td width="100%" class="tbheader" colspan="8"  align="center"><b>Available Vacant Positions</b></td>
				    	</tr>	
				    	
					    <tr>
						<td><br></td>
						</tr>
						<tr class="NormalRow">
						
						 <td style="padding-left:5px" class="labelheading1 tbcellCss" width="20%">Department: <span style="color: #ff0000"> *</span></td>
						  <td class="tbcellBorder" width="15%"><html:select  property="deptName" name="createEmployeeForm" styleId="deptName" onmouseover="addTooltip('deptName');" title="Select Department" onchange="fn_getVacantPosition()">
						<option value="">--------Select---------</option>
						 <html:options collection="deptNamesList" labelProperty="value" property="id"/>
						</html:select></td> 
						
						
					    <td id="vacPos1" style="padding-left:5px;visibility:hidden" width="20%" class="labelheading1 tbcellCss">Vacant Position: <span style="color: #ff0000"> *</span></td>
						 <td id="vacPos2" class="tbcellBorder" width="15%" style="visibility:hidden"><html:select  property="vacPosName" name="createEmployeeForm" styleId="vacPosName" onmouseover="addTooltip('vacPosName');"  onchange="fn_getVacantPositionDetails()" title="Select Vacant Position">
						<option value="">--------Select---------</option>
						 <html:options collection="vacNamesList" labelProperty="VALUE" property="ID"/>
						</html:select></td> 
						
						 <td id="level1" style="padding-left:5px;visibility:hidden" width="15%" class="labelheading1 tbcellCss">Level:</td>
						<td id="level2" class="tbcellBorder" width="20%" style="visibility:hidden">
						<html:text style="width: 13em;" property="level" maxlength="10" title="Level" styleId="level" name="createEmployeeForm" readonly="true"/>		 
					    </td>
						
						</tr>
						<tr class="NormalRow" id="vacPosTR" style="display:none;">
						
						
						 <td style="padding-left:5px" width="20%" class="labelheading1 tbcellCss">Designation:</td>
						<td class="tbcellBorder" width="15%">
						<html:text style="width: 13em;" property="desgName" maxlength="10" title="Designation Name" styleId="desgName" name="createEmployeeForm" readonly="true"/>		 
					    </td>
					    
					     <td style="padding-left:5px" width="15%" class="labelheading1 tbcellCss">Reporting Person:</td>
						<td class="tbcellBorder" width="15%">
						<html:text style="width: 13em;" property="repPerson" maxlength="10" title="Reporting Person" styleId="repPerson" name="createEmployeeForm" readonly="true"/>		 
					    </td>
					    
					     <td style="padding-left:5px" width="15%" class="labelheading1 tbcellCss">Group Name:</td>
						<td class="tbcellBorder" width="20%">
						<html:textarea style="width: 13em;" property="groupName" rows="3" title="Group Name" styleId="groupName" name="createEmployeeForm" readonly="true"/>		 
					    </td>
							 
                       </tr>
                       	<tr>
						<td><br></td>
						</tr>
                       <tr class="NormalRow" id="allocateTR" style="display:none">
						 <td colspan="8" style="text-align:center">
						 <button class="but" id="allocate" name="allocate" tabindex="0" type="button"  title="Allocate Employee"   onClick="allocateEmployee();">Allocate</button>
                                   <button class="but"  id="Cancel" name="reset" tabindex="0" type="reset"  title="Cancel"   onClick="cancelEmployee();">Cancel</button> 
					
						</td>
						</tr>
					</table>
		<table id="vacPosTable1" width="90%" cellpadding="0" cellspacing="0" style="padding-top:0px;margin:0px auto;display:none" class="tb" >
		
                  <tr class="HeaderRow">
                                  <td width="100%" class="tbheader" colspan="8"  align="center"><b>Employee Allocation Details</b></td>
				    	</tr>	
				    	<tr>
						<td><br></td>
						</tr>	
						<tr class="NormalRow" id="unallocateRow">
						
						 <td style="padding-left:5px" class="labelheading1 tbcellCss" width="20%">Department: <span style="color: #ff0000"> *</span></td>
						  <td class="tbcellBorder" width="15%">
						  <html:select  property="allocatedDeptName" name="createEmployeeForm" styleId="allocatedDeptName" onmouseover="addTooltip('allocatedDeptName');" title="Select Department" onchange="fn_getEmployeePositions()">
						  <option value="">--------Select---------</option>
						  <html:options collection="allocatedDeptsList" labelProperty="value" property="id"/>
						  </html:select></td> 
					    <td id="empPos1" style="padding-left:5px;visibility:hidden" width="15%" class="labelheading1 tbcellCss">Employee Position:</td>
						 <td id="empPos2" class="tbcellBorder" width="15%" style="visibility:hidden;">
						 <html:select  property="empPosName" name="createEmployeeForm" styleId="empPosName" onmouseover="addTooltip('empPosName');" title="Select Position of employee" onchange="fn_getAllocRepPersons()">
						  <option value="">--------Select---------</option>
						  <html:options collection="empPosNamesList" labelProperty="value" property="id"/>
						  </html:select>
						 </td> 
						
						 <td id="allocRepPerson1" style="padding-left:5px;visibility:hidden" width="15%" class="labelheading1 tbcellCss" >Reporting Person:</td>
						<td id="allocRepPerson2" class="tbcellBorder" width="20%" style="visibility:hidden;">
						<html:text style="width: 13em;" property="allocRepPerson"  title="Reporting Person" styleId="allocRepPerson" name="createEmployeeForm" readonly="true"/>		 
					    </td>
						
						</tr>
						
                       	<tr>
						<td><br></td>
						</tr>
                       <tr class="NormalRow" id="unAllocateTR" style="display:none">
						 <td colspan="8" style="text-align:center">
						 <button class="but" id="unAllocate" name="unAllocate" tabindex="0" type="button"  title="Unallocate Employee"   onClick="unAllocateEmployee();">Unallocate</button>
                                   <button class="but"  id="Cancel" name="reset" tabindex="0" type="reset"  title="Cancel"   onClick="cancelEmployee();">Cancel</button> 
					
						</td>
						</tr>
					</table>
					<div class="container-fluid center-block">
 <c:if test="${records eq 0}">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1" align="center"><br/>
<label><h4>No records found...</h4></label>
</div>
</c:if></div> 
					<html:hidden  name="createEmployeeForm"  property="empNumber" styleId="empNumber" />
 <html:hidden  name="createEmployeeForm"  property="loginName" styleId="loginName" />
 <html:hidden  name="createEmployeeForm"  property="allocationFlag" styleId="allocationFlag" />
 
 
 
 <html:hidden property="showPage" name="createEmployeeForm" />
		<html:hidden property="startPage" name="createEmployeeForm" value="${startPage}"/>
		<html:hidden property="endPage" name="createEmployeeForm" value="${endPage}"/>
		<html:hidden property="rowsPerPage" name="createEmployeeForm" />

</form>

</body>
</html>