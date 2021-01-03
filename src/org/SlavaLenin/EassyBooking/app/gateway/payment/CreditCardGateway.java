package org.SlavaLenin.EassyBooking.app.gateway.payment;

public class CreditCardGateway implements PaymentGateway {

	@Override
	public boolean pay(int amount) {
		System.out.println("Conectandoa  Credit card...");
		return false;
	}

}
