package org.SlavaLenin.EassyBooking.app.services;

import java.util.ArrayList;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.db.DBHandler;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;


public class AirlineService {
	
	private static AirlineService instance;
	private static AirlineGatewayFactory factory;
	
	
	private AirlineService() {
	}
	
	public static AirlineService getInstance() {
		if (instance == null) {
			instance = new AirlineService();
		}

		return instance;
	}
	
	public List<Flight> buscarVuelo(String id) throws LoginTypeNotFoundException{
		
		List<AirlineGateway> gateways = new ArrayList<AirlineGateway>();
		
		for (AirlineEnum airline : AirlineEnum.values()) {
			gateways.add(factory.create(airline));
		}
		
		List<Flight> result = new ArrayList<Flight>();
		
		for (AirlineGateway gateway : gateways) {
			List<Flight> temp = gateway.buscar(id);
			for (Flight flight : temp) {
				result.add(flight);
			}	
		}
		return result;
	}
	
	
	public boolean reservar(String id, AirlineEnum airline) throws LoginTypeNotFoundException{
		AirlineGateway gateway = factory.create(airline);
		gateway.reservar(id);
		return true;
	}
	

}
