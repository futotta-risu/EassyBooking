package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.login.GoogleGateway;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginGateway;

class LoginTypeNotFoundException extends Exception {
	public LoginTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}


public class LoginGatewayFactory {
	public LoginGateway create(LoginEnum loginType) throws LoginTypeNotFoundException{
		switch(loginType) {
		case Google:
			return new GoogleGateway();
		default:
			throw new LoginTypeNotFoundException("The Login Method: " + loginType.toString() + " isn't implemented");
		}
	}
}
