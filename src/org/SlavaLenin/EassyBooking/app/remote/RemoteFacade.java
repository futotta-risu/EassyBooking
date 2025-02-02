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
 *  <strong> Remote facade </strong>
 *  Esta clase implementa la interfaz de nuestra façade remota. Los metodos que encapsula esta clase son los siguientes:
 *  <ul>
 *  	<li> login(String email, String password, LoginEnum loginType) : UserDTO </li>
 *      <li> register(String email, String password, LoginEnum registerType) </li>
 *      <li> bookFlight(String id, String username, String userKey) </li>
 *      <li> buscarVuelo(String id, String username, String userKey) : List<FlightDTO/> </li>
 *  </ul>
 *  
 *  <strong>Patterns</strong>
 * <ul>
 * 		<li>Façade</li>
 * 		<li>Singleton</li>
 *	</ul>
 */
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
		UserDTO user = LoginService.getInstance().login(email, password, loginType);
		System.out.println(user);
		return user;
	}

	public void register(String email, String password, LoginEnum registerType) throws RemoteException {
		System.out.println(" *RemoteFacade REGISTER: " + email + "/" + password + "/" + registerType);
		LoginService.getInstance().register(email, password, registerType);
	}


<<<<<<< HEAD
	public void bookFlight(String id, String username, String userKey, int amount) throws RemoteException {
		for(int i = 0; i < amount; i++)
			try {
				AirlineService.getInstance().reservar(id,  username, userKey);
			} catch (Exception e) {
				System.err.println("Error durante el pago");
				break;
			}
=======
	public void bookFlight(String id, String username, String userKey) throws RemoteException {
		System.out.println("Fachada: Iniciando reserva del vuelo " + id + " del username " + username + " con la session " + userKey);
		AirlineService.getInstance().reservar(id,  username, userKey);
>>>>>>> parent of 25dcc85... Cambios
	}

	public List<FlightDTO> buscarVuelo(String id) throws RemoteException {
		System.out.println(" *RemoteFacade BUSCAR Vuelo: " + id);
		return FlightAssembler.getInstance().assemble(AirlineService.getInstance().buscarVuelo(id));
	}

}
