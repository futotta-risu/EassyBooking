package org.SlavaLenin.EassyBooking.app.data;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Pago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	@Unique
	private int paymentID;
	private String confirmationCode;
	private String extraInfo;
	
	public Pago(Date date, int id, String confCode, String extraInfo) {
		this.date = date;
		this.paymentID = id;
		this.confirmationCode = confCode;
		this.extraInfo = extraInfo;
	}
	
	private User user;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	public String getExtraInfo() {
		return extraInfo;
	}
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	public void borrarPago() {
		this.user = null;
	}
	
	@Override
	public String toString() {
		return "Pago [paymentID=" + paymentID + ", date=" + date + "]";
	}
	
	

}