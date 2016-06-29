/**
 * This class reads data from a XML file supplied by the user and stores the 
 * data collected from each runner in an array list.
**/

import java.util.*;
import java.io.*;
import java.nio.file.*;
import javax.xml.stream.*; //StAX API

public class RunnerXMLFile implements RunnerReader {
	
	private String XMLFileName;
	private Path runnersPath = null;
	private ArrayList<ThreadRunner> runners = null;

	public RunnerXMLFile(String XMLFileName) {
		this.XMLFileName = XMLFileName;
		runnersPath = Paths.get(this.XMLFileName);
		runners = this.getRunners();
	} //end constructor
	
	/**
	 * Obtain the data from the XML file and store it in an array list.
	 * 
	 * @return An array list full of ThreadRunner objects
	**/
	
	public ArrayList<ThreadRunner> getRunners() {
		//if the XML file has already been read, don't read it again
		if (runners != null)
			return runners;
		
		runners = new ArrayList<>();
		
		ThreadRunner tr = null;
		
		if (Files.exists(runnersPath)) { 	//Prevent the FileNotFoundException
			
			//create the XMLInputFactory object
			XMLInputFactory inputFactory = XMLInputFactory.newFactory();
			
			try {
				//create XMLStreamReader object
				FileReader fileReader = new FileReader(runnersPath.toFile());
				XMLStreamReader reader = inputFactory.createXMLStreamReader(fileReader);
				
				//read the runners from the XML file and store in an array list
				while (reader.hasNext()) {
					int eventType = reader.getEventType();
					
					switch (eventType) {
					case XMLStreamConstants.START_ELEMENT:
						String elementName = reader.getLocalName();
						if (elementName.equals("Runner")) 
						{
							tr = new ThreadRunner();
							String name = reader.getAttributeValue(0);
							tr.setRunnerName(name);
						} //end if
						if (elementName.equals("RunnersMoveIncrement"))
						{
							String speedText = reader.getElementText();
							int speed = Integer.parseInt(speedText);
							tr.setRunnerSpeed(speed);
						} //end if
						if (elementName.equals("RestPercentage"))
						{
							String restText = reader.getElementText();
							int rest = Integer.parseInt(restText);
							tr.setRunnersRestPercentage(rest);
						} //end if
						break;
					case XMLStreamConstants.END_ELEMENT:
						elementName = reader.getLocalName();
						if (elementName.equals("Runner"))
						{
							runners.add(tr);
						} //end if
						break;
					default:
						break;
					} //end switch
					
					reader.next();
				} //end while
			} //end try
			catch (IOException | XMLStreamException e)
			{
				System.out.println(e);
				return null;
			} //end catch
			
			System.out.println("\nGet set...Go!");
		} //end if
		else {
			System.out.println("\nSorry XML file is not found");
		}
		return runners;
	} //end getRunners
	
} //end RunnerXMLFile class
