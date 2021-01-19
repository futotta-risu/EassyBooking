package org.SlavaLenin.EassyBooking.app.controller;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.SlavaLenin.EassyBooking.app.data.Airport;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
import org.SlavaLenin.EassyBooking.app.remote.IRemoteFacade;
import org.SlavaLenin.EassyBooking.app.remote.RemoteFacade;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;
import org.SlavaLenin.EassyBooking.app.services.PaymentService;

public class ServerManagerController {

	List<Flight> flightSearchResult;
	
	UserDTO user = null;
	
	public ServerManagerController() throws RemoteException {		
		new ServerManagerFrame(this);
	}
	
    public UserDTO login(String username, String password){
    	Logger logger = ServerLogger.getLogger();
    	logger.info("ServerManagerController: Login de " + username);
    	
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
    	
    	return flightSearchResult;
    }
    
    public void bookFlight(String id) {
    	Logger logger = ServerLogger.getLogger();
    	logger.info("BookFlight with " + id );
    	
    	Flight f = DBManager.getInstance().getFlight(id);
    	
    	if(user == null) {
    		logger.severe("No user available on id " + id + " and Airline " + f.getAirline().getCode());
    		return;
    	}
		AirlineService.getInstance().reservar(id, user.getUsername(), user.getSessionKey());
		
		
    }
    
    public Flight getFlightFromSearch(int index) {
    	return flightSearchResult.get(index);   
    }
    
    public void exit(){
    	System.exit(0);
    }
    
  
}
