package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;

public interface AirlineGateway {
	public void reservar(String id);
	public List<Flight> buscar(String id);
	public Flight buscarVuelo(String id);
}
