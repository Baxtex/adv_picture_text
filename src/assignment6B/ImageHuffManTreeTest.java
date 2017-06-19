package assignment6B;

import org.junit.Test;

/**
 * Tests the HuffmanTree with different images
 * 
 * @author Anton Gustafsson
 *
 */
public class ImageHuffManTreeTest {

	//Tests encoding an image with many colurs
	@Test
	public void testImageHFT1() {
		System.out.println("Test1-Complex image");
		new ImageHuffmanTree("bc1.jpg");
	}

	//Tests encoding an image with few colors.
	@Test
	public void testImageHFT2() {
		System.out.println("Test2-Simple image");
		new ImageHuffmanTree("white_black.jpg");
	}
}
