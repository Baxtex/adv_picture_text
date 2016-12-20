package assignment6B;

/**
 * One node in the Huffman tree.
 * 
 * @author Anton Gustafsson
 *
 */
public class Node implements Comparable<Node> {

	private int data;
	private int freq;
	private Node left;
	private Node right;
	private String code;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
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
			return freq - ((Node) o).freq;
		}
		return -1;
	}
}
