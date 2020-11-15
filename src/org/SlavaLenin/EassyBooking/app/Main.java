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

	public static void main(String[] args) {

		try {
				
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
		    
		    
		    GenericDAO.pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		    PersistenceManager pm = GenericDAO.pmf.getPersistenceManager();
		    Transaction tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        pm.makePersistent(user);
		        pm.makePersistent(flight);
		        pm.makePersistent(pago);
		        pm.makePersistent(flightReservation);
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("2----- Hemos guardado y a editar--------");
		    
		    // UPDATE
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        Query<FlightReservation> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName());
				List<FlightReservation> cosa = query.executeList();
				int i = 444;
				for(FlightReservation frl : cosa){
		        	
					frl.setFlightReservationID(i++);
				}
				pm.makePersistentAll(cosa);
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("3-------------");
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        Query<FlightReservation> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName());
				for(FlightReservation frl : query.executeList()){
					System.out.println(frl.getFlightReservationID());
				}
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("4-------------");
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        Query<FlightReservation> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName());
				for(FlightReservation frl : query.executeList()){
					System.out.println(frl.getFlightReservationID());
				}
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("5-------------");
		    System.out.println("4-------------");
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        Query<User> query = pm.newQuery("SELECT FROM " + User.class.getName());
				for(User frl : query.executeList()){
					System.out.println(frl.getUsername());
				}
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("353454-------------");
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        pm.flush();
		        Query<User> query1 = pm.newQuery(User.class);
				System.out.println(" * '" + query1.deletePersistentAll() + "' users and their accounts deleted from the DB.");
		        pm.flush();
				tx.commit();
				pm.flush();
		    }catch(Exception e) {
		    	System.out.println("999-------------");
		    	e.printStackTrace();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    System.out.println("8-------------");
		    System.out.println("4-------------");
		    pm = GenericDAO.pmf.getPersistenceManager();
		    tx=pm.currentTransaction();
		    try{
		        tx.begin();
		        Query<User> query = pm.newQuery("SELECT FROM " + User.class.getName());
				for(User frl : query.executeList()){
					System.out.println(frl.getUsername());
				}
		        tx.commit();
		    }
		    finally{
		        if (tx.isActive()){
		            tx.rollback();
		        }
		        pm.close();
		    }
		    /*
		    userDAO.storeUser(user);
		    fl.storeFlight(flight);
		    flr.storeFlightReservation(flightReservation);
		    pagoDAO.storePago(pago);
			    
			
			// ------------------------------------- SELECT -----------------------------------------------------
			
			    
			List<Flight> flightList_t = fl.getFlights();
			
		    
		    for (Flight flight_t : flightList_t) 
		        System.out.println("? Selected Flight from db: " + flight_t.getFlightNumber());
			    
			User user_t = userDAO.getUser("Username1234");
		    System.out.println("? Selected User from db: " + user_t.getUsername());
			    
			    
			    
			FlightReservation flightReservation_t = flr.getProduct("24343");
		    System.out.println("? Selected flightReservation from db: " + flightReservation_t.getFlightReservationID());
		    
			Pago pago_t = pagoDAO.getPago("4567890");
		    System.out.println("? Selected pago from db: " + pago_t.getPaymentID());
			    
			   //------------------------UPdate------------
		    user.setEmail("otroemailw@gmail.com");
		    userDAO.updateUser(user);
			
			//------------------------------------ DELETE -------------------------------------------------
			flr.deleteAllFlightReservations();
			*/
			
		} catch (Exception ex) {
			System.err.println("* Exception: " + ex.getMessage());
		}
	}

}