<!DOCTYPE html>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%> 
<html>
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<title>Employees Health Scheme </title>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="shortcut icon" href="images/favicon.ico" >
<link rel="icon" type="images/ico" href="images/favicon.ico">
<link rel="stylesheet" href="css/login.css" type="text/css" media="screen" />
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" />
<style>
.page-header{
	background-color : #F8F8F8;
	background-height : 15%;
}
.list-group-item{
border:0;
}
#fdiv2 .table,th,tr,td{border:0;}
.header{
	
	color:#135B95;
	font-size:2em;
	font-family:Times New Roman;
	text-align:center;
}
.modal-content {
    /* ... */
    border: 0.1em solid #428BCA;border-radius:3px;
    overflow-y: auto;
    overflow-x:scroll;
}
.step{background-color:#428BCA;border-radius:3px;color:#ffffff;font-weight:bold}
.header sup{
	color:#F10104;
}
.list-group a{
	color : #ffffff;
	text-decoration:none;
}

.list-group a:hover{
	color : #ffffff;
	text-decoration:underline;
}
.login-container{
	border-radius:10px;
}
.user-login-info {
    background: rgb(130,169,224);
    padding: 20px;
    margin-bottom: 10px;
}
</style>
<style>

.login div.form-group {
    margin: 0;
}
.login div.form-group a {
    margin: 4% 0 0 0;
}
.login div.form-group label {
    padding: 5px;
}
.moduleName {
    text-align: center;
}
.moduleName h1 {
color: #093edc;
    font-size: 25px;
    padding: 18px;
    text-shadow: 2px 2px 1px #bae5ce;
}
.moduleName h2 {
    font-size: 20px;
    padding-bottom: 10px;
    color: #093edc;
    text-shadow: 2px 2px 1px #bae5ce;
}
.login {
    background: radial-gradient(#ffffff, #e19ad8);
    border: 1px solid #bcbcbc;
    border-radius: 8px;
    margin-top: 3%;
    padding: 10px;
     width: 35%;
}
#usernameAlert {
    left: 51.5%;
    position: absolute;
    top: 35%;
    z-index: 1;
}
#passwordAlert {
    left: 51.5%;
    position: absolute;
    top: 44%;
    z-index: 1;
}
.usernamepwd{
margin: 10px auto;
}
.btn-primary {
    color: #fff !important ;
    background-color: rgb(130,169,224) !important;
    border-color: rgb(130,169,224) !important;
}
.btn:hover, .btn:focus {
     color: #fff !important ;
    background-color:rgb(20, 99, 212) !important;
    border-color: rgb(20, 99, 212) !important;
    text-decoration: none;
}
.pull-right {
    float: right !important;
}
.login-button
{
	border-color: #077131 !important;
    background: linear-gradient(#4fba7c, #1e472f) !important;
    color: #fff !important;
    border-radius: 15px !important ;
    height: 55px !important;
}
</style>
<script type="text/javascript" >
$("input").keypress(function(event) {
    if (event.which == 13) {
        event.preventDefault();
        fn_validateUser();
        return false;  
    }
});

</script>
<script type="text/javascript" >
function fn_validateUser()
{
	var loginName = document.loginForm.username.value;
	var password = document.loginForm.password.value;
	if(loginName == "")
	 {
	  document.getElementById('usernameAlert').style.display="block";
	  document.loginForm.username.focus();
	 return false;
	 }
	if(password == "")
	 {	
	  document.getElementById('passwordAlert').style.display="block";
	  //alert("Please Enter Password");
	  document.loginForm.password.focus();
	 return;
	 }
	document.forms[0].username.focus();
	//var url='/OperationsAP/loginAction.do?actionFlag=checkLoginOnload';  
	//window.open(url,'EmployeeHealthCareFund','toolbar=no,resizable=no,scrollbars=no,menubar=no,location=no,height=0,width=0,left=200,top=130');
	document.forms[0].action = "loginAction.do?actionFlag=checkLogin"; 
    document.forms[0].submit(); 
}
function capLock(e){
	 kc = e.keyCode?e.keyCode:e.which;
	 sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);
	 if(((kc >= 65 && kc <= 90) && !sk)||((kc >= 97 && kc <= 122) && sk))
		 if(document.getElementById('caps_lock'))
		 document.getElementById('caps_lock').style.display = 'block';
	 else
		 {
			 if(document.getElementById('caps_lock'))
				 document.getElementById('caps_lock').style.display = 'none';
		 }
	}
