
package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


	@Entity
	@Table(name = "EHFM_EMP_DISP_ACCT_DTLS")
	public class EhfmEmpDispAcctDtls implements Serializable {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@EmbeddedId
		@AttributeOverrides({
			@AttributeOverride(name="userID", column=@Column(name="USER_ID", nullable=false) ), 
	        @AttributeOverride(name="accountNo", column=@Column(name="ACCOUNT_NO", nullable=false) )
	      } )
		
		private EhfmEmpDispAcctDtlsId id;
		
		@Column(name = "bank_id", nullable = true)
		private String bankId;
		@Column(name = "name_as_per_acc", nullable = true)
		private String nameAsPerAcc;
		@Column(name = "pan_number", nullable = true)
		private String panNumber;
		@Column(name = "pan_holder_name", nullable = true)
		private String panHolderName;
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "crt_date", nullable = true)
		private Date crtDt;
		@Column(name = "crt_usr", nullable = true)
		private String crtUsr;
		@Column(name = "lst_upd_usr", nullable = true)
		private String lstUpdUsr;
		@Column(name = "lst_upd_date", nullable = true)
		@Temporal(TemporalType.TIMESTAMP)
		private Date lstUpdDt;
		@Column(name = "active_yn", nullable = true)
		private String activeYn;

		
		public EhfmEmpDispAcctDtls() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public EhfmEmpDispAcctDtls(EhfmEmpDispAcctDtlsId id, String bankId,
				String nameAsPerAcc, String panNumber, String panHolderName,
				Date crtDt, String crtUsr, String lstUpdUsr, Date lstUpdDt,
				String activeYn) {
			super();
			this.id = id;
			this.bankId = bankId;
			this.nameAsPerAcc = nameAsPerAcc;
			this.panNumber = panNumber;
			this.panHolderName = panHolderName;
			this.crtDt = crtDt;
			this.crtUsr = crtUsr;
			this.lstUpdUsr = lstUpdUsr;
			this.lstUpdDt = lstUpdDt;
			this.activeYn = activeYn;
		}
		
		public EhfmEmpDispAcctDtlsId getId() {
			return id;
		}

		public void setId(EhfmEmpDispAcctDtlsId id) {
			this.id = id;
		}

		public String getBankId() {
			return bankId;
		}

		public void setBankId(String bankId) {
			this.bankId = bankId;
		}

		public String getNameAsPerAcc() {
			return nameAsPerAcc;
		}

		public void setNameAsPerAcc(String nameAsPerAcc) {
			this.nameAsPerAcc = nameAsPerAcc;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getPanHolderName() {
			return panHolderName;
		}

		public void setPanHolderName(String panHolderName) {
			this.panHolderName = panHolderName;
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

		public String getLstUpdUsr() {
			return lstUpdUsr;
		}

		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}

		public Date getLstUpdDt() {
			return lstUpdDt;
		}

		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}

		public String getActiveYn() {
			return activeYn;
		}

		public void setActiveYn(String string) {
			this.activeYn = string;
		}


}
