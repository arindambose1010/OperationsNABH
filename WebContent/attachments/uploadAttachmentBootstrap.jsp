 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%><html>
<head>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery.msgBox.js" type="text/javascript"></script>
<LINK href="./css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="./bootstrap/css/bootstrap.min.css" rel="stylesheet">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Upload Attachemnt</title>
<style>
html, body { overflow: hidden; }
</style>
<script>
var attachType='${attachType}';

var patientId='${patientId}';

var enrollId=parent.patientId;
var perPage='${perPage}';
var attachName='${attachName}';

function uploadAttachment()
{ 
	var filename='';
	if(document.getElementById("upload").value!=''){
		filename=document.getElementById("upload").value;
		
	}
	if(validateAttachment(filename)){
		// parent.fn_load_image();
 document.patientForm.action='./patientDetailsNew.do?actionFlag=uploadAtach&attachType='+attachType+'&patientId='+patientId;
   document.patientForm.submit(); 
  
	}
	else{
		parent.bootbox.alert("Please Upload pdf,jpeg,jpg files only");
		return false;
	}
}

function validateAttachment(filename){
/* 	alert(filename);
 */	var len=filename.length;
	if(filename!=''){
		var pos=filename.lastIndexOf(".");
        var sub=filename.substring(pos+1,len);
        if(sub!="pdf" && sub!="jpg" && sub!= "jpeg"){
        	return false;
        }
        else{
        	return true;
        }
	}
}
function chkSpecailChars(vFileName)
{

   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
} 
function fn_openAtachment1(filepath,fileName)
{
	window.open('./patientDetailsNew.do?actionFlag=viewAttachment&&filePath='+filepath);
	//window.parent.$('#viewImage').modal('show');
	//parent.document.getElementById("imageViewDiv").src='./patientDetailsNew.do?actionFlag=viewAttachment&filePath='+filepath;
}
function error(){	
	var rm='${resMsg}';
	if(rm!=null && rm!=""){
	 parent.bootbox.alert(rm);
	 parent.parent.$('html, body').animate({"scrollTop": $('body').height()}, 500,'linear');
	}
}
$(document).ready(function() {
	var perPage='${perPage}';
	var ais='${ais}';
	if(ais!=null && ais!='' && ais=='Y')
		{
		alert("Attachment uploaded successfully");
		if(parent.document.getElementById("fileCnt")!=null)	
		{
		parent.document.getElementById("fileCnt").value=parseInt(parent.document.getElementById("fileCnt").value)+1;
		}
		}
	if(perPage=="S"){
		
		parent.photopath1='${filepath}';
		parent.viewPhoto('${filepath}');
		
	}
	var resMgs='${ResultMsg}';
	if(resMgs!=null && resMgs!=""){
		parent.parent.$('html, body').animate({scrollTop: 0}, 500, 'linear');
		 parent.$('html, body').animate({scrollTop: 0}, 500, 'linear');
		 $('html, body').animate({scrollTop: 0}, 500, 'linear');	
		parent.bootbox.alert(resMgs);
			
	}
	var unitRole='${unitRole}';
	if(unitRole=="CD1303")
		$("#uploadDiv").show();

}); 

function deleteData(seq){

	var url= "/claimreimbursment.do?actionFlag=delAttachments&seq="+seq;

	  $.post(
				url,
				$('[name="patientForm"]')
						.find(
								':input:not(:radio:not(:checked)):not(:checkbox:not(:checked)):not(:file)'),
				function(data) {
					
					if(data != null){
				
					data=data.split("~");
				
					data[0] = data[0].replace('"', '');
					data[0] = data[0].replace('"', '');
					data[1] = data[1].replace('"', '');
				
					if(data[0] != null && data[0]=='Y'){
						parent.bootbox.alert("Attachment Deleted Successfully");
						$("#singleAttach").hide();
						
						}else{
							bootbox.alert("Could not Delete Attachment,Please Try Again");
						}
						
					}
					
				});  
	
	

}

</script>
</head>
<body>


<form action="/patientDetailsNew.do" method="post" name="patientForm" enctype="multipart/form-data">
<html:hidden name="patientForm" property="patientNo" styleId="patientNo" />
<c:if test="${msg ne null && msg eq 'initiated'}">
<script>
	  parent.fn_removeLoadingImage();
	parent.bootbox.alert("Claim Already Initiated",function(){
		parent.parent.$('html, body').animate({"scrollTop": $('body').height()}, 500,'linear');
		parent.InClaimReimbursement();
	});
	
	  </script>

</c:if>


<c:if test="${msg ne null && msg ne 'initiated' }">
	  <script>
	  parent.fn_removeLoadingImage();
	parent.bootbox.alert("${msg}");
	parent.parent.$('html, body').animate({"scrollTop": $('body').height()}, 500,'linear');
	  </script>
</c:if>	
<c:if test="${patientForm.attachName eq null ||  patientForm.attachName eq ''}">

  <div class="row" id="uploadDiv">

            <div class="col-md-12 col-sm-12 col-xs-12 form-group" >

<!--  <input type="file" name="attachFile"  value="Upload" id="upload" onchange="javascript:uploadAttachment()" />
 -->
 <label id="uploadFile" class="btn btn-primary btn-file">Upload<input
					type="file" id="upload" name="upload"
					onchange="javascript:uploadAttachment()" styleClass="form-control"
					style="display: none;" /></label>
</div></div>
</c:if>


<div class="row" id="singleAttach">
<c:if test="${patientForm.attachName ne null &&  patientForm.attachName ne ''}">
<font size="2"  color="black"><a  style="color:#2a6496;word-break: break-word;margin-left:1.5em;" href="javascript:fn_openAtachment1('<bean:write name="patientForm" property="attachPath"/>','<bean:write name="patientForm" property="attachName"/>');">${patientForm.attachName}</a></font>
</c:if>
</div>


</form>
</body>
</html>