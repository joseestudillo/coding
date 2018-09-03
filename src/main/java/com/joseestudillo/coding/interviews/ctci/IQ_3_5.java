package com.joseestudillo.coding.interviews.ctci;

import java.util.Comparator;
import java.util.Stack;

/**
 * Sort Stack: Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and is Empty.
 */
public class IQ_3_5 {

    public static class SorterStack<T extends Comparable<? super T>> {

        private Stack<T> stack = new Stack<>();
        private Comparator<T> comparator;

        public SorterStack(Comparator<T> comparator) {

            this.comparator = comparator;
        }

        public T push(T item) {
            Stack<T> tmpStack = new Stack<>();
            while(!stack.isEmpty() && this.comparator.compare(stack.peek(), item) < 0) {
                tmpStack.push(stack.pop());
            }
            stack.push(item);
            while(!tmpStack.empty()) {
                stack.push(tmpStack.pop());
            }
            return item;
        }

        public T pop() {
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        SorterStack<Integer> stack = new SorterStack<>(Integer::compareTo);

        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }

        for (int i = 5 - 1; i >= 0; i--) {
            stack.push(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(stack.pop());
        }

    }
}
