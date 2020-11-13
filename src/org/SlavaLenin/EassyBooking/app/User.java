package org.SlavaLenin.EassyBooking.app;

import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class User {
	//@PrimaryKey
	private String OAuth;
	private String username;
	private String name;
	private String email;
	private int loginSystemType;
	private List<FlightReservation> flightReservations;
	
	private PaymentMethod paymentMethod;
	
	//@Join
	//@Persistent(mappedBy = "user", dependentElement = "true")
	private List<Pago> pagos;
	
	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}
	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}
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
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	


}