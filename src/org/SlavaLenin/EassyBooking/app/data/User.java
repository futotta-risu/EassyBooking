package org.SlavaLenin.EassyBooking.app.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.*;


@PersistenceCapable(detachable = "true")
public class User implements Serializable {
	
	@Unique
	private String username;
	private String name;
	private String email;
	private int loginSystemType;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "user", dependentElement = "true")
	@Join
	private List<FlightReservation> flightReservations;
	
	@Persistent
	private PaymentMethod paymentMethod;
	
	@Persistent(defaultFetchGroup = "true", mappedBy = "user", dependentElement = "true")
	@Join
	private List<Pago> pagos;
	
	public User() {
		this.flightReservations = new ArrayList<>();
		this.pagos = new ArrayList<>();
	}
	
	public User(String email) {
		this.email = email;
		this.username = email;
		this.flightReservations = new ArrayList<>();
		this.pagos = new ArrayList<>();
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
	
	@Override
	public String toString() {
		return "User [username=" + username + "]";
	}
	
	


}