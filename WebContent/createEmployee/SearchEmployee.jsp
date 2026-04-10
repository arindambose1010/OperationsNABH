
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
<title>Search Employee Details</title>
<style>
 body{font-size:1.2em !important;} 
 ..pagination {display:none;} .bottom-margin{margin:0px 0px 3px 0px !important;} .marginTop{margin-top:3px} .but{border-radius:16px;}
 
.but{
border-radius:16px;
}
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
.ui-autocomplete-input {
    width: 15em;
}
#iFrameIdEmpSearch{
 display: none;
 height: 600px; 
 overflow: hidden !important;
 width: 100%;
}
</style>
<script>$(document).ready(function() 
		{
	
	
	
	$('#CreateEmployeeForm').formValidation(
	{
		
		framework: 'bootstrap',
		icon: 
		{
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
		},
		 fields:{
			
		       /* empName: {
		            validators: {
		                notEmpty: {
		                    message: 'The  emp name is required'
		                },
		                regexp: {
		                    regexp: /^[a-zA-Z]+$/,
		                    message: 'The  emp name can only consist of alphabets '
		                }
		            }
		        }, */
		        
		            }
		        
		       	 
		});	
});


function addTooltip(id)
{
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;		

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

	 $('#CreateEmployeeForm').formValidation().formValidation('validate');
		var x=$('#CreateEmployeeForm').data('formValidation').isValid();
	scheme=document.getElementById('schemeType').value;
	deptName=document.getElementById('deptName').value;
	desgName=document.getElementById('desgName').value;
	loginName=document.getElementById('loginName').value;
	empName=document.getElementById('empName').value;
	groupName=document.getElementById('groupName').value;
	hospName=document.getElementById('hospName').value;
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
			
			var url;
			if(scheme==null || scheme==""){
				
				url='createEmployee.do?actionFlag=searchResultList&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName+'&hospName='+hospName;
			}
			else {
				
				url='createEmployee.do?actionFlag=searchResultList&deptName='+deptName+'&desgName='+desgName+'&loginName='+loginName+'&empName='+empName+'&status='+status +'&groupName='+groupName+'&hospName='+hospName+'&scheme='+scheme;
			}

	 if((hospName!="")&&(desgName==""))
		 {
		 jqueryAlertMsg("Alert","Please select designation to search employee of the hospital");
		  return;
		 }
	 
	 if((deptName=="")&&(desgName=="")&&(status=="")&&(loginName==null ||loginName=="")&&(empName==""||empName==null)&&(groupName=="")&&(scheme==""||scheme==null)){
		 jqueryAlertMsg("Alert","Please select any search criteria");
			  return;
		}
	 else
	 {
     document.getElementById("empSearchFrame").src="";
     document.getElementById("iFrameIdEmpSearch").style.display="block";
	 document.getElementById("empSearchFrame").src=url;
     document.getElementById("empSearchFrame").style.display="block"; 
	 
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
</script>
</head>

<body onload="fn_removeLoadingImage();fn_setOption()">
<br>

<html:form action="/createEmployee.do" method="POST" enctype="multipart/form-data" styleId="CreateEmployeeForm">


<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss">
                                   <div class="tbheader"><b>Search Employee Details</b></div>
                                   
</div>


					<div class="container-fluid center-block">
						<div class="row">
                        	<div class="col-lg-6 col-md-46col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								 Employee Id:
		                         <html:text property="loginName" name="createEmployeeForm" styleId="loginName" styleClass="form-control" title="Login Name of Employee">
								</html:text>
							</div>
								 <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						 Employee Name:
                         <html:text property="empName" name="createEmployeeForm" styleId="empName" styleClass="form-control" title="Name of the Employee">
						</html:text>
						</div>
				      	
					  </div>
				
						<div class="row">
				       		<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								 Designation:
		                         <html:select property="desgName" name="createEmployeeForm" styleId="desgName" styleClass="form-control" title="Select Designation">
								<option value="">--------Select---------</option>
								 <html:options collection="desgNamesList" labelProperty="value" property="id"/>
								</html:select>
							</div>
						<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						
						Status:
						<br>
						 <html:radio title="Active" name="createEmployeeForm" property = "status"  styleId="status"  value = "Y" onclick="cancelEmployee();delSearch()"/>Active &nbsp;&nbsp;
						<html:radio title="Inactive" name="createEmployeeForm" property = "status"  styleId="status"  value = "N" onclick="cancelEmployee();delSearch()"/>Inactive
					  </div>
		
			
					
						
						</div>
		
						</div>
						
                              
						</div> 
						<div></div>
                       <div align="center">
                                 <button class="btn but"   id="search" name="search" tabindex="0" type="button"  title="Search"   onClick="searchEmployee();">Search</button>
                                  <button class="btn but"   id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetPage();">Reset</button>   
                     </div>
            
				
				
					
	
 <div id="iFrameIdEmpSearch">
         <iframe width="100%" height="100%" 
        id="empSearchFrame"  
        name="empSearchFrame" frameborder="0" onload="fn_removeLoadingImage();">
        </iframe>
 </div>
 
 
  
 <html:hidden  name="createEmployeeForm"  property="schemeFlag" styleId="schemeFlag" />
 
 <div id="processImagetable" style="top:40%;z-index:50;position:absolute;left:45%">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src=images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
        
</html:form>
</body>
</html>