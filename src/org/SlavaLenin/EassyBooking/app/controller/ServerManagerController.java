package org.SlavaLenin.EassyBooking.app.controller;

import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;

/**
 * <strong>Pattern</strong>
 * <ul>
 *      <li>Controller</li>
 * </ul>
 */
public class ServerManagerController {

	List<Flight> flightSearchResult;
	
	UserDTO user = null;
	
	public ServerManagerController() throws RemoteException {		
		new ServerManagerFrame(this);
	}
	
	public UserDTO register(String username, String password) {
		Logger logger = ServerLogger.getLogger();
    	logger.info("Register user " + username);
    	
    	user = LoginService.getInstance().register(username, password, LoginEnum.Google);
    	
    	if(user == null) {
    		logger.info("Registro fallido " + username);
    		return null;
    	}
    	
    	return user;
	}
	
    public UserDTO login(String username, String password){
    	Logger logger = ServerLogger.getLogger();
    	logger.info("Login de " + username);
    	
    	UserDTO user = LoginService.getInstance().login(username, password, LoginEnum.Google);
    	
    	if(user == null) {
    		logger.info("ServerManagerController: Login fallido con el user " + username);
    		return null;
    	}
    	this.user = user;
    	logger.info("ServerManagerController: Logeado con el user " + username);
		return user;
    }
    
    public List<Flight> searchFlight(String id){
    	Logger.getLogger(ServerManagerFrame.class.getName()).info("ServerManagerController: SearchFlight con " + id);
    	
    	flightSearchResult = AirlineService.getInstance().buscarVuelo(id);
    	System.out.println("HEMOS SABEADO " + flightSearchResult.size());
    	return flightSearchResult;
    }
    
    public void bookFlight(String id) {
    	Logger logger = ServerLogger.getLogger();
    	logger.info("BookFlight with " + id );
    	
    	
    	if(user == null) {
    		logger.severe("No user currently logged");
    		return;
    	}
    	
    	Flight f = DBManager.getInstance().getFlight(id);
    	
    	System.out.println("------ESTA ES IMPORTANTE-------||||" +f.getFlightID() + "|||||-");
    	
		AirlineService.getInstance().reservar(id, user.getUsername(), user.getSessionKey());
		
		
    }
    
    public Flight getFlightFromSearch(int index) {
    	return flightSearchResult.get(index);   
    }
    
    public void exit(){
    	System.exit(0);
    }
    
  
}
