package org.SlavaLenin.EassyBooking.app.services;

import java.util.ArrayList;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.db.DBHandler;


public class AirlineService {
	
	private static AirlineService instance;
	private static AirlineGatewayFactory factory;
	private AirlineEnum<String> airlines;
	
	
	private AirlineService() {
	}
	
	public static AirlineService getInstance() {
		if (instance == null) {
			instance = new AirlineService();
		}

		return instance;
	}
	
	public List<FlightDTO> buscarVuelo(String id){
		
		List<AirlineGateways> gateways = new ArrayList<AirlineGateway>();
		for (AirlineEnum<String> airline : airlines) {
			gateways.add(factory.create(airline));
		}
		
		List<FlightDTO> result = new ArrayList<FlightDTO>();
		for (AirlineGateways gateway : gateways) {
			List<FlightDTO> temp = gateways.buscar(id);
			for (FlightDTO flight : temp) {
				result.add(flight);
			}
			
		}
		return result;
	}
	
	public boolean reservar(String id, AirlineEnum airline) {
		AirlineGateway gateway = factory.create(airline);
		gateway.reservar(id);
	}
	

}
