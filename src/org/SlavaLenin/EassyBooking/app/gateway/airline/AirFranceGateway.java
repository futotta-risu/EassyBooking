package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

public class AirFranceGateway implements AirlineGateway {

	public AirFranceGateway() {
		if(!DBManager.getInstance().hasAirline(AirlineEnum.AirFrance.getCode())) {
			Airline koreanAir = new Airline("AirFrance", AirlineEnum.AirFrance.getCode());
			DBManager.getInstance().storeAirline(AirlineEnum.AirFrance.getCode(), koreanAir);
		}
	}
	
	@Override
	public void reservar(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Flight> buscar(String id) {
		Logger.getLogger(ServerManagerFrame.class.getName()).info("AirFranceGateway: buscar con " + id);
		// TODO Auto-generated method stub
		return new ArrayList<Flight>();
	}

}
