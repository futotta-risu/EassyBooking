package org.SlavaLenin.EassyBooking.app.data;
import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Flight implements Serializable {

	private static final long serialVersionUID = 89562850644839269L;
	@Unique
	private int flightNumber;
	@NotPersistent
	private String airlineCode;

	@NotPersistent
	private Date dateDeparture;
	@NotPersistent
	private Date dateArrival;
	@NotPersistent
	private int totalSeats;
	@NotPersistent
	private int numberRemainingSeats;
	
	/*
	@Join
	@Persistent(mappedBy="flight", dependentElement="true")
	private List<FlightReservation> flightReservations;*/
	
	@NotPersistent
	private int numberPassengers;
	
	/**
	 * ICAO of the Departure Airport.
	 * 
	 * We can get the Airport object through the DBManager and the getAiport(String code) method
	 */
	@NotPersistent
	private String airportDeparture;
	
	/**
	 * ICAO of the Arrival Airport.
	 * 
	 * We can get the Airport object through the DBManager and the getAiport(String code) method
	 */
	@NotPersistent
	private String airportArrival;
	
	
	public Flight() {
	}
	
	public String getAirlineCode() {
		return this.airlineCode;
	}
	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
	}
	
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
	@Override
	public String toString() {
		return "Flight [id=" + flightNumber + ", date=" + dateDeparture
				+ "]";
	}

	public String getAirportDeparture() {
		return airportDeparture;
	}

	public void setAirportDeparture(String airportDeparture) {
		this.airportDeparture = airportDeparture;
	}

	public String getAirportArrival() {
		return airportArrival;
	}

	public void setAirportArrival(String airportArrival) {
		this.airportArrival = airportArrival;
	}


	
}