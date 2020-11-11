package org.SlavaLenin.EassyBooking.app;

import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Pago {

	private Date date;
	@PrimaryKey
	private int paymentID;
	private String confirmationCode;
	private String extraInfo;
	private FlightReservation flightReservations;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public FlightReservation getFlightReservations() {
		return flightReservations;
	}
	public void setFlightReservations(FlightReservation flightReservations) {
		this.flightReservations = flightReservations;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	

}