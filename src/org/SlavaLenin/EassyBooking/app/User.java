package org.SlavaLenin.EassyBooking.app;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class User {

	private String OAuth;
	private String username;
	private String name;
	private String email;
	private int loginSystemType;
	
	public String getOAuth() {
		return OAuth;
	}
	public void setOAuth(String oAuth) {
		OAuth = oAuth;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getLoginSystemType() {
		return loginSystemType;
	}
	public void setLoginSystemType(int loginSystemType) {
		this.loginSystemType = loginSystemType;
	}
	
	


}