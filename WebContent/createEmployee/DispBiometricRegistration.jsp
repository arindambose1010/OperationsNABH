<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE struts-config PUBLIC "-//Apache Software Foundation//DTD Struts Configuration 1.2//EN"
                               "http://struts.apache.org/dtds/struts-config_1_2.dtd">
<struts-config>
 <data-sources/>
 <form-beans>
  <form-bean name="chronicOpForm" type="com.ahct.chronicOp.form.ChronicOPForm"/>
  <form-bean name="loginForm" type="com.ahct.login.form.LoginForm"/>
  <form-bean name="empHistoryForm" type="com.ahct.login.form.EmployeeForm"/>
  <form-bean name="loginRequestForm" type="com.ahct.login.form.LoginRequestForm"/>
  <form-bean name="casesSearchForm" type="com.ahct.caseSearch.form.CasesSearchForm"/>
  <form-bean name="casesSearchFormClaims" type="com.ahct.caseSearch.form.CasesSearchForm"/>
  <form-bean name="claimsFlowForm" type="com.ahct.claims.form.ClaimsFlowForm"/>
  <form-bean name="attachmentForm" type="com.ahct.attachments.form.AttachmentForm"/>
  <form-bean name="followUpForm" type="com.ahct.followup.form.FollowUpForm"/>
  <form-bean name="clinicalNotesForm" type="com.ahct.preauth.form.PreauthClinicalNotesForm"/>
  <form-bean name="preauthDetailsForm" type="com.ahct.preauth.form.PreauthDetailsForm"/>
  <form-bean name="panelDocPayForm" type="com.ahct.panelDoctor.form.PanelDocPayForm"/>
  <form-bean name="medicalAuditForm" type="com.ahct.medicalAudit.form.MedicalAuditForm"/>
  <form-bean name="flaggingForm" type="com.ahct.flagging.form.FlaggingForm"/>
  <form-bean name="annualCheckUpForm" type="com.ahct.annualCheckUp.form.AnnualCheckUpForm"/>
  <form-bean name="patientForm" type="com.ahct.patient.form.PatientForm"/>
  <form-bean name="telephonicForm" type="com.ahct.telephonic.form.TelephonicForm"/>
  <form-bean name="ahcClaimsForm" type="com.ahct.annualCheckUp.form.AhcClaimsForm"/>
  <form-bean name="schedulersForm" type="com.ahct.schedulers.form.SchedulersForm"/>
  	  <form-bean name="adminSanctionForm" type="com.ahct.CEO.form.AdminSanctionForm"/>
  	   <form-bean name="ceoApprovalsForm" type="com.ahct.CEO.form.CeoApprovalsForm"/>
	<form-bean name="ceoWorkListForm"  type="com.ahct.CEO.form.CeoWorkListForm"/>
	<form-bean name="cardSearchForm"  type="com.ahct.cardSearch.form.cardSearchForm"/>
		<form-bean name="bioMetricForm"  type="com.ahct.bioMetric.form.BioMetricForm"/>
				<form-bean name="bioMetricForm"  type="com.ahct.bioMetric.form.BioMetricForm"/>
						<form-bean name="createEmployeeForm" type="com.ahct.createEmployee.form.CreateEmployeeForm"/>
				
		

 </form-beans>
 <global-exceptions>
  <exception handler="com.ahct.common.action.CustomExceptionHandler"
   key="error.global.message" path="/patient/Error.jsp" type="java.lang.Exception"/>
 </global-exceptions>
 <global-forwards>
  <forward name="openFile" path="/common/openFile.jsp"/>
  <forward name="openExcelFile" path="/common/openExcelFile.jsp"/>
  <forward name="ajaxResult" path="/common/ajaxResult.jsp"/>
  <forward name="json" path="/common/json.jsp"/>
  <forward name="panelDoctor" path="/finance/panelDoctor.jsp"/>
  <forward name="sessionExpire" path="/login/sessionInvalid.jsp"/>
  <forward name="index" path="/login/login.jsp"/>
 </global-forwards>
 <action-mappings>
  <!--Added by ishank for Login integration 05-june-2013 -->
  <action attribute="loginForm" name="loginForm" path="/loginAction"
   scope="request" type="com.ahct.login.controller.LoginAction" validate="false">
   <forward name="frame" path="/login/HomePageTest.jsp"/>
   <forward name="frameCEO" path="/login/ceoHomePage.jsp"></forward>
    <forward name="frameCEOOpenAm" path="/login/ceoHomePageOpenAm.jsp"></forward>
   <forward name="frameOpenAm" path="/login/HomePageOpenAMBootstrap.jsp"/>
   <forward name="login" path="/login/login.jsp"/>
   <forward name="indexOpenAM" path="indexOpenAM.jsp"/>
   <forward name="ChangePasswordPage" path="/login/ChangePassword.jsp"/>
   <forward name="defaultPage" path="/login/defaultPage.jsp"/>
   <forward name="onload" path="/login/login.jsp"/>

   <forward name="indexOpenAM" path="/indexOpenAM.jsp"/>

   <forward name="openAMLogout" path="/login/openAMLogout.jsp"/>

   <forward name="noAuthorizationPage" path="/login/noAuthorizationPage.jsp"/>
   <forward name="sessionExpire" path="/login/sessionInvalid.jsp"/>
   <forward name="UpdateProfilePage" path="/login/updateProfile.jsp"/>
   <forward name="digitalCertifiCateAuthenticate" path="/login/DigitalCertificate/digitalCertifiCateAuthenticate.jsp"/>
   <forward name="OTPAuthenticate" path="/login/ceoAuthenticate.jsp"/>
  </action>
  <!-- END 
