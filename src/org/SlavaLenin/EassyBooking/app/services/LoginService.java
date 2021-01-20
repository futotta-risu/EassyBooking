package org.SlavaLenin.EassyBooking.app.services;

import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.LoginGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;

public class LoginService {
	
	private static LoginService instance;
	
	private LoginService(){}
	
	public static LoginService getInstance() {
		if(instance == null )
			instance = new LoginService();
		
		return instance;
	}
	
	
	public UserDTO login(String email, String password, LoginEnum loginType){
		Logger logger = ServerLogger.getLogger();
		logger.info("Iniciando login");
		
		
		User user = LoginGatewayFactory.getInstance().create(loginType).login(email, password);
		logger.info("Hemos recivido el user del gateway " + user);
		
		if(user == null) return null;
		
		user = DBManager.getInstance().getUser(user.getUsername());
		logger.info("Hemos recivido el user del db " + user);
		
		if(user == null) return null;

		UserAssembler userAssembler = new UserAssembler();
		return userAssembler.assemble(user);
	}
	
	public UserDTO register(String email, String password, LoginEnum registerType) {
		Logger logger = ServerLogger.getLogger();
		logger.info("Iniciando login");
		
		User user = LoginGatewayFactory.getInstance().create(registerType).login(email, password);
		logger.info("Hemos recivido el user del gateway " + user);
		
		if(user == null) return null;
		
		User dbUser = DBManager.getInstance().getUser(user.getUsername());
		logger.info("Hemos recivido el user del db " + dbUser);
		
		if(dbUser == null) {
			DBManager.getInstance().storeUser(user);
			logger.info("Hemos almacenado el usuario");
			user = DBManager.getInstance().getUser(email);
			logger.info("Hemos recuperado el " + user);
		}
		
		UserAssembler userAssembler = new UserAssembler();
		return userAssembler.assemble(user);

	}

	
}
