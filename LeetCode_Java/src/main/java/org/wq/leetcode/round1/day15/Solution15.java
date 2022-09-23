package org.wq.leetcode.round1.day15;

import org.wq.leetcode.common.TreeNode;

public class Solution15 {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        TreeNode head = root;
        while (head != null) {
            TreeNode curr = head.left;
            if (curr != null) {
                while (curr.right != null) {
                    curr = curr.right;
                }

                curr.right = head.right;
                head.right = head.left;

                head.left = null;
            }
            head = head.right;
        }
    }


    // 121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 0, min = Integer.MAX_VALUE; i < prices.length; i++) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return res;
    }


    // 124. 二叉树中的最大路径和
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        process1(root);
        return res;
    }

    private int process1(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(0, process1(root.left));
        int right = Math.max(0, process1(root.right));
        res = Math.max(res, left + right + root.val);
        return Math.max(left, right) + root.val;
    }

    //543. 二叉树的直径
    int res_543 = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        process2(root);
        return res_543;
    }

    private int process2(TreeNode root) {

        if (root == null) {
            return 0;
        }
        int left = process2(root.left);
        int right = process2(root.right);
        res = Math.max(res, left + right);

        return Math.max(left, right) + 1;
    }

    // 4. 寻找两个正序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int[] arr = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (nums1[i] <= nums2[j]) {
                arr[k++] = nums1[i++];
            } else {
                arr[k++] = nums2[j++];
            }
        }

        while (i < n) {
            arr[k++] = nums1[i++];
        }

        while (j < m) {
            arr[k++] = nums2[j++];
        }

        if ((m + n) % 2 == 0) {
            return (arr[(m + n) / 2 - 1] + arr[(m + n) / 2]) / 2.0;
        } else {
            return arr[(m + n) / 2];
        }
    }


    public static void main(String[] args) {
        Solution15 s15 = new Solution15();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(s15.findMedianSortedArrays(nums1, nums2));
    }
}
