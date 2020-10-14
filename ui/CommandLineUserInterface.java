package edu.upenn.cit594.ui;

import java.util.Scanner;

import edu.upenn.cit594.processor.Processor;

public class CommandLineUserInterface {
	Processor myProcessor;

	// constructor
	public CommandLineUserInterface(Processor processor) {
		this.myProcessor = processor;
	}

	public void start() {
		System.out.println("Welcome to the Program, please enter the ID of action you'd like to perform");

		System.out.println("Please see options below: " + "\n0 - Exit current program"
				+ "\n1 - Display sum of total population For all zip codes"
				+ "\n2 - Display parking fines per capita for each zip code"
				+ "\n3 - Display average Market Value for homes in a user-specified zip code"
				+ "\n4 - Display average total livable area for homes in a user-specified zip code"
				+ "\n5 - Display total market value per capita for homes in a user-specified zip code"
				+ "\n6 - Display customed features");

		try {

			Scanner in = new Scanner(System.in);

			while (in.hasNext()) {

				String actionID = in.next();
				// check input--must be integer and between 1 to 6
				if (isValid(actionID) == true) {// if false, the program will terminate
					int ID = Integer.parseInt(actionID);
					switch (ID) {
					case 0:
						
						System.exit(0);
						break;
					case 1:
						myProcessor.getTotalPopulation();
						break;
					case 2:
						myProcessor.getTotalFinesPerCaptia();
						break;
					case 3:
						myProcessor.getAvgMarketValue(zipCodePrompt());
						break;
					case 4:
						myProcessor.getAvgTotLivableArea(zipCodePrompt());
						break;
					case 5:
						myProcessor.getTotResidentialMarketValuePerCapita(zipCodePrompt());
						break;
					case 6:
						myProcessor.getAdditionalFeature(zipCodePrompt());
						break;

					default:
						System.out.println("The action ID is invalid, the program will exit");
						System.exit(0);
						break;
					}
				}

				System.out.println("Please enter the ID of action you'd like to perform");
			}

			in.close();

		} catch (Exception e) {

			System.out.println("Error reading the action ID");
			System.exit(0);
		}

	}

	public boolean isValid(String s) {

		try {
			int actionID = Integer.parseInt(s);
			if (actionID < 0 || actionID > 7) {
				System.out.println("The action ID you entered is invalid");
				System.exit(0);
				return false;

			} else {
				return true;
			}

		} catch (NumberFormatException e) {
			System.out.println("The action ID you entered is invalid");
			System.exit(0);
			return false;
		}
	}

	public String zipCodePrompt() {
		String zipCode = "0";
		System.out.println("please enter a five-digital zip code");
		try {

			Scanner in = new Scanner(System.in);

			if (in.hasNext()) {

				zipCode = in.next();
			}
			try {
				// if not int, zip code is invalid
				Integer.parseInt(zipCode);

			} catch (NumberFormatException e) {
				System.out.println("The Zip Code you entered is invalid");
				System.exit(0);

			}

		} catch (Exception e) {

			System.out.println("Error reading the zip code");
			System.exit(0);
		}
		return zipCode;

	}

}
