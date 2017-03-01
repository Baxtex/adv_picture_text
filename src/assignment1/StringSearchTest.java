package assignment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the String search.
 * 
 * @author Anton Gustafsson
 *
 */
public class StringSearchTest {
	String shortText = "aaaaaaaab";
	String shortPattern = "aaaab";

	String longText;
	String longPattern;

	@Before
	public void longText() {
		try {
			longText = new String(Files.readAllBytes(Paths.get("src/assignment1/text2.txt")));
			longPattern = new String(Files.readAllBytes(Paths.get("src/assignment1/pattern2.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNaive() {
		StringSearches.naiveStringSearch(longText, longPattern);
	}

	@Test
	public void testRabinKarp() {
		StringSearches.rabinKarpSearch(longText, longPattern);
	}

	@Test
	public void testKnutMorrisPratt() {
		StringSearches.kmpSearch(longText, longPattern);
	}
}
