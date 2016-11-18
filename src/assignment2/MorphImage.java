package assignment2;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Program that morphs an image by changing it's raster values.
 * 
 * @author Anton Gustafsson
 *
 */
public class MorphImage {
	private final String URL0 = "pic1.jpg";
	private final String URL1 = "template1.png";
	private final String URL2 = "template2.png";
	private final String URL3 = "template3.png";

	public static void main(String[] args) {
		try {
			new MorphImage();
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public MorphImage() throws IOException, URISyntaxException {
		//A
		BufferedImage img0 = readImage(URL0);
		float contrast = 1.2f; // 1 is standard
		int brightness = 3; // 1 is standard
		// pointOp(img0, contrast, brightness);
		//B
		BufferedImage img1 = readImage(URL1);
		BufferedImage img2 = readImage(URL2);
		BufferedImage img3 = readImage(URL3);
		mergeImages(img1, img2, img3);
	}

	/**
	 * Read and create a bufferedImage from an URL.
	 * 
	 * @param URL
	 * @return
	 * @throws URISyntaxException
	 */
	private BufferedImage readImage(String URL) throws URISyntaxException {
		
		URL defaultImage = MorphImage.class.getResource(URL);
		File file = new File(defaultImage.toURI());
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Uses a BufferedImage to create a new copy of it with altered brightness
	 * and contrast
	 * 
	 * @param img
	 * @throws IOException
	 */
	private void pointOp(BufferedImage img0, float contrast, int brightness) throws IOException {
		int width = img0.getWidth();
		int height = img0.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster inraster = img0.getRaster();
		WritableRaster outraster = image.getRaster();
		int[] pixel = new int[3];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				// System.out.println(valueR);
				// System.out.println(valueG);
				// System.out.println(valueB);

				// outraster.setSample(i, j, 0, (brig * valueR) + brig);
				// outraster.setSample(i, j, 1, (brig * valueG) + brig);
				// outraster.setSample(i, j, 2, (brig * valueB) + brig);
				
				int valueR = inraster.getSample(i, j, 0);
				int valueG = inraster.getSample(i, j, 1);
				int valueB = inraster.getSample(i, j, 2);
				pixel = inraster.getPixel(i, j, new int[3]);

				pixel[0] = (int) ((contrast * valueR) + brightness);
				pixel[1] = (int) ((contrast * valueG) + brightness);
				pixel[2] = (int) ((contrast * valueB) + brightness);

				//Checks if we are below 0 or above 255.
				if (pixel[0] > 255)
					pixel[0] = 255;
				else if (pixel[0] < 0)
					pixel[0] = 0;

				if (pixel[1] > 255)
					pixel[1] = 255;
				else if (pixel[1] < 0)
					pixel[1] = 0;

				if (pixel[2] > 255)
					pixel[2] = 255;
				else if (pixel[2] < 0)
					pixel[2] = 0;

				outraster.setPixel(i, j, pixel);
			}

		ImageIO.write(image, "jpg", new File("BrightContrast" + URL0));
		System.out.println("Altered brightness and contrast.");
	}

	/**
	 * Merges 3 images with alpha channel.
	 * 
	 * @param img1
	 * @param img2
	 * @param img3
	 */
	private void mergeImages(BufferedImage img0, BufferedImage img1, BufferedImage img2) {
		int width0 = img0.getWidth();
		int height0 = img0.getHeight();
		WritableRaster inraster0 = img0.getRaster();

		int width1 = img1.getWidth();
		int height1 = img1.getHeight();
		WritableRaster inraster1 = img1.getRaster();

		// int width2 = img2.getWidth();
		// int height2 = img2.getHeight();
		// WritableRaster inraster2 = img2.getRaster();

		BufferedImage image = new BufferedImage(width0, height0, BufferedImage.TYPE_BYTE_BINARY);
		WritableRaster outraster = image.getRaster();

		/**
		 * Now we got RGB values for all pixels 
		 */
		for (int i = 0; i < width0; i++) {
			for (int j = 0; j < height0; j++) {

				int valueA0 = inraster0.getSample(i, j, 0);
				int valueA1 = inraster1.getSample(i, j, 0);
				int newAlpha = valueA0 + valueA1;

				if (newAlpha > 255) {
					newAlpha = 255;
				} else if (newAlpha < 0) {
					newAlpha = 0;
				}
				outraster.setSample(i, j, 0, newAlpha);
			}
		}

		try {
			ImageIO.write(image, "PNG", new File("MergedImages " + URL0));
			System.out.println("Merged three images.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
