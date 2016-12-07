package assignment3A;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the trie.
 * @author Anton Gustafsson
 *
 */
public class TrieTest {

	  private Trie trie;

	    @Before
	    public void setUp() {
	        trie = new Trie();
	        trie.add("cat");
	        trie.add("car");
	        trie.add("craft");
	        trie.add("yes");
	        trie.add("yesterday");
	        trie.add("good");
	    }

	    @Test
	    public void testInsert() {
	        assertTrue(trie.find("cat"));
	        assertTrue(trie.find("car"));
	        assertTrue(trie.find("craft"));
	        assertTrue(trie.find("yes"));
	        assertFalse(trie.find("lol"));
	        assertFalse(trie.find("carri"));
	    }

}
