package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.payment.CreditCardGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaypalGateway;



public class PaymentGatewayFactory {
	
	/** 
	 * Creates the PaymentGateway object.
	 * 
	 * The default gateway is Paypal.
	 * 
	 * @param paymentType PaymentEnum
	 */
	public PaymentGateway create(PaymentEnum paymentType){
		switch(paymentType) {
		case Paypal:
			return new PaypalGateway();
		case CreditCard:
			return new CreditCardGateway();
		default:
			return new PaypalGateway();
		}
	}
}
