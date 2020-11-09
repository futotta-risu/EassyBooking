package org.SlavaLenin.EassyBooking.app;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class User {

	private String OAuth;
	private String username;
	private String name;
	private String email;
	private int loginSystemType;


}