function fnValidate(event)
{
	capLock(event);
	//if (event.which == 9 || event.keyCode == 9) { 	
		//fn_validateUser();
		 //       }
  //var keyCode = document.layers ? event.which : event.keyCode;  
	if (event.which == 13 || event.keyCode == 13) { 
	fn_validateUser();
	        }
}
function fn_onloadFocus()
{
document.getElementById("username").focus();
}	
function fn_resetUser(){
document.loginForm.username.value="";
document.loginForm.password.value="";

}
function fn_resetUser1(event){
if (event.which == 13 || event.keyCode == 13) { 
document.loginForm.username.value="";
document.loginForm.password.value="";
}
}
function close(){
	document.getElementById('usernameAlert').style.display="none";
}
function close1(){
	document.getElementById('passwordAlert').style.display="none";
}
function fn_changePassword() {
	var userName = '${userName}';
	document.getElementById("changePasswordDiv").src='loginAction.do?actionFlag=ChangePwd';
}
function forgotPassword()
	{
		var url="http://www.ehf.telangana.gov.in/HomePage/ChangePwdReq.do?actionFlag=forgotPassword";
		window.open(url,"",'width=700,height=400,resizable=yes,top=175,left=175,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	}
/* function getTokens()
{
	var url="tokenAction.do?actionFlag=showTokenNumbers&hospId=DISP1";
	window.open(url,"",'width=700,height=400,resizable=yes,top=175,left=175,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
} */
</script>
</head>
<body onload="fn_onloadFocus()" style="height: 100%;">
<!--Alerts-->

<div class="alert alert-danger" role="alert" id="usernameAlert" style="display:none;">
Please Enter User Name
<p align="center"><a class="btn btn-danger btn-xs" role="button" href="javascript:close()">OK</a></p></div>
<div class="alert alert-danger" role="alert" id="passwordAlert" style="display:none;">
Please Enter Password
<p align="center"><a class="btn btn-danger btn-xs" role="button" href="javascript:close1()">OK</a></p></div>
<html:form  method="post"  action="/loginAction.do" > 
<div class="container">
<logic:notEmpty name="loginForm" property="msg">
   <script type="text/javascript" language="javascript">
   alert('<bean:write name="loginForm" property="msg"/>');
   //window.close();
   </script>
</logic:notEmpty>
<div class="container login">
	<div class="moduleName">
  	<h1>Employees & Journalists Health Scheme</h1>
  	<img src="images/tgLogo.png" alt="TG LOGO" width="50%">
  <h2>Operations NABH Super speciality Hospitals</h2>
	</div>
	
	<div class="form-group ">
     <!--  <label for="username">Username:</label> -->
     <div class="user-login-info">
      <input type="text" name="username" id="username" class="form-control usernamepwd" title="User name" placeholder="User Name"/>
     <!--  <label for="password">Password:</label> -->
      <input type="password" name="password" id="password" class="form-control usernamepwd" title="Password" placeholder="Password" onkeypress="javascript:fnValidate(event)"/>
     </div>
     
		  <div class="pull-right">
		  	<a align="center"   href="javascript:forgotPassword()"  title="Click to get new Password" >Forgot Password?</a>
		  </div>
		  
		   <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 " align="center">
	      
	      <a class="btn btn-primary" id="login-submit" tabindex="0" href="javascript:fn_validateUser();" title="Login" onkeypress="javascript:fnValidate(event)" role="button">Login</a>
		 
		  
	      &nbsp;&nbsp;
		  <a class="btn btn-primary" id="reset" tabindex="0" href="#" title="Reset" onclick="javascript:fn_resetUser()" onkeypress="javascript:fn_resetUser1(event)" role="button">Reset</a>
		  </div>

<!-- 
		   <a align="center" href="javascript:getTokens()"  title="Click to get Token Numbers" style="text-decoration:none">Tokens </a>
		  -->

	 	</div> 

	<html:hidden name="loginForm" property="meeLogin" value="${meeLogin}"/>
	<html:hidden name="loginForm" property="meePassword" value="${meePwd}"/>
	<html:hidden name="loginForm" property="meeReqId" value="${meeReqId}"/>
</div>
<!-- START FOR FORGOT PASSWORD POPUP -->
<div class="modal fade bs-example-modal-md" id="forgotPassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog modal-md">
    <div class="modal-content" >
    <ul class="nav nav-tabs" id="forgtpwdtabs" role="tablist">
  <li id="#ftab1" class="active disabled" style="cursor:no-drop"><a href="" role="tab" data-toggle="tab">Instructions</a></li>
  <li id="#ftab2" class="disabled"><a href="" role="tab" data-toggle="tab">Step1</a></li>
  <li  id="#ftab3" class="disabled"><a role="tab" href="" data-toggle="tab">Step2</a></li>
</ul>

<!-- Tab panes -->
<div class="tab-content bg-primary"   >
   
  	<div id="fdiv1" class="tab-pane active bg-primary" style="padding:3%;" >		
			<h5><span class="step">1. Enter User ID and proceed</span></h5>
			
			<h5><span class="step">2. Password will be sent to registered mobile.</span></h5>
			<br/>
			<button type="button" class="btn btn-default" align="center" onclick="javascript:fn_proceedNext('ftab2');" >Click here to Proceed</button>
		</div>
	<div id="fdiv2" class="tab-pane" style="padding:3%"><br />
		<table style="border:0;width:auto !important;">
		<tr style="height:10px;">
		<td colspan="3"><span class="step">1. Enter User ID and proceed</span></td>
		</tr>
		<tr>
		<td>Enter UserID:</td>
		<td><input  tabindex="1" type="text" name="userID" id="userID"  placeholder="Enter user id" style="color:#000"  maxlength="20"  title="Enter user id"  ></input></td>
		<td></td>
		</tr>
		<tr>
		<td>Login as:</td>
		<td><select  tabindex="2" id="loginAs" name="loginType" style="color:#000"    title="Select login type">
            <option value="-1">-------Login as-------</option>
            <option value="Hospital">Hospital</option>
            <option value="Trust">Trust</option>
            
		</select></td>
		<td><button type="button" class="btn btn-default" onclick="javascript:fn_proceedFinalGo('ftab3');"  style="height:25px;" id="goButton" title="Click here to proceed">Go</button></td>
		</tr>
		
		</table>
		
	<br />
		</div>
		<div id="fdiv3" class="tab-pane" style="padding:3%"><br />	
			<h4><span class="step" id="passwordSentMsg"> </span></h4>
		</div>
	</div>
<div class="modal-footer bg-primary">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       </div>
</div>
     
    
    </div>
  </div>
  <script>
  
  function fn_proceedNext(id){
		
		if(id=="ftab2"){
		$("#fdiv1").removeClass('active');
		$("#fdiv2").addClass('active');
		
		var node = document.getElementById("forgotPassword").getElementsByTagName("li")[1];
		node.setAttribute("class", "active");
		node = document.getElementById("forgotPassword").getElementsByTagName("li")[0];
		node.setAttribute("class", "");
		fn_proceed1();
		}
		else{
			
			$("#fdiv2").removeClass('active');
			$("#fdiv3").addClass('active');
			
			var node = document.getElementById("forgotPassword").getElementsByTagName("li")[2];
			
			node.setAttribute("class", "active");
			node = document.getElementById("forgotPassword").getElementsByTagName("li")[1];
			node.setAttribute("class", "");
			

		}
		
		return false;
	}

	function fn_proceed1(){
		 setTimeout(function() {
		      document.getElementById('userID').focus();
		    }, 5);
	}
	function  saveDataAndReset(url){
		document.getElementById("goButton").style.display="none";
		var xmlhttp;
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
	    xmlhttp.onreadystatechange=function()
	    {
	    if(xmlhttp.readyState==4)
	    {
	    	document.getElementById("goButton").style.display="";
	    	var responseData=xmlhttp.responseText.split("@@");
	    	
	    	if(responseData[0]=="true")
	    	{
		    	
	    		if(responseData[1]==null || responseData[1]=='')
		    	{
	    			
	    			document.getElementById("passwordSentMsg").innerHTML="Password has been reset but unable to send message.Please contact admin";
		    	}
	    		else{
		    		
	    			var contactInfo=responseData[1].split("##");
	    			var status;
	    			if(contactInfo[0]!=null)
	    				contactInfo[0]=trim(contactInfo[0]);
	    			if(contactInfo[1]!=null)
	    				contactInfo[1]=trim(contactInfo[1]);
					if(contactInfo[0]!=null && contactInfo[0]!='' && contactInfo[1]!=null && contactInfo[1]!='')
						status="Password has been sent to-"+contactInfo[0]+", "+contactInfo[1] ;
					else if(contactInfo[0]!=null && contactInfo[0]!='')
						status="Password has been sent to "+contactInfo[0];
					else
						status="Password has been sent to "+contactInfo[1];
	    			document.getElementById("passwordSentMsg").innerHTML=status;
	    			}
	    			fn_proceedNext('ftab3');
	    	
	    		}
	    		else
	    		{
		    		
	    			var result=xmlhttp.responseText.substring(0,9);
	    			alert(result);
	    			if(result=="noPhoneNo"){
	    			alert("Please Update Your Details");
	    			document.getElementById("userID").readonly="true";  
	    			document.getElementById("setNumber").style.display="";
	    			alert(xmlhttp.responseText.substring(10,xmlhttp.responseText.length));
	    			document.getElementById("empName").innerHTML=xmlhttp.responseText.substring(10,xmlhttp.responseText.length);
	    			document.getElementById("empLoginNo").innerHTML=document.getElementById("userID").value;

	    			}
	    			else{
	    				alert(xmlhttp.responseText);
	    				fn_proceedNext();
	    			}
	    		
	    		}

	    }			
	    }

		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	
	}
	function fn_proceed2(){
		setTimeout(function() {
		      document.getElementById('loginAs').focus();
		    }, 5);
	}

	function fn_proceedFinalGo(id){

		if(document.getElementById('userID').value.length<1)
		{
			alert("Enter User-ID");
			fn_proceed1();
		}
		else if (document.getElementById('loginAs').value=='-1')
		{
			alert("Select login type");
			fn_proceed2();
		}
		else{
			var url =  'ChangePwdReq.do?actionFlag=sendPassword&userId='+document.getElementById('userID').value+'&loginType='+document.getElementById('loginAs').value;
			saveDataAndReset(url);
	    }
	}
	function trim (str)
	{
		return str.replace (/^\s+|\s+$/g, '');
	}
	  
  </script>
  </div>	
	</html:form>
	
</body>
</html>