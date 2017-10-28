package spelling;

//import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	// This constructor is used by the Text Editor Application
	public WPTree () {
		this.root = null;
		
		 
		 Dictionary d = new DictionaryHashSet();	//create and load up a new dictionary
		 DictionaryLoader.loadDictionary(d, "data/dict.txt");
		 this.nw = new NearbyWords(d);				//pass the dictionary to NearbyWords
	}
	
	/*
	//This constructor will be used by the grader code
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	*/
	
	// see method description in WordPath interface
	public List<String> findPath(String word1, String word2) 
	{
		if (word1.equals("") || word1==null || word2.equals("") 
				|| word2==null	|| nw.dict.isWord(word1)==false 
				|| nw.dict.isWord(word2)==false)	
			{		
					return null;
			}
		
		List<WPTreeNode> queue = new LinkedList<WPTreeNode>();	//nodes+strings to explore
		HashSet<String> visited = new HashSet<String>();		//strings we've tested
		
		root = new WPTreeNode(word1, null);						//root contains word1, no parent
		visited.add(word1);
		queue.add(root);
		while(!queue.isEmpty()){
			WPTreeNode curr = queue.remove(0);					//take the node at the front of the queue
			List<String> oneMutation = nw.distanceOne(curr.getWord(), true);  //list of 1 away word mutations
			for(String n: oneMutation){			//for each string neighbor
				if(!visited.contains(n)){
					visited.add(n);				//add to visited				
					WPTreeNode child = curr.addChild(n);		//add n as a child of curr
					queue.add(child);							//add the node for n to the back of the queue
					if(n.equals(word2)){						//if n is word2
						return child.buildPathToRoot();			//return path from child to root
					}
				}
			}
			
		}
		
	    return null;		//no path exists
	}
	
	// Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /** Construct a node with the word w and the parent p
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child of a node containing the String s
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get the list of children of the calling object
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Allows you to build a path from the root node to 
     *  the calling object
     * @return The list of strings starting at the root and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get the word for the calling object
     *
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /** toString method
    *
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }

}

