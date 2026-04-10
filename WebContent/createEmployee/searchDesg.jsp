<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/includeCalendar.jsp"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Employee Designation Details</title>
<%@ include file="/common/include.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css"
	rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">
function partial(func /*, 0..n args */) 
{
	var args = new Array();
	for ( var i = 1; i < arguments.length; i++) 
	{
		args.push(arguments[i]);
	}
	return function() 
	{
		var allArguments = args.concat(Array.prototype.slice.call(arguments));
		return func.apply(this, allArguments);
	};
}
function focusBox(arg) 
	{
		aField = arg;
		setTimeout("aField.focus()", 0);
	}
function popitup(url) 
	{
		newwindow = window.open(url,'EditDesignationDetails','width=1000, height=400, top=10,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');
		if (window.focus) {
			newwindow.focus();
		}
		return false;
	}
function editDesgDetails(designationId) 
	{
		popitup("createEmployee.do?actionFlag=editDesgDtls&designationId="
				+ designationId);
	}
function validateId(arg)
{
	 var value = document.getElementById(arg).value;
	 var dot = /^[A-Z a-z 0-9]+$/;
		if (value != "") {
			if (!value.match(dot)) {
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsgTop("Alert", "Please Enter Valid Designation Id", fr);
				document.getElementById(arg).value = "";
				return;
			} 
		}
}
function validateAlphabetic(arg) 
	{
		var value = document.getElementById(arg).value;
		var pattern1 = /^[A-Z a-z _.-]+$/;
		var pattern2 = /^[?:.!@#$%^&*<>()~|+=, 0-9]+$/;
		if (value != "") 
		{
			if (!value.match(pattern1)) 
			{
				if (arg == "desgName")
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert", "Please Enter Valid Designation Name",fr);
				document.getElementById(arg).value = "";
				return false;
			}
			if (value.match(pattern2)) 
			{
				if (arg == "desgName")
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert", "Please enter valid designation name",fr);
				document.getElementById(arg).value = "";
				//document.getElementById(arg).focus();
				return false;
			}
			if (value.charCodeAt(0) == 32) 
			{
				if (arg == "desgName")
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert","Designation  name cannot start with space", fr);
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
		if (value != "") {
			if (!value.match(pattern1)) {
				if (arg == "desgSname")
					var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert",
						"Please Enter Valid Designation Short Name", fr);
				document.getElementById(arg).value = "";
				return false;
			}
			if (value.match(pattern2)) {
				if (arg == "desgSname")
					var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert",
						"Please enter valid designation short name", fr);
				document.getElementById(arg).value = "";
				//document.getElementById(arg).focus();
				return false;
			}
			if (value.charCodeAt(0) == 32) {
				if (arg == "desgSname")
					var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert",
						"Designation short  name cannot start with space", fr);
				document.getElementById(arg).value = "";
				//document.getElementById(arg).focus();
				return false;
			}
		}
		return true;
	}
function fn_validate() 
{
		if (document.getElementById('desgId').value == "") 
		{
			alert("Please enter designation Id");
			return false;
		}
		if (document.getElementById('desgName').value == "") {
			alert("Please enter designation name");
			return false;
		}
		return true;
	}
