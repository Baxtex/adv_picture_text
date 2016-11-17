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
	private final String URL0 = "pic1.jpg";
	private final String URL1 = "template1.png";
	private final String URL2 = "template2.png";
	private final String URL3 = "template3.png";

	public static void main(String[] args) {
		try {
			new MorphImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MorphImage() throws IOException {
		//A
		BufferedImage img0 = readImage(URL0);
		pointOp(img0, 20, 1.5f);
		//B
		BufferedImage img1 = readImage(URL1);
		BufferedImage img2 = readImage(URL2);
		BufferedImage img3 = readImage(URL3);
		// mergeImages(img1, img2, img3);
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
	 * Uses a BufferedImage to create a new copy of it with altered brightness
	 * and contrast
	 * 
	 * @param img
	 * @throws IOException
	 */
	private void pointOp(BufferedImage img0, int con, float brig) throws IOException {
		int width = img0.getWidth();
		int height = img0.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		WritableRaster inraster = img0.getRaster();
		WritableRaster outraster = image.getRaster();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				int valueR = inraster.getSample(i, j, 0);
				int valueG = inraster.getSample(i, j, 1);
				int valueB = inraster.getSample(i, j, 2);

				// System.out.println(valueR);
				// System.out.println(valueG);
				// System.out.println(valueB);

				outraster.setSample(i, j, 0, (brig * valueR) + brig);
				outraster.setSample(i, j, 1, (brig * valueG) + brig);
				outraster.setSample(i, j, 2, (brig * valueB) + brig);


			}

		// RescaleOp op = new RescaleOp(brig, con, null);
		// image = op.filter(image, image);

		ImageIO.write(image, "PNG", new File("Morphed " + URL0));
		System.out.println("Morphed");
	}

	private void mergeImages(BufferedImage img1, BufferedImage img2, BufferedImage img3) {
		int width1 = img1.getWidth();
		int height1 = img1.getHeight();
		WritableRaster inraster1 = img1.getRaster();
		// ArrayList<int[]> list0 = getRGBValues(inraster1, width1, height1);
		// System.out.println("printing array");
		// Iterator<int[]> iter = list0.iterator();
		// int[] arr = null;
		// while (iter.hasNext()) {
		// arr = iter.next();
		// System.out.println(arr[0] + "," + arr[1] + "," + arr[2]);
		// }
		// System.out.println("printing done");


		int width2 = img2.getWidth();
		int height2 = img2.getHeight();
		WritableRaster inraster2 = img2.getRaster();
		// ArrayList<int[]> list1 = getRGBValues(inraster1, width1, height1);

		int width3 = img3.getWidth();
		int height3 = img3.getHeight();
		WritableRaster inraster3 = img3.getRaster();
		// ArrayList<int[]> list2 = getRGBValues(inraster1, width1, height1);

		BufferedImage image = new BufferedImage(width3, height3, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster outraster = image.getRaster();

		/**
		 * Now we got RGB values for all pixels 
		 */
		for (int i = 0; i < width3; i++) {
			for (int j = 0; j < height3; j++) {
				int R0 = inraster1.getSample(i, j, 0);
				// int G0 = inraster1.getSample(i, j, 1);
				// int B0 = inraster1.getSample(i, j, 2);

				int R1 = inraster2.getSample(i, j, 0);
				// int G1 = inraster2.getSample(i, j, 1); // int B1 =
				// inraster2.getSample(i, j, 2);

				// int R2 = inraster3.getSample(i, j, 0);
				// int G2 = inraster3.getSample(i, j, 1);
				// int B2 = inraster3.getSample(i, j, 2);

				outraster.setSample(i, j, 0, R1);
				outraster.setSample(i, j, 0, R1);
				outraster.setSample(i, j, 0, R1);
			}
		}

		try {
			ImageIO.write(image, "PNG", new File("Morphed " + URL0));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//
	// private ArrayList<int[]> getRGBValues(WritableRaster raster, int width,
	// int height) {
	// ArrayList list = new ArrayList<int[]>();
	// for (int i = 0; i < width; i++) {
	// for (int j = 0; j < height; j++) {
	// int R = raster.getSample(i, j, 0);
	// int G = raster.getSample(i, j, 1);
	// int B = raster.getSample(i, j, 2);
	// int[] arr = { R, G, B };
	// list.add(arr);
	// }
	// }
	// return list;
	// }
}
