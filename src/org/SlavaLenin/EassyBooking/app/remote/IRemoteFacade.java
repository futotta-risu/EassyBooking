package org.SlavaLenin.EassyBooking.app.remote;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;

/**
 * <strong> IRemoteFaçade </strong>
 * Interface remota que contiene los metodos que dan funcionalidad a nuestro servidor EassyBookin.
 * @see RemoteFacade
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Façade</li>
 *	</ul>
 */
public interface IRemoteFacade extends Remote{
	
	public UserDTO login(String email, String password, LoginEnum loginType) throws RemoteException;
	public void register(String email, String password, LoginEnum registerType)throws RemoteException;
	public List<FlightDTO> buscarVuelo(String id) throws RemoteException;
	
	/**
	 * Flight booking method.
	 * 
	 * @param flightID General ID of the flight
	 * @param username Username of the petitioner user
	 * @param sessionKey SessionKey of the client to verify his identity
	 * 
	 * @throws RemoteException
	 */
	public void bookFlight(String flightID, String username, String sessionKey, int amount) throws RemoteException;
}
