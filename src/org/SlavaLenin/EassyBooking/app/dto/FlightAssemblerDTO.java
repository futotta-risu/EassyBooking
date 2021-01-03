package org.SlavaLenin.EassyBooking.app.dto;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;




public class FlightAssemblerDTO {

	
	private static FlightAssemblerDTO instance;
	public FlightAssemblerDTO() {
		super();
		
	}

	public FlightDTO assenble (Flight flight) {
		FlightDTO flightDTO= new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats());
		return flightDTO;
	}
	public static FlightAssemblerDTO getInstance() {
		if (instance == null) {
			try {
				instance = new FlightAssemblerDTO();
			} catch (Exception e) {
				System.err.println("# Error crando la RemoteFacade: " + e);
			}
		}
		return instance;
	}
	
}
