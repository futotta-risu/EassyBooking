package org.SlavaLenin.EassyBooking.app.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
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
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;

public class ServerManagerController {

	List<Flight> flightSearchResult;
	
	public ServerManagerController() throws RemoteException {		
		new ServerManagerFrame(this);
	}
	
    public String login(String username, String password){
    	System.out.println("------------");
    	try {
			User user = LoginService.getInstance().login(username, password);
			System.out.println("Todo salio a pedir de milhouse");
			if(user == null) {
				System.out.println("El user no existe");
				
			}else {
				System.out.println("El user es " + user.getUsername());
				return user.getUsername();
			}
		} catch (LoginTypeNotFoundException e) {
			System.err.println("Error escogiendo el tipo de login");
		} 
    	System.out.println("------------");
    	return null;
    }
    
    public List<Flight> searchFlight(String id){
    	Logger.getLogger(ServerManagerFrame.class.getName()).info("Controller: SearchFlight con " + id);
    	flightSearchResult = new ArrayList<Flight>();
		try {
			flightSearchResult = AirlineService.getInstance().buscarVuelo(id);
		} catch (AirlineTypeNotFoundException e) {
			e.printStackTrace();
		} 	
    	return flightSearchResult;
    }
    
    public Flight getFlightFromSearch(int index) {
    	if(index>=0 && index < flightSearchResult.size())
    		return flightSearchResult.get(index);
		return null;
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