Added by ishank for Change Password integration 10-june-2013 -->
  <action attribute="loginRequestForm" name="loginRequestForm"
   path="/ChangePwdReq" scope="request"
   type="com.ahct.login.controller.ChangePwdRequestAction" validate="false">
   <forward name="ChangePasswordPage" path="/login/ChangePassword.jsp"/>
  </action>
  <!-- END 
Added by Bhaskar for Emp History Details 24-Jan-2017 -->
  <action attribute="empHistoryForm" name="empHistoryForm"
   path="/empHistory" scope="request"
   type="com.ahct.login.controller.EmployeeHistoryAction" validate="false">
   <forward name="EmpHistoryPage" path="/login/EmployeeHistory.jsp"/>
  </action>
  <!-- END 
Added by ishank for Cases Search integration in preauth 05-june-2013 -->
  <action attribute="casesSearchFormClaims" name="casesSearchFormClaims"
   path="/casesSearchAction" scope="request"
   type="com.ahct.caseSearch.action.CasesSearchAction" validate="false">
   <forward name="search" path="/caseSearch/CasesViewSearchBootstrap.jsp"/>
   <forward name="nabhReport" path="/caseSearch/NabhReport.jsp"/>
   <forward name="PreauthCaseDisplayFrame" path="/Preauth/preauthCaseDisplayFrame.jsp"/>
   <forward name="deathCasesView" path="/caseSearch/deathCasesViewBootstrap.jsp"/>
   <forward name="caseNotFound" path="/caseSearch/caseNotFound.jsp"/>
   <forward name="ceoSearch" path="/CEO/operationsWorkflow.jsp"/>
   <forward name="csvDownloads" path="/caseSearch/csvDownloads.jsp"/>
  </action>
  <!-- END 
 START By ishank for ClaimsFlow -->
  <action attribute="claimsFlowForm" name="claimsFlowForm"
   path="/ClaimsFlow" scope="request" type="com.ahct.claims.action.ClaimsFlowAction">
   <forward name="viewClaimPage" path="/claimsFlow/viewClaimPage.jsp"/>
   <forward name="PreAuthSuccessPage" path="/actions/PreAuthSuccessPage.jsp"/>
   <forward name="claimsPayment" path="/claimsFlow/claimsPayment.jsp"/>
   <forward name="viewClaimDtlsPayment" path="/claimsFlow/viewClaimDtlsPayment.jsp"/>
   <forward name="errClaimsPayment" path="/claimsFlow/errClaimsPayment.jsp"/>
   <forward name="tdsClaimsPayment" path="/claimsFlow/tdsClaimsPayment.jsp"/>
    <forward name="chronicOpClaimsPayment" path="/claimsFlow/chronicOpClaimsPayment.jsp"/>
  </action>
  <!-- END By ishank for ClaimsFlow 
  Added by Sravanthi for Update Profile -->
  <action attribute="loginRequestForm" name="loginRequestForm"
   path="/updateProfile" scope="request"
   type="com.ahct.login.controller.UpdateProfileAction" validate="false">
   <forward name="UpdateProfilePage" path="/login/updateProfile.jsp"/>
  </action>
  <!-- End by Sravanthi for update profile 
