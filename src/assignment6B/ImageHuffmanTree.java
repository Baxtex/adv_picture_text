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
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import javax.imageio.ImageIO;

/**
 * Program that reads an image and analyses if it should be compressed with a
 * Huffman Tree.
 * 
 * @author Anton Gustafsson
 *
 */
public class ImageHuffmanTree {

	private String url;
	private PriorityQueue<Node> pq;
	private Node[] nodeArray;
	private List<Integer> pixels;
	private int[] pixelArr, pixelFreq;
	private int imageSize, length;

	/**
	 * Constructor that initializes variables and arrays.
	 */
	public ImageHuffmanTree(String url) {
		System.out.println("image to encode: " + url);
		this.url = url;
		calculateColors();
		nodeArray = new Node[pixelArr.length];
		pq = new PriorityQueue<Node>(pixelFreq.length);
		// Create Nodes from the color values and occurrences and put them in a
		// array.
		for (int i = 0; i < pixelArr.length; i++) {
			nodeArray[i] = new Node(pixelArr[i], pixelFreq[i]);
		}
		buildTree();
		printEncoding();
	}

	/**
	 * Build the huffman tree with the help of a heap.
	 */
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
		encode(pq.remove(), 0);
	}

	/**
	 * Sets the encoding for every node by depth first traversal through the
	 * tree. Used recursivly and using bitwise operators.
	 * 
	 * @param n
	 *            - the current node.
	 * @param c
	 *            - the code for the current node.
	 */
	private int encode(Node n, int c) {

		if (!n.isLeafNode()) {
			length++;
			// While going left append 0
			c = c << 1;
			c = encode(n.getLeft(), c);
			// while going right, append 1
			length++;
			c = (c << 1) | 1;
			c = encode(n.getRight(), c);
		} else {
			// Set the code of the node.
			n.setLength(length);
			n.setCode(c);
		}
		length--;
		return c >> 1;
	}

	/**
	 * Builds a list containing all three color values.
	 * 
	 * @param URL
	 * @param contrast
	 * @param brightness
	 */
	private void calculateColors() {
		BufferedImage img = readImage(url);
		int width = img.getWidth();
		int height = img.getHeight();
		imageSize = (width * height) * 24;
		int listSize = (width * height) * 3;
		pixels = new ArrayList<Integer>(listSize);

		WritableRaster inraster = img.getRaster();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				pixels.add(inraster.getSample(i, j, 0));
				pixels.add(inraster.getSample(i, j, 1));
				pixels.add(inraster.getSample(i, j, 2));
			}
		countOccurences();
	}

	/**
	 * Counts each color and then removes them. Builds two arrays that contain the freq and color.
	 */
	private void countOccurences() {
		List<Integer> duplicateRemoved = new ArrayList<Integer>(pixels);
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

	/**
	 * Loops through the nodes and arrays to pretty print their values. Also
	 * does some calculations to show the number of bits and the percentage.
	 */
	public void printEncoding() {
		System.out.println("color   Freq    Code   ");
		int bits = 0;
		for (Node n : nodeArray) {
			String s = n.ToString();
			bits += n.getFreq() * s.length();
			System.out.println("" + n.getData() + " :: " + n.getFreq() + " :: " + s + "");
		}
		int difference = (imageSize - bits);
		float p2 = (1 - (bits * 1f / imageSize)) * 100;
		System.out.println("Image: '" + url + "'");
		System.out.println("original : compressed : difference :  percent saved");
		System.out.println(imageSize + "::" + bits + "::" + difference + "::" + p2 + "%\n");
	}

	/**
	 * Uses a URL to read an image into a file object.
	 * 
	 * @param URL - the filepath of the image.
	 * @return BufferedImage - a BufferedImage object of an image.
	 */
	private BufferedImage readImage(String URL) {
		BufferedImage img = null;
		URL defaultImage = ImageHuffmanTree.class.getResource(URL);
		try {
			File file = new File(defaultImage.toURI());
			img = ImageIO.read(file);
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		return img;
	}
}
