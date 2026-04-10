package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

@Entity
@Table(name = "EHF_WC_INDENT_VNDR_DOC_ATT") 
public class EhfVendorUpload implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DOC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EHF_DOC_ID")
	@SequenceGenerator(name = "SEQ_EHF_DOC_ID", sequenceName = "SEQ_EHF_DOC_ID", allocationSize = 1)
	private Long docId;

		@Temporal(TemporalType.DATE)
		@Column(name="CRT_DATE")
		private Date crtDt;

		@Column(name="CRT_USR")
		private String crtUsr;
		
		@Column(name="ITEM_ID")
		private String itemId;
		
		@Column(name="INDENT_COUNT")
		private String indentCount;

		@Column(name="INDENT_ID")
		private String indentId;

		@Temporal(TemporalType.DATE)
		@Column(name="LST_UPD_DT")
		private Date lstUpdDt;

		@Column(name="LST_UPD_USR")
		private String lstUpdUsr;

		@Column(name="PO_ID")
		private String poId;

		@Column(name="STATUS")
		private String status;
		
		@Column(name="INTERNAL_STATUS")
		private String internalStatus;
	
	   @Column(name="INVOICE_NO")
	   private String invoiceNo;
	   
	   @Column(name="INVOICE_DATE")
	   private String invoiceDate;
	
	   @Column(name="ACTUAL_NAME")
	   private String actualName;
	   
	   @Column(name="SAVED_NAME")
	   private String savedName;
	   
	   @Column(name="SEQ_ID")
	   private Long seqId;
	   
		public static long getSerialversionuid() {
		return serialVersionUID;
	}

		public Long getDocId() {
		return docId;
		}

		public void setDocId(Long docId) {
			this.docId = docId;
		}

		public String getInvoiceNo() {
			return invoiceNo;
		}

		public void setInvoiceNo(String invoiceNo) {
			this.invoiceNo = invoiceNo;
		}

		public String getInvoiceDate() {
			return invoiceDate;
		}

		public void setInvoiceDate(String invoiceDate) {
			this.invoiceDate = invoiceDate;
		}
		
		public Date getCrtDt() {
			return this.crtDt;
		}

		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}

		public String getCrtUsr() {
			return this.crtUsr;
		}

		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}

		public String getIndentCount() {
			return this.indentCount;
		}

		public void setIndentCount(String indentCount) {
			this.indentCount = indentCount;
		}

		public Date getLstUpdDt() {
			return this.lstUpdDt;
		}

		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}

		public String getLstUpdUsr() {
			return this.lstUpdUsr;
		}

		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}

		public String getPoId() {
			return this.poId;
		}

		public void setPoId(String poId) {
			this.poId = poId;
		}

		public String getStatus() {
			return this.status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getInternalStatus() {
			return internalStatus;
		}

		public void setInternalStatus(String internalStatus) {
			this.internalStatus = internalStatus;
		}

		public String getItemId() {
			return itemId;
		}

		public void setItemId(String itemId) {
			this.itemId = itemId;
		}

		public String getIndentId() {
			return indentId;
		}

		public void setIndentId(String indentId) {
			this.indentId = indentId;
		}

		public String getActualName() {
			return actualName;
		}

		public void setActualName(String actualName) {
			this.actualName = actualName;
		}

		public String getSavedName() {
			return savedName;
		}

		public void setSavedName(String savedName) {
			this.savedName = savedName;
		}

		public Long getSeqId() {
			return seqId;
		}
		
		public void setSeqId(Long seqId) {
			this.seqId = seqId;
		}
		
	}
