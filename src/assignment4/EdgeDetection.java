package assignment4;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import assignment2.MorphImage;

public class EdgeDetection {

	private final int THRESHOLD = 65;// Decides at which threshold for black and
										// white. Needs modifying for different
										// images.
	private final int MAX = 255;// Represent white;
	private final int MIN = 0;// Represent black;

	public static void main(String[] args) {
		new EdgeDetection();
	}

	public EdgeDetection() {
		String url0 = "rubik.jpg";
		detect(url0);
	}

	/**
	 * Detects edges.
	 * 
	 * @param url - filepath to the iamge.
	 */
	private void detect(String url) {
		BufferedImage orgImage = readImage(url);
		int width = orgImage.getWidth();
		int height = orgImage.getHeight();
		BufferedImage resImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
		WritableRaster inraster = orgImage.getRaster();
		WritableRaster outraster = resImage.getRaster();

		System.out.println("Size: " + width + "X" + height + "Pixels");
		// Loop through every pixel, ignores the edges as these will throw out
		// of
		// bounds.
		for (int i = 1; i < width - 2; i++) {
			for (int j = 1; j < height - 2; j++) {

				// Compute filter result, loops over in a
				// box pattern.
				int sum = 0;
				for (int x = -1; x <= 1; x++) {
					for (int y = -1; y <= 1; y++) {
						int jy = i + y;
						int jx = j + x;
						int p = inraster.getSample(jy, jx, 0);
						sum = sum + p;
					}
				}
				int q = (int) Math.round(sum / 9.0);
				if (q > THRESHOLD) {
					q = MIN;
				} else {
					q = MAX;
				}
				outraster.setSample(i, j, 0, q);
			}
		}

		writeImage(resImage, "jpg", "EdgeDetection " + url);
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
		System.out.println("DONE");
	}

}
