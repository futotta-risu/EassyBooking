package org.SlavaLenin.EassyBooking.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;

import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;


@PersistenceCapable(detachable = "true")
public class User implements Serializable {
	
	@Unique
	private String username;
	private String name;
	private String email;
	
	private String sessionKey;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "user", dependentElement = "true")
	@Join
	private List<FlightReservation> flightReservations;
	
	@Persistent
	private PaymentMethod paymentMethod;
	@Persistent
	private LoginEnum loginSystemType;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "user", dependentElement = "true")
	@Join
	private List<Pago> pagos;
	
	public User() {
		this.flightReservations = new ArrayList<>();
		this.pagos = new ArrayList<>();
		this.sessionKey = "123456789";
	}
	
	public User(String email) {
		this.email = email;
		this.username = email;
		this.flightReservations = new ArrayList<>();
		this.pagos = new ArrayList<>();
		
		this.sessionKey = "123456789";
	}
	
	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}
	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}
	public void addFlightReservation(FlightReservation flightReservation) {
		this.flightReservations.add(flightReservation);
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
	public LoginEnum getLoginSystemType() {
		return loginSystemType;
	}
	public void setLoginSystemType(LoginEnum loginSystemType) {
		this.loginSystemType = loginSystemType;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public List<Pago> getPagos() {
		return pagos;
	}
	public void setPagos(List<Pago> pagos) {
		this.pagos = pagos;
	}
	public void addPago(Pago pago) {
		this.pagos.add(pago);
	}
	
	public void removeFlightReservation(FlightReservation flightReservation) {
		this.flightReservations.remove(flightReservation);
	}
	
	public String getSessionKey() {
		return this.sessionKey;
	}
	
	public boolean checkSessionKey(String sessionKey) {
		return sessionKey.equals(this.sessionKey);
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}
	
	


}