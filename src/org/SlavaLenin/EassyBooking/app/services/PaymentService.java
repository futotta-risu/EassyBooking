package org.SlavaLenin.EassyBooking.app.services;

import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;


/**
 * Application Service for Payment Services
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Application Service</li>
 *	</ul>
 *
 */
public class PaymentService {

	
	private static PaymentService instance;
	private static int contador = 0;
	private Logger logger = Logger.getLogger(ServerManagerFrame.class.getName());
		
	private PaymentService(){
	}
	
	public static PaymentService getInstance() {
		if(instance == null ) {
			instance = new PaymentService();
		}
		return instance;
	}
	
	public void pay(int amount){
		logger.log(Level.INFO,"Cantidad a pagar: "+ amount);
		String confCode = contador + 23572037886777807L + "";
		Calendar c = Calendar.getInstance();
		Pago pago = new Pago( c.getTime(), contador, confCode, "Info =" + contador);
		contador++;
		
		if(pago != null) {
			DBManager.getInstance().storePago(pago);
			logger.log(Level.INFO,"Pago guardado");
		}

	}
	
	
	
	
	
	

}
