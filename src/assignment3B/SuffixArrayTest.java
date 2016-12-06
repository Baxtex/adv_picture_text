package assignment3B;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the trie.
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
	    public void testSearch() {
	    	st.printLongestPrefix("banana");
	    }

}
