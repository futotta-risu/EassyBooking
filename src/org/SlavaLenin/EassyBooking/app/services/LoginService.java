package org.SlavaLenin.EassyBooking.app.services;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.LoginGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;

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
	
	
	public UserDTO login(String email, String password, LoginEnum loginType){
		System.out.println("LoginService: login");
		User user = LoginGatewayFactory.getInstance().create(loginType).login(email, password);
		if(user == null)
			return null;
		return UserAssembler.getInstance().assemble(user);
	}
	
	public UserDTO register(String email, String password, LoginEnum registerType) {
		// Si login bien, crear en db.
		User user = LoginGatewayFactory.getInstance().create(registerType).login(email, password);
		if(DBManager.getInstance().getUser(user.getUsername()) == null) {
			
			DBManager.getInstance().storeUser(user);
			user = DBManager.getInstance().getUser(email);
		}
		return UserAssembler.getInstance().assemble(user);

	}

	
}
