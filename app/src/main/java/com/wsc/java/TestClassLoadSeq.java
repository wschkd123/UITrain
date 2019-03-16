package com.wsc.java;

/**
 * @author shichao5
 * @date 2018/11/8
 * @describ
 */
class Father {
    private static String s = print();
    private String t = print1();

    static {
        System.out.println("父类静态代码块初始化");
    }

    {
        System.out.println("父类代码块初始化");
    }

    public Father() {
        System.out.println("父类无参构造函数初始化完成");
        show();
    }

    public  String print1() {
        System.out.println("父类变量初始化");
        return "";
    }

    public static String print() {
        System.out.println("父类静态变量初始化");
        return "父类静态成员变量的初始化";
    }

    public void show() {
        System.out.println("父类show()方法");
    }
}

class Son extends Father {
    private int i = print2();
    private static int j = print3();

    static {
        System.out.println("子类静态代码块初始化");
    }

    private String s = "子类私有成员变量";

    {
        System.out.println("子类代码块初始化");
    }

    public int print2() {
        System.out.println("子类静态属性初始化");
        return 1;
    }

    public static int print3() {
        System.out.println("子类属性初始化");
        return 2;
    }

    public Son() {
        System.out.println("子类构造函数初始化完成");
        show();
    }

    public void show() {
        System.out.println("子类show()方法：i=" + i);
    }
}

public class TestClassLoadSeq {
    public static void main(String[] args) {
        new Son();
    }

}