function searchPage() 
{
		var designationId = document.getElementById('desgId').value;
		var designationName = document.getElementById('desgName').value;
		var designationSname = document.getElementById('desgSname').value;
		if (designationId == "" && designationName == "" && designationSname == "") 
		{
			var fr = partial(focusBox, document.getElementById('bankName'));
			jqueryAlertMsg("Alert", "Please select any of the search criteria",	fr);
			return false;
		} 
		else 
		{
			document.forms[0].action = 'createEmployee.do?actionFlag=searchDsgnById&designationId='+ designationId + '&designationName=' + designationName + '&designationSname=' + designationSname;
			document.forms[0].submit();
			document.forms[0].Search.disabled = true;
		}
}
function resetPage() 
{
	document.getElementById('desgName').value = "";
	document.getElementById("desgId").value = "";
	document.getElementById("desgSname").value = "";
}
</script>
</head>
<body>
	<form action="/createEmployee.do" name="createEmployeeForm" method="post">
		<table align="center" width="90%" cellpadding="0" cellspacing="0"	class="tb" style="padding-top: 0px; margin: 0px auto;">
			<tr class="HeaderRow">
				<td width="100%" class="tbheader" colspan="6" align="left"><b>Search Employee Designation Details</b></td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr class="NormalRow">
				<td class="labelheading1 tbcellCss" style="padding-left: 5px">Designation ID</td>
				<td class="tbcellBorder"><html:text property="designationId" style=" width :200px;" styleId="desgId" onchange="validateId('desgId')" name="createEmployeeForm" /></td>
				<td class="labelheading1 tbcellCss" style="padding-left: 5px">Designation Name</td>
				<td class="tbcellBorder"><html:text property="designationName"style=" width :200px;" styleId="desgName" onchange="validateAlphabetic('desgName')" name="createEmployeeForm"></html:text></td>
				<td class="labelheading1 tbcellCss" style="padding-left: 5px">Designation short Name</td>
				<td class="tbcellBorder"><html:text property="designationSname" style=" width :200px;" styleId="desgSname" onchange="validateAlpha('desgSname')" name="createEmployeeForm"></html:text></td>
			</tr>
			<tr class="NormalRow">
				<td colspan="6" style="text-align: center"><input type="button" class="but" name="Search" onclick="searchPage()" value="Search" />&nbsp; 
				<input type="button" class="but" name="reset" onclick="resetPage()" value="Reset" /></td>
			</tr>
		</table>
		<br>
		<table style="padding-top: 0px; margin: 0px auto;">
			<tr>
				<td><logic:present name="createEmployeeForm" property="searchDesgList">
						<bean:size id="size" name="createEmployeeForm" property="searchDesgList" />
						<logic:greaterThan name="size" value="0">
							<div id="designationTable">
								<%
									int i = 1;
								%>
								<display:table name="createEmployeeForm.searchDesgList"
									id="rowId" requestURI="/createEmployee.do" pagesize="10"
									export="false">
									<display:caption>
										<display:setProperty name="paging.banner.some_items_found">{0} Designation Details found, displaying {2} to {3}</display:setProperty>
										<display:setProperty name="export.export name.include_header">true</display:setProperty>
										<display:setProperty name="basic.show.header">false</display:setProperty>
										<thead>
											<tr>
												<th class="tbheader1" style="width: 40px; height: 20px;">S.No</th>
												<th class="tbheader1" style="width: 200px; height: 20px;">Designation ID</th>
												<th class="tbheader1" style="width: 200px; height: 20px;">Designation Name</th>
												<th class="tbheader1" style="width: 100px; height: 20px;">Designation Short Name</th>
												<th class="tbheader1" style="width: 100px; height: 20px;">Designation Status</th>
												<th class="tbheader1" style="width: 40px; height: 20px;">Edit</th>
											</tr>
										</thead>
									</display:caption>
									<display:column class="tbcellBorder" value="<%=i++ %>" title="S.No" style="text-align:center;" />
									<display:column class="tbcellBorder" title="Designation ID" property="designationId" style="text-align:center;" />
									<display:column class="tbcellBorder" title="Designation Name" property="designationName" style="text-align:center;" />
									<display:column class="tbcellBorder" title="Designation Short Name" property="designationSname" style="text-align:center;" />
									<display:column class="tbcellBorder" title="Designation Status" property="desgStatus" style="text-align:center;" />
									<display:column class="tbcellBorder"
										title="Edit Designation Details" style="text-align:center;">
										<a
											href="javascript:editDesgDetails('${rowId.designationId}');"
											title="Click to Edit Designation Details"> <img
											src="/<%=context%>/images/current.png"></img>
										</a>
									</display:column>
								</display:table>
							</div>
						</logic:greaterThan>
					</logic:present>
				</td>				
			</tr>
		</table>
			
		<logic:empty name="createEmployeeForm"  property="searchDesgList">
			<table width="90%" cellpadding="1" cellspacing="1" align="center"
				style="padding-top: 0px; margin: 1px auto;">
				<tr>
					<td align="center" height="50" class="tbcellBorder"><b>No
							Records Found</b></td>
				</tr>
			</table>
		</logic:empty>
	</form>
</body>
</html>