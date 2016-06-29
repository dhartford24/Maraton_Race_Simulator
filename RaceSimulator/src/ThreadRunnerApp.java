/**
 * @author Derek Hartford
 * @since 03/20/16
 * Instructor: Bineet Sharma
 * 
 * This program simulates a race between different animals based on their speed and
 * amount of time they need to rest.
**/

public class ThreadRunnerApp {
	
	public static void main(String[] args) {
		
		Helper startProgram = new Helper();
		startProgram.starter();
		
	} //end main
	
	/**
	 * The finished method below is called in the ThreadRunner class once one of the 
	 * runners finishes the race. It also ensures that two runners cannot result in 
	 * a tie if they reach 1000 at the same time by incorporating the synchronized
	 * key word in the method signature so that only one thread can used this method 
	 * at a time.
	 * 
	 * @param The ThreadRunner object who reaches 1000 first
	**/
	public static synchronized void finished(ThreadRunner r)
    {
    	
    	System.out.println(r.getRunnerName() + " : I finished!");
        System.out.println("\nThe race is over! The " + 
        						r.getRunnerName()  + " is the winner.\n");
        Helper.raceOver = true;
        
    }

} //end ThreadRunnerApp class