Added by ishank for Attachments 19-oct-2012 -->
  <action attribute="attachmentForm" name="attachmentForm"
   path="/attachmentAction" scope="request"
   type="com.ahct.attachments.action.AttachmentAction" validate="false">
   <forward name="onload" path="/attachments/Attachment.jsp"/>
   <forward name="caseAttachments" path="/attachments/casesAttachment.jsp"/>
   <forward name="ahcAttachments" path="/AnnualCheckUp/AhcAttachments.jsp"/>
   <forward name="chronicAttachments" path="/ChronicOP/chronicAttachments.jsp"/>
   <forward name="claimPaymentAttachments" path="/attachments/claimPayAttachment.jsp"/>
  </action>
  <!--End 
 Start for Follow UP by Ishank -->
  <action attribute="followUpForm" name="followUpForm"
   path="/followUpAction" scope="request" type="com.ahct.followup.action.FollowUpAction">
   <forward name="followUpInit" path="/FollowUp/followUpInit.jsp"/>
   <forward name="cochFollowUpinit" path="/FollowUp/initiateCochlearFollowUp.jsp"/>
   <forward name="folowUpsearch" path="/FollowUp/followUpCaseSearchBootstrap.jsp"/>
   <forward name="FolowUpClaimForm" path="/FollowUp/followUpClaimForm.jsp"/>
   <forward name="fpclaimsPayment" path="/FollowUp/fpclaimsPayment.jsp"/>
   <forward name="viewFPDtlsPayPage" path="/FollowUp/viewFPPayPage.jsp"/>
   <forward name="viewClinicalData" path="/FollowUp/viewClinicalData.jsp"/>
   <forward name="printPrescription" path="/FollowUp/printPrescription.jsp"/>
   <forward name="printDTRS" path="/FollowUp/printDTRS.jsp"/>
   <forward name="caseNotFound" path="/caseSearch/caseNotFound.jsp"/>
   <forward name="cochfollowUpViewClaim" path="/FollowUp/viewCochlearFPClaims.jsp"/>
   <forward name="cochlearFlpClaim" path="/FollowUp/cochlearFollowUpClaim.jsp"/>
   
  </action>
  <action path="/ceoWorklist"
             type="com.ahct.CEO.action.PendingApprovals"
            name="ceoApprovalsForm" attribute="ceoApprovalsForm"
            scope="request" validate="false">
            <forward name="pendingCrApprvls"
               path="/ChangeMgmt/pendingApprvls.jsp"/>

     <forward name="formViewDetails"
               path="/ChangeMgmt/CrDetails.jsp"/>
			   </action>
			   
  
  <!-- End for Follow UP by Ishank 
 Added by satish for user manuals -->
  <action path="/trainingMaterialsAction" scope="session" type="com.ahct.common.action.TrainingMaterialsAction"/>
  <!-- end  
