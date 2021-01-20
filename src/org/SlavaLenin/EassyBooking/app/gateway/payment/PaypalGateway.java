package org.SlavaLenin.EassyBooking.app.gateway.payment;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import es.deusto.ingenieria.sd.sms.server.remote.IPaymentManager;


/**
 * <strong>Pattern</strong>
 * <ul>
 * 		<li>Gateway</li>
 * </ul>
 *
 */
public class PaypalGateway implements PaymentGateway {

	private IPaymentManager service = null;
	private String name =  "//" + "127.0.0.1" + ":" + 1099 + "/" + "Paypal";
	
	public PaypalGateway() {
		try {
			service = (IPaymentManager) java.rmi.Naming.lookup(name);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void pay(String user, int amount) {
		try {
			service.pay(user, amount);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
