package assignment5B;

import org.junit.Before;
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
		HuffmanTree ht = new HuffmanTree("Mississippi River");
		ht.printEncoding();

	}
	
	
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
}
