package spelling;

import java.util.TreeSet;

public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;

   //implement dictionary using a TreeSet
   public DictionaryBST(){
	   dict = new TreeSet<String>();
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
