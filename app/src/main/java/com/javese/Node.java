package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ 链结点，相当于车厢
 */
public class Node {
    //数据域
    public long data;
    //指针域
    public Node next;

    public Node(long value) {
        this.data = value;
    }

    /**
     * 显示方法
     */
    public void display() {
        System.out.print(data + "\t");
    }
}
