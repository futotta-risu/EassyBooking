package org.SlavaLenin.EassyBooking.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.dao.FlightDAO;
import org.SlavaLenin.EassyBooking.app.dao.FlightReservationDAO;
import org.SlavaLenin.EassyBooking.app.dao.GenericDAO;
import org.SlavaLenin.EassyBooking.app.dao.PagoDAO;
import org.SlavaLenin.EassyBooking.app.dao.UserDAO;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.PassengerInfo;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBHandler;


public class Main {
	
	public static void printAll(FlightDAO flightDAO, FlightReservationDAO flightReservationDAO, PagoDAO pagoDAO, UserDAO userDAO) {
		System.out.println("---------SELECT ALL --------");
		
		List<FlightReservation>list = flightReservationDAO.getFlightReservations();
	    for(FlightReservation fl2 : list)
			System.out.println(fl2);
		
	    List<User> u_list = userDAO.getUsers();
	    for(User u : u_list)
			System.out.println(u);
	    
	    List<Pago> p_list = pagoDAO.getPagos();
	    
	    for(Pago u : p_list)
			System.out.println(u);
	    
	    List<Flight> f_list = flightDAO.getFlights();
	    for(Flight u : f_list)
			System.out.println(u);
	    
	    System.out.println("#-----------------#");
	}

	public static void main(String[] args) {

			
		Calendar calendar = Calendar.getInstance();
		
		FlightDAO flightDAO = new FlightDAO();
		FlightReservationDAO flightReservationDAO = new FlightReservationDAO();
		PagoDAO pagoDAO = new PagoDAO();
		UserDAO userDAO = new UserDAO();
		
		// ------------------------------------- INSERT -----------------------------------------------------
		
		    
	    List<PassengerInfo> listaPasajeros = new ArrayList<PassengerInfo>();
	    PassengerInfo passengerInfo = new PassengerInfo();

	    List<FlightReservation> listaReservas = new ArrayList<FlightReservation>();
	    Pago pago = new Pago();
	    Flight flight = new Flight();
	    FlightReservation flightReservation = new FlightReservation();
	    User user = new User();
	    
	    
	    
	     // ------ PASSENGER INFO ------
	    passengerInfo.setPassengerDNI("456765347U");
	    passengerInfo.setPassengerLastName("Gimenez");
	    passengerInfo.setPassengerName("Pedro");
	    passengerInfo.setPassengerPhoneNum("687656744");
	    
	    listaPasajeros.add(passengerInfo);
	    
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
		// -------- PAGO ---------------
	    pago.setConfirmationCode("bfndsk2");
	    pago.setDate(calendar.getTime());
	    pago.setExtraInfo("Informacion extra");
	    pago.setPaymentID(4567890);
	    pagoDAO.storePago(pago);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    
	    // ------ FLIGHT -------
	    flight.setFlightNumber(1234);
	    flight.setDateArrival(calendar.getTime());
	    flight.setDateDeparture(calendar.getTime());
	    flight.setNumberPassengers(5);
	    flight.setNumberRemainingSeats(20);
	    
	    flightDAO.storeFlight(flight);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    
		
	    
	    
	    
	    

		// -------- USER ---------------
	    user.setEmail("kfjeejw@gmail.com");
	    user.setLoginSystemType(2);
	    user.setName("NombreUser");
	    user.setOAuth("djofndspfmmfp83yr8y2gf293fh");
	    user.setUsername("Username1234");
	    userDAO.storeUser(user);
	    
	    // -------- USER ADD PAGO ---------------
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    user = userDAO.getUser("Username1234");
	    pago = pagoDAO.getPago("4567890");
	    user.addPago(pago);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    
	    
	    user = userDAO.getUser("Username1234");
	    System.out.println("88--");
	    pago = pagoDAO.getPago("4567890");
	    flight = flightDAO.getFlight("1234");
	    
	    // -------- FLIGHT RESERVATION ---------------
	    flightReservation.setFlight(flight);
	    flightReservation.setFlightReservationID(24343);
	    flightReservation.setNumberOfSeats(4);
	    flightReservation.setPago(pago);
	    flightReservation.setPassengersInfo(listaPasajeros);
	    flightReservation.setPrice(202);
	    flightReservation.setUser(user);
	    
	    listaReservas.add(flightReservation);
	    
	    
	    flightReservationDAO.storeFlightReservation(flightReservation);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    
	    // -------- USER ADD FLIGHTRESERVATION---------------
	    flightReservation = flightReservationDAO.getFlightReservation("24343");
	    user.addFlightReservation(flightReservation);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	    
	    // -------- UPDATE---------------
	    System.out.println("-----UPDATE-------");
	    flightReservation = flightReservationDAO.getFlightReservation("24343");
	    flightReservation.setFlightReservationID(88);
	    flightReservationDAO.updateFlightReservation(flightReservation);
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
	     
	    
	    // -------- DELETE ---------------
	    System.out.println("-----DELETE-------");
	    
	    // Get flight reservation and user
	    flightReservation = flightReservationDAO.getFlightReservation("88");
	    user = userDAO.getUser("Username1234");
	    
	    // remove from user list
	    user.removeFlightReservation(flightReservation);
	    
	    flightReservationDAO.deleteFlightReservation(String.valueOf(flightReservation.getFlightReservationID()));
	    
	    printAll(flightDAO, flightReservationDAO, pagoDAO, userDAO);
		   
			
		
	}

}