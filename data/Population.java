package edu.upenn.cit594.data;


public class Population{
	
	double populationNumber;
	String zipCode;
	
	public Population(String zipCode,double populationNumber) {
		this.populationNumber = populationNumber;
		this.zipCode = zipCode;
		
	}

	
	public double getPopluationNumber() {
		return populationNumber;
	}



	public String getZipCode() {
		return zipCode;
	}

	
}