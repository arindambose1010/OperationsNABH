<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
      <%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:setLocale value='en_US'/>


<html>
<head>

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/navyblue/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">
<script src="js/DateTimePicker.js"></script>
	
<script  src="bootstrap/js/modernizr.min.js"></script>
<script  src="bootstrap/js/css3-mediaqueries.js"></script> 
<script  src="bootstrap/js/html5.js"></script>
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<%@ include file="/common/includeBootstrapCalendar.jsp"%>
   
<style type="text/css">
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
body{font-family:Arial, Helvetica, sans-serif;font-size:13px;color:#333;}
  #remarksDiv {
width:100%;
height:540px;
overflow:auto;
background:white;
}
table.center {
    margin-left:auto; 
    margin-right:auto;
  }
  
  .tbcellBorder2{
  margin:2px; 
  padding:8px;
  border-right:2px solid #ffff;
  } 
  .modal-dialog{
width:950px;
}

.tdclass2{
word-break: break-word; 

}

.newDivClass
 {
 	margin-top:5px;
 	
 }

.but {
	background-color:<%=themeColour%>  !important;
}
 .but{
	border-radius:16px;
}
.form-group{
margin-bottom:5px !important;
}

.text-center
{
    text-align: center !important;
}
.ui-autocomplete-input {
    width: 15em;
}

</style>

<script>


var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

