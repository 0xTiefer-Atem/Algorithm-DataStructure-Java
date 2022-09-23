package org.wq.leetcode.round1.day6;

public class RadixSort {

    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        sort(arr, 0, arr.length - 1, getMaxDigit(arr));
    }

    private static void sort(int[] arr, int L, int R, int maxDigit) {
        final int radix = 10;
        int[] bucket = new int[arr.length];
        int i = 0;
        int j = 0;

        for (int k = 1; k <= maxDigit; k++) {

            // 词频统计数组
            // 10个空间
            int[] count = new int[radix];
            for (i = L; i < R; i++) {
                j = getDigit(arr[i], k);
                count[j]++;
            }

            // 求出最大前缀和，对数组进行分片
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], k);
                bucket[count[j] - 1] =arr[i];
                count[j]--;
            }

            for ( i = L, j = 0; i < R; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    // 获取数组中最大值的位数
    private static int getMaxDigit(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int j : arr) {
            max = Math.max(max, j);
        }

        int res = 0;

        while (max != 0) {
            res++;
            max = max / 10;
        }

        return res;
    }


    public static int getDigit(int x, int d) {

        return (x / ((int) Math.pow(1, d - 1))) % 10;
    }
}
