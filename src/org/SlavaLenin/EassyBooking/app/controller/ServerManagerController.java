package org.SlavaLenin.EassyBooking.app.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.SlavaLenin.EassyBooking.app.data.Airport;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;

public class ServerManagerController {

	List<Flight> flightSearchResult;
	
	public ServerManagerController() throws RemoteException {		
		new ServerManagerFrame(this);
	}
	
    public String login(String username, String password){
    	Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
    	logger.info("ServerManagerController: Login de " + username);
    	
    	User user = LoginService.getInstance().login(username, password, LoginEnum.Google);
    	
    	if(user == null) {
    		logger.info("ServerManagerController: Login fallido con el user " + username);
    		return null;
    	}
    	
    	logger.info("ServerManagerController: Logeado con el user " + username);
		return user.getUsername();
    }
    
    public List<Flight> searchFlight(String id){
    	Logger.getLogger(ServerManagerFrame.class.getName()).info("ServerManagerController: SearchFlight con " + id);
    	
    	flightSearchResult = AirlineService.getInstance().buscarVuelo(id);
    	
    	return flightSearchResult;
    }
    
    public void bookFlight(String id, AirlineEnum airline) {
    	Logger.getLogger(ServerManagerFrame.class.getName()).info("Controller: BookFlight con " + id + " and Airline " + airline.getCode());
    	
		AirlineService.getInstance().reservar(id, airline);
			
    }
    
    public Flight getFlightFromSearch(int index) {
    	return flightSearchResult.get(index);   
    }
    
    public void exit(){
    	System.exit(0);
    }
    
    public static void main(String[] args) throws RemoteException { 
    	System.out.println("Comenzando el server");
    	
    	final String[] airportCodes = {"BILB","BARC","MADR","BUDA","PARI","BTCH","COTI"};
    	final String[] airportNames = {"BilbaoAir", "BarcelonaAir", "MadridAir", "BudapestAir", "ParisCash", "BitcoinH","CotiBTC"};
    	final String[] airportLocations = { "Bilbao","Barcelona", "Madrid", "Budapest","Paris","Bitcoin","Coti"};
    	
    	Random rnd = new Random();
    	for(int i = 0; i < 7; i++) {
    		Airport a = new Airport(airportCodes[i], airportNames[i]);
    		a.setGates(Math.abs(rnd.nextInt())%15);
    		a.setLocation(airportLocations[i]);
    		DBManager.getInstance().storeAirport(airportCodes[i],a);
    	}
    	
    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	      // Here, we can safely update the GUI
    	      // because we'll be called from the
    	      // event dispatch thread
    	    	try {
    	    		System.out.println("Ola");
					new ServerManagerController();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	  });
    	
    }
}
