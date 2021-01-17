package org.SlavaLenin.EassyBooking.app.gateway.exceptions;

public class AirlineTypeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public AirlineTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
