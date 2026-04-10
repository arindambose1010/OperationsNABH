package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfDispTransferAuditId implements Serializable
{

    private String fromCode;
    private String toCode;
    private Long actOrder;
    
    public EhfDispTransferAuditId() 
    {
    }
    
	public EhfDispTransferAuditId(String fromCode, String toCode,
			Long actOrder) {
		
		this.fromCode = fromCode;
		this.toCode = toCode;
		this.actOrder = actOrder;
	}
	@Column(name="FROM_CODE")
	public String getFromCode() {
		return fromCode;
	}
	public void setFromCode(String fromCode) {
		this.fromCode = fromCode;
	}
	@Column(name="TO_CODE")
	public String getToCode() {
		return toCode;
	}
	public void setToCode(String toCode) {
		this.toCode = toCode;
	}
	@Column(name="ACT_ORDER")
	public Long getActOrder() {
		return actOrder;
	}
	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}
    


}
