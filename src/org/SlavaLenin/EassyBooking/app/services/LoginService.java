package org.SlavaLenin.EassyBooking.app.services;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBHandler;

public class LoginService {
	
	private static LoginService instance;
	
	private LoginService(){
	}
	
	public static LoginService getInstance() {
		if(instance == null ) {
			instance = new LoginService();
		}
		
		return instance;
	}
	
	public User login(String email, String password) {
		User user = DBHandler.getInstance().getUser(email);
		
		if(user!= null && user.chekPassword(password)) {
			return user;
		}else {
			return null;
		}
	}
	
	public void register(String email, String password) {
		User user = DBHandler.getInstance().getUser(email);
		
		if(user == null) {
			DBHandler.store(new User(email, password));
		}

	}

	public UserDTO getUser(String username) {
		return null;
		// TODO Auto-generated method stub
	}
	
}
