package info.noip.piupiu.model.mongo;

import java.io.Serializable;

public class Liker implements Serializable{

	private static final long serialVersionUID = 2840833134326874166L;

	private String userEmail;
	private String hash;
	
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
	
}
