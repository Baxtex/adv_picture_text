package assignment3;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Trie datastructure that is a tree datastructure for strings.
 * 
 * @author Anton Gustafsson Implementera en trie-datastruktur. Den ska stödja
 * operationerna sätt in sträng och sök exakt sträng. Övriga operationer (som
 * borttagning) är frivilliga. Diskutera tidskomplexiteten i er implementation.
 * Implementera en suffixdatastruktur, antingen ett suffixträd eller en
 * suffixvektor. Den ska stödja operationerna bygg, givet en text (som kan vara
 * en konstruktor om ni så önskar), och sök upp längsta prefix, givet en
 * sökmönstersträng. Diskutera tidskomplexiteten i er implementation. Det finns
 * inget krav på att den ska vara optimal, en rimlig tidskomplexitet för en naiv
 * implementation är tillräcklig.
 */
public class Trie {

	private Node root;
	private int size;

	/**
	 * Creates the root node and sets the size to 0.
	 */
	public Trie() {
		root = new Node();
		size = 0;
	}

	/**
	 * Adds a new word to the trie.
	 * 
	 * @param word
	 * @return
	 */
	public boolean add(String word) {
		Node curr = root;
		if (curr == null || word == null) {
			return false;
		}
		LinkedList<Node> children = curr.getChildren();
		int i = 0;
		char[] chars = word.toCharArray();
		int charsSize = chars.length - 1;
		// Loop through all letters in the word.
		while (i < chars.length) {
			// If the children nodes does not contain the letter at i.
			if (!children.contains(chars[i])) {
				// Insert a new node
				insertChar(curr, chars[i]);
				// if we have traversed all letts.
				if (i == charsSize) {
					// Get the child and set word to true ie we have found it.
					getChild(curr, chars[i]).setWord(true);
					size++;
					return true;
				}
			}
			// get the current child.
			curr = getChild(curr, chars[i]);
			// If the current childs text is equal the word or not the word.
			if (curr.getText().equals(word) && !curr.isWord()) {
				// set the current word to true.
				curr.setWord(true);
				size++;
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * Returns true if the given string is found.
	 * TODO: FIX!
	 * @param str
	 * @return
	 */
	
	//Vi letar efter Beat, som inte finns.
	public boolean find(String str) {
		System.out.println(str);
		System.out.println("-----------------------------------------");
		
		LinkedList<Node> children = root.getChildren();
		Node node = null;
		char[] chars = str.toCharArray();
		//Loop over all letters.
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			//If child contains c.
			if (childContain(children, c)) {
				System.out.println("DET FINNS");
				//get the child and it's children.
				node = getChild(root, c);
				children = node.getChildren();
			} else {
				System.out.println("DET FINNS INGET");
				return false;
			}
		}
		return true;
	}

	/**
	 * Inserts a new Node with a char.
	 * 
	 * @param node
	 * @param c
	 * @return
	 */
	private Node insertChar(Node node, Character c) {
		if (childContain(node.getChildren(), c)) {
			return null;
		}

		Node next = new Node(node.getText() + c.toString());
		node.getChildren().add(next);
		return next;
	}

	/**
	 * Retrieves a given node's child with the character c
	 * 
	 * @param trie
	 * @param c
	 * @return
	 */
	private Node getChild(Node node, Character c) {
		LinkedList<Node> list = node.getChildren();
		Iterator<Node> iter = list.iterator();
		while (iter.hasNext()) {
			Node n = iter.next();
			if (n.getText().equals(String.valueOf(c)));
			{
				return n;
			}
		}
		System.out.println("Returning null"); // This could never happen
		return null;
	}

	/**
	 * Checks if any child contains the char c.
	 * 
	 * @param list
	 * @param c
	 * @return
	 */
	private boolean childContain(LinkedList<Node> list, char c) {
		Iterator<Node> iter = list.iterator();

		while (iter.hasNext()) {
			Node n = iter.next();
			
			System.out.println("char " + String.valueOf(c) +" lista " + n.getText() + "?");
			System.out.println(n.getText().equals(String.valueOf(c)));
			
			
			if (n.getText().equals(String.valueOf(c)))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the trie's size.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}
}
