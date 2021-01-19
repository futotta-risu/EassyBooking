package org.SlavaLenin.EassyBooking.app.data.dto;

import org.SlavaLenin.EassyBooking.app.data.User;
import java.io.Serializable;

public class UserAssembler implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	public UserAssembler() {
		super();	
	}

	public UserDTO assemble(User user) {
		return new UserDTO(user.getUsername(), user.getSessionKey());
	}

	
}
