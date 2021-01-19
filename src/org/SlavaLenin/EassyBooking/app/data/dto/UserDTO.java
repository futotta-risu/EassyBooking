package org.SlavaLenin.EassyBooking.app.data.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String sessionKey;
	
	public UserDTO(String username, String sessionKey) {
		super();
		this.username = username;
		this.sessionKey = sessionKey;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getSessionKey() {
		return this.sessionKey;
	}


}
