package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

public class EhfDispDrgCreditNoteId implements Serializable{
	private String creditRqstId;
	private String purchaseInvoiceNum;
	
	public EhfDispDrgCreditNoteId()
	{
		
	}
	
    public EhfDispDrgCreditNoteId(String creditRqstId,String purchaseInvoiceNum)
    {
    	this.creditRqstId=creditRqstId;
    	this.purchaseInvoiceNum=purchaseInvoiceNum;
    }
	
	@Column(name="CREDIT_NOTE_ID")
	public String getCreditRqstId() {
		return creditRqstId;
	}
	public void setCreditRqstId(String creditRqstId) {
		this.creditRqstId = creditRqstId;
	}
	@Column(name="PURCHASE_INVOICE_NO")
	public String getPurchaseInvoiceNum() {
		return purchaseInvoiceNum;
	}
	public void setPurchaseInvoiceNum(String purchaseInvoiceNum) {
		this.purchaseInvoiceNum = purchaseInvoiceNum;
	}
	
	

}
