package org.SlavaLenin.EassyBooking.app.data;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.*;

@PersistenceCapable(detachable = "true")
public class Pago implements Serializable {

	private Date date;
	private int paymentID;
	private String confirmationCode;
	private String extraInfo;
	
	
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
	@Override
	public String toString() {
		return "Pago [paymentID=" + paymentID + ", date=" + date + "]";
	}
	
	

}