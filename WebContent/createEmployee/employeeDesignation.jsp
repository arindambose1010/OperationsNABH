<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/common/include.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Add New Designation </title>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript" >

function partial(func /*, 0..n args */) 
	{
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
function focusBox(arg) 
	{
	aField = arg;
	setTimeout("aField.focus()", 0);
	}
function validateAlphabetic(arg) 
	{
		var value = document.getElementById(arg).value;
		var pattern1 = /^[A-Z a-z _.-]+$/;
		var pattern2 = /^[?:.!@#$%^&*<>()~|+=,-_ 0-9]+$/;
		if (value != "")
		{ 
			if(!value.match(pattern1))
				{
					if(arg=="desgName")
					var fr = partial(focusBox,document.getElementById(arg));
					jqueryAlertMsg("Alert","Please Enter Valid Designation Name",fr);
					document.getElementById(arg).value="";
					return false;
				}
			if(value.match(pattern2))
		 	{
				if(arg=="desgName")
				var fr = partial(focusBox, document.getElementById(arg));					
				jqueryAlertMsg("Alert","Please enter valid designation name",fr);
	         	document.getElementById(arg).value="";	
	         	//document.getElementById(arg).focus();
	        	 return false; 
	     	}	
			if (value.charCodeAt(0) == 32) 
			 {				 
				 	if(arg=="desgName")
				 	var fr = partial(focusBox, document.getElementById(arg));
				 	jqueryAlertMsg("Alert","Designation  name cannot start with space",fr);
					document.getElementById(arg).value = "";
					//document.getElementById(arg).focus();
					return false;
			} 
		}
		return true;
	}			
function validateAlpha(arg) 
	{
	var value = document.getElementById(arg).value;
	var pattern1 = /^[A-Z a-z _.-]+$/;
	var pattern2 = /^[?:.!@#$%^&*<>()~|+=, 0-9]+$/;
	if (value != "")
	{ 
		if(!value.match(pattern1))
		{
			if(arg=="desgSname")
			var fr = partial(focusBox,document.getElementById(arg));
			jqueryAlertMsg("Alert","Please Enter Valid Designation short Name",fr);
			document.getElementById(arg).value="";
			return false;
		}
		if(value.match(pattern2))
	 	{
			if(arg=="desgSname")
			var fr = partial(focusBox, document.getElementById(arg));					
			jqueryAlertMsg("Alert","Please enter valid designation short name",fr);
         	document.getElementById(arg).value="";	
         	//document.getElementById(arg).focus();
        	 return false; 
     	}	
		if (value.charCodeAt(0) == 32) 
		 {	 
			 	if(arg=="desgSname")
			 	var fr = partial(focusBox, document.getElementById(arg));
			 	jqueryAlertMsg("Alert","Designation short  name cannot start with space",fr);
				document.getElementById(arg).value = "";
				//document.getElementById(arg).focus();
				return false;
		} 
	}
	return true;
	}
function fn_validate()
	{
		if(document.getElementById('desgName').value== "")
			{ 
				alert("please enter designation  name");
			  	return false;
			}	
		if(document.getElementById('desgSname').value== "")
	     	{
				alert("please enter designation short  name");
	  			return false;
		 	}	
		return true;
	}
function successFunction()
	{
		if("${saveFlag}"=="success")
			{
				if("${editDesgFlag}"=="NO")
				{
			 		alert("Data Saved successfully");
			 		resetPage();
				}		    
			else if("${editDesgFlag}"=="YES")
				{
			 		alert("Data Updated successfully");
	         		window.onunload = refreshParent;
				}
		 window.close();		 
	}
		if("${saveFlag}"=="failure")
		{
			if("${editDesgFlag}"=="NO")
				alert("Data not saved due to some error");
			else if("${editDesgFlag}"=="YES")
				alert("Data not updated due to some error");
			 window.close();
		}		
	}
function refreshParent() 
	{
    	window.opener.location.reload();
	}
function savePage()
	{
		var flag=fn_validate();
		var url="";
    	if(flag==true)
    	{
    		url = '/<%=context%>/createEmployee.do?actionFlag=saveDesgDetails&updateFlag=false';
    	//alert(flag);
    	 	var fr=partial(saveDesg,url);
    	 	jqueryConfirmMsg("Confirm","Are you sure you want to save designation details",fr);
    	}
	}
function UpdatePage()
	{
	var flag=fn_validate();
	var url="";
	//alert("ghgf");
    if(flag==true)
    	{
    		url = '/<%=context%>/createEmployee.do?actionFlag=saveDesgDetails&updateFlag=true';
    		var fr=partial(updateDesg,url);
    		jqueryConfirmMsg("Confirm","Are you sure you want to update the details",fr);
	 		 	
    	}	 
}
function saveDesg(url)
	{
	 document.forms[0].action = url;
	 document.forms[0].submit();
	 document.forms[0].save.disabled = true;
	}
function updateDesg(url)
	{
	 document.forms[0].action = url;
	 document.forms[0].submit();
	 document.forms[0].update.disabled = true;
	}
function resetPage()
	{
	    document.getElementById('desgName').value="";
	    document.getElementById("desgSname").value="";
	    document.forms[0].activate[0].checked=false;
		document.forms[0].activate[1].checked=false;
	}
function goToClose()
	{
  window.close();
	}
</script>
</head>
	<body onload="successFunction()">
		<form action="/createEmployee.do" name="createEmployeeForm" method="post">
			<table  align="center"  width="90%" cellpadding="0" cellspacing="0" class="tb" style="padding-top:0px;margin:0px auto;">
				<tr class="HeaderRow">
                      <td width="100%" class="tbheader" colspan="6"  align="left"> <b>Add New Designation</b></td>
				</tr>	
				<tr> <td><br></td> </tr>
				<tr class="NormalRow">
					<td class="labelheading1 tbcellCss"  style="padding-left:5px"> Designation ID  </td>
					<td class="tbcellBorder" >
					<html:text property="designationId" readonly="true" style=" width :200px;" styleId="desgId"  name="createEmployeeForm"/></td>
				</tr>
				<tr class="NormalRow">
					<td class="labelheading1 tbcellCss" style="padding-left:5px"> Designation Name  </td>
					<td class="tbcellBorder" >
					<html:text property="designationName" style=" width :200px;" styleId="desgName" onchange="validateAlphabetic('desgName')" name="createEmployeeForm"></html:text>
					</td>
				</tr>
				<tr class="NormalRow">
					<td class="labelheading1 tbcellCss" style="padding-left:5px" > Designation Short Name  </td>
					<td class="tbcellBorder" >
					<html:text property="designationSname" name="createEmployeeForm" style=" width :200px;" styleId="desgSname" onchange="validateAlpha('desgSname')"  />
					</td>
				</tr>
				<tr class="NormalRow">
					<td style="padding-left:5px" class="labelheading1 tbcellCss"> Designation Status </td>
					<td class="tbcellBorder" width="30%">
					<html:radio  name="createEmployeeForm" property = "desgStatus"  styleId="activate"  value = "A" />Activate &nbsp;&nbsp;
					<html:radio  name="createEmployeeForm" property = "desgStatus"  styleId="activate"  value = "I" />Deactivate
				</td>			
				</tr>
				<c:if test="${editDesgFlag eq 'NO' }">	
				<tr class="NormalRow">
					<td colspan="6" style="text-align:center">
					<input type="button" class="but" name="Save" onclick="savePage()" value="Save"/>
				&nbsp;
					<input type="button" class="but" name="reset" onclick="resetPage()" value="Reset"/>
					</td>
				</tr>
				</c:if>
				<c:if test="${editDesgFlag eq 'YES' }">
				<tr class="NormalRow">
					<td colspan="6" style="text-align:center">
					<input type="button" class="but" name="Save"  onclick="UpdatePage()" value="Update"/>
				&nbsp;
					<input type="button" class="but" name="reset" onclick="resetPage()" value="Reset"/>
				&nbsp;
					<input type="button" class="but" name="Close" onClick="goToClose()" value="Close"/>
					</td>
				</tr>
				</c:if>
			</table>
		</form>	
	</body>
</html>