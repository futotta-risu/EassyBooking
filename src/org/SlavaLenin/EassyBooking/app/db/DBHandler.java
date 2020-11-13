package org.SlavaLenin.EassyBooking.app.db;

import java.util.Calendar;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.User;

public class DBHandler {

	public static Query<User> getUserFromUserName(String username){
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		PersistenceManager pm = pmf.getPersistenceManager();				
		Transaction transaction = pm.currentTransaction();	
		Calendar calendar = Calendar.getInstance();
		
		@SuppressWarnings("unchecked")
		Query<User> userQuery = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == Username1234");
	    
		return userQuery;
	}
}
