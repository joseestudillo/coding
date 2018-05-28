package com.joseestudillo.coding.basics.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created on 27/07/2017.
 *
 * @author Jose Estudillo
 */
public class Trees {

	public static class BinaryTree<T> {
		T element;
		BinaryTree<T> r;
		BinaryTree<T> l;

		public BinaryTree(T e, BinaryTree<T> l, BinaryTree<T> r) {
			if (e == null) {
				throw new IllegalArgumentException("Illegal argument exception");
			}
			this.element = e;
			this.l = l;
			this.r = r;
		}

		public BinaryTree(T e) {
			this(e, null, null);
		}

		public List<BinaryTree<T>> getChildren() {
			List<BinaryTree<T>> res = new ArrayList<>();
			if(this.l != null) {
				res.add(this.l);
			}
			if(this.r != null) {
				res.add(this.r);
			}
			return res;
		}
	}

	//search level by level
	public static <T extends Comparable> boolean breadthFirstSearch(BinaryTree<T> tree, T toFind) {
		if (tree == null) {
			return false;
		} else {
			Queue<BinaryTree<T>> queue = new LinkedBlockingQueue<>();
			queue.add(tree);
			return breadthFirstSearch(queue, toFind);
		}
	}

	public static <T extends Comparable> boolean breadthFirstSearch(Queue<BinaryTree<T>> queue, T toFind) {
		while(!queue.isEmpty()) {
			BinaryTree<T> toCheck = queue.remove();
			if (toCheck.element.equals(toFind)) {
				return true;
			} else {
				queue.addAll(toCheck.getChildren());
			}
		}
		return false;
	}


	public static <T extends Comparable> boolean deepFirstSearchRec(BinaryTree<T> tree, T toFind) {
		return tree != null && (
				deepFirstSearchRec(tree.l, toFind) ||
				tree.element.equals(toFind) ||
				deepFirstSearchRec(tree.r, toFind));
	}


	public static void main(String[] args) {
		BinaryTree<Integer> tree =
				new BinaryTree<Integer>(0,
					new BinaryTree<Integer>(1,
							new BinaryTree<Integer>(3),
							new BinaryTree<Integer>(4)),
					new BinaryTree<Integer>(2,
							new BinaryTree<Integer>(5),
							new BinaryTree<Integer>(6)));

		System.out.println(breadthFirstSearch(tree, 6));
		System.out.println(breadthFirstSearch(tree, 7));
		System.out.println(deepFirstSearchRec(tree, 6));
		System.out.println(deepFirstSearchRec(tree, 7));
	}

}
