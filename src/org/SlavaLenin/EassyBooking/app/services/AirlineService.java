package org.SlavaLenin.EassyBooking.app.services;

import es.deusto.ingenieria.sd.auctions.server.services.EntityDataService;

public class AirlineService {
	
	private static AirlineService instance;
	
	private AirlineService() {
	}
	
	public static AirlineService getInstance() {
		if (instance == null) {
			instance = new AirlineService();
		}

		return instance;
	}
	
	public List<FlightDTO> buscar(){
		return FlightAssembler.getInstance().entityToDTO(DBManager.getInstance().getFlights());
	}
	
	public boolean reservar(String id) {
		
		
	}
	

}
