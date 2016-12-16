package assignment3B;

/**
 * Program that builds an array with suffixes from a given string.
 * 
 * @author Anton Gustafsson
 *
 */
public class SuffixArray {
	private String[] suffix;
	private String[] text;
	private int length;
	private int[] index;

	/**
	 * Initilizes the arrays.
	 * 
	 * @param text
	 */
	public SuffixArray(String text) {
		System.out.println("ADDING: " + text);
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
		System.out.println(" ");
		printArray();
		System.out.println(" ");
		build();
	}

	/**
	 * Builds the array by adding suffixes to it and setting up the index.
	 */
	private void build() {
		// Decides where the suffix starts.
		for (int i = 0; i < length; i++) {
			String txt = "";
			//Creates suffixes .
			for (int txtI = i; txtI < length; txtI++) {
				txt += text[txtI];
				System.out.println(txt);
			}
			System.out.println("Adding suffix to array: " + txt);
			suffix[i] = txt;
		}
		System.out.println("");
		System.out.println("Before sorting:");
		printArray();

		// Now all suffixes are added to the suffix array. Now we need to sort them.
		System.out.println(" ");
		int y;
		for (int x = 1; x < length; x++) {
			String key = suffix[x]; // Each suffix, except the first.
			int keyI = index[x]; // Each's suffix index.

			// Then we loop from the beginning, starting at 0
			for (y = x - 1; y >= 0; y--) {
				System.out.println("y- " + y);
				if (suffix[y].compareTo(key) > 0) {
					System.out.println(suffix[y] + " is lexicographically less then " + key);
					System.out.println( suffix[y + 1] + " is set to "+ suffix[y]);
					suffix[y + 1] = suffix[y];
					index[y + 1] = index[y];
				} else {
					System.out.println(suffix[y] + " is lexicographically greater  or equal " + key);
					System.out.println("Break, we have found the right place");
					break;
				}
				printArray();
			}
			System.out.println("New loop");
			suffix[y + 1] = key;
			index[y + 1] = keyI;
		}
		System.out.println("");
		System.out.println("After sorting:");
		printArray();
	}


	/**
	 * Prints the longest common prefix (lcp) for the given string using kasai's algoritm.
	 * 
	 */
	public void printLongestPrefix(String s) {
		int k = 0;
		char[] chars = s.toCharArray();
		int[] lcp = new int[length]; //The resulting lcp array.
		int[] invIndex = new int[length]; //the suffix start pos in reverse

		// Put the suffix array in inverse. so we can find the next suffix.
		for (int i = 0; i < length; i++) {
			invIndex[index[i]] = i;
		}

		// Process each suffix.
		for (int i = 0; i < length; i++) {
			System.out.println("\n Now on index: " + i + " and invIndex is : "  + invIndex[i]);
			
			//When we reach suffix at length-1 we are at the last suffix, and therefore we can't compare it to nothing, so 
			//we set k = 0.
			if (invIndex[i] == length - 1) {
				k = 0;
				continue;// "Breaks" the current iteration, but not the loop.
			}

			// Index of the next suffix.
			int y = index[invIndex[i] + 1];
			
			//Start matching first suffix with second suffix. If their prefixes have the same length, set k to that
			//prefix's length.
			//While(prefixA < length && prefixB<length && suffixA == suffixB)

			while (i + k < length && y + k < length && chars[i + k] == chars[y + k]) {
				k++;
			}
			lcp[invIndex[i]] = k; //lcp for the current suffix.
			//Deletes the starting character.
			 if (k>0){
				 k--;				 
			 }
		}
		prtLCP(lcp);
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

	/**
	 * Prints the lcp array.
	 * 
	 * @param lcp
	 */
	private void prtLCP(int[] lcp) {
		System.out.println("");
		System.out.println("LCP array:");
		System.out.println("Pos,  pref1,   pref2,    lcp:");
		for(int i = 0; i<lcp.length;i++) {
			System.out.println( i + "----" + "'"+ suffix[i] + "' -------  '" + suffix[i+1] + "' ------ " +lcp[i]);
			if(i+1>=lcp.length-1)
				break;
		}
	}
}
