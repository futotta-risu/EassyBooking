package org.SlavaLenin.EassyBooking.app.data.dto;

import java.util.ArrayList;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;




public class FlightAssembler {

	
	private static FlightAssembler instance;
	public FlightAssembler() {
		super();
		
	}
	
	public static List<FlightDTO> assemble(List<Flight> flights) {
		List<FlightDTO> flightDTOList = new ArrayList<FlightDTO>();
		
		for (Flight flight : flights) {
			 flightDTOList.add(new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats()));
		}
		
		return flightDTOList;
	}
	
	public static FlightDTO assemble(Flight flight) {
	
		FlightDTO result = new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats());
		return result;
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
