package assignment5B;

/**
 * One node in the Huffman tree.
 * 
 * @author i7
 *
 */
public class Node {

	private char data;
	private int freq;
	private Node left;
	private Node right;

	public Node(char data, int freq) {
		this.data = data;
		this.freq = freq;
		left = null;
		right = null;
	}
}
