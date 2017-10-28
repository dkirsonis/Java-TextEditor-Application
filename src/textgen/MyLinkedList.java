package textgen;

import static org.junit.Assert.fail;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;		
	LLNode<E> tail;		
	int size;			//# of elements in the LinkedList

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		//not using sentinel nodes, so a new LinkedList is empty with only head+tail pointers
		this.head = new LLNode<E>();
		this.tail = new LLNode<E>();
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null)
			throw new NullPointerException("Element can not be null");
		
		LLNode<E> newest = new LLNode<E>(element);	//the new node
		
		if(size == 0){				//if empty list
			head = newest;			//both head & tail point to the same element
			tail = newest;
		}
		else{
			tail.next = newest;		//last element points to new element
			newest.prev = tail;		//new element points back to the last element
			tail = newest;			//new element is now the tail
		}	
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index must be a positive value and less than list size");
		
		LLNode<E> temp = head;					//start at index 0
			for(int i=0; i < index; i++){
				temp = temp.next;				//traverse the list to the correct index
			}
		
		return temp.data;		//returns the data at the requested index
	}
	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if(element == null)
			throw new NullPointerException("Element can not be null");
		if(index < 0 || (index >= size && size != 0))
			throw new IndexOutOfBoundsException("Index must be a positive value and less than list size");
		LLNode<E> newest = new LLNode<E>(element);
		LLNode<E> temp = head;
		for(int i =0; i < index; i++){
			temp=temp.next;					//get to the index
		}
		
		if(size == 0 || index == size-1)	//if list is empty or adding to the end, call other add method
			add(element);
		else{
			if(index == 0){						//adding to the front of the list
				newest.next = head;				//new element points to the head
				head.prev = newest;				//the head points back to the new element
				head = newest;					//new element is now the head
			}		
			else{
				(temp.prev).next = newest;			//have element at index-1 point to new element
				newest.next= temp;					//new element points at original index value
				newest.prev = temp.prev;			//new element points back at index-1 location
				temp.prev = newest;					//original index value points back at the new element 
			}
			size++;
		}
		
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index must be a positive value and less than list size");
		
		LLNode<E> temp = head;		
		for(int i =0; i < index; i++){
			temp=temp.next;					//get to the index
		}
		if(index == 0 && size == 1){		//if removing the only element in the list
			head = null;
			tail = null;
		}
		else if(index == 0){				//if removing the head
			head = temp.next;				//point head to the next element
			head.prev = null;				//new head points back to nothing
			temp.next = null;				//null out previous heads pointer for garbage collection(?)
		}
		else if (index == size - 1){		//if removing the tail
			tail = temp.prev;				//point tail to previous element
			(temp.prev).next = null;		//new tail points to nothing
			temp.prev = null;				//null out previous tails pointer for garbage collection(?)			
		}
		else{								//removing from somewhere in the middle 
			(temp.prev).next = temp.next;	//index-1 points to index+1
			(temp.next).prev = temp.prev;	//index+1 points back to index-1
			temp.next = null;				//null out pointers of element being removed for garbage collection(?)
			temp.prev = null;
		}
		
		size--;
		return temp.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if(element == null)
			throw new NullPointerException("Element can not be null");
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index must be a positive value and less than list size");
		
		LLNode<E> temp = head;		
		for(int i =0; i < index; i++){
			temp=temp.next;					//get to the index
		}
		
		E value = temp.data;
		temp.data = element;
		
		return value;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;
	
	public LLNode(){
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e) 		//data constructor 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
