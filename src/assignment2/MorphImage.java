package assignment2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Program that morphs an image by changing it's raster values.
 * 
 * @author Anton Gustafsson
 *
 */
public class MorphImage {
	private final String URL = "pic1.jpg";

	public static void main(String[] args) {
		try {
			new MorphImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MorphImage() throws IOException {
		BufferedImage img = readImage(URL);
		pointOp(img, 50, 75);
	}

	/**
	 * Read and create a bufferedImage from an URL.
	 * 
	 * @param URL
	 * @return
	 */
	private BufferedImage readImage(String URL) {
		URL url = getClass().getResource(URL);
		File file = new File(url.getPath());
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;

	}

	/**
	 * Uses a BufferedImage to create a new copy of it with altered values.
	 * 
	 * @param img
	 * @throws IOException
	 */
	private void pointOp(BufferedImage img, int con, int brig) throws IOException {
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster inraster = img.getRaster();
		WritableRaster outraster = image.getRaster();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int value = inraster.getSample(i, j, 0);
				outraster.setSample(i, j, 0, 255 - value);
			}
		ImageIO.write(image, "PNG", new File("Morphed " + URL));
		System.out.println("Morphed");

	}


}
