package org.SlavaLenin.EassyBooking.app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.db.DBHandler;

public class Main {

	public static void main(String[] args) {

		try {
			DBHandler dbh = new DBHandler();	
			dbh.createNewTransaction();
			Calendar calendar = Calendar.getInstance();
			
			// ------------------------------------- INSERT -----------------------------------------------------
			try {
				dbh.beginTransaction();
			    
			    //List<PassengerInfo> listaPasajeros = new ArrayList<PassengerInfo>();
			    //PassengerInfo passengerInfo = new PassengerInfo();

			    List<FlightReservation> listaReservas = new ArrayList<FlightReservation>();
			    Pago pago = new Pago();
			    Flight flight = new Flight();
			    FlightReservation flightReservation = new FlightReservation();
			    User user = new User();
			    
			    ArrayList<String> pp = new ArrayList<String>();
			    
			    /*
			     ------ PASSENGER INFO ------
			    passengerInfo.setPassengerDNI("456765347U");
			    passengerInfo.setPassengerLastName("Gimenez");
			    passengerInfo.setPassengerName("Pedro");
			    passengerInfo.setPassengerPhoneNum(687656744);
			    passengerInfo.setFlightReservations(listaReservas);
			    
			    listaPasajeros.add(passengerInfo);
			    */
			    
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
			    flightReservation.setPassengersInfo(pp);
			    flightReservation.setPrice(202);
			    flightReservation.setUser(user);
			    
			    listaReservas.add(flightReservation);
			    
			    
			    // ------ FLIGHT -------
			    flight.setFlightNumber(1234);
			    flight.setDateArrival(calendar.getTime());
			    flight.setDateDeparture(calendar.getTime());
			    //flight.setFlightReservations(listaReservas);
			    flight.setNumberPassengers(5);
			    flight.setNumberRemainingSeats(20);
			    
		
				// -------- USER ---------------
			    user.setEmail("kfjeejw@gmail.com");
			    user.setFlightReservations(listaReservas);
			    user.setLoginSystemType(2);
			    user.setName("NombreUser");
			    user.setOAuth("djofndspfmmfp83yr8y2gf293fh");
			    user.setUsername("Username1234");
			    
			        
			    // Cuales creamos persistentes??
			    dbh.getPm().makePersistent(user);
			    System.out.println("+ Inserted user into db: " + user.getUsername());

			    dbh.getPm().makePersistent(flight);
			    System.out.println("+ Inserted flight into db: " + flight.getFlightNumber());

			    dbh.getPm().makePersistent(flightReservation);
			    System.out.println("+ Inserted flightReservation into db: " + flightReservation.getFlightReservationID());

			    dbh.getPm().makePersistent(pago);
			    System.out.println("+ Inserted pago into db: " + pago.getPaymentID());
			    
			    //pm.makePersistent(passengerInfo);
			    //System.out.println("+ Inserted passengerInfo into db: " + passengerInfo.getPassengerDNI());

		    
			    dbh.commitTransaction();
			} catch(Exception ex) {
				System.err.println("* Exception inserting data into db: " + ex.getMessage());
			} finally {		    
				dbh.rollbakcAndClosePM();
			}
			
			// ------------------------------------- SELECT -----------------------------------------------------
			dbh.createNewTransaction();
				
			try {
				dbh.beginTransaction();
	
			    @SuppressWarnings("unchecked")
				Query<Flight> flightQuery = dbh.getFlightByFlightNumber("1234");
				
			    
			    for (Flight flight : flightQuery.executeList()) {
			        System.out.println("? Selected Flight from db: " + flight.getFlightNumber());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<User> userQuery = dbh.getUserFromUsername("Username1234");
			    
			    for (User user : userQuery.executeList()) {
			        System.out.println("? Selected User from db: " + user.getUsername());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<FlightReservation> flightReservationQuery = dbh.getFlightReservationFromFlightReservationID("24343");
			    
			    for (FlightReservation flightReservation : flightReservationQuery.executeList()) {
			        System.out.println("? Selected flightReservation from db: " + flightReservation.getFlightReservationID());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<Pago> pagoQuery = dbh.getPago("4567890");
			    for (Pago pago : pagoQuery.executeList()) {
			        System.out.println("? Selected pago from db: " + pago.getPaymentID());
			    }
			    
			    /*@SuppressWarnings("unchecked")
				Query<PassengerInfo> passengerInfoQuery = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE passengerDNI == 1234");
			    
			    for (PassengerInfo passengerInfo : passengerInfoQuery.executeList()) {
			        System.out.println("? Selected product from db: " + passengerInfo.getPassengerDNI());
			    }*/
			    
			    dbh.commitTransaction();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				dbh.rollbakcAndClosePM();
			}			
			
			//------------------------------------ DELETE -------------------------------------------------
			dbh.createNewTransaction();
			
			try {
				dbh.beginTransaction();
				
			    @SuppressWarnings("unchecked")
				Query<Flight> flightQuery = dbh.getFlights();
			    
			    for (Flight flight : flightQuery.executeList()) {
			        System.out.println("- Deleted flight from db: " + flight.getFlightNumber());
			        dbh.getPm().deletePersistent(flight);
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