package com.joseestudillo.coding.interviews;

/**
 * Given a binary tree, check if it is a binary search tree.
 *
 * @author Jose Estudillo
 */
public class CheckIfBinarySearchTree {

	// Inner class to represent the node
	class Node {
		int data;
		Node left;
		Node right;
	}

	boolean isCorrectLocally(Node root) {
		return
			(root.left == null || root.left.data < root.data) &&
			(root.right == null || root.right.data > root.data);
	}

	boolean isCorrectInTree(Node root, int min, int max) {
		return min < root.data && root.data < max;
	}


	boolean isBST(Node root, int min, int max) {
		if (root == null) {
			return true;
		} else {
			return
				isCorrectLocally(root) &&
				isCorrectInTree(root, min, max) &&
				isBST(root.left, min, root.data) &&
				isBST(root.right, root.data, max);
		}
	}

	boolean isBST(Node root) {
		if (root == null) {
			return true;
		} else {
			return
				isBST(root.left, Integer.MIN_VALUE, root.data) &&
				isBST(root.right, root.data, Integer.MAX_VALUE);
		}
	}
}
