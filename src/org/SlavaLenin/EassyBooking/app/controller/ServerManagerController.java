package org.SlavaLenin.EassyBooking.app.controller;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.SwingUtilities;

import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.services.LoginService;

public class ServerManagerController {

	
	public ServerManagerController() throws RemoteException {		
		new ServerManagerFrame(this);
	}
	
    public void login(String username, String password){
    	try {
			LoginService.getInstance().login(username, password);
			System.out.println("Todo salio a pedir de milhouse");
		} catch (LoginTypeNotFoundException e) {
			System.err.println("Error escogiendo el tipo de login");
			e.printStackTrace();
		}
    }
    
  
    public void exit(){
    	System.exit(0);
    }
    
    public static void main(String[] args) throws RemoteException { 
    	System.out.println("Comenzando el server");
    	SwingUtilities.invokeLater(new Runnable() {
    	    public void run() {
    	      // Here, we can safely update the GUI
    	      // because we'll be called from the
    	      // event dispatch thread
    	    	try {
					new ServerManagerController();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	  });
    	
    }
}
