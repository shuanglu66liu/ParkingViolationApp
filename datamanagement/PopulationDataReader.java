package edu.upenn.cit594.datamanagement;


import java.io.File;
import java.util.*;


import edu.upenn.cit594.logging.Logger;

public class PopulationDataReader {

	protected String fileName;
	protected Logger log;

	public PopulationDataReader(String fileName, Logger log) {
		this.fileName = fileName;
		this.log = log;
	}

	public HashMap<String, Double> getData() {
		HashMap<String, Double> population = new HashMap<>();

		try {

			Scanner in = new Scanner(new File(fileName), "UTF-8");
			String text = System.currentTimeMillis() + " " + fileName;
			log.writTolog(text);

			while (in.hasNextLine()) {
				String next = in.nextLine();
				String[] lineData = next.split(" ");
				String zipCode = lineData[0];
				String pop = lineData[1];

				population.put(zipCode, Double.parseDouble(pop));

			}
			in.close();

		} catch (Exception e) {
			System.out.println("Error Reading the Population File, Please Check");
			System.exit(0);
		}
		return population;
	}

	public boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
