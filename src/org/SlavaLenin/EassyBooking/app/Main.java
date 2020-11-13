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

public class Main {

	public static void main(String[] args) {

		try {
			PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
			PersistenceManager pm = pmf.getPersistenceManager();				
			Transaction transaction = pm.currentTransaction();	
			Calendar calendar = Calendar.getInstance();
			
			// ------------------------------------- INSERT -----------------------------------------------------
			try {
			    transaction.begin();
			    
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
			    pm.makePersistent(user);
			    System.out.println("+ Inserted user into db: " + user.getUsername());

			    pm.makePersistent(flight);
			    System.out.println("+ Inserted flight into db: " + flight.getFlightNumber());

			    pm.makePersistent(flightReservation);
			    System.out.println("+ Inserted flightReservation into db: " + flightReservation.getFlightReservationID());

			    pm.makePersistent(pago);
			    System.out.println("+ Inserted pago into db: " + pago.getPaymentID());
			    
			    //pm.makePersistent(passengerInfo);
			    //System.out.println("+ Inserted passengerInfo into db: " + passengerInfo.getPassengerDNI());

		    
			    transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception inserting data into db: " + ex.getMessage());
			} finally {		    
				if (transaction.isActive()) {
			        transaction.rollback();
			    }
			    pm.close();
			}
			
			// ------------------------------------- SELECT -----------------------------------------------------
			pm = pmf.getPersistenceManager();
			transaction = pm.currentTransaction();
				
			try {
				transaction.begin();
	
			    @SuppressWarnings("unchecked")
				Query<Flight> flightQuery = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE flightNumber == 1234");
			    
			    for (Flight flight : flightQuery.executeList()) {
			        System.out.println("? Selected Flight from db: " + flight.getFlightNumber());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<User> userQuery = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == Username1234");
			    
			    for (User user : userQuery.executeList()) {
			        System.out.println("? Selected User from db: " + user.getUsername());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<FlightReservation> flightReservationQuery = pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == 24343");
			    
			    for (FlightReservation flightReservation : flightReservationQuery.executeList()) {
			        System.out.println("? Selected flightReservation from db: " + flightReservation.getFlightReservationID());
			    }
			    
			    @SuppressWarnings("unchecked")
				Query<Pago> pagoQuery = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE paymentID == 4567890");
			    
			    for (Pago pago : pagoQuery.executeList()) {
			        System.out.println("? Selected pago from db: " + pago.getPaymentID());
			    }
			    
			    /*@SuppressWarnings("unchecked")
				Query<PassengerInfo> passengerInfoQuery = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE passengerDNI == 1234");
			    
			    for (PassengerInfo passengerInfo : passengerInfoQuery.executeList()) {
			        System.out.println("? Selected product from db: " + passengerInfo.getPassengerDNI());
			    }*/
			    
			    transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
			        transaction.rollback();
			    }

			    pm.close();
			}			
			
			//------------------------------------ DELETE -------------------------------------------------
			pm = pmf.getPersistenceManager();
			transaction = pm.currentTransaction();
			
			try {
				transaction.begin();
				
			    @SuppressWarnings("unchecked")
				Query<Flight> flightQuery = pm.newQuery("SELECT FROM " + Flight.class.getName());
			    
			    for (Flight flight : flightQuery.executeList()) {
			        System.out.println("- Deleted flight from db: " + flight.getFlightNumber());
			        pm.deletePersistent(flight);
			    }

				
				Extent<FlightReservation> reservationExtend = pm.getExtent(FlightReservation.class);
			    
			    for (FlightReservation reservation : reservationExtend) {
			    	System.out.println("- Deleted inventory from db: " + reservation.getFlightReservationID());
			    	pm.deletePersistent(reservation);			        
			    }
			    transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception deleting data from DB: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
			        transaction.rollback();
			    }
	
			    pm.close();
			}
			
			
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
	}

}