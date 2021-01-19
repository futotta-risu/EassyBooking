package org.SlavaLenin.EassyBooking.app.dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;



public class GenericDAO {
	
	private static PersistenceManagerFactory pmf = null;
	
	public static PersistenceManagerFactory getPMF() {
		if(pmf == null)
			pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		return pmf;
	}

	public GenericDAO() {
		if(pmf == null)
			pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
	}
	
	
	
	
	
}
