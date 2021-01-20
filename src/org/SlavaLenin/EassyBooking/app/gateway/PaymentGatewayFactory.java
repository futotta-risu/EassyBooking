package org.SlavaLenin.EassyBooking.app.gateway;


import org.SlavaLenin.EassyBooking.app.gateway.payment.CreditCardGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaypalGateway;


/**
 * <strong>Pattern</strong>
 * <ul>
 * 		<li>Factory</li>
 * </ul>
 */
public class PaymentGatewayFactory {
	
	private static PaymentGatewayFactory instance;
	
	private PaymentGatewayFactory() {
		super();
	}
	
	public static PaymentGatewayFactory getInstance() {
		if (instance == null) 
			instance = new PaymentGatewayFactory();
		return instance;
	}
		
	
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
