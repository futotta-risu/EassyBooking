package org.SlavaLenin.EassyBooking.app.gateway.login;

import org.SlavaLenin.EassyBooking.app.data.User;

public interface LoginGateway {
	public User login(String email, String password);
	public void register(String email, String password);
}