$(document).ready(function() {
	
								$('#createEmployeeForm').find('[name="deptName"]').change(function (e){
									$('#createEmployeeForm').formValidation('revalidateField','deptName');	
								}).end();
								
								$('#createEmployeeForm').find('[name="deptName"]').change(function (e){
									$('#createEmployeeForm').formValidation('revalidateField','vacPosName');	
								}).end();
	
							 	$('#empDob').datepicker({
	 						 	defaultDate: "+1w",
							 	endDate: new Date(y, m, d),
								todayHighlight: true,
								autoclose: true,
								format:'dd-mm-yyyy'
     							})
     							.on('changeDate', function(e) {
         						// Revalidate the date field
         						$('#createEmployeeForm').formValidation('revalidateField', 'empDob');
    							 });
	
	 							$('#empDoj').datepicker({
		 						defaultDate: "+1w",
								endDate: new Date(y, m, d),
								todayHighlight: true,
								autoclose: true,
								format:'dd-mm-yyyy'
	     						})
	     						.on('changeDate', function(e) {
	         					// Revalidate the date field
	        					 $('#createEmployeeForm').formValidation('revalidateField', 'empDoj');
	     						});
	 
	
    							$('#createEmployeeForm').formValidation({
    							framework: 'bootstrap',
        						icon: {
            							valid: 'glyphicon glyphicon-ok',
            							invalid: 'glyphicon glyphicon-remove',
           								validating: 'glyphicon glyphicon-refresh'
        							},
        						fields: {
        							fName: {
                						validators: {
                    						notEmpty: {
                       						 message: 'The first name is required'
                    						},
                    						regexp: {
                    							regexp: /^[a-zA-Z. ]+$/,
                        					message: 'The first name can only consist of alphabets'
                    						},
                    						/*  regexp:{
                    							regexp:/^([^._]|[.](?=[^.]|$))*$/,
                    							message: 'Consecutive special character(.) are not allowed'
                    						}  */
                    					
                						}
            						},
            						lName: {
            			                validators: {
            			                    notEmpty: {
            			                        message: 'The last name is required'
            			                    },
            			                    regexp: {

            			                      


            			                        regexp: /^[a-zA-Z. ]+$/,



            			                        message: 'The last name can only consist of alphabets'
            			                    },
            			           	 		different: {
            			                	field: 'fName',
            			                	message: 'The last name cannot be the same as firstname'
            			            		},
                    						/* regexp:{
                    							regexp:/^([^._]|[.](?=[^.]|$))*$/,
                    							message: 'Consecutive special character(.) are not allowed'
                    						} */
            			                }
            			            },
            			            gender: {
            			                validators: {
            			                    notEmpty: {
            			                        message: 'The gender is required'
            			                    }
            			                }
            			            },
            			            empNumber: {
            		            		validators: {
            		            			notEmpty:	{
            		            				message: 'Employee Number is required'
            		            			},
            		            			regexp:	{

            		            				


            		            				regexp: /^(?=.*[A-Za-z])(?=.*\d)(?!.*[-_]{2}).+[A-Za-z0-9-_]+$/,



            		            				message: 'Employee Number should be apha numeric.(-_ can be used)'
            		            				},
            		            				/* regexp:{
                        							regexp:/^([^-_]|[-](?=[^-]|$)|_(?=[^_]|$))*$/,
                        							message: 'Employee number should have atleast one alpha numeric'
                        						} */
            		            		}
            		            },
            		            loginName: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The login name name is required'
            		                    },
            		                    regexp: {

            		                     

            		                    	
            		                        regexp: /^(?=.*[A-Za-z])(?=.*\d)(?!.*[-_]{2}).+[A-Za-z0-9-_]+$/,



            		                        message: 'The login name should be apha numeric.(-_ can be used)'
            		                    },
            		                    /* regexp:{
                							regexp:/^([^-_]|[-](?=[^-]|$)|_(?=[^_]|$))*$/,
                							message: 'Login name should have atleast one alpha numeric'
                						} */
            		                    
            		                }
            		            },
            		            empDob: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The date of birth is required'
            		                    }
            		                   
            		                }
            		            },
            		            empDoj: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The date of joining is required'
            		                    }
            		                   
            		                }
            		            },
            		            empAddress: {
            		        		validators: {
            		        			notEmpty:	{
            		        				message: 'Employee Address is required'
            		        			},
            		        			
            		        			regexp:	{
            		        				regexp:/^[a-zA-Z0-9-_, ]+$/,
            		        				message: 'Address includes alpha Numerics and -,_'
            		        			},
            		        		} 
            		        	},
            		        	postalCode: {
            		        		validators: {
            		        			notEmpty: {
            		        				message: 'Postal Code should not be empty'
            		        			},
            		        			regexp:	{
            		        				regexp: /^[0-9]+$/,
            		        				message: 'Postal Code can only consists of numbers'
            		        			},
            		        			regexp: {
            		        				 regexp: /^\d{6}$/,
            		        				 message: 'Postal Code should contain only six digits'
            		        			}
            		        		}
            		        	},
            		        	mobNumber :{
            		            	validators: {
            		                    notEmpty: {
            		                        message: 'The mobile number is required'
            		                    },
            		                    number: {
            		                        message: 'The input is not a valid mobile number'
            		                    },
            		                    regexp:	{
        		            				regexp: /^[0-9]+$/,
        		            				message: 'Mobile number can consist of only numbers'
        		            				},
        		            				regexp:	{
	        		            				regexp:/^[789]\d{9}$/,
	        		            				message: 'Mobile number should start with only [9,8,7]'
        		            				},
            		                    stringLength: {
            		                        min: 10,
            		                        max: 10,
            		                        message: 'The mobile number must be 10 digits.'
            		                    }
            		                }
            		            },
            		            email: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The email address is required'
            		                    },
            		                    regexp: {
            		                        regexp:/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
            		                        message: 'Enter valid email'
            		                    }
            		                   
            		                }
            		            },
            		            accHolderName: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z ]+$/,
            		                        message: 'The account holder name can only consist of alphabets'
            		                    }
            		                }
            		            },
            		            accNumber: {
            		            	validators: {
            		        			regexp:	{
            		        				regexp: /^[0-9]+$/,
            		        				message: 'Account Number can only consists of numbers'
            		        				}
            		        		}
            		        	},
            		        	panCardName: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z ]+$/,
            		                        message: 'The name on the PAN card should consists of only alphabets'
            		                    },
            		                }
            		            },
            		            panNumber: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,

            		                        message: 'First 5 should be alphabets followed by 4 numbers and an alphabet'
            		                    },
            		                    stringLength: {
            		                        min: 10,
            		                        max: 10,
            		                        message: 'The PAN number must be 10 digits.'
            		                    }
            		                }
            		            },
            		            ifscCode: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z0-9]+$/,
            		                        message: 'The Ifsc Code should be albha numaric'
            		                    },
            		                    stringLength: {
            		                        min: 11,
            		                        max: 11,
            		                        message: 'The Ifsc Code must be 11 digits.'
            		                    }
            		                }
            		            },
            		            state: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'Please select a state'
            		                    }
            		                }
            		            },      
            		            deptName: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'Please select a department'
            		                    }
            		                }
            		            },
            		            vacPosName: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'Please select a vacant position'
            		                    }
            		                }
            		            },
            		            district:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select a district'
            		            		}
            		            	}
            		            },
            		            distType:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select district type'
            		            		}
            		            	}
            		            },
            		            mandals:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select mandal or municipality'
            		            	
            		            		}
            		            	}
            		            },
            		            villages:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select city or village'
            		            		}
            		            	}
            		            },
            		            accHolderName: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z ]+$/,
            		                        message: 'The account holder name can only consist of alphabets'
            		                    }
            		                }
            		            },
            		            accNumber: {
            		            	validators: {
            		        			regexp:	{
            		        				regexp: /^[0-9]+$/,
            		        				message: 'Account Number can only consists of numbers'
            		        				}
            		        		}
            		        	},
            		        	panCardName: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z ]+$/,
            		                        message: 'The name on the PAN card should consists of only alphabets'
            		                    },
            		                }
            		            },
            		            panNumber: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,

            		                        message: 'First 5 should be alphabets followed by 4 numbers and an alphabet'
            		                    },
            		                    stringLength: {
            		                        min: 10,
            		                        max: 10,
            		                        message: 'The PAN number must be 10 digits.'
            		                    }
            		                }
            		            }
            		            
        				}
    				});
			});
			
