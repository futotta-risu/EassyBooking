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
	private static int contador;
	
	
	public Pago() {
		super();
		contador++;
		this.paymentID=contador;
		// TODO Auto-generated constructor stub
	}
	public Pago(Date date, String confCode, String extraInfo) {
		this.date = date;
		this.paymentID = contador;
		this.confirmationCode = confCode;
		this.extraInfo = extraInfo;
		contador++;
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
	public int getContador() {
		return contador;
	}
	public void setContador(int contador) {
		this.contador = contador;
	}
	@Override
	public String toString() {
		return "Pago [paymentID=" + paymentID + ", date=" + date + "]";
	}
	
	

}