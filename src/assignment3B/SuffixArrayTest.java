package assignment3B;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the trie.
 * 
 * @author Anton Gustafsson
 *
 */
public class SuffixArrayTest {

	private SuffixArray st;

	@Before
	public void setUp() {
		st = new SuffixArray("banana");
	}

	@Test
	public void testSearch1() {
		st.printLongestMatch("nan");
	}

}