function submitForm()
	{
		$('#createEmployeeForm').formValidation().formValidation('validate');
		var x=$('#createEmployeeForm').data('formValidation').isValid();
		if(x)
			{
				
				if(confirm("Are you sure you want to create a new employee with the details provided?"))
					{
					document.forms[0].action='createEmployee.do?actionFlag=saveCreatePVTEmp';
					document.forms[0].submit();
					}
			}
	}
function myFunction(n)
{
	var da=document.getElementById(n).value.trim();
	da = da.replace(/\s\s+/g, ' ');
	document.getElementById(n).value=da;
	  
	    

	
	
	
}

function getAge(arg) 
	{
	var dateString=document.getElementById(arg).value;
	var now = new Date();
	var yearNow = now.getYear();
	var monthNow = now.getMonth();
	var dateNow = now.getDate();
	var dob = new Date(dateString.substring(6,10),dateString.substring(0,2)-1,dateString.substring(3,5));
	var yearDob = dob.getYear();
	var monthDob = dob.getMonth();
	var dateDob = dob.getDate();
	var age = {};
	var ageString = "";
	var yearString = "";
	var monthString = "";
	var dayString = "";
	
	yearAge = yearNow - yearDob;
	if (monthNow >= monthDob)
		var monthAge = monthNow - monthDob;
	else {
		yearAge--;
		var monthAge = 12 + monthNow -monthDob;
	}
	
	if (dateNow >= dateDob)
		var dateAge = dateNow - dateDob;
	else {
		monthAge--;
	    var dateAge = 31 + dateNow - dateDob;
	    if (monthAge < 0) {
	      	monthAge = 11;
	      	yearAge--;
	    }
	  }

	  age = {
	      years: yearAge,
	      months: monthAge,
	      days: dateAge
	      };
        if(age.years<18)
            {
        	 var fr=partial(focusBox, document.getElementById(arg));
             jqueryAlertMsg("Alert","Employee age must be greater than 18 or equal to 18",fr);
             document.getElementById(arg).value="";	
             return ; 
            }
	}
function checkdoj(arg) 
{
var curDate=new Date();
var startDate=document.getElementById(arg).value;

var endDate=document.forms[0].empDob.value;

var startDateVal=startDate.split('-');

var endDateVal=endDate.split('-');

var firstDate=new Date();
firstDate.setFullYear(startDateVal[2], (startDateVal[1] - 1), startDateVal[0]);

var secondDate=new Date();
secondDate.setFullYear(endDateVal[2], (endDateVal[1] - 1), endDateVal[0]);


if (firstDate < secondDate) {
    alert("Date of joining must be 18 years greater than date of birth");
    document.getElementById(arg).value="";	
    return ; 
}

}
function addTooltip(id) {
	var numOptions = document.getElementById(id).options.length;
	for ( var i = 0; i < numOptions; i++)
		document.getElementById(id).options[i].title = document
				.getElementById(id).options[i].text;

}
function partial(func /*, 0..n args */) 
{
	var args = new Array();
	for ( var i = 1; i < arguments.length; i++) {
		args.push(arguments[i]);
	}
	return function() {
		var allArguments = args.concat(Array.prototype.slice
				.call(arguments));
		return func.apply(this, allArguments);
	};
}
function focusBox(arg) 
{
	aField = arg;
	setTimeout("aField.focus()", 0);
}
function LoginName(arg) 
	{
		var loginName = document.getElementById(arg).value;
		
		 if (window.XMLHttpRequest) 
		 {
					xmlhttp = new XMLHttpRequest();
		 }
		 else if (window.ActiveXObject) 
		 		{
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
				} else 
				{
					alert("Your browser does not support XMLHTTP!");
				}

			url= "createEmployee.do?actionFlag=checkLoginName&loginName="+loginName;

			xmlhttp.open("Post",url,true);
			xmlhttp.send(null);				      
			  
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
				if(resultArray!=null)
				{   
					if(resultArray=="true")
					{
					var fr=partial(focusBox, document.getElementById(arg));
					jqueryAlertMsg("Alert","Employee already exists with the given LoginName "+loginName,fr);
					document.getElementById(arg).value="";	
					}
				else
				return;
				}
			}
		}
	  }
	}
