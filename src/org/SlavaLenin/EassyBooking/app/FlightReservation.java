package org.SlavaLenin.EassyBooking.app;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class FlightReservation {
	
	private int flightReservationID;
	private int price;
	private int numberOfSeats;
	private List<PassengerInfo> passengersInfo;
	
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

	
	
}