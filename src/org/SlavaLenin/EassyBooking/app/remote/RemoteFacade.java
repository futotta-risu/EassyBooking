package org.SlavaLenin.EassyBooking.app.remote;

import java.rmi.server.UnicastRemoteObject;

public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	

	public boolean login(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public void register(String email, String password) {
		// TODO Auto-generated method stub
		
	}

	public List<FlightDTO> buscarvuelo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean reservarVuelo(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
