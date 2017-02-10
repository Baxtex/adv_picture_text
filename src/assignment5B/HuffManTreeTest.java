package assignment5B;

import org.junit.Test;

/**
 * Tests the HuffmanTree.
 * 
 * @author Anton Gustafsson
 *
 */
public class HuffManTreeTest {

	@Test
	public void testSearch2() {
		HuffmanTree ht = new HuffmanTree("banana");
		ht.printEncoding();

	}

	@Test
	public void testSearch3() {
		HuffmanTree ht = new HuffmanTree("abcccccccdfghh");
		ht.printEncoding();

	}

	@Test
	public void testSearch4() {
		HuffmanTree ht = new HuffmanTree("abcdefghc");
		ht.printEncoding();

	}
}
