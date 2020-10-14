package edu.upenn.cit594;


import java.io.File;

import edu.upenn.cit594.datamanagement.ParkingCSVReader;
import edu.upenn.cit594.datamanagement.ParkingDataReader;
import edu.upenn.cit594.datamanagement.ParkingJSONReader;
import edu.upenn.cit594.datamanagement.PopulationDataReader;
import edu.upenn.cit594.datamanagement.PropertyDataReader;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;
import edu.upenn.cit594.ui.CommandLineUserInterface;


public class Main {

	public static void main(String[] args) {

		//user need to input 5 arguments from the command line, check if all 5 arguments are entered
		if (args.length != 5) {
			System.out.println("Please Double Check If You Have Entered All 5 Runtime Arguments");
			System.exit(0);
		}

		String parkingVoliationFileFormat = args[0];
	    //only takes csv or json as format and case sensitive
		if (!parkingVoliationFileFormat.equals("csv") && !parkingVoliationFileFormat.equals("json")) {
			System.out.println("Please Double Check Your Input Format for Parking Violation File: Has to Be Either csv or json and Case-Sensitive");
			System.exit(0);
		}
		
		
		//check if file can be read/exist, if not, print message and exit;
		for (int i=1; i<5;i++) {
			
			fileValidator(args[i]);
		}
		
		
		String parkingVoliationFileName = args[1];
		String propertyValueFileName = args[2];
		String populationFileName = args[3];
		String logFileName = args[4];

		
		System.out.println("Processing Data, Please Wait...");
		
		Logger log = Logger.getInstance(logFileName);
		ParkingDataReader parkingRr;
		if (parkingVoliationFileFormat.equals("csv")){
			 parkingRr = new ParkingCSVReader(parkingVoliationFileName,log);

		}
		else {
			 parkingRr = new ParkingJSONReader(parkingVoliationFileName,log);

		}
		PropertyDataReader propertyRr = new PropertyDataReader(propertyValueFileName,log);
		PopulationDataReader populationRr = new PopulationDataReader(populationFileName, log);
		Processor processor = new Processor(parkingRr, propertyRr, populationRr, log);//placeholder: add argument
		CommandLineUserInterface ui = new CommandLineUserInterface(processor);
		ui.start();
		
			
	}
	
	public static void fileValidator(String fileName){
	
	File file = new File(fileName);
	
	
	if ( !file.canRead() || !file.exists() ) {
		
		System.out.println("please double check, the "+fileName+ " file doesnot exist or cannot be read");
		System.exit(0);
		
	}
	}
	
}