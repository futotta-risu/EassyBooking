package org.SlavaLenin.EassyBooking.app;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class FlightReservation {
	//@PrimaryKey
	private int flightReservationID;
	private int price;
	private int numberOfSeats;
	
	//@Join
	//@Persistent(mappedBy = "flightReservations", dependentElement="true")
	private List<String> passengersInfo;
	//@Join
	private User user;
	//@Join
	private Pago pago;
	//@Join
	private Flight flight;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getFlightReservationID() {
		return flightReservationID;
	}
	public void setFlightReservationID(int flightReservationID) {
		this.flightReservationID = flightReservationID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNumberOfSeats() {
		return numberOfSeats;
	}
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}
	public List<String> getPassengersInfo() {
		return passengersInfo;
	}
	public void setPassengersInfo(List<String> passengersInfo) {
		this.passengersInfo = passengersInfo;
	}

	
	
}