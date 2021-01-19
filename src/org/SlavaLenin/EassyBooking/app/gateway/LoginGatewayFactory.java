package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.login.GoogleGateway;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginGateway;


public class LoginGatewayFactory {
	
	private static LoginGatewayFactory instance;
	
	private LoginGatewayFactory() {
		super();
	}
	
	public static LoginGatewayFactory getInstance() {
		if (instance == null) 
			instance = new LoginGatewayFactory();
		return instance;
	}
	
	/** 
	 * Creates the LoginGateway object.
	 * 
	 * The default gateway is Google.
	 * 
	 * @param loginType LoginEnum
	 */
	public LoginGateway create(LoginEnum loginType) {
		switch(loginType) {
		case Google:
			return new GoogleGateway();
		default:
			return new GoogleGateway();
		}
	}
}
