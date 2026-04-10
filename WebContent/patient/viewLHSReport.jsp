

<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>View document</title>
</head>
<body>
 
<%

    String base64DataDoc = (String) request.getAttribute("base64DataDoc");

    String contentType = (String) request.getAttribute("ext");

    /* if (base64DataDoc != null) {

        if (base64DataDoc.startsWith("JVBERi0xLjQ") || base64DataDoc.startsWith("dGVzdC5odG1s")) { 

            contentType = "pdf";

        } else if (base64DataDoc.startsWith("iVBORw0KGgo")) {

            contentType = "png";

        } else if (base64DataDoc.startsWith("/9j/")) {

            contentType = "jpeg";

        } else if (base64DataDoc.startsWith("PGh0bWw+")) {

            contentType = "html";

        }

    } */

%>
 
<% if ("pdf".equals(contentType)) { %>
<iframe src="data:application/pdf;base64,<%= base64DataDoc %>" frameborder="0" scrolling="auto" height="500px" width="100%" style="visibility: visible; height: 680px; margin-left: 100px;"></iframe>
<% } else if (contentType != null && !"".equals(contentType)) { %>
<img src="data:image/<%= contentType %>;base64,<%= base64DataDoc %>" style="width:100%; height:500px; margin-left:100px;" />
<% } else { %>
<p>Sorry, we could not display the document. Unsupported content type.</p>
<% } %>
 
</body>
</html>