package assignment1;

import java.util.Arrays;

/**
 * Class for finding a specific word.
 * 
 * @author i7
 *
 */
public class TestFindWord {
	private int outerIterations;
	private int innerIterations;
	private long totalNS;

	public static void main(String[] args) {
		new TestFindWord();
	}

	public TestFindWord() {
		System.out.println("Welcome to the pattern finder!");

		String text = "In this approach, we avoid backtracking by constructing a deterministic finite automaton (DFA) that recognizes stored search string. These are expensive to construct—they are usually created using the powerset construction—but are very quick to use. For example, the DFA shown to the right recognizes the word MOMMY. This approach is frequently generalized in practice to search for arbitrary regular expressions.";
		String pattern = "approach ";

		for (int i = 0; i < 5; i++) {

			long startTime = System.nanoTime();
			System.out.println("Did your pattern exist?: " + rabinKarp(text, pattern)); //choose method here
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);

			totalNS += duration;
			System.out.println("Total Iterations in: " + innerIterations);
			System.out.println("Total Iterations out: " + outerIterations);
			System.out.println("Total Iterations: ");
			System.out.println(innerIterations + outerIterations);
			innerIterations = 0;
			outerIterations = 0;

		}
		totalNS = totalNS / 5;
		System.out.println("Average ns is : " + totalNS);

	}

	/**
	 * Algorithm that finds a certain pattern in a string. Naive Search
	 * 
	 * @param text - String the text to search in.
	 * @param pattern - String the pattern to look for.
	 * @return - True if the pattern exists.
	 */
	private boolean naiveSearch(String text, String pattern) {
		StringBuilder result = new StringBuilder();
		char[] txt = text.toCharArray();
		char[] patt = pattern.toCharArray();

		// Loop through the text array.
		for (int i = 0; i < txt.length; i++) {
			innerIterations++;
			// Loop through each character for the pattern length.
			for (int j = 0; j < patt.length && i + j < txt.length; j++) {
				// If the character does not match the pattern, clear result and
				// break.
				outerIterations++;
				if (txt[i + j] != patt[j]) {
					result = new StringBuilder();
					break;
					// Else, add it to the result.
				} else {
					result.append(txt[i + j]);
					// if result is equal pattern, return true.
					System.out.println(result.toString());
					if (result.toString().equals(pattern)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Algorithm that finds a certain pattern in a string. KMP search. Think of
	 * it as naive search with intelligent index.
	 * 
	 * @param text - String the text to search in.
	 * @param pattern - String the pattern to look for.
	 * @return - True if the pattern exists.
	 */
	private boolean knuthMorrisPratt(String text, String pattern) {
		int[] arr = computeTable(pattern);
		int j = 0; // Number of chars matched in pattern
		for (int i = 0; i < text.length(); i++) {
			innerIterations++;
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				outerIterations++;
				// Fall back in the pattern
				j = arr[j - 1]; // Strictly decreasing
			}
			if (text.charAt(i) == pattern.charAt(j)) {
				// Next char matched, increment position
				j++;
				if (j == pattern.length())
					return true;
			}
		}
		return false;
	}

	/**
	 * Builds a "Partial Match" or "failure table" that the KMP algorithmen uses
	 * to decide not to match any character more than once.
	 * 
	 * @param pattern
	 * @return
	 */
	private int[] computeTable(String pattern) {
		int[] arr = new int[pattern.length()];
		arr[0] = 0; // Base case
		for (int i = 1; i < pattern.length(); i++) {
			int j = arr[i - 1];
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = arr[j - 1];
			if (pattern.charAt(i) == pattern.charAt(j))
				j++;
			arr[i] = j;
		}
		System.out.println(Arrays.toString(arr));
		return arr;
	}

	/**
	 * Searches for a pattern in the text using Rabin Karp algorithm. Hashes the
	 * pattern and compares it.
	 * http://www.geeksforgeeks.org/searching-for-patterns-set-3-rabin-karp-algorithm/
	 * 
	 * @return
	 */
	private boolean rabinKarp(String text, String pattern) {
		char[] txt = text.toCharArray();
		char[] patt = pattern.toCharArray();
		int sigmaLen = 256;
		int prime = 101; //primenumber
		int pattLen = patt.length;
		int txtLen = txt.length;
		int pattHash = 0; // hash value for pattern
		int txtHash = 0; // hash value for txt
		int h = 1;
		int i, j;
		innerIterations++;
		outerIterations++;

		 // The value of h would be "pow(d, M-1)%q"
	    for (i = 0; i < pattLen-1; i++)
	        h = (h*sigmaLen)%prime;
	 
	    // Calculate the hash value of pattern and first
	    // window of text
	    for (i = 0; i < pattLen; i++)
	    {
	        pattHash = (sigmaLen*pattHash + patt[i])%prime;
	        txtHash = (sigmaLen*txtHash + txt[i])%prime;
	    }
	 
	    // Slide the pattern over text one by one
	    for (i = 0; i <= txtLen - pattLen; i++)
	    {
	 
	        // Check the hash values of current window of text
	        // and pattern. If the hash values match then only
	        // check for characters on by one
	        if ( pattHash == txtHash )
	        {
	            /* Check for characters one by one */
	            for (j = 0; j < pattLen; j++)
	            {
	                if (txt[i+j] != patt[j])
	                    break;
	            }
	            // pattern is found.
	            if (j == pattLen){	
	            	return true;
	            }
	        }
	 
	        // Calculate hash value for next window of text: Remove
	        // leading digit, add trailing digit
	        if ( i < txtLen-pattLen )
	        {
	            txtHash = (sigmaLen*(txtHash - txt[i]*h) + txt[i+pattLen])%prime;
	            // We might get negative value of t, converting it
	            // to positive
	            if (txtHash < 0)
	            txtHash = (txtHash + prime);
	        }
	    }
		return false;
	}
}
