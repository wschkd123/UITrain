package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ 链表，相当于火车
 */
public class LinkList {
    //头结点
    private Node first;

    public LinkList() {
        first = null;
    }

    /**
     * 插入一个结点，在头结点后插入
     */
    public void insertFirst(long value) {
        Node node = new Node(value);
        node.next = first;
        first = node;
    }

    /**
     * 删除一个结点，在头结点后进行删除
     */
    public Node deleteFirst() {
        Node tmp = first;
        first = tmp.next;
        return tmp;
    }

    /**
     * 显示方法
     */
    public void display() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
    }

    public Node find(long value) {
        Node current = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
        return current;
    }

    public Node delete(long value) {
        Node current = first;
        Node previous = first;
        while (current.data != value) {
            if (current.next == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        return current;

//        if (current == first) {
//            first = first.next;
//        } else {
//            previous.next = current.next;
//        }
//        return current;
    }
}
