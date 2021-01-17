package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.login.GoogleGateway;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginGateway;

import org.SlavaLenin.EassyBooking.app.gateway.exceptions.*;



public class LoginGatewayFactory {
	public static LoginGateway create(LoginEnum loginType) throws LoginTypeNotFoundException{
		switch(loginType) {
		case Google:
			return new GoogleGateway();
		default:
			throw new LoginTypeNotFoundException("The Login Method: " + loginType.toString() + " isn't implemented");
		}
	}
}
