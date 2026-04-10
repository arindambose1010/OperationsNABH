package com.ahct.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfmDispUsrMpgId implements Serializable {
	
	private String  dispId;
	private String userId;
	private String grpId;
	private String activeyn;
	
	@Column(name="DISP_ID", nullable=false)
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	@Column(name="USER_ID", nullable=false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="GRP_ID", nullable=false)
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	@Column(name="ACTIVE_YN", nullable=false)
	public String getActiveyn() {
		return activeyn;
	}
	public void setActiveyn(String activeyn) {
		this.activeyn = activeyn;
	}

	
	public EhfmDispUsrMpgId(String dispId, String userId,String grpId,String activeyn) {
		super();
		this.dispId = dispId;
		this.userId = userId;
		this.grpId = grpId;
		this.activeyn = activeyn;
	}
	public EhfmDispUsrMpgId() {
		super();
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dispId == null) ? 0 : dispId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((grpId == null) ? 0 : grpId.hashCode());
		result = prime * result + ((activeyn == null) ? 0 : activeyn.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfmDispUsrMpgId other = (EhfmDispUsrMpgId) obj;
		if (dispId == null) {
			if (other.dispId != null)
				return false;
		} else if (!dispId.equals(other.dispId))
			return false;
		
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		
		if (grpId == null) {
			if (other.grpId != null)
				return false;
		} else if (!grpId.equals(other.grpId))
			return false;
		
		if (activeyn == null) {
			if (other.activeyn != null)
				return false;
		} else if (!activeyn.equals(other.activeyn))
			return false;
		
		return true;
	}

	
	
}
