package org.SlavaLenin.EassyBooking.app.services;

import org.SlavaLenin.EassyBooking.app.data.User;
import org.SlavaLenin.EassyBooking.app.data.dto.UserDTO;
import org.SlavaLenin.EassyBooking.app.db.DBManager;

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
		User user = DBManager.getInstance().getUser(email);
		
		if(user!= null && user.chekPassword(password)) {
			return user;
		}else {
			return null;
		}
	}
	
	public User register(String email, String password) {
		User user = DBManager.getInstance().getUser(email);
		
		if(user == null) {
			DBManager.store(new User(email, password));
		}

	}

	
}
