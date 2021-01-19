package org.SlavaLenin.EassyBooking.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.data.User;

public class UserDAO extends GenericDAO{
	public UserDAO() {
		super();
	}
	
	public static void storeUser(User user) {
		PersistenceManager pm = getPMF().getPersistenceManager();
	    Transaction tx=pm.currentTransaction();

		try{
			System.out.println("JOJJOJOJ1");
			System.out.println(user);
	        tx.begin();
	        System.out.println("JOJJOJOJ2");
	        pm.makePersistent(user);
	        System.out.println("JOJJOJOJ3");
	        tx.commit();
	        System.out.println("JOJJOJOJ4");
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    finally{
	        if (tx.isActive()){
	            tx.rollback();
	        }
	        pm.close();
	    }
	}
	
	public static List<User> getUsers() {
			PersistenceManager pm = getPMF().getPersistenceManager();
			/*
			 * By default only 1 level is retrieved from the db so if we wish to fetch more
			 * than one level, we must indicate it
			 */
			pm.getFetchPlan().setMaxFetchDepth(3);

			Transaction tx = pm.currentTransaction();
			List<User> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for User.");

				tx.begin();
				Extent<User> extent = pm.getExtent(User.class, true);

				for (User product : extent) {
					products.add((User) pm.detachCopy(product));
				}

				tx.commit();
			} catch (Exception ex) {
				System.out.println("   $ Error retrieving an extent: " + ex.getMessage());
			} finally {
				if (tx != null && tx.isActive()) {
					tx.rollback();
				}

				pm.close();
			}

			return products;
		
	}
	
	public static User getUser(String username) {
		PersistenceManager pm = getPMF().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		User user = null;

		try {
			System.out.println("   * Querying a User: " + username);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + username +"'");
			query.setUnique(true);
			user = (User) pm.detachCopy((User) query.execute());
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {

			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
		return user;
	}
	
	public static void updateUser(User user) {
		PersistenceManager pm = getPMF().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			tx.begin();
			pm.makePersistent(user);
			tx.commit();
		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}
	}
}
