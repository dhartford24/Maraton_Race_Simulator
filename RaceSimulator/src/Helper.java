/**
 * This class is the driver of the application. It passes the starter method
 * in the main method to start running the code
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Helper {
	
	static boolean raceOver = false;
	
	public Helper() {
		
	} //end constructor
	
	/**
	 * The starter method is called in the main method to help start the program
	 * and hide code.  It is in this method that the menu is displayed and user
	 * input methods are called so that data can then be read, stored and processed.
	 */
	
	public void starter() {
		
		Scanner input = new Scanner(System.in);
		ArrayList<ThreadRunner> runnersName = null;
		
		String fileName;
		String XMLFileName;
		
		int myChoice = displayMenu(input);
		
		while (myChoice != 5) {
			
			switch(myChoice) {
			case 1:
				//add code to connect to the database
				RunnerDB allRunners1 = new RunnerDB();
				runnersName = allRunners1.getRunners();
				startThreads(runnersName);
				break;
			case 2:
				//add code to read XML file
				XMLFileName = Validator.getChoiceString(input, "Enter XML file name: ", '.', 'x', 'm', 'l');
				RunnerXMLFile allRunners2 = new RunnerXMLFile(XMLFileName);
				runnersName = allRunners2.getRunners();
				startThreads(runnersName);
				break;
			case 3:
				//add code to read text file
				fileName = Validator.getChoiceString(input, "Enter Text file name: ", '.', 't', 'x', 't');
				RunnerTextFile allRunners3 = new RunnerTextFile(fileName);
				runnersName = allRunners3.getRunners();
				startThreads(runnersName);
				break;
			case 4:
				//add code to test default runners
				System.out.println("\nGet set...Go!");
				DefaultRunners allRunners4 = new DefaultRunners();
				runnersName = allRunners4.getRunners();
				startThreads(runnersName);
				break;
			} //end switch
			
			System.out.println("\nPress enter to continue...");
			String w = "wait to press enter";
			while (!w.equals("")) {
				w = input.nextLine();				
			} //end while
			raceOver = false;
			
			myChoice = displayMenu(input);
			
		} //end while
		
	} //end starter
	
	/**
	 * displayMenu method displays the menu and obtains the user's choice
	 * @param sc
	 * @return an integer based on the menu options
	 */
	
	public int displayMenu(Scanner sc) {
		System.out.println("Welcome to the Marathon Race Runner Program\n");
		System.out.println("Select your data source:\n");
		System.out.println("1.  Derby database\n" +
						   "2.  XML file\n" +
						   "3.  Text file\n" +
						   "4.  Default two runners\n" +
						   "5.  Exit\n");
		
		int choice = Validator.getIntWithinRange(sc, "Enter your choice: ", 0, 6);
		
		return choice;
	} //end displayMenu
	
	/**
	 * This method loops through all of the ThreadRunner objects in an array list and 
	 * starts each object so they all begin to run. It also pauses Thread-0 until all
	 * of the other threads finish running.
	 * @param RunnersName
	 */
	
	public void startThreads(ArrayList<ThreadRunner> runnersName) {
		for (ThreadRunner s : runnersName)
			s.start();
		for (ThreadRunner s : runnersName) {
			try {
				s.join();
			} //end try
			catch (InterruptedException e) {
				e.printStackTrace();
			} //end catch
		} //end for
	} //end startThreads
	
} //end class
