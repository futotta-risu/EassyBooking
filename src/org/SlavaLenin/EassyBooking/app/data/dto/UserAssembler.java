package org.SlavaLenin.EassyBooking.app.data.dto;

import org.SlavaLenin.EassyBooking.app.data.User;
import java.io.Serializable;

public class UserAssembler implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static UserAssembler instance;
	
	public UserAssembler() {
		super();	
	}
	
	public static UserAssembler getInstance() {
		if(instance == null ) {
			instance = new UserAssembler();
		}
		return instance;
	}

	public UserDTO assemble (User user) {
		
		UserDTO result = new UserDTO(user.getUsername(), user.getName());
		return result;
		
	}

	
}
