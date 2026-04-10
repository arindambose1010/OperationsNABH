<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create Employee</title>
<%@ include file="/common/include.jsp"%>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<style>
#iFrameIdCreateEmp{
 display: none;
 height: 700px; 
 overflow: hidden;
 width: 100%;
}
</style>
<script>

function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}

function fn_removeLoadingImage()
 {   
   document.getElementById('processImagetable').style.display="none";
 }

function fn_commonMethod()
{
	 fn_loadImage();
     document.getElementById("empCreateFrame").src="";
     document.getElementById("iFrameIdCreateEmp").style.display="block";
     document.getElementById("empCreateFrame").style.display="block"; 

}
function fn_loadSearchEmp()
{
	fn_commonMethod();
	 document.getElementById("empCreateFrame").src='/<%=context%>/createEmployee.do?actionFlag=searchEmployees';
    
}
 function fn_getVacantPosition()
 {
	 fn_commonMethod();
	 document.getElementById("empCreateFrame").src='/<%=context%>/createEmployee.do?actionFlag=vacantPosition';
}

function fn_createEmployee()
{
	fn_commonMethod();
	 document.getElementById("empCreateFrame").src='/<%=context%>/createEmployee.do?actionFlag=employeeCreation'; 
}

function fn_updateEmployee()
{
	fn_commonMethod();
	 document.getElementById("empCreateFrame").src='/<%=context%>/createEmployee.do?actionFlag=updateEmployee';
}

function fn_allocateEmployee()
{
	fn_commonMethod();
	 document.getElementById("empCreateFrame").src='/<%=context%>/createEmployee.do?actionFlag=allocateEmployee'; 

}

</script>
</head>
<body onload="fn_removeLoadingImage();">
<form action="/createEmployee.do" method="POST" enctype="multipart/form-data" name="createEmployeeForm">
<table align="center" width="100%" cellpadding="0" cellspacing="0" class="tb" style="padding-top:0px;margin:0px auto;">
<tr class="tbheader"><th><b>Create Employee</b></th></tr>
</table> 
<table align="center"  width="98%" height="10%" cellpadding="0" cellspacing="0" class="tb" style="padding-top:0px;margin:0px auto;">
        <tr><td class="tbcellBorder" align="center" style="width:10em;"><a href="#" onclick="fn_loadSearchEmp()">Search Employee</a></td>
        <td class="tbcellBorder" align="center" style="width:10em;"> <a href="#" onclick="fn_getVacantPosition()">New Vacant Position</a></td>
        <td class="tbcellBorder" align="center" style="width:10em;"><a href="#" onclick="fn_createEmployee()">Create Employee</a></td>
        <td class="tbcellBorder" align="center" style="width:10em;"><a href="#" onclick="fn_updateEmployee()">Update Employee</a></td>
       <td class="tbcellBorder" align="center" style="width:10em;"><a href="#" onclick="fn_allocateEmployee()">Allocate/Unallocate Employee</a></td>  
        </tr>
        </table>
        
        
        
 <div id="iFrameIdCreateEmp">
         <iframe width="100%" height="100%" 
        id="empCreateFrame" scrolling="no" 
        name="empCreateFrame" frameborder="0" onload="fn_removeLoadingImage();">
        </iframe>
 </div>
 
 
 
 
 <div id="processImagetable" style="top:20%;z-index:50;position:absolute;left:45%">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="/<%=context%>/images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
 
    </form>    
</body>
</html>