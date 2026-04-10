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
            fileInput.setAttribute("disabled", true);
        });
    };
    
  //for view.
    function viewDocument(filePath) {
        window.open('./patientDetailsNew.do?actionFlag=viewLhsDocument&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
    }

    function fn_Print()
    {
    window.print();
    }
    function closeNrefresh()
    {
    	window.opener.close();
    	window.close();
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
     
        .reject-btn {
            padding: 3px 5px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        @media print {
		    .print_buttons {
		        display: none !important;
		    }
		}
        
    </style>
</head>
<body>
<br>

<table width="95%" style="margin:0 auto" class="tb print_table">


<tr><td>
	         <table  style="width:80%;margin:0 auto" >
			<tr  class="screen_header"><td width="15%" style="text-align:right;"><img border='0' src="images/telanganalogo.png"/></td>
				<td width="70%" style="font-size:22px;text-align:center;"><!-- <span style="font-size:14px;"></span><br /> -->Telangana Legislatures Health Scheme </td>
			 	 <td width="15%" style="text-align:left;"><img border='0' src="images/telangana_cm_right.png"/></td>  
				<!-- <td width="18%" style="font-size:22px;text-align:center;">&nbsp;</td> -->
			</tr>
</table> 
    
    
<logic:notEmpty name="registeredLhsPatientsList">
    <bean:define id="patientVO" name="registeredLhsPatientsList" />
</logic:notEmpty>
            <div style="text-align: center;"> <b>Registration Number: <bean:write name='patientVO' property='patientId'/> </b></div>

<div class="section-title" style="text-align: center;">MLA/MLC Personal Details</div>
    <table style="margin-top: 11px;">
		<tr>

			<td><label>Full Name </label></td>
			<td><input type="text" name="fullName" value="<bean:write name='patientVO' property='firstName'/>" /></td>

			<td><label>Father Name</label></td>
			<td><input type="text" name="fatherName"
				value="<bean:write name='patientVO' property='fatherName'/>" /></td>
		</tr>
		<tr>	
		<!-- 	<td><label>Gender</label></td>
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
					
			
			<td><label>Date of Birth</label></td>
			<td><input type="text" name="dob" id="dob" style="width: 120px;"
				value="<bean:write name='patientVO' property='dateOfBirth'/>" /></td>
		</tr>
		
		 <tr>
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
			        <label>Constituency Name </label>
			    </td>
			    <td>
			       <bean:define id="constituency" name="patientVO" property="constituency" />
                  <input type="text" name="constType" id="constType" value="<%= constituency %>" readonly />
			    </td>
			</logic:present> 
		</tr>
		<tr>	
            <td><label>MLA/MLC/PSO ID Number </label></td>
          <!--   <td><input type="text" name="lhsId" /></td> -->
            <td><input type="text" name="lhsId" id="lhsId" style="width: 120px;"
				value="<bean:write name='patientVO' property='cardNo'/>" /></td>
				
				<td><label>Registration Number </label></td>
			 <td><input type="text" name="lhsId" id="lhsId" style="width: 120px;"
				value="<bean:write name='patientVO' property='patientId'/>" />
			 </td>
       </tr>
       <tr>
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
		</tr>
		<%-- <tr>
			  <td><label>Registartion Number </label></td>
			 <td><input type="text" name="lhsId" id="lhsId" style="width: 120px;"
				value="<bean:write name='patientVO' property='patientId'/>" />
			 </td>
		   </tr> --%>
    </table>
    
    <logic:notEmpty name="registeredDependentsList">
     <div class="section-title" style="text-align: center;" id="dependentDiv">Dependent Details</div>  
       <table id="dependentTable" border="1" style="margin-top:20px; width: 100%;" >
		  <thead style="background: #7aabe4;color: white;">
		    <tr>
		      <th>Dependent Name</th>
		      <th>Mobile Number</th>
		      <th>Gender</th>
		      <th>Date of Birth</th>
		      <th>Relationship</th>
		      <th>Marital Status</th>
		      <th>Aadhaar Number</th>
		      <th>Aadhaar Document</th> 
		    </tr>
		  </thead>
		  <tbody>
		       <logic:iterate id="dependentVO" name="registeredDependentsList">
	                <tr>
	                    <td><bean:write name="dependentVO" property="firstName" /></td>
	                    <td><bean:write name="dependentVO" property="mobile" /></td>
	                    <td><bean:write name="dependentVO" property="gender" /></td>
	                    <td><bean:write name="dependentVO" property="dateOfBirth" /></td>
	                    <td><bean:write name="dependentVO" property="type1" /></td>
	                    <td><bean:write name="dependentVO" property="memberType" /></td>
	                    <td><bean:write name="dependentVO" property="aadharID" /></td>
	                    <td><bean:write name="dependentVO" property="aadharFileName" /></td>
	                    
	                    <%-- <td>
	                        <logic:notEmpty name="dependentVO" property="aadharFileName">
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
	                    </td> --%>
	                   
	                </tr>
             </logic:iterate>
		  </tbody>
	</table>
	
    </logic:notEmpty>

    <div class="section-title" style="text-align: center;">MLA/MLC Address Details</div>
    <table style="margin-top: 11px;">
        <tr>
            <td><label>Permanent Address</label></td>
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
           </tr>
           <tr>
            <td><label>Constituency Address </label></td>
            <td>
             <!-- <input type="text" name="constAdd" /> -->
             <input type="text" name="constAdd" id="constAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='constituencyAddress'/>" />
            </td>
             <td><label>Email ID</label></td>
			<td><input type="text" name="emailId" id="email"
				value="<bean:write name='patientVO' property='eMailId'/>" /></td>
        </tr>
        <tr>  
           <td id="districtMla"><label>District</label></td>
			<td id="districtMlaId">
			    <logic:present name="lhsMemberDistrict">
			        <bean:define id="district" name="lhsMemberDistrict" />
                    <input type="text" name="district" id="district" value="<%= district %>" readonly />
			    </logic:present>
			      <logic:notPresent name="lhsMemberDistrict">
			        <input type="text" name="district" id="district" value="" readonly />
			    </logic:notPresent>
						</td>
            
          
            
            <td><label>PIN Code </label></td>
			<td>
			  <c:choose>
			    <c:when test="${empty patientVO.pinCode || patientVO.pinCode == 0}">
			      <input type="text" name="pinCode" id="pinCode" style="width: 120px;" value="" />
			    </c:when>
			    <c:otherwise>
			      <input type="text" name="pinCode" id="pinCode" style="width: 120px;" value="${patientVO.pinCode}" />
			    </c:otherwise>
			  </c:choose>
			</td>
			                        
         </tr>
       <%--   <tr>
          <td><label>Aadhaar Card</label></td>
           <td><bean:write name="patientVO" property="aadharFileName" /></td>
			
             <td><label>MLA/MLC Photo</label></td>
	          
	          <td><bean:write name="patientVO" property="photoFileName" /></td>
	        
	           <td><label>Support Document</label></td>
	           
	           <td><bean:write name="patientVO" property="supportFileName" /></td>
         </tr> --%>
       <tr>
        <td> <br> <br> <b>Date:</b> </td>
        <td> <br> <br> <b style="margin-left:224%">Signature</b></td></tr>
       <tr>
       <td>
        <b> Place:</b>
       </td>
       </tr>
       

    </table>
 <table width="100%" style="margin:0 auto" class="tb print_table">
<tr class="print_buttons">
<td style="text-align:center">
<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
</tr>
</table>
</td>
</tr>
</table>
</body>
</html>
</fmt:bundle>