function UserNo(arg)
{
	var userNo = document.getElementById(arg).value; 
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

		    var url='createEmployee.do?actionFlag=checkUserNo&userNo='+userNo;
			  xmlhttp.open("Post",url,true);
			  xmlhttp.send(null);				      
			  
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
		if(resultArray!=null)
        {   if(resultArray=="true")
	        {
         	   var fr=partial(focusBox, document.getElementById(arg));
        	   jqueryAlertMsg("Alert","Employee already exists with the given Employee Number "+userNo,fr);
        	   document.getElementById(arg).value="";	
               }
           else
               return;
        }
		}
	  }
	  }
}
function fn_getDistricts()
{
	document.getElementById('mandalTR').style.display="none";
	var xmlhttp;
	var url;
	var state=document.getElementById('state').value;	
   url= "createEmployee.do?actionFlag=getDistrictList&state="+state;
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
	    	document.forms[0].district.options[0]=new Option('-------- Select --------',"");
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
		                     	 document.forms[0].district.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	document.getElementById('district').options.length=0;
	$('#district-input').val('');
}
function fn_getVacantPosition()
	{
		document.getElementById('vacPos1').style.display="";
		document.getElementById('vacPos2').style.display="";	
		document.getElementById('level1').style.display="none";
		document.getElementById('level2').style.display="none";
		document.getElementById('vacPosTR').style.display="none";
		var xmlhttp;
		var url;
		var deptId=document.getElementById('deptName').value;
		   url= "createEmployee.do?actionFlag=getVacantPosition&deptId="+deptId;
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
	    	document.forms[0].vacPosName.options[0]=new Option('-------- Select --------',"");
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
		                     	 document.forms[0].vacPosName.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
				if(resultArray=="[]")
				{
					document.forms[0].vacPosName.options[0]=new Option('No Vacant Position',"NA");
					var fr=partial(focusBox,document.getElementById('vacPosName'));
					jqueryAlertMsg("Alert","Please create a new vacant position for creating new employee ",fr);
					return false;
				}
			}			
		}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		document.getElementById('vacPosName').options.length=0;
		$('#vacPosName-input').val('');	
	}
function fn_getVacantPositionDetails()
	{
		var xmlhttp;
		var url;
		var deptId=document.getElementById('deptName').value;
		var vacPosName=document.getElementById('vacPosName').value;
		if(vacPosName!="NA"){
		 document.getElementById('level1').style.display="";
			document.getElementById('level2').style.display="";
			document.getElementById('vacPosTR').style.display="";
			 document.forms[0].groupName.value="";
	   url= "createEmployee.do?actionFlag=getVacantPosDetails&dept="+deptId+"&vacPosName="+vacPosName;

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
		    
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null){
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");
		         resultArray2=resultArray2.split("^");
	        	var vacPosDtls = resultArray2[0].split("~");
	        	document.getElementById('level').value=vacPosDtls[0];
	        	document.getElementById('desgName').value=vacPosDtls[1];
	        	document.getElementById('repPerson').value=vacPosDtls[2];
	        	
	        	
	        	var groupNames=resultArray2[1].split("~");
	        	
	        	if(groupNames.length>0){   
	                for(var i = 0; i<groupNames.length;i++){	
	                     if(groupNames[i]!="null"){
	                         var val1 = groupNames[i].replace(/^\s+|\s+$/g,"");
	                         if(document.forms[0].groupName.value!=null)
	                     	 document.forms[0].groupName.value +=val1+"\n";  
	                         else
	    	                   document.forms[0].groupName.value=val1;
					   } 
	                     else
	                    	 document.forms[0].groupName.value="No Groups Found";
	                }
	            }
	        	
	        	}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	 }	
}
function fn_getList()
{	
	document.getElementById('mandals').options.length=0;
	document.getElementById('villages').options.length=0;
	$('#mandals-input').val('');
	$('#villages-input').val('');
	document.forms[0].distType[0].checked=false;
	document.forms[0].distType[1].checked=false;
	document.getElementById('mandalTR').style.display="";
	document.getElementById('distType1').style.display="";
	document.getElementById('distType2').style.display="";
	document.getElementById('mandalsTD1').style.display="none";
	document.getElementById('mandalsTD2').style.display="none";
	document.getElementById('villagesTD1').style.display="none";
	document.getElementById('villagesTD2').style.display="none";
}

