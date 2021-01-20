package org.SlavaLenin.EassyBooking.app.remote;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;

/**
 * Remote interface that contains the methods that give functionality to our EassyBooking server.
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Facade</li>
 *	</ul>
 *
 * @see {@link RemoteFacade}
 */
public interface IRemoteFacade extends Remote{
	
	/**
	 * Login function.
	 * 
	 * @param email User email.
	 * @param password User password.
	 * @param loginType {@link LoginEnum} type based on the external server used.
	 * @return UserDTO of the user if correctly logged, null otherwise.
	 * @throws RemoteException
	 */
	public UserDTO login(String email, String password, LoginEnum loginType) throws RemoteException;
	
	/**
	 * Register function.
	 * 
	 * @param email User email.
	 * @param password User password.
	 * @param registerType {@link LoginEnum} type based on the external server used.
	 * @return UserDTO of the user if correctly registered, null otherwise.
	 * @throws RemoteException
	 */
	public void register(String email, String password, LoginEnum registerType)throws RemoteException;
	
	/**
	 * Flight search function.
	 * @param flightID ID of the flight to be searched
	 * @return List of flights which hold the criteria
	 * @throws RemoteException
	 */
	public List<FlightDTO> buscarVuelo(String flightID) throws RemoteException;
	
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
