package com.ahct.common.vo;

import java.io.Serializable;

public class LabelBeanInventory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	 
		private String DRUG_TYPE_ID;
		private String DRUG_TYPE_NAME;	
		private String DRUG_ID;
		private String DRUG_NAME;	
		private String MNFCTR_ID;
		private String MNFCTR_NAME;
		private String DSTRBTR_ID;
		private String DSTRBTR_NAME;
		private Number ID_DRUG_AUDIT;
		
		public String getDRUG_TYPE_ID() {
			return DRUG_TYPE_ID;
		}
		public void setDRUG_TYPE_ID(String dRUG_TYPE_ID) {
			DRUG_TYPE_ID = dRUG_TYPE_ID;
		}
		public String getDRUG_TYPE_NAME() {
			return DRUG_TYPE_NAME;
		}
		public void setDRUG_TYPE_NAME(String dRUG_TYPE_NAME) {
			DRUG_TYPE_NAME = dRUG_TYPE_NAME;
		}
		public String getDRUG_ID() {
			return DRUG_ID;
		}
		public void setDRUG_ID(String dRUG_ID) {
			DRUG_ID = dRUG_ID;
		}
		public String getDRUG_NAME() {
			return DRUG_NAME;
		}
		public void setDRUG_NAME(String dRUG_NAME) {
			DRUG_NAME = dRUG_NAME;
		}
		public String getMNFCTR_ID() {
			return MNFCTR_ID;
		}
		public void setMNFCTR_ID(String mNFCTR_ID) {
			MNFCTR_ID = mNFCTR_ID;
		}
		public String getMNFCTR_NAME() {
			return MNFCTR_NAME;
		}
		public void setMNFCTR_NAME(String mNFCTR_NAME) {
			MNFCTR_NAME = mNFCTR_NAME;
		}
		public String getDSTRBTR_ID() {
			return DSTRBTR_ID;
		}
		public void setDSTRBTR_ID(String dSTRBTR_ID) {
			DSTRBTR_ID = dSTRBTR_ID;
		}
		public String getDSTRBTR_NAME() {
			return DSTRBTR_NAME;
		}
		public void setDSTRBTR_NAME(String dSTRBTR_NAME) {
			DSTRBTR_NAME = dSTRBTR_NAME;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public Number getID_DRUG_AUDIT() {
			return ID_DRUG_AUDIT;
		}
		public void setID_DRUG_AUDIT(Number iD_DRUG_AUDIT) {
			ID_DRUG_AUDIT = iD_DRUG_AUDIT;
		}
			
}
