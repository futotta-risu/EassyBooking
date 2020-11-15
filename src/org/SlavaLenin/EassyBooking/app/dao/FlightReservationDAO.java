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
	
	public void storeFlight(FlightReservation flightreservation) {
		this.storeObject(flightreservation);
	}
	
	public List<FlightReservation> getFlights() {
			PersistenceManager pm = this.pmf.getPersistenceManager();
			/*
			 * By default only 1 level is retrieved from the db so if we wish to fetch more
			 * than one level, we must indicate it
			 */
			pm.getFetchPlan().setMaxFetchDepth(3);

			Transaction tx = pm.currentTransaction();
			List<FlightReservation> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for Products.");

				tx.begin();
				Extent<FlightReservation> extent = pm.getExtent(FlightReservation.class, true);

				for (FlightReservation product : extent) {
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
			System.out.println("   * Querying a Product: " + flightNumber);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + FlightReservation.class.getName() + " WHERE flightReservationID == " + flightNumber );
			query.setUnique(true);
			product = (FlightReservation) query.execute();
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
	
	public void updateFlight(FlightReservation flightreservation) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
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
}
