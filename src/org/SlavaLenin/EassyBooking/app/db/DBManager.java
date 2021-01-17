package org.SlavaLenin.EassyBooking.app.db;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.dao.FlightDAO;
import org.SlavaLenin.EassyBooking.app.dao.FlightReservationDAO;
import org.SlavaLenin.EassyBooking.app.dao.PagoDAO;
import org.SlavaLenin.EassyBooking.app.dao.UserDAO;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;
import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;

public class DBManager {
	
	private static final long serialVersionUID = 1L;
	
	private static DBManager instance;
	private PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	
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
	
	public List<Flight> getFlights() {
		return FlightDAO.getFlights();
	}
	
	public Flight getFlight(String flightNumber) {
		return FlightDAO.getFlight(flightNumber);
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
	
}
