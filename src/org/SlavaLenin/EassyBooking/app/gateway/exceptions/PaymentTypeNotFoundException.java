package org.SlavaLenin.EassyBooking.app.gateway.exceptions;

public class PaymentTypeNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public PaymentTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
