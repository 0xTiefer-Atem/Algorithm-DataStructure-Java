package org.wq.leetcode.day5;

import java.util.Arrays;

public class MergeSort {
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int length = arr.length;
        process(arr, L, length - 1);
    }

    private void process(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }

        int mid = left + ((right - left) >> 1);

        process(arr, left, mid);
        process(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int i = 0;
        int p = left;
        int q = mid + 1;
        while (p <= mid && q <= right) {
            help[i++] = arr[p] <= arr[q] ? arr[p++] : arr[q++];
//            i++;
//            p++;
//            q++;
        }

        while (p <= mid) {
            help[i++] = arr[p++];
        }

        while (q <= right) {
            help[i++] = arr[q++];
        }

        for (int l = 0; l < help.length; l++) {
            arr[left + l] = help[l];
        }
    }

    public static void main(String[] args) {


        int[] a = {1, 5, 2, 9, 0, 8, 7, 6, 5};
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(a);
        Arrays.stream(a).forEach(System.out::println);

//        Arrays.sort();
    }
}
