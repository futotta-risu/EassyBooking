package org.SlavaLenin.EassyBooking.app.data;
import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class FlightReservation implements Serializable {

	private int flightReservationID;
	private int price;
	private int numberOfSeats;
	
	@NotPersistent
	private List<PassengerInfo> passengersInfo;
	
	
	private User user;
	
	@Join
	private Flight flight;
	
	@Join
	private Pago pago;
	
	
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
	public List<PassengerInfo> getPassengersInfo() {
		return passengersInfo;
	}
	public void setPassengersInfo(List<PassengerInfo> passengersInfo) {
		this.passengersInfo = passengersInfo;
	}

	public void removeFlight() {
		this.flight=null;
		this.user=null;
		this.pago = null;
	}
	@Override
	public String toString() {
		return "FlightReservation [flightReservationID=" + flightReservationID + "]";
	}
	
	
}