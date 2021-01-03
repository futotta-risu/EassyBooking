package org.SlavaLenin.EassyBooking.app.gateway;

import org.SlavaLenin.EassyBooking.app.gateway.payment.CreditCardGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentGateway;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaypalGateway;

class PaymentTypeNotFoundException extends Exception {
	public PaymentTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}


public class PaymentGatewayFactory {
	public PaymentGateway create(PaymentEnum paymentType) throws PaymentTypeNotFoundException{
		switch(paymentType) {
		case Paypal:
			return new PaypalGateway();
		case CreditCard:
			return new CreditCardGateway();
		default:
			throw new PaymentTypeNotFoundException("The Payment Method: " + paymentType.toString() + " isn't implemented");
		}
	}
}
