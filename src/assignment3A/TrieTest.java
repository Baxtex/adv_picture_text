package assignment3A;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the trie by adding diffrent wors and then tries to find them.
 * 
 * @author Anton Gustafsson
 *
 */
public class TrieTest {

	private Trie trie;

	@Before
	public void addWords() {
		trie = new Trie();
		trie.add("cat");
		trie.add("car");
		trie.add("craft");
		trie.add("yes");
		trie.add("yesterday");
		trie.add("good");
	}

	@Test
	public void testFind() {
		assertTrue(trie.find("cat"));
		assertTrue(trie.find("ca"));
		assertTrue(trie.find("car"));
		assertTrue(trie.find("craft"));
		assertTrue(trie.find("yes"));
		assertFalse(trie.find("lol"));
		assertFalse(trie.find("carri"));
	}
}
