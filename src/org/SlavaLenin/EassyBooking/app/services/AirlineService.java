package org.SlavaLenin.EassyBooking.app.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.PaymentMethod;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.PaymentGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

/**
 * Application Service for Airlines
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Aplication Service</li>
 *	</ul>
 *
 */
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
	
	/**
	 * Flight booking function.
	 * <ol>
	 * 	<li> Connects to the Airline to send a booking request</li>
	 * 	<li> Connects to the Payment System to pay for the flight</li>
	 * 		<ul> 
	 * 			<li>If the process fails a abort message is sent to the Airline Server.</li>
	 * 		</ul>
	 * </ol>
	 * @param flightID General ID of the flight
	 * @param username Username of the petitioner user
	 * @param sessionKey SessionKey of the client to verify his identity
	 */
	public void reservar(String flightID, String username, String sessionKey){
		Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		logger.info("Reservando vuelo con id " + flightID );
		
		Flight flight = DBManager.getInstance().getFlight(flightID);
		
		User user = DBManager.getInstance().getUserWithKey(username, sessionKey);
		if(user == null) {
			logger.warning("Alguien ha intentado acceder a la cuenta de " + username + " con la clave.");
			return;
		}
		
		try {
			// TODO Cambiar esto de String a Integer
			AirlineGatewayFactory.create(flight.getAirline()).reservar(String.valueOf(flight.getFlightNumber()));
			PaymentMethod paymentType = user.getPaymentMethod();
			PaymentGatewayFactory.getInstance().create(user.getPaymentMethod().getPaymentType()).pay(username, flight.getPrice());
			
		} catch (RemoteException e) {
			logger.severe("Error al intentar hacer la reserva con id  " + flightID + " en la aerolinea " + flight.getAirline().getCode());
		}
		
	}
	

}
