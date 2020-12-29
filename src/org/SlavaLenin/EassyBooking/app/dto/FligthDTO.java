package org.SlavaLenin.EassyBooking.app.dto;

import java.io.Serializable;

public class FligthDTO implements Serializable {
private String fligthNumber;
private String dateDeparture;
private String dateArrival;
private String airlineNumber;
private String airportDeparture;
private String airportArrival;




public FligthDTO() {
	super();
	// TODO Auto-generated constructor stub
}




public FligthDTO(String fligthNumber, String dateDeparture, String dateArrival, String airlineNumber,
		String airportDeparture, String airportArrival) {
	super();
	this.fligthNumber = fligthNumber;
	this.dateDeparture = dateDeparture;
	this.dateArrival = dateArrival;
	this.airlineNumber = airlineNumber;
	this.airportDeparture = airportDeparture;
	this.airportArrival = airportArrival;
}




public String getFligthNumber() {
	return fligthNumber;
}




public void setFligthNumber(String fligthNumber) {
	this.fligthNumber = fligthNumber;
}




public String getDateDeparture() {
	return dateDeparture;
}




public void setDateDeparture(String dateDeparture) {
	this.dateDeparture = dateDeparture;
}




public String getDateArrival() {
	return dateArrival;
}




public void setDateArrival(String dateArrival) {
	this.dateArrival = dateArrival;
}




public String getAirlineNumber() {
	return airlineNumber;
}




public void setAirlineNumber(String airlineNumber) {
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


}
