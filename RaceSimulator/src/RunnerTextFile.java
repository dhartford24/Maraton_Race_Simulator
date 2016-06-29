/**
 * This class reads data from a text file supplied by the user and stores the
 * data collected from each runner in an array list.
**/

import java.util.*;
import java.io.*;
import java.nio.file.*;

public class RunnerTextFile implements RunnerReader {

	private ArrayList<ThreadRunner> runners = null;
	private Path runnersPath = null;
	private File runnersFile = null;
	private String fileName;
	
	private final String FIELD_SEP = "\t";
	
	public RunnerTextFile(String fileName) {
		this.fileName = fileName;
		runnersPath = Paths.get(this.fileName);
		runnersFile = runnersPath.toFile();
		runners = this.getRunners();
	} //end constructor
	
	/**
	 * Obtain the data from the text file and store it in an array list.
	 * 
	 * @return An array list full of ThreadRunner objects
	**/
	
	public ArrayList<ThreadRunner> getRunners() {
		
		//if the runners file has already been read, don't read it again
		if (runners != null)
			return runners;
		
		runners = new ArrayList<>();
		
		if (Files.exists(runnersPath)) {	//prevent the FileNotFoundException
			
			try (BufferedReader in = 
					new BufferedReader(
					new FileReader(runnersFile)))
			{
				//read all runners stored in the file into an array list
				String line = in.readLine();
				while (line != null) {
					String[] columns = line.split(FIELD_SEP);
					String name = columns[0];
					String speed = columns[1];
					String restPercentage = columns[2];
					
					ThreadRunner tr = new ThreadRunner(
							name, Integer.parseInt(speed), Integer.parseInt(restPercentage));
					runners.add(tr);
					
					line = in.readLine();
				} //end while
				
			} //end try
			catch(IOException e) {
				System.out.println(e);
				return null;
			} //end catch
			
			System.out.println("\nGet set...Go!");
			
		} //end if
		else {
			System.out.println("\nSorry Text file not found");
		}
		return runners;
	} //end getRunners
	
} //end class
