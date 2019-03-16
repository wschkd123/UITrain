package com.javese;

/**
 * @author shichao5
 * @date 2019/2/25
 * @describ
 */
public class TestLinkList {
    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.insertFirst(34);
        linkList.insertFirst(23);
        linkList.insertFirst(12);


        linkList.find(23).display();

    }
}
