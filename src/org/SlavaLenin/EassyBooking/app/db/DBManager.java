package org.SlavaLenin.EassyBooking.app.db;

import java.util.HashMap;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.SlavaLenin.EassyBooking.app.dao.FlightDAO;
import org.SlavaLenin.EassyBooking.app.dao.FlightReservationDAO;
import org.SlavaLenin.EassyBooking.app.dao.PagoDAO;
import org.SlavaLenin.EassyBooking.app.dao.UserDAO;
import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Airport;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

public class DBManager {
	
	private HashMap<AirlineEnum, Airline> airlines = new HashMap<AirlineEnum, Airline>();
	private HashMap<String, Airport> airports = new HashMap<String, Airport>();
	
	private static DBManager instance;
	private PersistenceManagerFactory pmf;
	
	public static DBManager getInstance() {
		if(instance == null ) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public DBManager() {
		pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	
	// -------------- USER -----------------
	public void storeUser(User user) {
		UserDAO.storeUser(user);
	}
	
	public List<User> getUsers() {
		return UserDAO.getUsers();
	}
	
	public User getUser(String username) {
		return UserDAO.getUser(username);
	}
	
	public void updateUser(User user) {
		UserDAO.updateUser(user);
	}
	
	public User getUserWithKey(String username, String sessionKey) {
		
		User user = this.getUser(username);
		
		if(user == null) return null;
		
	
		if(!user.checkSessionKey(sessionKey))
			return null;
	s
		return user;
	}
	
	
	// -------------- PAGO -----------------
	public void storePago(Pago pago) {
		PagoDAO.storePago(pago);
	}
	
	public List<Pago> getPagos() {
		return PagoDAO.getPagos();
	}
	
	public Pago getPago(String id_pago) {
		return PagoDAO.getPago(id_pago);
	}
	
	public void updatePago(Pago pago) {
		PagoDAO.updatePago(pago);
	}
	
	public void deletePago(String id_pago) {
		PagoDAO.deletePago(id_pago);
	}
	
	public void deleteAllPagos() {
		PagoDAO.deleteAllPagos();
	}
	
	// -------------- FLIGHT -----------------
	public void storeFlight(Flight flight) {
		FlightDAO.storeFlight(flight);
	}
	
	public void storeFlights(List<Flight> flights) {
		for(Flight f : flights)
			storeFlight(f);
	}
	
	public List<Flight> getFlights() {
		return FlightDAO.getFlights();
	}
	
	public Flight getFlight(String flightID) {
		return FlightDAO.getFlight(flightID);
	}
	
	public void updateFlight(Flight flight) {
		FlightDAO.updateFlight(flight);
	}
	
	// -------------- FLIGHT RESERVATION -----------------
	public void storeFlightReservation(FlightReservation flightReservation) {
		FlightReservationDAO.storeFlightReservation(flightReservation);
	}
	
	public List<FlightReservation> getFlightReservations() {
		return FlightReservationDAO.getFlightReservations();
	}
	
	public FlightReservation getFlightReservation(String flightNumber) {
		return FlightReservationDAO.getFlightReservation(flightNumber);
	}
	
	public void updateFlightReservation(FlightReservation flightReservation) {
		FlightReservationDAO.updateFlightReservation(flightReservation);
	}
	
	public void deleteFlightReservation(String flightNumber) {
		FlightReservationDAO.deleteFlightReservation(flightNumber);
	}
	
	public void deleteAllFlightReservations() {
		FlightReservationDAO.deleteAllFlightReservations();
	}
	
	public void storeAirport(String code, Airport airport) {
		if(!airports.containsKey(code)) 
			airports.put(code, airport);
	}
	
	public boolean hasAirport(String code) {
		return airports.containsKey(code);
	}
	
	public Airport getAirport(String code) {
		if(airports.containsKey(code))
			return airports.get(code);
		return null;
	}
	
	public void storeAirline(AirlineEnum code, Airline airline) {
		if(!airlines.containsKey(code)) 
			airlines.put(code, airline);
	}
	
	public boolean hasAirline(AirlineEnum code) {
		return airlines.containsKey(code);
	}
	
	public Airline getAirline(AirlineEnum code) {
		if(airlines.containsKey(code))
			return airlines.get(code);
		return null;
	}
	
}
