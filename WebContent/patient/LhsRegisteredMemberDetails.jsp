<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%>

<%
int liTabIndex = 0;
String photoUrl=(String)request.getAttribute("photo");
 %>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>
	<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>
<script>
    window.onload = function() {
        // Make all inputs readonly
        document.querySelectorAll("input").forEach(function(input) {
            input.setAttribute("readonly", true);
        });

        // Disable all select dropdowns
        document.querySelectorAll("select").forEach(function(select) {
            select.setAttribute("disabled", true);
        });
        
        document.querySelectorAll("input[type=radio]").forEach(function(radio) {
            radio.setAttribute("disabled", true);
        });

        // If you have textareas, include this
        document.querySelectorAll("textarea").forEach(function(textarea) {
            textarea.setAttribute("readonly", true);
        });

        // Optionally disable file inputs too
        document.querySelectorAll("input[type='file']").forEach(function(fileInput) {
        	 if (fileInput.id !== "signedDocument") {
        	        fileInput.setAttribute("disabled", true);
        	    }
        });
    };
    
    function fn_back() {
        window.location.href = 'patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&regFlag=Y';
    }
    function fn_checker_back() {
         window.location.href = 'patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers';
    }
    //second level checker 
    function fn_approve_back() {
    	window.location.href = 'patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&approveFlag=Y';
    }
    function fn_recomended_approve(){
    	
    	  var confirmApprove = confirm("Are you sure you want to send for approval?"); 
    	    
    	    if (!confirmApprove) {
    	        return; 
    	    }
    	    
         var lhsRegNo = document.getElementById("lhsId").value;
         var approve = "true";

         var params = 
         	"lhsRegNo=" + encodeURIComponent(lhsRegNo) +
         	"&lhsApprove=" + encodeURIComponent(approve);
         
         var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberApproveOrReject&" + params;

         var xhr = new XMLHttpRequest();
         xhr.open("POST", url, true);
         xhr.onreadystatechange = function() {
             if (xhr.readyState === 4) {
                 if (xhr.status === 200) {
                 	var response = xhr.responseText.trim(); // Trim to remove extra spaces or newlines
                     if (response === "success") {
                         alert("Legislative member's request is sent for approval!");
                        // fn_approve_back();
                         fn_checker_back();
                     } else {
                         alert("Failed To approve: ");
                     }
                 } else {
                     alert("Failed To approve:");
                 }
             }
         };
         xhr.send(); 
         
    }
    
    function submit_approve(){
    	var fileInput = document.getElementById("signedDocument");
    	if (!fileInput || !fileInput.files || fileInput.files.length === 0) {
    	    alert("Signed document is mandatory");
    	    return;
    	}
    	 document.getElementById("approveModal").style.display = "block";
         document.getElementById("modalBackdrop").style.display = "block";
         document.querySelectorAll("textarea").forEach(function(textarea) {
             textarea.removeAttribute("readonly");
         });
    }
    
    function fn_approve() {
    	
    	var approveRemarks = document.getElementById("approveRemarks").value.trim();
    	if (!approveRemarks) {
    	    alert("Please enter approval remarks");
    	   // remarks.focus();
    	    return;
    	}
    	
    	var fileInput = document.getElementById("signedDocument");
    	var signedDocFile = fileInput.files[0];

    	  var confirmApprove = confirm("Are you sure you want to approve..?");
  	    
	  	  if (!confirmApprove) {
	  	        return; 
	  	    }
	  	  
	  	 var regNo = document.getElementById("lhsId").value;
	  	 var approveFlag = "true";
	      
	  	 // Create FormData object
	      var formData = new FormData();
	  	 
	    //  var signedDocFile = document.getElementById("signedDocument").files[0];
	      if (signedDocFile) {
	          formData.append("signedDocFile", signedDocFile);
	      }

		formData.append("mlaId",regNo);
		formData.append("status",approveFlag);
		formData.append("remarks",approveRemarks);
		
	      var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberSignedDocument&";

	         var xhr = new XMLHttpRequest();
	         xhr.open("POST", url, true);
	         xhr.onreadystatechange = function() {
	             if (xhr.readyState === 4) {
	                 if (xhr.status === 200) {
	                 	var response = xhr.responseText.trim(); // Trim to remove extra spaces or newlines
	                     if (response === "success") {
	                         alert("Legislative member's request is approved successfully!");
	                         fn_approve_back();
	                        // fn_checker_back();
	                     } else {
	                         alert("Failed To approve: ");
	                     }
	                 } else {
	                     alert("Failed To approve:");
	                 }
	             }
	         };
	         xhr.send(formData); 
    }
    
    //second level reject
    function fn_final_reject(){
    	
            var reason = document.getElementById("rejectReason").value.trim();
            var lhsRegNo = document.getElementById("lhsId").value;

            if (!reason) {
                alert("Please enter a rejection reason.");
                return;
            }

        	var fileInput = document.getElementById("signedDocument");
        	var signedDocFile = fileInput.files[0];

        	 var confirmApprove = confirm("Are you sure you want to Reject..?");
        	 

   	  	  if (!confirmApprove) {
   	  	        return; 
   	  	    }
   	      
   	  	 // Create FormData object
   	      var formData = new FormData();
   	  	 
   	    //  var signedDocFile = document.getElementById("signedDocument").files[0];
   	      if (signedDocFile) {
   	          formData.append("signedDocFile", signedDocFile);
   	      }

   		formData.append("mlaId",lhsRegNo);
   		formData.append("rejectStatus","True");
   		formData.append("remarks",reason);
   		
   	      var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberSignedDocument&";

   	         var xhr = new XMLHttpRequest();
   	         xhr.open("POST", url, true);
   	         xhr.onreadystatechange = function() {
   	             if (xhr.readyState === 4) {
   	                 if (xhr.status === 200) {
   	                 	var response = xhr.responseText.trim(); // Trim to remove extra spaces or newlines
   	                     if (response === "success") {
   	                         alert("Legislative member's request is rejected!");
   	                         fn_approve_back();
   	                        // fn_checker_back();
   	                     } else {
   	                         alert("Failed To approve: ");
   	                     }
   	                 } else {
   	                     alert("Failed To approve:");
   	                 }
   	             }
   	         };
   	         xhr.send(formData); 

          
    }
    
    function fn_reject() {
    	var fileInput = document.getElementById("signedDocument");
    	if (!fileInput || !fileInput.files || fileInput.files.length === 0) {
    	    alert("Signed document is mandatory");
    	    return;
    	}
    	
        document.getElementById("rejectModal").style.display = "block";
        document.getElementById("modalBackdrop").style.display = "block";
        document.querySelectorAll("textarea").forEach(function(textarea) {
            textarea.removeAttribute("readonly");
        });

    }
    function fn_reject1() {
        document.getElementById("rejectModal").style.display = "block";
        document.getElementById("modalBackdrop").style.display = "block";
        document.querySelectorAll("textarea").forEach(function(textarea) {
            textarea.removeAttribute("readonly");
        });

    }

    function closeRejectModal() {
    	document.getElementById("rejectReason").value='';
        document.getElementById("rejectModal").style.display = "none";
        document.getElementById("modalBackdrop").style.display = "none";
    }
    
    //1st level reject
    function submitReject() {
        var reason = document.getElementById("rejectReason").value.trim();
        var lhsRegNo = document.getElementById("lhsId").value;

        if (!reason) {
            alert("Please enter a rejection reason.");
            return;
        }

        var params = 
        	"rejectReason=" + encodeURIComponent(reason) +
        	"&lhsRegNo=" + encodeURIComponent(lhsRegNo);
        
        var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberApproveOrReject&" + params;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                	var response = xhr.responseText.trim(); // Trim to remove extra spaces or newlines
                    if (response === "success") {
                        alert("Legislative member's request is rejected!");
                        fn_checker_back();
                    } else {
                        alert("Failed To Reject: ");
                    }
                } else {
                    alert("Failed To Reject:");
                }
            }
        };
        xhr.send(); 
        
   }
    
 
    
    //revert
    function submitRevert() {
        var reason = document.getElementById("revertReason").value.trim();
        var lhsRegNo = document.getElementById("lhsId").value;

        if (!reason) {
            alert("Please enter a return reason.");
            return;
        }

        var params = 
        	"revertReason=" + encodeURIComponent(reason) +
        	"&lhsRegNo=" + encodeURIComponent(lhsRegNo);
        
        var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberRevert&" + params;

        var xhr = new XMLHttpRequest();
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                	var response = xhr.responseText.trim(); // Trim to remove extra spaces or newlines
                    if (response === "success") {
                        alert("Legislative member's request is returned!");
                        fn_checker_back();
                    } else {
                        alert("Failed To Reject: ");
                    }
                } else {
                    alert("Failed To Reject:");
                }
            }
        };
        xhr.send(); 
        
   }
    
    
    function fn_revert() {
        document.getElementById("revertModal").style.display = "block";
        document.getElementById("modalBackdrop").style.display = "block";
        document.querySelectorAll("textarea").forEach(function(textarea) {
            textarea.removeAttribute("readonly");
        });

    }

    function closeModal() {
    	document.getElementById("revertReason").value='';
        document.getElementById("revertModal").style.display = "none";
        document.getElementById("modalBackdrop").style.display = "none";
    }
    
    function closeApproveModal() {
    	document.getElementById("approveRemarks").value='';
        document.getElementById("approveModal").style.display = "none";
        document.getElementById("modalBackdrop").style.display = "none";
    }


  //for view.
    function viewDocument(filePath) {
        window.open('./patientDetailsNew.do?actionFlag=viewLhsDocument&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
    }

    function validateAttachment(fileInput) {
        const allowedExtensions = ['pdf', 'jpg', 'jpeg', 'png'];
        const maxSize = 200 * 1024; 

        if (fileInput.files.length === 0) {
            alert("Please select a file.");
            return false;
        }

        const file = fileInput.files[0];
        const fileName = file.name;
        const fileSize = file.size;

        const extension = fileName.split('.').pop().toLowerCase();

        if (!allowedExtensions.includes(extension)) {
            alert("Invalid file format. Accepted formats: .pdf, .jpg, .jpeg, .png");
            fileInput.value = ""; 
            return false;
        }

        if (fileSize > maxSize) {
            alert("File size must be less than or equal to 200 KB.");
            fileInput.value = ""; 
            return false;
        }

        return true;
    }

   
</script>

  <style>
        .section-title {
            background-color: #4A90E2;
            color: white;
            padding: 5px;
            font-weight: bold;
            margin-top: 10px;
            
        }
        .form-section {
            border: 1px solid #c0c0c0;
            padding: 10px;
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        table {
            width: 100%;
        }
        td {
            padding: 5px;
        }
        .mandatory {
            color: red;
        }
        .submit-btn {
            padding: 5px 15px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        .back-btn {
            padding: 5px 15px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        .reject-btn {
            padding: 3px 5px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        .revert-btn {
            padding: 3px 5px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        .approve-btn{
            padding: 3px 5px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        
    </style>
</head>
<body>
<br>
<div>
  <c:choose>
    <c:when test="${regFlag == 'Y'}">
      <input type="button" class="back-btn" 
             style="background-color: #4A90E2; color: white; margin-left: 94%;" 
             value="Back" onclick="fn_back()" />
    </c:when>

    <c:when test="${lhsApproveFlag == 'Y'}">
      <input type="button" class="back-btn" 
             style="background-color: #4A90E2; color: white; margin-left: 94%;" 
             value="Back" onclick="fn_approve_back()" />
    </c:when>

    <c:otherwise>
      <input type="button" class="back-btn" 
             style="background-color: #4A90E2; color: white; margin-left: 94%;" 
             value="Back" onclick="fn_checker_back()" />
    </c:otherwise>

  </c:choose>
</div>

 
    <div class="section-title" style="text-align: center;">MLA/MLC Personal Details</div>
    
<logic:notEmpty name="registeredLhsPatientsList">
    <bean:define id="patientVO" name="registeredLhsPatientsList" />
</logic:notEmpty>

    <table style="margin-top: 11px;">
		<tr>

			<td><label>Full Name</label></td>
			<td><input type="text" name="fullName" value="<bean:write name='patientVO' property='firstName'/>" /></td>

			<td><label>Father Name </label></td>
			<td><input type="text" name="fatherName"
				value="<bean:write name='patientVO' property='fatherName'/>" /></td>
				
			<!-- <td><label>Gender</label></td>
					<td>
					    <input type="radio" name="gender" value="M"
					        <logic:equal name="patientVO" property="gender" value="Male">checked="checked"</logic:equal> />
					    Male
					
					    <input type="radio" name="gender" value="F"
					        <logic:equal name="patientVO" property="gender" value="Female">checked="checked"</logic:equal> />
					    Female
					</td> -->
					
					<td><label>Gender</label></td>
						<td>
						    <input type="text" name="gender" 
						           value="<bean:write name="patientVO" property="gender"/>" 
						           readonly="readonly" />
						</td>
			
		</tr>
		
		 <tr>
		
			<td><label>Date of Birth </label></td>
			<td><input type="text" name="dob" id="dob" style="width: 120px;"
				value="<bean:write name='patientVO' property='dateOfBirth'/>" /></td>

			<td><label>Mobile Number </label></td>
			<td><input type="text" name="mobileNo" id="mobileNo"
				maxlength="10" onkeyup="validateMobile(this)"
				onchange="mobileLength(this)"
				value="<bean:write name='patientVO' property='mobile'/>" /></td>
				
			<td><label>Aadhaar Number </label></td>
            <td>
              <input type="text" name="aadharNo" id="aadharNo" style="width: 120px;"
				value="<bean:write name='patientVO' property='aadharID'/>" />
		    </td>
		    
		</tr>
		
	</table>

    <div class="section-title" style="text-align: center;">MLA/MLC Member Details</div>
    <table style="margin-top: 11px;">
        <tr>
       
			  <td><label>Member Type</label></td>
			<td>
			  <bean:define id="memberType" name="patientVO" property="memberType" />
              <input type="text" name="memberType" id="memberType" value="<%= memberType %>" readonly />
			</td>
    
            <logic:equal name="patientVO" property="memberType" value="MLA">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="MLC">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="EX-MLA">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="EX-MLC">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="FSOD">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
           <logic:present name="patientVO" property="constituency">
			    <td>
			        <label>Constituency Name</label>
			    </td>
			    <td>
			       <bean:define id="constituency" name="patientVO" property="constituency" />
                  <input type="text" name="constType" id="constType" value="<%= constituency %>" readonly />
			    </td>
			</logic:present> 
		</tr>
		<tr>	
            <td><label>MLA/MLC/PS ID Number </label></td>
          <!--   <td><input type="text" name="lhsId" /></td> -->
            <td><input type="text" style="width: 120px;"
				value="<bean:write name='patientVO' property='cardNo'/>" /></td>
       
            <td>
			  <label>Term Start Date </label>
			</td>
			<td>
			  <input type="text" name="termStartDate" id="termStartDate" style="width: 120px;"
				value="<bean:write name='patientVO' property='termStartDate'/>" />
			</td>

            
            <td><label>Term End Date </label></td>
            <td>
			  <input type="text" name="termEndDate" id="termEndDate" style="width: 120px;"
				value="<bean:write name='patientVO' property='termEndDate'/>" />
			</td>
			
			  <td><label>Registration Number </label></td>
            <td>
			 <td><input type="text" name="lhsId" id="lhsId" style="width: 120px;"
				value="<bean:write name='patientVO' property='patientId'/>" /></td>
        </tr>
    </table>
    
    <logic:notEmpty name="registeredDependentsList">
     <div class="section-title" style="text-align: center;" id="dependentDiv">Dependent Details</div>  
       <table id="dependentTable" border="1" style="margin-top:20px; width: 100%;" >
		  <thead style="background: #4A90E2;color: white;">
		    <tr>
		      <th>Dependent Name</th>
		      <th>Gender</th>
		      <th>Date of Birth</th>
		      <th>Relationship</th>
		      <th>Marital Status</th>
		      <th>Mobile Number</th>
		      <th>Aadhaar Number</th>
		      <th>Aadhaar Document</th> 
		      
		    </tr>
		  </thead>
		  <tbody>
		       <logic:iterate id="dependentVO" name="registeredDependentsList">
	                <tr>
	                    <td><bean:write name="dependentVO" property="firstName" /></td>
	                    <td><bean:write name="dependentVO" property="gender" /></td>
	                    <td><bean:write name="dependentVO" property="dateOfBirth" /></td>
	                    <td><bean:write name="dependentVO" property="type1" /></td>
	                    <td><bean:write name="dependentVO" property="memberType" /></td>
	                    <td><bean:write name="dependentVO" property="mobile" /></td>
	                    <td><bean:write name="dependentVO" property="aadharID" /></td>
	                    
	                    <td>
	                        <logic:notEmpty name="dependentVO" property="aadharFileName">
	                            <%-- <a href="patientDetailsNew.do?actionFlag=viewDocument&fileName=<bean:write name='dependentVO' property='aadharFilePath'/>" target="_blank"
	                             onclick="viewDocument('<bean:write name='dependentVO' property='aadharFileName'/>')">
	                             <bean:write name="dependentVO" property="aadharFileName"/>
	                            </a> --%> 
	                          
	                                <bean:define id="aadharFilePath" name="dependentVO" property="aadharFilePath" />
									<bean:define id="aadharFileName" name="dependentVO" property="aadharFileName" />
									
									<a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= aadharFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= aadharFileName %>
									</a>
	                             
						    </logic:notEmpty>
						    <logic:empty name="dependentVO" property="aadharFileName">
						        No file chosen
						    </logic:empty>
	                    </td>
	                   
	                </tr>
             </logic:iterate>
		  </tbody>
	</table>
	
    </logic:notEmpty>

    <div class="section-title" style="text-align: center;">MLA/MLC Address Details</div>
    <table style="margin-top: 11px;">
        <tr>
            <td><label>Permanent Address </label></td>
            <td>
              <!--  <input type="text" name="perAdd" /> -->
              <input type="text" name="perAdd" id="perAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='permanentAddress'/>" />
            </td>
            
            <td><label>Official Address </label></td>
            <td>
            <!--  <input type="text" name="offAdd" /> -->
             <input type="text" name="offAdd" id="offAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='officialAddress'/>" />
            </td>
            
            <td><label>Constituency Address </label></td>
            <td>
             <!-- <input type="text" name="constAdd" /> -->
             <input type="text" name="constAdd" id="constAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='constituencyAddress'/>" />
            </td>
        </tr>
        <tr>  
          <td id="districtMla"><label>District</label></td>
			<td id="districtMlaId">
			    <logic:present name="lhsMemberDistrict">
			        <bean:define id="district" name="lhsMemberDistrict" />
                    <input type="text" name="district" id="district" value="<%= district %>" readonly />
			    </logic:present>
			</td>
            
            <td><label>PIN Code </label></td>
            <td>
             <!--  <input type="text" name="pinCode" id="pinCode" /> -->
             <input type="text" name="pinCode" id="pinCode" style="width: 120px;"
				value="<bean:write name='patientVO' property='pinCode'/>" />
            </td>
            <td><label>Email ID</label></td>
			<td><input type="text" name="emailId" id="email"
				value="<bean:write name='patientVO' property='eMailId'/>" /></td>
            
         </tr>
         <tr>
          <td><label>Aadhaar Card</label></td>
			<td>
			  <logic:notEmpty name="patientVO" property="aadharFileName">
			   <%--  <a href="patientDetailsNew.do?actionFlag=viewDocument&fileName=<bean:write name='patientVO' property='aadharFilePath'/>">
			       <bean:write name="patientVO" property="aadharFileName"/>
			    </a> --%>
			                    <bean:define id="aadharFilePath" name="patientVO" property="aadharFilePath" />
									<bean:define id="aadharFileName" name="patientVO" property="aadharFileName" />
									
									<a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= aadharFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= aadharFileName %>
									</a>
			  </logic:notEmpty>
			  <logic:empty name="patientVO" property="aadharFileName">
				    No file choosen
				  </logic:empty>
			</td>

             <td><label>MLA/MLC Photo</label></td>
	           <!-- <td>
	               <input type="file" id="photoFile" name="photoFile" /></label>
	          </td> -->
	         <td>
				  <logic:notEmpty name="patientVO" property="photoFileName">
				    <%-- <a href="patientDetailsNew.do?actionFlag=viewDocument&fileName=<bean:write name='patientVO' property='photoFileName'/>"
				       target="_blank"
				       title="<bean:write name='patientVO' property='photoFilePath'/>">
				       <bean:write name="patientVO" property="photoFileName"/>
				    </a> --%>
				     <bean:define id="photoFilePath" name="patientVO" property="photoFilePath" />
									<bean:define id="photoFileName" name="patientVO" property="photoFileName" />
									
									<a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= photoFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= photoFileName %>
									</a>
				  </logic:notEmpty>
				  <logic:empty name="patientVO" property="photoFileName">
				    No file choosen
				  </logic:empty>
			 </td>
	            
	           <td><label>Support Document</label></td>
	           <!--  <td>
	                 <input type="file" id="supportDocFile" name="supportDocFile"/></label>
	            </td> -->
	            <td>
				  <logic:notEmpty name="patientVO" property="supportFileName">
				      <bean:define id="supportFilePath" name="patientVO" property="supportFilePath" />
									<bean:define id="supportFileName" name="patientVO" property="supportFileName" />
									
									<a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= supportFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= supportFileName %>
									</a>
				  </logic:notEmpty>
				  
				  <logic:empty name="patientVO" property="supportFileName">
				    No file choosen
				  </logic:empty>
  
			   </td>
         </tr>
        <tr>
		  <c:choose>
		
		   <%--  <c:when test="${regFlag != 'Y' && lhsApproveFlag != 'Y'}"> --%>
		    <c:when test="${lhsApproveFlag == 'Y' && caseStatus != 'A'}">
		      <td><label>Upload Signed Document<span class="mandatory">*</span></label></td>
		      <td>
		        <input type="file" id="signedDocument" name="signedDocument" onchange="validateAttachment(this)" />
		      </td>
		      
		     <%--  <td>
				  <logic:notEmpty name="patientVO" property="signedFileName">
				    <bean:define id="signedFilePath" name="patientVO" property="signedFilePath" />
				    <bean:define id="signedFileName" name="patientVO" property="signedFileName" />
				    
				    <a href="javascript:void(0);"
				       onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= signedFilePath %>', 
				       'PatientRegPrintPage', 
				       'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
				      <%= signedFileName %>
				    </a>
				  </logic:notEmpty>
				
				  <logic:empty name="patientVO" property="signedFileName">
				    <input type="file" id="signedDocument" name="signedDocument" onchange="validateAttachment(this)" />
				  </logic:empty>
				</td> --%>
		    </c:when>
		
		    <c:when test="${lhsApproveFlag == 'Y' && caseStatus == 'A'}">
		      <td><label>Signed Document</label></td>
		        <td>
		        
		           <logic:notEmpty name="patientVO" property="signedFileName">
			                    <bean:define id="signedFilePath" name="patientVO" property="signedFilePath" />
									<bean:define id="signedFileName" name="patientVO" property="signedFileName" />
									
									<a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= signedFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= signedFileName %>
									</a>
		          </logic:notEmpty>
		          
		          <logic:empty name="patientVO" property="signedFileName">
				    No file choosen
				  </logic:empty>
				  
		      </td>
		    </c:when>
		
		  </c:choose>
		 <c:if test="${caseStatus == 'R'}">
		 
			        <td><label>Rejection Reason</label></td>
			        <td> 
			        <input type="text" name="rejReason" id="rejReason"
				        value="<bean:write name='patientVO' property='result'/>" readonly />
				        
			         <%-- <textarea rows="4" style="width: 100%;" readonly>
					    <bean:write name="patientVO" property="result"/>
					</textarea> --%>
		         </td>
		</c:if>
		 <c:if test="${caseStatus == 'REV'}">
		 
			        <td><label>Return Reason</label></td>
			        <td> 
			        <input type="text" name="rejReason" id="rejReason"
				        value="<bean:write name='patientVO' property='revertRemarks'/>" readonly />
		         </td>
		</c:if>
					  
		</tr>

    </table>
    <br>
    
   <c:if test="${regFlag != 'Y' }">
	    <div style="display: flex; justify-content: center; gap: 20px; margin-top: 20px;">
	         <c:if test="${lhsApproveFlag !='Y' && !(caseStatus eq 'R' 
                  or caseStatus eq 'REV' 
                  or caseStatus eq 'RA')}">
	            <input type="button" class="submit-btn" 
	               style="background-color: #4A90E2; color: white;" 
	               value="Recomended for Approve" onclick="fn_recomended_approve()" />
	               
	                <input type="button" class="submit-btn" 
	               style="background-color: #4A90E2; color: white;" 
	               value="Return" onclick="fn_revert()" />
	               
	               <input type="button" class="submit-btn" 
	               style="background-color: #c96868; color: white;" 
	               value="Reject" onclick="fn_reject1()" />
	         </c:if> 
	        <c:if test="${lhsApproveFlag =='Y' && !(caseStatus == 'R' || caseStatus == 'A')}">    
	        	<input type="button" class="submit-btn" 
	               style="background-color: #4A90E2; color: white;" 
	               value="Approve" onclick="submit_approve()" />
	               
	               <input type="button" class="submit-btn" 
	               style="background-color: #c96868; color: white;" 
	               value="Reject" onclick="fn_reject()" />
	       </c:if>
	       
	    </div>
  </c:if>

<!-- Rejection Reason Modal -->
<div id="rejectModal" style="display:none; position:fixed; top:30%; left:35%; background:#fff; padding:20px; border:1px solid #aaa; box-shadow: 0 0 10px #aaa; z-index:9999;">
    <h3>Enter Rejection Reason<span class="mandatory">*</span></h3>
    <br>
    <textarea id="rejectReason" rows="4" cols="40" placeholder="Enter reason here..."></textarea><br><br>
   <c:choose>
	  <c:when test="${lhsApproveFlag == 'Y'}">
	    <button onclick="fn_final_reject()" class="reject-btn" style="margin-left: 25%; background-color: #4A90E2; color: white;">Submit</button>
	  </c:when>
	  <c:otherwise>
	    <button onclick="submitReject()" class="reject-btn" style="margin-left: 25%; background-color: #4A90E2; color: white;">Submit</button>
	  </c:otherwise>
  </c:choose>
	   
   <button onclick="closeRejectModal()" class="reject-btn" style="margin-left: 10px; background-color: #c96868; color: white;;">Cancel</button>
</div>

<!-- Optional modal backdrop -->
<div id="modalBackdrop" style="display:none; position:fixed; top:0; left:0; height:100%; width:100%; background:rgba(0,0,0,0.5); z-index:9998;"></div>


<div id="revertModal" style="display:none; position:fixed; top:30%; left:35%; background:#fff; padding:20px; border:1px solid #aaa; box-shadow: 0 0 10px #aaa; z-index:9999;">
    <h3>Enter Return Reason<span class="mandatory">*</span></h3>
    <br>
    <textarea id="revertReason" rows="4" cols="40" placeholder="Enter reason here..."></textarea><br><br>
  
	    <button onclick="submitRevert()" class="revert-btn" style="margin-left: 25%; background-color: #4A90E2; color: white;">Submit</button>
	   
   <button onclick="closeModal()" class="revert-btn" style="margin-left: 10px; background-color: #c96868; color: white;;">Cancel</button>
</div>

<!-- second level approval -->
<div id="approveModal" style="display:none; position:fixed; top:30%; left:35%; background:#fff; padding:20px; border:1px solid #aaa; box-shadow: 0 0 10px #aaa; z-index:9999;">
    <h3>Enter Approval Remarks<span class="mandatory">*</span></h3>
    <br>
    <textarea id="approveRemarks" rows="4" cols="40" placeholder="Enter remarks here..."></textarea><br><br>
  
	    <button onclick="fn_approve()" class="approve-btn" style="margin-left: 25%; background-color: #4A90E2; color: white;">Submit</button>
	   
   <button onclick="closeApproveModal()" class="approve-btn" style="margin-left: 10px; background-color: #c96868; color: white;;">Cancel</button>
</div>
   
</body>
</html>
</fmt:bundle>