function fn_getMandals(value){
	 var distId=document.getElementById('district').value;
	 var mandals=document.getElementById('mandals').value;
	 document.getElementById('mandalsTD1').style.display="";
	 document.getElementById('mandalsTD2').style.display="";
	 document.getElementById('villagesTD1').style.display="none";
		document.getElementById('villagesTD2').style.display="none";

    if(value=="Mdl")
        {
    	document.getElementById('mandalsTD1').innerHTML='Mandals <span style="color: #ff0000"> *</span>';
    	document.getElementById('mandalsTD2').title="Select Mandal";
    	//document.getElementById('villagesTD1').innerHTML="Village";
    	//document.getElementById('villagesTD2').title="Select Villages";
        }
    if(value=="Mpl")
    {
	document.getElementById('mandalsTD1').innerHTML='Municipalities <span style="color: #ff0000"> *</span>';
	document.getElementById('mandalsTD1').title="Select Municipalities";
	//document.getElementById('villagesTD1').innerHTML="City";
	//document.getElementById('villagesTD2').title="Select City";
    }
		
	 var mand=value;
	  	var xmlhttp;
		var url;
		url= "createEmployee.do?actionFlag=getMandalList&mandal="+mand+"&distId="+distId;
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
		 alert("Your Browser Does Not Support XMLHTTP!");
		}
		
		 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	document.forms[0].mandals.options[0]=new Option('-------- Select --------',"");
		        var resultArray=xmlhttp.responseText;
		        
		        if(resultArray!= null)
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
			                     	 document.forms[0].mandals.options[i+1] =new Option(val1,val2);       
							   } 
			                }
			            }
		        	}
		        if(resultArray=="[]")
				{
				document.forms[0].mandals.options[0]=new Option('No Mandal/Municipality',"NA");
				}
		        
		    }			
		}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	
		document.getElementById('mandals').options.length=0;
		$('#mandals-input').val('');

	}
function fn_getVillages(){
	$('#villages-input').val('');
	document.getElementById('villagesTD1').style.display="";
	document.getElementById('villagesTD2').style.display="";
	 document.getElementById('villages').length=0;
	 if(document.forms[0].distType[0].checked==true){
		value="Mdl";
	  }
	 if(document.forms[0].distType[1].checked==true){
	    value="Mpl";
		 }
	 var mand=value;
	 var mandal=document.getElementById('mandals').value;
	 	var xmlhttp;
		var url;
		url= "createEmployee.do?actionFlag=getMandalList&mandal="+mand+"&mmpty="+mandal;

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
		 alert("Your Browser Does Not Support XMLHTTP!");
		}
		
		 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	document.forms[0].villages.options[0]=new Option('-------- Select --------',"");
		        var resultArray=xmlhttp.responseText;
		        if(resultArray!= null)
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
			                     	 document.forms[0].villages.options[i+1] =new Option(val1,val2);       
							   } 
			                }
			            }
			            
		        	}
		        if(resultArray=="[]")
				{
				
				document.forms[0].villages.options[0]=new Option('No City/Village',"NA");
				}
		    }			
		}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		    //Ajax end
}
<%-- function getIFSCCode(arg){

	document.getElementById("ifscCode").disabled=false;
	document.getElementById("accHolderName").disabled=false;
	document.getElementById("accNumber").disabled=false;
	document.getElementById("panCardName").disabled=false;
	document.getElementById("panNumber").disabled=false;

	   var xmlhttp;
 var url;
 var resultArray;
 var IFSCCode;
	   
 var bankCode=document.getElementById(arg).value;  

 if(bankCode=="Select")
	 {
	
	 document.getElementById('ifscCode').value="";
	 document.getElementById("accHolderName").value="";
		document.getElementById("accNumber").value="";
		document.getElementById("panCardName").value="";
		document.getElementById("panNumber").value="";
	 document.getElementById("ifscCode").disabled=true;
		document.getElementById("accHolderName").disabled=true;
		document.getElementById("accNumber").disabled=true;
		document.getElementById("panCardName").disabled=true;
		document.getElementById("panNumber").disabled=true;
	 }
	
 else
	 {
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
 	 jqueryAlertMsg("Alert","Your browser does not support XMLHTTP!");
  }
  xmlhttp.onreadystatechange=function()
  {
      if(xmlhttp.readyState==4)
      {
          resultArray=xmlhttp.responseText;
  				IFSCCode = resultArray.replace(/^\s+|\s+$/g,"");
				document.getElementById('ifscCode').value = IFSCCode;
					
      }
  }  
 
   url= '/<%=context%>/createEmployee.do?actionFlag=GETIFSCCODE&bankCode='+bankCode;
   xmlhttp.open("Post",url,true);
   xmlhttp.send(null);

} 
} --%>

