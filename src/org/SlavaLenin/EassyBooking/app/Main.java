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
			
			try {
			    transaction.begin();
			    
List<PassengerInfo> listaPasajeros = new ArrayList<PassengerInfo>();
			    
			    // ------ PASSENGER INFO ------
			    PassengerInfo passengerInfo = new PassengerInfo();
			    passengerInfo.setPassengerDNI("456765347U");
			    passengerInfo.setPassengerLastName("Gimenez");
			    passengerInfo.setPassengerName("Pedro");
			    passengerInfo.setPassengerPhoneNum(687656744);
			    
			    listaPasajeros.add(passengerInfo);
			    
			    
				   
			    Pago pago = new Pago();
			    Flight flight = new Flight();

			    
			 
			    FlightReservation flightReservation = new FlightReservation();
			    flightReservation.setFlight(flight);
			    flightReservation.setFlightReservationID(24343);
			    flightReservation.setNumberOfSeats(4);
			    flightReservation.setPago(pago);
			    
			    
			    // ------ FLIGHT -------
			    flight.setFlightNumber(1234);
			    flight.setDateArrival(calendar.getTime());
			    flight.setDateDeparture(calendar.getTime());
			    //flight.setFlightReservations(flightReservation);
			    flight.setNumberPassengers(5);
			    flight.setNumberRemainingSeats(20);
			    
		
			    
			    User user = new User();
			    
			   // pm.makePersistent(reservation);
			    
			   /*
			    * System.out.println("+ Inserted inventory into db: " + resrvation.getFlightReservationID());
			    
			    transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception inserting data into db: " + ex.getMessage());
			} finally {		    
				if (transaction.isActive()) {
			        transaction.rollback();
			    }
			    
			    pm.close();
			}
			
			//Select data using a Query
			pm = pmf.getPersistenceManager();
			transaction = pm.currentTransaction();
				
			try {
				transaction.begin();
	
			    @SuppressWarnings("unchecked")
				Query<Product> productsQuery = pm.newQuery("SELECT FROM " + Product.class.getName() + " WHERE price < 150.00 ORDER BY price ASC");
			    
			    for (Product product : productsQuery.executeList()) {
			        System.out.println("? Selected product from db: " + product.getName());
			    }
			    	
			    transaction.commit();
			} catch(Exception ex) {
				System.err.println("* Exception executing a query: " + ex.getMessage());
			} finally {
				if (transaction.isActive()) {
			        transaction.rollback();
			    }
	
			    pm.close();
			}			
			
			//Delete data using Extent
			pm = pmf.getPersistenceManager();
			transaction = pm.currentTransaction();
			
			try {
				transaction.begin();
				
			    @SuppressWarnings("unchecked")
				Query<Flight> flightQuery = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE price < 150.00 ORDER BY price ASC");
			    
			    for (Flight flight : flightQuery.executeList()) {
			        System.out.println("- Deleted product from db: " + flight.getFlightNumber()());
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
			*/
			
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
	}

}