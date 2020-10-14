package edu.upenn.cit594.processor;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.data.Population;
import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingDataReader;
import edu.upenn.cit594.datamanagement.PopulationDataReader;
import edu.upenn.cit594.datamanagement.PropertyDataReader;
import edu.upenn.cit594.logging.Logger;

public class Processor {

	protected ParkingDataReader parkingRr;
	protected List<Parking> parkingData;
	protected PropertyDataReader propertyRr;
	protected List<Property> propertyData;
	protected PopulationDataReader populationRr;
	protected HashMap<String, Double> populationData;
	protected Logger log;
	protected double totalPopulation;
	protected TreeMap<Integer, Double> finesPerCapita = new TreeMap<Integer, Double>();

	public Processor(ParkingDataReader parkingRr, PropertyDataReader propertyRr, PopulationDataReader populationRr,
			Logger log) {
		this.parkingRr = parkingRr;
		this.parkingData = this.parkingRr.getData();
		this.propertyRr = propertyRr;
		this.propertyData = this.propertyRr.getData();
		this.populationRr = populationRr;
		this.populationData = this.populationRr.getData();
		this.log = log;
	}

	public void getTotalPopulation() {
		if (totalPopulation == 0) {

			double total = 0;
			for (String p : populationData.keySet()) {
				total = total + populationData.get(p);
			}
			totalPopulation = total;
		}
		System.out.println("Total population of All ZipCodes are: " + (int) totalPopulation);
	}

	public void getTotalFinesPerCaptia() {
		if (finesPerCapita.isEmpty()) {

			HashMap<String, Double> totalFines = new HashMap<>();

			for (Parking p : parkingData) {
				String zipCode = p.getZipCode();
				double fine = p.getFine();
				String state = p.getState();
				if (zipCode.length() > 0 & state.equals("PA")) {
					if (totalFines.containsKey(zipCode)) {
						totalFines.put(zipCode, totalFines.get(zipCode) + fine);
					} else {
						totalFines.put(zipCode, fine);
					}
				}

			}

			for (String z : totalFines.keySet()) {
				double fines = totalFines.get(z);
				if (fines > 0) {
					if (populationData.containsKey(z)) {
						if (populationData.get(z) > 0) {
							double findperc = fines / populationData.get(z);
							finesPerCapita.put(Integer.parseInt(z), findperc);
						}

					}

				}

			}
		}
		System.out.println("Total Fines Per Capita By ZipCode:");
		for (int z : finesPerCapita.keySet()) {
			double fine = finesPerCapita.get(z);
			int fineCast = (int) (fine * 10000);

			System.out.println(z + " " + String.format("%.4f", fineCast / 10000.0));
		}

	}

	protected HashMap<String, Double> marketValueMap = new HashMap<>();
	protected HashMap<String, Double> livableAreaMap = new HashMap<>();

	private double getAvgMeasure(String zipCode, PropertyMeasurable measurable) {
		int count = 0;
		double total = 0;
		for (Property p : propertyData) {
			String z = p.getZipCode().trim();
			if (z.equals(zipCode)) {
				count++;
				total = total + measurable.getMeasures(p);
			}
		}

		if (count == 0) {
			return 0;
		} else {
			return (total / count);

		}

	}

	private double calAvgMarketValue(String zc) {
		double avg;
		String zipCode = zc.trim();
		if (marketValueMap.containsKey(zipCode)) {
			avg = marketValueMap.get(zipCode);
		} else {
			avg = getAvgMeasure(zipCode, new MarketValueMeasurable());
			marketValueMap.put(zipCode, avg);
		}

		return avg;
	}

	public void getAvgMarketValue(String zc) {

		double avg = calAvgMarketValue(zc);
		System.out.println("Average Market Value for homes in this ZipCode is: " + (int) avg);
	}

	private double calAvgTotLivableArea(String zc) {
		double avg;
		String zipCode = zc.trim();
		if (livableAreaMap.containsKey(zipCode)) {
			avg = livableAreaMap.get(zipCode);
		} else {
			avg = getAvgMeasure(zipCode, new LiveableAreaMeasurable());
			livableAreaMap.put(zipCode, avg);
		}

		return avg;
	}

	public void getAvgTotLivableArea(String zc) {

		double avg = calAvgTotLivableArea(zc);
		System.out.println("Average Total livable Area for homes in this ZipCode is: " + (int) avg);
	}

	protected HashMap<String, Double> totalResMarketValuePerCapita = new HashMap<>();

	public void getTotResidentialMarketValuePerCapita(String zc) {
		double value = 0;
		String zipCode = zc.trim();
		if (totalResMarketValuePerCapita.containsKey(zipCode)) {
			value = totalResMarketValuePerCapita.get(zipCode);
		} else {
			double total = 0;
			for (Property p : propertyData) {
				String z = p.getZipCode().trim();
				if (z.equals(zipCode)) {
					total = total + p.getMarketValue();
				}
			}

			double population = 0;

			if (populationData.containsKey(zipCode)) {
				population = populationData.get(zipCode);
			}

			if (population == 0) {
				value = 0;
			} else {
				value = total / population;
			}
			totalResMarketValuePerCapita.put(zipCode, value);
		}
		System.out.println("Total Residential Market Value Per Capita for this ZipCode is: " + (int) value);

	}

	protected HashMap<String, Double[]> additionalFeature = new HashMap<>();

	public void getAdditionalFeature(String zc) {
		double avgViolation;
		double avgValuePerSq;
		String zipCode = zc.trim();
		if (additionalFeature.containsKey(zipCode)) {
			avgViolation = additionalFeature.get(zipCode)[0];
			avgValuePerSq = additionalFeature.get(zipCode)[1];

		} else {
			double avgMarketValue = calAvgMarketValue(zipCode);
			double avgLivableArea = calAvgTotLivableArea(zipCode);

			int count = 0;
			for (Parking p : parkingData) {
				if (p.getZipCode().equals(zipCode)) {
					count++;
				}
			}

			double population = 0;
			if (populationData.containsKey(zipCode)) {
				population = populationData.get(zipCode);
			}

			if (population == 0) {
				avgViolation = 0;
			} else {
				avgViolation = count / population;
			}

			if (avgLivableArea == 0) {
				avgValuePerSq = 0;
			} else {
				avgValuePerSq = avgMarketValue / avgLivableArea;
			}

			Double[] output = new Double[2];
			output[0] = avgViolation;
			output[1] = avgValuePerSq;
			additionalFeature.put(zipCode, output);
		}

		System.out.println("Average Price Per Unit of Livable Area is " + String.format("%.2f", avgValuePerSq) + ", "
				+ "Number of Parking Violations per Capita is " + String.format("%.2f", avgViolation));

	}

	

}
