package org.SlavaLenin.EassyBooking.app;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Random;

import javax.swing.SwingUtilities;

import org.SlavaLenin.EassyBooking.app.controller.ServerManagerController;
import org.SlavaLenin.EassyBooking.app.data.Airport;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
import org.SlavaLenin.EassyBooking.app.remote.IRemoteFacade;
import org.SlavaLenin.EassyBooking.app.remote.RemoteFacade;


public class Launcher {
	
	public static void main(String[] args) throws RemoteException { 
	  	ServerLogger.setLoggerHandler();
    	System.out.println("Server starting");
    	
    	if (args.length < 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}
    	
    	startWindow();
    	startServer( "//" + args[0] + ":" + args[1] + "/" + args[2]);
    }
  
  
	public static void initializeData() {
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
	}
	public static void startServer(String name) {
		  
	
		try {		
			IRemoteFacade objServer = RemoteFacade.getInstance();
			Naming.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}
  
	public static void startWindow() {
		SwingUtilities.invokeLater(() -> new ServerManagerController());
	}

}