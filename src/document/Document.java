package document;

/** 
 * A class that represents a text document
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
	 *
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word
	 */
	protected int countSyllables(String word)
	{	
		int count = 0, flag = 0;
		char[] text = word.toCharArray();				//turn the word into a char array
		for(int i = 0; i < text.length; i++){
			if(i == text.length - 1 && text[i] == 'e'){	//test if the last char is an 'e'
				if(count == 0)
					count++;
				}
			else if(text[i] == 'a'||text[i] == 'e'||text[i] == 'i'||text[i] == 'o'||text[i] == 'u'||text[i] == 'y'
			       ||text[i] == 'A'||text[i] == 'E'||text[i] == 'I'||text[i] == 'O'||text[i] == 'U'||text[i] == 'Y')
				{
					 if(flag == 0){		//if this is the first vowel encountered in a chain, set a flag
						 count++;
						 flag = 1;
					 }
				}
			else
				flag = 0;			//if not a vowel, clear the flag
		}
	    return count;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
		double words = getNumWords();
		double sentences = getNumSentences();
		double syllables = getNumSyllables();
		double score = 206.835 - (1.015*(words/sentences)) - (84.6*(syllables/words));
	    return score;
	}
	
	
	
}
