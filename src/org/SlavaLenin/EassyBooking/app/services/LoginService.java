package org.SlavaLenin.EassyBooking.app.services;


import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserAssembler;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.LoginGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;


/**
 * Application Service for Login Services
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Application Service</li>
 *	</ul>
 */
public class LoginService {
	
	private static LoginService instance;
	
	private LoginService(){}
	
	public static LoginService getInstance() {
		if(instance == null )
			instance = new LoginService();
		
		return instance;
	}
	
	
	/**
	 * This method creates a gateway depending of the loginType and calls the login function of the gateway.
	 * If the user returned from the gateway is not null we will pick the user from the db.
	 * @param email Email of the user
	 * @param password Password of the user
	 * @param loginType Login type that the user wants to use
	 * @return UserDTO object that contains basic info about the loged user
	 */
	public UserDTO login(String email, String password, LoginEnum loginType){
		Logger logger = ServerLogger.getLogger();
		logger.info("Iniciando login");
		
		
		User user = LoginGatewayFactory.getInstance().create(loginType).login(email, password);
		logger.info("Hemos recivido el user del gateway " + user);
		
		if(user == null) return null;
		
		user = DBManager.getInstance().getUser(user.getUsername());
		logger.info("Hemos recivido el user del db " + user);
		
		if(user == null) return null;

		return UserAssembler.getInstance().assemble(user);
	}
	
	
	/**
	 * This method creates a gateway depending of the loginType and calls the login function of the gateway.
	 * If the user is not null we will try to pick that user from the db and if it is null again, the user will be stored in the db.
	 * @param email Email of the user
	 * @param password Password of the user
	 * @param registerType Register type that the user wants to use
	 * @return UserDTO object that contains basic info about the loged user
	 */
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
		return UserAssembler.getInstance().assemble(user);

	}

	
}
