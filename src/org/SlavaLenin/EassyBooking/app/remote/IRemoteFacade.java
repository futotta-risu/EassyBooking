package org.SlavaLenin.EassyBooking.app.remote;

public interface IRemoteFacade {

public boolean login(String email, String password);
public void register(String email, String password);
public List<FlightDTO> buscarVuelo(String id);
public boolean reservarVuelo(String id, AirlineEnum airline);
}
