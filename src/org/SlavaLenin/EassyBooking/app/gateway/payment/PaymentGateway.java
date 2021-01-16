package org.SlavaLenin.EassyBooking.app.gateway.payment;

public interface PaymentGateway {
	public void validateUser(String user, String key);
	public void pay(String user, String key, int amount);
}
