package assignment3B;

import org.junit.Test;

/**
 * Tests the trie.
 * 
 * @author Anton Gustafsson
 *
 */
public class SuffixArrayTest {

	private SuffixArray st;

	@Test
	public void testSearch1() {
		System.out.println("Test1");
		st = new SuffixArray("lexographically lower");
		st.printLongestMatch("cally");
		st.printLongestMatch("callx");
	}

	@Test
	public void testSearch2() {
		System.out.println("Test2");
		st = new SuffixArray("banana");
		st.printLongestMatch("na");
		st.printLongestMatch("nana");
	}

}
