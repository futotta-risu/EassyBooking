package org.SlavaLenin.EassyBooking.app.gateway.login;

import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;

public interface LoginGateway {
	public UserDTO login(String email, String password);
	public void register(String email, String password);
}
