package org.wq.leetcode.round1.day18;

import org.wq.leetcode.common.ListNode;

import java.util.PriorityQueue;

public class Solution18 {
    // 198. 打家劫舍
//    public int rob(int[] nums) {
//        int n = nums.length;
//
//        int[] dp = new int[n];
//        int max = Integer.MIN_VALUE;
//        for (int i = 2; i < n; i++) {
//            dp[i] = nums[i];
//        }
//
//    }

    //200. 岛屿数量
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1) {
            return 0;
        }

        int r = grid.length;
        int c = grid[0].length;

        int res = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    process1(grid, i, j);
                }
            }
        }

        return res;
    }

    private void process1(char[][] grid, int i, int j) {

        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }

        if (grid[i][j] == '0' || grid[i][j] == '2') {
            return;
        }

        if (grid[i][j] == '1') {
            grid[i][j] = '2';
        }


        process1(grid, i - 1, j);
        process1(grid, i + 1, j);
        process1(grid, i, j - 1);
        process1(grid, i, j + 1);
    }

    // 206. 反转链表
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }


        ListNode pre = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode node = curr.next;
            curr.next = pre;
            pre = curr;
            curr = node;
        }

        return pre;
    }


    // 215. 数组中的第K个最大元素
    public int findKthLargest(int[] nums, int k) {

        // 默认小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(k);

        for (int i : nums) {
            if (queue.size() < k) {
                queue.add(i);
            } else if (queue.peek() < i) {
                queue.poll();
                queue.offer(i);
            }
        }

        return queue.peek();
    }


    public static void main(String[] args) {
        Solution18 s18 = new Solution18();


//        char[][] nums = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
//        System.out.println(s18.numIslands(nums));


        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(s18.findKthLargest(nums, 4));

    }
}
