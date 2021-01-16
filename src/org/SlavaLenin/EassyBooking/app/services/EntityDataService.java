package org.SlavaLenin.EassyBooking.app.services;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;

public class EntityDataService {

	public EntityDataService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FlightDTO getFlight(String id) {
		
		
		return FlightAssembler.assenble(AirlineService.getInstance().buscarVuelo(id));
		
	}
	
	public UserDTO getUser(String username) {
		
		return LoginService.getInstance().getUser(username);

	}
	
}
