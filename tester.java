package edu.upenn.cit594;

import java.util.Scanner;

public class tester {
	
	public static void main(String[] args)  {
		System.out.println("Welcome to the Program, please enter the ID of action you'd like to perform");
		
		//must be integer
		try {
		
			Scanner in = new Scanner(System.in);
			
			while (in.hasNext()) {
				
				String actionID = in.next();
				//check input--must be integer and between 1 to 6
				if(isValid(actionID)==true) {//if false, the program will terminate
					int ID=Integer.parseInt(actionID);
					switch(ID) {					
					 case 0: 
						 System.out.println("You choose 1, Exiting");
						    System.exit(0);	
				            break; 
				     case 1: 
				    	 System.out.println("you choose 1 ");
				    	 break;
				     case 2:
				    	 System.out.println("you choose 2 ");
				    	 break;
				     case 3:
				    	 
				    	 System.out.println(zipCodePrompt());
				    	 System.out.println("you choose 3 ");
				    	 break;
				     case 4:
				    	 System.out.println(zipCodePrompt());
				    	 System.out.println("you choose 4 ");
				    	
				    	 break;
				     case 5:
				    	 System.out.println(zipCodePrompt());
				    	 System.out.println("you choose 5 ");
				    	
				    	 break;
				     case 6:
				    	 //placeholder
				    	 System.out.println(zipCodePrompt());
				    	 System.out.println("you choose 6 ");
				    	
				    	 break;
				    	
				     default: 
				           System.out.println("Error in case selection, Exiting");
				           System.exit(0);	
				            break;
					

					}
					
					
					
					}
					
				}

		
			in.close();
			
		
		}catch (Exception e) {
			
			System.out.println("Error reading the action ID");
	    	System.exit(0);	
		}
			
			
			
		}
		
		
	public static boolean isValid(String s) { 
		
		  try {  
		    int actionID=Integer.parseInt(s); 
		    if(actionID<0||actionID>7) {
		    	System.out.println("The action ID you entered is invalid");
		    	System.exit(0);	
		    	return false;
		    	
		    }else {
		    	return true;
		    }
		    
		  } catch(NumberFormatException e){ 
			  System.out.println("The action ID you entered is invalid");
		    System.exit(0);	
		    return false;  
		  }  
		}
	
	public static String zipCodePrompt() {
		String zipCode="0";
		 System.out.println("please enter a five-digital zip code");
		 try {
				
				Scanner in = new Scanner(System.in);
				
				if (in.hasNext()) {
					
					// no need to check zip code?
					zipCode=in.next();}
					//if(zipCode.length()<5) 
						//System.out.println("The Zip Code you entered is invalid");
						
					//}
					//check if all ints
					 try {  
						   Integer.parseInt(zipCode);
						    
						  } catch(NumberFormatException e){ 
							System.out.println("The Zip Code you entered is invalid");
						    System.exit(0);	
	 
						  }  
					
				
				//in.close();
				
	}catch (Exception e) {
		
		System.out.println("Error reading the zip code");
  	System.exit(0);	
	}
		return zipCode;
	
	}
	
	
	public boolean exitingFlag() {
		boolean exitingFlag=false;
		
		
		return false;
	}
	

}
