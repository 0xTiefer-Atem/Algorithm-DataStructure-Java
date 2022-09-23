package org.wq.leetcode.round1.day5;

import java.util.Arrays;

public class HeapSort {

    public static void heapSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        int heapSize = arr.length;

        swap(arr, 0, --heapSize);

        while (heapSize > 0) {

            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }

    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[((index - 1) / 2)]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] arr, int index, int i) {
        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {

            // 右孩子如果有，两个下标的值谁更大记录较大的下标
            int largeIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            // 比较子节点和父节点值大小
            largeIndex = arr[largeIndex] > arr[index] ? largeIndex : index;

            if (index == largeIndex) {
                break;
            }

            swap(arr, index, largeIndex);
            index = largeIndex;

            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 5, 6, 7, 9, 8, 2, 7};
        heapSort(arr);

        Arrays.stream(arr).forEach(System.out::print);
    }
}
