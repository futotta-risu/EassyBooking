package org.SlavaLenin.EassyBooking.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;


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
	
	public List<Flight> buscarVuelo(String id) throws AirlineTypeNotFoundException{
		Logger.getLogger(ServerManagerFrame.class.getName()).info("AirlineService: buscarVuelo con " + id);
		
		List<AirlineGateway> gateways = new ArrayList<AirlineGateway>();
		
		for (AirlineEnum airline : AirlineEnum.values()) {
			gateways.add(AirlineGatewayFactory.create(airline));
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
	
	
	public void reservar(String id, AirlineEnum airline) throws AirlineTypeNotFoundException{
		AirlineGateway gateway = AirlineGatewayFactory.create(airline);
		gateway.reservar(id);
	}
	

}
