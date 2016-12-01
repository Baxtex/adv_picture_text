package assignment3B;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the trie.
 * @author Anton Gustafsson
 *
 */
public class SuffixTreeTest {

	  private SuffixTree st;

	    @Before
	    public void setUp() {
	    	st = new SuffixTree("there once was a duck");
	    	st.add("cat");
	    	st.add("car");
	    	st.add("craft");
	    	st.add("yes");
	    	st.add("yesterday");
	    	st.add("good");
	    }

	    @Test
	    public void testInsert() {

	        assertTrue(st.find("cat"));
	        assertTrue(st.find("car"));
	        assertTrue(st.find("craft"));
	        assertTrue(st.find("yes"));
	        assertFalse(st.find("lol"));
	        assertFalse(st.find("carri"));
	    }

}
