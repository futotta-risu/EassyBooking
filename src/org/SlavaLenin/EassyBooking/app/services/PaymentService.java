package org.SlavaLenin.EassyBooking.app.services;


import java.util.Calendar;
import java.util.logging.Logger;

import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.db.DBManager;
import org.SlavaLenin.EassyBooking.app.gateway.PaymentGatewayFactory;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.log.ServerLogger;

/**
 * Application Service for Payment Services
 * </br>
 * 
 * <strong>Patterns</strong>
 * <ul>
 * 		<li>Singleton</li>
 * 		<li>Application Service</li>
 *	</ul>
 */
public class PaymentService {

	private static PaymentService instance;
		
	private PaymentService(){
	}
	
	public static PaymentService getInstance() {
		if(instance == null ) {
			instance = new PaymentService();
		}
		return instance;
	}
	
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
		
	}
	
	
	
	
	
	

}
