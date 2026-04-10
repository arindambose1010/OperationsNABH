<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Group Email</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
     <%@ include file="../common/include.jsp"%> 
     <link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
     <script src="/<%=context%>/js/jquery.msgBox.js" type="text/javascript"></script>
     <script>
     function partial(func /*, 0..n args */) {
    	 var args = new Array();
    	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
    	  return function() {
    	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
    	    return func.apply(this, allArguments);
    	  };
    }
    function focusBox(arg)
    {	
     aField = arg; 
     setTimeout("aField.focus()", 0);  
    }
     function fn_sendEmail()
     {
    	 var fr=partial(onSuccessSave);
 	   	 jqueryConfirmMsg("Confirm","Are you sure you want to send emails to users?",fr);
     }
     function onSuccessSave()
     {
    	 document.forms[0].action ='/<%=context%>/createEmployee.do?actionFlag=sendGroupMails';
		 document.forms[0].submit();
		 document.forms[0].sendEmail.disabled = true;
     }
     </script>
     
</head>
<body>
<html:form  method="post"  action="/createEmployee.do" enctype="multipart/form-data"> 
<table width="50%" align="center" style="padding-top:0px;margin:0px auto;table-layout: fixed;" border="0">
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td class="tbheader"  colspan="2"><b>Group Email</b></td></tr>

<tr><td colspan="2" class="labelheading1 tbcellCss" ><span style='color: red'>Click here to send group E-Mail regarding EHS Services</span></td></tr>
<tr>
<td colspan="2" class="labelheading1 tbcellCss" align="center">
<div id="buttonBlock">
<button class="but" id="sendEmail"  type="button" name="sendEmail" value="SendEmail" onclick="javascript:fn_sendEmail()" >Send Email</button>
</div>
</td>
</tr>
</table>

</html:form>
</body>
</html>