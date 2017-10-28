package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	public DictionaryLL(){		
		dict = new LinkedList<String>();		//create the object
	}

    /** Add this word to the dictionary
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     */
    public boolean addWord(String word) {
    	String temp = word.toLowerCase();
    	if(dict.contains(temp))		//returns true if the word is already in the dict
    		return false;
    	else 
    		dict.add(temp);			//add new word
        return true;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String temp = s.toLowerCase();
    	if(dict.contains(temp))			//check if in the dict
    		return true;
    	
        return false;
    }

    
}
