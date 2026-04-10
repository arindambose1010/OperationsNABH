package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfmPnlDocAcctDtlsId implements Serializable{
	
	private Long userId;
	private String accountNo;
	
	public EhfmPnlDocAcctDtlsId(Long userId, String accountNo) {
		this.userId=userId;
		this.accountNo=accountNo;
	}
	public EhfmPnlDocAcctDtlsId() {
		// TODO Auto-generated constructor stub
	}
	@Column(name = "USER_ID", nullable = false)
		public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(name = "ACCOUNT_NO", nullable = false)
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	
	public boolean equals(Object other) {
	    if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfmPnlDocAcctDtlsId) ) return false;
		 EhfmPnlDocAcctDtlsId castOther = ( EhfmPnlDocAcctDtlsId ) other; 
	    
		 return ( (this.getUserId()==castOther.getUserId()) || ( this.getUserId()!=null && castOther.getUserId()!=null && this.getUserId().equals(castOther.getUserId()) ) )
	&& ( (this.getAccountNo()==castOther.getAccountNo()) || ( this.getAccountNo()!=null && castOther.getAccountNo()!=null && this.getAccountNo().equals(castOther.getAccountNo()) ) );
	}

	public int hashCode() {
	    int result = 17;
	    
	    result = 37 * result + ( getUserId() == null ? 0 : this.getUserId().hashCode() );
	    result = 37 * result + ( getAccountNo() == null ? 0 : this.getAccountNo().hashCode() );
	    return result;
	}   


}
