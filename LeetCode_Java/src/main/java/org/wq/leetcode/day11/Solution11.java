package org.wq.leetcode.day11;

import java.util.HashMap;
import java.util.Hashtable;

public class Solution11 {

    // 给定一个有序数组arr，代表数轴上从左到右有n个点arr[0,1,2,3.....]
    // 给定一个正数l，代表一根绳子的长度，求绳子最多能覆盖几个点
    public static int process1(int[] arr, int L) {
        int res = Integer.MIN_VALUE;

        for (int Left = 0; Left < arr.length; Left++) {
            int R = Left;
            while (R < arr.length && (arr[R] - arr[Left]) <= L) {
                res = Math.max(res, R - Left + 1);
                R++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 8, 9, 12, 17};
        int l = 5;
    }
}
