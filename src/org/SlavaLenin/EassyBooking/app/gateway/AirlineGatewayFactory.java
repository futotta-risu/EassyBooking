package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirFranceGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.KoreanAirGateway;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;



public class AirlineGatewayFactory {
	public static AirlineGateway create(AirlineEnum airlineType) throws AirlineTypeNotFoundException{
		switch(airlineType) {
		case KoreanAir:
			return new KoreanAirGateway();
		case AirFrance:
			return new AirFranceGateway();
		default:
			throw new AirlineTypeNotFoundException("The Login Method: " + airlineType.toString() + " isn't implemented");
		}
	}
	
}

