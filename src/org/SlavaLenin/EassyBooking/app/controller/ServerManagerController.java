package org.SlavaLenin.EassyBooking.app.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;


/**
 * ServerManager Controller for the Server Manager GUI.
 * 
 * <strong>Pattern</strong>
 * <ul>
 *      <li>Controller</li>
 * </ul>
 */
public class ServerManagerController {

	/**
	 * Flight list for the last user flight search.
	 */
	List<Flight> flightSearchResult;
	
	/**
	 * UserDTO for the logged user. The user is null if the user is not logged.
	 */
	UserDTO user = null;
	
	
	/**
	 * ServerManagerController constructor. Generates a ServerManagerFrame.
	 * @throws RemoteException
	 */
	public ServerManagerController(){		
		new ServerManagerFrame(this);
	}
	
	/**
	 * Controller register function. Calls the LoginService {@link LoginService#register(String email, String password, LoginEnum registerType)} function.
	 * 
	 * @param username Username from the GUI
	 * @param password Password from the GUI
	 * @return Created UserDTO if the registration success, null otherwise
	 * @see {@link LoginService},{@link UserDTO}
	 */
	public UserDTO register(String username, String password) {
		Logger logger = ServerLogger.getLogger();
    	logger.info("Register user " + username);
    	
    	user = LoginService.getInstance().register(username, password, LoginEnum.Google);
    	
    	if(user == null)    logger.warning("Registro fallido.");
    	else 				logger.info("Register correctly applied.");
    		
    	return user;
	}
	
	/**
	 * Controller login function. Calls the LoginService {@link LoginService#login(String email, String password, LoginEnum registerType)} function.
	 * 
	 * @param username Username from the GUI
	 * @param password Password from the GUI
	 * @return User's UserDTO if the registration success, null otherwise
	 * @see {@link LoginService},{@link UserDTO}
	 */
    public UserDTO login(String username, String password){
    	Logger logger = ServerLogger.getLogger();
    	logger.info("Login de " + username);
    	
    	this.user = LoginService.getInstance().login(username, password, LoginEnum.Google);
    	
    	if(this.user == null)   logger.warning("Login fallido con el user " + username);
    	else 					logger.info("Logeado con el user " + username);
    	
		return user;
    }
    
    /**
	 * Controller searchFlight function. Calls the AirlineService {@link AirlineService#buscarVuelo(String flightID)} function.
	 * 
	 * @param flightID Identification of the Flight
	 * @return Query of List<Flight> from the external services.
	 * @see {@link AirlineService},{@link Flight}
	 */
    public List<Flight> searchFlight(String flightID){
    	Logger logger = ServerLogger.getLogger();
    	logger.info("SearchFlight con " + flightID);
    	
    	return AirlineService.getInstance().buscarVuelo(flightID);
    }
    
    /**
   	 * Controller reservar function. Calls the AirlineService {@link AirlineService#reservar(String flightID, String username, String sessionKey)} function.
   	 * 
   	 * @param flightID Identification of the Flight
   	 * @see {@link AirlineService},{@link Flight}
   	 */
    public void bookFlight(String flightID) {
    	Logger logger = ServerLogger.getLogger();
    	logger.info("BookFlight with " + flightID );
    	
    	if(user == null) logger.severe("No user currently logged");
    	else AirlineService.getInstance().reservar(flightID, user.getUsername(), user.getSessionKey());
    }
    
    /**
     * Return the Flight object from the last search ({@link ServerManagerController#searchFlight(String flightID)}). 
     * The JList and the flightSearchResult list have the same order and cardinal.
     * @param index Index of the JList
     * @return Flight object from the search
     * @see {@link AirlineService},{@link Flight}
     */
    public Flight getFlightFromSearch(int index) {
    	return flightSearchResult.get(index);   
    }
  
}
