package org.SlavaLenin.EassyBooking.app.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;


public class AirlineService {
	
	private static AirlineService instance;
	
	private AirlineService() {}
	
	public static AirlineService getInstance() {
		if (instance == null) 
			instance = new AirlineService();
		
		return instance;
	}
	
	public List<Flight> buscarVuelo(String id) {
		Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		logger.info("BuscarVuelo con " + id);
		
		List<Flight> searchFlights = new ArrayList<Flight>();
		
		for (AirlineEnum airline : AirlineEnum.values()) 
			searchFlights.addAll(AirlineGatewayFactory.create(airline).buscar(id));
		
		logger.info("Se han encontrado " + searchFlights.size() + " vuelos");
		return searchFlights;
	}
	
	
	public void reservar(String id, AirlineEnum airline, User user){
		Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		logger.info("Reservando vuelo con id " + id + " en la aerolinea " + airline.getCode());
		
		try {
			AirlineGatewayFactory.create(airline).reservar(id);
			PaymentEnum paymentType = u.
		} catch (RemoteException e) {
			logger.severe("Error al intentar hacer la reserva con id  " + id + " en la aerolinea " + airline.getCode());
		}
		
	}
	

}
