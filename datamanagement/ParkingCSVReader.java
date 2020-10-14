package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.upenn.cit594.data.Parking;
import edu.upenn.cit594.logging.Logger;

public class ParkingCSVReader extends ParkingDataReader{
    private String fileName;
    private Logger logger;
    public ParkingCSVReader(String file, Logger logger ) {
    	fileName = file;
    	this.logger = logger;
    }
	@Override
	public List<Parking> getData() {
		List<Parking> parking = new LinkedList<Parking>();
		Scanner in = null;
		try {
			in = new Scanner(new File(fileName));
			String text = System.currentTimeMillis()+" " + fileName;
			logger.writTolog(text);
			while (in.hasNext()) {
				String obs = in.nextLine();
				String[] tokens = obs.split(",");
	
				String timeStamp = tokens[0];	
				double fine = Double.parseDouble(tokens[1]);
				String description = tokens[2];
				String vehicleID = tokens[3];
				String state = tokens[4].trim();
				String violationID = tokens[5];
				String zipCode = "";
				if (tokens.length==7) {
					 zipCode = tokens[6].trim();
				}
				
				Parking p = new Parking(timeStamp, fine, description, vehicleID, state, violationID, zipCode);
				parking.add(p);
				
			}
		} catch (FileNotFoundException e) {
			
			System.out.println(" Parking file not found");
			System.exit(0);

			e.printStackTrace();
		}		
		return parking;
	}
	


}
