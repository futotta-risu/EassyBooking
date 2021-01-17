package org.SlavaLenin.EassyBooking.app.data.dto;

import java.util.Date;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

public class AirlineFlightDTO {

	private int fligthNumber;
	private Date dateDeparture;
	private Date dateArrival;
	private int airlineNumber;
	private String airportDeparture;
	private String airportArrival;
	AirlineEnum airline;
	
	public AirlineFlightDTO(int fligthNumber, Date dateDeparture, Date dateArrival, int airlineNumber,
			String airportDeparture, String airportArrival, AirlineEnum airline) {
		super();
		this.fligthNumber = fligthNumber;
		this.dateDeparture = dateDeparture;
		this.dateArrival = dateArrival;
		this.airlineNumber = airlineNumber;
		this.airportDeparture = airportDeparture;
		this.airportArrival = airportArrival;
		this.airline = airline;
	}
	
	
	
	public int getFligthNumber() {
		return fligthNumber;
	}
	public void setFligthNumber(int fligthNumber) {
		this.fligthNumber = fligthNumber;
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
	public int getAirlineNumber() {
		return airlineNumber;
	}
	public void setAirlineNumber(int airlineNumber) {
		this.airlineNumber = airlineNumber;
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
	public AirlineEnum getAirline() {
		return airline;
	}
	public void setAirline(AirlineEnum airline) {
		this.airline = airline;
	}
	
}
