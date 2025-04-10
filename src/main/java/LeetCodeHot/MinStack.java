package LeetCodeHot;

import java.util.PriorityQueue;
import java.util.Stack;

public class MinStack {
    private final Stack<Integer> minStack;
    private final PriorityQueue<Integer> minVals;
    public MinStack() {
        minStack = new Stack<>();
        minVals = new PriorityQueue<>();
    }

    public void push(int val) {
        minStack.add(val);
        minVals.add(val);
    }

    public void pop() {
        Integer pop = minStack.pop();
        minVals.remove(pop);
    }

    public int top() {
        return minStack.peek();
    }

    public int getMin() {
        Integer peek = minVals.peek();
        return peek == null ? Integer.MIN_VALUE : peek;
    }

}
