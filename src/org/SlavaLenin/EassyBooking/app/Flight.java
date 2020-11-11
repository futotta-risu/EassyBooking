package org.SlavaLenin.EassyBooking.app;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Flight {
	@PrimaryKey
	private int flightNumber;
	private Date dateDeparture;
	private Date dateArrival;
	private int totalSeats;
	private int numberRemainingSeats;
	private int numberPassengers;
	private List<FlightReservation> flightReservations;
	
	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}
	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}
	private PassengerInfo[] infoPassengers;
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	public Date getDateDeparture() {
		return dateDeparture;
	}
	public void setDateDeparture(Date dateDeparture) {
		this.dateDeparture = dateDeparture;
	}
	public Date getDateArrival() {
		return dateArrival;
	}
	public void setDateArrival(Date dateArrival) {
		this.dateArrival = dateArrival;
	}
	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getNumberRemainingSeats() {
		return numberRemainingSeats;
	}
	public void setNumberRemainingSeats(int numberRemainingSeats) {
		this.numberRemainingSeats = numberRemainingSeats;
	}
	public int getNumberPassengers() {
		return numberPassengers;
	}
	public void setNumberPassengers(int numberPassengers) {
		this.numberPassengers = numberPassengers;
	}
	public PassengerInfo[] getInfoPassengers() {
		return infoPassengers;
	}
	public void setInfoPassengers(PassengerInfo[] infoPassengers) {
		this.infoPassengers = infoPassengers;
	}

	
}