function getBankName(arg){

	 var xmlhttp;
	 var url;
	 var resultArray;
	 var IFSCCode;
		   
	 var ifscCode=document.getElementById(arg).value;  

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
	 	 jqueryAlertMsg("Alert","Your browser does not support XMLHTTP!");
	  }
	  xmlhttp.onreadystatechange=function()
	  {
	      if(xmlhttp.readyState==4)
	      {	
	          resultArray=xmlhttp.responseText;
					 var addList = resultArray.split("@");
			         if(addList.length>0)
			            {   
			                for(var i = 0; i<addList.length;i++)
			                {	
			                    var arr=addList[i].split("~");
			                     if(arr[1]!=null && arr[0]!=null)
			                     {
			                         var val1 = arr[1].replace(/^\s+|\s+$/g,"")+"("+arr[2].replace(/^\s+|\s+$/g,"")+")";
			                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			                     	 document.forms[0].bankName.options[i+1] =new Option(val1,val2);       
							  	 } 
			                }
			                
			                document.getElementById("bankName").disabled=false;
			            	document.getElementById("accHolderName").disabled=false;
			            	document.getElementById("accNumber").disabled=false;
			            	document.getElementById("panCardName").disabled=false;
			            	document.getElementById("panNumber").disabled=false;
			            }
	  		}  
	  }
	   url= 'createEmployee.do?actionFlag=getBankName&ifscCode='+ifscCode;
	   xmlhttp.open("Post",url,true);
	   xmlhttp.send(null);
	  }

function fn_showAlert()
	{
		var successflag="${success}";
		var userID="${userID}";
		if(successflag=="Success")
		{
			alert("Employee Created Successfully with the Employee ID : "+userID);
		}
		resetPage();
	}
function resetPage()
	{
	  
	      document.getElementById('fName').value="";
	      document.getElementById("lName").value="";
	      document.forms[0].gender[0].checked=false;
		  document.forms[0].gender[1].checked=false;
		  document.getElementById('empDob').value="";
		  document.getElementById('empDoj').value="";
	
	      document.getElementById('empAddress').value="";
	      document.getElementById('vacPosName').value="";

	      document.getElementById('state').value=""; $('#state-input').val('');
	      document.getElementById('district').value="";$('#district-input').val('');
          document.getElementById('postalCode').value="";
	      document.getElementById('mobNumber').value="";
	      document.getElementById('email').value=""; 
	      document.getElementById('vacPos1').value="";
		  document.getElementById('ifscCode').value=""; 
		  document.getElementById('accHolderName').value=""; 
		  document.getElementById('accNumber').value=""; 
		  document.getElementById('panCardName').value=""; 
		  document.getElementById('panNumber').value="";

		  
	}
	
function resetPage1()
{
	 parent.document.getElementById("middleFrame").src='createEmployee.do?actionFlag=employeeCreation'; 
}



	function addTooltip(id) {
		var numOptions = document.getElementById(id).options.length;
		for ( var i = 0; i < numOptions; i++)
			document.getElementById(id).options[i].title = document
					.getElementById(id).options[i].text;

	}
	
	function checkNames(name)
	{
		var val= document.getElementById(name).value;
		var pattern=/^[0-9 A-Z a-z]+$/; 
		if(!val.match(pattern))
			{
			 var fr=partial(focusBox,document.getElementById(name));
			 jqueryAlertMsgTop("Alert",'Names accepts _ and alphanumerics only',fr);
	         document.getElementById(name).value="";	
	         return false;
			}
		return true;
	}

</script>
</head>

