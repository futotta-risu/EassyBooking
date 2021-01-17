package org.SlavaLenin.EassyBooking.app.services;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.LoginGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginGateway;

public class LoginService {
	
	private static LoginService instance;
	
	private LoginService(){
	}
	
	public static LoginService getInstance() {
		if(instance == null ) {
			instance = new LoginService();
		}
		
		return instance;
	}
	
	
	public User login(String email, String password) throws LoginTypeNotFoundException{
		LoginGateway logingateway;
		logingateway = LoginGatewayFactory.create(LoginEnum.Google);
		
		UserDTO userDTO = logingateway.login(email, password);
		User user = new User(userDTO.getUsername());
		return user;
	}
	
	public User register(String email, String password) {
		User user = DBManager.getInstance().getUser(email);
		
		if(user == null) {
			user = new User(email);
			DBManager.getInstance().storeUser(user);
		}
		return user;

	}

	
}
