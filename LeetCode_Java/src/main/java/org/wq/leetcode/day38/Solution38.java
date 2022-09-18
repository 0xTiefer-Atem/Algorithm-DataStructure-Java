package org.wq.leetcode.day38;

import java.util.ArrayList;
import java.util.List;

public class Solution38 {
    // 剑指 Offer II 088. 爬楼梯的最少成本
    public int minCostClimbingStairs(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }

        if (cost.length == 1) return cost[0];


        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }


        return Math.min(dp[dp.length - 1], dp[dp.length - 2]);
    }


    // 面试题 08.01. 三步问题
    // 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，
    // 小孩一次可以上1阶、2阶或3阶。实现一种方法，
    // 计算小孩有多少种上楼梯的方式。结果可能很大，
    // 你需要对结果模1000000007。
    public int waysToStep(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n] % 1000000007;
    }

    // 给定一个整数数组，找出总和最大的连续数列，并返回总和。
    public int maxSubArray(int[] nums) {
        int res = Integer.MIN_VALUE;

        int sum = 0;
        for (int num : nums) {
            sum = Math.max(num, sum + num);
            res = Math.max(res, sum);
        }

        return res;
    }

    // 面试题 17.16. 按摩师
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }


        return dp[nums.length - 1];
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {

            String s1 = palindrome(s, i, i + 1);

            res = res.length() > s1.length() ? res : s1;

            String s2 = palindrome(s, i, i);


            res = res.length() > s2.length() ? res : s2;
        }

        return res;
    }

    private String palindrome(String s, int l, int r) {
        while (l > 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }

        if (l + 1 > r) {
            return "";
        }
        return s.substring(l + 1, r);
    }

    // 22. 括号生成
    List<String> res22 = new ArrayList<String>();

    public List<String> generateParenthesis(int n) {
        StringBuilder sub = new StringBuilder();
        process1(sub, n, n);
        return res22;
    }

    private void process1(StringBuilder sub, int l, int r) {

        if (l < 0) {
            return;
        }

        if (l == 0 && r == 0) {
            res22.add(sub.toString());
            return;
        }

        sub.append("(");
        process1(sub, l - 1, r);
        sub.deleteCharAt(sub.length() - 1);

        if (l < r) {
            sub.append(")");
            process1(sub, l, r - 1);
            sub.deleteCharAt(sub.length() - 1);
        }
    }

    // 45. 跳跃游戏 II
    int res45 = Integer.MAX_VALUE;

    public int jump(int[] nums) {

        process2(nums, 0, 0, nums.length - 1);

        return res45;
    }

    private void process2(int[] nums, int startIndex, int step, int targetIndex) {

        if (startIndex > targetIndex) {
            return;
        }

        if (startIndex == targetIndex) {
            res45 = Math.min(step, res45);
        }

        int s = nums[startIndex];
        for (int i = 1; i <= s; i++) {
            process2(nums, startIndex + i, step + 1, targetIndex);
        }
    }


    public static void main(String[] args) {
        Solution38 s38 = new Solution38();

        int[] nums = {2, 3, 0, 1, 4};
        System.out.println(s38.jump(nums));
    }
}