<body onload="fn_showAlert();">
	<html:form action="/createEmployee.do" method="POST" enctype="multipart/form-data" styleId="createEmployeeForm">
		
			<!-- <div class="tbheader1 col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group">		
				<b>New Employee Creation</b>
			</div> -->
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss">
                                   <div class="tbheader1" style="height:5%"><b>New Employee Creation</b></div>
                                   
         </div>
         <div class="container-fluid center-block">
			<div align="right">		
				<span style="color: #ff0000"> *-Mandatory Fields</span>
			</div>
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group"><div align="center"><b>Personal Details</b></div>
           		<div class="row">
                        <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				First Name<font color="Red">*</font>
						<html:text title="Enter FirstName of Employee" styleClass="form-control" property="fName" styleId="fName" maxlength="50" onchange="myFunction('fName')" name="createEmployeeForm"></html:text>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Last Name<font color="Red">*</font>
           				<html:text title="Enter LastName of Employee" styleClass="form-control" property="lName" styleId="lName" maxlength="50"  onchange="myFunction('lName')" name="createEmployeeForm" />
           			</div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Gender<font color="Red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           				<br>
           				<html:radio title="Select Gender of Employee"  name="createEmployeeForm" property = "gender"  styleId="gender"  value = "M"/>Male&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio title="Select Gender of Employee"  name="createEmployeeForm" property = "gender"  styleId="gender"  value = "F"/>Female
           			</div>
           		</div>
      
           		<div class="row">
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Date of Birth<font color="Red">*</font>		 
           				<html:text styleClass="form-control" property="empDob" title="Select Employee DOB" styleId="empDob" name="createEmployeeForm" readonly="true" onchange="getAge('empDob')"/>
           			</div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Date of Joining<font color="Red">*</font>
           				<html:text styleClass="form-control" property="empDoj" title="Select Employee DOJ"  styleId="empDoj" name="createEmployeeForm" onchange="checkdoj('empDoj')" readonly="true"/>
           			</div>	
	                 <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Address<font color="Red">*</font>
           				<html:text title="Enter Address of Employee" styleClass="form-control" maxlength="300" property="empAddress" styleId="empAddress" onchange="myFunction('empAddress')"  name="createEmployeeForm"/>
           			</div>
	
					</div>
           		
           		<div class="row">
          
           				
           			
         
		
           		</div>

           		<div class="row">
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Country
           				<html:text styleClass="form-control" property="country" title="Country" styleId="country" name="createEmployeeForm" value="India" readonly="true"/>
           			</div>
           		
           		  			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				State<font color="Red">*</font>	
           				<br>
           				<html:select  styleClass="form-control" property="state" name="createEmployeeForm" styleId="state" onmouseover="addTooltip('state');"  onchange="fn_getDistricts()" title="Select State">
						<option value="">--------Select---------</option>
						<html:options collection="stateList" labelProperty="VALUE" property="ID"/>
						</html:select> 
					</div>
           					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				District<font color="Red">*</font>	
           				<br>
           				<html:select  styleClass="form-control" property="district" name="createEmployeeForm" styleId="district" onmouseover="addTooltip('district');" title="Select District" onchange="javascript:fn_getList()">
						<option value="">--------Select---------</option>
						 <html:options collection="distList" labelProperty="DISTRICTNAME" property="LOCID"/>
						</html:select>
					</div>
	
     
			</div>
			           		<div class="row" id="mandalTR" style="display:none;">
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                 		<div id="distType1" style="display:none;">Mandal/Municipality<font color="Red">*</font></div>
						<div class="tbcellBorder" id="distType2" style="display:none;">
							<html:radio title="Select Mandal/Municipality" name="createEmployeeForm" property = "distType"  styleId="distType" onclick="fn_getMandals('Mdl')" value = "Mdl"/>Mandal &nbsp;&nbsp;
							<html:radio title="Select Mandal/Municipality" name="createEmployeeForm" property = "distType"  styleId="distType" onclick="fn_getMandals('Mpl')"  value = "Mpl"/>Municipality
						</div>
                 	</div>
                 	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                  		<div id="mandalsTD1" style="display:none;">Mandals/Municipalities <font color="Red">*</font></div>
						 <div id="mandalsTD2" class="tbcellBorder" style="display:none;">
						 	<html:select  styleClass="form-control" property="mandals" name="createEmployeeForm" styleId="mandals" onmouseover="addTooltip('mandals');"  onchange="javascript:fn_getVillages()" title="Select Mandal/Municipality">
							<option value="">--------Select---------</option>
						 	<html:options collection="mdlList" labelProperty="VALUE" property="ID"/>
							</html:select>
                 		</div>
                 	</div>
                 	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                 		 <div id="villagesTD1" style="display:none;">City/Village <font color="Red">*</font></div>
						 <div id="villagesTD2" class="tbcellBorder" style="display:none;">
						 	<html:select  styleClass="form-control" property="villages" name="createEmployeeForm" styleId="villages" onmouseover="addTooltip('villages');" title="Select City/Village/Town">
							<option value="">--------Select---------</option>
						 	<html:options collection="vilList" labelProperty="VALUE" property="ID"/>
							</html:select>
                 		 </div>
                 	</div>
           		</div>
			<div class="row">
				   <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Postal Code<font color="Red">*</font>
           				<html:text styleClass="form-control" maxlength="6" property="postalCode" title="Enter Postal Code" styleId="postalCode" name="createEmployeeForm"/>	
           			</div>	
			      			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Mobile Number<font color="Red">*</font>
           				<div class="input-group">
						<div class="input-group-addon">+91</div>
           				<html:text styleClass="form-control" maxlength="10" property="mobNumber" title="Enter Mobile Number" styleId="mobNumber" name="createEmployeeForm"/>
           			</div>
           			</div>	
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				E-Mail<font color="Red">*</font>
           				<html:text styleClass="form-control" property="email" maxlength="50" title="Enter Email" styleId="email"onchange="myFunction('email')"  name="createEmployeeForm"/>
           			</div>		 	 
           		</div>
			
			</div>

	<%-- 
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group"><div align="center"><b>Bank Details</b></div>
				<div class="row">
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						
						IFSC Code
						<html:text styleClass="form-control" property="ifscCode" tabindex="0" maxlength="11"  styleId="ifscCode"  name="createEmployeeForm" title="IFSC code"  onchange = "getBankName('ifscCode')"/>
						 
					</div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Bank Name
						<html:text disabled="true" styleClass="form-control" property="ifscCode" tabindex="0" readonly="true" styleId="ifscCode"  name="createEmployeeForm" title="IFSC code" />
					<html:text disabled="true" styleClass="form-control" property="bankName" tabindex="0" readonly="true" styleId="bankName"  name="createEmployeeForm" title="Bank Name" />
					
					  <html:select disabled="true" styleClass="form-control" property="bankName" name="createEmployeeForm" styleId="bankName"  onmouseover="addTooltip('bankName');" title="Bank Name" onchange = "getIFSCCode('bankName')"  >
                     	<option value="Select">----------Select Bank Name--------</option>
							<logic:notEmpty name="BankCombo">
                    	<html:options collection="BankCombo" labelProperty="VALUE" property="ID"/>
                    	</logic:notEmpty>
                      	</html:select>
					</div>
						<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Name as per Bank Account:
						<html:text styleClass="form-control" disabled="true" property="accHolderName" maxlength="50" title="Name of Account Holder" styleId="accHolderName" name="createEmployeeForm"/>		 
					</div>
				</div>
				<div class="row">
           		
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Account Number:
						<html:text styleClass="form-control" disabled="true" property="accNumber" maxlength="20" title="Account Number" styleId="accNumber" onchange="myFunction('accNumber')"  name="createEmployeeForm"/>		 
					</div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						Name as per PAN Card:
						<html:text styleClass="form-control" disabled="true" property="panCardName" maxlength="50" title="Name on Pan Card" styleId="panCardName"  onchange="myFunction('panCardName')"name="createEmployeeForm"/>		 
					</div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
						PAN Card Number:
						<html:text styleClass="form-control" disabled="true" property="panNumber" maxlength="12" title="Pan CardNumber" styleId="panNumber" name="createEmployeeForm"/>		 
					</div>
				</div>`
			</div> --%>
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group"><div align="center"><b>Other Details</b></div>
           			<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Department<font color="Red">*</font>
           				<html:select styleClass="form-control" property="deptName" name="createEmployeeForm" styleId="deptName" onmouseover="addTooltip('deptName');" title="Select Department" onchange="fn_getVacantPosition()">
						<html:options property="ID" collection="hospitalList" labelProperty="VALUE"/>
							
						</html:select>
					    </div>
					<div id="vacPos1" class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Designation<font color="Red">*</font>
 
           					<html:select  styleClass="form-control" property="vacPosName" name="createEmployeeForm" styleId="vacPosName" onmouseover="addTooltip('vacPosName');"  title="Select Vacant Position">
							<option value="">--------Select---------</option>	
							<logic:notEmpty name="vacNamesList">
							 <html:options collection="vacNamesList" labelProperty="VALUE" property="ID"/>
							 </logic:notEmpty>
							</html:select>

				    </div>
				    
			</div>
	</div>
			
</div>

			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center">
					<button class="btn but" id="create" name="create" type="button"  title="create" onClick="submitForm()">Create</button>
               	 	<button class="btn but" id="reset" name="reset"  type="reset"  onClick="resetPage1()" title="Reset">Reset</button> 	
			</div>
	</html:form>
</body>
</html>


