package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

import es.deusto.ingenieria.sd.sms.server.data.AirlineFlightDTO;
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
		Logger.getLogger(ServerManagerFrame.class.getName()).info("KoreanAirGateway: buscar con " + id);
		List<Flight> flights = new ArrayList<Flight>();
		try {
			Logger.getLogger(ServerManagerFrame.class.getName()).info("KoreanAirGateway: buscando " + id);
			List<AirlineFlightDTO> flightsDTO = service.buscar(id);
			Logger.getLogger(ServerManagerFrame.class.getName()).info("KoreanAirGateway: busqueda acabada "
					+ "con un total de " + flightsDTO.size() + " vuelos" );
			for(AirlineFlightDTO flightDTO : flightsDTO) {
				Flight f = new Flight();
				f.setFlightNumber(flightDTO.getFligthNumber());
				f.setDateDeparture(flightDTO.getDateDeparture());
				f.setDateArrival(flightDTO.getDateArrival());
				f.setTotalSeats(flightDTO.getFligthNumber());
				flights.add(f);
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El tamaño de vuelos es de " + flights.size());
		return flights;
	}

}
