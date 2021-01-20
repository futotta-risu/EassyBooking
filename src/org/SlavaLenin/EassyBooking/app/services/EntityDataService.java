package org.SlavaLenin.EassyBooking.app.services;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.dto.FlightAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;

/**
 * Application Service for Entity Data Services
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Application Service</li>
 * </ul>
 */
public class EntityDataService {

	private static EntityDataService instance;
	
	private EntityDataService() {}
	
	public static EntityDataService getInstance() {
		if (instance == null) 
			instance = new EntityDataService();
		
		return instance;
	}
	
	
	public FlightDTO getFlight(String id) {
		
		return FlightAssembler.getInstance().assemble(DBManager.getInstance().getFlight(id));
	}
	
	public List<FlightDTO> getFlights(String id) {
		
		return FlightAssembler.getInstance().assemble(DBManager.getInstance().getFlights());
	}
	
	public UserDTO getUser(String username) {
		
		return UserAssembler.getInstance().assemble(DBManager.getInstance().getUser(username));

	}
	
}
