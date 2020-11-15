package org.SlavaLenin.EassyBooking.app;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable
public class Flight {
	//@PrimaryKey
	private int flightNumber;
	private Date dateDeparture;
	private Date dateArrival;
	private int totalSeats;
	private int numberRemainingSeats;
	private int numberPassengers;
	@NotPersistent
	private Airline aerolinea;
	@NotPersistent
	private Airport aeropuerto;
	
	public Airline getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Airline aerolinea) {
		this.aerolinea = aerolinea;
	}
	public Airport getAeropuerto() {
		return aeropuerto;
	}
	public void setAeropuerto(Airport aeropuerto) {
		this.aeropuerto = aeropuerto;
	}
	//private List<FlightReservation> flightReservations;
	/*
	public List<FlightReservation> getFlightReservations() {
		return flightReservations;
	}
	public void setFlightReservations(List<FlightReservation> flightReservations) {
		this.flightReservations = flightReservations;
	}
	*/
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


	
}