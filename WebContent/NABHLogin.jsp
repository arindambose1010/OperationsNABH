<!DOCTYPE html>
<!-- saved from url=(0045)http://bucketadmin.themebucket.net/login.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <meta charset="utf-8">
 <%@ include file="/common/include.jsp"%>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="TeamAarogyasri">
    <link rel="shortcut icon" href="images/favicon.ico" >
<link rel="icon" type="images/ico" href="images/favicon.ico">
<link rel="icon" href="images/aplogo.png" sizes="16x16">

    <title>Login</title>

   <!--Core CSS -->
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
.modal-header{background:#1fb5ad;color:#fff;}
.close-thik:after {
  content: 'X'; /* UTF-8 symbol */
  color:white;
}
[class*='close-'] {
  color: #777;
  font: 14px/100% arial, sans-serif,bold;
  position: absolute;
  right: 5px;
  text-decoration: none;
  text-shadow: 0 1px 0 #fff;
  top: 5px;
  font-weight:bold
}
</style>
    <!-- Just for debugging purposes. Don't actually copy this line! -->
    <!--[if lt IE 9]>
    <script src="js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script>
    var floginName;
    $(document).ready(function() {
   	 
   	 /* Initialize your widget via javascript as follows */
   	
   	
    /*   $('#loginFormNew').formValidation({
      	framework: 'bootstrap',
          icon: {
              valid: 'glyphicon glyphicon-ok',
              invalid: 'glyphicon glyphicon-remove',
              validating: 'glyphicon glyphicon-refresh'
          },
          fields: {
              otp: {
                  validators: {
                      notEmpty: {
                          message: 'OTP is required'
                      },
                      identical: {
                          field: 'otpTemp',
                          message: 'OTP is not matched with the sent one.Please check'
                      }
                  }
              } }
       }); */
   });
    function fn_validateUser()
{
	var loginName = document.loginForm.username.value;
	var password = document.loginForm.password.value;
	if(loginName == "")
	 {
		$("#errorMsg3").show();
		$("#errorMsg3").html('* Please Enter Login Name');
	 	return false;
	 }
	if(password == "")
	 {	
		$("#errorMsg3").show();
		$("#errorMsg3").html('* Please Enter Password');
	  return false;
	 }
	if(loginName!="" && password!="")
		{
	document.forms[0].action = "loginAction.do?actionFlag=checkLogin";  
    document.forms[0].submit();
    document.getElementById("signInBtn").disabled = true;
		}
}

    function fnValidate(event)
    {
    	$("#errorMsg3").hide();
		$("#errorMsg3").val('');
    	if (event.which == 13 || event.keyCode == 13) { 
    	fn_validateUser();
    	        }
     
    }
    function fnValidatePass(event)
    {
    	$("#errorMsg1").hide();
		$("#errorMsg1").val('');
		 $("#correctImg").hide();
		 $("#wrongImg").hide();
    	if (event.which == 13 || event.keyCode == 13) { 
    		fn_getOTP();
    	        }
     
    }
    function fnValidateNewPass(event)
    {
    	$("#errorMsg").hide();
		$("#errorMsg").val('');
		if (event.which == 13 || event.keyCode == 13) { 
    		//fn_getOTP();
    		fn_validateNewPassword();
    	        }
     
    }
    function fn_validateNewPassword(){
    	var newPass = document.loginForm.newpass.value;
    	var cPass = document.loginForm.cPassword.value;
    	if(newPass == "")
	   	 {
	   	   	$("#errorMsg").show();
			$("#errorMsg").html('* Please Enter Password');
	   	   return false;
	   	 }
    	if(validateChangePassword(newPass)){
		    if(cPass == "")
		   	 {
		    	$("#errorMsg").show();
				$("#errorMsg").html('* Please Enter Confirm Password');
		   	   return false;
		   	 }
		    if(newPass == cPass){
		    	var urlUpd = 'loginAction.do?actionFlag=updatePWD';
		    	$("#updateDivMsg").empty();
		    	$("#buttonsDiv").hide();
		    	$("#buttonsDiv1").hide();
		    	$.post(urlUpd,{'loginName':floginName,'password':newPass},function(data){
		    		if(data.trim()=="SessionExpired*"){
		    			$("#updateDivMsg").html("Session has been expired");
		               }
		               else
		               {
		            	   if(data.trim()=="success"){
		            		   $("#updateDivMsg").html('<font style="color:green;font-weight:bold">Password has been updated successfully and an SMS has been sent to registered Mobile. Please wait while we redirect you to login page.');
		            	   }
		            	   if(data.trim()=="fail"){
		            		   $("#updateDivMsg").html('<font style="color:red;font-weight:bold">An error occured while updating the password and sending through SMS. Please try again after some time</font>');
		            	   }
		            	   if(data.trim()=="noData"){
		            		   $("#updateDivMsg").html('<font style="color:red;font-weight:bold">Mobile number has not been updated. Please contact Administrator</font>');
		            	   }
		            	   
		               }  
		    			setTimeout(function() {
			    		   window.location.href = "#";
			    		 }, 3000);
		          },'text');
		   			
		    } else{
		    	$("#errorMsg").show();
				$("#errorMsg").html('* Confirm Password does not match with the Password');
		    	document.loginForm.cPassword.value = "";
		    	document.loginForm.cPassword.focus();
		    }
    	}
	    return false;
    }
    function fn_getOTP(){
    	var logName = document.loginForm.logName.value;
    	if(logName == "")
	   	 {
	   	   	$("#errorMsg1").show();
			$("#errorMsg1").html('* Please Enter Login Name');
	   	   return false;
	   	 }
	   	if(logName!="")
	   	{
	   		generateOtp();
	   		document.getElementById("myModal").style.display = "none";
	   		document.getElementById("otpDiv").style.display="block";
    		document.getElementById("otpLabel").style.display="block";
    		document.getElementById("buttons").style.display="block";
	    }
    }
    function fn_enableForgotDIV(){
    	document.getElementById("myModal").style.display = "";
    	document.loginForm.logName.value = "";
    	document.getElementById("loginForm").style.display = "none";
    }
    function fn_enableLoginForm(){
    	document.getElementById("myModal").style.display = "none";
    	document.getElementById("loginForm").style.display = "";
    }
 
    function generateOtp(){
		var logName = document.loginForm.logName.value;
		floginName = logName;
		var xmlhttp;var url;
	    if (window.XMLHttpRequest)
	    {
	    	xmlhttp=new XMLHttpRequest();
	   	}
		else if (window.ActiveXObject)
		{
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
   		else{
   			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
   		}
        xmlhttp.onreadystatechange=function()
       {
           if(xmlhttp.readyState==4)
           {
               var resultArray=xmlhttp.responseText;
               
               if(resultArray.trim()=="SessionExpired*"){
               	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
               }
               else
               {
               	if(resultArray!=null)
               	{
               	 	otp=resultArray;     
               	 	$("#errorMsg2").hide();
           	  		$("#errorMsg2").html('');
                   	if(otp.length>0)
                   	{  
                   	  if(otp=="NA"){
                   		document.getElementById('otpNA').style.display="block";
                   		document.getElementById("otpLabel").style.display="none";
                		document.getElementById("validate").style.display="none";
                		document.getElementById("cancel").style.display="none";
                		document.getElementById('back').style.display="block";
                		document.getElementById('otpValue').style.display="none";
                		document.getElementById('head').style.display="none";
                   	  }else{	
	                      document.forms[0].otpTemp.value=otp;
	                      document.forms[0].otp.value=''; 
	                	  document.getElementById('otpSent').style.display="block";
	                	  document.getElementById("validate").style.display="";
	                	  document.getElementById("cancel").style.display="";
	                	  document.getElementById('back').style.display="none";
	                	  document.getElementById('otpValue').style.display="";
	                		document.getElementById('head').style.display="";
                   	  } 
                   }
                   if(otp.length==0)
                   	{  
                   	 document.getElementById('otpFail').style.display="block";
                   	}
               	}
           	}
           }
       }

    	url = 'loginAction.do?actionFlag=generateOTPForCEO&callType=Ajax&loginName='+logName;
    	
    	xmlhttp.open("Post",url,true);
    	xmlhttp.send(null);
    	
    }
    function fn_resetUser(){
    	document.loginForm.username.value="";
    	document.loginForm.password.value="";
    	document.loginForm.otp.value="";
    	document.loginForm.logName.value="";
    	$("#myModal").hide();
    	$("#loginForm").show();
    	$("#myModal").hide();
   		$("#otpDiv").hide();
		$("#otpLabel").hide();
		$("#otpSent").hide();
		$("#buttons").hide();
		$("#otpFail").hide();
		$("#otpNA").hide();
		$("#passDiv").hide();
		return false;
    }
    var otp=null;
    function fn_loginUser()
    {
    	var otpGenerated=otp;
	    var otpEntered=document.forms[0].otp.value;
	    if(otpEntered==null || otpEntered=="")
	    {
	    	$("#errorMsg2").show();
	    	$("#errorMsg2").html('* Please enter OTP sent to your registered mobile number');
	    	 document.getElementById("otpValue").focus();
		    return false;
	    }
	    if(otpEntered!=otpGenerated)
	    {
	    	$("#errorMsg2").show();
	    	$("#errorMsg2").html('* Entered OTP does not match. Please re-enter OTP');
	    	 document.getElementById("otpValue").value="";
	    	 document.getElementById("otpValue").focus();
	    	return false ;
	    }
	    else
	    {
	    	$("#passDiv").show();
	    	document.loginForm.newpass.value = "";
	    	document.loginForm.cPassword.value = "";
	    	$("#loginForm").hide();
	    	$("#myModal").hide();
	   		$("#otpDiv").hide();
			$("#otpLabel").hide();
			$("#otpSent").hide();
			$("#buttons").hide();
			$("#otpFail").hide();
			$("#otpNA").hide();
	    }
 }
 
    function fn_onloadFocus()
    {
    document.getElementById("username").focus();
    }	
</script>
</head>
  <body class="login-body" style="" onload="fn_onloadFocus();">
    <div class="container form-signin">
    
<html:form  method="post"  styleId="loginFormNew" action="/loginAction.do">
     <logic:notEmpty name="loginForm" property="msg" >
   <script type="text/javascript">
   alert('<bean:write name="loginForm" property="msg"/>');
   window.close();
   </script>
   </logic:notEmpty>
      
        <h2 class="form-signin-heading"><img src="images/tgLogo.png" alt="TG LOGO" width="80%"></h2>
        <div class="login-wrap" id="loginForm">
            <div class="user-login-info">
              <input type="text" class="form-control" placeholder="Login Name" id="username" name="username"  onkeypress="javascript:fnValidate(event)">
                <input type="password" class="form-control" placeholder="Password" id="password" name="password" onkeypress="javascript:fnValidate(event)">
                 <label id="errorMsg3" style="display:none;color:red;font-weight:bold"></label>
            </div>
            <label class="checkbox">
                <span class="pull-right">
                  <a  href="javascript:fn_enableForgotDIV();"> Forgot Password?</a>
                </span><br/>
            </label>
            <button class="btn btn-lg btn-login btn-block" onclick="javascript:fn_validateUser()" type="button" id="signInBtn" onkeypress="javascript:fnValidate(event)"> Sign in</button>
        </div>
          <!-- Modal -->
          <div tabindex="-1" id="myModal" style="display:none;" >
                  <div class="modal-content">
                      <div class="modal-header">
                          <h4 class="modal-title">Forgot Password ?</h4>
                      </div>
                      <div class="modal-body">
                          <p>Enter your Login Name below to reset your password.</p>
                          <input type="text" name="logName" placeholder="Login Name" autocomplete="off" class="form-control placeholder-no-fix" onkeypress="javascript:fnValidatePass(event)">
                          <label id="errorMsg1" style="display:none;color:red;font-weight:bold"></label>
                      </div>
                      <div class="modal-footer" style="text-align:center">
                          <button class="btn btn-round btn-primary" onclick="javascrip:fn_getOTP();" onkeypress="javascript:fnValidatePass(event)" type="button">Submit</button>
                          <button  onclick="javascrip:fn_enableLoginForm();" class="btn btn-round btn-danger"  type="button">Cancel</button>
                          
                      </div>
                  </div>
           </div>
        
          <!-- modal -->
         <!-- OTP -->
         <div  id="otpDiv" style="display:none;" >
         	 <div class="modal-content">
                      <div class="modal-header" id="head">
                          <h4 class="modal-title">
                             <label  id="otpLabel" style="display:none" > Enter OTP here <span class="glyphicon glyphicon-circle-arrow-down"></span>	</label>
                             </h4>
                        </div>
                        <div class="modal-body">    
                         <table> 
							<tr><td style="width:150px"><html:text name="loginForm"    styleClass="form-control" property="otp" onkeyup="javascript:removeErrorDiv(event)" title="enter OTP Here "   styleId="otpValue"  style="width:150px" maxlength="12"></html:text></td>
								<td>&nbsp;&nbsp;<span class=" fa   fa-check text-success text-active" id="correctImg" style="display:none"></span><span class="fa  fa-times text-danger text" id="wrongImg" style="display:none"></span></td>
							</tr>
							<tr>
							 <td colspan="2">
								<label  id="otpLabel2" style="display:none" > 
								<html:text name="loginForm"    styleClass="form-control" property="otpTemp" title="OTPTemp"  styleId="otpValue2"  maxlength="8"></html:text>
							 </label>
								<label  id="otpSent" style="display:none" ><i><font color="#317531">An OTP has been successfully generated and  sent to your Registered Mobile number</font></i>
							    </label>
							
								<label  id="otpFail" style="display:none" ><i><font color="#317531">An error occured while generating OTP.Please Try Again</font></i>
							    </label>
							    <label  id="otpNA" style="display:none" ><i><font color="#317531">Invalid Login Name</font></i>
							    </label>
							     <label id="errorMsg2" style="display:none;color:red;font-weight:bold"></label>
							     </td>
							     </tr>
					   </table>
					</div>		    
			   		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" align="center" style="display:none;" id="buttons">
			   		<br/>
			   		<div class="modal-footer" style="text-align:center">
					         <button type="button" class="btn btn-round btn-primary" value="Submit"  onclick="javascript:fn_loginUser();" align="center" id="validate" style="display:none">Validate</button>
					         <button type="button" class="btn btn-round btn-danger"  value="Reset" onclick="fn_resetUser();" align="center" id="cancel" style="display:none">Cancel</button>
					          <button type="button" class="btn btn-round btn-danger"  value="Back" onclick="fn_resetUser();" align="center" id="back" style="display:none" >Back</button>
					         <br/><br/><br/>
					        </div> 
					   </div>
					   </div>
			</div>
         <!-- OTP -->
         <!-- Forgot Password -->
          <div  id="passDiv" style="display:none;" >
           <div class="modal-content">
                      <div class="modal-header">
                          <h4 class="modal-title">Change Password</h4> 
                       </div>
            <div class="modal-body">    
                <input type="password" class="form-control" placeholder="Password" id="newpass" maxlength="8" name="newpass"  onkeypress="javascript:fnValidateNewPass(event)">
                <input type="password" class="form-control" placeholder="Confirm Password" maxlength="8" id="cPassword" name="cPassword" onkeypress="javascript:fnValidateNewPass(event)">
                <label id="errorMsg" style="display:none;color:red;font-weight:bold"></label>
            </div>
            <div class="modal-footer" style="text-align:center" id="updateDivMsg" >
            <button class="btn btn-round btn-primary" onclick="javascript:fn_validateNewPassword()" id=buttonsDiv" type="button" id="updtBtn" onkeypress="javascript:fnValidateNewPass(event)" style="color:white"> Update</button>
            <button type="button" class="btn btn-round btn-danger"  value="Back" onclick="fn_resetUser();" id=buttonsDiv1" align="center" >Cancel</button>
            &nbsp;&nbsp;<!-- <a href="javascript:fn_enablePassPolicy()" style="align:right" id="Policy">Password Policy</a><br/><br/> -->
            <div  id="passwordPolicy" style="display:none;" >
           			<div class="modal-content">
                      <div class="modal-header">
                          <h4 class="modal-title">Password Policy<a href="javascript:fn_disablePolicy();" class="close-thik"></a></h4> 
                       </div>
            <div class="modal-body" style="text-align:left">  Your Password Should Have:<br/>  
             <!--   * 8 characters only<br/> -->
               * Minimum 6 alphanumeric characters<br/>
               * At least one Character [A-Z]/[a-z].<br/>
			   * At least one Number (0-9).<br/>
			   * At least one Special Character out of these('! @ # $ ^ & * ~') etc.<br/>
			</div>	
				</div>
           </div> 
        </div>
        </div>
        </div>
         <!-- Forgot Password -->
         <br/>
      </html:form>

    </div>



    <!-- Placed js at the end of the document so the pages load faster -->

    <!--Core js-->
 	<script src="CEO/ceoFilesNew/jquery.js"></script>
    <script src="CEO/ceoFilesNew/bootstrap.min.js"></script>

  <script>
  function fn_disablePolicy(){
	  $("#passwordPolicy").hide();
	  $("#Policy").show();
  }
  function fn_enablePassPolicy(){
	  $("#passwordPolicy").show();
	  $("#Policy").hide();
  }
  function validateChangePassword(val)
  {
	 if(val == "")
	  {
        $("#errorMsg").show();
		$("#errorMsg").html('* Password cannot be empty');
        clearAllPasswords();
        return false;
	  }
	  if(val.length<8)
	  {
		  $("#errorMsg").show();
		  $("#errorMsg").html('* Password should be Eight characters');
		//$("#errorMsg").html('* Password should contain atleast one numeric digit');
		  clearAllPasswords();
	      return false;
      }
	  /* ret1 = checkForNumber(val);
	  if(!ret1)
	  {
		$("#errorMsg").show();
		$("#errorMsg").html('* Password should contain atleast one numeric digit');
        clearAllPasswords();
        return false;		
	  }
	  ret2 = checkForSpecialChrs(val);
	  if(!ret2)
	  {
        $("#errorMsg").show();
		$("#errorMsg").html('* Password should contain atleast one special character');
        clearAllPasswords();
        return false;		
      }
	  ret3 = checkForChrs(val);
	  if(!ret3)
	  {
		$("#errorMsg").show();
		$("#errorMsg").html('* Password should contain atleast one alphabet');
        clearAllPasswords();
        return false;		
	  } */
	  ret4=checkForSpace(val);
	  if(!ret4)
	  {
        $("#errorMsg").show();
		$("#errorMsg").html('* Password should not contain spaces');
        clearAllPasswords();
        return false;
	  }  
   return true;
  }
  function fn_changePswd(){
      document.forms[0].action="/EHS/ChangePwdReq.do?";
      document.forms[0].requestType.value;
       document.forms[0].submit(); 
  }
  function checkForNumber(val)
  {
  	var i;
  	for (i = 0; i < val.length; i++) 
        	{ 
              ls_char1 = val.charAt(i); 
              if(ls_char1 >= '0' && ls_char1 <= '9')
                  return true;
  	}
  	return false ;
  }
  function checkForSpecialChrs(val)
  {
  	var i;
          var splCharStr="!@#$%^&*()<>,./?\|[]{}'~`+=_";
  	for (i = 0; i < val.length; i++) 
        	{ 
              ls_char1 = val.charAt(i);
              if(splCharStr.indexOf(ls_char1) >= 0)
                      return true;
  	}
  	return false ;
  }
  function checkForChrs(val)
  {
  	var i;
          var splCharStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  	for (i = 0; i < val.length; i++) 
        	{ 
  	    ls_char1 = val.charAt(i);
              if(splCharStr.indexOf(ls_char1) >= 0)
  		return true;
  	}
  	return false ;
  }
  function checkForSpace(val)
  {
       var i;
       for(i =0 ; i < val.length;i++)
       {
            char=val.charAt(i);
            if(char==" ")
              return false;
       }
       return true;
  }    
  function clearAllPasswords()
  {
        document.forms[0].newpass.value = "";
        document.forms[0].cPassword.value = "";
  }
  function removeErrorDiv(){
	  $("#errorMsg2").hide();
	  $("#errorMsg2").html('');
	  var otpGenerated=otp;
	  var otpEntered=document.forms[0].otp.value;
      if(otpGenerated!=null && otpGenerated!='' && otpEntered!=null && otpEntered!=''){
    	  if(otpEntered.length==8){
	    	  if(otpGenerated==otpEntered){
	    		  $("#correctImg").show();
	    		  $("#wrongImg").hide();
	    	  } 
	    	  else{
	    		  $("#correctImg").hide();
	    		  $("#wrongImg").show();
	    	  }  
    	  }else if(otpEntered.length<8 && otpEntered.length>1){
    		  $("#correctImg").hide();
    		  $("#wrongImg").show();
    	  }	 else{
    		  $("#correctImg").hide();
    		  $("#wrongImg").hide();
    	   } 
   
      }
	  
  }
  </script>
  

</body></html>