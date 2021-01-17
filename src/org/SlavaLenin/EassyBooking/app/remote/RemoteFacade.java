package org.SlavaLenin.EassyBooking.app.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;


public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	public User user = null;
	
	private RemoteFacade() throws RemoteException{
		super();
	}
	
	public static RemoteFacade getInstance() {
		if (instance == null) {
			try {
				instance = new RemoteFacade();
			} catch (Exception e) {
				System.err.println("# Error crando la RemoteFacade: " + e);
			}
		}
		return instance;
	}

	public void login(String email, String password) throws LoginTypeNotFoundException, RemoteException {
		System.out.println(" *RemoteFacade LOGIN: " + email + "/" + password);
		this.user = LoginService.getInstance().login(email, password);
	}

	public void register(String email, String password) throws RemoteException {
		System.out.println(" *RemoteFacade REGISTER: " + email + "/" + password);
		LoginService.getInstance().register(email, password);
	}


	public void reservarVuelo(String id, AirlineEnum airline) throws AirlineTypeNotFoundException , RemoteException {
		if (user != null) {
			System.out.println(" *RemoteFacade RESERVA de vuelo: " + id + "/" + airline);
			AirlineService.getInstance().reservar(id, airline);
		}
		
	}

	public List<FlightDTO> buscarVuelo(String id) throws AirlineTypeNotFoundException , RemoteException {
		System.out.println(" *RemoteFacade BUSCAR Vuelo: " + id);
		return FlightAssembler.assemble(AirlineService.getInstance().buscarVuelo(id));
	}

}
