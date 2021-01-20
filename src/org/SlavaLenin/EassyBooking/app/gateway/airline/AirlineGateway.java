package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;

public interface AirlineGateway {
	public void reservar(String flightID);
	public List<Flight> buscar(String flightID);
	public Flight buscarVuelo(String flightID);
	public void cancelReservation(String flightID);
}
