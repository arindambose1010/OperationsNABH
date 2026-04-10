<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
  	 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
 <script language="javascript" type="text/javascript"></script>
</head>

<style>

table#tokenNos tr:nth-child(even) {
    background-color: #eee;
}
table#tokenNos tr:nth-child(odd) {
   background-color:#fff;
}
/* table#tokenNos th {
    background-color: black;
    color: white;
} */

</style>

<script>
$( document ).ready(function() {
	setTimeout(function(){
		   window.location.reload(1);
		}, 30000);
	});
</script>
<body bgcolor="#ffcccc">

<table  style="width:100%;margin:0 auto">
    
			<tr  class="screen_header"><td width="15%" style="text-align:right;"><img border='0' src="images/telanganalogo.png"/></td>
				<td width="70%" style="font-size:22px;text-align:center;"><!-- <span style="font-size:14px;"></span><br /> -->TELANGANA GOVERNMENT <br> EMPLOYEES / JOURNALISTS HEALTH SCHEME</td>
			 	 <td width="15%" style="text-align:left;"><img border='0' src="images/telangana_cm_right.png"/></td>  
				<!-- <td width="18%" style="font-size:22px;text-align:center;">&nbsp;</td> -->
			</tr>
			<tr><td style="text-align:center" width="100%" colspan=3>
		Contact : ${contactNo}
	</td></tr>
			</table>

<br>
<hr>
 <table  style="width:100%;margin:0 auto">
<tr >
	<td  style="font-size:25px;text-align:center;font-family:Verdana;">${dispName}</td>	
</tr>
</table>
<br> 

<table border="1" style="width:70%;margin:0 auto;border-collapse:collapse;" id="tokenNos" cellpadding="5px" >
<tr>
							<th width="40%"  style="font-size:25px;text-align:center;font-family:Verdana;">Room No</th>
							<th  width="40%"  style="font-size:25px;text-align:center;font-family:Verdana;">Token No</th>
							
  					</tr>
		 <c:forEach items="${roomTokens}" var="inv">
		
					 	<tr>
							<td  width="40%"  style="font-size:25px;text-align:center;font-family:Verdana;">${inv.roomNo}</td>
							<td  width="40%"  style="font-size:25px;text-align:center;font-family:Verdana;"><blink>${inv.tokenNo}</blink></td>
							
  					</tr>
					</c:forEach>

</table>

</body>
</html>