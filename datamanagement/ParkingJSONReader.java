package edu.upenn.cit594.datamanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.logging.Logger;

public class ParkingJSONReader extends ParkingDataReader{
	private String fileName;
	private Logger logger;
	public ParkingJSONReader(String file, Logger logger) {
		fileName = file;
		this.logger = logger;
	}
	@Override
	public List<Parking> getData() {
		List<Parking> output = new LinkedList<Parking>();
		JSONParser parser = new JSONParser();
		try {
			JSONArray parkings = (JSONArray)parser.parse(new FileReader(fileName));
			String text = System.currentTimeMillis()+" " + fileName;
			logger.writTolog(text);
			Iterator iter = parkings.iterator();
			while (iter.hasNext()) {
				// get the next JSON object
				JSONObject parking = (JSONObject) iter.next();
				// use the "get" method to print the value associated with that key
				String timeStamp = parking.get("date").toString();	
				double fine = Double.parseDouble(parking.get("fine").toString());
				String description = parking.get("violation").toString();
				String vehicleID = parking.get("plate_id").toString();
				String state = parking.get("state").toString().trim();
				String violationID = parking.get("ticket_number").toString();
				String zipCode = parking.get("zip_code").toString().trim();
				Parking p = new Parking(timeStamp, fine, description, vehicleID, state, violationID, zipCode);

				output.add(p);
			}		
		} catch (FileNotFoundException e) {
			System.out.println(" Parking file not found");
			System.exit(0);
			e.printStackTrace();
		} catch (IOException e) {
			
			System.out.println(" Parking file not correct");
			System.exit(0);
			e.printStackTrace();
		} catch (ParseException e) {
			
			System.out.println(" Error during parsing the parking file");
			System.exit(0);
			e.printStackTrace();
		}
		return output;
	}

}
