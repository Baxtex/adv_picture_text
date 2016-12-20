package assignment1;

/**
 * Program that uses different methods for finding a pattern in a text.
 * 
 * @author Anton Gustafsson
 *
 */
public class StringSearch {
	private int outerIterations, innerIterations;
	private long totalNS;

	public StringSearch(String text, String pattern, String method) {

		boolean exist = false;

		long startTime = System.nanoTime();

		if (method.equals("naive")) {
			System.out.println("Naive");
			exist = naiveSearch(text, pattern);

		} else if (method.equals("rabin")) {
			System.out.println("Rabin Karp");
			exist = rabinKarp(text, pattern);

		} else if (method.equals("kmp")) {
			System.out.println("Knut Morris Pratt");
			exist = knuthMorrisPratt(text, pattern);

		} else {
			System.out.println("ERROR");
		}
		System.out.println("Did your pattern exist?: " + exist);

		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		totalNS += duration;
		prtIterations();

		System.out.println("Execution time in ns is : " + totalNS + "\n");
	}

	/**
	 * Algorithm that finds a certain pattern in a string. Naive Search. Basicly
	 * compares each and every letter if they match. If the pattern is found,
	 * return true.
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

					if (result.toString().equals(pattern)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Searches for a pattern in the text using Rabin Karp algorithm. Hashes the
	 * pattern and compares them.
	 * 
	 * @return - true if we got a match.
	 */
	private boolean rabinKarp(String text, String pattern) {
		char[] txt = text.toCharArray();
		char[] patt = pattern.toCharArray();
		int sigmaLen = 256;
		int prime = 101;
		int pattLen = patt.length;
		int txtLen = txt.length;
		int pattHash = 0;
		int txtHash = 0;
		int i, j;
		int h = 1;

		for (i = 0; i < pattLen - 1; i++)
			h = (h * sigmaLen) % prime;

		// Calculate the hash value of pattern and first
		// window of text
		for (i = 0; i < pattLen; i++) {
			pattHash = (sigmaLen * pattHash + patt[i]) % prime;
			txtHash = (sigmaLen * txtHash + txt[i]) % prime;
		}

		// Slide the pattern over text one by one
		for (i = 0; i <= txtLen - pattLen; i++) {
			innerIterations++;
			// Check the hash values of current window of text
			// and pattern. If the hash values match then only
			// check for characters on by one
			if (pattHash == txtHash) {
				/* Check for characters one by one */
				for (j = 0; j < pattLen; j++) {
					outerIterations++;
					if (txt[i + j] != patt[j])
						break;
				}
				// pattern is found.
				if (j == pattLen) {
					return true;
				}
			}

			// Calculate hash value for next window of text: Remove
			// leading digit, add trailing digit
			if (i < txtLen - pattLen) {
				txtHash = (sigmaLen * (txtHash - txt[i] * h) + txt[i + pattLen]) % prime;
				// We might get negative value of t, converting it
				// to positive
				if (txtHash < 0)
					txtHash = (txtHash + prime);
			}
		}
		return false;
	}

	/**
	 * Algorithm that finds a certain pattern in a string. KMP search. KMP tries
	 * to compare as little as possible. If we already have comapared a substing
	 * and it doesn't containg anything, we can skip that section.
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
		return arr;
	}

	/**
	 * Just prints the number of iterations.
	 */
	private void prtIterations() {
		int i = innerIterations + outerIterations;
		System.out.println("Total Iterations in: " + innerIterations);
		System.out.println("Total Iterations out: " + outerIterations);
		System.out.println("Total Iterations: " + i);

		innerIterations = 0;
		outerIterations = 0;
	}
}
