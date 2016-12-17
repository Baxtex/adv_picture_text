package assignment5B;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class HuffmanTree {

	private PriorityQueue<Node> pq;
	private Node[] nodeArray;
	private String s;
	private char charArr[];
	private int charFreqs[];

	public HuffmanTree(String s) {
		this.s = s;
		buildArrays();
		pq = new PriorityQueue<Node>(charFreqs.length);
		nodeArray = new Node[charArr.length];

		// Create Nodes from the chars and occurrences and put them in a array.
		for (int i = 0; i < charArr.length; i++) {
			nodeArray[i] = new Node(charArr[i], charFreqs[i]);
		}
		buildTree();
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
	 * Finds occurencess of each letter in the given string and initializes the
	 * arrays containing the letters and their frequencies.
	 * 
	 * @param s
	 */
	private void buildArrays() {
		List<String> original = s.chars().mapToObj(i -> (char) i).map(String::valueOf).collect(Collectors.toList());
		List<String> duplicateRemoved = s.chars().mapToObj(i -> (char) i).map(String::valueOf).distinct().collect(Collectors.toList());

		ArrayList<Integer> Occurrences = new ArrayList<>();
		int counter = 1;
		for (String aList : duplicateRemoved) {
			counter = (int) original.stream().filter(s1 -> s1.equals(aList)).count();
			Occurrences.add(counter);
		}
		// Assign the values to the arrays:
		charFreqs = new int[duplicateRemoved.size()];
		charArr = new char[duplicateRemoved.size()];
		for (int i = 0; i < charArr.length; i++) {
			charArr[i] = duplicateRemoved.get(i).charAt(0);
			charFreqs[i] = Occurrences.get(i);
		}
	}

	/**
	 * Loops through the nodes and arrays to print their values.
	 */
	public void printEncoding() {
		Map<Character, String> ht = new Hashtable<Character, String>();
		System.out.println("Char  Freq  Code");
		for (Node n : nodeArray) {
			System.out.println("'" + n.getData() + "' -- " + n.getFreq() + " -- '" + n.getCode() + "'");
			ht.put(n.getData(), n.getCode());
		}
		System.out.println("'" + s + "'" + " is encoded as:");
		char[] arr = s.toCharArray();
		for (char c : arr) {
			System.out.println(c + "=" + ht.get(c) + " ");
		}
		System.out.println("\n \n \n");
	}
}
