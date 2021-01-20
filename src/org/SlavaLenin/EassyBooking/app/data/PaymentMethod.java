package org.SlavaLenin.EassyBooking.app.data;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;

@PersistenceCapable(detachable = "true")
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class PaymentMethod {
	protected PaymentEnum paymentType = null;
	
	private User user;
	
	public PaymentEnum getPaymentType() {
		return this.paymentType;
	}
}
