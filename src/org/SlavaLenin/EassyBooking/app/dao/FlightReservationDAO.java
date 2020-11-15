package org.SlavaLenin.EassyBooking.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.FlightReservation;


public class FlightReservationDAO extends GenericDAO{
	public FlightReservationDAO() {
		super();
	}
	
	public void storeFlightReservation(FlightReservation flightreservation) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx=pm.currentTransaction();

		try{
	        tx.begin();
	        System.out.println("*Guardando el flightReservation*");
	        pm.makePersistent(flightreservation);
	        tx.commit();
	    }
	    finally{
	        if (tx.isActive()){
	            tx.rollback();
	        }
	        pm.close();
	    }
	}
	
	public List<FlightReservation> getFlightReservations() {
			PersistenceManager pm = this.pmf.getPersistenceManager();

			Transaction tx = pm.currentTransaction();
			List<FlightReservation> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for FlightReservation.");

				tx.begin();
				Query<FlightReservation> extent = pm.newQuery("SELECT FROM " + FlightReservation.class.getName());
				System.out.println(extent.executeList().size());
				for (FlightReservation product : extent.executeList()) {
					products.add(product);
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
	
	public FlightReservation getProduct(String flightNumber) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		FlightReservation product = null;

		try {
			System.out.println("   * Querying a FlightReservation: " + flightNumber);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightNumber );
			query.setUnique(true);
			
			product = (FlightReservation) query.execute();
			tx.commit();
			System.out.println(" Imprimo esto porque sino no funciona" + product.getFlightReservationID());

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
	
	public void updateFlight(FlightReservation flightreservation) {
		PersistenceManager pm = pmf.getPersistenceManager();
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
	
	
	public void deleteAllFlightReservations() {
		System.out.println("- Cleaning the DB...");
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		
		try {
			tx.begin();
			
			Extent<FlightReservation> extentB = pm.getExtent(FlightReservation.class, true);
			
			for (FlightReservation b : extentB) {
				System.out.println(".........4");
				System.out.println(b.getFlightReservationID());
				System.out.println("--------");
				pm.deletePersistent(b);
				System.out.println(".........1");
			}
			System.out.println(" * 12.");
			
			tx.commit();
			System.out.println(" * 12.");
		} catch (Exception ex) {
			System.err.println(" $ Error cleaning the DB: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			if (tx != null && tx.isActive())
				tx.rollback();
			System.out.println(" * 14.");
			pm.close();
		}
	}
}
