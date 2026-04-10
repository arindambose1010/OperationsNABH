

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/include.jsp"%> 
<HTML>
<title>Labels Page</title>
	<HEAD>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 <script src="js/jquery-1.9.1.min.js"></script>
	 	 <LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
</HEAD>
<script type="text/javascript">

var jq = $.noConflict();
jq(document).ready(function(){
	
	var isSubmitted = "${isSubmitted}";
	var investigatnId = "${patientForm.investigationSel}";
	if(isSubmitted == 'true')
	{
		window.opener.$('#lbl'+investigatnId).css("color","#0000FF");
	}
	
});

function saveData(btnType)
{
	var patientId = window.opener.document.getElementById('patientNo').value;
	var investigtnId = "${patientForm.investigationSel}";	
	//alert(investigtnId);
	document.forms[0].action='patientDetails.do?actionFlag=saveLabReport&patientId='+patientId+'&investgtnId='+investigtnId+'&btnType='+btnType;
	document.forms[0].method="post";
	document.forms[0].submit();

}

function showMsg()
{
	var msg = "${msg}";
	if(msg != '' && msg != null)
	{
		if(msg)
		{
			alert("Data saved successfully");
			return false;
		}
		else
		{
			alert("Failed to save the data");
			return false;
		}
	}
}

</script>

<body onload="showMsg();" style = "width:90%;margin:auto;">
<html:form action="/patientDetails.do" method="post">
	<logic:notEmpty name="patientForm" property="labelsList">
	
			<table border="0" width="100%" align="center" >
				<tr class="tbheader print_heading"><td><bean:write name="patientForm" property="investigations"/></td></tr>
			</table>
		
			<table border="0" width="100%">
				<tr class="tbheader">
				<td style=""><b>Description of the field</b></td>
				<td style=""><b>Field</b></td>
				<td style=""><b>Reference Interval</b></td>
				<!--  <td style=""><b>Method</b></td>-->
				</tr>
			
				<logic:iterate id="labelsItem" name="patientForm" property="labelsList" indexId="cnt">
	
					<input type="hidden" name="labelsItem[${cnt}].FIELDTYPE"  id="labelsItem[${cnt}].FIELDTYPE" value="<bean:write name='labelsItem' property='FIELDTYPE'/>"/>
					<input type="hidden" name="labelsItem[${cnt}].INVESTIGATIONNAME"  id="labelsItem[${cnt}].INVESTIGATIONNAME" value="<bean:write name='labelsItem' property='INVESTIGATIONNAME'/>"/>
					<input type="hidden" name="labelsItem[${cnt}].SID"  id="labelsItem[${cnt}].SID" value="<bean:write name='labelsItem' property='SID'/>"/>
					<input type="hidden" name="labelsItem[${cnt}].REFERENCEINTERVAL"  id="labelsItem[${cnt}].REFERENCEINTERVAL" value="<bean:write name='labelsItem' property='REFERENCEINTERVAL'/>"/>
					<!--  <input type="hidden" name="labelsItem[${cnt}].METHOD"  id="labelsItem[${cnt}].METHOD" value="<bean:write name='labelsItem' property='METHOD'/>"/>-->
					
				
				<tr>
					
					<logic:equal name="labelsItem" property="FIELDTYPE" value="T">
						<td><bean:write name="labelsItem" property="INVESTIGATIONNAME"/></td>
						<td>
						   <html:text name="labelsItem"  property="VALUEOFFIELD"  styleId = "labelsItem[${cnt}].VALUEOFFIELD" indexed="true"></html:text>
						</td>
						<td>
						    <bean:write name="labelsItem" property="REFERENCEINTERVAL"/>
						</td>
						<!-- <td>
							<bean:write name="labelsItem" property="METHOD"/>		
						</td> -->
					</logic:equal>
					<td>
				</tr>
				
				</logic:iterate>
				
				
				</table>
				<table  border="0" width="100%">
					<tr>
						<td  align="center">
							<button class="btn btn-primary"  type="button" id="save" onclick="javascript:saveData('save');">Save</button>
							<button class="btn btn-primary"  type="button" id="Submit" onclick="javascript:saveData('submit');">Submit</button>
							<!-- <input type = "button" name="save" id="save" value="save" onclick="saveData('save');"/> -->
						</td>
						<td>
							
							<!-- <input type = "button" name="save" id="save" value="submit" onclick="saveData('submit');"/> -->
						</td>
					</tr>
				</table>
	</logic:notEmpty>
	<logic:empty name="patientForm" property="labelsList">
		<table  border="0" width="100%">
			<tr>
				<td  align="center"><b>Data for this Investigation is Not Available </b></td>
							
			</tr>
		</table>
	</logic:empty>
</html:form>
</body>
</HTML>