package org.SlavaLenin.EassyBooking.app.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;



public class GenericDAO {

	public static PersistenceManagerFactory pmf = null;

	public GenericDAO() {
		if(pmf == null)
			pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	
	
	
	
}
