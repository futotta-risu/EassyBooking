package org.SlavaLenin.EassyBooking.app.data.dto;


import java.io.Serializable;
import java.util.Date;

/**
 * <strong>Pattern</strong>
 * <ul>
 *      <li>DTO</li>
 * </ul>
 */
public class FlightDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int fligthNumber;
	private int seats, price;
	
	private Date dateDeparture,dateArrival;
	private String airline, airportDeparture,  airportArrival;
	
	
	public FlightDTO() {
	    super();
	}
	
	public FlightDTO(int fligthNumber, Date dateDeparture, Date dateArrival, String airlineNumber,
	        String airportDeparture, String airportArrival, int seats, int price) {
	    super();
	    this.fligthNumber = fligthNumber;
	    this.dateDeparture = dateDeparture;
	    this.dateArrival = dateArrival;
	    this.airline = airlineNumber;
	    this.airportDeparture = airportDeparture;
	    this.airportArrival = airportArrival;
	    this.seats = seats;
	    this.price = price;
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
	
	public String getAirlineNumber() {
	    return airline;
	}
	
	public void setAirlineNumber(String airlineNumber) {
	    this.airline = airlineNumber;
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

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}


	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flight " + fligthNumber + ": " + airportDeparture + " "+ dateDeparture ;
	}
}
