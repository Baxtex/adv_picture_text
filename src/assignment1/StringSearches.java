package assignment1;

/**
 * Has static factory methods for generating different StringSearch objects.
 * @author Anton Gustafsson
 *
 */
public class StringSearches {
	
	public static StringSearch naiveStringSearch(String t, String p){
		return new StringSearch(t,p,"naive");
	}
	
	public static StringSearch rabinKarpSearch(String t, String p){
		return new StringSearch(t,p,"rabin");
	}
	
	public static StringSearch kmpSearch(String t, String p){
		return new StringSearch(t,p,"kmp");
	}

}
