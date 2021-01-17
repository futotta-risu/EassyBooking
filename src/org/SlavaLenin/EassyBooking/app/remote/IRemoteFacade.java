package org.SlavaLenin.EassyBooking.app.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.AirlineTypeNotFoundException;
import org.SlavaLenin.EassyBooking.app.gateway.exceptions.LoginTypeNotFoundException;


public interface IRemoteFacade extends Remote{

public void login(String email, String password) throws LoginTypeNotFoundException, RemoteException;
public void register(String email, String password)throws RemoteException;
public List<FlightDTO> buscarVuelo(String id) throws AirlineTypeNotFoundException, RemoteException;
public void reservarVuelo(String id, AirlineEnum airline) throws AirlineTypeNotFoundException, RemoteException;
}
