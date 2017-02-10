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
	public void testImageHFT1() {
		System.out.println("Test1");
		new ImageHuffmanTree("ernst_pic1.jpg");
	}

	@Test
	public void testImageHFT2() {
		System.out.println("Test2");
		new ImageHuffmanTree("blackWhite.jpg");
	}
}
