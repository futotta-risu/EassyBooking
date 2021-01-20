package org.SlavaLenin.EassyBooking.app.data.dto;

import java.util.ArrayList;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;

/**
 * <strong>Pattern</strong>
 * <ul>
 *      <li>Assembler</li>
 * </ul>
 */
public class FlightAssembler {

	
	private static FlightAssembler instance;
	
	private FlightAssembler() {}
	
	public static FlightAssembler getInstance(){
		if (instance == null) 
			instance = new FlightAssembler();
		
		return instance;
	}
	
	public List<FlightDTO> assemble(List<Flight> flights) {
		List<FlightDTO> flightDTOList = new ArrayList<FlightDTO>();
		
		for (Flight flight : flights) {
			 flightDTOList.add(new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats()));
		}
		
		return flightDTOList;
	}
	
	public FlightDTO assemble(Flight flight) {
	
		FlightDTO result = new FlightDTO( flight.getFlightNumber(),flight.getDateArrival(),flight.getDateDeparture(),flight.getNumberPassengers(),flight.getNumberRemainingSeats(),flight.getTotalSeats());
		return result;
	}
	
}
	

