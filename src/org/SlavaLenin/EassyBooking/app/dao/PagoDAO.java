package org.SlavaLenin.EassyBooking.app.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.SlavaLenin.EassyBooking.app.data.Flight;
import org.SlavaLenin.EassyBooking.app.data.Pago;

public class PagoDAO extends GenericDAO{
	public PagoDAO() {
		super();
	}
	
	public void storePago(Pago pago) {
		PersistenceManager pm = pmf.getPersistenceManager();
	    Transaction tx=pm.currentTransaction();

		try{
	        tx.begin();
	        pm.makePersistent(pago);
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
	
	public List<Pago> getPagos() {
			PersistenceManager pm = this.pmf.getPersistenceManager();
			/*
			 * By default only 1 level is retrieved from the db so if we wish to fetch more
			 * than one level, we must indicate it
			 */
			pm.getFetchPlan().setMaxFetchDepth(3);

			Transaction tx = pm.currentTransaction();
			List<Pago> products = new ArrayList<>();

			try {
				System.out.println("   * Retrieving an Extent for Pago.");

				tx.begin();
				Extent<Pago> extent = pm.getExtent(Pago.class, true);

				for (Pago product : extent) {
					products.add((Pago) pm.detachCopy(product));
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
	
	public Pago getPago(String id_pago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.getFetchPlan().setMaxFetchDepth(3);

		Transaction tx = pm.currentTransaction();
		Pago product = null;

		try {
			System.out.println("   * Querying a Pago: " + id_pago);

			tx.begin();
			Query<?> query = pm.newQuery("SELECT FROM " + Pago.class.getName() + " WHERE paymentID == " + id_pago );
			query.setUnique(true);
			product = (Pago) pm.detachCopy((Pago) query.execute());
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
	
	public void updateFlight(Pago pago) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx = pm.currentTransaction();

		try {
			System.out.println("   * Updating a Pago: " + pago);
			tx.begin();
			
			pm.makePersistent(pago);
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
