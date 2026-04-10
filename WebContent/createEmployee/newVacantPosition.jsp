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
<link href="CEO/ceoFilesNew/select2.min.css" rel="stylesheet">
<script src="CEO/ceoFilesNew/select2.min.js"></script>
<title>Vacant Position Creation</title>
<!-- Ended testing -->
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<style>
body{
font-size:1.3em; !important;}  
.but{
border-radius:16px;
}
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
.ui-autocomplete-input {
    width: 17em;
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

//$('groupType').multiselect();

/* $(document).ready(function() 
		{
	$('#createEmployeeForm').formValidation(
	{
		
		framework: 'bootstrap',
		icon: 
		{
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
		},
		 fields:{
			
		        groupType: {
		            validators: {
		                notEmpty: {
		                    message: 'Please select group type'
		                },
		                
		        }, 
		        
		            } 
		        
		 } 
		});	
}); */

var checkFlag=false;

function addTooltip(id)
{
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;		

}

function fn_checkAvailability()
{

	var positionName=document.getElementById("vacPosName").value;
	positionName = positionName.replace(/\s\s+/g, ' ');
	document.getElementById("vacPosName").value=positionName;
	if(positionName=="" || positionName==null )
		{
		jqueryAlertMsg("Alert","Please enter Vacant Position Name");
		return;
		}
	else
		{
		 fn_loadImage();
		 document.forms[0].action ='createEmployee.do?actionFlag=checkAvailability&positionName='+positionName;
		 document.forms[0].submit();
		 document.forms[0].check.disabled = true;
		// document.forms[0].save.disabled =false;
		 
		}
	}
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
function closeIframe(){
	parent.document.getElementById("iFrameIdCreateEmp").style.display="none";
}

  function fn_showAlert()
    {
	  var flag = "${flag}"; 
	
	  var successflag="${result}";
	  var postName=  document.getElementById('vacPosName').value;
	  if(flag == "true") {
		  document.getElementById('checkPosImg').src='images/error1.jpg';
		  document.getElementById('checkPosImg').name='error';
		  document.getElementById('checkPosImg').style.display='inline';
		  document.getElementById('checkPosImg').title="Not Available";
		  var fr=partial(focusBox,document.getElementById('vacPosName'));
		  jqueryAlertMsg("Alert","Position name "+ postName +" already existing . Please enter a new position name",fr);
		  document.getElementById("vacPosName").value="";
		  return;
		}
	if(flag=="false")
		{
		 document.getElementById('checkPosImg').style.display='inline';
		 document.getElementById('checkPosImg').title="Available";
		 jqueryAlertMsg("Alert","Name of the Position "+ postName +"  is available");
		 document.getElementById("vacPosName").disabled=true;
		 document.forms[0].check.disabled = true;
		 checkFlag=true;
		  return;
		}
	if(successflag=="success")
		{
		var fr=partial(resetPage);
		jqueryAlertMsg("Alert","Vacant Position "+ postName +" created Succesfully",fr);
		}
	 parent.fn_removeLoadingImage();
	}
  function resetPage()
  {

	    document.getElementById('vacPosName').value="";
	    document.getElementById("vacPosName").readOnly=false;
	    document.getElementById("vacPosName").disabled=false;
	    document.getElementById('checkPosImg').style.display='none';
	    document.getElementById('deptName').value="";$('#deptName-input').val('');
	    document.getElementById('desgName').value=""; $('#desgName-input').val('');
	   
	    document.getElementById("groupType").value="";
	    $("#groupType").select2("val", 'All');
	   
	    document.getElementById('weekOffDay').value="";$('#weekOffDay-input').val('');
	    document.getElementById('shift').value="";$('#shift-input').val('');
	    document.getElementById('level').value="";$('#level-input').val('');
	    document.getElementById('repPerson').value="";$('#repPerson-input').val('');
    	document.forms[0].check.disabled = false;
    	/* disableComboBox('deptName-input');
    	disableComboBox('desgName-input');
    	disableComboBox('weekOffDay-input');
    	disableComboBox('shift-input');
    	disableComboBox('level-input');
    	disableComboBox('repPerson-input'); */
   	    document.forms[0].save.disabled =true;
    	 
    	if(document.getElementById('shi1')!=null)
    		document.getElementById('shi1').style.display="none";
   	    
    	 if(document.getElementById('shi2')!=null)
    	 	document.getElementById('shi2').style.display="none";
   	    
   	    if(document.getElementById('rep')!=null)
   	    	document.getElementById('rep').style.display="none";

   	 
   	  	 document.getElementById('schemeType').value="";$('#schemeType-input').val('');
			 
  
  	    
  }
  function fn_loadImage()
  {
    document.getElementById('processImagetable').style.display="";
  }
  function fn_vacantPositionName(){
		
		var val= document.getElementById('vacPosName').value; 
		var pattern=/^[0-9 A-Z a-z _]+$/; 
		var dot = /^[_]+$/;
		if(!val.match(pattern))
			{
			 var fr=partial(focusBox,document.getElementById('vacPosName'));
			 jqueryAlertMsgTop("Alert",'Vacant position name accepts _ and alphanumerics only',fr);
	         document.getElementById('vacPosName').value="";	
	         return false;
			}
		if(val.match(dot))
			{
			var fr=partial(focusBox,document.getElementById('vacPosName'));
			 jqueryAlertMsgTop("Alert",'Please enter valid vacant position name',fr);
	         document.getElementById('vacPosName').value="";	
	         return false;
			}
		
  }
  function fn_removeLoadingImage()
   {   
     document.getElementById('processImagetable').style.display="none";
   }
  function fn_removeImg()
  {
	  var imgName=document.getElementById('checkPosImg').name;
	  if(imgName=="error")
		  {
	  document.getElementById('checkPosImg').style.display="none";
		  }
  }
  function fn_getLevels()
  {

	  document.getElementById('level').value="";$('#level-input').val('');
   	  document.getElementById('repPerson').value="";$('#repPerson-input').val('');
   	 
	  //document.getElementById('shi1').style.display="";
	  //document.getElementById('shi2').style.display="";  
	 // document.getElementById('rep').style.display="";
	  
 	// alert(document.getElementById('rep').value);
  	if(document.getElementById('shi1')!=null)
  		document.getElementById('shi1').style.display="";
 	    
  	 if(document.getElementById('shi2')!=null)
  	 	document.getElementById('shi2').style.display="";
  	 
  	if(document.getElementById('rep')!=null) 
    	document.getElementById('rep').style.display="";
	  
	  if(document.getElementById('level').value!="" && document.getElementById('deptName').value!="")
		  {
		  		  fn_getReportingPerson();
		  }
	  
  }
  function fn_validate()
  {
	//  alert(document.getElementById('groupType').value);
		if($('#deptName option:selected').val()== "")
			     {
			 var fr=partial(focusBox, $('#deptName-input').val(''));
					jqueryAlertMsg("Alert",'Please Select Department Name',fr);
			  		return false;
				 }	
		if($('#desgName option:selected').val()== "")
	     {
			 var fr=partial(focusBox, $('#desgName-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Designation',fr);
	  		return false;
		 }	
		 if(document.getElementById('groupType').value == "")
	     {
			 var fr=partial(focusBox, $('#groupType-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Group Type',fr);
	  		return false;
		 }	
		if($('#shift option:selected').val()== "")
	     {
			 var fr=partial(focusBox, $('#shift-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Shift',fr);
	  		return false;
		 }	
		if($('#weekOffDay option:selected').val()== "")
	     {
			 var fr=partial(focusBox, $('#weekOffDay-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Week Off Day',fr);
	  		return false;
		 }
		if($('#level option:selected').val()== "")
	     {
			var fr=partial(focusBox, $('#level-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Level',fr);
	  		return false;
		 }	
		if($('#repPerson option:selected').val()== "")
	     {
			var fr=partial(focusBox, $('#repPerson-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Reporting Person',fr);
	  		return false;
		 }	 
		 
		 
		if($('#schemeType option:selected').val()== "")
	     {
			var fr=partial(focusBox, $('#schemeType-input').val(''));
			jqueryAlertMsg("Alert",'Please Select Scheme Type',fr);
	  		return false;
		 }	
		
		if(checkFlag==false)
			{
			var fr=partial(focusBox, $('#check').val(''));
			jqueryAlertMsg("Alert",'Please Check Availability',fr);
	  		return false;
			}
		return true;
	  
	  }
 
  function saveVacantPosition()
  {
	  var group=document.getElementById("groupType").value;
	 
	 var flag=fn_validate();
	 $('#createEmployeeForm').formValidation().formValidation('validate');
		var x=$('#createEmployeeForm').data('formValidation').isValid();

     if(flag==true && x==true)
         {
    	 
    	 var fr=partial(onSuccessSave);
 	   	 jqueryConfirmMsg("Confirm","Are you sure you want to save vacant position details?",fr);
    	 
    	
        }
 
  }
  function onSuccessSave()
  {
	  var vacPosName=document.getElementById('vacPosName').value;
	    fn_loadImage();
 	    document.forms[0].action = 'createEmployee.do?actionFlag=saveVacantPositionDtls&vacPosName='+vacPosName;
		 document.forms[0].submit();
		 document.forms[0].save.disabled = true;
  }
  function fn_getDesignations()
  {  
	var deptName=document.getElementById('deptName').value;
	var xmlhttp;
	var url;
	
	
	url= 'createEmployee.do?actionFlag=getDesignations&deptName='+deptName;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.forms[0].desgName.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){   
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].desgName.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
	document.getElementById('desgName').options.length=0;
	$('#desgName-input').val('');
	$("#deptName").on('change', function (event) {
       /*  $("#jqxComboBox").jqxComboBox('clear'); */
    }); 
	
	
	}
  
function getGroupNames()
  {
  var desgName=document.getElementById('desgName').value;
  var deptName=document.getElementById('deptName').value;
	var xmlhttp;
	var url;
	url= 'createEmployee.do?actionFlag=getGroups&desgName='+desgName+'&deptName='+deptName;
	if (window.XMLHttpRequest){
	 xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
	 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	xmlhttp.onreadystatechange=function(){
	    if(xmlhttp.readyState==4){	
	    	document.getElementById("groupType").length=0;
	    	document.getElementById("groupType").options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0){ 
                 	
		                for(var i = 0; i<addList.length;i++){	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null){
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,""); 
		                         //document.forms[0].groupType.options[i+1] =new Option(val1,val2); 
		                         document.getElementById("groupType").options[i+1] =new Option(val1,val2); 
		                     	}
		                }
		                
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
	
	
	
	
  }
  function fn_getReportingPerson()
  {
	  
	  document.forms[0].repPerson.options.length=0;
   	document.getElementById('repPerson').value="";//$('#repPerson-input').val('');
    document.getElementById('schemeType').value="";//$('#schemeType-input').val('');
	  document.getElementById('repPerson').style.display="";
	  var level=document.getElementById('level').value;
	  var deptName=document.getElementById('deptName').value;

	  if (window.XMLHttpRequest)
	  {
	   xmlhttp=new XMLHttpRequest();
	  }
	  else if (window.ActiveXObject)
	  {
	   xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	  else
	  {
	   alert("Your browser does not support XMLHTTP!");
	  } 
	 
	  
	  var url='createEmployee.do?actionFlag=getReportingPersonDetails&level='+level+'&deptId='+deptName;		
	  xmlhttp.onreadystatechange=function()
	  {
	  if(xmlhttp.readyState==4)
	  {
		  var resultArray=xmlhttp.responseText;
		 
		 if(resultArray =="SessionExpired")
   		{
   			alert("Session has been expired");
   			parent.location.href="index.jsp";
   		}
   		else
   		{  
   			document.forms[0].repPerson.options[0]=new Option('-------- Select --------',"");	
   		
		if(resultArray!=null)
         {   
			 resultArray2 = resultArray.replace("[","");
	         resultArray2 = resultArray2.replace("]","");            
	         var addList = resultArray2.split(", @");
	         if(addList.length>0)
	            {   
	                for(var i = 0; i<addList.length;i++)
	                {	
	                    var arr=addList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null)
	                     {
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                     	 document.forms[0].repPerson.options[i+1] =new Option(val1,val2);       
					   } 
	                }
	            }
		
		
		}
		if(resultArray=="[]")
			{
			document.forms[0].repPerson.options[1]=new Option('No Reporting Person',"NA");
			}
		
	  }
	  }
	  }
	  xmlhttp.open("Post",url,true);
	  xmlhttp.send(null);				      
	  
  }
  
/*   $(document).ready(function () {   
	  $("#jqxComboBox").jqxComboBox({multiSelect: true, width: 252, height: 25, showArrow: true ,disabled:true});
	  $('#reset').on('click', function (event) {
	        $("#jqxComboBox").jqxComboBox('clearSelection');
	        $("#jqxComboBox").jqxComboBox({disabled:true});
	    });	 	
	  $('#deptName').on('change', function (event) {
	        $("#jqxComboBox").jqxComboBox('clearSelection');
	    });	 
  }); */
  

</script>

</head>
<body onload="fn_showAlert();fn_removeLoadingImage()">
 
    
<form action="/createEmployee.do" method="POST" enctype="multipart/form-data" name="createEmployeeForm">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss no-margin">
                       <div class="tbheader"><b>Vacant position Creation</b></div>
  </div>     
					    	<div align="left">		
				<span style="color: #ff0000"> *-Mandatory Fields</span>
			</div>
					    <div class="container-fluid center-block" id="createEmployeeForm">
						<div class="row">
						<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
							Vacant Position Name:<font color="Red">*</font>
		                         <html:text property="vacPosName" name="createEmployeeForm" styleId="vacPosName" onkeypress="fn_removeImg()" styleClass="form-control" title="Enter name of the new Vacant Position" onchange="fn_vacantPositionName()">
								</html:text>
							</div>
					   <img id="checkPosImg" src="images/current.png" style="display:none"/>
					   <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12  bottom-margin tbcellCss" style="padding-top:14px;">
					     <button class="but form-group"  id="check" name="check" tabindex="0" type="button"  title="Check Availability of Vacant Position Name"  onClick="fn_checkAvailability();">Check Availability</button>
						</div>
						</div>
						<div class="row">
						 <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						 Department:<font color="Red">*</font>
						 <html:select property="deptName" name="createEmployeeForm" styleId="deptName" styleClass="form-control" title="Select Department"  onmouseover="addTooltip('deptName');"  onblur="fn_getLevels()" onchange="fn_getDesignations()">
								<option value="">--------Select---------</option>
								<html:options collection="deptNamesList" labelProperty="value" property="id"/>
								</html:select>
						</div>
						<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						 Designation:<font color="Red">*</font>
						 <html:select property="desgName" name="createEmployeeForm" styleId="desgName" styleClass="form-control" title="Select Designation" onmouseover="addTooltip('desgName');" onchange="getGroupNames()">
								<option value="">--------Select---------</option>
								 <html:options collection="desgNamesList" labelProperty="value" property="id"/>
								</html:select>
						</div>
						</div>
						<div class="row">
							<!-- <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
							<em>Group Type:</em><font color="Red">*</font>
							<select data-placeholder="select group list" style="width:350px;display:none;" multiple class="chosen-select" tab index="-1"></select>
							<div class="chosen-container chosen-container-multi chosen-with-drop chosen-container-active" style="width:350px;"title></div>
							<ul class="chosen-choice"></ul>
							<div class="chosen-drop"></div>
							<select id="groupType" multiple="multiple" style="display:none;"> </select>
							<div class="btn-group">
							<button type="button" class="multiselect dropdown-toggle btn btn-default" data-toggle="dropdown" title="Select Group List" aria-expanded="true">
							<span class="multiselect-selected-text"></span>
							<b class="caret"></b>
							</button>
							<ul class="multiselect-container dropdown-menu"></ul>
							</div></div> -->
							
							<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
							Group Type:<font color="Red">*</font>
					    <html:select  property="groupType" multiple="multiple" style="width:100%" name="createEmployeeForm" styleId="groupType" styleClass="groupType-monika" title="Select Group List" onmouseover="addTooltip('groupType');">
							
								<html:options collection="groupNamesList" labelProperty="value" property="id"/>
								</html:select>
					</div>
						
						<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						 Shift:<font color="Red">*</font>
						 <html:select property="shift" name="createEmployeeForm" styleId="shift" styleClass="form-control" title="Select Shift" onmouseover="addTooltip('shift');">
								<option value="">--------Select---------</option>
								<html:options collection="shiftList" labelProperty="value" property="id"/>
								</html:select>
								</div>
								</div>
								<div class="row">
								<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								Week Off Day:<font color="Red">*</font>
								<html:select property="weekOffDay" name="createEmployeeForm" styleId="weekOffDay" styleClass="form-control" title="Select Week Off Day" onmouseover="addTooltip('weekOffDay');">
								<option value="">--------Select---------</option>
								<html:options collection="weekList" labelProperty="value" property="id"/>
								</html:select>
								</div>
								<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								Level:<font color="Red">*</font>
								<html:select property="level" name="createEmployeeForm" styleId="level" styleClass="form-control" title="Select Level" onmouseover="addTooltip('level');"onblur="fn_getReportingPerson()">
								<option value="">--------Select---------</option>
								<html:options collection="levelList" labelProperty="value" property="id"/>
								</html:select>
								</div>
								</div>
						
						
						<div class="row" id="rep" style="display:none">
						<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Reporting Person:<font color="Red">*</font>
						<html:select property="repPerson" name="createEmployeeForm" styleId="repPerson" styleClass="form-control" title="Select Reporting Person" onmouseover="addTooltip('repPerson');">
								<option value="">--------Select---------</option>
								<html:options collection="repPersonList" labelProperty="value" property="id"/>
								</html:select>
								</div>
					    <div class="col-lg-5 col-md-5 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Scheme:<font color="Red">*</font>
						<html:select property="schemeType" name="createEmployeeForm" styleId="schemeType" styleClass="form-control" title="Select Scheme of Employee" onmouseover="addTooltip('schemeType');">
								<option value="">--------Select---------</option>
								<html:option value="CD202">Telangana</html:option>
						
						</html:select>
								</div>
								</div>
								
						
					
						</div>
						
                          <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center">
									<br/>
                                   <button class="but"  id="save" name="save" tabindex="0" type="button"  title="Save" onClick="saveVacantPosition();">Save</button>
                                  <button class="but"  id="reset" name="reset" tabindex="0" type="button"  title="Reset"   onClick="resetPage();">Reset</button>   
                                  <!-- <button class="but" id="closeBtn" name="closeBtn" tabindex="0" type="button"   title="Close" onClick="closeIframe();">Close</button></td> -->
                       
                       </div>
               </div>     
                       
			<html:hidden  name="createEmployeeForm"  property="schemeFlag" styleId="schemeFlag" />		
			<html:hidden  name="createEmployeeForm"  property="vacPosName" styleId="vacPosName" />		
			
 <div id="processImagetable" style="top:20%;z-index:50;position:absolute;left:45%">
        <table border="0" align="center" width="100%" style="height:100" >
          <tr>
            <td>
              <div id="processImage" align="center">
                <img src="images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div>
            </td>
          </tr>
        </table>
         </div>
<html:hidden name="createEmployeeForm" property="groupName" styleId="groupName"></html:hidden>
<script>
$(".groupType-monika").select2();
$("#groupType").select2({
	placeholder:"--------Select---------",allowClear:true
});

</script>

</form>
</body>
</html>