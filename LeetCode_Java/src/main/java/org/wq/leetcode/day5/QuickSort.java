package org.wq.leetcode.day5;

//&
public class QuickSort {
    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] part = partition(arr, L, R);
            quickSort(arr, L, part[0] - 1);
            quickSort(arr, L, part[1] + 1);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;

        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            }else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            }else {
                l++;
            }
        }
        swap(arr, more, r);

        return new int[]{less+1, less};
    }

    private static void swap(int[] arr, int i, int r) {
        int temp = arr[i];
        arr[i] = arr[r];
        arr[r] = temp;
    }

    public static void main(String[] args) {
        System.out.println((0-1) / 2);
    }
}
