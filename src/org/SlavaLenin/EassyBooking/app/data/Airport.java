package org.SlavaLenin.EassyBooking.app.data;

public class Airport {
	private String name;
	private String location;
	private String airportCode;
	private int gates;
	
	public Airport(String name, String airportCode) {
		this.name = name;
		this.airportCode = airportCode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public int getGates() {
		return gates;
	}
	public void setGates(int gates) {
		this.gates = gates;
	}
	
	

}