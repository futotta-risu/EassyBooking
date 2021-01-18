package org.SlavaLenin.EassyBooking.app.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.gateway.airline.AirlineEnum;


public interface IRemoteFacade extends Remote{

public void login(String email, String password) throws RemoteException;
public void register(String email, String password)throws RemoteException;
public List<FlightDTO> buscarVuelo(String id) throws RemoteException;
public void reservarVuelo(String id, AirlineEnum airline) throws RemoteException;
}
