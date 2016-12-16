package assignment5B;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HuffmanTree {

	private PriorityQueue pq;

	public HuffmanTree(String s) {
		char[] data = s.toCharArray();
		pq = new PriorityQueue();
		
		

	}

	/**
	 * Used to find the characters to encode and their frequencies.
	 * 
	 * @param data
	 * @return
	 */
	private char[] findFreqs(char[] data) {
		int length = data.length;

		int[] freq = new int[length];
		char[] check = new char[length];
		List list = new ArrayList(length);

		// for (int i = 0; i < length; i++) {
		// freq[i] = 0;
		// }

		for (int i = 0; i < length; i++) {
			if (!list.contains(data[i])) {

			}
		}

		return null;
	}

}
