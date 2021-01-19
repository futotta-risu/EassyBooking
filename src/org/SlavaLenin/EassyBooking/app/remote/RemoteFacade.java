package org.SlavaLenin.EassyBooking.app.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;


public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	
	private RemoteFacade() throws RemoteException{
		
		super();
	}
	
	public static RemoteFacade getInstance() {
		if (instance == null) {
			try {
				instance = new RemoteFacade();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public UserDTO login(String email, String password, LoginEnum loginType) throws RemoteException {
		System.out.println(" *RemoteFacade LOGIN: " + email + "/" + password + "/" + loginType);
		
		return LoginService.getInstance().login(email, password, loginType);
	}

	public void register(String email, String password, LoginEnum registerType) throws RemoteException {
		System.out.println(" *RemoteFacade REGISTER: " + email + "/" + password + "/" + registerType);
		LoginService.getInstance().register(email, password, registerType);
	}


	public void bookFlight(String id, String username, String userKey) throws RemoteException {
		
		AirlineService.getInstance().reservar(id,  username, userKey);
	}

	public List<FlightDTO> buscarVuelo(String id) throws RemoteException {
		System.out.println(" *RemoteFacade BUSCAR Vuelo: " + id);
		return FlightAssembler.assemble(AirlineService.getInstance().buscarVuelo(id));
	}

}
