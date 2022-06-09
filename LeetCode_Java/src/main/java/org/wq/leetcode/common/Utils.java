package org.wq.leetcode.common;

public class Utils {

    public static void swap(Object[] objArr, int i, int j) {
        Object temp = objArr[i];
        objArr[i] = objArr[j];
        objArr[j] = temp;
    }

    public static void swap(char[] charArr, int i, int j) {
        char temp = charArr[i];
        charArr[i] = charArr[j];
        charArr[j] = temp;
    }
}
