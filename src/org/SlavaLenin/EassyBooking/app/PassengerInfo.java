package org.SlavaLenin.EassyBooking.app;

import javax.jdo.annotations.*;

@PersistenceCapable
public class PassengerInfo {
	private String passengerName;
	private String passengerLastName;
	private String passengerDNI;
	private long passengerPhoneNum;
	
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPassengerLastName() {
		return passengerLastName;
	}
	public void setPassengerLastName(String passengerLastName) {
		this.passengerLastName = passengerLastName;
	}
	public String getPassengerDNI() {
		return passengerDNI;
	}
	public void setPassengerDNI(String passengerDNI) {
		this.passengerDNI = passengerDNI;
	}
	public long getPassengerPhoneNum() {
		return passengerPhoneNum;
	}
	public void setPassengerPhoneNum(long passengerPhoneNum) {
		this.passengerPhoneNum = passengerPhoneNum;
	}
	
	

}
