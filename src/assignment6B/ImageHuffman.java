package assignment6B;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

/**
 * Program that reads an image and analyses if it should be compressed with a
 * Huffman Tree.
 * 
 * @author Anton Gustafsson
 *
 */
public class ImageHuffman {

	private PriorityQueue<Node> pq;
	private Node[] nodeArray;
	private int pixelArr[];
	private int pixelFreq[];
	private List<Integer> pixels;
	private final String url = "blackRed.jpg";

	/**
	 * Starts this program.
	 * http://www.print-driver.com/stories/huffman-coding-jpeg
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new ImageHuffman();
	}

	public ImageHuffman() {
		saveColors();

		nodeArray = new Node[pixelArr.length];
		pq = new PriorityQueue<Node>(pixelFreq.length);
		// Create Nodes from the color values and occurrences and put them in a array.
		for (int i = 0; i < pixelArr.length; i++) {
			nodeArray[i] = new Node(pixelArr[i], pixelFreq[i]);
		}
		buildTree();
		printEncoding();
	}

	/**
	 * Builds the arrays.
	 * 
	 * @param URL
	 * @param contrast
	 * @param brightness
	 */
	private void saveColors() {
		BufferedImage img = readImage(url);
		int width = img.getWidth();
		int height = img.getHeight();
		int size = width * height;
		pixels = new ArrayList<Integer>(size);

		WritableRaster inraster = img.getRaster();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				pixels.add(inraster.getSample(i, j, 0));
				pixels.add(inraster.getSample(i, j, 1));
				pixels.add(inraster.getSample(i, j, 2));
			}
		countColors2();
	}

	private void countColors() {
		List<Integer> duplicateRemoved;
		duplicateRemoved = new ArrayList<Integer>(pixels);

		Set<Integer> hs = new HashSet<Integer>();
		hs.addAll(duplicateRemoved);
		duplicateRemoved.clear();
		duplicateRemoved.addAll(hs);

		pixelFreq = new int[duplicateRemoved.size()];
		pixelArr = new int[duplicateRemoved.size()];

		for (int i = 0; i < pixelArr.length; i++) {
			int p = duplicateRemoved.get(i);
			int count = Collections.frequency(pixels, p);
			pixelArr[i] = p;
			pixelFreq[i] = count;
		}

	}
	
	private void countColors2() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < pixels.size(); i++) {
			sb.append(pixels.get(i));
		}
		
		String s= sb.toString();
		
		List<String> original = s.chars().mapToObj(i -> (char) i).map(String::valueOf).collect(Collectors.toList());
		List<String> duplicateRemoved = s.chars().mapToObj(i -> (char) i).map(String::valueOf).distinct().collect(Collectors.toList());

		ArrayList<Integer> Occurrences = new ArrayList<>();
		int counter = 1;
		for (String aList : duplicateRemoved) {
			counter = (int) original.stream().filter(s1 -> s1.equals(aList)).count();
			Occurrences.add(counter);
		}
		// Assign the values to the arrays:
		pixelFreq = new int[duplicateRemoved.size()];
		pixelArr = new int[duplicateRemoved.size()];
		
		for (int i = 0; i < pixelArr.length; i++) {
			pixelArr[i] = duplicateRemoved.get(i).charAt(0);
			pixelFreq[i] = Occurrences.get(i);
		}

	}

	private void buildTree() {
		Node left, right, top;
		// Put the Nodes from the node array in a min-heap/priorityQueue.
		for (int i = 0; i < nodeArray.length; i++) {
			pq.add(nodeArray[i]);
		}

		// Find two trees with least freq and creates a new node and inserts it.
		while (pq.size() > 1) {
			left = pq.remove();
			right = pq.remove();
			int newFreq = left.getFreq() + right.getFreq();
			top = new Node('$', newFreq, left, right);
			pq.add(top);
		}
		// Now the min heap only contains one node with the character $
		// and it has all the other nodes as children.
		// It's frequency should be the same as the total
		// number of characters in the string.
		// This is our complete tree.
		encode(pq.remove(), "0");
	}

	/**
	 * Set's the encoding for every node by depth first traversal through the
	 * tree.
	 * 
	 * @param n - the current node.
	 * @param c - the code for the current node.
	 */
	private void encode(Node n, String c) {
		if (!n.isLeafNode()) {
			// While going left append 0
			// System.out.println("LEFT");
			c += 0;
			encode(n.getLeft(), c);
			// while going right, append 1
			// System.out.println("RIGHT");
			c += 1;
			encode(n.getRight(), c);
		} else {
			// System.out.println("LEAF-" + code);
			if (c.length() > 0) {
				c = c.substring(0, c.length() - 1); // Removes one zero
			}
			// Set the code of the node.
			n.setCode(String.valueOf(c));
		}
	}

	/**
	 * Loops through the nodes and arrays to print their values.
	 */
	public void printEncoding() {
//		Map<Integer, String> ht = new Hashtable<Integer, String>();
		System.out.println("color   Freq    Code");
		for (Node n : nodeArray) {
			System.out.println("'" + n.getData() + "' -- " + n.getFreq() + " -- '" + n.getCode() + "'");
//			ht.put(n.getData(), n.getCode());
		}
	}

	/**
	 * Uses a URL to read an image into a file.
	 * 
	 * @param URL - the filepath of the image.
	 * @return BufferedImage - a BufferedImage object of an image.
	 */
	private BufferedImage readImage(String URL) {
		BufferedImage img = null;

		URL defaultImage = ImageHuffman.class.getResource(URL);
		try {
			File file = new File(defaultImage.toURI());
			img = ImageIO.read(file);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}

}
