package assignment5B;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HuffmanTree {

	private PriorityQueue<Node> pq;
	private Node[] nodeArray;
	private char charArr[];
	private int charFreqs[];
	private String s;

	public HuffmanTree(String s) {
		this.s = s;
		findOccurences(s);
		pq = new PriorityQueue<Node>(charFreqs.length);
		nodeArray = new Node[charArr.length];

		// Create Nodes from the chars and frequncies and put them in a array.
		for (int i = 0; i < charArr.length; i++) {
			nodeArray[i] = new Node(charArr[i], charFreqs[i]);
		}
		build(nodeArray);
	}

	private void build(Node[] nodeArray) {
		Node left, right, top;
		// Put the Nodes from the node array in a min heap/priorityQueue.
		for (int i = 0; i < nodeArray.length; i++) {
			pq.add(nodeArray[i]);
		}

		// Find two trees with least freq and creates a new node and inserts it.
		while (pq.size() > 1) {
			left = pq.remove();
			right = pq.remove();
			// dena nya noden tar en char och en freq.
			int newFreq = left.getFreq() + right.getFreq();
			top = new Node('$', newFreq, left, right);
			pq.add(top);
		}
		encode(pq.remove(), "0");
	}

	/**
	 * Set's the encoding for every node.
	 * 
	 * @param node
	 * @param code
	 */
	private void encode(Node node, String code) {
		if (!node.isLeafNode()) {
			code += 0;
			encode(node.getLeft(), code);
			code += 1;
			encode(node.getRight(), code);
		} else {
			if (code.length() > 0) {
				code = code.substring(0, code.length() - 1); //Removes one zero
			}
			node.setCode(String.valueOf(code));
		}

	}

	/**
	 * Finds occurencess of each letter in the given string and initializes the
	 * arrays containing the letters and their frequencies. Currently the
	 * letters in the array become in random order.
	 * 
	 * @param s
	 */
	private void findOccurences(String s) {
		List<Character> original = new ArrayList(s.length());
		List<Character> duplicateRemoved;

		for (int i = 0; i < s.length(); i++) {
			original.add(s.charAt(i));
		}
		duplicateRemoved = new ArrayList<Character>(original);

		// Remove duplicates from second list.
		HashSet<Character> hs = new HashSet<Character>();
		hs.addAll(duplicateRemoved);
		duplicateRemoved.clear();
		duplicateRemoved.addAll(hs);

		charFreqs = new int[duplicateRemoved.size()];
		charArr = new char[duplicateRemoved.size()];

		for (int i = 0; i < charArr.length; i++) {
			char c = duplicateRemoved.get(i);
			int count = Collections.frequency(original, c);
			charArr[i] = c;
			charFreqs[i] = count;
		}

		for (int i = 0; i < charArr.length; i++) {
			System.out.println(charArr[i] + " " + charFreqs[i]);
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

			System.out.print(c + "=" + ht.get(c) + " ");
		}
		
		System.out.println(" \n \n \n \n \n \n \n");

	}
}
