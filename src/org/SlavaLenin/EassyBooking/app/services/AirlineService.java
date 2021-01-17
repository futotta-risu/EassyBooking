package org.SlavaLenin.EassyBooking.app.services;

import java.util.ArrayList;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.AirlineFlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.db.DBHandler;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;


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
		
		List<AirlineGateway> gateways = new ArrayList<AirlineGateway>();
		
		for (AirlineEnum airline : AirlineEnum.values()) {
			gateways.add(AirlineGatewayFactory.create(airline));
		}
		
		List<Flight> result = new ArrayList<Flight>();
		
		for (AirlineGateway gateway : gateways) {
			List<AirlineFlightDTO> temp = gateway.buscar(id);
			for (AirlineFlightDTO flight : temp) {
				result.add(new Flight(flight));
			}	
		}
		return result;
	}
	
	
	public void reservar(String id, AirlineEnum airline) throws AirlineTypeNotFoundException{
		AirlineGateway gateway = AirlineGatewayFactory.create(airline);
		gateway.reservar(id);
	}
	

}
