package org.SlavaLenin.EassyBooking.app.data.payment;

import java.util.Date;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.PersistenceCapable;

import org.SlavaLenin.EassyBooking.app.data.PaymentMethod;
import org.SlavaLenin.EassyBooking.app.gateway.payment.PaymentEnum;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)
public class CreditCard extends PaymentMethod{
	
	private String name;
	private String bankAccount;
	private String cardCode;
	
	private Date expirationDate;
	

	public CreditCard() {
		this.paymentType = PaymentEnum.CreditCard;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	
}
