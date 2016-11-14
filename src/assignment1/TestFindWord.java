package assignment1;

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
			// System.out.println("Did your pattern exist?: " +
			// naiveSearch(text, pattern));
			System.out.println("Did your pattern exist?: " + knuthMorrisPratt(text, pattern));
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
		int[] lsp = computeLspTable(pattern);
		int j = 0; // Number of chars matched in pattern
		for (int i = 0; i < text.length(); i++) {
			innerIterations++;
			while (j > 0 && text.charAt(i) != pattern.charAt(j)) {
				outerIterations++;
				// Fall back in the pattern
				j = lsp[j - 1]; // Strictly decreasing
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
	private int[] computeLspTable(String pattern) {
		int[] lsp = new int[pattern.length()];
		lsp[0] = 0; // Base case
		for (int i = 1; i < pattern.length(); i++) {
			// Start by assuming we're extending the previous LSP
			int j = lsp[i - 1];
			while (j > 0 && pattern.charAt(i) != pattern.charAt(j))
				j = lsp[j - 1];
			if (pattern.charAt(i) == pattern.charAt(j))
				j++;
			lsp[i] = j;
		}
		return lsp;
	}


}
