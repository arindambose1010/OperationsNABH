package com.ahct.caseSearch.service;

import java.util.List;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;


public interface CasesSearchService  {
	public List<LabelBean> getCatList();
	public List<LabelBean> getCaseStatus(String module);
	public List<LabelBean> getListOfSurgery();
	public List<LabelBean> getErroneousList();
	public CasesSearchVO getFeedbackList(CasesSearchVO casesSearchVO);
	public CasesSearchVO getListCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getTeleIntimationCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getRegTeleIntimationCases(CasesSearchVO casesSearchVO);
	//public CasesSearchVO getUserRole(CasesSearchVO casesSearchVO);
	public String setviewFlag(String pStrCaseId, String pStrFlag, String lStrEmpId);
	public List<LabelBean> getCaseErrStatus();
	public CasesSearchVO getListIssues(CasesSearchVO casesSearchVO);
	public List<CasesSearchVO> getDeathCases(CasesSearchVO casesSearchVO);
	public CasesSearchVO getAccountDetails(CasesSearchVO casesSearchVO);
	public CasesSearchVO getListCasesDirect(CasesSearchVO casesSearchVO);
	public String getTimeOutCount(String caseId1, List<LabelBean> groupsList, String lStrModule);
	public String getCaseStatusForCase(String caseId);
    /*
     * Used to get all the therapies of the case
     */
    public List<CasesSearchVO> getDopDtls(String caseId);
	/*
	 * Added by Srikalyan for CSV Downloads Query Insertion
	 */
	public CasesSearchVO getListCasesForCSV(CasesSearchVO casesSearchVO);
	/*
	 * Added by Srikalyan to view CSV Downloads  
	 */
	public CasesSearchVO viewCSVDownloads(CasesSearchVO casesSearchVO);
	/*
	 * Added by Srikalyan to get User Details  
	 */
	public CasesSearchVO getUserDtls(CasesSearchVO casesSearchVO);
	/*
	 * Added by Srikalyan to get CSV File Path  
	 */
	public CasesSearchVO getCSVPath(CasesSearchVO casesSearchVO);
	public CasesSearchVO getCasesSearchCSV(CasesSearchVO casesSearchVO);

	public CasesSearchVO getListCasesNabh(CasesSearchVO casesSearchVO);
	public String getTherapyId(String caseId);
	public String getPnlNameNew(String autoCaseId);
}
