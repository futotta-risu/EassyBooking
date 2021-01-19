package org.SlavaLenin.EassyBooking.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.data.FlightReservation;


public class FlightReservationDAO extends GenericDAO{
	public FlightReservationDAO() {
		super();
	}
	
	public static void storeFlightReservation(FlightReservation flightreservation) {
		PersistenceManager pm = getPMF().getPersistenceManager();
	    Transaction tx=pm.currentTransaction();

		try{
	        tx.begin();
	        pm.makePersistent(flightreservation);
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
	
	public static List<FlightReservation> getFlightReservations() {
			PersistenceManager pm = getPMF().getPersistenceManager();

			Transaction tx = pm.currentTransaction();
			List<FlightReservation> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for FlightReservation.");

				tx.begin();
				Extent<FlightReservation> extent = pm.getExtent(FlightReservation.class, true);
				
				for (FlightReservation product : extent) 
					products.add((FlightReservation) pm.detachCopy(product));
				

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
	
	public static FlightReservation getFlightReservation(String flightReservationID) {
		PersistenceManager pm = getPMF().getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		FlightReservation product = null;

		try {
			System.out.println("   * Querying a FlightReservation: " + flightReservationID);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightReservationID );
			query.setUnique(true);
			
			product = (FlightReservation) pm.detachCopy((FlightReservation) query.execute());
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
	
	public static void updateFlightReservation(FlightReservation flightreservation) {
		PersistenceManager pm = getPMF().getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Updating a FlightReservation: " +flightreservation);
			tx.begin();
			pm.makePersistent(flightreservation);
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
	
	
	public static void deleteFlightReservation(String flightReservationID) {
		PersistenceManager pm = getPMF().getPersistenceManager();

		Transaction tx = pm.currentTransaction();
		FlightReservation flightReservation = null;

		try {
			System.out.println("   * Querying a FlightReservation: " + flightReservationID);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightReservationID );
			query.setUnique(true);
			
			flightReservation = (FlightReservation) pm.detachCopy((FlightReservation) query.execute());
			flightReservation.removeFlight();
			pm.deletePersistent(flightReservation);
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
	
	public static void deleteAllFlightReservations() {
		System.out.println("- Cleaning the DB...");
		PersistenceManager pm = getPMF().getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		List<String> IDs = new ArrayList<String>();
		try {
			tx.begin();
			
			Extent<FlightReservation> extentB = pm.getExtent(FlightReservation.class, true);
			
			for (FlightReservation b : extentB) {
				IDs.add(String.valueOf(b.getFlightReservationID()));
			}
			
			tx.commit();
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			pm.close();
		}
		for(String id : IDs)
			deleteFlightReservation(id);
	}
	
}
