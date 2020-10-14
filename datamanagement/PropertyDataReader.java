package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.util.*;

import edu.upenn.cit594.data.Property;
import edu.upenn.cit594.logging.Logger;

public class PropertyDataReader {

	protected String fileName;
	protected Logger log;

	public PropertyDataReader(String propertyFileName, Logger log) {
		this.fileName = propertyFileName;
		this.log = log;
	}

	public List<Property> getData() {

		List<Property> propertyList = new ArrayList<>();

		Scanner in = null;

		try {
			File file = new File(fileName);

			String text = System.currentTimeMillis() + " " + fileName;
			log.writTolog(text);

			in = new Scanner(file);

			int marketValueIndex = 0;
			int totalLivableAreaIndex = 0;
			int zipCodeIntex = 0;

			if (in.hasNextLine()) {
				String[] headers = in.nextLine().split(",");// this is the headers

				// loop through headers to match each index with field name
				for (int i = 0; i < headers.length; i++) {
					if (headers[i].equals("market_value")) {
						marketValueIndex = i;
					} else if (headers[i].equals("total_livable_area")) {
						totalLivableAreaIndex = i;
					} else if (headers[i].equals("zip_code")) {
						zipCodeIntex = i;
					}
				}

			}

			while (in.hasNext()) {
				String propertyData = in.nextLine();

				String[] data = propertyData.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

				String marketValue = data[marketValueIndex];
				String totalLivableArea = data[totalLivableAreaIndex];
				String zipCode = data[zipCodeIntex].trim();// remove leading and trimming whitespace

				if (!isNumeric(marketValue) || !isNumeric(totalLivableArea)) {
					continue;
				}

				// if zipcode>5, only take substring, else take full string
				if (zipCode.length() > 5) {

					propertyList.add(new Property(zipCode.substring(0, 5), Double.parseDouble(marketValue),
							Double.parseDouble(totalLivableArea)));

				} else {

					propertyList.add(new Property(zipCode, Double.parseDouble(marketValue),
							Double.parseDouble(totalLivableArea)));
				}

			}
		} catch (Exception e) {
			System.exit(0);
			System.out.println("Error processing the Property Data file");

		}
		in.close();

		return propertyList;
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

	
	

