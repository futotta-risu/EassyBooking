package org.SlavaLenin.EassyBooking.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.AirlineGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.PaymentGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;



/**
 * Application Service for Airlines
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Application Service</li>
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
	
	/**
	 * This method creates a gateway of each airline and the adds the result flights to the list.
	 * @param flightID ID for searching flights
	 * @return List<Flight> List with the result of all the flights
	 */
	public List<Flight> buscarVuelo(String flightID) {
		Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		logger.info("BuscarVuelo con " + flightID);
		
		List<Flight> searchFlights = new ArrayList<Flight>();
		
		for (AirlineEnum airline : AirlineEnum.values()) {
			for(Flight flight : AirlineGatewayFactory.create(airline).buscar(flightID)) {
				searchFlights.add(new Flight(flight));
				DBManager.getInstance().storeFlight(flight);
				}
		}
			
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
	 * @throws Exception 
	 */
	public void reservar(String flightID, String username, String sessionKey) throws Exception{
		Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		logger.info("Reservando vuelo con id " + flightID );
		logger.info("\n\n" );
		
		Flight flight = DBManager.getInstance().getFlight(flightID);
		logger.info("Obtenido el vuelo" + flight );
		User user = DBManager.getInstance().getUserWithKey(username, sessionKey);
		if(user == null) {
			logger.warning("Alguien ha intentado acceder a la cuenta de " + username + " con la clave.");
			return;
		}
		
		logger.info("iniciando reserva con vuelo: " + flight.getFlightID() + " : con airline " + flight.getAirline() );
				
		AirlineGateway gateway = AirlineGatewayFactory.create(AirlineEnum.getEnum(flight.getAirline()));
		logger.info("Gateway created: " + gateway);
		
		gateway.reservar(String.valueOf(flight.getFlightNumber()));
		logger.info("Getting Payment Method " + user.getPaymentMethod());
		
<<<<<<< HEAD
	
		logger.info("iniciando pago " + user.getPaymentMethod());
=======
		PaymentEnum paymentType = user.getPaymentMethod().getPaymentType();
		
		logger.info("iniciando pago " + paymentType);
		
>>>>>>> parent of 76e49f5... Merge branch 'main' of https://github.com/futotta-risu/EassyBooking into main
		flight = gateway.buscarVuelo(String.valueOf(flight.getFlightNumber()));
		
		System.out.println("Precio:" + flight.getPrice());
		
		PaymentGatewayFactory.getInstance().create(paymentType).pay(username, flight.getPrice());
		
		logger.info("Processo de pago correcto" );
		
		Pago pago=new Pago();
		
		pago.setDate(Calendar.getInstance().getTime());
		pago.setExtraInfo(String.valueOf(flight.getPrice()));
		
		DBManager.getInstance().storePago(pago);
		
		
		
		//Hacer FlightReservation
		FlightReservation fReservation=new FlightReservation();
		
		fReservation.setFlight(flight);
		fReservation.setUser(user);
		
		DBManager.getInstance().storeFlightReservation(fReservation);
		System.out.println("Proceso de guardados de vuelos completado");
	}
	

}
