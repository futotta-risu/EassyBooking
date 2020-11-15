package org.SlavaLenin.EassyBooking.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jdo.Query;

import org.SlavaLenin.EassyBooking.app.dao.FlightDAO;
import org.SlavaLenin.EassyBooking.app.dao.FlightReservationDAO;
import org.SlavaLenin.EassyBooking.app.dao.PagoDAO;
import org.SlavaLenin.EassyBooking.app.dao.UserDAO;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.PassengerInfo;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBHandler;

public class Main {

	public static void main(String[] args) {

		try {
			DBHandler dbh = new DBHandler();	
			dbh.createNewTransaction();
			Calendar calendar = Calendar.getInstance();
			
			FlightDAO fl = new FlightDAO();
			FlightReservationDAO flr = new FlightReservationDAO();
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
		    
		    
			// -------- PAGO ---------------
		    pago.setConfirmationCode("bfndsk2");
		    pago.setDate(calendar.getTime());
		    pago.setExtraInfo("Informacion extra");
		    pago.setFlightReservations(flightReservation);
		    pago.setPaymentID(4567890);
		    pago.setUser(user);
		    
		    
			// -------- FLIGHT RESERVATION ---------------
		    flightReservation.setFlight(flight);
		    flightReservation.setFlightReservationID(24343);
		    flightReservation.setNumberOfSeats(4);
		    flightReservation.setPago(pago);
		    flightReservation.setPassengersInfo(listaPasajeros);
		    flightReservation.setPrice(202);
		    flightReservation.setUser(user);
		    
		    listaReservas.add(flightReservation);
		    
		    
		    // ------ FLIGHT -------
		    flight.setFlightNumber(1234);
		    flight.setDateArrival(calendar.getTime());
		    flight.setDateDeparture(calendar.getTime());
		    flight.setNumberPassengers(5);
		    flight.setNumberRemainingSeats(20);
		    
	
			// -------- USER ---------------
		    user.setEmail("kfjeejw@gmail.com");
		    user.setFlightReservations(listaReservas);
		    user.setLoginSystemType(2);
		    user.setName("NombreUser");
		    user.setOAuth("djofndspfmmfp83yr8y2gf293fh");
		    user.setUsername("Username1234");
		    
		        
		   
		    userDAO.storeUser(user);
		    fl.storeFlight(flight);
		    flr.storeFlight(flightReservation);
		    pagoDAO.storePago(pago);
			    
			
			// ------------------------------------- SELECT -----------------------------------------------------
			
			    
			List<Flight> flightList_t = fl.getFlights();
			
		    
		    for (Flight flight_t : flightList_t) 
		        System.out.println("? Selected Flight from db: " + flight_t.getFlightNumber());
			    
			    
			    
			User user_t = userDAO.getProduct("Username1234");
		    System.out.println("? Selected User from db: " + user_t.getUsername());
			    
			    
			    
			FlightReservation flightReservation_t = flr.getProduct("24343");
		    System.out.println("? Selected flightReservation from db: " + flightReservation_t.getFlightReservationID());
		    
			Pago pago_t = dbh.getPago("4567890");
		    System.out.println("? Selected pago from db: " + pago_t.getPaymentID());
			    
			    
			
			//------------------------------------ DELETE -------------------------------------------------
			dbh.createNewTransaction();
			
			try {
				dbh.beginTransaction();
				
			    
				Query<Flight> flightQuery = dbh.getFlights();
			    
			    for (Flight flight_tt : flightQuery.executeList()) {
			        System.out.println("- Deleted flight from db: " + flight_tt.getFlightNumber());
			        dbh.getPm().deletePersistent(flight_tt);
			    }

			    Query<FlightReservation> reservations = dbh.getFlightReservations();
			    
			    for (FlightReservation reservation : reservations.executeList()) {
			    	System.out.println("- Deleted inventory from db: " + reservation.getFlightReservationID());
			    	dbh.deleteFlightReservation(reservation);		        
			    }
			    dbh.commitTransaction();
			} catch(Exception ex) {
				System.err.println("* Exception deleting data from DB: " + ex.getMessage());
			} finally {
				dbh.rollbakcAndClosePM();
			}
			
			
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
	}

}