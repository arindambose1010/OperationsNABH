package com.ahct.patient.form;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.LabelVO;
import com.ahct.patient.vo.SpecialityVO;

public class PatientForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String msg;
	private String diag;
	private List<LabelBean> appointmentsList;
	public String getDiag() {
		return diag;
	}
	public void setDiag(String diag) {
		this.diag = diag;
	}
	private String distributor;
    private String drugTypeID;
	private String searchType;
	private String empCode;
	private String dispname;
	private String robotSurg;
	private String medicalSpclty;
	private String errMsg;
	private String cardNo;
	private String card_type;
	private String aisType;
	private String servType;
	private String head;
	private String cardIssueDt;
	private String dateOfBirth;
	private String ageString;
	private String fname;
	private String fname1;
	private String destDate;
	private List<LabelBean> indentedPOList;
	private String status;
	List<CommonDtlsVO> drugXlList= new ArrayList<CommonDtlsVO>();//Chandana - 8251 - Added this
	private FormFile attachment;
	
	private String DSTRBTRNAME;
		
	public String getDSTRBTRNAME() {
		return DSTRBTRNAME;
	}
	public void setDSTRBTRNAME(String dSTRBTRNAME) {
		DSTRBTRNAME = dSTRBTRNAME;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDestDate() {
		return destDate;
	}
	public void setDestDate(String destDate) {
		this.destDate = destDate;
	}
	//Chandana - 8251 - Setters and getters
	public List<CommonDtlsVO> getDrugXlList() {
		return drugXlList;
	}
	public void setDrugXlList(List<CommonDtlsVO> drugXlList) {
		this.drugXlList = drugXlList;
	}

	public FormFile getAttachment() {
		return attachment;
	}
	public void setAttachment(FormFile attachment) {
		this.attachment = attachment;
	}
	private String mname;
	private String fromDetailed;
	private List<LabelBean> drugReportList;	
	private List<LabelBean> returnVendorList;
	private List<LabelBean> creditNoteList;
	private List<LabelBean> wellnesscenters;
	private List<LabelBean> indentNolist;
	private String wellnesscenter;
	private String dName;
	private String dType;
	private String UserName;
	private List<LabelBean> POList;	
	public List<LabelBean> getDrugReportList() {
		return drugReportList;
	}
	public String getdName() {
		return dName;
	}
	public void setdName(String dName) {
		this.dName = dName;
	}
	public String getdType() {
		return dType;
	}
	public void setdType(String dType) {
		this.dType = dType;
	}
	public void setDrugReportList(List<LabelBean> drugReportList) {
		this.drugReportList = drugReportList;
	}
	public String getFromDetailed() {
		return fromDetailed;
	}
	public void setFromDetailed(String fromDetailed) {
		this.fromDetailed = fromDetailed;
	}
	private String lname;
	private String eMailId;
	private String gender;
	private String child;
	private String months;
	private String days;
	private String years;
	private String ayushSubg;
	private String ayushSubt;
	private String aisRemarks;
	private String contractYear;
	private String itemId;
	public String getAyushSubg() {
		return ayushSubg;
	}
	public void setAyushSubg(String ayushSubg) {
		this.ayushSubg = ayushSubg;
	}
	public String getAyushSubt() {
		return ayushSubt;
	}
	public void setAyushSubt(String ayushSubt) {
		this.ayushSubt = ayushSubt;
	}
	private String caste;
	private String maritalStatus;
	private String relation;
	private String occupation;
	private String contactno;
	private String slab;
	private String serType;
	private String ayushID;
	private String commCheck;
	private String comm_hno;
	private String comm_street;
	private String comm_state;
	private String comm_dist;
	private String comm_mandal;
	private String same_mdl_mcl;
	private String comm_mdl_mcl;
	private String same_municipality;
	private String comm_municipality;
	private String comm_village;
	private String specialistDoctorName;
	private String invoiceNumber;
	private String invoiceNumberNew;
	private String newDrugName;
	private String pickupName;
	private String pickLocation;
	public String getPickLocation() {
		return pickLocation;
	}
	public void setPickLocation(String pickLocation) {
		this.pickLocation = pickLocation;
	}
	public String getPickupName() {
		return pickupName;
	}
	public void setPickupName(String pickupName) {
		this.pickupName = pickupName;
	}
	public String getPickupNum() {
		return pickupNum;
	}
	public void setPickupNum(String pickupNum) {
		this.pickupNum = pickupNum;
	}
	private String pickupNum;
	
	
	
	
	public String getNewDrugName() {
		return newDrugName;
	}
	public void setNewDrugName(String newDrugName) {
		this.newDrugName = newDrugName;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSpecialistDoctorName() {
		return specialistDoctorName;
	}
	public void setSpecialistDoctorName(String specialistDoctorName) {
		this.specialistDoctorName = specialistDoctorName;
	}
	private String comm_hamlet;
	private String comm_pin;
	private String hno;
	private String street;
	private String state;
	private String diffDate;
	private String newProc;
	private String hospNims;
	private String ayush;
	private String attachName;
	private String attachPath;
	private String invoiceDate;
	
	private String invoiceDateMn;
	
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getInvoiceDateMn() {
		return invoiceDateMn;
	}
	public void setInvoiceDateMn(String invoiceDateMn) {
		this.invoiceDateMn = invoiceDateMn;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getAyush() {
		return ayush;
	}
	public void setAyush(String ayush) {
		this.ayush = ayush;
	}
	public String getHospNims() {
		return hospNims;
	}
	public void setHospNims(String hospNims) {
		this.hospNims = hospNims;
	}
	public String getNewProc() {
		return newProc;
	}
	public void setNewProc(String newProc) {
		this.newProc = newProc;
	}
	public String getDiffDate() {
		return diffDate;
	}
	public void setDiffDate(String diffDate) {
		this.diffDate = diffDate;
	}
	private String reason;
	private String district;
	private String mdl_mcl;
	private String mandal;
	private String municipality;
	private String village;
	private String hamlet;
	private String pin;
	private String medicalCat;
	private List<LabelBean> districtList;
	private List<LabelBean> symList;
	private List<LabelBean> genInvList;
	private List<LabelBean> dispGenInvList;
	private List<DrugsVO> drugLt;
	private List<DrugsVO> otherDrugLt;
	private List<CommonDtlsVO> referalDtls;
	private List<PreauthVO> investigationLt;
	private String same_state;
	private String same_dist;
	private String same_mandal;
	private String same_village;
	private String same_hamlet;
	private String hospitalId;
	private String hospitalName;
	private String dtTime;
	private String sourceNo;
	private String photoUrl;
	private String disableFlag;
	private String hosptype;
	//Start of patient search
	private String patientNo;
	private String patientName;
	private String fromDate;
	private String toDate;
	private String phaseId;
	private String ageRange;
	private int startIndex;
	private int noOfPages;
	private String currCaseId;
	private String currPatId;
	private String currPatName;
	private String currHealthCardNo;
	private String currStateId;
	private String currDistrictId;
	private String currFromDate;
	private String currToDate;
	private String currHospId;
	//end of patient search
	//Start of Ramco patient diagnosis to convert to IP/OP
	private String complaints;
	private String[] patientComplaint;
	private String complaintCode;
	private String patComplaintCode;
	private String presentHistory;
	private String[] pastHistory;
	private String[] personalHistory;
	private String[] personalHist;
	private String[] familyHistory;
	private String[] examinationFnd;
	private String speciality;
	private String otherDocDetailsList;
	private String genInvestigations;
	private String genBlockInvestName;
	private String provisionalDiagnosis;
	private String investigations;
	private String investigationSel;
	private String addTests;
	private String diagnosedBy;
	private String doctorName;
	private String otherDocName;
	private String docRegNo;
	private String docQual;
	private String docMobileNo;
	private String ais_no;

	//IP Doc Details Properties
	private String ipDiagnosedBy;
	private String ipDoctorName;
	private String ipOtherDocName;
	private String ipDocRegNo;
	private String ipDocQual;
	private String ipDocMobileNo;
	//End of IP Doc Details Properties
	private String addr;
	private String hospaddr;
	private String dateIp;
	private String patTemp;
	private String patPulse;
	private String respir;
	private String bloodPr;
	private String pastHist;
	private String famHist;
	private String complType;
	private String pastIllnessValue;
	private String familyHistValue;
	private String allergy;
	private String habbits;
	private List<PreauthVO> procList;
	private String patientType;
	private String testsCount;
	private String ipNo;
	private String admissionType;
	private String ipDate;
	private String diagnosisCategory;
	private String therapyCategory;
	private String therapy;
	private String therapyType;
	private String remarks;
	private String ipFinalDiagnosis;
	private String ipPrescription;
	private String opNo;
	private String opDate;
	private String opRemarks;
	private String opFinalDiagnosis;
	private String[] opPrescription;
	private FormFile attach[]= new FormFile[300];
	private FormFile genAttach[]= new FormFile[300];
	private FormFile updateGenAttach[]= new FormFile[300];
	private String caseId;
	//END of IP/OP
	private String teleCallerDesgn;
	private String teleCallerName;
	private String telePhoneNo;
	private String teleDocName;
	private String teleDocDesgn;
	private String teleDocPhoneNo;
	private String remarksProcedure;
	private String remarksDiagnosis;
	private String surgeryName;
	private String catName;
	private String subCatName;
	private String ICDCatName;
	private String ICDSubCatName;
	private String ICDProcName;
	private String ICDCatCode;
	private String ICDSubCatCode;
	private String ICDProcCode;
	private String procCode;
	private String diagType;
	private String mainCatName;
	private String diseaseName;
	private String disAnatomicalName;
	private String diagCode;
	private String mainCatCode;
	private String catCode;
	private String subCatCode;
	private String diseaseCode;
	private String disAnatomicalCode;
	private String indication;
	private String telephonicId;
	private String telephonicReg;
	private String bloodPrLt;
	private String bloodPrRt;

	private String personalHistVal;
	private String examFndsVal;

	private String mainSymptomCode;
	private String mainSymptomName;
	private String subSymptomName;
	private String subSymptomCode;
	private String symptomName;
	private String symptomCode;
	private String asriCatName;
	private String asriCatCode;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugSubTypeName;
	private String asriDrugCode;
	private String drugName;
	private String drugCode;
	private String drugs;
	private String symptoms;
	private String pSubGrpName;
	private String pSubGrpCode;
	private String cSubGrpName;
	private String cSubGrpCode;
	
	private String pastHistryOthr;
	private String pastHistryCancers;
	private String pastHistryEndDis;
	private String pastHistrySurg;
	private String familyHistoryOthr;
	private String route;
	private String routeType;
	private String strengthType;
	private String strength;
	private String route1;
	private String routeType1;
	private String strengthType1;
	private String strength1;
	private String quantity;	
	private String quantity1;
	private String leaveDocname;

	private Long leaveDocMob;
	private String wellnessName;
	private String asriCatCode1;
	private String ICDCatCode1;
	private String subCatName1;
	private String ICDSubCatName1;
	private String ICDProcName1;
	private String ICDSubCatCode1;
	private String ICDProcCode1;
	private String procCode1;
	private String ipOtherRemarks;
	private String indent_no;
	public String getIpOtherRemarks() {
		return ipOtherRemarks;
	}
	public void setIpOtherRemarks(String ipOtherRemarks) {
		this.ipOtherRemarks = ipOtherRemarks;
	}
	public String getSubCatName1() {
		return subCatName1;
	}
	public void setSubCatName1(String subCatName1) {
		this.subCatName1 = subCatName1;
	}
	public String getICDSubCatName1() {
		return ICDSubCatName1;
	}
	public void setICDSubCatName1(String iCDSubCatName1) {
		ICDSubCatName1 = iCDSubCatName1;
	}
	public String getICDProcName1() {
		return ICDProcName1;
	}
	public void setICDProcName1(String iCDProcName1) {
		ICDProcName1 = iCDProcName1;
	}
	public String getICDSubCatCode1() {
		return ICDSubCatCode1;
	}
	public void setICDSubCatCode1(String iCDSubCatCode1) {
		ICDSubCatCode1 = iCDSubCatCode1;
	}
	public String getICDProcCode1() {
		return ICDProcCode1;
	}
	public void setICDProcCode1(String iCDProcCode1) {
		ICDProcCode1 = iCDProcCode1;
	}
	public String getProcCode1() {
		return procCode1;
	}
	public void setProcCode1(String procCode1) {
		this.procCode1 = procCode1;
	}
	public String getAsriCatCode1() {
		return asriCatCode1;
	}
	public void setAsriCatCode1(String asriCatCode1) {
		this.asriCatCode1 = asriCatCode1;
	}
	public String getICDCatCode1() {
		return ICDCatCode1;
	}
	public void setICDCatCode1(String iCDCatCode1) {
		ICDCatCode1 = iCDCatCode1;
	}
	private  List<CreateEmployeeVO> levRepList;
	public String getLeaveDocname() {
		return leaveDocname;
	}
	public void setLeaveDocname(String leaveDocname) {
		this.leaveDocname = leaveDocname;
	}

	public Long getLeaveDocMob() {
		return leaveDocMob;
	}
	public void setLeaveDocMob(Long leaveDocMob) {
		this.leaveDocMob = leaveDocMob;
	}
	public String getWellnessName() {
		return wellnessName;
	}
	public void setWellnessName(String wellnessName) {
		this.wellnessName = wellnessName;
	}
	private List<LabelBean> dispatchedDetailsList;
	public String getRoute1() {
		return route1;
	}
	public void setRoute1(String route1) {
		this.route1 = route1;
	}
	public String getRouteType1() {
		return routeType1;
	}
	public void setRouteType1(String routeType1) {
		this.routeType1 = routeType1;
	}
	public String getStrengthType1() {
		return strengthType1;
	}
	public void setStrengthType1(String strengthType1) {
		this.strengthType1 = strengthType1;
	}
	public String getStrength1() {
		return strength1;
	}
	public void setStrength1(String strength1) {
		this.strength1 = strength1;
	}
	private String dosage;
	private String medicatPeriod;
	private String dosage1;
	private String medicatPeriod1;

	private String opCatName;
	private String opCatCode;
	private String opPkgName;
	public String getDosage1() {
		return dosage1;
	}
	public void setDosage1(String dosage1) {
		this.dosage1 = dosage1;
	}
	public String getMedicatPeriod1() {
		return medicatPeriod1;
	}
	public void setMedicatPeriod1(String medicatPeriod1) {
		this.medicatPeriod1 = medicatPeriod1;
	}
	private String opPkgCode;
	private String opIcdName;
	private String opIcdCode;
	private String mithra;
	private String docSpecReg;
	private String legalCase;
	private String legalCaseNo;
	private String policeStatName;
	private String procUnits;
	private String scheme;
	private String addrEnable;
	private String telScheme;
	private String patientScheme;
	private FormFile childPhoto;
	private FormFile upload;
	private FormFile identificationAttachment;
	public FormFile getUpload() {
		return upload;
	}
	public void setUpload(FormFile upload) {
		this.upload = upload;
	}
	private String newBornBaby; 
	private String dentalProc;
	private String dentalSpecApproval;
	private long investPrice;
	private long totInvestPrice;
	private long consultFee;
	private long totalOpCost;
	
	//sowmya
	private FormFile aadhaarFile;
	private FormFile photoFile;
	private FormFile supportDocFile;
	private String fatherName;
	private String spouseName;
	private String memberType;
	private String memberStatus;
	private String constituency;
	private String mlaId;
	private String termStartDate;
	private String termEndDate;
	private String permanentAddress;
	private String officialAddress;
	private String constituencyAddress;
	private String pincode;
	private String dependentName;
	private String dependentGender;
	private String dependentRelation;
	private String dependentMobile;
	
	private FormFile dependentAadhaarFile;
	private FormFile signedDocFile;
	
	private String dependentCount; // number of dependents
	
	private String existingAadharFileName;
	private String existingAadharFilePath;
	
	private String rejectStatus;
	
	
	public String getRejectStatus() {
		return rejectStatus;
	}
	public void setRejectStatus(String rejectStatus) {
		this.rejectStatus = rejectStatus;
	}
	public String getExistingAadharFileName() {
		return existingAadharFileName;
	}
	public void setExistingAadharFileName(String existingAadharFileName) {
		this.existingAadharFileName = existingAadharFileName;
	}
	public String getExistingAadharFilePath() {
		return existingAadharFilePath;
	}
	public void setExistingAadharFilePath(String existingAadharFilePath) {
		this.existingAadharFilePath = existingAadharFilePath;
	}
	public FormFile getSignedDocFile() {
		return signedDocFile;
	}
	public void setSignedDocFile(FormFile signedDocFile) {
		this.signedDocFile = signedDocFile;
	}
	public FormFile getDependentAadhaarFile() {
		return dependentAadhaarFile;
	}
	public void setDependentAadhaarFile(FormFile dependentAadhaarFile) {
		this.dependentAadhaarFile = dependentAadhaarFile;
	}
	public String getDependentCount() {
	    return dependentCount;
	}
	public void setDependentCount(String dependentCount) {
	    this.dependentCount = dependentCount;
	}
	
    public String getDependentName() {
		return dependentName;
	}
	public void setDependentName(String dependentName) {
		this.dependentName = dependentName;
	}
	public String getDependentGender() {
		return dependentGender;
	}
	public void setDependentGender(String dependentGender) {
		this.dependentGender = dependentGender;
	}
	public String getDependentRelation() {
		return dependentRelation;
	}
	public void setDependentRelation(String dependentRelation) {
		this.dependentRelation = dependentRelation;
	}
	public String getDependentMobile() {
		return dependentMobile;
	}
	public void setDependentMobile(String dependentMobile) {
		this.dependentMobile = dependentMobile;
	}
	public FormFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(FormFile photoFile) {
		this.photoFile = photoFile;
	}
	public FormFile getSupportDocFile() {
		return supportDocFile;
	}
	public void setSupportDocFile(FormFile supportDocFile) {
		this.supportDocFile = supportDocFile;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}
	public String getConstituency() {
		return constituency;
	}
	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}
	public String getMlaId() {
		return mlaId;
	}
	public void setMlaId(String mlaId) {
		this.mlaId = mlaId;
	}
	public String getTermStartDate() {
		return termStartDate;
	}
	public void setTermStartDate(String termStartDate) {
		this.termStartDate = termStartDate;
	}
	public String getTermEndDate() {
		return termEndDate;
	}
	public void setTermEndDate(String termEndDate) {
		this.termEndDate = termEndDate;
	}
	public String getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public String getOfficialAddress() {
		return officialAddress;
	}
	public void setOfficialAddress(String officialAddress) {
		this.officialAddress = officialAddress;
	}
	public String getConstituencyAddress() {
		return constituencyAddress;
	}
	public void setConstituencyAddress(String constituencyAddress) {
		this.constituencyAddress = constituencyAddress;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public FormFile getAadhaarFile() {
		return aadhaarFile;
	}
	public void setAadhaarFile(FormFile aadhaarFile) {
		this.aadhaarFile = aadhaarFile;
	}
	private String otherInvName;
	private String otherInvBlockName;
	private String investOthers;
	private String drugsOthers;
	private String otherDrugName;
	private int otherInvestCount;
	private int otherDrugCount;
	private String opActiveMsg;
	

	private String unitName;
	private String unitHodName;
	
	private String height;
	private String weight;
	private String bmi;
	
	private String chronicNo;
	 
	   private String packageDrugAmt;
	   
	   private String caseApprovalFlag;
	   private String chronicStatus;
	   private String ceoRemark;
	   
	   
	   private String chronicID;
		private String name;
		private String employeeNo;
	
		private BigDecimal  age;
		private String registrationDate;

	

		
		private int startPage;
		private int endPage;

		private int endIndex;
		private String rowsPerPage;
		private String showPage;
		private String next;
		private String prev;

	    private String deptHod;
	    private String postDist;
	    private String stoCode;
	    private String ddoCode;

	
		


	 	private String batchNo;
	 	private String expiryDt;
		private String regCaseFlg;
		private String addPckgFlg;
		
		private  String otherComplaint;
		private String otherSymptomName;
		private String otherSymptoms;
		private int otherSymptomCount;
		
		private String diagOthers;
		private String diagnosisName;
		
		private List<LabelBean> consultData;
		List<CasesSearchVO> lstCaseSearch;
		private String bioFinger;
		private String bioCaptureDtlsYN;
		private String biometricValue;
		
		private String tgGovPatCond;
		private String selectedDrug;
		private String fingerPrint;
		private String diagOthersName;
		private String bioHand;
		private String totalRows;
		

		private String diagCondition;
		
		private String checkType;
		private String hosType;
		private String[] softTissue;
		private String[] childCaries;
		private String[] missingTeeth;
		private String[] caries;
		private String[] decayed;
		//private String[] mobile;
		private String[] attrition;
		private String previousDentalTreatment;
		private String occlusion;
		//private String tmj;
		private String[] probeDepth;
		private String diagnosis;
		private String admissionDetails;
		private String advancedInvestigations;
		private String followupAdv;
		private String medicationGiven;
		//private String face;
		//private String jaws;
		private String mandibleFracture;
		private String maxillaFracture;
		private String zygomaticFracture;
		private String occlusionType;
		private String occlusionOther;
		private String permanentDent;
		
		private String deciduousDent;
		private String lymphapathyType;
		private String lymphapathyText;
		
		
		
		
		//private String lymphadenopathy;
		private String faceCheck;
		private String tmjCheck;
		private String jawsCheck;
		private String lymphadenopathyCheck;
		
		
		private String otherPermntDent;
		private String otherPermText;
		private String face ;           
	    private String tmj ;            
	    private String  jaws ;            
	    private String lymphadenopathy ;            
	    private String gingiva ;  
	    private String buccalMucosa;             
	    private String frenalAttachment ;              
	    private String tongue;              
	    private String floorMouth;                         
	    private String softPalate;      
	    private String hardPalate; 
	    private String deciduousCaries ;           
		private String deciduousMissing ;          
		private String grosslyDecayed ;            
		private String mobile       ;               
		private String permanentCaries ;           
		private String rootGrosslyDecayed  ;      
		private String mobility       ;             
		private String attritionAbrasion ;         
		private String permanentMissing ;          
		private String permanentothers ; 
		/*added by pavan for dental case sheet new block*/
		private String drughistory;
		private String drughistoryid;
		private String medicalHistVal;
		private String showMedicalTextval;
		private String[] medicalHistsText;
		private List<LabelBean> extraoraldtls;
		private List<LabelBean> intraoraldtls;
		private List<LabelBean> occlusiondtls;
		private List<LabelBean> deciduousdentdtls;
		private List<LabelBean> permanentdentdtls;
		private List<LabelBean> medicalhsitorydtls;
		private List<LabelBean> dispDentalTreatDoneLst;
		private List<LabelBean> dispDentalTreatDoneSubLst;
		private String[] medicalDtlsid;
		private String[] extraoral;
		//private String[] medicalDtlsid;
		private String[] intraoral;
		private String[] deciduous;
		private String[] permanent;
		private String[] dntsublistoral;
		private String Subdental0;
		private String Subdental1;
	    private String Subdental2;
		private String Subdental3;
		private List<LabelBean> Dentalsublist;
		private List<LabelBean> Dentalmainlist0;
		private List<LabelBean> Dentalmainlist1;
		private List<LabelBean> Dentalmainlist2;
		private List<LabelBean> Dentalmainlist3;
		private List<LabelBean> Dentalsublistjaws1;
		private List<LabelBean> Dentalsublistrl0;
		private String subdentaljaws1;
		private String subdentaljaws2;
		private String subdentaljaws3;
		private String subdentaljaws0;
		private String subdentaljawstxt;
		private String subdentalrltxt;
		private String [] subdentalrl0;
		private String subdentalrl1;
		private String subdentalrl2;
		private String subdentalrl3;
		private String subdentalrl4;
		private String subdentalrl5;
		private String dntsublistoral0;
		private String dntsublistoral1;
		private String dntsublistoral2;
		private String dntsublistoral3;
		private String dntsublistoral4;
		private String dntsublistoral5;
		private String dntsublistoral6;
		private String deciduous0;
		private String deciduous1;
		private String deciduous2;
		private String deciduous3;
		private String permanent0;
		private String permanent1;
		private String permanent2;
		private String permanent3;
		private String permanent4;
		private String permanent5;
		private String treatmentDntl;

		private String carriesdecidous;
		private String missingdecidous;
		private String mobiledecidous;
		private String grosslydecadedecidous;
		private String carriespermanent;
		private String rootstumppermannet;
		private String mobilitypermanent;
		private String attritionpermanent;
		private String missingpermanent;
		private String otherpermanent;
		private String probingdepth;
		
		//Added for Patient Registration
		private String prc;
	    private String payScale;
	    private String dept;
	    private String postDDOcode;
	    private String servDsgn;
	    private String ddoOffUnit;
	    private String currPay;
	    private String designation;
	    private String aadharID;
	    private String aadharEID;
	    
	  //Added for Swelling & Pus/Discharge details
	    private String swSite;
	    private String swSize;
	    private String swExtension;
	    private String swColour;
	    private String swConsistency;
	    private String swTenderness;
	    private String swBorders;
	    private String psSite;
	    private String psDischarge;
	    private String fromDisp;
	    private String dispCode;		
	    
	    private String regionalLymphadenopathyDtrsTxt;
	    private String jawsDtrsTxt;
	    private String tmjDtrsTxt;
	    private String faceDtrsTxt;
	    
	    private String regionalLymphadenopathyDtrsSub;
	    private String jawsDtrsSub;
	    private String tmjDtrsSub;
	    private String faceDtrsSub;
	    
	    private String occlusionTypeTxt;
	    private String occlusionTxt;
	    private String occlusionOtherTxt;
	    private String specialityType;
	    private String tokenNo;
	    private String roomNo;
	    private String specialities;
	    private String specialityDoctors;
	    private String otherSpecialistDoctorName;
	    
	    
	    private List<SpecialityVO> specLt;
	    private List<SpecialityVO> docLt;
	    
	    public String getOtherSpecialistDoctorName() {
			return otherSpecialistDoctorName;
		}
		public void setOtherSpecialistDoctorName(String otherSpecialistDoctorName) {
			this.otherSpecialistDoctorName = otherSpecialistDoctorName;
		}
		public String getSpecialityDoctors() {
			return specialityDoctors;
		}
		public void setSpecialityDoctors(String specialityDoctors) {
			this.specialityDoctors = specialityDoctors;
		}
		public List<SpecialityVO> getDocLt() {
			return docLt;
		}
		public void setDocLt(List<SpecialityVO> docLt) {
			this.docLt = docLt;
		}
	     /* Medical Management Changes
	     */
	    private String hospQuantity;
	    private String hospQuantNo;
	    private String hospCode;
	    private String imageology;
	    private String imageCode;
	    private String imageQuantity;
	    private String labInvest;
	    private String labInvestCode;
	    private String labInvestQuantity;
	    private String drugQuantity;
	    private String implants;
	    private String implantCode;
	    private String implantQuantity;
	    private String hospCodeUnit;
	    private String enhancementDtls;
	    private String imageCodeUnit;
	    private String labInvestCodeUnit;
	    private String drugCodeUnit;
	    private String implantCodeUnit;
	    private String labTokenNo;
	    private List<LabelVO> labelsList;
	    
	    private String dispDrugID;
	    private String dispDrugQuantity;
	    private String drugBatchNo;
	    private String drugexpiryDate;
	    private String drugType;
	    private String dispDrugName;
	    private String dispDrugPrice;
	    private String presentDrugQuantity;
	    private String manufacturerName;
	    private String distributerName;
	    private String dispDrugMfg;
	    private String mftrName;
	    private String dstrName;
	    private String drugPrice;
	    private String hospContactNo;
	    private List<CommonDtlsVO> pastDrugHist;
	    
	    private String textValDHD1;
	    private String textValDHD2;
	    private String textValDH1;
	    private String textValDH2;
	    private String textValDH3;
	    private String textValDH4;
	    private String textValDH5;
	    private String textValDH6;
	    private String textValDH7;
	    private String textValDH8;
	    private String textValDH9;
	    private String textValDH71;
	    private String textValDH72;
	    private String textValDH73;
	    private String stateType;
	    private String enablePharma;
	    private List<LabelBean> investList;
	    private FormFile drugsIssue;
	   //pravalika 
	    private List<DrugsVO> drugsList;
	    
	    public List<DrugsVO> getDrugsList() {
			return drugsList;
		}
		public void setDrugsList(List<DrugsVO> drugsList) {
			this.drugsList = drugsList;
		}
		private String critical;
	    private String brandName;
	    
		public String getBrandName() {
			return brandName;
		}
		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}
		public String getMftrName() {
			return mftrName;
		}
		public void setMftrName(String mftrName) {
			this.mftrName = mftrName;
		}
		public String getDstrName() {
			return dstrName;
		}
		public void setDstrName(String dstrName) {
			this.dstrName = dstrName;
		}
		public String getManufacturerName() {
			return manufacturerName;
		}
		public void setManufacturerName(String manufacturerName) {
			this.manufacturerName = manufacturerName;
		}
		public String getDistributerName() {
			return distributerName;
		}
		public void setDistributerName(String distributerName) {
			this.distributerName = distributerName;
		}
		public String getDispDrugQuantity() {
			return dispDrugQuantity;
		}
		public void setDispDrugQuantity(String dispDrugQuantity) {
			this.dispDrugQuantity = dispDrugQuantity;
		}
		public String getDrugBatchNo() {
			return drugBatchNo;
		}
		public void setDrugBatchNo(String drugBatchNo) {
			this.drugBatchNo = drugBatchNo;
		}
		 public LabelVO getLabelsItem(int index)
	     {
	         
	         if(this.labelsList == null)
	         {
	             this.labelsList = new ArrayList<LabelVO>();
	         }
	              
	         while(index >= this.labelsList.size())
	         {
	             this.labelsList.add(new LabelVO());
	         }
	  
	         return labelsList.get(index);
	     }
	    
		public List<LabelVO> getLabelsList() {
			return labelsList;
		}
		public void setLabelsList(List<LabelVO> labelsList) {
			this.labelsList = labelsList;
		}
		public String getHospQuantity() {
			return hospQuantity;
		}
		public void setHospQuantity(String hospQuantity) {
			this.hospQuantity = hospQuantity;
		}
		public String getHospQuantNo() {
			return hospQuantNo;
		}
		public void setHospQuantNo(String hospQuantNo) {
			this.hospQuantNo = hospQuantNo;
		}
		public String getHospCode() {
			return hospCode;
		}
		public void setHospCode(String hospCode) {
			this.hospCode = hospCode;
		}
		public String getImageology() {
			return imageology;
		}
		public void setImageology(String imageology) {
			this.imageology = imageology;
		}
		public String getImageCode() {
			return imageCode;
		}
		public void setImageCode(String imageCode) {
			this.imageCode = imageCode;
		}
		public String getImageQuantity() {
			return imageQuantity;
		}
		public void setImageQuantity(String imageQuantity) {
			this.imageQuantity = imageQuantity;
		}
		public String getLabInvest() {
			return labInvest;
		}
		public void setLabInvest(String labInvest) {
			this.labInvest = labInvest;
		}
		public String getLabInvestCode() {
			return labInvestCode;
		}
		public void setLabInvestCode(String labInvestCode) {
			this.labInvestCode = labInvestCode;
		}
		public String getLabInvestQuantity() {
			return labInvestQuantity;
		}
		public void setLabInvestQuantity(String labInvestQuantity) {
			this.labInvestQuantity = labInvestQuantity;
		}
		public String getDrugQuantity() {
			return drugQuantity;
		}
		public void setDrugQuantity(String drugQuantity) {
			this.drugQuantity = drugQuantity;
		}
		public String getImplants() {
			return implants;
		}
		public void setImplants(String implants) {
			this.implants = implants;
		}
		public String getImplantCode() {
			return implantCode;
		}
		public void setImplantCode(String implantCode) {
			this.implantCode = implantCode;
		}
		public String getImplantQuantity() {
			return implantQuantity;
		}
		public void setImplantQuantity(String implantQuantity) {
			this.implantQuantity = implantQuantity;
		}
		public String getHospCodeUnit() {
			return hospCodeUnit;
		}
		public void setHospCodeUnit(String hospCodeUnit) {
			this.hospCodeUnit = hospCodeUnit;
		}
		public String getEnhancementDtls() {
			return enhancementDtls;
		}
		public void setEnhancementDtls(String enhancementDtls) {
			this.enhancementDtls = enhancementDtls;
		}
		public String getImageCodeUnit() {
			return imageCodeUnit;
		}
		public void setImageCodeUnit(String imageCodeUnit) {
			this.imageCodeUnit = imageCodeUnit;
		}
		public String getLabInvestCodeUnit() {
			return labInvestCodeUnit;
		}
		public void setLabInvestCodeUnit(String labInvestCodeUnit) {
			this.labInvestCodeUnit = labInvestCodeUnit;
		}
		public String getDrugCodeUnit() {
			return drugCodeUnit;
		}
		public void setDrugCodeUnit(String drugCodeUnit) {
			this.drugCodeUnit = drugCodeUnit;
		}
		public String getImplantCodeUnit() {
			return implantCodeUnit;
		}
		public void setImplantCodeUnit(String implantCodeUnit) {
			this.implantCodeUnit = implantCodeUnit;
		}
		public String getOcclusionTypeTxt() {
			return occlusionTypeTxt;
		}
		public void setOcclusionTypeTxt(String occlusionTypeTxt) {
			this.occlusionTypeTxt = occlusionTypeTxt;
		}
		public String getOcclusionTxt() {
			return occlusionTxt;
		}
		public void setOcclusionTxt(String occlusionTxt) {
			this.occlusionTxt = occlusionTxt;
		}
		public String getOcclusionOtherTxt() {
			return occlusionOtherTxt;
		}
		public void setOcclusionOtherTxt(String occlusionOtherTxt) {
			this.occlusionOtherTxt = occlusionOtherTxt;
		}
	    
	    
	    
	    public String getRegionalLymphadenopathyDtrsSub() {
			return regionalLymphadenopathyDtrsSub;
		}
		public void setRegionalLymphadenopathyDtrsSub(
				String regionalLymphadenopathyDtrsSub) {
			this.regionalLymphadenopathyDtrsSub = regionalLymphadenopathyDtrsSub;
		}
		public String getJawsDtrsSub() {
			return jawsDtrsSub;
		}
		public void setJawsDtrsSub(String jawsDtrsSub) {
			this.jawsDtrsSub = jawsDtrsSub;
		}
		public String getTmjDtrsSub() {
			return tmjDtrsSub;
		}
		public void setTmjDtrsSub(String tmjDtrsSub) {
			this.tmjDtrsSub = tmjDtrsSub;
		}
		public String getFaceDtrsSub() {
			return faceDtrsSub;
		}
		public void setFaceDtrsSub(String faceDtrsSub) {
			this.faceDtrsSub = faceDtrsSub;
		}
		public String getRegionalLymphadenopathyDtrsTxt() {
			return regionalLymphadenopathyDtrsTxt;
		}
		public void setRegionalLymphadenopathyDtrsTxt(
				String regionalLymphadenopathyDtrsTxt) {
			this.regionalLymphadenopathyDtrsTxt = regionalLymphadenopathyDtrsTxt;
		}
		public String getJawsDtrsTxt() {
			return jawsDtrsTxt;
		}
		public void setJawsDtrsTxt(String jawsDtrsTxt) {
			this.jawsDtrsTxt = jawsDtrsTxt;
		}
		public String getTmjDtrsTxt() {
			return tmjDtrsTxt;
		}
		public void setTmjDtrsTxt(String tmjDtrsTxt) {
			this.tmjDtrsTxt = tmjDtrsTxt;
		}
		public String getFaceDtrsTxt() {
			return faceDtrsTxt;
		}
		public void setFaceDtrsTxt(String faceDtrsTxt) {
			this.faceDtrsTxt = faceDtrsTxt;
		}

		

		public String getFromDisp() {
			return fromDisp;
		}
		public void setFromDisp(String fromDisp) {
			this.fromDisp = fromDisp;
		}
		public String getDispCode() {
			return dispCode;
		}
		public void setDispCode(String dispCode) {
			this.dispCode = dispCode;
		}

		

		public String getDeciduous0() {
			return deciduous0;
		}
		public void setDeciduous0(String deciduous0) {
			this.deciduous0 = deciduous0;
		}
		public String getDeciduous1() {
			return deciduous1;
		}
		public void setDeciduous1(String deciduous1) {
			this.deciduous1 = deciduous1;
		}
		public String getDeciduous2() {
			return deciduous2;
		}
		public void setDeciduous2(String deciduous2) {
			this.deciduous2 = deciduous2;
		}
		public String getDeciduous3() {
			return deciduous3;
		}
		public void setDeciduous3(String deciduous3) {
			this.deciduous3 = deciduous3;
		}
		public String getPermanent0() {
			return permanent0;
		}
		public void setPermanent0(String permanent0) {
			this.permanent0 = permanent0;
		}
		public String getPermanent1() {
			return permanent1;
		}
		public void setPermanent1(String permanent1) {
			this.permanent1 = permanent1;
		}
		public String getPermanent2() {
			return permanent2;
		}
		public void setPermanent2(String permanent2) {
			this.permanent2 = permanent2;
		}
		public String getPermanent3() {
			return permanent3;
		}
		public void setPermanent3(String permanent3) {
			this.permanent3 = permanent3;
		}
		public String getPermanent4() {
			return permanent4;
		}
		public void setPermanent4(String permanent4) {
			this.permanent4 = permanent4;
		}
		public String getPermanent5() {
			return permanent5;
		}
		public void setPermanent5(String permanent5) {
			this.permanent5 = permanent5;
		}
		public String getSubdentaljaws3() {
			return subdentaljaws3;
		}
		public void setSubdentaljaws3(String subdentaljaws3) {
			this.subdentaljaws3 = subdentaljaws3;
		}
		public String getSubdentaljaws0() {
			return subdentaljaws0;
		}
		public void setSubdentaljaws0(String subdentaljaws0) {
			this.subdentaljaws0 = subdentaljaws0;
		}
		
		public String getSubdentalrl1() {
			return subdentalrl1;
		}
		public void setSubdentalrl1(String subdentalrl1) {
			this.subdentalrl1 = subdentalrl1;
		}
		public String getDntsublistoral0() {
			return dntsublistoral0;
		}
		public void setDntsublistoral0(String dntsublistoral0) {
			this.dntsublistoral0 = dntsublistoral0;
		}
		public String getDntsublistoral1() {
			return dntsublistoral1;
		}
		public void setDntsublistoral1(String dntsublistoral1) {
			this.dntsublistoral1 = dntsublistoral1;
		}
		public String getDntsublistoral2() {
			return dntsublistoral2;
		}
		public void setDntsublistoral2(String dntsublistoral2) {
			this.dntsublistoral2 = dntsublistoral2;
		}
		public String getDntsublistoral3() {
			return dntsublistoral3;
		}
		public void setDntsublistoral3(String dntsublistoral3) {
			this.dntsublistoral3 = dntsublistoral3;
		}
		public String getDntsublistoral4() {
			return dntsublistoral4;
		}
		public void setDntsublistoral4(String dntsublistoral4) {
			this.dntsublistoral4 = dntsublistoral4;
		}
		public String getDntsublistoral5() {
			return dntsublistoral5;
		}
		public void setDntsublistoral5(String dntsublistoral5) {
			this.dntsublistoral5 = dntsublistoral5;
		}
		public String getDntsublistoral6() {
			return dntsublistoral6;
		}
		public void setDntsublistoral6(String dntsublistoral6) {
			this.dntsublistoral6 = dntsublistoral6;
		}
		public String getSubdentaljaws1() {
			return subdentaljaws1;
		}
		public void setSubdentaljaws1(String subdentaljaws1) {
			this.subdentaljaws1 = subdentaljaws1;
		}
		public String getSubdentalrl4() {
			return subdentalrl4;
		}
		public void setSubdentalrl4(String subdentalrl4) {
			this.subdentalrl4 = subdentalrl4;
		}
		public String getSubdentalrl5() {
			return subdentalrl5;
		}
		public void setSubdentalrl5(String subdentalrl5) {
			this.subdentalrl5 = subdentalrl5;
		}
		public String getSubdentaljaws2() {
			return subdentaljaws2;
		}
		public void setSubdentaljaws2(String subdentaljaws2) {
			this.subdentaljaws2 = subdentaljaws2;
		}
		
		public String getSubdentalrl2() {
			return subdentalrl2;
		}
		public void setSubdentalrl2(String subdentalrl2) {
			this.subdentalrl2 = subdentalrl2;
		}
		public String getSubdentalrl3() {
			return subdentalrl3;
		}
		public void setSubdentalrl3(String subdentalrl3) {
			this.subdentalrl3 = subdentalrl3;
		}
		public List<LabelBean> getExtraoraldtls() {
			return extraoraldtls;
		}
		public void setExtraoraldtls(List<LabelBean> extraoraldtls) {
			this.extraoraldtls = extraoraldtls;
		}
		public List<LabelBean> getIntraoraldtls() {
			return intraoraldtls;
		}
		public void setIntraoraldtls(List<LabelBean> intraoraldtls) {
			this.intraoraldtls = intraoraldtls;
		}
		public List<LabelBean> getOcclusiondtls() {
			return occlusiondtls;
		}
		public void setOcclusiondtls(List<LabelBean> occlusiondtls) {
			this.occlusiondtls = occlusiondtls;
		}
		public List<LabelBean> getDeciduousdentdtls() {
			return deciduousdentdtls;
		}
		public void setDeciduousdentdtls(List<LabelBean> deciduousdentdtls) {
			this.deciduousdentdtls = deciduousdentdtls;
		}
		public List<LabelBean> getPermanentdentdtls() {
			return permanentdentdtls;
		}
		public void setPermanentdentdtls(List<LabelBean> permanentdentdtls) {
			this.permanentdentdtls = permanentdentdtls;
		}
		public String getGingiva() {
			return gingiva;
		}
		public void setGingiva(String gingiva) {
			this.gingiva = gingiva;
		}
		public String getBuccalMucosa() {
			return buccalMucosa;
		}
		public void setBuccalMucosa(String buccalMucosa) {
			this.buccalMucosa = buccalMucosa;
		}
		public String getFrenalAttachment() {
			return frenalAttachment;
		}
		public void setFrenalAttachment(String frenalAttachment) {
			this.frenalAttachment = frenalAttachment;
		}
		public String getTongue() {
			return tongue;
		}
		public void setTongue(String tongue) {
			this.tongue = tongue;
		}
		public String getFloorMouth() {
			return floorMouth;
		}
		public void setFloorMouth(String floorMouth) {
			this.floorMouth = floorMouth;
		}
		public String getSoftPalate() {
			return softPalate;
		}
		public void setSoftPalate(String softPalate) {
			this.softPalate = softPalate;
		}
		public String getHardPalate() {
			return hardPalate;
		}
		public void setHardPalate(String hardPalate) {
			this.hardPalate = hardPalate;
		}
		public String getDeciduousCaries() {
			return deciduousCaries;
		}
		public void setDeciduousCaries(String deciduousCaries) {
			this.deciduousCaries = deciduousCaries;
		}
		public String getDeciduousMissing() {
			return deciduousMissing;
		}
		public void setDeciduousMissing(String deciduousMissing) {
			this.deciduousMissing = deciduousMissing;
		}
		public String getGrosslyDecayed() {
			return grosslyDecayed;
		}
		public void setGrosslyDecayed(String grosslyDecayed) {
			this.grosslyDecayed = grosslyDecayed;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getPermanentCaries() {
			return permanentCaries;
		}
		public void setPermanentCaries(String permanentCaries) {
			this.permanentCaries = permanentCaries;
		}
		public String getRootGrosslyDecayed() {
			return rootGrosslyDecayed;
		}
		public void setRootGrosslyDecayed(String rootGrosslyDecayed) {
			this.rootGrosslyDecayed = rootGrosslyDecayed;
		}
		public String getMobility() {
			return mobility;
		}
		public void setMobility(String mobility) {
			this.mobility = mobility;
		}
		public String getAttritionAbrasion() {
			return attritionAbrasion;
		}
		public void setAttritionAbrasion(String attritionAbrasion) {
			this.attritionAbrasion = attritionAbrasion;
		}
		public String getPermanentMissing() {
			return permanentMissing;
		}
		public void setPermanentMissing(String permanentMissing) {
			this.permanentMissing = permanentMissing;
		}
		public String getPermanentothers() {
			return permanentothers;
		}
		public void setPermanentothers(String permanentothers) {
			this.permanentothers = permanentothers;
		}
		public String getOtherPermntDent() {
			return otherPermntDent;
		}
		public void setOtherPermntDent(String otherPermntDent) {
			this.otherPermntDent = otherPermntDent;
		}
		public String getOtherPermText() {
			return otherPermText;
		}
		public void setOtherPermText(String otherPermText) {
			this.otherPermText = otherPermText;
		}
	public String getMandibleFracture() {
			return mandibleFracture;
		}
		public void setMandibleFracture(String mandibleFracture) {
			this.mandibleFracture = mandibleFracture;
		}
		public String getMaxillaFracture() {
			return maxillaFracture;
		}
		public void setMaxillaFracture(String maxillaFracture) {
			this.maxillaFracture = maxillaFracture;
		}
		public String getZygomaticFracture() {
			return zygomaticFracture;
		}
		public void setZygomaticFracture(String zygomaticFracture) {
			this.zygomaticFracture = zygomaticFracture;
		}
	public FormFile[] getUpdateGenAttach() {
			return updateGenAttach;
		}
		public void setUpdateGenAttach(FormFile[] updateGenAttach) {
			this.updateGenAttach = updateGenAttach;
		}
		public String getFace() {
			return face;
		}
		public void setFace(String face) {
			this.face = face;
		}
		public String getJaws() {
			return jaws;
		}
		public void setJaws(String jaws) {
			this.jaws = jaws;
		}
		public String getLymphadenopathy() {
			return lymphadenopathy;
		}
		public void setLymphadenopathy(String lymphadenopathy) {
			this.lymphadenopathy = lymphadenopathy;
		}
		public String getFaceCheck() {
			return faceCheck;
		}
		public void setFaceCheck(String faceCheck) {
			this.faceCheck = faceCheck;
		}
		public String getTmjCheck() {
			return tmjCheck;
		}
		public void setTmjCheck(String tmjCheck) {
			this.tmjCheck = tmjCheck;
		}
		public String getJawsCheck() {
			return jawsCheck;
		}
		public void setJawsCheck(String jawsCheck) {
			this.jawsCheck = jawsCheck;
		}
		public String getLymphadenopathyCheck() {
			return lymphadenopathyCheck;
		}
		public void setLymphadenopathyCheck(String lymphadenopathyCheck) {
			this.lymphadenopathyCheck = lymphadenopathyCheck;
		}
	public String getMedicationGiven() {
			return medicationGiven;
		}
		public void setMedicationGiven(String medicationGiven) {
			this.medicationGiven = medicationGiven;
		}
	public String[] getCaries() {
			return caries;
		}
		public void setCaries(String[] caries) {
			this.caries = caries;
		}
	public String[] getSoftTissue() {
			return softTissue;
		}
		public void setSoftTissue(String[] softTissue) {
			this.softTissue = softTissue;
		}
		
		public String[] getChildCaries() {
			return childCaries;
		}
		public void setChildCaries(String[] childCaries) {
			this.childCaries = childCaries;
		}
		public String[] getMissingTeeth() {
			return missingTeeth;
		}
		public void setMissingTeeth(String[] missingTeeth) {
			this.missingTeeth = missingTeeth;
		}
		public String[] getDecayed() {
			return decayed;
		}
		public void setDecayed(String[] decayed) {
			this.decayed = decayed;
		}
		/*public String[] getMobile() {
			return mobile;
		}
		public void setMobile(String[] mobile) {
			this.mobile = mobile;
		}*/
		public String[] getAttrition() {
			return attrition;
		}
		public void setAttrition(String[] attrition) {
			this.attrition = attrition;
		}
		public String getPreviousDentalTreatment() {
			return previousDentalTreatment;
		}
		public void setPreviousDentalTreatment(String previousDentalTreatment) {
			this.previousDentalTreatment = previousDentalTreatment;
		}
		public String getOcclusion() {
			return occlusion;
		}
		public void setOcclusion(String occlusion) {
			this.occlusion = occlusion;
		}
		public String getTmj() {
			return tmj;
		}
		public void setTmj(String tmj) {
			this.tmj = tmj;
		}
		public String[] getProbeDepth() {
			return probeDepth;
		}
		public void setProbeDepth(String[] probeDepth) {
			this.probeDepth = probeDepth;
		}
		public String getDiagnosis() {
			return diagnosis;
		}
		public void setDiagnosis(String diagnosis) {
			this.diagnosis = diagnosis;
		}
		public String getAdmissionDetails() {
			return admissionDetails;
		}
		public void setAdmissionDetails(String admissionDetails) {
			this.admissionDetails = admissionDetails;
		}
		public String getAdvancedInvestigations() {
			return advancedInvestigations;
		}
		public void setAdvancedInvestigations(String advancedInvestigations) {
			this.advancedInvestigations = advancedInvestigations;
		}
		public String getFollowupAdv() {
			return followupAdv;
		}
		public void setFollowupAdv(String followupAdv) {
			this.followupAdv = followupAdv;
		}
	public String getAsriDrugCode() {
		return asriDrugCode;
	}
	public void setAsriDrugCode(String asriDrugCode) {
		this.asriDrugCode = asriDrugCode;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}

	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getAsriCatCode() {
		return asriCatCode;
	}
	public void setAsriCatCode(String asriCatCode) {
		this.asriCatCode = asriCatCode;
	}

	public String getAsriCatName() {
		return asriCatName;
	}
	public void setAsriCatName(String asriCatName) {
		this.asriCatName = asriCatName;
	}
	
	public String getDrugs() {
		return drugs;
	}
	public void setDrugs(String drugs) {
		this.drugs = drugs;
	}
	public String getMainSymptomCode() {
		return mainSymptomCode;
	}
	public void setMainSymptomCode(String mainSymptomCode) {
		this.mainSymptomCode = mainSymptomCode;
	}
	public String getSymptomCode() {
		return symptomCode;
	}
	public void setSymptomCode(String symptomCode) {
		this.symptomCode = symptomCode;
	}
	public String getMainSymptomName() {
		return mainSymptomName;
	}
	public void setMainSymptomName(String mainSymptomName) {
		this.mainSymptomName = mainSymptomName;
	}
	public String getSymptomName() {
		return symptomName;
	}
	public void setSymptomName(String symptomName) {
		this.symptomName = symptomName;
	}
	public String getPersonalHistVal() {
		return personalHistVal;
	}
	public void setPersonalHistVal(String personalHistVal) {
		this.personalHistVal = personalHistVal;
	}
	public String getExamFndsVal() {
		return examFndsVal;
	}
	public void setExamFndsVal(String examFndsVal) {
		this.examFndsVal = examFndsVal;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getSame_state() {
		return same_state;
	}
	public void setSame_state(String same_state) {
		this.same_state = same_state;
	}
	public String getSame_dist() {
		return same_dist;
	}
	public void setSame_dist(String same_dist) {
		this.same_dist = same_dist;
	}
	public String getSame_mandal() {
		return same_mandal;
	}
	public void setSame_mandal(String same_mandal) {
		this.same_mandal = same_mandal;
	}
	public String getSame_village() {
		return same_village;
	}
	public void setSame_village(String same_village) {
		this.same_village = same_village;
	}
	public String getSame_hamlet() {
		return same_hamlet;
	}
	public void setSame_hamlet(String same_hamlet) {
		this.same_hamlet = same_hamlet;
	}

	public String getMdl_mcl() {
		return mdl_mcl;
	}
	public void setMdl_mcl(String mdl_mcl) {
		this.mdl_mcl = mdl_mcl;
	}
	public String getMandal() {
		return mandal;
	}
	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getSame_mdl_mcl() {
		return same_mdl_mcl;
	}
	public void setSame_mdl_mcl(String same_mdl_mcl) {
		this.same_mdl_mcl = same_mdl_mcl;
	}
	public String getComm_mdl_mcl() {
		return comm_mdl_mcl;
	}
	public void setComm_mdl_mcl(String comm_mdl_mcl) {
		this.comm_mdl_mcl = comm_mdl_mcl;
	}
	public String getSame_municipality() {
		return same_municipality;
	}
	public void setSame_municipality(String same_municipality) {
		this.same_municipality = same_municipality;
	}
	public String getComm_municipality() {
		return comm_municipality;
	}
	public void setComm_municipality(String comm_municipality) {
		this.comm_municipality = comm_municipality;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	public String getCommCheck() {
		return commCheck;
	}
	public void setCommCheck(String commCheck) {
		this.commCheck = commCheck;
	}
	public List<LabelBean> getDistrictList() {
		return districtList;
	}
	public void setDistrictList(List<LabelBean> districtList) {
		this.districtList = districtList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getComm_hno() {
		return comm_hno;
	}
	public void setComm_hno(String comm_hno) {
		this.comm_hno = comm_hno;
	}
	public String getComm_street() {
		return comm_street;
	}
	public void setComm_street(String comm_street) {
		this.comm_street = comm_street;
	}
	public String getComm_state() {
		return comm_state;
	}
	public void setComm_state(String comm_state) {
		this.comm_state = comm_state;
	}
	public String getComm_dist() {
		return comm_dist;
	}
	public void setComm_dist(String comm_dist) {
		this.comm_dist = comm_dist;
	}
	public String getComm_mandal() {
		return comm_mandal;
	}
	public void setComm_mandal(String comm_mandal) {
		this.comm_mandal = comm_mandal;
	}
	public String getComm_village() {
		return comm_village;
	}
	public void setComm_village(String comm_village) {
		this.comm_village = comm_village;
	}
	public String getComm_hamlet() {
		return comm_hamlet;
	}
	public void setComm_hamlet(String comm_hamlet) {
		this.comm_hamlet = comm_hamlet;
	}
	public String getComm_pin() {
		return comm_pin;
	}
	public void setComm_pin(String comm_pin) {
		this.comm_pin = comm_pin;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String geteMailId() {
		return eMailId;
	}
	public void seteMailId(String eMailId) {
		this.eMailId = eMailId;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child = child;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getCardIssueDt() {
		return cardIssueDt;
	}
	public void setCardIssueDt(String cardIssueDt) {
		this.cardIssueDt = cardIssueDt;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAgeString() {
		return ageString;
	}
	public void setAgeString(String ageString) {
		this.ageString = ageString;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getHno() {
		return hno;
	}
	public void setHno(String hno) {
		this.hno = hno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getHamlet() {
		return hamlet;
	}
	public void setHamlet(String hamlet) {
		this.hamlet = hamlet;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getDtTime() {
		return dtTime;
	}
	public void setDtTime(String dtTime) {
		this.dtTime = dtTime;
	}
	public String getSourceNo() {
		return sourceNo;
	}
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getDisableFlag() {
		return disableFlag;
	}
	public void setDisableFlag(String disableFlag) {
		this.disableFlag = disableFlag;
	}

	public String getHosptype() {
		return hosptype;
	}
	public void setHosptype(String hosptype) {
		this.hosptype = hosptype;
	}
	public String getPatientNo() {
		return patientNo;
	}
	public void setPatientNo(String patientNo) {
		this.patientNo = patientNo;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getAgeRange() {
		return ageRange;
	}
	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}
	public String getCurrCaseId() {
		return currCaseId;
	}
	public void setCurrCaseId(String currCaseId) {
		this.currCaseId = currCaseId;
	}
	public String getCurrPatId() {
		return currPatId;
	}
	public void setCurrPatId(String currPatId) {
		this.currPatId = currPatId;
	}
	public String getCurrPatName() {
		return currPatName;
	}
	public void setCurrPatName(String currPatName) {
		this.currPatName = currPatName;
	}
	public String getCurrHealthCardNo() {
		return currHealthCardNo;
	}
	public void setCurrHealthCardNo(String currHealthCardNo) {
		this.currHealthCardNo = currHealthCardNo;
	}
	public String getCurrStateId() {
		return currStateId;
	}
	public void setCurrStateId(String currStateId) {
		this.currStateId = currStateId;
	}
	public String getCurrDistrictId() {
		return currDistrictId;
	}
	public void setCurrDistrictId(String currDistrictId) {
		this.currDistrictId = currDistrictId;
	}
	public String getCurrFromDate() {
		return currFromDate;
	}
	public void setCurrFromDate(String currFromDate) {
		this.currFromDate = currFromDate;
	}
	public String getCurrToDate() {
		return currToDate;
	}
	public void setCurrToDate(String currToDate) {
		this.currToDate = currToDate;
	}
	public String getCurrHospId() {
		return currHospId;
	}
	public void setCurrHospId(String currHospId) {
		this.currHospId = currHospId;
	}
	public String getComplaints() {
		return complaints;
	}
	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}
	public String[] getPatientComplaint() {
		return patientComplaint;
	}
	public void setPatientComplaint(String[] patientComplaint) {
		this.patientComplaint = patientComplaint;
	}

	public String getComplaintCode() {
		return complaintCode;
	}
	public void setComplaintCode(String complaintCode) {
		this.complaintCode = complaintCode;
	}
	public String getPatComplaintCode() {
		return patComplaintCode;
	}
	public void setPatComplaintCode(String patComplaintCode) {
		this.patComplaintCode = patComplaintCode;
	}
	public String getPresentHistory() {
		return presentHistory;
	}
	public void setPresentHistory(String presentHistory) {
		this.presentHistory = presentHistory;
	}
	public String[] getPastHistory() {
		return pastHistory;
	}
	public void setPastHistory(String[] pastHistory) {
		this.pastHistory = pastHistory;
	}
	public String[] getPersonalHistory() {
		return personalHistory;
	}
	public void setPersonalHistory(String[] personalHistory) {
		this.personalHistory = personalHistory;
	}
	public String[] getFamilyHistory() {
		return familyHistory;
	}
	public void setFamilyHistory(String[] familyHistory) {
		this.familyHistory = familyHistory;
	}
	public String[] getExaminationFnd() {
		return examinationFnd;
	}
	public void setExaminationFnd(String[] examinationFnd) {
		this.examinationFnd = examinationFnd;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getOtherDocDetailsList() {
		return otherDocDetailsList;
	}
	public void setOtherDocDetailsList(String otherDocDetailsList) {
		this.otherDocDetailsList = otherDocDetailsList;
	}
	public String getGenInvestigations() {
		return genInvestigations;
	}
	public void setGenInvestigations(String genInvestigations) {
		this.genInvestigations = genInvestigations;
	}
	public String getProvisionalDiagnosis() {
		return provisionalDiagnosis;
	}
	public void setProvisionalDiagnosis(String provisionalDiagnosis) {
		this.provisionalDiagnosis = provisionalDiagnosis;
	}
	public String getInvestigations() {
		return investigations;
	}
	public void setInvestigations(String investigations) {
		this.investigations = investigations;
	}
	public String getInvestigationSel() {
		return investigationSel;
	}
	public void setInvestigationSel(String investigationSel) {
		this.investigationSel = investigationSel;
	}
	public String getAddTests() {
		return addTests;
	}
	public void setAddTests(String addTests) {
		this.addTests = addTests;
	}
	public String getDiagnosedBy() {
		return diagnosedBy;
	}
	public void setDiagnosedBy(String diagnosedBy) {
		this.diagnosedBy = diagnosedBy;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getOtherDocName() {
		return otherDocName;
	}
	public void setOtherDocName(String otherDocName) {
		this.otherDocName = otherDocName;
	}
	public String getDocRegNo() {
		return docRegNo;
	}
	public void setDocRegNo(String docRegNo) {
		this.docRegNo = docRegNo;
	}
	public String getDocQual() {
		return docQual;
	}
	public void setDocQual(String docQual) {
		this.docQual = docQual;
	}
	public String getDocMobileNo() {
		return docMobileNo;
	}
	public void setDocMobileNo(String docMobileNo) {
		this.docMobileNo = docMobileNo;
	}
	//Setters and Getters for IP Doc Details 
	public String getIpDiagnosedBy() {
		return ipDiagnosedBy;
	}
	public void setIpDiagnosedBy(String ipDiagnosedBy) {
		this.ipDiagnosedBy = ipDiagnosedBy;
	}
	public String getIpDoctorName() {
		return ipDoctorName;
	}
	public void setIpDoctorName(String ipDoctorName) {
		this.ipDoctorName = ipDoctorName;
	}
	public String getIpOtherDocName() {
		return ipOtherDocName;
	}
	public void setIpOtherDocName(String ipOtherDocName) {
		this.ipOtherDocName = ipOtherDocName;
	}
	public String getIpDocRegNo() {
		return ipDocRegNo;
	}
	public void setIpDocRegNo(String ipDocRegNo) {
		this.ipDocRegNo = ipDocRegNo;
	}
	public String getIpDocQual() {
		return ipDocQual;
	}
	public void setIpDocQual(String ipDocQual) {
		this.ipDocQual = ipDocQual;
	}
	public String getIpDocMobileNo() {
		return ipDocMobileNo;
	}
	public void setIpDocMobileNo(String ipDocMobileNo) {
		this.ipDocMobileNo = ipDocMobileNo;
	}
	//End Of Setters and Getters for IP Doc Details 
	public String getPatientType() {
		return patientType;
	}
	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}
	public String getTestsCount() {
		return testsCount;
	}
	public void setTestsCount(String testsCount) {
		this.testsCount = testsCount;
	}
	public String getIpNo() {
		return ipNo;
	}
	public void setIpNo(String ipNo) {
		this.ipNo = ipNo;
	}
	public String getAdmissionType() {
		return admissionType;
	}
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}
	public String getIpDate() {
		return ipDate;
	}
	public void setIpDate(String ipDate) {
		this.ipDate = ipDate;
	}
	public String getDiagnosisCategory() {
		return diagnosisCategory;
	}
	public void setDiagnosisCategory(String diagnosisCategory) {
		this.diagnosisCategory = diagnosisCategory;
	}
	public String getTherapyCategory() {
		return therapyCategory;
	}
	public void setTherapyCategory(String therapyCategory) {
		this.therapyCategory = therapyCategory;
	}
	public String getTherapy() {
		return therapy;
	}
	public void setTherapy(String therapy) {
		this.therapy = therapy;
	}
	public String getTherapyType() {
		return therapyType;
	}
	public void setTherapyType(String therapyType) {
		this.therapyType = therapyType;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIpFinalDiagnosis() {
		return ipFinalDiagnosis;
	}
	public void setIpFinalDiagnosis(String ipFinalDiagnosis) {
		this.ipFinalDiagnosis = ipFinalDiagnosis;
	}
	public String getIpPrescription() {
		return ipPrescription;
	}
	public void setIpPrescription(String ipPrescription) {
		this.ipPrescription = ipPrescription;
	}

	public String getOpFinalDiagnosis() {
		return opFinalDiagnosis;
	}
	public void setOpFinalDiagnosis(String opFinalDiagnosis) {
		this.opFinalDiagnosis = opFinalDiagnosis;
	}
	public String[] getOpPrescription() {
		return opPrescription;
	}
	public void setOpPrescription(String[] opPrescription) {
		this.opPrescription = opPrescription;
	}
	public String getOpNo() {
		return opNo;
	}
	public void setOpNo(String opNo) {
		this.opNo = opNo;
	}
	public String getOpDate() {
		return opDate;
	}
	public void setOpDate(String opDate) {
		this.opDate = opDate;
	}
	public String getOpRemarks() {
		return opRemarks;
	}
	public void setOpRemarks(String opRemarks) {
		this.opRemarks = opRemarks;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public FormFile getAttach(int index) {
		return attach[index];
	}
	public void setAttach(int index,FormFile value) {
		this.attach[index] = value;
	}
	public FormFile[] getAttach() {
		return attach;
	}
	public void setAttach(FormFile[] attach) {
		this.attach = attach;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTeleCallerDesgn() {
		return teleCallerDesgn;
	}
	public void setTeleCallerDesgn(String teleCallerDesgn) {
		this.teleCallerDesgn = teleCallerDesgn;
	}
	public String getTeleCallerName() {
		return teleCallerName;
	}
	public void setTeleCallerName(String teleCallerName) {
		this.teleCallerName = teleCallerName;
	}
	public String getTelePhoneNo() {
		return telePhoneNo;
	}
	public void setTelePhoneNo(String telePhoneNo) {
		this.telePhoneNo = telePhoneNo;
	}
	public String getTeleDocName() {
		return teleDocName;
	}
	public void setTeleDocName(String teleDocName) {
		this.teleDocName = teleDocName;
	}
	public String getTeleDocDesgn() {
		return teleDocDesgn;
	}
	public void setTeleDocDesgn(String teleDocDesgn) {
		this.teleDocDesgn = teleDocDesgn;
	}
	public String getTeleDocPhoneNo() {
		return teleDocPhoneNo;
	}
	public void setTeleDocPhoneNo(String teleDocPhoneNo) {
		this.teleDocPhoneNo = teleDocPhoneNo;
	}
	public String getRemarksProcedure() {
		return remarksProcedure;
	}
	public void setRemarksProcedure(String remarksProcedure) {
		this.remarksProcedure = remarksProcedure;
	}
	public String getRemarksDiagnosis() {
		return remarksDiagnosis;
	}
	public void setRemarksDiagnosis(String remarksDiagnosis) {
		this.remarksDiagnosis = remarksDiagnosis;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getSubCatName() {
		return subCatName;
	}
	public void setSubCatName(String subCatName) {
		this.subCatName = subCatName;
	}
	public String getICDCatName() {
		return ICDCatName;
	}
	public void setICDCatName(String iCDCatName) {
		ICDCatName = iCDCatName;
	}
	public String getICDSubCatName() {
		return ICDSubCatName;
	}
	public void setICDSubCatName(String iCDSubCatName) {
		ICDSubCatName = iCDSubCatName;
	}
	public String getICDProcName() {
		return ICDProcName;
	}
	public void setICDProcName(String iCDProcName) {
		ICDProcName = iCDProcName;
	}
	public String getICDCatCode() {
		return ICDCatCode;
	}
	public void setICDCatCode(String iCDCatCode) {
		ICDCatCode = iCDCatCode;
	}
	public String getICDSubCatCode() {
		return ICDSubCatCode;
	}
	public void setICDSubCatCode(String iCDSubCatCode) {
		ICDSubCatCode = iCDSubCatCode;
	}
	public String getICDProcCode() {
		return ICDProcCode;
	}
	public void setICDProcCode(String iCDProcCode) {
		ICDProcCode = iCDProcCode;
	}
	public String getProcCode() {
		return procCode;
	}
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}
	public String getDiagType() {
		return diagType;
	}
	public void setDiagType(String diagType) {
		this.diagType = diagType;
	}
	public String getMainCatName() {
		return mainCatName;
	}
	public void setMainCatName(String mainCatName) {
		this.mainCatName = mainCatName;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getDisAnatomicalName() {
		return disAnatomicalName;
	}
	public void setDisAnatomicalName(String disAnatomicalName) {
		this.disAnatomicalName = disAnatomicalName;
	}

	public String getDiagCode() {
		return diagCode;
	}
	public void setDiagCode(String diagCode) {
		this.diagCode = diagCode;
	}
	public String getMainCatCode() {
		return mainCatCode;
	}
	public void setMainCatCode(String mainCatCode) {
		this.mainCatCode = mainCatCode;
	}
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getSubCatCode() {
		return subCatCode;
	}
	public void setSubCatCode(String subCatCode) {
		this.subCatCode = subCatCode;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getDisAnatomicalCode() {
		return disAnatomicalCode;
	}
	public void setDisAnatomicalCode(String disAnatomicalCode) {
		this.disAnatomicalCode = disAnatomicalCode;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public String getTelephonicId() {
		return telephonicId;
	}
	public void setTelephonicId(String telephonicId) {
		this.telephonicId = telephonicId;
	}
	public String getTelephonicReg() {
		return telephonicReg;
	}
	public void setTelephonicReg(String telephonicReg) {
		this.telephonicReg = telephonicReg;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public FormFile getGenAttach(int index) {
		return genAttach[index];
	}
	public void setGenAttach(int index,FormFile value) {
		this.genAttach[index] = value;
	}

	public FormFile getUpdateGenAttach(int index) {
		return updateGenAttach[index];
	}
	public void setUpdateGenAttach(int index,FormFile value) {
		this.updateGenAttach[index] = value;
	}
	public String getPastHistryOthr() {
		return pastHistryOthr;
	}
	public void setPastHistryOthr(String pastHistryOthr) {
		this.pastHistryOthr = pastHistryOthr;
	}
	public String getPastHistryCancers() {
		return pastHistryCancers;
	}
	public void setPastHistryCancers(String pastHistryCancers) {
		this.pastHistryCancers = pastHistryCancers;
	}
	public String getPastHistryEndDis() {
		return pastHistryEndDis;
	}
	public void setPastHistryEndDis(String pastHistryEndDis) {
		this.pastHistryEndDis = pastHistryEndDis;
	}
	public String getPastHistrySurg() {
		return pastHistrySurg;
	}
	public void setPastHistrySurg(String pastHistrySurg) {
		this.pastHistrySurg = pastHistrySurg;
	}
	public String getFamilyHistoryOthr() {
		return familyHistoryOthr;
	}
	public void setFamilyHistoryOthr(String familyHistoryOthr) {
		this.familyHistoryOthr = familyHistoryOthr;
	}
	public FormFile[] getGenAttach() {
		return genAttach;
	}
	public void setGenAttach(FormFile[] genAttach) {
		this.genAttach = genAttach;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getMedicatPeriod() {
		return medicatPeriod;
	}
	public void setMedicatPeriod(String medicatPeriod) {
		this.medicatPeriod = medicatPeriod;
	}
	public String getOpCatName() {
		return opCatName;
	}
	public void setOpCatName(String opCatName) {
		this.opCatName = opCatName;
	}
	public String getOpCatCode() {
		return opCatCode;
	}
	public void setOpCatCode(String opCatCode) {
		this.opCatCode = opCatCode;
	}
	public String getOpPkgName() {
		return opPkgName;
	}
	public void setOpPkgName(String opPkgName) {
		this.opPkgName = opPkgName;
	}
	public String getOpPkgCode() {
		return opPkgCode;
	}
	public void setOpPkgCode(String opPkgCode) {
		this.opPkgCode = opPkgCode;
	}
	public String getOpIcdName() {
		return opIcdName;
	}
	public void setOpIcdName(String opIcdName) {
		this.opIcdName = opIcdName;
	}
	public String getOpIcdCode() {
		return opIcdCode;
	}
	public void setOpIcdCode(String opIcdCode) {
		this.opIcdCode = opIcdCode;
	}
	public String getSubSymptomName() {
		return subSymptomName;
	}
	public void setSubSymptomName(String subSymptomName) {
		this.subSymptomName = subSymptomName;
	}
	public String getSubSymptomCode() {
		return subSymptomCode;
	}
	public void setSubSymptomCode(String subSymptomCode) {
		this.subSymptomCode = subSymptomCode;
	}
	public String getSymptoms() {
		return symptoms;
	}
	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getDateIp() {
		return dateIp;
	}
	public void setDateIp(String dateIp) {
		this.dateIp = dateIp;
	}
	public String getHospaddr() {
		return hospaddr;
	}
	public void setHospaddr(String hospaddr) {
		this.hospaddr = hospaddr;
	}
	public String[] getPersonalHist() {
		return personalHist;
	}
	public void setPersonalHist(String[] personalHist) {
		this.personalHist = personalHist;
	}
	public List<LabelBean> getSymList() {
		return symList;
	}
	public void setSymList(List<LabelBean> symList) {
		this.symList = symList;
	}
	public List<LabelBean> getGenInvList() {
		return genInvList;
	}
	public void setGenInvList(List<LabelBean> genInvList) {
		this.genInvList = genInvList;
	}
	public List<DrugsVO> getDrugLt() {
		return drugLt;
	}
	public void setDrugLt(List<DrugsVO> drugLt) {
		this.drugLt = drugLt;
	}
	public List<PreauthVO> getInvestigationLt() {
		return investigationLt;
	}
	public void setInvestigationLt(List<PreauthVO> investigationLt) {
		this.investigationLt = investigationLt;
	}
	public String getGenBlockInvestName() {
		return genBlockInvestName;
	}
	public void setGenBlockInvestName(String genBlockInvestName) {
		this.genBlockInvestName = genBlockInvestName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getPatTemp() {
		return patTemp;
	}
	public void setPatTemp(String patTemp) {
		this.patTemp = patTemp;
	}
	public String getPatPulse() {
		return patPulse;
	}
	public void setPatPulse(String patPulse) {
		this.patPulse = patPulse;
	}
	public String getRespir() {
		return respir;
	}
	public void setRespir(String respir) {
		this.respir = respir;
	}
	public String getBloodPr() {
		return bloodPr;
	}
	public void setBloodPr(String bloodPr) {
		this.bloodPr = bloodPr;
	}
	public String getPastHist() {
		return pastHist;
	}
	public void setPastHist(String pastHist) {
		this.pastHist = pastHist;
	}
	public String getFamHist() {
		return famHist;
	}
	public void setFamHist(String famHist) {
		this.famHist = famHist;
	}
	public String getComplType() {
		return complType;
	}
	public void setComplType(String complType) {
		this.complType = complType;
	}
	public String getPastIllnessValue() {
		return pastIllnessValue;
	}
	public void setPastIllnessValue(String pastIllnessValue) {
		this.pastIllnessValue = pastIllnessValue;
	}
	public String getFamilyHistValue() {
		return familyHistValue;
	}
	public void setFamilyHistValue(String familyHistValue) {
		this.familyHistValue = familyHistValue;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getHabbits() {
		return habbits;
	}
	public void setHabbits(String habbits) {
		this.habbits = habbits;
	}
	public List<PreauthVO> getProcList() {
		return procList;
	}
	public void setProcList(List<PreauthVO> procList) {
		this.procList = procList;
	}
	public String getpSubGrpName() {
		return pSubGrpName;
	}
	public void setpSubGrpName(String pSubGrpName) {
		this.pSubGrpName = pSubGrpName;
	}
	public String getpSubGrpCode() {
		return pSubGrpCode;
	}
	public void setpSubGrpCode(String pSubGrpCode) {
		this.pSubGrpCode = pSubGrpCode;
	}
	public String getcSubGrpName() {
		return cSubGrpName;
	}
	public void setcSubGrpName(String cSubGrpName) {
		this.cSubGrpName = cSubGrpName;
	}
	public String getcSubGrpCode() {
		return cSubGrpCode;
	}
	public void setcSubGrpCode(String cSubGrpCode) {
		this.cSubGrpCode = cSubGrpCode;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getStrengthType() {
		return strengthType;
	}
	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}
	public String getBloodPrLt() {
		return bloodPrLt;
	}
	public void setBloodPrLt(String bloodPrLt) {
		this.bloodPrLt = bloodPrLt;
	}
	public String getBloodPrRt() {
		return bloodPrRt;
	}
	public void setBloodPrRt(String bloodPrRt) {
		this.bloodPrRt = bloodPrRt;
	}
	public String getMithra() {
		return mithra;
	}
	public void setMithra(String mithra) {
		this.mithra = mithra;
	}
	public String getDocSpecReg() {
		return docSpecReg;
	}
	public void setDocSpecReg(String docSpecReg) {
		this.docSpecReg = docSpecReg;
	}
	public String getLegalCase() {
		return legalCase;
	}
	public void setLegalCase(String legalCase) {
		this.legalCase = legalCase;
	}
	public String getLegalCaseNo() {
		return legalCaseNo;
	}
	public void setLegalCaseNo(String legalCaseNo) {
		this.legalCaseNo = legalCaseNo;
	}
	public String getPoliceStatName() {
		return policeStatName;
	}
	public void setPoliceStatName(String policeStatName) {
		this.policeStatName = policeStatName;
	}
	public String getProcUnits() {
		return procUnits;
	}
	public void setProcUnits(String procUnits) {
		this.procUnits = procUnits;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getAddrEnable() {
		return addrEnable;
	}
	public void setAddrEnable(String addrEnable) {
		this.addrEnable = addrEnable;
	}
	public String getTelScheme() {
		return telScheme;
	}
	public void setTelScheme(String telScheme) {
		this.telScheme = telScheme;
	}
	public String getPatientScheme() {
		return patientScheme;
	}
	public void setPatientScheme(String patientScheme) {
		this.patientScheme = patientScheme;
	}
	public FormFile getChildPhoto() {
		return childPhoto;
	}
	public void setChildPhoto(FormFile childPhoto) {
		this.childPhoto = childPhoto;
	}
	public FormFile getIdentificationAttachment() {
		return identificationAttachment;
	}
	public void setIdentificationAttachment(FormFile identificationAttachment) {
		this.identificationAttachment = identificationAttachment;
	}
	public String getNewBornBaby() {
		return newBornBaby;
	}
	public void setNewBornBaby(String newBornBaby) {
		this.newBornBaby = newBornBaby;
	}
	public String getDentalProc() {
		return dentalProc;
	}
	public void setDentalProc(String dentalProc) {
		this.dentalProc = dentalProc;
	}
	public String getDentalSpecApproval() {
		return dentalSpecApproval;
	}
	public void setDentalSpecApproval(String dentalSpecApproval) {
		this.dentalSpecApproval = dentalSpecApproval;
	}
	public long getInvestPrice() {
		return investPrice;
	}
	public void setInvestPrice(long investPrice) {
		this.investPrice = investPrice;
	}
	public long getTotInvestPrice() {
		return totInvestPrice;
	}
	public void setTotInvestPrice(long totInvestPrice) {
		this.totInvestPrice = totInvestPrice;
	}
	public long getConsultFee() {
		return consultFee;
	}
	public void setConsultFee(long consultFee) {
		this.consultFee = consultFee;
	}
	public long getTotalOpCost() {
		return totalOpCost;
	}
	public void setTotalOpCost(long totalOpCost) {
		this.totalOpCost = totalOpCost;
	}
	public String getOtherInvName() {
		return otherInvName;
	}
	public void setOtherInvName(String otherInvName) {
		this.otherInvName = otherInvName;
	}
	public String getOtherInvBlockName() {
		return otherInvBlockName;
	}
	public void setOtherInvBlockName(String otherInvBlockName) {
		this.otherInvBlockName = otherInvBlockName;
	}
	public String getInvestOthers() {
		return investOthers;
	}
	public void setInvestOthers(String investOthers) {
		this.investOthers = investOthers;
	}
	public String getDrugsOthers() {
		return drugsOthers;
	}
	public void setDrugsOthers(String drugsOthers) {
		this.drugsOthers = drugsOthers;
	}
	public String getOtherDrugName() {
		return otherDrugName;
	}
	public void setOtherDrugName(String otherDrugName) {
		this.otherDrugName = otherDrugName;
	}
	public int getOtherInvestCount() {
		return otherInvestCount;
	}
	public void setOtherInvestCount(int otherInvestCount) {
		this.otherInvestCount = otherInvestCount;
	}
	public int getOtherDrugCount() {
		return otherDrugCount;
	}
	public void setOtherDrugCount(int otherDrugCount) {
		this.otherDrugCount = otherDrugCount;
	}
	public String getOpActiveMsg() {
		return opActiveMsg;
	}
	public void setOpActiveMsg(String opActiveMsg) {
		this.opActiveMsg = opActiveMsg;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitHodName() {
		return unitHodName;
	}
	public void setUnitHodName(String unitHodName) {
		this.unitHodName = unitHodName;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public String getChronicNo() {
		return chronicNo;
	}
	public void setChronicNo(String chronicNo) {
		this.chronicNo = chronicNo;
	}
	public String getPackageDrugAmt() {
		return packageDrugAmt;
	}
	public void setPackageDrugAmt(String packageDrugAmt) {
		this.packageDrugAmt = packageDrugAmt;
	}
	public String getCaseApprovalFlag() {
		return caseApprovalFlag;
	}
	public void setCaseApprovalFlag(String caseApprovalFlag) {
		this.caseApprovalFlag = caseApprovalFlag;
	}
	public String getChronicStatus() {
		return chronicStatus;
	}
	public void setChronicStatus(String chronicStatus) {
		this.chronicStatus = chronicStatus;
	}
	public String getCeoRemark() {
		return ceoRemark;
	}
	public void setCeoRemark(String ceoRemark) {
		this.ceoRemark = ceoRemark;
	}
	public String getChronicID() {
		return chronicID;
	}
	public void setChronicID(String chronicID) {
		this.chronicID = chronicID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public BigDecimal getAge() {
		return age;
	}
	public void setAge(BigDecimal age) {
		this.age = age;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getEndIndex() {
		return endIndex;
	}
	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public String getRowsPerPage() {
		return rowsPerPage;
	}
	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}
	public String getShowPage() {
		return showPage;
	}
	public void setShowPage(String showPage) {
		this.showPage = showPage;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public String getPrev() {
		return prev;
	}
	public void setPrev(String prev) {
		this.prev = prev;
	}
	public String getDeptHod() {
		return deptHod;
	}
	public void setDeptHod(String deptHod) {
		this.deptHod = deptHod;
	}
	public String getPostDist() {
		return postDist;
	}
	public void setPostDist(String postDist) {
		this.postDist = postDist;
	}
	public String getStoCode() {
		return stoCode;
	}
	public void setStoCode(String stoCode) {
		this.stoCode = stoCode;
	}
	public String getDdoCode() {
		return ddoCode;
	}
	public void setDdoCode(String ddoCode) {
		this.ddoCode = ddoCode;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public String getRegCaseFlg() {
		return regCaseFlg;
	}
	public void setRegCaseFlg(String regCaseFlg) {
		this.regCaseFlg = regCaseFlg;
	}
	public String getAddPckgFlg() {
		return addPckgFlg;
	}
	public void setAddPckgFlg(String addPckgFlg) {
		this.addPckgFlg = addPckgFlg;
	}
	public String getOtherComplaint() {
		return otherComplaint;
	}
	public void setOtherComplaint(String otherComplaint) {
		this.otherComplaint = otherComplaint;
	}
	public String getOtherSymptomName() {
		return otherSymptomName;
	}
	public void setOtherSymptomName(String otherSymptomName) {
		this.otherSymptomName = otherSymptomName;
	}
	public String getOtherSymptoms() {
		return otherSymptoms;
	}
	public void setOtherSymptoms(String otherSymptoms) {
		this.otherSymptoms = otherSymptoms;
	}
	public int getOtherSymptomCount() {
		return otherSymptomCount;
	}
	public void setOtherSymptomCount(int otherSymptomCount) {
		this.otherSymptomCount = otherSymptomCount;
	}
	public List<LabelBean> getConsultData() {
		return consultData;
	}
	public void setConsultData(List<LabelBean> consultData) {
		this.consultData = consultData;
	}
	public List<CasesSearchVO> getLstCaseSearch() {
		return lstCaseSearch;
	}
	public void setLstCaseSearch(List<CasesSearchVO> lstCaseSearch) {
		this.lstCaseSearch = lstCaseSearch;
	}
	public String getBioFinger() {
		return bioFinger;
	}
	public void setBioFinger(String bioFinger) {
		this.bioFinger = bioFinger;
	}
	public String getBiometricValue() {
		return biometricValue;
	}
	public void setBiometricValue(String biometricValue) {
		this.biometricValue = biometricValue;
	}
	public String getBioCaptureDtlsYN() {
		return bioCaptureDtlsYN;
	}
	public void setBioCaptureDtlsYN(String bioCaptureDtlsYN) {
		this.bioCaptureDtlsYN = bioCaptureDtlsYN;
	}

	public String getTgGovPatCond() {
		return tgGovPatCond;
	}
	public void setTgGovPatCond(String tgGovPatCond) {
		this.tgGovPatCond = tgGovPatCond;
	}
	public String getDiagOthers() {
		return diagOthers;
	}
	public void setDiagOthers(String diagOthers) {
		this.diagOthers = diagOthers;
	}
	public String getDiagnosisName() {
		return diagnosisName;
	}
	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}
	public String getDiagOthersName() {
		return diagOthersName;
	}
	public void setDiagOthersName(String diagOthersName) {
		this.diagOthersName = diagOthersName;
	}
	public String getSelectedDrug() {
		return selectedDrug;
	}
	public void setSelectedDrug(String selectedDrug) {
		this.selectedDrug = selectedDrug;
	}
	public String getFingerPrint() {
		return fingerPrint;
	}
	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

	public String getDiagCondition() {
		return diagCondition;
	}
	public void setDiagCondition(String diagCondition) {
		this.diagCondition = diagCondition;
	}

	public String getBioHand() {
		return bioHand;
	}
	public void setBioHand(String bioHand) {
		this.bioHand = bioHand;
	}
	public String getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(String totalRows) {
		this.totalRows = totalRows;
	}
	public String getCheckType() {
		return checkType;
	}
	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}
	public String getHosType() {
		return hosType;
	}
	public void setHosType(String hosType) {
		this.hosType = hosType;
	}
	public String getOcclusionType() {
		return occlusionType;
	}
	public void setOcclusionType(String occlusionType) {
		this.occlusionType = occlusionType;
	}
	public String getOcclusionOther() {
		return occlusionOther;
	}
	public void setOcclusionOther(String occlusionOther) {
		this.occlusionOther = occlusionOther;
	}
	public String getPermanentDent() {
		return permanentDent;
	}
	public void setPermanentDent(String permanentDent) {
		this.permanentDent = permanentDent;
	}
	public String getDeciduousDent() {
		return deciduousDent;
	}
	public void setDeciduousDent(String deciduousDent) {
		this.deciduousDent = deciduousDent;
	}
	public String getLymphapathyType() {
		return lymphapathyType;
	}
	public void setLymphapathyType(String lymphapathyType) {
		this.lymphapathyType = lymphapathyType;
	}
	public String getLymphapathyText() {
		return lymphapathyText;
	}
	public void setLymphapathyText(String lymphapathyText) {
		this.lymphapathyText = lymphapathyText;
	}
	public String[] getExtraoral() {
		return extraoral;
	}
	public void setExtraoral(String[] extraoral) {
		this.extraoral = extraoral;
	}
	public String getDrughistory() {
		return drughistory;
	}
	public void setDrughistory(String drughistory) {
		this.drughistory = drughistory;
	}
	public String getDrughistoryid() {
		return drughistoryid;
	}
	public void setDrughistoryid(String drughistoryid) {
		this.drughistoryid = drughistoryid;
	}
	
	
	public List<LabelBean> getDentalsublist() {
		return Dentalsublist;
	}
	public void setDentalsublist(List<LabelBean> dentalsublist) {
		Dentalsublist = dentalsublist;
	}
	public String getSubdental1() {
		return Subdental1;
	}
	public void setSubdental1(String subdental1) {
		Subdental1 = subdental1;
	}
	public String getSubdental2() {
		return Subdental2;
	}
	public void setSubdental2(String subdental2) {
		Subdental2 = subdental2;
	}
	public String getSubdental3() {
		return Subdental3;
	}
	public void setSubdental3(String subdental3) {
		Subdental3 = subdental3;
	}
	public String getSubdental0() {
		return Subdental0;
	}
	public void setSubdental0(String subdental0) {
		Subdental0 = subdental0;
	}
	public String[] getIntraoral() {
		return intraoral;
	}
	public void setIntraoral(String[] intraoral) {
		this.intraoral = intraoral;
	}
	public String[] getDntsublistoral() {
		return dntsublistoral;
	}
	public void setDntsublistoral(String[] dntsublistoral) {
		this.dntsublistoral = dntsublistoral;
	}
	public String getSubdentaljawstxt() {
		return subdentaljawstxt;
	}
	public void setSubdentaljawstxt(String subdentaljawstxt) {
		this.subdentaljawstxt = subdentaljawstxt;
	}
	public String getSubdentalrltxt() {
		return subdentalrltxt;
	}
	public void setSubdentalrltxt(String subdentalrltxt) {
		this.subdentalrltxt = subdentalrltxt;
	}
	public String[] getDeciduous() {
		return deciduous;
	}
	public void setDeciduous(String[] deciduous) {
		this.deciduous = deciduous;
	}
	public String[] getPermanent() {
		return permanent;
	}
	public void setPermanent(String[] permanent) {
		this.permanent = permanent;
	}
	public List<LabelBean> getMedicalhsitorydtls() {
		return medicalhsitorydtls;
	}
	public void setMedicalhsitorydtls(List<LabelBean> medicalhsitorydtls) {
		this.medicalhsitorydtls = medicalhsitorydtls;
	}
	public String[] getMedicalDtlsid() {
		return medicalDtlsid;
	}
	public void setMedicalDtlsid(String[] medicalDtlsid) {
		this.medicalDtlsid = medicalDtlsid;
	}
	public String getTreatmentDntl() {
		return treatmentDntl;
	}
	public void setTreatmentDntl(String treatmentDntl) {
		this.treatmentDntl = treatmentDntl;
	}
	public String getShowMedicalTextval() {
		return showMedicalTextval;
	}
	public void setShowMedicalTextval(String showMedicalTextval) {
		this.showMedicalTextval = showMedicalTextval;
	}
	public String [] getSubdentalrl0() {
		return subdentalrl0;
	}
	public void setSubdentalrl0(String [] subdentalrl0) {
		this.subdentalrl0 = subdentalrl0;
	}
	public List<LabelBean> getDentalmainlist0() {
		return Dentalmainlist0;
	}
	public void setDentalmainlist0(List<LabelBean> dentalmainlist0) {
		Dentalmainlist0 = dentalmainlist0;
	}
	public List<LabelBean> getDentalmainlist1() {
		return Dentalmainlist1;
	}
	public void setDentalmainlist1(List<LabelBean> dentalmainlist1) {
		Dentalmainlist1 = dentalmainlist1;
	}
	public List<LabelBean> getDentalmainlist2() {
		return Dentalmainlist2;
	}
	public void setDentalmainlist2(List<LabelBean> dentalmainlist2) {
		Dentalmainlist2 = dentalmainlist2;
	}
	public List<LabelBean> getDentalmainlist3() {
		return Dentalmainlist3;
	}
	public void setDentalmainlist3(List<LabelBean> dentalmainlist3) {
		Dentalmainlist3 = dentalmainlist3;
	}
	public List<LabelBean> getDentalsublistjaws1() {
		return Dentalsublistjaws1;
	}
	public void setDentalsublistjaws1(List<LabelBean> dentalsublistjaws1) {
		Dentalsublistjaws1 = dentalsublistjaws1;
	}
	public List<LabelBean> getDentalsublistrl0() {
		return Dentalsublistrl0;
	}
	public void setDentalsublistrl0(List<LabelBean> dentalsublistrl0) {
		Dentalsublistrl0 = dentalsublistrl0;
	}
	public String getCarriesdecidous() {
		return carriesdecidous;
	}
	public void setCarriesdecidous(String carriesdecidous) {
		this.carriesdecidous = carriesdecidous;
	}
	public String getProbingdepth() {
		return probingdepth;
	}
	public void setProbingdepth(String probingdepth) {
		this.probingdepth = probingdepth;
	}
	public String getMissingdecidous() {
		return missingdecidous;
	}
	public void setMissingdecidous(String missingdecidous) {
		this.missingdecidous = missingdecidous;
	}
	public String getMobiledecidous() {
		return mobiledecidous;
	}
	public void setMobiledecidous(String mobiledecidous) {
		this.mobiledecidous = mobiledecidous;
	}
	public String getGrosslydecadedecidous() {
		return grosslydecadedecidous;
	}
	public void setGrosslydecadedecidous(String grosslydecadedecidous) {
		this.grosslydecadedecidous = grosslydecadedecidous;
	}
	public String getCarriespermanent() {
		return carriespermanent;
	}
	public void setCarriespermanent(String carriespermanent) {
		this.carriespermanent = carriespermanent;
	}
	public String getRootstumppermannet() {
		return rootstumppermannet;
	}
	public void setRootstumppermannet(String rootstumppermannet) {
		this.rootstumppermannet = rootstumppermannet;
	}
	public String getMobilitypermanent() {
		return mobilitypermanent;
	}
	public void setMobilitypermanent(String mobilitypermanent) {
		this.mobilitypermanent = mobilitypermanent;
	}
	public String getAttritionpermanent() {
		return attritionpermanent;
	}
	public void setAttritionpermanent(String attritionpermanent) {
		this.attritionpermanent = attritionpermanent;
	}
	public String getMissingpermanent() {
		return missingpermanent;
	}
	public void setMissingpermanent(String missingpermanent) {
		this.missingpermanent = missingpermanent;
	}
	public String getOtherpermanent() {
		return otherpermanent;
	}
	public void setOtherpermanent(String otherpermanent) {
		this.otherpermanent = otherpermanent;
	}
	
	
	public String getPrc() {
		return prc;
	}
	public void setPrc(String prc) {
		this.prc = prc;
	}
	public String getPayScale() {
		return payScale;
	}
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPostDDOcode() {
		return postDDOcode;
	}
	public void setPostDDOcode(String postDDOcode) {
		this.postDDOcode = postDDOcode;
	}
	public String getServDsgn() {
		return servDsgn;
	}
	public void setServDsgn(String servDsgn) {
		this.servDsgn = servDsgn;
	}
	public String getDdoOffUnit() {
		return ddoOffUnit;
	}
	public void setDdoOffUnit(String ddoOffUnit) {
		this.ddoOffUnit = ddoOffUnit;
	}
	public String getCurrPay() {
		return currPay;
	}
	public void setCurrPay(String currPay) {
		this.currPay = currPay;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getAadharID() {
		return aadharID;
	}
	public void setAadharID(String aadharID) {
		this.aadharID = aadharID;
	}
	public String getAadharEID() {
		return aadharEID;
	}
	public void setAadharEID(String aadharEID) {
		this.aadharEID = aadharEID;
	}
	public String getSwSite() {
		return swSite;
	}
	public void setSwSite(String swSite) {
		this.swSite = swSite;
	}
	public String getSwSize() {
		return swSize;
	}
	public void setSwSize(String swSize) {
		this.swSize = swSize;
	}
	public String getSwExtension() {
		return swExtension;
	}
	public void setSwExtension(String swExtension) {
		this.swExtension = swExtension;
	}
	public String getSwColour() {
		return swColour;
	}
	public void setSwColour(String swColour) {
		this.swColour = swColour;
	}
	public String getSwConsistency() {
		return swConsistency;
	}
	public void setSwConsistency(String swConsistency) {
		this.swConsistency = swConsistency;
	}
		public String getSwTenderness() {
		return swTenderness;
	}
	public void setSwTenderness(String swTenderness) {
		this.swTenderness = swTenderness;
	}
	public String getSwBorders() {
		return swBorders;
	}
	public void setSwBorders(String swBorders) {
		this.swBorders = swBorders;
	}	
	public String getPsSite() {
		return psSite;
	}
	public void setPsSite(String psSite) {
		this.psSite = psSite;
	}
	public String getPsDischarge() {
		return psDischarge;
	}
	public void setPsDischarge(String psDischarge) {
		this.psDischarge = psDischarge;
	}
	public String getMedicalHistVal() {
		return medicalHistVal;
	}
	public void setMedicalHistVal(String medicalHistVal) {
		this.medicalHistVal = medicalHistVal;
	}
	public List<CommonDtlsVO> getReferalDtls() {
		return referalDtls;
	}
	public void setReferalDtls(List<CommonDtlsVO> referalDtls) {
		this.referalDtls = referalDtls;
	}
	public List<LabelBean> getDispGenInvList() {
		return dispGenInvList;
	}
	public void setDispGenInvList(List<LabelBean> dispGenInvList) {
		this.dispGenInvList = dispGenInvList;
	}
	public String getMedicalCat() {
		return medicalCat;
	}
	public void setMedicalCat(String medicalCat) {
		this.medicalCat = medicalCat;
	}

	public String getMedicalSpclty() {
		return medicalSpclty;
	}
	public void setMedicalSpclty(String medicalSpclty) {
		this.medicalSpclty = medicalSpclty;
	}

		public String getTokenNo() {
			return tokenNo;
		}
		public void setTokenNo(String tokenNo) {
			this.tokenNo = tokenNo;
		}
	public String getSpecialityType() {
		return specialityType;
	}
	public void setSpecialityType(String specialityType) {
		this.specialityType = specialityType;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public List<DrugsVO> getOtherDrugLt() {
		return otherDrugLt;
	}
	public void setOtherDrugLt(List<DrugsVO> otherDrugLt) {
		this.otherDrugLt = otherDrugLt;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getQuantity1() {
		return quantity1;
	}
	public void setQuantity1(String quantity1) {
		this.quantity1 = quantity1;
	}
	public String getLabTokenNo() {
		return labTokenNo;
	}
	public void setLabTokenNo(String labTokenNo) {
		this.labTokenNo = labTokenNo;
	}

	public String getDispDrugID() {
		return dispDrugID;
	}
	public void setDispDrugID(String dispDrugID) {
		this.dispDrugID = dispDrugID;
	}
	public String getDrugexpiryDate() {
		return drugexpiryDate;
	}
	public void setDrugexpiryDate(String drugexpiryDate) {
		this.drugexpiryDate = drugexpiryDate;
	}
	public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	public String getDispDrugName() {
		return dispDrugName;
	}
	public void setDispDrugName(String dispDrugName) {
		this.dispDrugName = dispDrugName;
	}
	public String getDispDrugPrice() {
		return dispDrugPrice;
	}
	public void setDispDrugPrice(String dispDrugPrice) {
		this.dispDrugPrice = dispDrugPrice;
	}
	public String getPresentDrugQuantity() {
		return presentDrugQuantity;
	}
	public void setPresentDrugQuantity(String presentDrugQuantity) {
		this.presentDrugQuantity = presentDrugQuantity;
	}
	public String getDispDrugMfg() {
		return dispDrugMfg;
	}
	public void setDispDrugMfg(String dispDrugMfg) {
		this.dispDrugMfg = dispDrugMfg;
	}
	public String getDrugPrice() {
		return drugPrice;
	}
	public void setDrugPrice(String drugPrice) {
		this.drugPrice = drugPrice;
	}
	public String getHospContactNo() {
		return hospContactNo;
	}
	public void setHospContactNo(String hospContactNo) {
		this.hospContactNo = hospContactNo;
	}
	public List<CommonDtlsVO> getPastDrugHist() {
		return pastDrugHist;
	}
	public void setPastDrugHist(List<CommonDtlsVO> pastDrugHist) {
		this.pastDrugHist = pastDrugHist;
	}
	public String getEnablePharma() {
		return enablePharma;
	}
	public void setEnablePharma(String enablePharma) {
		this.enablePharma = enablePharma;
	}
	public List<LabelBean> getDispatchedDetailsList() {
		return dispatchedDetailsList;
	}
	public void setDispatchedDetailsList(List<LabelBean> dispatchedDetailsList) {
		this.dispatchedDetailsList = dispatchedDetailsList;
	}
	public String getRobotSurg() {
		return robotSurg;
	}
	public void setRobotSurg(String robotSurg) {
		this.robotSurg = robotSurg;
	}
	public List<LabelBean> getInvestList() {
		return investList;
	}
	public void setInvestList(List<LabelBean> investList) {
		this.investList = investList;
	}
	public String[] getMedicalHistsText() {
		return medicalHistsText;
	}
	public void setMedicalHistsText(String[] medicalHistsText) {
		this.medicalHistsText = medicalHistsText;
	}
	public List<LabelBean> getDispDentalTreatDoneLst() {
		return dispDentalTreatDoneLst;
	}
	public void setDispDentalTreatDoneLst(List<LabelBean> dispDentalTreatDoneLst) {
		this.dispDentalTreatDoneLst = dispDentalTreatDoneLst;
	}
	public List<LabelBean> getDispDentalTreatDoneSubLst() {
		return dispDentalTreatDoneSubLst;
	}
	public void setDispDentalTreatDoneSubLst(
			List<LabelBean> dispDentalTreatDoneSubLst) {
		this.dispDentalTreatDoneSubLst = dispDentalTreatDoneSubLst;
	}
	public String getTextValDH1() {
		return textValDH1;
	}
	public void setTextValDH1(String textValDH1) {
		this.textValDH1 = textValDH1;
	}
	public String getTextValDH2() {
		return textValDH2;
	}
	public void setTextValDH2(String textValDH2) {
		this.textValDH2 = textValDH2;
	}
	public String getTextValDH3() {
		return textValDH3;
	}
	public void setTextValDH3(String textValDH3) {
		this.textValDH3 = textValDH3;
	}
	public String getTextValDH4() {
		return textValDH4;
	}
	public void setTextValDH4(String textValDH4) {
		this.textValDH4 = textValDH4;
	}
	public String getTextValDH5() {
		return textValDH5;
	}
	public void setTextValDH5(String textValDH5) {
		this.textValDH5 = textValDH5;
	}
	public String getTextValDH6() {
		return textValDH6;
	}
	public void setTextValDH6(String textValDH6) {
		this.textValDH6 = textValDH6;
	}
	public String getTextValDH7() {
		return textValDH7;
	}
	public void setTextValDH7(String textValDH7) {
		this.textValDH7 = textValDH7;
	}
	public String getTextValDH8() {
		return textValDH8;
	}
	public void setTextValDH8(String textValDH8) {
		this.textValDH8 = textValDH8;
	}
	public String getTextValDH9() {
		return textValDH9;
	}
	public void setTextValDH9(String textValDH9) {
		this.textValDH9 = textValDH9;
	}
	public String getTextValDH71() {
		return textValDH71;
	}
	public void setTextValDH71(String textValDH71) {
		this.textValDH71 = textValDH71;
	}
	public String getTextValDH72() {
		return textValDH72;
	}
	public void setTextValDH72(String textValDH72) {
		this.textValDH72 = textValDH72;
	}
	public String getTextValDH73() {
		return textValDH73;
	}
	public void setTextValDH73(String textValDH73) {
		this.textValDH73 = textValDH73;
	}
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	public String getCritical() {
		return critical;
	}
	public void setCritical(String critical) {
		this.critical = critical;
	}
	public List<LabelBean> getAppointmentsList() {
		return appointmentsList;
	}
	public void setAppointmentsList(List<LabelBean> appointmentsList) {
		this.appointmentsList = appointmentsList;
	}
	public String getDispname() {
		return dispname;
	}
	public void setDispname(String dispname) {
		this.dispname = dispname;
	}
	public List<CreateEmployeeVO> getLevRepList() {
		return levRepList;
	}
	public void setLevRepList(List<CreateEmployeeVO> levRepList) {
		this.levRepList = levRepList;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public String getInvoiceNumberNew() {
 		return invoiceNumberNew;
	}
	public void setInvoiceNumberNew(String invoiceNumberNew) {
		this.invoiceNumberNew = invoiceNumberNew;
	}
	public String getAis_no() {
		return ais_no;
	}
	public void setAis_no(String ais_no) {
		this.ais_no = ais_no;
	}
	public String getSerType() {
		return serType;
	}
	public void setSerType(String serType) {
		this.serType = serType;
	}
	public String getAyushID() {
		return ayushID;
	}
	public void setAyushID(String ayushID) {
		this.ayushID = ayushID;
	}
	public String getAisType() {
		return aisType;
	}
	public void setAisType(String aisType) {
		this.aisType = aisType;
	}
	public String getServType() {
		return servType;
	}
	public void setServType(String servType) {
		this.servType = servType;
	}
	public String getAisRemarks() {
		return aisRemarks;
	}
	public void setAisRemarks(String aisRemarks) {
		this.aisRemarks = aisRemarks;
	}
	public String getFname1() {
		return fname1;
	}
	public void setFname1(String fname1) {
		this.fname1 = fname1;
	}
	private List<LabelBean> ledrepList;
	public List<LabelBean> getLedrepList() {
		return ledrepList;
	}
	public void setLedrepList(List<LabelBean> ledrepList) {
		this.ledrepList = ledrepList;
	}
	private List<LabelBean> drugExpList;
	public List<LabelBean> getDrugExpList() {
		return drugExpList;
	}
	public void setDrugExpList(List<LabelBean> drugExpList) {
		this.drugExpList = drugExpList;
	}
	private List<LabelBean> indentPoList;
	
	public List<LabelBean> getIndentPoList() {
		return indentPoList;
	}
	public void setIndentPoList(List<LabelBean> indentPoList) {
		this.indentPoList = indentPoList;
	}
	public List<LabelBean> getReturnVendorList() {
		return returnVendorList;
	}
	public void setReturnVendorList(List<LabelBean> returnVendorList) {
		this.returnVendorList = returnVendorList;
	}
	public List<LabelBean> getCreditNoteList() {
		return creditNoteList;
	}
	public void setCreditNoteList(List<LabelBean> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}
	private List<LabelBean> indentList;
	public List<LabelBean> getIndentList() {
		return indentList;
	}
	public void setIndentList(List<LabelBean> indentList) {
		this.indentList = indentList;
	}
	public FormFile getDrugsIssue() {
		return drugsIssue;
	}
	public void setDrugsIssue(FormFile drugsIssue) {
		this.drugsIssue = drugsIssue;
	}
	private List<LabelBean> patList;
	public List<LabelBean> getPatList() {
		return patList;
	}
	public void setPatList(List<LabelBean> patList) {
		this.patList = patList;
	}
	private FormFile drugsIssue2;
    private FormFile drugsIssue3;
    
    public FormFile getDrugsIssue2() {
		return drugsIssue2;
	}
	public void setDrugsIssue2(FormFile drugsIssue2) {
		this.drugsIssue2 = drugsIssue2;
	}
	public FormFile getDrugsIssue3() {
		return drugsIssue3;
	}
	public void setDrugsIssue3(FormFile drugsIssue3) {
		this.drugsIssue3 = drugsIssue3;
	}
	public String getContractYear() {
		return contractYear;
	}
	public void setContractYear(String contractYear) {
		this.contractYear = contractYear;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSpecialities() {
		return specialities;
	}
	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}
	public List<SpecialityVO> getSpecLt() {
		return specLt;
	}
	public void setSpecLt(List<SpecialityVO> specLt) {
		this.specLt = specLt;
	}
	public String getDrugTypeID() {
		return drugTypeID;
	}
	public void setDrugTypeID(String drugTypeID) {
		this.drugTypeID = drugTypeID;
	}
	public List<LabelBean> getWellnesscenters() {
		return wellnesscenters;
	}
	public void setWellnesscenters(List<LabelBean> wellnesscenters) {
		this.wellnesscenters = wellnesscenters;
	}
	public String getWellnesscenter() {
		return wellnesscenter;
	}
	public void setWellnesscenter(String wellnesscenter) {
		this.wellnesscenter = wellnesscenter;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public List<LabelBean> getPOList() {
		return POList;
	}
	public void setPOList(List<LabelBean> pOList) {
		POList = pOList;
	}
	public String getIndent_no() {
		return indent_no;
	}
	public void setIndent_no(String indent_no) {
		this.indent_no = indent_no;
	}
	public List<LabelBean> getIndentNolist() {
		return indentNolist;
	}
	public void setIndentNolist(List<LabelBean> indentNolist) {
		this.indentNolist = indentNolist;
	}
	public List<LabelBean> getIndentedPOList() {
		return indentedPOList;
	}
	public void setIndentedPOList(List<LabelBean> indentedPOList) {
		this.indentedPOList = indentedPOList;
	}
	public String getDistributor() {
		return distributor;
	}
	public void setDistributor(String distributor) {
		this.distributor = distributor;
	}
	//Chandana - 8251 - Added below
	private FormFile id_File_Inc;
	private String fileName;
	public FormFile getId_File_Inc() {
		return id_File_Inc;
	}
	public void setId_File_Inc(FormFile id_File_Inc) {
		this.id_File_Inc = id_File_Inc;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	//Chandana - 8133 - for Abha
	private String abhaDoneYN;
	private String errMsgForEkyc;
	private String abhaId;
	private String ekycFlag;
	
	public String getAbhaId() {
		return abhaId;
	}
	public void setAbhaId(String abhaId) {
		this.abhaId = abhaId;
	}
	public String getAbhaDoneYN() {
		return abhaDoneYN;
	}
	public void setAbhaDoneYN(String abhaDoneYN) {
		this.abhaDoneYN = abhaDoneYN;
	}
	public String getErrMsgForEkyc() {
		return errMsgForEkyc;
	}
	public void setErrMsgForEkyc(String errMsgForEkyc) {
		this.errMsgForEkyc = errMsgForEkyc;
	}	
	public String getEkycFlag() {
		return ekycFlag;
	}
	public void setEkycFlag(String ekycFlag) {
		this.ekycFlag = ekycFlag;
	}
	
	
	
}