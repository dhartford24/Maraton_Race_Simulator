/**
 * This class creates two default runners and passes their information to create
 * two instances of the ThreadRunner class, then it stores both created objects 
 * in an array list.
**/

import java.util.ArrayList;

public class DefaultRunners implements RunnerReader {

	private ArrayList<ThreadRunner> runners = null;
	private String runner1Name;
	private String runner2Name;
	private int runner1Speed;
	private int runner2Speed;
	private int runner1RestP;
	private int runner2RestP;
	
	public DefaultRunners() {
		runner1Name = "Tortoise";
		runner2Name = "Hare";
		runner1Speed = 10;
		runner2Speed = 100;
		runner1RestP = 0;
		runner2RestP = 90;
	}
	
	/**
	 * Create two ThreadRunner objects from the runner's default data, then store
	 * the objects in an array list.
	 * 
	 * @return An array list of two ThreadRunner objects
	**/
	
	public ArrayList<ThreadRunner> getRunners() {
		runners = new ArrayList<>();
		
		ThreadRunner tr1 = new ThreadRunner(runner1Name, runner1Speed, runner1RestP);
		runners.add(tr1);
		
		ThreadRunner tr2 = new ThreadRunner(runner2Name, runner2Speed, runner2RestP);
		runners.add(tr2);
		
		return runners;
	}
	
} //end class
