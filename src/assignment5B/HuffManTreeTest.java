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
	public void testSearch1() {
		System.out.println("Test 1");
		HuffmanTree ht = new HuffmanTree("abcdefghcccc");
		ht.printEncoding();

	}

	@Test
	public void testSearch2() {
		System.out.println("Test 2");
		HuffmanTree ht = new HuffmanTree("abcccccccdddddfgh");
		ht.printEncoding();

	}

	@Test
	public void testSearch3() {
		System.out.println("Test 3");
		HuffmanTree ht = new HuffmanTree("Hejsan detta är ett test!");
		ht.printEncoding();

	}
}
