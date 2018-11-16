package com.joseestudillo.coding.basics.data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 31/07/2017.
 *
 * @author Jose Estudillo
 */
public class Tries {

	public static class TrieRec {
		TrieNodeRec root = new TrieNodeRec('*');

		public void add(String s) {
			root.add(s);
		}

		public int find(String s) {
			return root.find(s);
		}
	}

	public static class TrieNodeRec {

		Map<Character, TrieNodeRec> children = new HashMap<>();
		Character c;
		boolean word = false;

		public TrieNodeRec(char c) {
			this.c = c;
		}

		public void add(String s) {
			if(s.isEmpty()) {
				return;
			}
			char toCheck = s.charAt(0);
			TrieNodeRec child = children.get(toCheck);
			if (child == null) {
				child = new TrieNodeRec(toCheck);
				children.put(toCheck, child);
			}
			child.word |= s.length() == 1;
			//we could save a recursive call here but it looks cleaner this way.
			child.add(s.substring(1));
		}

		public int find(String s) {
			if(s.isEmpty()) {
				return 0;
			}
			char toCheck = s.charAt(0);
			TrieNodeRec child = children.get(toCheck);
			if (child == null) {
				return 0;
			} else {
				if(s.length() == 1) {
					return child.countWords();
				} else {
					return child.find(s.substring(1));
				}
			}
		}

		public int countWords() {
			int total = (this.word) ? 1 : 0;
			for(TrieNodeRec child : this.children.values()) {
				total += child.countWords();
			}
			return total;
		}

		public String toString() {
			return String.format("{c:%s, w:%s}", this.c, this.word);
		}
	}


	public static class Trie {
		TrieNode root = new TrieNode('*');

		public void add(String s) {
			s = s.substring(0, Math.min(s.length(), 21));
			root.add(s);
		}

		public int find(String s) {
			s = s.substring(0, Math.min(s.length(), 21));
			return root.find(s);
		}
	}

	public static class TrieNode {

		Map<Character, TrieNode> children = new HashMap<>();
		Character c;
		boolean word = false;

		public TrieNode(char c) {
			this.c = c;
		}

		public void add(String s) {
			TrieNode parent = this;
			while(!s.isEmpty()) {
				char toCheck = s.charAt(0);
				TrieNode child = parent.children.get(toCheck);
				if (child == null) {
					child = new TrieNode(toCheck);
					parent.children.put(toCheck, child);
				}
				if (s.length() == 1) {
					child.word = true;
				}
				parent = child;
				s = s.substring(1);
			}
		}

		public int find(String s) {
			TrieNode parent = this;
			while(!s.isEmpty() && parent != null) {
				char toCheck = s.charAt(0);
				TrieNode child = parent.children.get(toCheck);
				parent = child;
				s = s.substring(1);
			}
			return (parent == null) ? 0 : parent.countWords();
		}

		public int countWords() {
			int total = 0;
			List<TrieNode> nodes = new ArrayList<>();
			nodes.add(this);
			while(!nodes.isEmpty()) {
				TrieNode node = nodes.remove(0);
				total += (node.word) ? 1 : 0;
				nodes.addAll(node.children.values());
			}
			return total;
		}

		public String toString() {
			return String.format("{c:%s, w:%s}", this.c, this.word);
		}
	}
}
