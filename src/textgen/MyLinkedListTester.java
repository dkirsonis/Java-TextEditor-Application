/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * Methods to test all of the LinkedList methods written in MyLinkedList.java
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	
		try {
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {			
		}
		list1.add(63);
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 1 is correct ", (Integer)63, list1.get(1));
		assertEquals("Remove: check size is correct ", 2, list1.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
       
		MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
		try {
			testList.add(null);
			fail("Check adding null element");
		}
		catch (NullPointerException e) {			
		}
		
		testList.add(5);
		assertEquals("Add: check 5 added correctly ", (Integer)5, testList.get(0));
		testList.add(23);
		testList.add(45);
		assertEquals("Add: check size is correct ", (Integer)3, (Integer)testList.size());
		assertEquals("Add: check 45 added correctly to the end", (Integer)45, testList.get(testList.size()-1));
		testList.add(90);
		assertEquals("Add: check size is correct ", (Integer)4, (Integer)testList.size());
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		MyLinkedList<Integer> list2 = new MyLinkedList<Integer>();
		assertEquals("Size: check size is correct ", (Integer)0, (Integer)list2.size());
		list2.add(1);
		assertEquals("Size: check size is correct ", (Integer)1, (Integer)list2.size());
		list2.add(1);
		list2.add(1);
		list2.remove(1);
		assertEquals("Size: check size is correct ", (Integer)2, (Integer)list2.size());
	}

	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		MyLinkedList<Integer> list2 = new MyLinkedList<Integer>();
		list2.add(0, 1);
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(2, 10);
		try {
			list2.add(5, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list2.add(2, null);
			fail("Check adding null element");
		}
		catch (NullPointerException e) {			
		}
		
		assertEquals("Add: check 10 added correctly at index 2", (Integer)10, list2.get(2));
		assertEquals("Add: check index 1 didn't change", (Integer)2, list2.get(1));
		assertEquals("Add: check index 3 is previous index 2", (Integer)3, list2.get(3));
		assertEquals("Add: check size is now 5", (Integer)5, (Integer)list2.size());
		list2.add(0, 20);
		assertEquals("Add: check 20 added correctly at head", (Integer)20, list2.get(0));
		assertEquals("Add: check index 1 is previous index 0", (Integer)1, list2.get(1));
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			list1.set(5, 10);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.set(2, null);
			fail("Check adding null element");
		}
		catch (NullPointerException e) {			
		}
	    assertEquals("Set: check 21 returned correctly ", (Integer)21, list1.set(1, 5));
	    assertEquals("Set: check 5 added correctly ", (Integer)5, list1.get(1));
	}
	
}
