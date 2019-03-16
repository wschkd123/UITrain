package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ
 */
public class TestMyStack {

    public static void main(String[] args) {
        MyStack ms = new MyStack(4);
        ms.push(1);
        ms.push(13);
        ms.push(3);
        ms.push(90);
        System.out.print(ms.isFull());
        System.out.print(ms.peek());
        while (!ms.isEmpty()) {
            System.out.print(ms.pop() + ",");
        }

    }
}
