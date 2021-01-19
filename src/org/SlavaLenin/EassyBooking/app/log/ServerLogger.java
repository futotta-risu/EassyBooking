package org.SlavaLenin.EassyBooking.app.log;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

public class ServerLogger {
	private final static Logger LOGGER = Logger.getLogger(ServerLogger.class.getName());
	
	public static void setLoggerHandler() {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("log/eassybooking/eassybookingserver");
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		LOGGER.addHandler(fileHandler);
	}
	
	public static Logger getLogger() {
		return LOGGER;
	}
	
}
