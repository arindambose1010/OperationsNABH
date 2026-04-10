package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHF_DISP_TRANSFER_AUDIT")
public class EhfDispTransferAudit implements Serializable {
	
	    private EhfDispTransferAuditId id;
		
		private long quantity;
		private String crtUsr;
		private Date crtDate;
		private String lstUpdUsr;
		private Date lstUpdDate;
		
		  public void setId(EhfDispTransferAuditId id) {
		        this.id = id;
		    }

		    
		    @EmbeddedId
		    @AttributeOverrides( {
		        @AttributeOverride(name="fromCode", column=@Column(name="FROM_CODE", nullable=false) ), 
		        @AttributeOverride(name="toCode", column=@Column(name="TO_CODE", nullable=false) ),
		        @AttributeOverride(name="actOrder", column=@Column(name="ACT_ORDER", nullable=false) )
		        } )
		    public EhfDispTransferAuditId getId() {
		        return id;
		    }

		@Column(name="QUANTITY")
		public long getQuantity() {
			return quantity;
		}
		public void setQuantity(long quantity) {
			this.quantity = quantity;
		}
		@Column(name="CRT_USR")
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="CRT_DT")
		public Date getCrtDate() {
			return crtDate;
		}
		public void setCrtDate(Date crtDate) {
			this.crtDate = crtDate;
		}
		@Column(name="LST_UPD_USR")
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name="LST_UPD_DT")
		public Date getLstUpdDate() {
			return lstUpdDate;
		}
		public void setLstUpdDate(Date lstUpdDate) {
			this.lstUpdDate = lstUpdDate;
		}
		

}
