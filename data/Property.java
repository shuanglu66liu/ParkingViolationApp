package edu.upenn.cit594.data;


public class Property{
	double marketValue;
	double totalLivableArea;
	String zipCode;
	
	public Property(String zipCode, double marketValue, double totalLivableArea) {
		this.marketValue = marketValue;
		this.totalLivableArea = totalLivableArea;
		this.zipCode = zipCode;
	}

 public double getMarketValue() {
		return marketValue;
	}

	public double getTotalLivableArea() {
		return totalLivableArea;
	}

	public String getZipCode() {
		return zipCode;
	}

}
