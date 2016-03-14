package jwang81.sstevens.edu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trie {
	private int SIZE = 26;
	/*
	 * The root of a trie
	 * */
	private TrieNode root;
	/*
	 * ini the trie
	 * */
	Trie() {
		root = new TrieNode();
	}
	/*
	 * node a trie
	 * */
	private class TrieNode {
		private TrieNode[] son;// all the children node
		private boolean isEnd;// is the last node
		private char val;// the value of the node
		private Set<Integer> positions;
		public Set<Integer> getPositions() {
			return positions;
		}
		public void setPositions(int index) {
			this.positions.add(index);
		}
		TrieNode() {
			son = new TrieNode[SIZE];
			positions = new HashSet<Integer>();
			isEnd = false;
		}
	}

	// insert the word into the Trie
	public void insert(String str, int index) {
		if (str == null || str.length() == 0) {
			return;
		}
		TrieNode node = root;
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] == null) {
				node.son[pos] = new TrieNode();
				node.son[pos].val = letters[i];
			} 
			node = node.son[pos];
		}
		node.isEnd = true;
		node.setPositions(index);
	}
	/*
	 * get all the index of a article by input a word, search from the tree
	 * */
	public Set<Integer> getIndexList(String string) {
		Set<Integer> result = new HashSet<Integer>();
		
		if (string == null || string.length() == 0) {
			return result;
		}
		TrieNode node = root;
		String str = Util.upperToLower(string);
		char[] letters = str.toCharArray();
		for (int i = 0, len = str.length(); i < len; i++) {
			int pos = letters[i] - 'a';
			if (node.son[pos] != null) {
				node = node.son[pos];
			} else {
				return result;
			}
		}
		
		if (node.isEnd) {
			result = node.getPositions();
		}
		return result;
	}

	public TrieNode getRoot() {
		return this.root;
	}
}
