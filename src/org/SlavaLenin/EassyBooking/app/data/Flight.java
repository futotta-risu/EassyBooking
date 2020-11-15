package org.SlavaLenin.EassyBooking.app.data;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Flight {

	private int flightNumber;
	private Airline aerolinea;

	@NotPersistent
	private Date dateDeparture;
	@NotPersistent
	private Date dateArrival;
	@NotPersistent
	private int totalSeats;
	@NotPersistent
	private int numberRemainingSeats;
	private int numberPassengers;
	
	/*
	@Join
	@Persistent(mappedBy="flight", dependentElement="true")
	private List<FlightReservation> flightReservations;*/
	
	@NotPersistent
	private int numberPassengers;
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
	/*
	public void removeFlightReservations() {
		flightReservations.clear();
	}
	public void removeFlightReservations(FlightReservation f) {
		flightReservations.remove(f);
	}*/


	
}