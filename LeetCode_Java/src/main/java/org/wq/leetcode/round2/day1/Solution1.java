package org.wq.leetcode.round2.day1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1 {

    // 435. 无重叠区间
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int res = 0;
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (end > intervals[i][0]) {
                res++;
            } else {
                end = intervals[i][1];
            }
        }

        return res;
    }

    // 452. 用最少数量的箭引爆气球
    public int findMinArrowShots(int[][] points) {
        if (points == null || points[0].length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        int end = points[0][1];
        int res = 0;
        for (int i = 1; i < points.length; i++) {
            if (end < points[i][0]) {
                res++;
                end = points[i][1];
            }
        }
        return res;
    }

    public int[] printN(int n) {
        int max = (int) Math.pow(10, n);
        int[] res = new int[max - 1];
        for (int i = 0; i < max - 1; i++) {
            res[i] = i + 1;
        }
        return res;
    }


    public static void main(String[] args) {
        Solution1 s1 = new Solution1();

//        int[][] intervals = {{1, 2}, {4, 5}, {1, 5}};
        System.out.println(Arrays.toString(s1.printN(1)));
    }
}

