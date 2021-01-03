package org.SlavaLenin.EassyBooking.app.data.dto;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;




public class FlightAssembler {

	
	private static FlightAssembler instance;
	public FlightAssembler() {
		super();
		
	}

	public FlightDTO assenble (Flight flight) {
		FlightDTO flightDTO= new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats());
		return flightDTO;
	}
	public static FlightAssembler getInstance() {
		if (instance == null) {
			try {
				instance = new FlightAssembler();
			} catch (Exception e) {
				System.err.println("# Error crando la RemoteFacade: " + e);
			}
		}
		return instance;
	}
	
}
