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
<title>Email Creation By Excel</title>
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
<style>
 body{font-size:1.2em !important;} 
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
 overflow: hidden;
 width: 100%;
}
.no-margin{
margin:0px !important;
}
</style>

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

function fn_loadImage()
{
  document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage()
{   
  document.getElementById('processImagetable').style.display="none";
}
function validateAttachment(vFileName)
{
	if(vFileName == null || vFileName=='')
	 {
	 //alert('Please upload file');
	 jqueryAlertMsg('Email Creation',"Please upload file");
	 flag = false;
	 return;
	 }
	 vSplit=vFileName.split("\\");
	 vFileName = vSplit[(vSplit.length)-1];	
	 var pos=vFileName.lastIndexOf(".");
	 var sub=vFileName.substring(pos+1,vFileName.length); 
	 if((sub=='xls')||(sub=='XLS')  )
	    {
		 flag = true;
	    } 
	 else
		 {
		 flag = false; 
		 jqueryAlertMsg('Email Creation',"Please upload xls  file");
		 return;
		 }
	  var flag = chkSpecailCharsAttach(vFileName);
	    if(!flag)
	   	 {
	    	 jqueryAlertMsg('Email Creation',"Special characters are not allowed");
	    	 flag = false; 
	    	 return;
	   	 }
	return flag;
	}
function chkSpecailCharsAttach(vFileName)
{
   var flag =true;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {  
        	flag=false;
           break;
        } 
    }
    return flag;
} 
function fn_submit()
   {
	var flag = validateAttachment(document.getElementById("attachment").value);
	if(flag)
		{
		 var fr=partial(onSuccessSave);
    	 jqueryConfirmMsg("Confirm","Are you sure you want to create email ids for the provided employees?",fr);
		}
     }
     function onSuccessSave()
     {
 		fn_loadImage();
 		document.getElementById('buttonBlock').disabled="true";
 		document.forms[0].action="/<%=context%>/createEmployee.do?actionFlag=emailCreationByExcel";
 		document.forms[0].submit();
     }
	function fn_reset()
	{
	 var fld = document.getElementById('attachment');
	 fld.parentNode.innerHTML = fld.parentNode.innerHTML;
	}
	
	function fn_openAtachment(filepath)
	{  
		document.forms[0].action= "/<%=context%>/createEmployee.do?actionFlag=viewAttachment&filePath="+filepath;
		document.forms[0].submit();    
	}
	function fn_alert()
	{
		fn_removeLoadingImage();
		if('${resMsg}' !=null && '${resMsg}'!='')
		jqueryAlertMsg('Email Creation','${resMsg}');	
		parent.fn_removeLoadingImage();
	}
	
	
</script>
</head>
<body  style="height:300px" onload="fn_alert()">
<html:form  method="post"  action="/createEmployee.do" enctype="multipart/form-data" styleId="createEmployeeForm"> 
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss no-margin">
<div class="tbheader"><b>Email Creations Through Excel</b></div>                          
</div>
<div class="container-fluid center-block">
<div class="row">
<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
<b>File Upload</b>
<b>(<a href="javascript:fn_openAtachment('<bean:write name="createEmployeeForm" property="templatePath" />')">Download Template</a>)</b>
</div>
<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
<html:file property="attachment" name="createEmployeeForm" styleId="attachment"></html:file>

</div>
</div>
</div>

<div id="buttonBlock">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss" align="center">
<button class="btn but" id="Submit"  type="button" name="Submit" value="Submit" onclick="javascript:fn_submit()" >Submit</button>
<button class="btn but" id="Cancel"  type="button" name="Cancel" value="Cancel" onclick="javascript:fn_reset()">Reset</button>
</div>
</div>

<div id="processImagetable" style="top:15%;z-index:50;position:absolute;left:45%">
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
</html:form>
</body>
</html>