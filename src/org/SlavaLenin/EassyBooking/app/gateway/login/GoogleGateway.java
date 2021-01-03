package org.SlavaLenin.EassyBooking.app.gateway.login;

import org.SlavaLenin.EassyBooking.app.data.User;

public class GoogleGateway implements LoginGateway {

	@Override
	public User login(String email, String password) {
		System.out.println("Logeandose con google");
		return null;
	}

	@Override
	public boolean register(String email, String password) {
		System.out.println("Registrandose con Google");
		return false;
	}

}
