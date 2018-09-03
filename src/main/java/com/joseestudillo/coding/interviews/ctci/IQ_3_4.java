package com.joseestudillo.coding.interviews.ctci;

import java.util.Stack;

/**
 * Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
 */
public class IQ_3_4 {

    public static class MyQueue<T> {
        Stack<T> stack0 = new Stack<>();
        Stack<T> stack1 = new Stack<>();

        public void add(T t) {
            stack0.push(t);
        }

        public T get() {
            while (stack0.size() > 1) {
                stack1.push(stack0.pop());
            }
            T result = stack0.pop();
            while (!stack1.empty()) {
                stack0.push(stack1.pop());
            }
            return result;
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();

        for (int i = 0; i < 5; i++) {
            myQueue.add(i);
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(myQueue.get());
        }
    }
}
