package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirFranceGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.KoreanAirGateway;



public class AirlineGatewayFactory {
	
	/** 
	 * Creates the AirlineGateway object.
	 * 
	 * The default gateway is KoreanAir.
	 * 
	 * @param airlineType AirlineEnum
	 */
	public static AirlineGateway create(AirlineEnum airlineType){
		switch(airlineType) {
		case KoreanAir:
			return new KoreanAirGateway();
		case AirFrance:
			return new AirFranceGateway();
		default:
			return new KoreanAirGateway();
		}
	}
	
}

