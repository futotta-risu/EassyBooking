package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.login.GoogleGateway;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginEnum;
import org.SlavaLenin.EassyBooking.app.gateway.login.LoginGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentGateway;

class AirlineTypeNotFoundException extends Exception {
	public AirlineTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}


public class AirlineGatewayFactory {
	public AirlineGateway create(AirlineEnum airlineType) throws LoginTypeNotFoundException{
		switch(airlineType) {
		case KoreanAir:
			return new KoreanAirGateway();
		case AirFrance:
			return new AirFranceGateway();
		default:
			throw new LoginTypeNotFoundException("The Login Method: " + airlineType.toString() + " isn't implemented");
		}
	}
	
}



public class LoginGatewayFactory {
	
}
