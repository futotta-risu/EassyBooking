package org.SlavaLenin.EassyBooking.app.data.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {
	private String username;
	private String key;
	
	public UserDTO() {
		super();
	}
	
	public UserDTO(String username, String password) {
		super();
		this.username = username;
		this.key = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return key;
	}
	public void setPassword(String password) {
		this.key = password;
	}


}
