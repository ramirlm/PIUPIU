package info.noip.piupiu.model.mongo;

import info.noip.piupiu.infra.MD5Util;

import java.io.Serializable;

public class Avatar implements Serializable {

	private static final long serialVersionUID = 2840833134326874166L;

	private String userEmail;
	private String hash;

	public Avatar() {
	}
	
	public Avatar(String userEmail) {
		this.userEmail = userEmail;
		this.hash = MD5Util.md5Hex(userEmail);
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userEmail == null) ? 0 : userEmail.hashCode());
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
		Avatar other = (Avatar) obj;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}

}
