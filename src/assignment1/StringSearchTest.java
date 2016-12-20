package assignment1;

import org.junit.Test;

/**
 * Tests the HuffmanTree.
 * 
 * @author Anton Gustafsson
 *
 */
public class StringSearchTest {

	@Test
	public void testSearch1() {
		StringSearch ss = new StringSearch("aaaaaaaab", "aaaab", "naive");
	}

	@Test
	public void testSearch2() {
		StringSearch ss = new StringSearch("aaaaaaaab", "aaaab", "rabin");
	}

	@Test
	public void testSearch3() {
		StringSearch ss = new StringSearch("aaaaaaaab", "aaaab", "kmp");
	}
}
