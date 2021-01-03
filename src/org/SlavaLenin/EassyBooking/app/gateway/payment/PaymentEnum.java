package org.SlavaLenin.EassyBooking.app.gateway.payment;

public enum PaymentEnum
{
	Paypal("Paypal"),
	CreditCard("CreditCard");
	
	private final String name;
	
	/**
     * @param Nombre del servicio
     */
	PaymentEnum(final String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return name;
    }
}
