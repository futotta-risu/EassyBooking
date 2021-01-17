package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;

import es.deusto.ingenieria.sd.sms.server.remote.IAirlineManager;


public class KoreanAirGateway implements AirlineGateway {
	
	private IAirlineManager service = null;
	private String name =  "//" + "127.0.0.1" + ":" + 1099 + "/" + "KoreanAirline";
	
	public KoreanAirGateway(){
		try {
			service = (IAirlineManager) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void reservar(String id) {
		try {
			service.reservar(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Flight> buscar(String id) {
		try {
			List<AirlineFlightDTO> flightsDTOs = service.buscar(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
