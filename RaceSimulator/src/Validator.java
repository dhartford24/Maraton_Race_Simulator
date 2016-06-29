/**
 * This class provides a series of methods to ensure that the user enters a
 * proper input
 */

import java.util.Scanner;

public class Validator
{
	/**
	 * method that obtains an integer
	 * @param sc
	 * @param prompt
	 * @return an integer
	 */
	
	public static int getInt(Scanner sc, String prompt)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			System.out.print(prompt);
			if (sc.hasNextInt())
			{
				i = sc.nextInt();
				isValid = true;
			}
			else
			{
				System.out.println("Error! Please enter a proper integer value between 1 and 5. Try again.");
			}
			sc.nextLine();  // discard any other data entered on the line
		}
		return i;
	}
	
	/**
	 * method that obtains an integer between 2 other integers
	 * @param sc
	 * @param prompt
	 * @param min
	 * @param max
	 * @return An integer between the specified 2 two integers
	 */

	public static int getIntWithinRange(Scanner sc, String prompt,
	int min, int max)
	{
		int i = 0;
		boolean isValid = false;
		while (isValid == false)
		{
			i = getInt(sc, prompt);
			if (i <= min)
				System.out.println(
					"Error! Your choice must be between 1 and 5, please enter again");
			else if (i >= max)
				System.out.println(
					"Error! Your choice must be between 1 and 5, please enter again");
			else
				isValid = true;
		}
		return i;
	}
	
	/**
	 * method that forces the user to enter a string
	 * @param sc
	 * @param prompt
	 * @return a String
	 */
	
    public static String getRequiredString(Scanner sc, String prompt)
    {
        String s = "";
        boolean isValid = false;
        while (isValid == false)
        {
            System.out.print(prompt);
            s = sc.nextLine();
            if (s.equals(""))
            {
                System.out.println("Error! This entry is required. Try again.");
            }
            else
            {
                isValid = true;
            }
        }
        return s;
    }
    
    /**
     * add a new method that forces the user to enter a string that ends with
     * a certain 4 char characters
     * @param sc
     * @param prompt
     * @param s1
     * @param s2
     * @param s3
     * @param s4
     * @return a proper string ending with a certain 4 characters
     */
    public static String getChoiceString(Scanner sc, String prompt, char s1, char s2, char s3, char s4)
    {
        String s = "";
        boolean isValid = false;
        while (isValid == false)
        {
            s = getRequiredString(sc, prompt);
            if (s.length() > 3) {
            	if (
            		!(s.charAt((s.length()-1)) == s4) &&
            		!(s.charAt((s.length()-2)) == s3) &&
            		!(s.charAt((s.length()-3)) == s2) &&
            		!(s.charAt((s.length()-4)) == s1)
                ) 
            	{
                System.out.println("Error! Entry must end with '" +
                		s1 + s2 + s3 + s4 + "'. Please try again.");
            	}
            	else
            	{
            		isValid = true;
            	}
            } //end if
            else {
            	System.out.println("Error! Entry must end with '" +
            			s1 + s2 + s3 + s4 + "'. " + "Please try again.");
            }
        } //end while
        return s;
    } //end method

}