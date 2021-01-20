package org.SlavaLenin.EassyBooking.app.gateway.airline;


import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
import org.SlavaLenin.SocketAirline.socket.echo.server.data.SocketAirlineFlightDTO;


/**
 * <strong>Pattern</strong>
 * <ul>
 * 		<li>Gateway</li>
 * </ul>
 */
public class AirFranceGateway implements AirlineGateway {
	
	private static final String 	IP 		= 	"127.0.0.1";
	private static final int 		PORT 	= 	8001;

	public AirFranceGateway() {
		if(!DBManager.getInstance().hasAirline(AirlineEnum.AirFrance)) {
			System.out.println("Saved AirFrance");
			Airline koreanAir = new Airline("AirFrance", AirlineEnum.AirFrance);
			DBManager.getInstance().storeAirline(AirlineEnum.AirFrance, koreanAir);
		}
	}
	
	@Override
	public void reservar(String flightID){
		Logger logger = ServerLogger.getLogger();
		logger.info("Flight booking for id " + flightID);
		try {
			Socket tcpSocket = new Socket(IP, PORT);
			String address = tcpSocket.getInetAddress().getHostAddress();
			
			logger.info("- EchoClient: Sent data to '" + address);
		
		    ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
		    DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());
			
		    String command = "RESERVAR" + flightID; 
		     
			out.writeUTF(command);
			logger.info("- Sent data to '" + address + ":" + tcpSocket.getPort() + "' -> '" + command + "'");	
			
			String data = in.readUTF();			
			logger.info("- Received data from '" + address + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		
		}catch (Exception e) {
			logger.info("# EchoClient: Error: " + e.getMessage());
		}
		
	}

	@Override
	public List<Flight> buscar(String id) {
		Logger logger = ServerLogger.getLogger();
		logger.info("AirFranceGateway: buscar con " + id);
		
		List<Flight> flights = new ArrayList<Flight>();
		try{
			
			Socket tcpSocket = new Socket(IP, PORT);
		
			//Streams to send and receive information are created from the Socket
			ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());
			logger.info("Connection with AirFrance");
			
			String command = "BUSCAR" + id;
			out.writeUTF(command);
			
			System.out.println("- EchoClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> ' " + command + "'");	
			@SuppressWarnings("unchecked")
			List<SocketAirlineFlightDTO> dataDTO = (List<SocketAirlineFlightDTO>) in.readObject();
			System.out.println("AirFrance has recived:  " + dataDTO.size());
			for(SocketAirlineFlightDTO flightDTO : dataDTO) {
				Flight f = new Flight();
				// TODO Maybe set an adapter for the SocketAirlineFlightDTO -> Flight 
				f.setFlightNumber(flightDTO.getFligthNumber());
				f.setDateDeparture(flightDTO.getDateDeparture());
				f.setDateArrival(flightDTO.getDateArrival());
				f.setTotalSeats(flightDTO.getFligthNumber());
				f.setAirline(AirlineEnum.AirFrance.getCode());
				f.setAirportDeparture(flightDTO.getAirportDeparture());
				f.setAirportArrival(flightDTO.getAirportArrival());
				f.setPrice(flightDTO.getPrice());
				flights.add(f);
			}
			
			return flights;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("# EchoClient: Error: " + e.getMessage());
		}		
		
		return flights;
	}

	@Override
	public Flight buscarVuelo(String id) {
		Logger.getLogger(ServerManagerFrame.class.getName()).info("AirFranceGateway: buscar con " + id);
		System.out.println("AirFrance se esta ejecuntando ");
		List<Flight> flights = new ArrayList<Flight>();
		Flight f = new Flight();
		try{
			System.out.println("AirFrance se esta ejecuntando 2");
			Socket tcpSocket = new Socket(IP, PORT);
		
			//Streams to send and receive information are created from the Socket
			ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
			DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream());
			System.out.println("AirFrance se esta ejecuntando 3");
			out.writeUTF("BUSCARVUELO "+ id);
			
			System.out.println("- EchoClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> 'BUSCAR " + id + "'");	
			@SuppressWarnings("unchecked")
			SocketAirlineFlightDTO flightDTO = (SocketAirlineFlightDTO) in.readObject();
			System.out.println("AirFrance ha recivido " + flightDTO);
			
			
			f.setFlightNumber(flightDTO.getFligthNumber());
			f.setDateDeparture(flightDTO.getDateDeparture());
			f.setDateArrival(flightDTO.getDateArrival());
			f.setTotalSeats(flightDTO.getFligthNumber());
			f.setAirline(AirlineEnum.AirFrance.getCode());
			f.setAirportDeparture(flightDTO.getAirportDeparture());
			f.setAirportArrival(flightDTO.getAirportArrival());
			f.setPrice(flightDTO.getPrice());
			flights.add(f);
			System.out.println("El Airline es " + f.getAirline());
			
			System.out.println("AirFrance: Hemos obtenido" + f);
			
		}catch (Exception e) {
			f = null;
			e.printStackTrace();
			System.out.println("# EchoClient: Error: " + e.getMessage());
		}		
		
		return f;
	}

}
