package org.SlavaLenin.EassyBooking.app.payments;

import org.SlavaLenin.EassyBooking.app.PaymentMethod;

public class Paypal  extends PaymentMethod{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}
}
