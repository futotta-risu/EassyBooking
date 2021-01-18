package org.SlavaLenin.EassyBooking.app.data;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

public class Airline {
	private String name;
	AirlineEnum enumVal;
	
	public Airline(String name, AirlineEnum enumVal) {
		this.name = name;
		this.enumVal = enumVal;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public AirlineEnum getEnumVal() {
		return enumVal;
	}

	public void setEnumVal(AirlineEnum enumVal) {
		this.enumVal = enumVal;
	}
	

	

}