Start by Anitha for cases Approval  -->
  <action attribute="casesSearchForm" name="casesSearchForm"
   path="/casesApprovalAction" scope="request"
   type="com.ahct.preauth.action.CasesApprovalAction" validate="false">
   <forward name="PreauthCaseDisplayFrame" path="/Preauth/preauthCaseDisplayFrame.jsp"/>
   <forward name="pasthistory" path="/Preauth/pastHistory.jsp"/>
   <forward name="empPenCaseSearch" path="/Preauth/EmpPenCaseSearch.jsp"/>
  </action>
  <!-- Start by Anitha for Clinical notes -->
  <action name="clinicalNotesForm" path="/clinicalNotesAction"
   scope="request" type="com.ahct.preauth.action.preauthClinicalNotesAction">
   <forward name="preauthClinicalNotes" path="/Preauth/preauthClinicalNotes.jsp"/>
   <forward name="json" path="/Preauth/json.jsp"/>
   <forward name="surgeryUpdated" path="/Preauth/surgeryResult.jsp"/>
   <forward name="dischargeSummryForm" path="/Preauth/PrintDischarge.jsp"/>
   <forward name="patSatisfactionLetter" path="/Preauth/PatientSatisfactionLetter.jsp"/>
  </action>
  <!-- Added by sathish adepufor Create Employees -->
  	 <action path="/createEmployee" name="createEmployeeForm"
			type="com.ahct.createEmployee.action.CreateEmployeeAction" scope="request"
			validate="false">
			<forward name="createEmp" path="/createEmployee/CreateWCEmployee.jsp" />
			<forward name="empCreation" path="/createEmployee/CreateWCEmployee.jsp" />
			<forward name="newVacantPostion" path="/createEmployee/newVacantPosition.jsp" />
			<forward name="searchEmployees"  path="/createEmployee/SearchEmployee.jsp" />
			<forward name="searchResults"  path="/createEmployee/SearchResults.jsp" />
			<forward name="updateEmployee"  path="/createEmployee/updateEmployee.jsp" />
			<forward name="allocateEmployee"  path="/createEmployee/allocateEmployee.jsp" />
			<forward name="groupEmail" path="/createEmployee/bulkUsersEmail.jsp"/>
			<forward name="emailCreation"  path="/createEmployee/EmailCreation.jsp" />
			<forward name="ajaxResult" path="/common/ajaxResult.jsp"></forward>
			<forward name="addDesignation" path="/createEmployee/employeeDesignation.jsp" />
			<forward name="searchDesgList" path="/createEmployee/searchDesg.jsp" />
		</action>
		<!-- End by Sathish adepu for Create Employees  -->
  <!--Added by lakshmi for Preauth Details  12-dec-2012  -->
  <action attribute="preauthDetailsForm" name="preauthDetailsForm"
   path="/preauthDetails" scope="request"
   type="com.ahct.preauth.action.PreauthDetailsAction" validate="false">
   <forward name="preauthDtls" path="/Preauth/PreauthDetails.jsp"/>
   <forward name="preauthDtlsEhf" path="/Preauth/PreauthDetailsEHF.jsp"/>
   <forward name="caseAttachments" path="/Preauth/caseAttachments.jsp"/>
   <forward name="pexQuestions" path="/Preauth/PexQuestionnaire.jsp"/>
   <forward name="preauthDtlsEhf1" path="/Preauth/PreauthDetailsEHF1.jsp"/>
   <forward name="getPRFForm" path="/Preauth/PRFProntForm.jsp"/>
   <forward name="onlineCaseSheet" path="/Preauth/OnlineCaseSheet.jsp"/>
   <forward name="ipCaseCopy" path="/patient/ipCaseCopy.jsp"/>
   <forward name="opCaseCopy" path="/patient/opCaseCopy.jsp"/>
   <forward name="ajaxResult" path="/common/ajaxResponse.jsp"/>
   <forward name="viewTelephonicDetails" path="/patient/viewTelephonicDtls.jsp"/>
   <forward name="cochlearQuesttionaire" path="/Preauth/cochlearQuestionnaire.jsp"/>
   <forward name="ceoSearch" path="/CEO/operationsWorkflow.jsp"/>
    <forward name="operationsPreauth" path="/CEO/operationsPreauth.jsp"/>
     <forward name="drugsView" path="/Preauth/DrugsView.jsp"/>
  </action>
  <!-- added by Prasanthi -->
  <action attribute="patientForm" name="patientForm"
   path="/patientDetails" scope="request"
   type="com.ahct.patient.action.PatientAction" validate="false">
   <forward name="InPatientRegistration" path="/patient/InPatientRegistration.jsp"/>
   <forward name="patient" path="/patient/patient.jsp"/>
   <forward name="ajaxResult" path="/common/ajaxResponse.jsp"/>
   <forward name="viewRegisteredPatients" path="/patient/ViewRegisteredPatients.jsp"/>
   <forward name="patientDetails" path="/patient/PatientDetailsNew.jsp"/>
    <forward name="dentalPatientDetails" path="/patient/DentalPatientDetails.jsp"/>
   <forward name="patientDetailsNims" path="/patient/PatientDetailsNims.jsp"/>
   <forward name="telephonicPatientEntry" path="/patient/TelephonicPatientEntry.jsp"/>
   <forward name="viewTelephonicDetails" path="/patient/viewTelephonicDtlsReg.jsp"/>
   <forward name="telephonicSucess" path="/patient/telephonicSucess.jsp"/>
   <forward name="printDetails" path="/patient/PrintDetails.jsp"/>
   <forward name="error" path="/patient/Error.jsp"/>
   <forward name="caseGeneratedSlip" path="/patient/CaseGeneratedSlip.jsp"/>
   <forward name="opCaseCopy" path="/patient/opCaseCopyReg.jsp"/>
   <forward name="opCaseCopyTG" path="/patient/OpCaseCopyRegTG.jsp"/>
   <forward name="opPharmaCaseCopyTG" path="/patient/PharmaOpCaseCopyReg.jsp"/>
   <forward name="ipCaseCopy" path="/patient/ipCaseCopyReg.jsp"/>
   <forward name="dtrsForm" path="/patient/DTRSForm.jsp"/>
   <forward name="dispDentalDtrsForm" path="/patient/dispDentalDTRSForm.jsp"/>
   <forward name="dentaldtrsForm" path="/patient/DentalDTRSForm.jsp"/>
   <forward name="opRegPatientsView" path="/patient/OpRegPatientsView.jsp"/>
   <forward name="opPharmPatientsView" path="/patient/OpPharmPatientsView.jsp"/>
   <forward name="noAuthorisation" path="/patient/NoAuthorization.jsp"/>
   <forward name="DispnsryPatientDtls" path="/patient/DispnsryPatientDtls.jsp"/>
   <forward name="ipmCaseCopy" path="/patient/ipmCaseCopyReg.jsp"/>
   <forward name="labReportsPage" path="/patient/labReportsPage.jsp"/>
   <forward name="viewLabReports" path="/patient/viewLabReports.jsp"/>
   <forward name="viewAllLabReports" path="/patient/viewAllLabReports.jsp"/>
   <forward name="DispDentalPatientDetails" path="/patient/DispDentalPatientDetails.jsp"/>
   <forward name="drugsInventory" path="/patient/drugsInventoryUpdate.jsp"/>
   <forward name="drugsOutStandingBalance" path="/patient/drugsOutStandingBalance.jsp"/>
   <forward name="addOrUpdateDrugs" path="/Preauth/addOrUpdateDrugs.jsp"/>         
  </action>
  <!-- Start for Panel Doctor by Smita -->
  <action attribute="panelDocPayForm" name="panelDocPayForm"
   path="/panelDocPay" scope="request"
   type="com.ahct.panelDoctor.action.PanelDocPayAction" validate="false">
   <forward name="panelDocPay" path="/panelDoctor/panelDoctorPay.jsp"/>
   <forward name="panelDocCases" path="/panelDoctor/panelDoctorCaseList.jsp"/>
   <forward name="panelDocPmntCEO" path="/panelDoctor/panelDocPmntCEO.jsp"/>
   <forward name="panelDocTDSPmnt" path="/panelDoctor/panelDocTDSPmnt.jsp"/>
   <forward name="panelDocRept" path="/panelDoctor/panelDocRept.jsp"/>
   
  </action>
  <!-- End for Panel Doctor by Smita 
 Start for Medical Audit by Srujana -->
  <action attribute="medicalAuditForm" name="medicalAuditForm"
   path="/medicalAudit" scope="request" type="com.ahct.medicalAudit.action.MedicalAuditAction">
   <forward name="questionnaire" path="/medicalAudit/questionnaire.jsp"/>
   <forward name="auditSearch" path="/medicalAudit/medicalAuditSearch.jsp"/>
   <forward name="maWorklist" path="/medicalAudit/medicalAuditWorklist.jsp"/>
   <forward name="auditedCasesView" path="/medicalAudit/auditedCasesView.jsp"/>
  </action>
  <action attribute="telephonicForm" name="telephonicForm"
   path="/telephonicAction" scope="request"
   type="com.ahct.telephonic.action.TelephonicAction" validate="false">
   <forward name="telephonicSearch" path="/patient/TelephonicIntimation.jsp"/>
   <forward name="telephonicIntimationReg" path="/patient/TeleIntimationReg.jsp"/>
  </action>
  <action path="/digitalCertificate" scope="request"
   type="com.ahct.digitalCertificate.action.DigitalCertificateAction" validate="false">
   <forward name="signUpUser" path="/digitalCertificate/signClient.jsp"/>
  </action>
  <!-- prasanthi 
  start for flagging by kalyan -->
  <action attribute="flaggingForm" name="flaggingForm"
   path="/flaggingAction" scope="request"
   type="com.ahct.flagging.action.FlaggingAction" validate="false">
   <forward name="flagging" path="/Flagging/Flagging.jsp"/>
   <forward name="flaggingAttach" path="/Flagging/FlaggingAttach.jsp"/>
   <forward name="viewFlaggedCases" path="/Flagging/viewFlaggedCases.jsp"/>
  </action>
  <!-- end for flagging by kalyan 
  start for Annual CheckUp by kalyan -->
  <action attribute="annualCheckUpForm" name="annualCheckUpForm"
   path="/annualCheckUpAction" scope="request"
   type="com.ahct.annualCheckUp.action.AnnualCheckUpAction" validate="false">
   <forward name="annualCheckUpReg" path="/AnnualCheckUp/AnnualCheckUp.jsp"/>
   <forward name="annualPatient" path="/AnnualCheckUp/AnnualPatient.jsp"/>
   <forward name="viewAhc" path="/AnnualCheckUp/viewAhcPage.jsp"/>
   <forward name="viewAhcTG" path="/AnnualCheckUp/ViewAhcPageTG.jsp"/>
   <forward name="annualPatientsView" path="/AnnualCheckUp/AnnualPatientsView.jsp"/>
   <forward name="ahcClaimsFramePage" path="/AnnualCheckUp/AhcClaimsViewFramePage.jsp"/>
   <forward name="ahcPrintPage" path="/AnnualCheckUp/AhcPrintPage.jsp"/>
   <forward name="annualPrintDetails" path="/AnnualCheckUp/AnnualPrintDetails.jsp"/>
   <forward name="ahcClaimCases" path="/AnnualCheckUp/AHCClaimCases.jsp"/>
   <forward name="viewAhcTGNims" path="/AnnualCheckUp/ViewAhcPageTGNIMS.jsp"/>
  </action>
  <!-- Added for AHC Claims by Gayathri -->
  <action attribute="ahcClaimsForm" name="ahcClaimsForm"
   path="/ahcClaimsAction" scope="request"
   type="com.ahct.annualCheckUp.action.AhcClaimsAction" validate="false">
   		<forward name="claimsPage" path="/AnnualCheckUp/AhcClaimsPage.jsp"/>
    	<forward name="claimsPaymentPage" path="/AnnualCheckUp/EoPayment.jsp"/>
    	<forward name="ahcAttachments" path="/AnnualCheckUp/AhcAttachments.jsp"/>
  </action>


