package org.wq.leetcode.round1.day16;

import java.util.Stack;

public class MinStack {
    private Stack<Integer> minStack = new Stack<>();
    private Stack<Integer> stack = new Stack<>();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
            return;
        }
        minStack.push(Math.min(minStack.peek(), val));
    }

    public void pop() {
        stack.pop();
        minStack.pop();

    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin()); // 返回-3
        minStack.pop();
        System.out.println(minStack.top()); // 返回0
        System.out.println(minStack.getMin()); // 返回-2
    }
}
