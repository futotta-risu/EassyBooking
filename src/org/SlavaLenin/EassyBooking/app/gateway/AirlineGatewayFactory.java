package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirFranceGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.KoreanAirGateway;

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

