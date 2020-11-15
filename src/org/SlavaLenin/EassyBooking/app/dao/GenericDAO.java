package org.SlavaLenin.EassyBooking.app.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;



public class GenericDAO {

	public static PersistenceManagerFactory pmf = null;

	public GenericDAO() {
		if(pmf == null)
			pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	
	
	
	
}
