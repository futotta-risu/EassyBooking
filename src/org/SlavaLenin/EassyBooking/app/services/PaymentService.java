package org.SlavaLenin.EassyBooking.app.services;

import java.util.Calendar;

import org.SlavaLenin.EassyBooking.app.data.Pago;
import org.SlavaLenin.EassyBooking.app.db.DBManager;

public class PaymentService {

	
	private static PaymentService instance;
	private static int contador = 0;
	
		
	private PaymentService(){
	}
	
	public static PaymentService getInstance() {
		if(instance == null ) {
			instance = new PaymentService();
		}
		return instance;
	}
	
	public void pay(int amount){
		String confCode = contador + 23572037886777807L + "";
		Calendar c = Calendar.getInstance();
		Pago pago = new Pago( c.getTime(), contador, confCode, "Info =" + contador);
		contador++;
		
		if(pago != null) {
			DBManager.getInstance().storePago(pago);
		}

	}
	
	
	
	
	
	

}
