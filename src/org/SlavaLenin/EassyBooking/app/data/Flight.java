package org.SlavaLenin.EassyBooking.app.data;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.*;

import org.SlavaLenin.EassyBooking.app.data.dto.AirlineFlightDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;

@PersistenceCapable(detachable = "true")
public class Flight implements Serializable {

	@Unique
	private int flightNumber;
	@NotPersistent
	private Airline aerolinea;

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
	@NotPersistent
	private Airport airportDeparture;
	@NotPersistent
	private Airport airportArrival;
	
	
	public Flight(AirlineFlightDTO afdto) {
		this.aerolinea = DBManager.getInstance().getAirlinetByEnum(afdto.getAirline());
		this.airportDeparture = DBManager.getInstance().getAirportByCode(afdto.getAirportDeparture());
		this.airportArrival = DBManager.getInstance().getAirportByCode(afdto.getAirportDeparture());
		this.dateArrival = afdto.getDateArrival();
		this.dateDeparture = afdto.getDateDeparture();
		this.flightNumber = afdto.getFligthNumber();
	}
	
	public Airline getAerolinea() {
		return aerolinea;
	}
	public void setAerolinea(Airline aerolinea) {
		this.aerolinea = aerolinea;
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
		return "Flight [flightNumber=" + flightNumber + "]";
	}

	public Airport getAirportDeparture() {
		return airportDeparture;
	}

	public void setAirportDeparture(Airport airportDeparture) {
		this.airportDeparture = airportDeparture;
	}

	public Airport getAirportArrival() {
		return airportArrival;
	}

	public void setAirportArrival(Airport airportArrival) {
		this.airportArrival = airportArrival;
	}


	
}