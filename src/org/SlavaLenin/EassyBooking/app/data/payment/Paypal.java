package org.SlavaLenin.EassyBooking.app.data.payment;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.SlavaLenin.EassyBooking.app.data.PaymentMethod;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class Paypal  extends PaymentMethod{
	private String username;

	public Paypal() {
		this.paymentType = PaymentEnum.Paypal;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		
		this.username = username;
	}
}
