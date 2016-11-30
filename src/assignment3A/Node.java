package assignment3A;

import java.util.LinkedList;

/**
 * Represents one node in the tree.
 * 
 * @author Anton Gustafsson
 *
 */

public class Node {

	private boolean isWord;
	private String text;
	private LinkedList<Node> children;

	public Node() {
		children = new LinkedList<Node>();
		text = "";
		isWord = false;
	}

	public Node(String text) {
		this();
		this.text = text;
	}
	
	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public LinkedList<Node> getChildren() {
		return children;
	}

	@Override
	public String toString(){
		return text;
	}
	
	

}
