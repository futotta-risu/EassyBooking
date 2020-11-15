package org.SlavaLenin.EassyBooking.app.payments;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.SlavaLenin.EassyBooking.app.PaymentMethod;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Paypal  extends PaymentMethod{
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}
}
