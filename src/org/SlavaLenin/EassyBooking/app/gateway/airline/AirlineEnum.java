package org.SlavaLenin.EassyBooking.app.gateway.airline;

public enum AirlineEnum {
	AirFrance("AiFr"), KoreanAir("KoAi");
	
	private String code;
	
	AirlineEnum(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
