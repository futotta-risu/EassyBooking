package org.SlavaLenin.EassyBooking.app.gateway.airline;


import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Airline;
import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.dto.FlightDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

public class AirFranceGateway implements AirlineGateway {
	
	private static final String IP = "127.0.0.1";
	private static final int PORT = 8001;

	public AirFranceGateway() {
		if(!DBManager.getInstance().hasAirline(AirlineEnum.AirFrance)) {
			Airline koreanAir = new Airline("AirFrance", AirlineEnum.AirFrance);
			DBManager.getInstance().storeAirline(AirlineEnum.AirFrance, koreanAir);
		}
	}
	
	@Override
	public void reservar(String id){
		try (Socket tcpSocket = new Socket(IP, PORT);
			 //Streams to send and receive information are created from the Socket
		     ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
			 ObjectOutputStream out = new ObjectOutputStream(tcpSocket.getOutputStream())){
			
			out.writeUTF("RESERVAR "+ id);
			
			System.out.println("- EchoClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> 'RESERVAR " + id + "'");	
			String data = in.readUTF();			
			System.out.println("- EchoClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");
		}catch (Exception e) {
			System.out.println("# EchoClient: Error: " + e.getMessage());
		}
		
	}

	@Override
	public List<Flight> buscar(String id) {
		Logger.getLogger(ServerManagerFrame.class.getName()).info("AirFranceGateway: buscar con " + id);
		try (Socket tcpSocket = new Socket(IP, PORT);
			//Streams to send and receive information are created from the Socket
			ObjectInputStream in = new ObjectInputStream(tcpSocket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(tcpSocket.getOutputStream())){
			
			out.writeUTF("BUSCAR "+ id);
			
			System.out.println("- EchoClient: Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> 'BUSCAR " + id + "'");	
			@SuppressWarnings("unchecked")
			List<Flight> data = (List<Flight>) in.readObject();
			return data;
		}catch (Exception e) {
			System.out.println("# EchoClient: Error: " + e.getMessage());
		}		
		
		return new ArrayList<Flight>();
	}

}
