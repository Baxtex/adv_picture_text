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
	private char chars[];
	private int freqs[];
	private int length;

	/**
	 * Constructor that initializes variables and arrays.
	 * 
	 * @param s- the string to compress.
	 */
	public HuffmanTree(String s) {
		System.out.println("String to encode: '" + s + "'");
		this.s = s;
		buildArrays();
		pq = new PriorityQueue<Node>(freqs.length);
		nodeArray = new Node[chars.length];

		// Create Nodes from the chars and occurrences and put them in a array.
		for (int i = 0; i < chars.length; i++) {
			nodeArray[i] = new Node(chars[i], freqs[i]);
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
		encode(pq.remove(), 0);
	}

	/**
	 * Set's the encoding for every node by depth first traversal through the
	 * tree. Used recursivly and using bitwise operators.
	 * 
	 * @param n - the current node.
	 * @param c - the code for the current node.
	 */
	private int encode(Node n, int c) {

		if (!n.isLeafNode()) {
			System.out.println("COUNTED");
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
			System.out.println("The code as int " + c + " and the length is " + length + "  char is " + n.getData());
			n.setLength(length);
			n.setCode(c);

	
		}
		length--;
		return c >> 1;
	}
	
	

	/**
	 * Finds occurencess of each letter in the given string and initializes the
	 * arrays containing the letters and their frequencies.
	 * 
	 * @param s
	 */
	private void buildArrays() {
		List<String> original = s.chars().mapToObj(i -> (char) i).map(String::valueOf).collect(Collectors.toList());
		List<String> duplicateRemoved = s.chars().mapToObj(i -> (char) i).map(String::valueOf).distinct()
				.collect(Collectors.toList());

		ArrayList<Integer> Occurrences = new ArrayList<>();
		int counter = 1;
		for (String aList : duplicateRemoved) {
			counter = (int) original.stream().filter(s1 -> s1.equals(aList)).count();
			Occurrences.add(counter);
		}
		// Assign the values to the arrays:
		freqs = new int[duplicateRemoved.size()];
		chars = new char[duplicateRemoved.size()];
		for (int i = 0; i < chars.length; i++) {
			chars[i] = duplicateRemoved.get(i).charAt(0);
			freqs[i] = Occurrences.get(i);
		}
	}

	/**
	 * Just for pretty printing all the values. Loops through the nodes and
	 * arrays to print their values. Also does some calculations to show the
	 * number of bits and the percentage.
	 */
	public void printEncoding() {
		System.out.println("Printing encoding...");
		int CHARSIZE = 16;
		int bits = 0;
		Map<Character, String> ht = new Hashtable<>();
		System.out.println("Char,  Freq,  Code");
		for (Node n : nodeArray) {
			String str = n.getCodeAsString();
			bits += n.getFreq() * str.length();
			System.out.println("'" + n.getData() + "' -- " + n.getFreq() + " -- '" + str + "'");
			ht.put(n.getData(), str);
		}
		System.out.println("'" + s + "'" + " is encoded as:");
		char[] arr = s.toCharArray();
		for (char c : arr) {
			System.out.print(ht.get(c) + " ");
		}
		int original = (s.length() * CHARSIZE);
		int difference = (s.length() * CHARSIZE) - bits;
		float p1 = bits * 1f / original;
		float p2 = (1 - p1) * 100;
		System.out.println("\nOrg   compr  diff   percent");
		System.out.println(original + "----" + bits + "----" + difference + "----" + p2 + "%");
		System.out.println("\n");
	}
}
