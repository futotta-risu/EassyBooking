package org.SlavaLenin.EassyBooking.app.gateway.airline;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.AirlineFlightDTO;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;

public interface AirlineGateway {
	public boolean reservar(String id);
	public List<AirlineFlightDTO> buscar(String id);
}
