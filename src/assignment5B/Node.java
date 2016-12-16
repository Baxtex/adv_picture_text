package assignment5B;

/**
 * One node in the Huffman tree.
 * 
 * @author i7
 *
 */
public class Node implements Comparable<Node> {

	private char data;
	public char getData() {
		return data;
	}

	private int freq;
	private Node left;
	private Node right;
	private String code;



	public Node(char data, int freq) {
		this.data = data;
		this.freq = freq;
	}

	public Node(char data, int freq, Node left, Node right) {
		this.data = data;
		this.freq = freq;
		this.left = left;
		this.right = right;
	}
	
	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Override
	public int compareTo(Node o) {
		if (o instanceof Node) {
			return freq - ((Node) o).freq;
		}
		return -1;

	}

	public int getFreq() {
		return freq;
	}

	public boolean isLeafNode() {
		return left == null && right == null;
	}

	@Override
	public String toString() {
		return "Node " + data + ", " + freq + ", " + code;
	}

}
