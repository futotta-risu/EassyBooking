package org.SlavaLenin.EassyBooking.app.data;

public class Airline {
	private String name, code;
	
	public Airline(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setICAO(String code) {
		this.code = code;
	}
	

	

}