package org.wq.leetcode.day5;


import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.stream.Stream;

public class ProgressBar {
    public static void main(String[] args) {
        printBar();
    }

    public static void printBar() {
        char incomplete = '░'; // U+2591 Unicode Character 表示还没有完成的部分
        char complete = '█'; // U+2588 Unicode Character 表示已经完成的部分
        int total = 100;
        StringBuilder builder = new StringBuilder();
        Stream.generate(() -> incomplete).limit(total).forEach(builder::append);

        System.out.println(builder);


        for (int i = 0; i < total; i++) {
            builder.replace(i, i + 1, String.valueOf(complete));
            String progressBar = "\r" + builder;
            String percent = " " + (i + 1) + "%";
            System.out.print(progressBar + percent);
            try {
                Thread.sleep(i * 5L);
            } catch (InterruptedException ignored) {

            }
        }
    }
}
