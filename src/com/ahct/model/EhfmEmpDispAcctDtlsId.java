
package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmEmpDispAcctDtlsId implements Serializable{
	
	@Column(name="USER_ID", nullable=false, length=12)
	private String userID;
	
	@Column(name="ACCOUNT_NO", nullable=false, length=20)
	private String accountNo;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmEmpDispAcctDtlsId other = (EhfmEmpDispAcctDtlsId) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	
	public EhfmEmpDispAcctDtlsId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EhfmEmpDispAcctDtlsId(String userID, String accountNo) {
		super();
		this.userID = userID;
		this.accountNo = accountNo;
	}
	
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

}
