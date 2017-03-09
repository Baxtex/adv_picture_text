package assignment3B;

/**
 * Program that builds an sorted array with suffixes from a given string.
 * 
 * @author Anton Gustafsson
 *
 */
public class SuffixArray {
	private String[] suffixArray, textArray;
	private String str;
	private int length;
	private int[] index;

	/**
	 * Initilizes the arrays.
	 * 
	 * @param text
	 */
	public SuffixArray(String text) {
		str = text;
		System.out.println("String: " + text);
		// setup variables
		length = text.length();
		index = new int[length];
		suffixArray = new String[length];

		// Copy the string to an array and put in indexes.
		this.textArray = new String[length];
		for (int i = 0; i < length; i++) {
			index[i] = i;
			this.textArray[i] = text.substring(i, i + 1);
		}
		build();
	}

	/**
	 * Builds the array by adding suffixes to it and setting up the index.
	 */
	private void build() {
		// Decides where the suffix starts.
		for (int i = 0; i < length; i++) {
			String txt = "";
			// Creates suffixes.
			for (int txtI = i; txtI < length; txtI++) {
				txt += textArray[txtI];
			}
			suffixArray[i] = txt;
		}

		// Now all suffixes are added to the suffix array. Now we need to sort
		// them.
		int y;
		for (int x = 1; x < length; x++) {
			String key = suffixArray[x]; // Each suffix, except the first.
			int keyI = index[x]; // Each's suffix index.

			// Then we loop from the beginning, starting at 0
			for (y = x - 1; y >= 0; y--) {
				if (suffixArray[y].compareTo(key) > 0) {
					suffixArray[y + 1] = suffixArray[y];
					index[y + 1] = index[y];
				} else {
					break;
				}
			}

			suffixArray[y + 1] = key;
			index[y + 1] = keyI;
		}
		System.out.println("");
		System.out.println("After sorting:");
		printArray();
	}

	public void printLongestMatch(String pattern) {
		int i = longestMatchIndex(pattern);
		System.out.println("\n" + pattern + " starts at " + i);
		System.out.println(str.substring(0, i));
	}

	/**
	 * Returns the index of the longest occuring prefix of the given pattern.
	 * This is done by binary searching the suffixes in this suffix array.
	 * 
	 * @param pattern the pattern to search for.
	 * @return the index where the longest prefix occurs.
	 */

	private int longestMatchIndex(String pattern) {

		int start = 0;
		int end = index.length - 1;
		int mid, res;

		while (start <= end) {

			mid = (end + start) / 2;
			res = compare(pattern, index[mid]);

			if (res < 0) {
				end = mid - 1;
			}
			if (res > 0) {
				start = mid + 1;
			}
			if (res == 0) {
				return index[mid];
			}
		}

		if (str.charAt(index[start - 1]) != pattern.charAt(0)) {
			return -1;
		}

		return index[start - 1];
	}

	/**
	 * Compares pattern to suffix. Returns 0 if pattern is a true prefix of
	 * suffix, < 0 if pattern i lexographically lower than suffix, and > 0 if
	 * pattern is higher.
	 * 
	 * @param pattern the pattern
	 * @param suffixArray the suffix
	 * @return
	 */
	public int compare(String pattern, int suffixIndex) {
		String suffix = str.substring(suffixIndex);
		int length = Math.min(pattern.length(), suffix.length());
		for (int i = 0; i < length; i++) {
			if (pattern.charAt(i) != suffix.charAt(i)) {
				return pattern.charAt(i) - suffix.charAt(i);
			}
		}
		if (suffix.length() < pattern.length()) {
			return 1;
		}
		return 0;
	}

	/**
	 * Prints the suffix array with it's old index.
	 */
	public void printArray() {
		System.out.println("Position, Suffix, suffix start pos");
		for (int i = 0; i < length; i++) {
			System.out.println(i + " --------- " + suffixArray[i] + " --------- " + index[i]);
		}
	}
}
