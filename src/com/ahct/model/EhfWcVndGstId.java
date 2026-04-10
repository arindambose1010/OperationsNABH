package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfWcVndGstId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name="INDENT_ID", nullable=false)
	private String  indentId;
	@Column(name="ITEM_ID", nullable=false)
	private String itemId;
	
	public EhfWcVndGstId()
	{
		
	}
	
    public EhfWcVndGstId(String indentId,String itemId)
    {
    	this.indentId=indentId;
    	this.itemId=itemId;
    }

    
   	public String getIndentId() {
   		return indentId;
   	}

   	public void setIndentId(String indentId) {
   		this.indentId = indentId;
   	}
       

	@Column(name="ITEM_ID", nullable=false)
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfWcVndGstId)) {
			return false;
		}
		EhfWcVndGstId castOther = (EhfWcVndGstId)other;
		return 
			this.itemId.equals(castOther.itemId)
			&& this.indentId.equals(castOther.indentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.itemId.hashCode();
		hash = hash * prime + this.indentId.hashCode();
		
		return hash;
	}

}
