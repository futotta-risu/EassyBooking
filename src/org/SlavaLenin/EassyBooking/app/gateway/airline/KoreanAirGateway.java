package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

import es.deusto.ingenieria.sd.sms.server.data.AirlineFlightDTO;
import es.deusto.ingenieria.sd.sms.server.remote.IAirlineManager;


/**
 * <strong>Pattern</strong>
 * <ul>
 * 		<li>Gateway</li>
 * </ul>
 */
public class KoreanAirGateway implements AirlineGateway {
	
	private IAirlineManager service = null;
	private String name =  "//" + "127.0.0.1" + ":" + 1099 + "/" + "KoreanAirline";
	
	public KoreanAirGateway(){
		try {
			service = (IAirlineManager) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		if(!DBManager.getInstance().hasAirline(AirlineEnum.KoreanAir)) {
			System.out.println("Saved KoreanAir");
			Airline koreanAir = new Airline("KoreanAir", AirlineEnum.KoreanAir);
			DBManager.getInstance().storeAirline(AirlineEnum.KoreanAir, koreanAir);
			System.out.println("Saved Not Saved");
		}
	}
	
	@Override
	public void reservar(String flightID) {
		// TODO Maybe change exception handling
		try {
			service.reservar(flightID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Flight> buscar(String flightID) {
		Logger.getLogger(ServerManagerFrame.class.getName()).info("KoreanAirGateway: id search " + flightID);
		List<Flight> flights = new ArrayList<Flight>();
		try {
			List<AirlineFlightDTO> flightsDTO = service.buscar(flightID);
			Logger.getLogger(ServerManagerFrame.class.getName()).info("KoreanAirGateway: the search has returned "
					+ flightsDTO.size() + " flights." );
			for(AirlineFlightDTO flightDTO : flightsDTO) {
				Flight f = new Flight();
				f.setFlightNumber(flightDTO.getFligthNumber());
				f.setDateDeparture(flightDTO.getDateDeparture());
				f.setDateArrival(flightDTO.getDateArrival());
				f.setTotalSeats(flightDTO.getFligthNumber());
				f.setAirline(AirlineEnum.KoreanAir.getCode());
				f.setAirportDeparture(flightDTO.getAirportDeparture());
				f.setAirportArrival(flightDTO.getAirportArrival());
				f.setPrice(flightDTO.getPrice());
				flights.add(f);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("Fligth size is: " + flights.size());
		return flights;
	}
	
	@Override
	public Flight buscarVuelo(String flightID) {
		Flight f = null;
		try {
			AirlineFlightDTO flightDTO = service.buscarVuelo(flightID);
			
			f = new Flight();
			
			f.setFlightNumber(flightDTO.getFligthNumber());
			f.setDateDeparture(flightDTO.getDateDeparture());
			f.setDateArrival(flightDTO.getDateArrival());
			f.setTotalSeats(flightDTO.getFligthNumber());
			f.setAirline(AirlineEnum.KoreanAir.getCode());
			f.setAirportDeparture(flightDTO.getAirportDeparture());
			f.setAirportArrival(flightDTO.getAirportArrival());
			f.setPrice(flightDTO.getPrice());
			System.out.println("The flight search has finished: "+ f);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return f;
		
	}

	@Override
	public void cancelReservation(String flightID) {
		// TODO Auto-generated method stub
		try {
			service.cancelReservation(flightID);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