<!-- added by venkatesh for chronic op cases -->

<action path="/chronicAction" type="com.ahct.chronicOp.action.ChronicOpAction"
			scope="request" name="chronicOpForm" attribute="chronicOpForm"
			validate="false">
			<forward name="chronicPatientDetails" path="/ChronicOP/chronicPatientDetails.jsp" />
			<forward name="chronicOpReg" path="/ChronicOP/ChronicOPReg.jsp"/>
			<forward name="patient" path="/ChronicOP/chronicPatientPrint.jsp"></forward> 
			<forward name="chronicOpcaseCopy" path="/ChronicOP/chronicOpCaseCopy.jsp"></forward> 
			<forward name="chronicDisplayFrame" path="/ChronicOP/chronicOpCasesDisplayFrame.jsp"></forward>
			<forward name="chronicOpDtls" path="/ChronicOP/chronicOpDtls.jsp"></forward>
			<forward name="chronicOpclaimForm" path="/ChronicOP/chronicOpclaimForm.jsp"></forward>
			<forward name="noAuthorizationPage" path="/login/noAuthorizationPage.jsp" />
			<forward name="chronicPatient" path="/ChronicOP/ChronicPatient.jsp"/>
			<forward name="chronicPrintDetails" path="/ChronicOP/ChronicPrintDetails.jsp"/>
			<forward name="viewChronicOpPatients" path="/ChronicOP/ViewChronicOPPatients.jsp"/>
            <forward name="coClaimCases" path="/ChronicOP/COClaimCases.jsp"/>
            <forward name="chronicOPCasesView" path="/ChronicOP/chronicOPCasesView.jsp"/>
			<forward name="chronicOPClinicalNotes" path="/ChronicOP/chronicOpClinicalNotes.jsp"/>
			<forward name="chronicOPPrescription" path="/ChronicOP/ChronicOpPrescription.jsp"/>
		</action>
 
 <!--end of chronic op cases -->
 
  <!-- Added by Grandhi Namratha for Admin Sanction Workflow -->


        <action path="/adminSanction"  name="adminSanctionForm"  
            type="com.ahct.CEO.action.AdminSanctionAction"
            scope="request" validate="false">
           <forward name="sanctionForm" path="/CEO/adminSanctionWF.jsp"></forward>
           <forward name="sanctionWorkFlow" path="/CEO/sanctionWorkFlow.jsp"></forward>
            <forward name="trackStatus" path="/CEO/viewSanctionStatus.jsp"></forward>
           <forward name="printSanctionForm" path="/CEO/printSancForm.jsp"></forward>
            <forward name="sanctionWorkFlowCEO" path="/CEO/ceoSanctionWorkFlow.jsp"></forward>
            <forward name="dashBoard" path="/CEO/ceoDashBoard.jsp"></forward>
           
           
        </action>
		<!-- Added by Sreedevi for Empanelment -->
		<action path="/CeoWorkListAction" 
            type="com.ahct.CEO.action.CeoWorkListAction" 
            name="ceoWorkListForm" attribute="ceoWorkListForm"
            scope="request">            
            <forward name="ceoWorkList" path ="/CEO/ceoWorkList.jsp" />
			<forward name="EdcCeoWorklist" path ="/CEO/EdcCeoWorklist.jsp" />			
			<forward name="aprvlsPage" path ="/CEO/aprvlsPage.jsp" />			
			<forward name="edcCeoRemarksPage" path ="/CEO/edcCEORemarks.jsp" />
       </action>
	<!--End of Empanelment-->

