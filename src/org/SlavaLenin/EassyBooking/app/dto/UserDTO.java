package org.SlavaLenin.EassyBooking.app.dto;

public class UserDTO {
private String userName;
private String name;
public UserDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public UserDTO(String userName, String name) {
	super();
	this.userName = userName;
	this.name = name;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}


}
