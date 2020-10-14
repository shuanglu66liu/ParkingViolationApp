package edu.upenn.cit594.data;

public class Parking {
	private String timeStamp;
	private double fine;
	private String description;
	private String vehicleID;
	private String state;
	private String violationID;
	private String zipCode;
	
	public Parking(String ts, double f, String des, String vId, String s, String vioID, String zipcode) {
		timeStamp = ts;
		fine = f;
		description = des;
		vehicleID= vId;
		state = s;
		violationID = vioID;
		zipCode = zipcode;
	}
	
	public String getTimeStamp() {
		return timeStamp;
	}
	
	public double getFine() {
		return fine;
	}
	public String getDescription() {
		return description;
	}
	public String getVehicleID() {
		return vehicleID;
	}
	public String getState() {
		return state;
	}
	public String getViolationID() {
		return violationID;
	}
	public String getZipCode() {
		return zipCode;
	}
}
