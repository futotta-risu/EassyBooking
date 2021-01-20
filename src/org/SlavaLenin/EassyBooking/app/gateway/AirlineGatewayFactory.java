package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.airline.AirFranceGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineGateway;
import org.SlavaLenin.EassyBooking.app.gateway.airline.KoreanAirGateway;


/**
 * <strong>Pattern</strong>
 * <ul>
 * 		<li>Factory</li>
 * </ul>
 */
public class AirlineGatewayFactory {
	
	private static AirlineGatewayFactory instance;
	
	private AirlineGatewayFactory() {
		super();
	}
	
	public AirlineGatewayFactory getInstance() {
		if (instance == null) 
			instance = new AirlineGatewayFactory();
		return instance;
	}
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

