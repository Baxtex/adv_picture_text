package assignment3B;

/**
 * Program that builds an sorted array with suffixes from a given string.
 * 
 * @author Anton Gustafsson
 *
 */
public class SuffixArray {
	private String[] suffix, text;
	private int length;
	private int[] index;

	/**
	 * Initilizes the arrays.
	 * 
	 * @param text
	 */
	public SuffixArray(String text) {
		System.out.println("String: " + text);
		// setup variables
		length = text.length();
		index = new int[length];
		suffix = new String[length];

		// Copy the string to an array and put in indexes.
		this.text = new String[length];
		for (int i = 0; i < length; i++) {
			index[i] = i;
			this.text[i] = text.substring(i, i + 1);
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
				txt += text[txtI];
			}
			suffix[i] = txt;
		}

		// Now all suffixes are added to the suffix array. Now we need to sort
		// them.
		int y;
		for (int x = 1; x < length; x++) {
			String key = suffix[x]; // Each suffix, except the first.
			int keyI = index[x]; // Each's suffix index.

			// Then we loop from the beginning, starting at 0
			for (y = x - 1; y >= 0; y--) {
				if (suffix[y].compareTo(key) > 0) {
					suffix[y + 1] = suffix[y];
					index[y + 1] = index[y];
				} else {
					break;
				}
			}

			suffix[y + 1] = key;
			index[y + 1] = keyI;
		}
		System.out.println("");
		System.out.println("After sorting:");
		printArray();
	}

	/**
	 * Finds and prints the longest prefix for the given string.
	 * 
	 * @param searchStr
	 */
	public void printLongestPrefix(String searchStr) {
		System.out.println("\nSearching for longest prefix for '" + searchStr + "'");

		int prefixLength = 0;
		String longestPrefix = "None";

		for (int i = 0; i < suffix.length; i++) {

			String currPrefix = suffix[i];

			if (currPrefix.length() > searchStr.length() && currPrefix.contains(searchStr)) {

				int currPrefixIndex = currPrefix.lastIndexOf(searchStr);

				if (currPrefixIndex > prefixLength) {
					longestPrefix = currPrefix.substring(0, currPrefixIndex);
					prefixLength = longestPrefix.length();
				}
			}
		}
		System.out.println("Longest prefix is '" + longestPrefix + "'");
	}

	/**
	 * Prints the suffix array with it's old index.
	 */
	public void printArray() {
		System.out.println("Position, Suffix, suffix start pos");
		for (int i = 0; i < length; i++) {
			System.out.println(i + " --------- " + suffix[i] + " --------- " + index[i]);
		}
	}

}
