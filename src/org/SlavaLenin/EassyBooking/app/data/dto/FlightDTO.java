package org.SlavaLenin.EassyBooking.app.data.dto;


import java.io.Serializable;
import java.util.Date;

public class FlightDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int fligthNumber;
	private Date dateDeparture;
	private Date dateArrival;
	private int airlineNumber;
	private int airportDeparture;
	private int airportArrival;
	
	
	public FlightDTO() {
	    super();
	}
	
	public FlightDTO(int fligthNumber, Date dateDeparture, Date dateArrival, int airlineNumber,
	        int airportDeparture, int airportArrival) {
	    super();
	    this.fligthNumber = fligthNumber;
	    this.dateDeparture = dateDeparture;
	    this.dateArrival = dateArrival;
	    this.airlineNumber = airlineNumber;
	    this.airportDeparture = airportDeparture;
	    this.airportArrival = airportArrival;
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
	
	
	public int getAirportDeparture() {
	    return airportDeparture;
	}
	public void setAirportDeparture(int airportDeparture) {
	    this.airportDeparture = airportDeparture;
	}
	
	
	public int getAirportArrival() {
	    return airportArrival;
	}
	public void setAirportArrival(int airportArrival) {
	    this.airportArrival = airportArrival;
	}


}
