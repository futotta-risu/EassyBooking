package org.SlavaLenin.EassyBooking.app.gateway.exceptions;

class LoginTypeNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginTypeNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}