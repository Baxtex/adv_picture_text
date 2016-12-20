package assignment2;

import org.junit.Test;

/**
 * Tests the image modifier program. The first two test test the a part, the
 * last one the b part.
 * 
 * @author Anton Gustafsson
 *
 */
public class ImageModifierTest {

	@Test
	public void testBrightCont1() {
		ImageModifier im = new ImageModifier("mushroom.png", 1.2f, 3);
	}

	@Test
	public void testBrightCont2() {
		ImageModifier im = new ImageModifier("ernst_pic1.jpg", 1.5f, 10);
	}

	@Test
	public void testMerge1() {
		ImageModifier im = new ImageModifier("template1.png", "template2.png");
	}
}
