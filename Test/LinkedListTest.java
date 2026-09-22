package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

class LinkedListTest {

	@Test
	void testInit() {
		LinkedList list = new LinkedList();
		assertNotNull(list);	
	}
	
	@Test
	void testAddFirst() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList();

		for(int i = 0; i < elements.length(); i++) {
			list.addFront(elements);
		}
		
	}
	
	@Test
	void testRemoveFirst() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addFront(elements);
		}
		
		for(int i = elements.length(); i > 0; i--) {
			String tmp = list.removeFront();
			
			assertEquals(tmp.charAt(0), elements.substring(i-1, i).charAt(0));
		}
	}
	
	@Test
	void testRemoveLast() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addFront(elements);
		}
		
		for(int i = 0; i < elements.length(); i++) {
			String tmp = list.removeBack();
			
			assertEquals(tmp.charAt(0), elements.charAt(i));
		}
	}
	
	@Test
	void testGet() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements);
		}
		
		for(int i = 0; i < elements.length(); i++) {
			String tmp = list.get(i);
			
			assertEquals(tmp.charAt(0), elements.charAt(i));
		}
	}
	
	@Test
	void testContains() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements);
		}
		
		for(int i = 0; i < elements.length(); i++) {
			assertTrue(list.contains(elements.substring(i, i+1)));
		}
	}
	
	@Test
	void testSize() {
		String elements = "ABCDEFGHI";
		LinkedList<String> list = new LinkedList<>();

		for(int i = 0; i < elements.length(); i++) {
			list.addBack(elements.substring(i, i+1));
		}
		
		assertEquals(list.size(), elements.length());
	}
}
