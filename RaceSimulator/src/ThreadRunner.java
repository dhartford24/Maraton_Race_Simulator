/**
 * This class creates a thread that allows multiple runners to 'run' a race at the same
 * time by either adding to their race position from their speed, or resting if their
 * resting percentage calls for it
**/

public class ThreadRunner extends Thread {
	
	private String name;
	private int restPercent;
	private int speed;
	private int endNumber = 1000;
	int runOrRest;
	
	public ThreadRunner () {
	}
	
	public ThreadRunner (String name, int speed, int restPercent) {
		this.name = name;
		this.restPercent = restPercent;
		this.speed = speed;
	} //end constructor
	
	/**
	 * The run method runs the racing simulation for each runner
	**/
	
	@Override
	public void run() {
		
		int racePosition = 0;
		
		while (racePosition < this.endNumber) {
			/**
			 * if a runner finishes the race (racePosition >= 1000), then raceOver will
			 * become true and all other threads will claim they were beaten and then
			 * the program will exit the loop and end the run method
			**/
			if (Helper.raceOver) {
				System.out.println(this.getRunnerName() + 
						": You beat me fair and square.");
				break;
			}
			
			//Generate a random number between 0 and 100 to decide whether to run or rest
			runOrRest = (int)(Math.random() * 101);
			
			/**
			 * if the random number generated is less than or equal to the rest percentage,
			 * then the thread will sleep for 100 milliseconds and start the loop over.
			 * Else, the runner will 'run' the distance of their run speed then start the loop
			 * over until one of the runner reaches 1000.
			**/
			if (runOrRest <= this.restPercent) {
				try {
					
					Thread.sleep(100);
					
				} //end try
				catch(InterruptedException e) {
					System.out.println("From Catch: Sorry thread was interrupted. The race is over, goodbye");
					break;
				}
			} //end if
			else {
				racePosition += this.speed;
				System.out.println(this.getRunnerName() + " : " + racePosition);
			} //end else
			
		} //end while
		
		/**
		 * if a runner reaches 1000, then they will exit the loop above and become the winner.
		 * Once that happens they will be able to call the finished method in the if statement
		 * below. This will also cause the raceOver variable to become true so the other runners
		 * will claim to be beaten fair and square and they will exit their loops as well, ending
		 * the run method
		**/
		if (!Helper.raceOver)
			ThreadRunnerApp.finished(this);
		
	} //end run
	
	public void setRunnerName(String name) {
		this.name = name;
	}
	
	public void setRunnerSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setRunnersRestPercentage(int restPercent) {
		this.restPercent = restPercent;
	}
	
	public String getRunnerName() {
		return this.name;
	} //end getRunnerName
	
} //end ThreadRunner class
