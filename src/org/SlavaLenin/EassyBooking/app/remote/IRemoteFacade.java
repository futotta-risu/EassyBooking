package org.SlavaLenin.EassyBooking.app.remote;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;

public interface IRemoteFacade {

public boolean login(String email, String password);
public void register(String email, String password);
public List<FlightDTO> buscarVuelo(String id);
public boolean reservarVuelo(String id, AirlineEnum airline);
}
