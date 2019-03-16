package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ 列队类
 */
public class MyQueue {
    //底层使用数组
    private long[] arr;
    //有效数据的大小
    private int elements;
    //队头
    private int front;
    //队尾
    private int end;

    public MyQueue() {
        arr = new long[10];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 带参数的构造方法
     *
     * @param maxsize 数组大小
     */
    public MyQueue(int maxsize) {
        arr = new long[maxsize];
        elements = 0;
        front = 0;
        end = -1;
    }

    /**
     * 添加数据，从队尾插入
     *
     * @param value
     */
    public void insert(long value) {
        arr[++end] = value;
        elements++;
    }

    /**
     * 删除数据，从队头删除
     */
    public long remove() {
        elements--;
        return arr[front++];
    }

    /**
     * 查看数据，从队头查看
     */
    public long peek() {
        return arr[front];
    }

    public boolean isEmpty() {
        return elements == 0;
    }

    public boolean isFull() {
        return elements == arr.length;
    }
}
