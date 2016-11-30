package assignment3;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TrieTest {

	  private Trie trie;

	    @Before
	    public void setUp() {
	        trie = new Trie();
	        trie.add("at");
	        trie.add("Hello");
	        trie.add("Been");
	        trie.add("yes");
	        trie.add("water");
	        trie.add("be");
	    }

	    @Test
	    public void testInsert() {

	        assertTrue(trie.find("water"));
	        assertTrue(trie.find("at"));
	        assertFalse(trie.find("Beat"));
	        assertFalse(trie.find("Test"));
	    }

}
