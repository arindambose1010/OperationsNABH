package com.ahct.reports.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PreauthReportVo {
	private String sno;
	private String name;
	private String action1;
	private String action2;
	private String action3;
	private String action4;
	private String action5;
	private String action6;
	private String action7;
	private String action8;
	private String action9;
	private String action10;
	private String action11;
	private String action12;
	private String action13;
	private String action14;
	private String action15;
	private String action16;
	private String action17;
	private String hospName;
	private String preAuthCount;
	private String preAuthAmt;
	private String enhApprAmt;
	private String comorBidAmt;
	private String preAuthApprAmt;
	private List<PreauthReportVo> lstPreauthReportVo;
	private String approveCnt;
	private String rejCnt;
	private String pendingCnt;
	private String totCnt;
	private String miscellanoCnt;
	
	private String approveLabel;
	private String rejLabel;
	private String pendingLabel;
	
	
	private String userRole;
	
	private String date;
	private String userId;
	private Number COUNT;
	private String cmbDtlId;
	private List<LabelBean> lstLabelBean;
	public String getServId() {
		return servId;
	}
	public void setServId(String servId) {
		this.servId = servId;
	}
	public String getCateId() {
		return cateId;
	}
	public void setCateId(String cateId) {
		this.cateId = cateId;
	}
	private List<LabelBean> lstLabelBean2;
	private  List<Object> lstObj ;
	private Map<String, Object> resMap;
	
	private Number caseCount;
	private String docName;
	private String designation;
	private String servId;
	private String cateId;
	
	
	public Number getCaseCount() {
		return caseCount;
	}
	public void setCaseCount(Number caseCount) {
		this.caseCount = caseCount;
	}
	public String getDocName() {
		return docName;
	}
	public void setDocName(String docName) {
		this.docName = docName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getMiscellanoCnt() {
		return miscellanoCnt;
	}
	public void setMiscellanoCnt(String miscellanoCnt) {
		this.miscellanoCnt = miscellanoCnt;
	}
	public String getAction9() {
		return action9;
	}
	public void setAction9(String action9) {
		this.action9 = action9;
	}
	public String getAction10() {
		return action10;
	}
	public void setAction10(String action10) {
		this.action10 = action10;
	}
	public String getAction11() {
		return action11;
	}
	public void setAction11(String action11) {
		this.action11 = action11;
	}
	public String getAction1() {
		return action1;
	}
	public void setAction1(String action1) {
		this.action1 = action1;
	}
	public String getAction2() {
		return action2;
	}
	public void setAction2(String action2) {
		this.action2 = action2;
	}
	public String getAction3() {
		return action3;
	}
	public void setAction3(String action3) {
		this.action3 = action3;
	}
	public String getAction4() {
		return action4;
	}
	public void setAction4(String action4) {
		this.action4 = action4;
	}
	public String getAction5() {
		return action5;
	}
	public void setAction5(String action5) {
		this.action5 = action5;
	}
	public String getAction6() {
		return action6;
	}
	public void setAction6(String action6) {
		this.action6 = action6;
	}
	public String getAction7() {
		return action7;
	}
	public void setAction7(String action7) {
		this.action7 = action7;
	}
	public String getAction8() {
		return action8;
	}
	public void setAction8(String action8) {
		this.action8 = action8;
	}
	public String getAction12() {
		return action12;
	}
	public void setAction12(String action12) {
		this.action12 = action12;
	}
	public String getAction13() {
		return action13;
	}
	public void setAction13(String action13) {
		this.action13 = action13;
	}
	public String getAction14() {
		return action14;
	}
	public void setAction14(String action14) {
		this.action14 = action14;
	}
	public String getAction15() {
		return action15;
	}
	public void setAction15(String action15) {
		this.action15 = action15;
	}
	public String getAction16() {
		return action16;
	}
	public void setAction16(String action16) {
		this.action16 = action16;
	}
	public String getAction17() {
		return action17;
	}
	public void setAction17(String action17) {
		this.action17 = action17;
	}
	public Map<String, Object> getResMap() {
		return resMap;
	}
	public void setResMap(Map<String, Object> resMap) {
		this.resMap = resMap;
	}
	public List<Object> getLstObj() {
		return lstObj;
	}
	public void setLstObj(List<Object> lstObj) {
		this.lstObj = lstObj;
	}
	public List<LabelBean> getLstLabelBean2() {
		return lstLabelBean2;
	}
	public void setLstLabelBean2(List<LabelBean> lstLabelBean2) {
		this.lstLabelBean2 = lstLabelBean2;
	}
	public String getCmbDtlId() {
		return cmbDtlId;
	}
	public void setCmbDtlId(String cmbDtlId) {
		this.cmbDtlId = cmbDtlId;
	}
	
	
	

	public List<LabelBean> getLstLabelBean() {
		return lstLabelBean;
	}
	public void setLstLabelBean(List<LabelBean> lstLabelBean) {
		this.lstLabelBean = lstLabelBean;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		if(this.date==null)
			return "";
		else
		return sdf.format(date);
	}
	public void setDate(Date date) {
		date=date;
	}
	public String getApproveLabel() {
		return approveLabel;
	}
	public void setApproveLabel(String approveLabel) {
		this.approveLabel = approveLabel;
	}
	public String getRejLabel() {
		return rejLabel;
	}
	public void setRejLabel(String rejLabel) {
		this.rejLabel = rejLabel;
	}
	public String getPendingLabel() {
		return pendingLabel;
	}
	public void setPendingLabel(String pendingLabel) {
		this.pendingLabel = pendingLabel;
	}
	public String getApproveCnt() {
		return approveCnt;
	}
	public void setApproveCnt(String approveCnt) {
		this.approveCnt = approveCnt;
	}
	public String getRejCnt() {
		return rejCnt;
	}
	public void setRejCnt(String rejCnt) {
		this.rejCnt = rejCnt;
	}
	public String getPendingCnt() {
		return pendingCnt;
	}
	public void setPendingCnt(String pendingCnt) {
		this.pendingCnt = pendingCnt;
	}
	public List<PreauthReportVo> getLstPreauthReportVo() {
		return lstPreauthReportVo;
	}
	public void setLstPreauthReportVo(List<PreauthReportVo> lstPreauthReportVo) {
		this.lstPreauthReportVo = lstPreauthReportVo;
	}
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getPreAuthCount() {
		return preAuthCount;
	}
	public void setPreAuthCount(String preAuthCount) {
		this.preAuthCount = preAuthCount;
	}
	public String getPreAuthAmt() {
		return preAuthAmt;
	}
	public void setPreAuthAmt(String preAuthAmt) {
		this.preAuthAmt = preAuthAmt;
	}
	public String getEnhApprAmt() {
		return enhApprAmt;
	}
	public void setEnhApprAmt(String enhApprAmt) {
		this.enhApprAmt = enhApprAmt;
	}
	public String getComorBidAmt() {
		return comorBidAmt;
	}
	public void setComorBidAmt(String comorBidAmt) {
		this.comorBidAmt = comorBidAmt;
	}
	public String getPreAuthApprAmt() {
		return preAuthApprAmt;
	}
	public void setPreAuthApprAmt(String preAuthApprAmt) {
		this.preAuthApprAmt = preAuthApprAmt;
	}
	
}
