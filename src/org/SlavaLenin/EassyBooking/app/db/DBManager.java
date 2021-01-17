package org.SlavaLenin.EassyBooking.app.db;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Airport;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

public class DBManager {
	
	private static final long serialVersionUID = 1L;
	
	private static DBManager instance;
	
	
	private final  String selectWhereQuery = "SELECT FROM %s WHERE %s == %s";
	
	private PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	private PersistenceManager pm = pmf.getPersistenceManager();				
	private Transaction transaction = pm.currentTransaction();	
	private User userP= (User) pm.getUserObject();
	
	public DBManager() {
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
		transaction.commit();
	}
	
	public boolean isActiveTransaction() {
		return transaction.isActive();
	}
	
	public void rollbackTransaction() {
		transaction.rollback();
	}
	public void storeUser(User user) {
		pm = pmf.getPersistenceManager();
		userP=(User)pm.setUserObject(user);
	}
	
	public void rollbakcAndClosePM() {
		if (this.isActiveTransaction()) {
	        this.rollbackTransaction();
	    }
	    this.pm.close();
	}
	
	@Deprecated
	@SuppressWarnings({ "unchecked", "static-access" })
	public Query<Object> getSelectWhere(String className, String columnName, String value){
		return this.pm.newQuery(selectWhereQuery.format(className, columnName, value));
	}
	
	@SuppressWarnings("unchecked")
	public Query<User> getUserFromUsername(String username){		
		return this.pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + username +"'");
	}
	
	@SuppressWarnings("unchecked")
	public Pago getPago(String paymentID){		
		Query<Pago> payment = this.pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE paymentID == " + paymentID);
		return (Pago) payment.execute();
	}
	
	public FlightReservation getFlightReservationFromID(String flightReservationID){		
		Query<FlightReservation> fr =  this.pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightReservationID); 
		return (FlightReservation) fr.execute();
	}
	
	@SuppressWarnings("unchecked")
	public Query<Flight> getFlightByFlightNumber(String flightNumber){
		return this.pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE flightNumber == " + flightNumber);
	}
	
	@SuppressWarnings("unchecked")
	public Query<Flight> getFlights(){
		return this.pm.newQuery("SELECT FROM " + Flight.class.getName());
	}
	
	@SuppressWarnings("unchecked")
	public Query<FlightReservation> getFlightReservations(){
		return this.pm.newQuery("SELECT FROM " + FlightReservation.class.getName());
	}
	
	public void updateFlightReservationPrice(String newPrice) {
		pm.newQuery("UPDATE " + FlightReservation.class.getName() + " SET price=value-5.0 WHERE this.value > 100");
	}
	
	public void deleteFlightReservation(FlightReservation reservation) {
		pm.deletePersistent(reservation);
	}
	
	public void deleteFlight(Flight flight) {
		pm.deletePersistent(flight);
	}
	
	
	public static DBManager getInstance() {
		if(instance == null ) {
			instance = new DBManager();
		}
		return instance;
	}

	public PersistenceManager getPm() {
		return pm;
	}
	public Airport getAirportByCode(String airportDeparture) {
		// TODO Auto-generated method stub
		return null;
	}
	public Airline getAirlinetByEnum(AirlineEnum airline) {
		// TODO Auto-generated method stub
		return null;
	}
}
