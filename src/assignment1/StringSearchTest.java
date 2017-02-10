package assignment1;

import org.junit.Test;

/**
 * Tests the String search.
 * 
 * @author Anton Gustafsson
 *
 */
public class StringSearchTest {
	String t = "aaaaaaaab";
	String p = "aaaab";

	@Test
	public void testNaive() {
		StringSearch ss = StringSearches.naiveStringSearch(t, p);
	}

	@Test
	public void testRabinKarp() {
		StringSearch ss = StringSearches.rabinKarpSearch(t, p);
	}

	@Test
	public void testKnutMorrisPratt() {
		StringSearch ss = StringSearches.kmpSearch(t, p);
	}
}
