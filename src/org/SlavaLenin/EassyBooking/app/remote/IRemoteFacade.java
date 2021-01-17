package org.SlavaLenin.EassyBooking.app.remote;

import java.util.List;

import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;

public interface IRemoteFacade {

public void login(String email, String password) throws LoginTypeNotFoundException;
public void register(String email, String password);
public List<FlightDTO> buscarVuelo(String id) throws AirlineTypeNotFoundException;
public void reservarVuelo(String id, AirlineEnum airline) throws AirlineTypeNotFoundException;
}
