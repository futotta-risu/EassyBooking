package org.SlavaLenin.EassyBooking.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;


import org.SlavaLenin.EassyBooking.app.data.Flight;


/**
 * <strong>Pattern</strong>
 * <ul>
 *      <li>DAO</li>
 * </ul>
 */
public class FlightDAO extends GenericDAO{

	public FlightDAO() {
		super();
	}
	
	public static void storeFlight(Flight flight) {
		System.out.println("Saving the flight");
		
		if(getFlight(String.valueOf(flight.getFlightID())) != null) {
			System.out.println("Flight Alredy in DB");
			return;
		}
		
		PersistenceManager pm = getPMF().getPersistenceManager();
	    Transaction tx=pm.currentTransaction();
	    
	    
		try{
	        tx.begin();
	        pm.makePersistent(flight);
	        tx.commit();
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
	
	public static List<Flight> getFlights() {
			PersistenceManager pm = getPMF().getPersistenceManager();
			pm.getFetchPlan().setMaxFetchDepth(3);

			Transaction tx = pm.currentTransaction();
			List<Flight> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for Flight.");

				tx.begin();
				Extent<Flight> extent = pm.getExtent(Flight.class, true);

				for (Flight product : extent) {
					products.add((Flight) pm.detachCopy(product));
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
	
	public static Flight getFlight(String flightID) {
		PersistenceManager pm = getPMF().getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Flight product = null;

		try {
			System.out.println("   * Querying a Flight: " + flightID);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Flight.class.getName() + " WHERE flightID == " + flightID );
			query.setUnique(true);
			product = (Flight) pm.detachCopy((Flight) query.execute());
			tx.commit();

		} catch (Exception ex) {
			System.out.println("   $ Error retreiving an extent: " + ex.getMessage());
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}

			pm.close();
		}

		return product;
	}
	
	public static void updateFlight(Flight flight) {
		PersistenceManager pm = getPMF().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Updating a Flight: " + flight);
			tx.begin();
			pm.makePersistent(flight);
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
	
	@Deprecated
	public void deleteAllFligths() {
		System.out.println("- Cleaning the DB...");
		PersistenceManager pm = getPMF().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();

			Query<Flight> query1 = pm.newQuery(Flight.class);
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			pm.close();
		}
	}
	
	
}
