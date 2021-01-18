package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

public class AirFranceGateway implements AirlineGateway {

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
