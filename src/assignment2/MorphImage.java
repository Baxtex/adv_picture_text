package assignment2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Program that morphs an image by changing it's raster values.
 * 
 * @author Anton Gustafsson
 *
 */
public class MorphImage {
	private BufferedImage image;


	public static void main(String[] args) {
		String URL = "/pic2.png";
		try {
			new MorphImage(URL);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MorphImage(String URL) throws IOException {
		System.out.println("Morphing image.");
		BufferedImage img = ImageIO.read(new File(URL));
		int width = img.getWidth();
		int height = img.getHeight();
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster inraster = img.getRaster();
		WritableRaster outraster = this.image.getRaster();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int value = inraster.getSample(i, j, 0);
				outraster.setSample(i, j, 0, 255 - value);
			}
		ImageIO.write(image, "PNG", new File("Morphed" + URL + ".png"));
	}

}