<!-- added by SrKalyan for Schedulers Link -->
<action path="/schedulersAction" type="com.ahct.schedulers.action.SchedulersAction" name="schedulersForm" attribute="schedulersForm" 
		scope="request" validate="false">
		<forward name="Schedulers" path="/Schedulers/schedulersLinks.jsp"></forward>
</action>
<!-- end of Schedulers Link -->

<!-- added by ramalakshmi for cardSearch  -->
<action path="/cardSearchAction" type="com.ahct.cardSearch.action.cardSearchAction" name="cardSearchForm" attribute="cardSearchForm" 
		scope="request" validate="false">
		<forward name="cardSearch" path="/cardSearch/cardSearchPage.jsp"></forward>
		<forward name="jrnlstCardPage" path="/cardSearch/cardViewPage.jsp"/>
		<forward name="empCardPage" path="/cardSearch/empCardViewPage.jsp"/>
</action>

<!-- added by ramalakshmi for biometric  -->
<action path="/bioMetricAction" type="com.ahct.bioMetric.action.BioMetricRegistrationAction" name="bioMetricForm" attribute="bioMetricForm" 
		scope="request" validate="false">
<forward name="EnrollmentPage" path="/BioMetric/BioMetricRegistration.jsp"></forward>
 <forward name="afterlogin" path="/login/afterLoginTest.jsp"/>
<forward name="biometricAttendance" path="/BioMetric/BiometricAttendance.jsp"/>
<forward name="biometricReport" path="/BioMetric/BiometricReport.jsp"/>
<forward name="biometricAttView" path="/BioMetric/BiometricAttendanceView.jsp" />
</action>

<!--  end of BioMetric -->

<action attribute="patientForm" name="patientForm"
   path="/tokenAction" scope="request"
   type="com.ahct.patient.action.TokenAction" validate="false">
   <forward name="showTokenNos" path="/showTokenNumbers.jsp"/>
   </action>
 </action-mappings>
 <controller/>
 <message-resources parameter="ApplicationResources"/>
</struts-config>
