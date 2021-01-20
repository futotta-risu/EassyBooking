package org.SlavaLenin.EassyBooking.app.remote;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.services.AirlineService;
import org.SlavaLenin.EassyBooking.app.services.LoginService;

/**
 *  This class implements the interface {@link IRemoteFacade} of our remote facade.
 *  
 * <br>
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Facade</li>
 * 		<li>Singleton</li>
 *	</ul>
 *
 */
public class RemoteFacade extends UnicastRemoteObject implements IRemoteFacade{

	private static final long serialVersionUID = 1L;
	private static RemoteFacade instance;
	
	private RemoteFacade() throws RemoteException{
		
		super();
	}
	
	public static RemoteFacade getInstance() {
		if (instance == null)
			try {
				instance = new RemoteFacade();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		return instance;
	}

	public UserDTO login(String email, String password, LoginEnum loginType) throws RemoteException {
		return LoginService.getInstance().login(email, password, loginType);
	}

	public void register(String email, String password, LoginEnum registerType) throws RemoteException {
		LoginService.getInstance().register(email, password, registerType);
	}


	public void bookFlight(String id, String username, String userKey, int amount) throws RemoteException {
		for(int i = 0; i < amount; i++)
			try {
				AirlineService.getInstance().reservar(id,  username, userKey);
			} catch (Exception e) {
				System.err.println("Error durante el pago");
				break;
			}
	}

	public List<FlightDTO> buscarVuelo(String flightID) throws RemoteException {
		return FlightAssembler.getInstance().assemble(AirlineService.getInstance().buscarVuelo(flightID));
	}

}
