package assignment6B;

import org.junit.Test;

/**
 * Tests the HuffmanTree with different images
 * 
 * @author Anton Gustafsson
 *
 */
public class ImageHuffManTreeTest {

	@Test
	public void testSearch1() {
		ImageHuffmanTree iht = new ImageHuffmanTree("blackWhite.jpg");
	}

	@Test
	public void testSearch2() {
		ImageHuffmanTree iht = new ImageHuffmanTree("ernst_pic1.jpg");
	}
}
