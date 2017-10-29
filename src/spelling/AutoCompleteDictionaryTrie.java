package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * A trie data structure that implements the Dictionary and the AutoComplete ADT
 * 
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;		//# of words in the trie
    
    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie. Appropriately uses existing nodes in the trie, only creating new 
	 * nodes when necessary.
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		if (word == null) {
			throw new NullPointerException();
		}
		if (isWord(word)) {				//return false if the word has already been added
			return false;							
		}
		
		String newWord = word.toLowerCase();		//make word lowercase
		char[] charArray = newWord.toCharArray();	//string to char array to go 1 letter at a time
		TrieNode currentNode = root;
		
		for(char c: charArray){					//for each letter
			if(currentNode.getChild(c) != null){		//if that letter is in the hashmap
				currentNode = currentNode.getChild(c);	//get its child node for that letter
			}
			else{						//letter not in hashmap
				currentNode = currentNode.insert(c);	//Insert the letter, return newly created node
			}
		}
		currentNode.setEndsWord(true);			//After everything, currentNode points to node with our finished word
								//Set that this node ends a word
		size++;						//inc size because a new word has been added to the trie

	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary
	 */
	public int size()
	{		
		//we use size to keep track of the # of words we add in addWord
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie
	 */
	@Override
	public boolean isWord(String s) 
	{
		if (s == null) {
			throw new NullPointerException();
		}
		String newWord = s.toLowerCase();			//make word lowercase
		char[] charArray = newWord.toCharArray();		//string to char array to go 1 letter at a time
		TrieNode currentNode = root;
		
		for(char c: charArray){					//for each letter
			if(currentNode.getChild(c) != null){		//if that letter is in the hashmap
				currentNode = currentNode.getChild(c);	//get its child node for that letter
			}
			else{						//letter not in hashmap
				return false;				//so the word can't be in the trie
			}
		}
		
		if(currentNode.endsWord()){			//if the last node is set as a word
			return true;				//then it has already been added and is a word
		}
		
		return false;					//reached the last node, but it's not set as a word we've added
	}

	/** 
     * Return a list, in order of increasing word length
     * All legal completions must be valid words in the dictionary.
     * If the string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 List<String> predictionsToReturn = new ArrayList<String>();	//list of predictions to return
    	 List<TrieNode> predictions = new LinkedList<TrieNode>();	//acts as our queue of nodes
    	 int completions = 0;
    	 
    	 if (prefix == null) {
 			throw new NullPointerException();
 		 }
 		String givenPrefix = prefix.toLowerCase();		//make word lowercase
 		char[] charArray = givenPrefix.toCharArray();		//string to char array to go 1 letter at a time
 		TrieNode currentNode = root;
 		
 		// Find the stem in the trie.  If the stem does not appear in the trie, return an
 		// empty list
 		for(char c: charArray){					//for each letter
 			if(currentNode.getChild(c) != null){		//if that letter is in the hashmap
 				currentNode = currentNode.getChild(c);	//get its child node for that letter
 			}
 			else{						//letter not in hashmap, prefix can't be in the trie
 				return predictionsToReturn;		//return an empty list
 			}
 		}
    	 
 		predictions.add(currentNode);					//add our stem to the start of the queue
 		
 		//while our queue of words isn't empty & we need more completions, perform BFS to generate
 		//more completions
 		while(!predictions.isEmpty() && completions != numCompletions){
 			currentNode = predictions.remove(0);				//remove first node in queue
 			if(currentNode.endsWord()){					//if it is a word
 				predictionsToReturn.add(currentNode.getText());		//add it to completions list
 				completions++;
 			}
 			Set<Character> children = currentNode.getValidNextCharacters(); //get the chars in this node
 			for(char c: children){
 				if(currentNode.getChild(c) != null){			//if the node has a child for a given char
 					TrieNode newChild = currentNode.getChild(c);
 					predictions.add(newChild);			//add the child to the queue
 				}
 			}
 			
 		}  	 
    	 
         return predictionsToReturn;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
	
}
