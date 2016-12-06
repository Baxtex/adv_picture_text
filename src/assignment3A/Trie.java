package assignment3A;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Trie datastructure that is a dictianry datastructure for strings.
 * 
 * @author Anton Gustafsson
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
	 * Basicly we check if any of the childen to a node contains the letters
	 * we are looking for. If not, create a node with that letter. When we are done
	 * mark the last node(leaf) as the location of the word.
	 * 
	 * @param word - String the word to add.
	 * @return true if we are done adding.
	 */
	public boolean add(String word) {
		System.out.println("adding " + "'" + word + "'");
		Node curr = root;
		int i = 0;
		char[] chars = word.toCharArray();

		// Loop through all letters in the word.
		while (i < chars.length) {
			LinkedList<Node> children = curr.getChildren();
			System.out.println("Current char is " + chars[i]);

			// If the node does not exists, add it.
			if (!childContain(children, String.valueOf(chars[i]))) {
				System.out.println("Can't find this char, adding node...");
				insertNode(curr, chars[i]);

				// if we have traversed all letters and reached the leaf, mark it as a leaf.
				if (i == chars.length - 1) {
					System.out.println("LEAF; SET IT TO TRUE--------------");
					getChild(curr, chars[i]).setWord(true);
					size++;
					return true;
				}
			}
			// Get the new child.
			curr = getChild(curr, chars[i]);
			// If we find the word we are adding already in the trie, mark it so that it has the word.
			if (curr.getText().equals(word) && !curr.isWord()) {
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
	 * 
	 * @param s - the string to find.
	 * @return - true if the string exists.
	 */
	public boolean find(String s) {
		LinkedList<Node> children = root.getChildren();
		// start the node at the root
		Node curr = root;
		char[] chars = s.toCharArray();
		// Loop over all letters.
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			// If child contains c.
			if (childContain(children, String.valueOf(c))) {
				// get the child *of the node, not root* and it's children.
				curr = getChild(curr, c);

		
				if (curr == null) {
					// we have reached a node that does not have children
					if (i == chars.length - 1) {
						// we are at the end of the word - it is found
						return true;
					} else {
						// we are in the middle of the word - it is not present
						return false;
					}
				}
				// if we have reached the end of the word this will cause
				// NullPointer
				children = curr.getChildren();

			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * Inserts a new Node with a given char.
	 * 
	 * @param node - the old node.
	 * @param c - the character to add.
	 */
	private void insertNode(Node node, Character c) {
		if (childContain(node.getChildren(), String.valueOf(c))) {
			return;
		}
		Node next = new Node(c.toString());
		node.getChildren().add(next);
	}

	/**
	 * Retrieves a given node's child with the character c
	 * 
	 * @param node - the node to get children from.
	 * @param c - the character we want to check.
	 * @return the children Node that matches c
	 */
	private Node getChild(Node node, Character c) {
		LinkedList<Node> list = node.getChildren();
		Iterator<Node> iter = list.iterator();
		while (iter.hasNext()) {
			Node n = iter.next();
			if (n.getText().equals(String.valueOf(c))) {
				System.out.println("Returning child with '" + c + "' as it's text");
				return n;
			}
		}
		return null;
	}

	/**
	 * Checks if any child contains the char c.
	 * 
	 * @param list - the children to look in,
	 * @param c - the String to match.
	 * @return true if there is a matching child.
	 * 
	 */
	private boolean childContain(LinkedList<Node> children, String c) {

		if (children.size() > 0) {

			System.out.println("List is not empty");
		} else {

			System.out.println("List is empty, can't iterate");
		}

		Iterator<Node> iter = children.iterator();

		while (iter.hasNext()) {
			Node n = iter.next();

			System.out.print("char " + (c) + " lista " + n.getText() + "?  ");
			System.out.println(n.getText().equals(c));

			if (n.getText().equals(c)) {
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
