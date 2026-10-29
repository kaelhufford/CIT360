package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import AbstractDataTypes.LinkedLists.LinkedList;

public class LinkedListTest {

	@Test
	public void testInit() {
		LinkedList<String> list = new LinkedList<>();
		assertNotNull(list);	
		assertEquals(0, list.size());
	}
	
	@Test
	public void testAddFirst() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addFront(elements.substring(i, i+1));
			assertEquals(elements.substring(i, i+1), list.get(0));
			assertEquals(i+1, list.size());
		}
		
	}
	
	@Test
	public void testRemoveFirst() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addFront(elements.substring(i, i+1));
		}
		
		for(int i = elements.length(); i > 0; i--) {
			String tmp = list.removeFront();
			
			assertEquals(elements.substring(i-1, i), tmp);
		}
		assertEquals(0, list.size());
	}
	
	@Test
	public void testRemoveLast() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements.substring(i, i+1));
		}
		
		for(int i = elements.length()-1; i >= 0; i--) {
			String tmp = list.removeBack();
			
			assertEquals(elements.substring(i, i+1), tmp);
		}
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGet() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements.substring(i, i+1));
		}
		
		for(int i = 0; i < elements.length(); i++) {
			String tmp = list.get(i);
			
			assertEquals(elements.substring(i, i+1), tmp);
		}
	}
	
	@Test
	public void testContains() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements.substring(i, i+1));
		}
		
		for(int i = 0; i < elements.length(); i++) {
			assertTrue(list.contains(elements.substring(i, i+1)));
		}
	}
	
	@Test
	public void testSize() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements.substring(i, i+1));
		}
		
		assertEquals(elements.length(), list.size());
	}
}
