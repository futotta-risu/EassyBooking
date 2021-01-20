package org.SlavaLenin.EassyBooking.app.services;


import java.util.Calendar;
<<<<<<< HEAD
=======
import java.util.logging.Level;
>>>>>>> parent of 76e49f5... Merge branch 'main' of https://github.com/futotta-risu/EassyBooking into main
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
<<<<<<< HEAD
import org.SlavaLenin.EassyBooking.app.gateway.PaymentGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;
=======
import org.SlavaLenin.EassyBooking.app.gui.ServerManagerFrame;

>>>>>>> parent of 76e49f5... Merge branch 'main' of https://github.com/futotta-risu/EassyBooking into main

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
	
<<<<<<< HEAD
	/**
	 * This method gets the user from the db and creates a gateway depending of the payment method that the user
	 * have configured. Finally the Payment is stored in the db.
	 * @param username Name of user that is paying for the flight
	 * @param amount Price that is payed for the flight
	 */
	public void pay(String username, int amount) throws Exception{
		Logger logger = ServerLogger.getLogger();
		User user = DBManager.getInstance().getUser(username);
		
		PaymentEnum paymentType = user.getPaymentMethod().getPaymentType();
		try{
			PaymentGatewayFactory.getInstance().create(paymentType).pay(username, amount);
		}catch(Exception e) {
			logger.info("Payment error happened. Probably since not enought balance. Check your account.");
			throw new Exception("Payment not fullfilled");
		}
		
		
		Pago pago=new Pago();
		pago.setDate(Calendar.getInstance().getTime());
		pago.setExtraInfo(String.valueOf(amount));
		DBManager.getInstance().storePago(pago);
=======
	public void pay(int amount){
		logger.log(Level.INFO,"Cantidad a pagar: "+ amount);
		String confCode = contador + 23572037886777807L + "";
		Calendar c = Calendar.getInstance();
		Pago pago = new Pago( c.getTime(), String.valueOf(contador), confCode);
		contador++;
>>>>>>> parent of 76e49f5... Merge branch 'main' of https://github.com/futotta-risu/EassyBooking into main
		
		if(pago != null) {
			DBManager.getInstance().storePago(pago);
			logger.log(Level.INFO,"Pago guardado");
		}

	}
	
	
	
	
	
	

}
