package org.SlavaLenin.EassyBooking.app.db;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.Flight;
import org.SlavaLenin.EassyBooking.app.FlightReservation;
import org.SlavaLenin.EassyBooking.app.Pago;
import org.SlavaLenin.EassyBooking.app.User;

public class DBHandler {
	
	private final  String selectWhereQuery = "SELECT FROM %s WHERE %s == %s";
	private PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	private PersistenceManager pm = pmf.getPersistenceManager();				
	private Transaction transaction = pm.currentTransaction();	
	
	public DBHandler() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		
	}
	public void createNewTransaction() {
		pm = pmf.getPersistenceManager();				
		transaction = pm.currentTransaction();
	}
	
	public void beginTransaction() {
		transaction.begin();
	}
	
	public void commitTransaction() {
		transaction.begin();
	}
	
	public boolean isActiveTransaction() {
		return transaction.isActive();
	}
	
	public void rollbackTransaction() {
		transaction.rollback();
	}
	
	public void rollbakcAndClosePM() {
		if (this.isActiveTransaction()) {
	        this.rollbackTransaction();
	    }
	    this.pm.close();
	}
	
	// TODO Hacer mas tarde para clases generalizadas
	//public void makePersistent();
	
	public Query<Object> getSelectWhere(String className, String columnName, String value){
		return this.pm.newQuery(selectWhereQuery.format(className, columnName, value));
	}
	
	public Query<User> getUserFromUsername(String username){		
		return this.pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == " + username);
	}
	
	public Query<Pago> getPago(String paymentID){		
		return this.pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE paymentID == 4567890" + paymentID);
	}
	
	public Query<FlightReservation> getFlightReservationFromFlightReservationID(String flightReservationID){		
		return this.pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightReservationID); 
	}
	
	public Query<Flight> getFlightByFlightNumber(String flightNumber){
		return this.pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE flightNumber == " + flightNumber);
	}
	
	public void deleteFlightReservation(FlightReservation reservation) {
		pm.deletePersistent(reservation);
	}
	
	public void deleteFlight(Flight flight) {
		pm.deletePersistent(flight);
	}
	

	public PersistenceManager getPm() {
		return pm;
	}
}
