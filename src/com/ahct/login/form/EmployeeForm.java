package com.ahct.login.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.common.vo.LabelBean;

public class EmployeeForm extends ActionForm
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String studentId;
	private String address;
	private String course;
	private List<LabelBean> empList;
	private List<LabelBean> empList1;
	private List<LabelBean> empList2;
	private String msg;
	private String errmsg;
	private String enrollId;
	private String ViewId;
	private String enrollPrntId;
	private String ENROLLPRNTID;
	private String dOs;
	private String preexistDiseases;
	private String drugscurrent;
	private String knownDrugaller;
	private String mdclhtry;
	private String anysurgerdne;
	private String noSurgery;
	private String anycongenital;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String viewflg;
	private String MEDFLG;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public List<LabelBean> getEmpList() {
		return empList;
	}
	public void setEmpList(List<LabelBean> empList) {
		this.empList = empList;
	}
	public List<LabelBean> getEmpList1() {
		return empList1;
	}
	public void setEmpList1(List<LabelBean> empList1) {
		this.empList1 = empList1;
	}
	public List<LabelBean> getEmpList2() {
		return empList2;
	}
	public void setEmpList2(List<LabelBean> empList2) {
		this.empList2 = empList2;
	}
	public String getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}
	public String getEnrollPrntId() {
		return enrollPrntId;
	}
	public void setEnrollPrntId(String enrollPrntId) {
		this.enrollPrntId = enrollPrntId;
	}
	
	public String getPreexistDiseases() {
		return preexistDiseases;
	}
	public void setPreexistDiseases(String preexistDiseases) {
		this.preexistDiseases = preexistDiseases;
	}
	public String getDrugscurrent() {
		return drugscurrent;
	}
	public void setDrugscurrent(String drugscurrent) {
		this.drugscurrent = drugscurrent;
	}
	public String getMdclhtry() {
		return mdclhtry;
	}
	public void setMdclhtry(String mdclhtry) {
		this.mdclhtry = mdclhtry;
	}
	public String getAnysurgerdne() {
		return anysurgerdne;
	}
	public void setAnysurgerdne(String anysurgerdne) {
		this.anysurgerdne = anysurgerdne;
	}
	public String getNoSurgery() {
		return noSurgery;
	}
	public void setNoSurgery(String noSurgery) {
		this.noSurgery = noSurgery;
	}
	public String getAnycongenital() {
		return anycongenital;
	}
	public void setAnycongenital(String anycongenital) {
		this.anycongenital = anycongenital;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	public String getViewflg() {
		return viewflg;
	}
	public void setViewflg(String viewflg) {
		this.viewflg = viewflg;
	}
	public String getKnownDrugaller() {
		return knownDrugaller;
	}
	public void setKnownDrugaller(String knownDrugaller) {
		this.knownDrugaller = knownDrugaller;
	}
	public String getdOs() {
		return dOs;
	}
	public void setdOs(String dOs) {
		this.dOs = dOs;
	}
	public String getENROLLPRNTID() {
		return ENROLLPRNTID;
	}
	public void setENROLLPRNTID(String eNROLLPRNTID) {
		ENROLLPRNTID = eNROLLPRNTID;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getViewId() {
		return ViewId;
	}
	public void setViewId(String viewId) {
		ViewId = viewId;
	}
	public String getMEDFLG() {
		return MEDFLG;
	}
	public void setMEDFLG(String mEDFLG) {
		MEDFLG = mEDFLG;
	}
	
}
