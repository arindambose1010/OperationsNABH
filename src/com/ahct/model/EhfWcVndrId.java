package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfWcVndrId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name="ITEM_ID", nullable=false)
	private String  itemId;
	@Column(name="ACT_ORDER", nullable=false)
	private Long actOrder;
	@Column(name="INDENT_ID", nullable=false)
	private String indentId;
	
	public EhfWcVndrId()
	{
		
	}
	
    public EhfWcVndrId(String itemId,Long actOrder,String indentId)
    {
    	this.itemId=itemId;
    	this.indentId = indentId;
    	this.actOrder=actOrder;
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


	@Column(name="ACT_ORDER", nullable=false)
	public Long getActOrder() {
		return actOrder;
	}

	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfWcVndrId)) {
			return false;
		}
		EhfWcVndrId castOther = (EhfWcVndrId)other;
		return 
			this.actOrder.equals(castOther.actOrder)
			&& this.itemId.equals(castOther.itemId)
			&& this.indentId.equals(castOther.indentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.actOrder.hashCode();
		hash = hash * prime + this.itemId.hashCode();
		hash = hash * prime + indentId.hashCode();
		
		return hash;
	}

}
