package org.wq.leetcode.day5;


import java.util.Arrays;

public class NumPartition {

    /**
     * 给定一个数组和一个数，将数组左边化为 < num的集合，右侧为 > num的集合
     */
    public static void partition1(int[] arr, int num) {
        int i = -1;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] < num) {
                swap(arr, j, i + 1);
                i++;
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * 给定一个数组和一个数，将数组左边化为 < num的集合， = 放中间，右侧为 > num的集合
     */
    public static void partition2(int[] arr, int num) {
        int i = -1;
        int b = arr.length;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] < num) {
                swap(arr, j, i + 1);
                i++;
            }
            if (arr[j] > num) {
                swap(arr, j, b - 1);
            }

        }
    }

    public static void main(String[] args) {
        int[] a = {1, 5, 4, 6, 2, 3, 7, 5, 7, 9};
        partition1(a, 5);
        Arrays.stream(a).forEach(System.out::print);
    }
}
