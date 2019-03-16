package com.wsc.java;

/**
 * @author shichao5
 * @date 2019/1/22
 * @describ
 */
class Student {
    private String name = "wang";
    private int age = 19;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
//        return "seq='" + name + '\'' +
//                ", msgId='" + name + '\'' +
//                ", cmdid=" + name +
//                ", expireTime=" + name +
//                ", body='" + name + '\'' +
//                '}';
        return "IMGiftBean{" + "mIsSendGift='" + name + '\'' + ",sendGiftTime='" + age + "\'" + "}";
    }
}

public class TestClass {
    public static void main(String[] args) {
        System.out.print(new Student("shichao",20).toString());
    }

}