package org.SlavaLenin.EassyBooking.app.gateway.payment;

public class PaypalGateway implements PaymentGateway {

	@Override
	public boolean pay(int amount) {
		System.out.println("Conectando a Paypal..");
		return true;
	}

}
