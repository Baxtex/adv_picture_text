package assignment2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Program that morphs an image by changing it's individual pixel values.
 * 
 * 
 * @author Anton Gustafsson
 *
 */
public class MorphImage {
	private final int MAX = 255;
	private final int MIN = 0;

	public static void main(String[] args) {
			new MorphImage();
	}

	/**
	 * Constructor that calls on A method and B method.
	 */
	public MorphImage() {
		String url0 = "mushroom.png";
		String url1 = "template1.png";
		String url2 = "template2.png";
		float contrast = 1.2f; // 1 is standard
		int brightness = 3; // 1 is standard

		// A
		pointOp(url0, contrast, brightness);
		// B
		mergeImages(url1, url2);
	}

	/**
	 * Multiplies and adds a constant value to every pixel to alter the image's
	 * brightness and contrast.
	 * 
	 * @param URL - the filepath for the image to alter.
	 * @param contrast - the contrast to multiply each pixel with.
	 * @param brightness - the value to add each pixel with.
	 */
	private void pointOp(String URL, float contrast, int brightness) {
		BufferedImage img = readImage(URL);
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster inraster = img.getRaster();
		WritableRaster outraster = image.getRaster();
		int[] pixel;
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int valueR = inraster.getSample(i, j, 0);
				int valueG = inraster.getSample(i, j, 1);
				int valueB = inraster.getSample(i, j, 2);

				pixel = new int[3];
				pixel[0] = (int) ((contrast * valueR) + brightness);
				pixel[1] = (int) ((contrast * valueG) + brightness);
				pixel[2] = (int) ((contrast * valueB) + brightness);

				// Check if we are above or belov max and min limit.
				for (int x = 0; x < 3; x++) {
					if (pixel[x] > MAX)
						pixel[x] = MAX;
					else if (pixel[x] < MIN)
						pixel[x] = MIN;
				}
				outraster.setPixel(i, j, pixel);
			}
		writeImage(image, "JPG", "BrightContrast " + URL);
	}

	/**
	 * Merges 2 grayscale images pixel values and creates a new image with them.
	 * 
	 * @param URLA - the filepath of the first image.
	 * @param URLB - the filepath of the second image.
	 */
	private void mergeImages(String URLA, String URLB) {
		BufferedImage img0 = readImage(URLA);
		BufferedImage img1 = readImage(URLB);

		int width = img0.getWidth();
		int height = img0.getHeight();

		WritableRaster inraster0 = img0.getRaster();
		WritableRaster inraster1 = img1.getRaster();

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		WritableRaster outraster = image.getRaster();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				int valueA0 = inraster0.getSample(i, j, 0);
				int valueA1 = inraster1.getSample(i, j, 0);
				int newValue = valueA0 + valueA1;

				// Check if we are above or belov max and min limit.
				if (newValue > MAX) {
					newValue = MAX;
				} else if (newValue < MIN) {
					newValue = MIN;
				}
				outraster.setSample(i, j, 0, newValue);
			}
		}
		writeImage(image, "png", "MergedImages " + URLA);
	}

	/**
	 * Uses a URL to read an image into a file.
	 * 
	 * @param URL - the filepath of the image.
	 * @return BufferedImage - a BufferedImage object of an image.
	 */
	private BufferedImage readImage(String URL) {
		BufferedImage img = null;

		URL defaultImage = MorphImage.class.getResource(URL);
		try {
			File file = new File(defaultImage.toURI());
			img = ImageIO.read(file);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Creates a new image file from the given BufferedImage.
	 * 
	 * @param img - BufferedImage that will get written.
	 * @param fileExt - file extension to the new file.
	 * @param title - name and filepath to the new file.
	 */
	private void writeImage(BufferedImage img, String fileExt, String title) {
		try {
			ImageIO.write(img, fileExt, new File(title));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Morphed image: " +"src/assignment2/"+ title);
	}
}
