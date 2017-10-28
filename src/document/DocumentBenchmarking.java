package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    int trials = 100;

	    // The text to test on
	    String textfile = "data/warAndPeace.txt";
		
	    // The amount of characters to increment each step
		int increment = 20000;

		// The number of steps to run.  
		int numSteps = 20;
		
		// The number of characters to start with. 
		int start = 50000;
		
		//prints out timing results for BasicDocument vs EfficientDocument
		long timeTaken = 0;
		double seconds = 0;
		System.out.println("Chars " +"\tBasicTime " +"\tEfficientTime");
		for (int numToCheck = start; numToCheck < numSteps*increment + start; 
				numToCheck += increment)
		{
			// numToCheck holds the number of characters to read from the 
			// file to create both a BasicDocument and an EfficientDocument.  
			System.out.print(numToCheck+"\t");
			String text = getStringFromFile(textfile, numToCheck);
			
			//time a loop that runs trials times to create a BasicDocument and calls fleshScore
			timeTaken =  System.nanoTime();
			for(int i = 0; i < trials; i++){
				BasicDocument tester = new BasicDocument(text);		
				tester.getFleschScore();
			}
			timeTaken =  System.nanoTime() - timeTaken;
			seconds = (double)timeTaken/1000000000;			//convert from nanseconds to seconds
			
			System.out.print(seconds + "\t");
			
			//time a loop that runs trials times to create an EfficientDocument and calls fleshScore
			timeTaken =  System.nanoTime();
			for(int j = 0; j < trials; j++){
				EfficientDocument tester2 = new EfficientDocument(text);
				tester2.getFleschScore();
			}
			timeTaken =  System.nanoTime() - timeTaken;
			seconds = (double)timeTaken/1000000000;			//convert from nanseconds to seconds
			System.out.print(seconds + "\n");
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			if (count < numChars) {
				System.out.println("Warning: End of file reached at " + count + " characters.");
			}
			bis.close();
		}
		catch(Exception e)
		{
		  System.out.println(e);
		  System.exit(0);
		}
		
		
		return s.toString();
	}
	
}
