package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ
 */
public class MyStack {

    private long[] arr;
    private int top;
    public MyStack() {
        arr = new long[10];
        top = -1;
    }

    public MyStack(int max) {
        arr = new long[max];
        top = -1;
    }

    /**
     * 添加数据
     * @param value
     */
    public void push(int value) {
        arr[++top] = value;
    }

    /**
     * 移除数据
     * @return
     */
    public long pop() {
        return arr[top--];
    }

    /**
     * 查看数据
     * @return
     */
    public long peek() {
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }
}
