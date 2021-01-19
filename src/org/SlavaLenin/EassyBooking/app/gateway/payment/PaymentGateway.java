package org.SlavaLenin.EassyBooking.app.gateway.payment;

public interface PaymentGateway {
	
	/**
	 * @param user 
	 * @param amount
	 */
	public void pay(String username, int amount);
}
