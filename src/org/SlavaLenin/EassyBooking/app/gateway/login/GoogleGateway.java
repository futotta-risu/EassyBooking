package org.SlavaLenin.EassyBooking.app.gateway.login;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;

import es.deusto.ingenieria.sd.sms.server.data.LoginUserDTO;
import es.deusto.ingenieria.sd.sms.server.remote.ILoginManager;

public class GoogleGateway implements LoginGateway {

	private String name =  "//" + "127.0.0.1" + ":" + 1079 + "/" + "Google";
	private ILoginManager service = null;
	
	public GoogleGateway() {
		try {
			service = (ILoginManager) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public UserDTO login(String email, String password) {
		try {
			LoginUserDTO loginUserDTO = service.login(email, password);
			// Falta parte del DBManager
			/*
			 * if(!DBManager.userExists(user))
			 * 		DBManager.createUser(loginUserDTO.email)
			 */
			
			// Opción 2: LoginUserDTO -> UserDTO
			/*
			 * return UserDTO(loginUserDTO.email,loginUserDTO.password)
			 */
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void register(String email, String password) {
		try {
			service.register(email, password);
		} catch (RemoteException e) {
			// TODO Send throw back
			e.printStackTrace();
		}
	}

}