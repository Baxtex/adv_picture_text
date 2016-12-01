package assignment3B;

public class SuffixArray {
	private String[] text;
	private int length;
	private int[] index;
	private String[] suffix;

	/**
	 * Initilizes the array.
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

		build();
	}

	/**
	 * Builds the array by adding data.
	 */
	private void build() {
		// Decides where the suffix starts.
		for (int i = 0; i < length; i++) {
			String txt = "";
			// Finds each letter, basicly creates substrings.
			for (int txtI = i; txtI < length; txtI++) {
				txt += text[txtI];
				System.out.println("tmp: " + txt);
			}
			System.out.println("Adding suffix to array: " + txt);
			suffix[i] = txt;
		}
		
		// Now all suffixes are added to the suffix array.
		// We start from position 1 as the first one is the complete word,
		int y;
		for (int x = 1; x < length; x++) {
			String key = suffix[x]; // Each suffix, except the first.
			int keyI = index[x]; // Each's suffix index. 
			//First one is ananas at index 1
	
			//Then we loop from the beginning, starting at 0
			for (y = x - 1; y >= 0; y--) {
				System.out.println("back " + y);
				if (suffix[y].compareTo(key) > 0) {
					System.out.println(suffix[y] + " is lexicographically less then " + key);
					suffix[y + 1] = suffix[y];
					index[y + 1] = index[y];
				} else {
					System.out.println(suffix[y] + " is lexicographically greater  or equal " + key);
					System.out.println("Break");
					break;
				}
				System.out.println("Suffix: " + suffix[y] + " at " + y);
			}
			System.out.println("New loop");
			suffix[y + 1] = key;
			index[y + 1] = keyI;
		}
		printDone();
	}
	
	/**
	 * Prints the complete array.
	 */
	public void printDone() {
		System.out.println("DONE:");
		for (int j = 0; j < length; j++) {
			System.out.println(suffix[j] + "\t" + index[j]);
		}

	}
	/**
	 * Prints the longest prefix.
	 */
	public void printLongestPrefix(){
		
	}
	
	
	

}
