package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ
 */
public class TestMyQueue {
    public static void main(String[] args) {
        MyCycleQueue mq = new MyCycleQueue(4);
        mq.insert(2);
        mq.insert(3);
        mq.insert(10);
        mq.insert(23);
        System.out.print(mq.isFull());
        System.out.print(mq.isEmpty());
        System.out.print("\t" + mq.peek() + "\t");

        while (!mq.isEmpty()) {
            System.out.print(mq.remove() + " ");
        }

        mq.insert(2);
        mq.insert(3);
        mq.insert(10);
        mq.insert(23);

        while (!mq.isEmpty()) {
            System.out.print(mq.remove() + " ");
        }
    }
}
