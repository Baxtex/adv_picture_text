package assignment4;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Program detects edges in a given image, and outputs a new image with only the
 * edges.
 * 
 * @author Anton Gustafsson
 *
 */
public class EdgeDetection {

	private int threshold;
	private final int WHITE = 255;
	private final int BLACK = 0;

	/**
	 * Initializes filepath and threshold.
	 * 
	 * @param url0 - String the name of the image.
	 * @param threshold - int the threshold for deciding if black or white.
	 */
	public EdgeDetection(String url, int threshold) {
		this.threshold = threshold;
		detect(url);
	}

	/**
	 * Detects edges with the help of sobel operator. For each pixel in the
	 * image, except the edges, get the srrunding pixels in a 3x3 pattern. Then
	 * calculate the magnitude for that block and check it with the threshold.
	 * 
	 * @param url - file name of the image.
	 */
	private void detect(String url) {
		BufferedImage orgImage = readImage(url);
		int width = orgImage.getWidth();
		int height = orgImage.getHeight();
		BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		WritableRaster inraster = orgImage.getRaster();
		WritableRaster outraster = resImage.getRaster();
		int[][] pixels = new int[3][3];
		System.out.println("Name: " + url + " Size: " + width + "X" + height + " Pixels, " + "Threshold: " + threshold);

		for (int i = 1; i < width - 2; i++) {
			for (int j = 1; j < height - 2; j++) {

				pixels[0][0] = inraster.getSample(i - 1, j - 1, 0);
				pixels[0][1] = inraster.getSample(i - 1, j, 0);
				pixels[0][2] = inraster.getSample(i - 1, j + 1, 0);
				pixels[1][0] = inraster.getSample(i, j - 1, 0);
				pixels[1][2] = inraster.getSample(i, j + 1, 0);
				pixels[2][0] = inraster.getSample(i + 1, j - 1, 0);
				pixels[2][1] = inraster.getSample(i + 1, j, 0);
				pixels[2][2] = inraster.getSample(i + 1, j + 1, 0);

				int value = (int) mag(pixels);
				value = checkMaxMin(value);
				outraster.setSample(i, j, 0, value);
			}
		}
		writeImage(resImage, "jpg", "EdgeDetection " + url);
	}

	/**
	 * Calculates the magnitude.
	 * 
	 * @param pixelMatrix
	 * @return the magnitude.
	 */
	private double mag(int[][] pixelMatrix) {
		int iX = (pixelMatrix[0][0] * -1) + (pixelMatrix[0][1] * -2) + (pixelMatrix[0][2] * -1) + (pixelMatrix[2][0])
				+ (pixelMatrix[2][1] * 2) + (pixelMatrix[2][2] * 1);

		int jY = (pixelMatrix[0][0]) + (pixelMatrix[0][2] * -1) + (pixelMatrix[1][0] * 2) + (pixelMatrix[1][2] * -2)
				+ (pixelMatrix[2][0]) + (pixelMatrix[2][2] * -1);

		return Math.sqrt((iX * iX) + (jY * jY));
	}

	/**
	 * Uses a URL to read an image into a file.
	 * 
	 * @param URL - the filepath of the image.
	 * @return BufferedImage - a BufferedImage object of an image.
	 */
	private BufferedImage readImage(String URL) {
		BufferedImage img = null;

		URL defaultImage = EdgeDetection.class.getResource(URL);
		try {
			File file = new File(defaultImage.toURI());
			img = ImageIO.read(file);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Check if we are above or below max and min limit of the pixel value.
	 * 
	 * @param p - pixel value
	 * @return - pixel value
	 */
	private int checkMaxMin(int p) {
		if (p > threshold) {
			p = WHITE;
		} else {
			p = BLACK;
		}
		return p;
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
		System.out.println("Edge Detected: " + title + "\n");
	}
}
