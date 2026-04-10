package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_PATIENT_TESTS")
public class EhfPatientTests implements Serializable {

	private String attachTotalPath;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String patientId;
    private String testId;
    private Long sno;
    private String investPrice;
    private String testName;
    private String fromDisp;
    //sai krishna:CR#8134API Integration.
    private String orderId;
	private String epsodId;
	private String tdInvstFlg;
	private String respRemarks;

    public EhfPatientTests() {
    }
    @Id
    @Column(name="SNO", nullable = false)
    public Long getSno() {
            return sno;
        }
    public void setSno(Long sno) {
        this.sno = sno;
    }

    @Column(name="ATTACH_PATH")
    public String getAttachTotalPath() {
        return attachTotalPath;
    }

    public void setAttachTotalPath(String attachTotalPath) {
        this.attachTotalPath = attachTotalPath;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable=false, length=7)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = false)
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }

    @Column(name="PATIENT_ID")
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    @Column(name="TEST_ID")
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
    
    @Column(name="INVEST_PRICE")
	public String getInvestPrice() {
		return investPrice;
	}
	public void setInvestPrice(String investPrice) {
		this.investPrice = investPrice;
	}
	
	 @Column(name="TEST_NAME")
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	 @Column(name="FROM_DISP")
	public String getFromDisp() {
		return fromDisp;
	}
	public void setFromDisp(String fromDisp) {
		this.fromDisp = fromDisp;
	}
    //sai:API Integration.
	 @Column(name="ORDER_ID")
	    public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		@Column(name="EPOSD_ID")
		public String getEpsodId() {
			return epsodId;
		}
		public void setEpsodId(String epsodId) {
			this.epsodId = epsodId;
		}
		@Column(name="TD_INVST_FLG")
		public String getTdInvstFlg() {
			return tdInvstFlg;
		}
		public void setTdInvstFlg(String tdInvstFlg) {
			this.tdInvstFlg = tdInvstFlg;
		}
		
		@Column(name="RESP_REMARKS")
		public String getRespRemarks() {
			return respRemarks;
		}

		public void setRespRemarks(String respRemarks) {
			this.respRemarks = respRemarks;
		}
}
