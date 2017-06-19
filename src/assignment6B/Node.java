package assignment6B;

/**
 * One node in the Huffman tree. Has getters and setters and a implementation of
 * the Comparable interface so we can compare the nodes when in the heap.
 * 
 * @author Anton Gustafsson
 *
 */
public class Node implements Comparable<Node> {

	private int data, freq;
	private Node left, right;
	private int code = -1;
	private int length = 0;

	public Node(int data, int freq) {
		this.data = data;
		this.freq = freq;
	}

	public Node(int data, int freq, Node left, Node right) {
		this.data = data;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}

	public int getData() {
		return data;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getFreq() {
		return freq;
	}

	/**
	 * If left or right child is null, this is a leaf.
	 * 
	 * @return
	 */
	public boolean isLeafNode() {
		return left == null && right == null;
	}

	/**
	 * Needs to be able to compare nodes in the order of their frequencies for
	 * the heap.
	 */
	@Override
	public int compareTo(Node o) {
		if (o instanceof Node) {
			return freq - o.freq;
		}
		return -1;
	}
	
	/**
	 * Override ToString method to pretty print.
	 * @return
	 */
	public String ToString() {

		String str = Integer.toBinaryString(code);
		String leadZero = "";

		for (int i = str.length(); i < length; i++) {
			leadZero += "0";
		}
		return leadZero + str;
	}
}
