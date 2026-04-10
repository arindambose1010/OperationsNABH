<html>
<head>
<script> 
window.history.forward();
</script>
<script>
var contextName="<%=request.getContextPath()%>";
//alert(contextName);
</script>
</head>
<%
response.setHeader("pragma", "no-cache");
response.setHeader("cache-content", "no-cache");
response.setHeader("expires", "0");
request.setAttribute("themeColour", session.getAttribute("themeColour"));
%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<bean:define id="context" value="OperationsNABH"/>
<bean:define id="themeColour" value="${themeColour}"/>
<base href="${pageContext.request.contextPath}/">
</html>

