/**
 * This class reads data stored in a database called FinalDB and stores the data
 * collected from each runner in an array list.
**/

import java.util.*;
import java.sql.*;

public class RunnerDB implements RunnerReader {
	
	private String dbDirectory;
	private ArrayList<ThreadRunner> runners = null;

	public RunnerDB() {
		dbDirectory = "FinalResources";
		System.setProperty("derby.system.home", dbDirectory);
	} //end constructor
	
	/**
	 * Establish a connection with the database
	 * 
	 * @return A Connection object
	**/
	private Connection getConnection() {
		
		String dbUrl = "jdbc:derby:FinalDB";
		String username = "";
		String password = "";
		
		try {
			Connection connection = DriverManager.getConnection(dbUrl, username, password);
			return connection;
		}
		catch (SQLException e) {
			System.err.println("SQL Exception: " + e);
			return null;
		}
		
	} //end getConnection
	
	/**
	 * Obtain the data from the database using JDBC by creating a result set 
	 * from a prepared statement to grab data. Then store the data gathered
	 * from each runner in an array list.
	 * 
	 * @return An array list full of ThreadRunner objects
	**/
	
	public ArrayList<ThreadRunner> getRunners() {
		
		runners = new ArrayList<>();
		
		String selectRunners = "SELECT Name, RunnersSpeed, RestPercentage " +
				"FROM Runners ORDER BY Name ASC";
		
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(selectRunners);
				ResultSet rs = ps.executeQuery())
		{
			//read data from each column and store in an array list
			while (rs.next()) {
				String name = rs.getString("Name");
				int speed = rs.getInt("RunnersSpeed");
				int restPercent = rs.getInt("RestPercentage");
				
				ThreadRunner tr = new ThreadRunner(name, speed, restPercent);
				runners.add(tr);
			} //end while
			System.out.println("\nGet set...Go!");
			return runners;
		} //end try
		catch (SQLException e) {
			System.err.println(e);
			return null;
		} //end catch
		
		
	} //end getRunners
	
} //end class
