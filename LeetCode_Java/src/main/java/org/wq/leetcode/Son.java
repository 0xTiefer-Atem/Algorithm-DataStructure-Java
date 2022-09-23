package org.wq.leetcode;


class Base {
    private String name = "Base";

    public Base() {
        printName();
    }

    public void printName() {
        System.out.println("Base : " + name);
    }
}

public class Son extends Base {
    private String name = "Tom";

    public Son() {
        printName();
    }

    public void printName() {
        System.out.println("Son : " + name);
    }
    public static void main(String[] args) {
        Son son = new Son();
//        String ip = "123.456";
//        System.out.println(ip.replace(".", "[.]"));
    }
}
